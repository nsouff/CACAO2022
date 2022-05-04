package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

import abstraction.eq8Romu.bourseCacao.FiliereTestBourse;
import abstraction.eq8Romu.clients.FiliereTestClientFinal;
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.FiliereTestContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Distributeur2Acteur implements IActeur, IVendeurContratCadre{

	protected int cryptogramme;
	protected IStock stock;
	protected List<ChocolatDeMarque> chocolats;
	protected Journal journal;

	public Distributeur2Acteur(List<ChocolatDeMarque> chocos) {
		if (chocos==null || chocos.size()<1) {
			throw new IllegalArgumentException("Arguments non valides");
		}
		this.stock = new Stock(this);
		this.chocolats = new LinkedList<ChocolatDeMarque>();
		for (int i=0; i<chocos.size(); i++) {
			this.chocolats.add(chocos.get(i));
		}
		this.journal = new Journal(this.getNom()+" activites", this);
	}

	public String getNom() {
		return "Biofour";
	}

	public String getDescription() {
		return "Du bon bio la mmh...";
	}

	public Color getColor() {
		return new Color(1,81,8); 
	}

	public void initialiser() {
		
	}
	
	//edgard 
	//A chaque étape, on créer un contrat cadre pour acheter un produit dont le stock est inférieur au seuil
	//On réalise alors des contrats avec tous les vendeurs qui le propose afin de voir quel est leur prix
	//On compare ces prixs et on réalise finalement le contrat avec le meilleur vendeur.
	//On réalise une moyenne des achats du client final sur les 6 steps précédents pour determiner la quantité achetée par step
	//On compare notre stock au seuil limite pour determiner la quantité à acheter pour remettre au seuil
	//Pour qu'un produit soit en tg, il doit être bioéquitable
	//On réalise un contrat pour chacun des différents chocolatss produits afin de tous les avoir en stock
	//Finalement, on fait un contrat avec le vendeur le plus offrant
	public void next() {
		SuperviseurVentesContratCadre c = ((SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre")));
		for (ChocolatDeMarque choc : Filiere.LA_FILIERE.getChocolatsProduits()) {
			boolean tg = true;
			if (choc.isBioEquitable()==false) {
				tg=false;
			}
			List <Double> prix = new ArrayList<Double>();
			int e = Filiere.LA_FILIERE.getEtape();
			if (stock.getQuantite(choc)<stock.getSeuilRachat(choc)) {
				int index = 0;
				
				double ventes = 0.0;
				for (int j=1; j<6; j++) {
					ventes+=Filiere.LA_FILIERE.getVentes(choc, e-j);
				}
				double venteParStep= ventes/6;
				double chocAacheter=stock.getSeuilRachat(choc)-stock.getQuantite(choc);
				int nmbStep = (int) Math.round(chocAacheter/venteParStep);
				Echeancier ech = new Echeancier(e,nmbStep,venteParStep);
				
				List<IVendeurContratCadre> vendeurs =c.getVendeurs(choc);
				for (int i=0; i<vendeurs.size(); i++) {
					ExemplaireContratCadre contrat = c.demandeAcheteur((IAcheteurContratCadre)this, vendeurs.get(i), choc, ech, this.cryptogramme, tg);
					prix.set(i, contrat.getPrix());
					double pMax = prix.get(0);
					for (double p : prix) {
						if(p>pMax) {
							pMax=p;
							index+=1;
						}
					}
				}
				IVendeurContratCadre vendeur= vendeurs.get(index);
				ExemplaireContratCadre contrat = c.demandeAcheteur((IAcheteurContratCadre)this, vendeur, choc, ech, this.cryptogramme, tg);
			}
		}
		
	}

	
	// Renvoie la liste des filières proposées par l'acteur
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		filieres.add("TESTCCBiofour");
		return(filieres);
	}

	// Renvoie une instance d'une filière d'après son nom
	public Filiere getFiliere(String nom) {
		switch (nom) { 
		case "TESTCCBiofour" : return new FiliereTestCCBiofour();
	    default : return null;
		}
	}

	// Renvoie les indicateurs
	public List<Variable> getIndicateurs() {
		List<Variable> res = new ArrayList<Variable>();
		return res;
	}

	// Renvoie les paramètres
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		return res;
	}

	// Renvoie les journaux
	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		return res;
	}

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
		
	}

	public void notificationFaillite(IActeur acteur) {
		System.out.println("F#*! you "+acteur.getNom()+". You won't miss Biofour team");
	}

	public void notificationOperationBancaire(double montant) {
	}
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(Filiere.LA_FILIERE.getActeur(getNom()), this.cryptogramme);
	}

	@Override
	public boolean vend(Object produit) {
		// TODO Auto-generated method stub
		return (produit!=null && (produit instanceof ChocolatDeMarque) && this.chocolats.contains(produit));
	}

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return 0;
	}

}
