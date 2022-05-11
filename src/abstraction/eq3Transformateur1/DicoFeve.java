package abstraction.eq3Transformateur1;

import java.util.HashMap;

import abstraction.eq8Romu.produits.Feve;

public class DicoFeve extends HashMap<Feve, Double>{
	
	public DicoFeve() {
		super();
		for (Feve f : Feve.values()) {
			this.put(f, 0.);
		}
	}

}
