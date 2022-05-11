package abstraction.eq6Distributeur1;
import java.util.Map;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import java.util.HashMap;
import java.util.List;

public class Stock { //Emma Humeau
	

	private Map<ChocolatDeMarque,Double> stockageQte;
	protected List<Variable> stockVar;
	protected IActeur acteur;

	/**
	 * @author Nathan Souffan
	 */
	public Stock(IActeur acteur) {
		this.acteur = acteur;
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
	public double qteStockageTotale() { //emma humeau
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
	public double getStock(ChocolatDeMarque c) { //emma humeau
		return stockageQte.get(c);
	}

	/**
	 * @author Nathan Souffan
	 * @param choco le chocolat dont on veut modifier la quantité.
	 * @param qte la quantité qu'on veut ajouter ou retirer
	 */
	public void addQte(ChocolatDeMarque choco, double qte) { //emma humeau
		stockageQte.put(choco, stockageQte.get(choco)+qte);
		
		/**
		 * @author Nolann
		 * actualisation des indicateurs
		 */
		
		for(int i = 0 ; i < stockVar.size(); i++) {				
			if(((stockVar.get(i)).getNom()).equals(choco+"")) {					
				stockVar.get(i).setValeur(this.acteur,stockageQte.get(choco)+qte);
				
			}
		}
		
		
			
	
	}

	public Map<ChocolatDeMarque, Double> getMapStock() { //emma humeau
		return stockageQte;
	}
}
