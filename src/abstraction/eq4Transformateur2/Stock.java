package abstraction.eq4Transformateur2;

import java.util.HashMap;

public class Stock<I> {
		private HashMap<I,Double> quantite;
		
	public Stock() {
		this.quantite = new HashMap<I, Double>();
	}
	public void ajouter (I produit, double qt) {
		if (qt>0) {	
			
			if (this.quantite.keySet().contains(produit) ) {
				this.quantite.put(produit, this.quantite.get(produit)+qt);
			}else {
				this.quantite.put(produit, qt);}
	}else{
		throw new IllegalArgumentException("impossible");
	}
	}	
	
	public void enlever (I produit, double qt) {
	if (qt>0) {	
		if (this.quantite.keySet().contains(produit) ) {
			this.quantite.put(produit, this.quantite.get(produit)-qt);
	}}
	else{
		throw new IllegalArgumentException("impossible");
}
}


}
