package abstraction.eq5Transformateur3;
import java.util.HashMap;
import java.util.Set;

//karladedeler

public class Stock<Produit> {
	private HashMap <Produit, Double> stock;

	public HashMap<Produit, Double> getStockDico() {
		return stock;
	}

	//Mise à jour les stocks
	public void ajouter(Produit p, double qtt) {
	if (qtt > 0) {
		if (this.stock.keySet().contains(p)) {
			this.stock.put(p, this.stock.get(p)+qtt);	
			}
		else {
			this.stock.put(p, qtt);
			}
		}
	}
	
	public void utiliser(Produit p, double qtt) {
	if (qtt > 0 && this.stock.get(p)-qtt >=0 ) {
		if (this.stock.keySet().contains(p)) {
			this.stock.put(p, this.stock.get(p)-qtt);	
			}
		}
	}

	// Récupérer la liste des produits en stock
	public Set<Produit> getProduitsEnStock() {
		return this.stock.keySet();
	}
	//Récupérer les stocks pour un type de produit
	public Double getstock(Produit p) {
		return this.stock.get(p);
	}

	//Récupérer la valeur du stock total (tous les types de produits)
	public Double getstocktotal(Produit p) {
		Double total = 0.0;
		for (Double q : this.stock.values()) {
			total += q;
		}
		return total;
	}

}