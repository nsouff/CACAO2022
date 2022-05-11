package abstraction.eq7Distributeur2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.ContratCadre;
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Distributeur2Achat extends Distributeur2Acteur implements IAcheteurContratCadre{
	public static final int EPS_ECH_OK=2;
	public static final int ECH_MAX=5;
	public static final Double PRIX_MAX=100.0;
	public static final Double PRIX_OK=50.0;
	public static final Double EPSILON_PRIX=5.0;
	
	protected List<ExemplaireContratCadre> mesContratEnTantQuAcheteur;
	
	public Distributeur2Achat() {
		super();
		this.mesContratEnTantQuAcheteur=new LinkedList<ExemplaireContratCadre>();
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
	@Override
	public void next() {
		SuperviseurVentesContratCadre c = ((SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre")));
		for (ChocolatDeMarque choc : Filiere.LA_FILIERE.getChocolatsProduits()) {
			boolean tg = true;
			if (choc.isBioEquitable()==false) {
				tg=false;
			}
			List <Double> prix = new ArrayList<Double>();
			int e = Filiere.LA_FILIERE.getEtape();
			if (stock.getQuantite(choc)<=stock.getSeuilRachat(choc)) {
				int index = 0;
				
				double ventes = 0.0;
				for (int j=1; j<6; j++) {
					ventes+=Filiere.LA_FILIERE.getVentes(choc, e-j);
				}
				double venteParStep = SuperviseurVentesContratCadre.QUANTITE_MIN_ECHEANCIER/10;
				//double chocAacheter=stock.getSeuilRachat(choc)-stock.getQuantite(choc);
				//int nmbStep = (int) Math.round(chocAacheter/venteParStep);
				int nmbStep = 10;
				Echeancier ech = new Echeancier(e+1,nmbStep,venteParStep);
				
				List<IVendeurContratCadre> vendeurs =c.getVendeurs(choc);
				for (int i=0; i<vendeurs.size(); i++) {
					journal.ajouter("BioFour propose un CC avec "+ vendeurs.get(i)+" avec le produit: "+choc);
					ContratCadre contrat = new ContratCadre((IAcheteurContratCadre)this, vendeurs.get(i), choc, ech, this.cryptogramme, tg);
					prix.add(contrat.getPrix());
					double pMin = prix.get(0);
					for (double p : prix) {
						if(p>pMin) {
							pMin=p;
							index+=1;
						}
					}
				}
				IVendeurContratCadre vendeur= vendeurs.get(index);
				ContratCadre contrat = new ContratCadre((IAcheteurContratCadre)this, vendeur, choc, ech, this.cryptogramme, tg);
				journal.ajouter("-->aboutit au contrat "+contrat);
			}
			this.actualiserIndicateurs();
			}
		}
		

		@Override
		//edgard
		public boolean achete(Object produit) {
			// TODO Auto-generated method stub
			if (produit instanceof ChocolatDeMarque && stock.getQuantite((ChocolatDeMarque)produit)<stock.getSeuilRachat((ChocolatDeMarque)produit)) {
				return true;
			}else {
				return false;
			}
			//return (produit!=null && (produit instanceof ChocolatDeMarque) && this.chocolats.contains(produit));
		}

		@Override
		//edgard
		public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat){
			// TODO Auto-generated method stub
			Echeancier eC = contrat.getEcheancier();
			
			ChocolatDeMarque choc = (ChocolatDeMarque)contrat.getProduit();
			int e = Filiere.LA_FILIERE.getEtape();
			double ventes = 0.0;
			for (int j=1; j<6; j++) {
				ventes+=Filiere.LA_FILIERE.getVentes(choc, e-j);
			}
			double venteParStep= ventes/6;
			double chocAacheter=stock.getSeuilRachat(choc)-stock.getQuantite(choc);
			int nmbStep = (int) Math.round(chocAacheter/venteParStep);
			Echeancier echOK = new Echeancier(e,nmbStep,venteParStep);
			
			if (eC.getStepFin()>ECH_MAX) {
				return null;
			}else {
				if (eC.equals(echOK)) {
					return eC;
				}else {
					if(eC.getStepFin()>echOK.getStepFin()) {
						return new Echeancier(eC.getStepDebut(),eC.getNbEcheances()-EPS_ECH_OK,eC.getQuantiteTotale()/(eC.getNbEcheances()-EPS_ECH_OK));
					}else {
						return null;
					}
				}
			}
		}

		@Override
		public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
			// TODO Auto-generated method stub
			if (contrat.getPrix()>PRIX_MAX) {
				return 0;
			}else {
				if(contrat.getPrix()==PRIX_OK) {
					return contrat.getPrix();
				}else {
					return contrat.getPrix()-EPSILON_PRIX;
				}
			}
		}

		@Override
		public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
			// TODO Auto-generated method stub
			this.stock.addProduit((ChocolatDeMarque)produit, quantite);
		}

		@Override
		public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
			// TODO Auto-generated method stub
			IVendeurContratCadre v = contrat.getVendeur();
			IAcheteurContratCadre a = contrat.getAcheteur();
			Echeancier e = contrat.getEcheancier();
			ChocolatDeMarque c = (ChocolatDeMarque) contrat.getProduit();
			Double q = contrat.getQuantiteTotale();
			System.out.println("Nouveau contrat cadre entre "+ v + "et"+ a + "pour une quantitée" + q + "de" + c + "étalé sur " + e);
		}
}
