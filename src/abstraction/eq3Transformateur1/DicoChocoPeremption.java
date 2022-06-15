package abstraction.eq3Transformateur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.produits.Chocolat;
/* anna*/
public class DicoChocoPeremption extends HashMap<Chocolat, ArrayList<Lots> >{
	
	
	public DicoChocoPeremption() {
		super();
		ArrayList<Lots> listelots= new ArrayList<Lots>() ;
		for (Chocolat c : Chocolat.values()) {
			this.put(c, listelots);
		}
	}
}

