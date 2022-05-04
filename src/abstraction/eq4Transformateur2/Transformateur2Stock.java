package abstraction.eq4Transformateur2;

import java.util.HashMap;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;

//auteur Marie
public class Transformateur2Stock extends Transformateur2 {
	private Stock<Feve> stockfeve;
	private Stock<Chocolat> stockchocolat;

public double coutStockage() {
	double cout=0;
	for (Feve f : stockfeve.quantite_stock.keySet()) {
		cout=cout+ stockfeve.quantite_stock.get(f)*coutStockage;
	}
	for (Chocolat c : stockchocolat.quantite_stock.keySet()) {
		cout= cout + stockchocolat.quantite_stock.get(c)*coutStockage;
	}
	return cout;
}
		
}

