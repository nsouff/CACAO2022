package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Distributeur2Acteur implements IActeur{
	
	protected int cryptogramme;
	private IStock stock;

	public Distributeur2Acteur() {
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
		this.stock = new Stock(this);
	}
	
	//edgard 
	//A chaque étape, on créer un contrat cadre pour acheter un produit dont le stock est inférieur au seuil
	//On réalise alors des contrats avec tous les vendeurs qui le propose afin de voir quel est leur prix
	//On compare ces prixs et on réalise finalement le contrat avec le meilleur vendeur.
	public void next() {
		SuperviseurVentesContratCadre c = ((SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre")));
		ChocolatDeMarque choc;
		int e = Filiere.LA_FILIERE.getEtape();
		if (stock.getQuantite(choc)<stock.getSeuilRachat(choc)) {
			List<IVendeurContratCadre> vendeurs =c.getVendeurs(choc);
			for (int i=0; i<vendeurs.size(); i++) {
				double ventes = 0.0;
				for (int j=1; j<6; j++) {
					ventes+=Filiere.LA_FILIERE.getVentes(choc, e-j);
				}
				double venteParStep= ventes/6;
				double chocAacheter=stock.getSeuilRachat(choc)-stock.getQuantite(choc);
				int nmbStep = (int) Math.round(chocAacheter/venteParStep);
				Echeancier(e,nmbStep,venteParStep);
				c.demandeAcheteur(this, vendeurs.get(i), choc, null, cryptogramme, false);
				
			}
		}
		
	}

	
	// Renvoie la liste des filières proposées par l'acteur
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		return(filieres);
	}

	// Renvoie une instance d'une filière d'après son nom
	public Filiere getFiliere(String nom) {
		return Filiere.LA_FILIERE;
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

}
