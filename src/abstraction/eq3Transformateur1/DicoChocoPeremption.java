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
	
	// ajouter un lot juste après la transfo ; Julien
	public void ajoutLot(Chocolat c, Lot lot) {
		ArrayList<Lot> liste= new ArrayList<Lot>() ;
		liste = this.get(c);
		liste.add(lot);
		this.put(c, liste);
	}
	
	// supprimer le chocolat des ventes ; Julien
	public void venteLot(Chocolat c, double nbre) {
		ArrayList<Lot> liste= new ArrayList<Lot>() ;
		liste = this.get(c);
		while (nbre>0.) {
			if (liste.get(0).getQuantite()>nbre) {
				double res=liste.get(0).getQuantite()-nbre;
				liste.get(0).setQuantite(res);
				this.put(c, liste);	
				nbre = 0.;
			} else { 
				nbre = nbre-liste.get(0).getQuantite();
				liste.remove(0);
				this.put(c, liste);
			}			
		}		
	}
}

