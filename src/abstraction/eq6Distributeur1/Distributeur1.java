package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.produits.Chocolat;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

public class Distributeur1 extends Distributeur1Acteur {

	private Map<Chocolat,Double> prixInstantanneChoco = new HashMap<Chocolat, Double>();

	private double prixInstantane;
	private double prixStockageActuel;
	private Map<Chocolat, Double> teteGondole = new HashMap<Chocolat, Double>(); // (nom du chocolat,% en tête de gondole) 
	private int qteStockage;
	private Map<Chocolat, ArrayList<Double>> Stockage = new HashMap<Chocolat, ArrayList<Double>>(); //(nom choco, table 2 entrées prix et qté)

	private Map<Chocolat, Double> Stockage = new HashMap<Chocolat, Double>(); 
	private Map<Chocolat, Integer> Achat = new HashMap<Chocolat, Integer>();
<<<<<<< HEAD
	
=======
	private Map<Chocolat, ArrayList<Double>> prixVente = new HashMap<Chocolat, ArrayList<Double>>();
>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022


//github.com/nsouff/CACAO2022
	public Distributeur1() {
		super();
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
		return (this.Stockage).get(chocolat);
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

