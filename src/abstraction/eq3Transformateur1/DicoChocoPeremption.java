package abstraction.eq3Transformateur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.produits.Chocolat;
/* anna*/
public class DicoChocoPeremption extends HashMap<Chocolat, ArrayList<Lot> >{
	
	
	public DicoChocoPeremption() {
		super();
		ArrayList<Lot> listelots= new ArrayList<Lot>() ;
		for (Chocolat c : Chocolat.values()) {
			this.put(c, listelots);
		}
	}
}

