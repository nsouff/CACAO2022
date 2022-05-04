package abstraction.eq2Producteur2;

import abstraction.eq8Romu.produits.Feve;

public class ArbreMGB extends Arbre {
	
	public ArbreMGB() {
		super(60, 1/60, 960, 0.2 + Math.random()*0.5, 3);
	}

	public Feve getArbreFeve() {
		return Feve.FEVE_MOYENNE_BIO_EQUITABLE;
	}	


}
