package abstraction.eq1Producteur1;

import java.util.HashMap;

import abstraction.eq8Romu.produits.Feve;

public class Producteur1Producteur extends Producteur1Stock{
	private Parc ParcAfrique;
	
	public Producteur1Producteur() {
		Parc afrique = new Parc("Afrique");
		this.ParcAfrique = afrique;
	}
	
	//Écrit par Antoine
	//Répartition de nos 500 000 000 arbre parmi nos parcs (un unique parc représentant toute l'Afrique pour la V1)
	//Arbres: 63% de nonBE_basse,27% de  nonBE_moyenne, 5% de BE_moyenne, 5% de BE_haute
	//Les arbres sont créés de manière à avoir un âge aléatoire compris entre 95 et 135 ut, ceci afin d'avoir une production non-nulle à l'ut initiale et de ne pas avoir tous les arbres initiaux mourant au même moment
	public void initialiser() {
		super.initialiser();
		int nombre_arbre_debut = 500000;
		int ut_debut = -115;
		for (int i=0;i<nombre_arbre_debut;i++) {
			int d = (int)Math.random()*40;
			if (i<nombre_arbre_debut*0.63) {
				this.getAfrique().Planter(new MilleArbre(1,false,ut_debut-d));
			}
			if ((i>=nombre_arbre_debut*0.63) && (i<nombre_arbre_debut*0.9)) {
				this.getAfrique().Planter(new MilleArbre(2,false,ut_debut-d));
			}
			if ((i>=nombre_arbre_debut*0.9) && (i<nombre_arbre_debut*0.95)) {
				this.getAfrique().Planter(new MilleArbre(2,true,ut_debut-d));
			}
			if ((i>=nombre_arbre_debut*0.95)) {
				this.getAfrique().Planter(new MilleArbre(3,true,ut_debut-d));
			}
		}
	}

	public void next() { //Écrit par Antoine
		this.getAfrique().MAJAleas();
		HashMap<Feve, Double> recolteAfrique = this.getAfrique().Recolte();
		this.addLot(Feve.FEVE_BASSE, recolteAfrique.get(Feve.FEVE_BASSE));
		this.addLot(Feve.FEVE_MOYENNE, recolteAfrique.get(Feve.FEVE_MOYENNE));
		this.addLot(Feve.FEVE_HAUTE, recolteAfrique.get(Feve.FEVE_HAUTE));
		this.addLot(Feve.FEVE_MOYENNE_BIO_EQUITABLE, recolteAfrique.get(Feve.FEVE_MOYENNE_BIO_EQUITABLE));
		this.addLot(Feve.FEVE_HAUTE_BIO_EQUITABLE, recolteAfrique.get(Feve.FEVE_HAUTE_BIO_EQUITABLE));
		this.getAfrique().MAJParc();
		this.getAfrique().MAJGuerre();
	}
	
	public Parc getAfrique() {
		return this.ParcAfrique;
	}

}
