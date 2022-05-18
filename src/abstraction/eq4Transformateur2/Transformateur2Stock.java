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
	
// Marie	
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
// Gabriel 
public void setStock(double newst) {
	this.stocktotalchoco = this.stocktotalchoco - newst;
}

//Marie
public double coutStockage() {
	double cout=0;
	/*for (Feve f : stockfeve.quantite_stock.keySet()) {
		cout=cout+ stockfeve.quantite_stock.get(f)*(Filiere.LA_FILIERE.getIndicateur("PrixStockage").getValeur());*/
	for (Feve f : stockfeve.getQuantite_stock().keySet()) {
		cout=cout+ stockfeve.getQuantite_stock().get(f)*this.getCout();
	}

	/*for (Chocolat c : stockchocolat.quantite_stock.keySet()) {
		cout= cout + stockchocolat.quantite_stock.get(c)*(Filiere.LA_FILIERE.getIndicateur("PrixStockage").getValeur());*/

	for (Chocolat c : stockchocolat.getQuantite_stock().keySet()) {
		cout= cout + stockchocolat.getQuantite_stock().get(c)*this.getCout();
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

