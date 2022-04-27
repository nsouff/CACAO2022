package abstraction.eq2Producteur2;

import abstraction.eq8Romu.produits.Feve;

public class ArbreHGB extends Arbre {
	
	public ArbreHGB() {
		super(60, 60, 960, 0.2 + Math.random()*0.5, 3);
	}

	public Feve getArbreFeve() {
		return Feve.FEVE_HAUTE_BIO_EQUITABLE;
	}
}

