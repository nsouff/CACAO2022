package abstraction.eq6Distributeur1;

import java.util.Map;

import abstraction.eq8Romu.produits.Chocolat;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

public class Distributeur1 extends Distributeur1Acteur {
	private double prixInstantane;
	private double prixStockageActuel;
	private Map<Chocolat, Double> teteGondole = new HashMap<Chocolat, Double>(); // (nom du chocolat,% en tête de gondole) 
	private int qteStockage;
	private Map<Chocolat, Double> Stockage = new HashMap<Chocolat, Double>(); 
	private Map<Chocolat, Integer> Achat = new HashMap<Chocolat, Integer>();


//github.com/nsouff/CACAO2022
	public Distributeur1() {
		super();
	}
	
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
	public Double getStockage(Chocolat chocolat) {//leorouppert
		return this.Stockage.get(chocolat);
	}

	/**
	 * @param stockage the stockage to set
	 */
	public void setStockage(Chocolat chocolat, Double stock) {//leorouppert
		this.Stockage.put(chocolat, stock);
	}

	/**
	 * @return the prixStockage
	 */
	public double getPrixStockage() {
		return prixStockage;
	}
		
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

	
	
}
