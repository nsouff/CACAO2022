package abstraction.eq4Transformateur2;

import java.util.HashMap;

import abstraction.eq8Romu.filiere.Filiere;
//auteur Marie

public class Stock<I> {
		public HashMap<I,Double> quantite_stock;
		private double stocktotal;
		
		
	public Stock(double stocktotal) {
		this.quantite_stock = new HashMap<I, Double>();
		this.stocktotal=stocktotal;
	}
	//Marie
	public double getStocktotal() {
		return this.stocktotal;
	}
	public HashMap<I,Double> getQuantiteStock(){
		return this.quantite_stock;
	}
	
	
	//Marie
	public void ajouter (I produit, double qt) {
		if (qt>0) {	
			
			if (this.quantite_stock.keySet().contains(produit) ) {
				this.quantite_stock.put(produit, this.quantite_stock.get(produit)+qt); //
			}else {
				this.quantite_stock.put(produit, qt);}
	}else{
		throw new IllegalArgumentException("impossible");
	}
	}	
	
	public void enlever (I produit, double qt) {
	if (qt>0) {	
		if (this.quantite_stock.keySet().contains(produit) ) {
			this.quantite_stock.put(produit, this.quantite_stock.get(produit)-qt);
	}}
	else{
		throw new IllegalArgumentException("impossible");
}
}
	//Marie
	public double getQuantite(I produit) {
		if (this.quantite_stock.keySet().contains(produit)) {
			return this.quantite_stock.get(produit);
		}else {
			return 0;
		}
		
	}
	// Marie
	public double quantiteStockTotale(I produit) {
		for(Double d: this.quantite_stock.values()) {
			this.stocktotal=this.stocktotal+d;
		}
		return this.stocktotal;
		
	}
	// Marie
	public double stockRestant(I produit) {
		return (Filiere.LA_FILIERE.getIndicateur("stock max")-this.quantiteStockTotale(produit));
	}
	public double coutStockage(Filiere.LA_FILIERE.getIndicateur("prix stockage")) { // demander comment ajouter variables
		return (this.quantiteStockTotale(produit)*Filiere.LA_FILIERE.getIndicateur("prix stockage")) // demander a Alexandre comment calculer prix 
	}
	
}
