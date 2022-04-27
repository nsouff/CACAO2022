package abstraction.eq2Producteur2;

import abstraction.eq8Romu.produits.Feve;

public class ArbreMG extends Arbre {
	
	public ArbreMG() {
		super(75, 1/75, 960, 0.2 + Math.random()*0.5, 2);
	}

	public Feve getArbreFeve() {
		return Feve.FEVE_MOYENNE;
	}

}
