package abstraction.eq5Transformateur3;
import java.util.HashMap;

//karladedeler

public class Stock<Produit> {
	private HashMap <Produit, Double> stock;

public void ajouter(Produit p, double qtt) {
	if (qtt > 0) {
		this.stock.put(p, this.stock.get(p)+qtt);
	}
}

public Double getstock(Produit p) {
	return this.stock.get(p);
}

}