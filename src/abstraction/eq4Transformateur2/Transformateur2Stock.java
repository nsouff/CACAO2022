package abstraction.eq4Transformateur2;

import java.util.HashMap;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;

//auteur Marie

public class Transformateur2Stock extends Transformateur2 {
	
	private Stock<Feve> stockfeve;
	private Stock<Chocolat>  stockchocolat;
	private Stock<ChocolatDeMarque> stockchocolatdemarque;
//	private double stocktotalfeve;
//	private double stocktotalchoco; Pas besoin de ces 2 là => stockchocolat.getStockTotale()
	
// Marie	
	public Transformateur2Stock(Stock<Feve> stockfeve,Stock<Chocolat> stockchocolat, Stock<ChocolatDeMarque> stockchocolatdemarque) {
		this.stockfeve = stockfeve;
		this.stockchocolat = stockchocolat;
		this.stockchocolatdemarque=stockchocolatdemarque;
	}
	
public Stock<Feve> getStockfeve(){
	return this.stockfeve;
}
public Stock<Chocolat> getStockchocolat(){
	return this.stockchocolat;
}
public Stock<ChocolatDeMarque> getStockchocolatdemarque(){
	return this.stockchocolatdemarque;
}

// Gabriel 
//public void setStock(double newst) {
//	this.stocktotalchoco = this.stocktotalchoco - newst;
//}

//Marie
public double coutStockage() {
	double cout=0;
	/*for (Feve f : stockfeve.quantite_stock.keySet()) {
		cout=cout+ stockfeve.quantite_stock.get(f)*(Filiere.LA_FILIERE.getIndicateur("PrixStockage").getValeur());*/
	for (Feve f : stockfeve.getStock().keySet()) {
		cout=cout+ stockfeve.getStock().get(f)*this.getCout();
	}

	/*for (Chocolat c : stockchocolat.quantite_stock.keySet()) {
		cout= cout + stockchocolat.quantite_stock.get(c)*(Filiere.LA_FILIERE.getIndicateur("PrixStockage").getValeur());*/

	for (Chocolat c : stockchocolat.getStock().keySet()) {
		cout= cout + stockchocolat.getStock().get(c)*this.getCout();
	}
	return cout;
}
// Marie 

//public double quantiteStockTotaleFeve(Feve produit) {
//	for(Feve f : stockfeve.getStock().keySet()) {
//		this.stocktotalfeve=this.stocktotalfeve+stockfeve.quantite_stock.get(f);
//	}
//	return this.stocktotalfeve;	
//}
//// Marie
//
//public double quantiteStockTotaleChocolat(Chocolat produit) {
//	for(Chocolat c: stockchocolat.quantite_stock.keySet()) {
//		this.stocktotalchoco=this.stocktotalchoco+stockchocolat.quantite_stock.get(c);
//	}
//	return this.stocktotalchoco;
	
//}		
}

