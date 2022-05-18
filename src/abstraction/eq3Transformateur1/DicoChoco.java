package abstraction.eq3Transformateur1;

import java.util.HashMap;

import abstraction.eq8Romu.produits.Chocolat;

public class DicoChoco extends HashMap<Chocolat, Double>{
	
	public DicoChoco() {
		super();
		for (Chocolat c : Chocolat.values()) {
			this.put(c, 0.);
		}
	}
}
