package abstraction.eq4Transformateur2;

import java.util.HashMap;

import abstraction.eq8Romu.filiere.Filiere;
//auteur Marie

public class Stock<I> {
		private HashMap<I,Double> quantite;
		private double stocktotal;
		
		
	public Stock(double stocktotal) {
		this.quantite = new HashMap<I, Double>();
		this.stocktotal=stocktotal;
	}
	//Marie
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
	//Marie
	public double getQuantite(I produit) {
		if (this.quantite.keySet().contains(produit)) {
			return this.quantite.get(produit);
		}else {
			return 0;
		}
		
	}
	// Marie
	public double quantiteStockTotale(I produit) {
		for(Double d: this.quantite.values()) {
			this.stocktotal=this.stocktotal+d;
		}
		return this.stocktotal;
		
	}
	// Marie
	public double stockRestant(I produit) {
		return (Filiere.LA_FILIERE.getIndicateur("stock max")-this.stocktotal);
	}
	// Gabriel
	public void setStock(double newst) {
		this.stocktotal = this.stocktotal - newst;
	
	//Gabriel
		}
	public double getStock() {
		return this.stocktotal;
	}

}
