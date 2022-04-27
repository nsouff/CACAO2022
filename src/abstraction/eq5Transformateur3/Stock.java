package abstraction.eq5Transformateur3;
import java.util.ArrayList;
import java.util.HashMap;

import abstraction.eq8Romu.produits.Feve;

public class Stock<Produit> {
	private HashMap <Produit, Double> stock;
	private ArrayList <Feve> L;

public void ajouter(Produit p, double qtt) {
	this.stock.put(p, this.stock.get(p)+qtt);
}
}