package abstraction.eq4Transformateur2;

import java.util.HashMap;

import abstraction.eq8Romu.filiere.Filiere;
//auteur Marie

public class Stock<I> {
	

	private HashMap<I,Double> stock;
	
	//Nawfel
	public Stock(HashMap<I, Double> stock) {
		this.stock = stock;
	}
	
	public Stock() {
		this.stock=new HashMap<I,Double>();
	}
	
	
	
	//Marie
		public double getQuantite(I produit) {
			if (this.stock.keySet().contains(produit)) {
				return this.stock.get(produit);
			}else {
				return 0;
			}
		}
	
	
	//Nawfel
	//Faire la somme  de toutes les values
	public double getStocktotal() {
		double somme=0;
		for(Double qt :this.stock.values()) {
			somme=somme+qt;
		}
		return somme;
	}
	
	// Gabriel
//	public void setStock(double newst) {
//		this.stocktotal = this.stocktotal - newst;
//	}
	
	//Marie
	public void ajouter (I produit, double qt) {
		if (qt>0) {	
			
			if (this.stock.keySet().contains(produit)) {
				this.stock.put(produit, this.stock.get(produit)+qt); //
			}else {
				this.stock.put(produit, qt);}
	}else{
		throw new IllegalArgumentException("impossible");
	}
	}
	
	public void enlever (I produit, double qt) {
	if (qt>0) {	
		if (this.stock.keySet().contains(produit) ) {
			this.stock.put(produit, this.stock.get(produit)-qt);
	}}
	else{
		throw new IllegalArgumentException("impossible");
}
}
		

	// Marie

	public double stockRestant(I produit) {
		return (Filiere.LA_FILIERE.getParametre("Prix Stockage").getValeur()-this.getStocktotal());
	}



	
	public HashMap<I, Double> getStock() {
		return stock;
	}

	public void clear() {
		this.clear();
	}


	/*public double coutStockage( Filiere.LA_FILIERE.getIndicateur("prix_stockage")) { // demander comment ajouter variables
		return (this.quantiteStockTotale(produit)*(Filiere.LA_FILIERE.getIndicateur("prix_stockage").getValeur())); // demander a Alexandre comment calculer prix 
	}*/



//	public void setQuantite_stock(HashMap<I,Double> quantite_stock) {
//		this.quantite_stock = quantite_stock;
//	}
	
}

