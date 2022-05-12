package abstraction.eq1Producteur1;

import java.util.HashMap;


import abstraction.eq8Romu.filiere.Filiere;


import abstraction.eq8Romu.general.Journal;

import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Feve;

public class Producteur1Producteur extends Producteur1Stock{
	private Parc ParcAfrique;
	protected Journal RetourMaladie;
	protected Journal RetourRécolte;
	protected Journal RetourGuerre;
	protected Journal RetourAléas;
	protected Journal RetourMAJParc;
	
	public Producteur1Producteur() {
		super();
		Parc afrique = new Parc("Afrique");
		this.ParcAfrique = afrique;
		//this.RetourMaladie = new Journal("Prise en compte des maladies?", this);
	}
	
	//Écrit par Antoine
	//Répartition de nos 500 000 000 arbre parmi nos parcs (un unique parc représentant toute l'Afrique pour la V1)
	//Arbres: 63% de nonBE_basse,27% de  nonBE_moyenne, 5% de BE_moyenne, 5% de BE_haute
	//Les arbres sont créés de manière à avoir un âge aléatoire compris entre 95 et 135 ut, ceci afin d'avoir une production non-nulle à l'ut initiale et de ne pas avoir tous les arbres initiaux mourant au même moment
	public void initialiser() {
		super.initialiser();
		int nombre_arbre_debut = 600000;
		double pourcentage_nBE_basse = 0.63;
		double pourcentage_nBE_moyenne = 0.27;
		double pourcentage_nBE_haute = 0;
		double pourcentage_BE_moyenne = 0.05;
		int nombre_arbre_nBE_basse = (int)Math.floor((pourcentage_nBE_basse*nombre_arbre_debut));
		int nombre_arbre_nBE_moyenne = (int)Math.floor((pourcentage_nBE_basse+pourcentage_nBE_moyenne)*nombre_arbre_debut);
		int nombre_arbre_nBE_haute = (int)Math.floor((pourcentage_nBE_basse+pourcentage_nBE_moyenne+pourcentage_nBE_haute)*nombre_arbre_debut);
		int nombre_arbre_BE_moyenne = (int)Math.floor((pourcentage_nBE_basse+pourcentage_nBE_moyenne+pourcentage_nBE_haute+pourcentage_BE_moyenne)*nombre_arbre_debut);
		int ut_debut = -115;
		for (int i=0;i<nombre_arbre_debut;i++) {
			int d = (int)Math.random()*40;
			if (i<nombre_arbre_nBE_basse) {
				this.getAfrique().Planter(new MilleArbre(1,false,ut_debut-d));
			}
			if ((i>=nombre_arbre_nBE_basse) && (i<nombre_arbre_nBE_moyenne)) {
				this.getAfrique().Planter(new MilleArbre(2,false,ut_debut-d));
			}
			if ((i>=nombre_arbre_nBE_moyenne) && (i<nombre_arbre_nBE_haute)) {
				this.getAfrique().Planter(new MilleArbre(3,false,ut_debut-d));
			}
			if ((i>=nombre_arbre_nBE_haute) && (i<nombre_arbre_BE_moyenne)) {
				this.getAfrique().Planter(new MilleArbre(2,true,ut_debut-d));
			}
			if ((i>=nombre_arbre_BE_moyenne)) {
				this.getAfrique().Planter(new MilleArbre(3,true,ut_debut-d));
			}
		}
	}

	public void next() { //Écrit par Antoine
		super.next();
		this.getAfrique().MAJAleas();
		HashMap<Feve, Double> recolteAfrique = this.getAfrique().Recolte();
		this.addLot(Feve.FEVE_BASSE, recolteAfrique.get(Feve.FEVE_BASSE));
		this.addLot(Feve.FEVE_MOYENNE, recolteAfrique.get(Feve.FEVE_MOYENNE));
		this.addLot(Feve.FEVE_HAUTE, recolteAfrique.get(Feve.FEVE_HAUTE));
		this.addLot(Feve.FEVE_MOYENNE_BIO_EQUITABLE, recolteAfrique.get(Feve.FEVE_MOYENNE_BIO_EQUITABLE));
		this.addLot(Feve.FEVE_HAUTE_BIO_EQUITABLE, recolteAfrique.get(Feve.FEVE_HAUTE_BIO_EQUITABLE));
		this.getAfrique().MAJParc();
		this.getAfrique().MAJGuerre();
		
		
		
		double prixTotal = 0 ;
		//Calcul du Prix Total de Stockage
		for (Feve f : this.getFeves().keySet()) {
			prixTotal = prixTotal + (this.getStock(f, true)*Filiere.LA_FILIERE.getParametre("Prix Stockage").getValeur()) ;
		}
		
		//Calcul Prix Entretien Arbre
		
		prixTotal = prixTotal 
				+ this.getAfrique().getNombre_BE_haute()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur() 
				+ this.getAfrique().getNombre_non_BE_haute()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*1.1 
				+ this.getAfrique().getNombre_non_BE_moyenne()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()
				+ this.getAfrique().getNombre_BE_moyenne()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*1.1 
				+ this.getAfrique().getNombre_non_BE_basse()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*0.9 ;
		
		
		//Retirer l'argent 
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), prixTotal);
	}
	
	public Parc getAfrique() {
		return this.ParcAfrique;
	}

}
