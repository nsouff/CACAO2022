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
	for (Feve f : stockfeve.getQuantite_stock().keySet()) {
		cout=cout+ stockfeve.getQuantite_stock().get(f)*Transformateur2Acteur.Test.getCout();
	}
	for (Chocolat c : stockchocolat.getQuantite_stock().keySet()) {
		cout= cout + stockchocolat.getQuantite_stock().get(c)*Transformateur2Acteur.Test.getCout();
	}
	return cout;
}
		
}

