package abstraction.eq4Transformateur2;

import java.util.HashMap;

import abstraction.eq8Romu.filiere.Filiere;
//auteur Marie

public class Stock<I> {
		public HashMap<I,Double> quantite_stock;
		private double stocktotal;
		
		
	public Stock(double stocktotal) {
		this.setQuantite_stock(new HashMap<I, Double>());
		this.stocktotal=stocktotal;
	}
	//Marie
	public double getStocktotal() {
		return this.stocktotal;
	}
	public HashMap<I,Double> getQuantiteStock(){
		return this.quantite_stock;
	}
	// Gabriel
	public void setStock(double newst) {
		this.stocktotal = this.stocktotal - newst;
	}
	
	//Marie
	public void ajouter (I produit, double qt) {
		if (qt>0) {	
			
			if (this.getQuantite_stock().keySet().contains(produit) ) {
				this.getQuantite_stock().put(produit, this.getQuantite_stock().get(produit)+qt); //
			}else {
				this.getQuantite_stock().put(produit, qt);}
	}else{
		throw new IllegalArgumentException("impossible");
	}
	}	
	
	public void enlever (I produit, double qt) {
	if (qt>0) {	
		if (this.getQuantite_stock().keySet().contains(produit) ) {
			this.getQuantite_stock().put(produit, this.getQuantite_stock().get(produit)-qt);
	}}
	else{
		throw new IllegalArgumentException("impossible");
}
}
	//Marie
	public double getQuantite(I produit) {
		if (this.getQuantite_stock().keySet().contains(produit)) {
			return this.getQuantite_stock().get(produit);
		}else {
			return 0;
		}
		
	}
	// Marie
	public double quantiteStockTotale() {

		

		for(Double d: this.getQuantite_stock().values()) {

			this.stocktotal=this.stocktotal+d;
		}
		return this.stocktotal;}
		

	// Marie

	public double stockRestant(I produit) {
		return (Filiere.LA_FILIERE.getParametre("Prix Stockage").getValeur()-this.quantiteStockTotale());
	}




	/*public double coutStockage( Filiere.LA_FILIERE.getIndicateur("prix_stockage")) { // demander comment ajouter variables
		return (this.quantiteStockTotale(produit)*(Filiere.LA_FILIERE.getIndicateur("prix_stockage").getValeur())); // demander a Alexandre comment calculer prix 
	}*/




	public HashMap<I,Double> getQuantite_stock() {
		return quantite_stock;
	}
	public void setQuantite_stock(HashMap<I,Double> quantite_stock) {
		this.quantite_stock = quantite_stock;
	}}

