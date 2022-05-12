package abstraction.eq6Distributeur1;
import java.util.Map;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.general.VariablePrivee;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

import java.util.HashMap;

public class Stock { //Emma Humeau
	

	private static Map<ChocolatDeMarque,Double> stockageQte;
	protected Map<Chocolat, Variable> stockVar;
	protected Distributeur1Acteur acteur;

	/**
	 * @author Nathan Souffan
	 */
	public Stock(Distributeur1Acteur acteur) {
		this.acteur = acteur;
		stockVar = new HashMap<Chocolat, Variable>(); 
		stockageQte = new HashMap<ChocolatDeMarque, Double>();
		initStockVar();
	}

	private void initStockVar() {
		stockVar.put(Chocolat.HQ_BE_O, new VariablePrivee("HQ_BE_O", acteur, 0));
		stockVar.put(Chocolat.HQ_BE, new VariablePrivee("HQ_BE", acteur, 0));
		stockVar.put(Chocolat.HQ_O, new VariablePrivee("HQ_O", acteur, 0));
		stockVar.put(Chocolat.HQ, new VariablePrivee("HQ", acteur, 0));
		stockVar.put(Chocolat.MQ_BE_O, new VariablePrivee("MQ_BE_O", acteur, 0));
		stockVar.put(Chocolat.MQ_BE, new VariablePrivee("MQ_BE", acteur, 0));
		stockVar.put(Chocolat.MQ_O, new VariablePrivee("MQ_O", acteur, 0));
		stockVar.put(Chocolat.MQ, new VariablePrivee("MQ", acteur, 0));
		stockVar.put(Chocolat.BQ_O, new VariablePrivee("BQ_O", acteur, 0));
		stockVar.put(Chocolat.BQ, new VariablePrivee("BQ", acteur, 0));
	}
	
	/**
	 * @author Nathan Souffan
	 * @return the coûtStockageTotale
	 */
	public double getCoûtStockageTotale() {
		//System.out.println("calcul du coût total :");
		System.out.println(Filiere.LA_FILIERE.getParametre("Prix Stockage").getValeur()* 16 * qteStockageTotale());
		/**
		 * @author Nolann
		
		double cout = 0.0;
		getMapStock().forEach((key,values)-> {
			cout = cout + getStock(key)*16;
		});*/
//		return getStock()*Filiere.LA_FILIERE.getParametre("Prix Stockage").getValeur()*16;
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
		Double res = stockageQte.get(c);
		if (res == null) {
			return 0;
		}
		return res;
	}

	/**
	 * @author Nathan Souffan
	 * @param choco le chocolat dont on veut modifier la quantité.
	 * @param qte la quantité qu'on veut ajouter ou retirer
	 */
	public void addQte(ChocolatDeMarque choco, double qte) { //emma humeau
		Double stockAct = stockageQte.get(choco);
		if (stockAct == null) {
			stockAct = 0.0;
		}
		stockageQte.put(choco, stockAct+qte);
		/**
		 * @author Nolann
		 * actualisation des indicateurs
		 */
		stockVar.get(choco.getChocolat()).setValeur(this.acteur, qte+stockAct,this.acteur.cryptogramme);	
	}

	public Map<ChocolatDeMarque, Double> getMapStock() { //emma humeau
		return stockageQte;
	}
}
