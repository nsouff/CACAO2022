package abstraction.eq4Transformateur2;

import java.util.HashMap;

import abstraction.eq8Romu.filiere.Filiere;
//auteur Marie

public class Stock<I> {
		private HashMap<I,Double> quantite;
		private double stocktotal;
		
	public Stock(double stocktotal){
		this.stocktotal=stocktotal;
	}
		
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
	public double getQuantite(I produit) {
		if (this.quantite.keySet().contains(produit)) {
			return this.quantite.get(produit);
		}else {
			return 0;
		}
		
	}
	public double quantiteStockTotale(I produit) {
		for(Double d: this.quantite.values()) {
			stocktotal=stocktotal+d;
		}
		return stocktotal;
		
	}
	
	public double stockRestant(I produit) {
		return (Filiere.LA_FILIERE.getIndicateur("stock max")-this.stocktotal);
	}

}
