package abstraction.eq4Transformateur2;

import java.util.HashMap;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;

//auteur Marie
public class Transformateur2Stock extends Transformateur2 {
	private Stock<Feve> stockfeve;
	private Stock<Chocolat> stockchocolat;
	private double stocktotalfeve;
	private double stocktotalchoco;
	
	
public Transformateur2Stock(double stocktotalfeve, double stocktotalchoco) {
	this.stockchocolat = stockchocolat;
	this.stockfeve=stockfeve;
	this.stocktotalfeve=stocktotalfeve;
	this.stocktotalchoco=stocktotalchoco;
}
public Stock<Feve> getStockfeve(){
	return this.stockfeve;
}
public Stock<Chocolat> getStockchocolat(){
	return this.stockchocolat;
}

public double getStocktotalfeve() {
	return this.stocktotalfeve;
}
public double getStocktotalChoco() {
	return this.stocktotalchoco;
}


public double coutStockage() {
	double cout=0;
	for (Feve f : stockfeve.quantite_stock.keySet()) {
		cout=cout+ stockfeve.quantite_stock.get(f)*Filiere.LA_FILIERE.getIndicateur("PrixStockage");
	}
	for (Chocolat c : stockchocolat.quantite_stock.keySet()) {
		cout= cout + stockchocolat.quantite_stock.get(c)*Filiere.LA_FILIERE.getIndicateur("PrixStockage");
	}
	return cout;
}
// Marie 

public double quantiteStockTotaleFeve(Feve produit) {
	for(Feve f : stockfeve.quantite_stock.keySet()) {
		this.stocktotalfeve=this.stocktotalfeve+stockfeve.quantite_stock.get(f);
	}
	return this.stocktotalfeve;	
}
// Marie

public double quantiteStockTotaleChocolat(Chocolat produit) {
	for(Chocolat c: stockchocolat.quantite_stock.keySet()) {
		this.stocktotalchoco=this.stocktotalchoco+stockchocolat.quantite_stock.get(c);
	}
	return this.stocktotalchoco;
	
}		
}

