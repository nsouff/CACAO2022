package abstraction.eq2Producteur2;


import abstraction.eq8Romu.produits.Feve;

public class ArbreBG extends Arbre {
	
	public ArbreBG() {
		super(75, 75, 960, 0.2 + Math.random()*0.5, 2);
	}

	public Feve getArbreFeve() {
		return Feve.FEVE_BASSE;
	}

}
