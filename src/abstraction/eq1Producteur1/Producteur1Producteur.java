package abstraction.eq1Producteur1;

import java.util.HashMap;

import abstraction.eq8Romu.produits.Feve;

public class Producteur1Producteur extends Producteur1Stock{
	
	public Producteur1Producteur() {
		Parc afrique = new Parc("Afrique");
	}
	
	public void initialiser(Parc parc) { //Écrit par Antoine
		int nombre_arbre_debut = 500000000;
		for (int i=0;i<nombre_arbre_debut;i++) {
			if (i<nombre_arbre_debut*0.63) {
				parc.Planter(new Arbre(1,false,0));
			}
			if ((i>=nombre_arbre_debut*0.63) && (i<nombre_arbre_debut*0.9)) {
				parc.Planter(new Arbre(2,false,0));
			}
			if ((i>=nombre_arbre_debut*0.9) && (i<nombre_arbre_debut*0.95)) {
				parc.Planter(new Arbre(2,true,0));
			}
			if ((i>=nombre_arbre_debut*0.95)) {
				parc.Planter(new Arbre(3,true,0));
			}
		}
	}
	public void next(Parc parc) { //Écrit par Antoine
		parc.MAJAleas();
		HashMap<Feve, Double> recolte = parc.Recolte();
		this.addLot(Feve.FEVE_BASSE, recolte.get(Feve.FEVE_BASSE));
		this.addLot(Feve.FEVE_MOYENNE, recolte.get(Feve.FEVE_MOYENNE));
		this.addLot(Feve.FEVE_HAUTE, recolte.get(Feve.FEVE_HAUTE));
		this.addLot(Feve.FEVE_MOYENNE_BIO_EQUITABLE, recolte.get(Feve.FEVE_MOYENNE_BIO_EQUITABLE));
		this.addLot(Feve.FEVE_HAUTE_BIO_EQUITABLE, recolte.get(Feve.FEVE_HAUTE_BIO_EQUITABLE));
		parc.MAJParc();
		parc.MAJGuerre();
	}
}
