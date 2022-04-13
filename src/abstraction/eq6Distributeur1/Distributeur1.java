package abstraction.eq6Distributeur1;

import java.util.Map;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

public class Distributeur1 extends Distributeur1Acteur {
	private double prixInstantane;
	private double prixStockage;
<<<<<<< HEAD
	private double prixStockageActuel;
	private Map<String, Double> teteGondole = new HashMap<String, Double>(); // (nom du chocolat,% en tête de gondole) 
	
	private int qteStockage;
	private Map<String, Double> Stockage = new HashMap<String, Double>();
	private Map<String, Integer> Achat = new HashMap<String, Integer>();


>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022
	public Distributeur1() {
		super();
	}
<<<<<<< HEAD
	
//Emma Humeau a écrit ce code pour la gestion du stockage
	
	/**
	 * @return the prixStockageActuel
	 */
	public double getPrixStockageActuel() {
		return prixStockageActuel;
	}

	/**
	 * @param prixStockageActuel the prixStockageActuel to set
	 */
	public void setPrixStockageActuel(double prixStockage, int qteStockage) {
		this.prixStockageActuel = prixStockage*qteStockage;
	}

	/**
	 * @return the qteStockage
	 */
	public int getQteStockage() {
		return qteStockage;
	}

	/**
	 * @param qteStockage the qteStockage to set
	 */
	
	public void setQteStockage(int qteStockage, Map Achat) {
		this.qteStockage = qteStockage ;
	}

	/**
	 * @return the stockage
	 */
	public Map<String, Double> getStockage() {
		return Stockage;
	}

	/**
	 * @param stockage the stockage to set
	 */
	public void setStockage(Map<String, Double> stockage) {
		Stockage = stockage;
	}

	/**
	 * @return the prixStockage
	 */
	public double getPrixStockage() {
		return prixStockage;
	}
		
=======
	/* FONCTION POUR FIXER LES PRIX DE VENTE :
	 * 
	 * ENTREES : prixAchat, quantiteAchetee
	 * SORTIES : prix 
	 * 
	 * V1.0 : 
	 * - prix fixe 
	 * - marge fixe (différente pour chaque produit)
	 * - raisonner en marge du prix d'achat
	 * 
	 * V1.1 :
	 * - 
	 *  
	 * 
	 * 
	 * V2 :
	 * - 
	 * 
	 */
	private Map<String, Double> prixVente = new HashMap<String, Double>();
>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022

	
	
}
