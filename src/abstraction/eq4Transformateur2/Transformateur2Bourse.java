package abstraction.eq4Transformateur2;

import java.util.List;

import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.bourseCacao.ExempleAbsAcheteurBourseCacao;
import abstraction.eq8Romu.bourseCacao.IAcheteurBourse;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IFabricantChocolatDeMarque;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;

// Marie et JAd

public class Transformateur2Bourse extends Transformateur2Transfo implements IAcheteurBourse{
	
	private double quantiteEnKg;
	

	public Transformateur2Bourse() {
		super();
		this.quantiteEnKg=quantiteEnKg;


	}

	public void next() {
		super.next();
		if(Filiere.LA_FILIERE.getEtape()>2) {
			BourseCacao bourse = (BourseCacao)(Filiere.LA_FILIERE.getActeur("BourseCacao"));
			double somme1 = 0;
			for (int i=0; i<3;i++ ) {
				somme1 = somme1 +  bourse.getCours(Feve.FEVE_BASSE).getValeur(Filiere.LA_FILIERE.getEtape()-3+i);
			}
			this.prixMinB.setValeur(superviseur, somme1/3); 
			double somme2 = 0;
			for (int i=0; i<3;i++ ) {
				somme2 = somme2 +  bourse.getCours(Feve.FEVE_MOYENNE).getValeur(Filiere.LA_FILIERE.getEtape()-3+i);
			}
			this.prixMinM.setValeur(superviseur, somme2/3);
		}
		this.journal.ajouter("La moyenne des cours pour la fève basse sur les trois derniers mois est"+ this.prixMinB.getValeur() + "");
		this.journal.ajouter("La moyenne des cours pour la fève moyenne sur les trois derniers mois est"+ this.prixMinM.getValeur()+ "");
	}
	public void initialiser() {
		super.initialiser();
	}
	
//Marie et Jad
//	public double demande(Feve f, double cours) {
//		if (cours < this.getPrixSeuil().getValeur()) {
//			if(!f.isBioEquitable()) {
//				
//				//On verifie qu'on a la capacite de paiemeent nécessaire, puis on demande
//				if (Filiere.LA_FILIERE.getBanque().verifierCapacitePaiement(this, this.cryptogramme, cours*Math.max(0.001,this.getStockReferenceFeve().getQuantite(f)-this.getStockfeve().getQuantite(f)))) {
//				return Math.max(0,this.getStockReferenceFeve().getQuantite(f)-this.getStockfeve().getQuantite(f));
//			}
//				else { 
//					return 0.0;}}
//			
//		} 
//		else {
//			return 0.0;
//		}
//		return 0.0;
//		}
	
//Jad
	//déroulement :

	//1)comparaison cours et prix seuil pour la feve
	//2)vérifiaction des besoins
	//3)vérification des capacités de paiement
	//4)achat si totu est validé
	public double demande(Feve f, double cours) {

			if(cours<this.getPrixSeuil(f).getValeur()) {
				double besoin=Math.max(0.1,this.getStockReferenceFeve().getQuantite(f)-this.getStockfeve().getQuantite(f));
				if(Filiere.LA_FILIERE.getBanque().verifierCapacitePaiement(this, this.cryptogramme, cours*besoin)){
					return besoin;
				}
			}
		
		
		return 0.00;
	}
	


//Marie et Jad
	public void notificationAchat(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		this.getStockfeve().ajouter(f,quantiteEnKg);
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), coursEnEuroParKg*quantiteEnKg);
		this.journal.ajouter("Achat de "+quantiteEnKg+" kg de fèves "+f+" pour "+coursEnEuroParKg +" €/kg");
	}

//Marie et Jad
	public void notificationBlackList(int dureeEnStep) {
		//this.journal.ajouter("Aie... je suis blackliste... Nous sommes pauvres... :(");
		
	}

	
}
