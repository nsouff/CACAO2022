package abstraction.eq6Distributeur1;
import java.util.Map;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import java.util.HashMap;

public class Stock extends Distributeur1{ //Emma Humeau
	

	private Map<ChocolatDeMarque,Double> stockageQte;

	/**
	 * @author Nathan Souffan
	 */
	public Stock() {
		System.out.println("Création stock");
		stockageQte = new HashMap<ChocolatDeMarque, Double>();
	}
	
	/**
	 * @author Nathan Souffan
	 * @return the coûtStockageTotale
	 */
	public double getCoûtStockageTotale() {
		return Filiere.LA_FILIERE.getParametre("Prix Stockage").getValeur()* 16 * qteStockageTotale();
	}

	/**
	 * @author Nathan Souffan
	 * @return the qteStockageTotale
	 */
	public double qteStockageTotale() {
		double sumqte=0;
		//calcule la qte du stockage total
		for (Double qte : stockageQte.values()) {
           sumqte += qte;
		}
		return sumqte;
	}

	/**
	 * @author Nathan Souffan
	 * @param c le chocolat dont on veut avoir la quantité stocké
	 * @return la quantité demandé
	 */
	public double getStock(ChocolatDeMarque c) {
		return stockageQte.get(c);
	}

	/**
	 * @author Nathan Souffan
	 * @param choco le chocolat dont on veut modifier la quantité.
	 * @param qte la quantité qu'on veut ajouter
	 */
	public void addQte(ChocolatDeMarque choco, double qte) {
		stockageQte.put(choco, stockageQte.get(choco)+qte);
	}

	public Map<ChocolatDeMarque, Double> getMapStock() {
		return stockageQte;
	}
}
