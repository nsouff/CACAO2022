package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.produits.Chocolat;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

public class Stock extends Distributeur1{
	
	private Map<Chocolat,Double> prixInstantanneChoco = new HashMap<Chocolat, Double>();
	private double prixStockageActuel;
	private Map<Chocolat, Double> teteGondole = new HashMap<Chocolat, Double>(); // (nom du chocolat,% en tête de gondole) 
	private int qteStockage;
	private Map<Chocolat, Double> Stockage = new HashMap<Chocolat, Double>(); //(nom choco, table 2 entrées prix et qté)
	//private Map<Chocolat, Integer> Achat = new HashMap<Chocolat, Integer>();
	
	public void main() {
		for (Map.Entry<Chocolat, Double > entry : prixInstantanneChoco.entrySet()) {
            Chocolat keyPrix = entry.getKey();
            Double valuePrix = entry.getValue();

		for (int i=0; i<qteStockage; i++){
			Stockage[0][i].add(valuePrix)
		}

	
		for (Map.Entry<String, ArrayList<Double> > entry : Stockage.entrySet()) {
            String keyStockage = entry.getKey();
            ArrayList<Double> valueStockage = entry.getValue();
        
		this.prixStockageActuel = 
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
	
	
	public Double getStockage(Chocolat chocolat) {//leorouppert
		return this.Stockage.get(chocolat);//Retourne le stock du chocolat demandé
	}
	public void setStockage(Chocolat chocolat, Double quantite) {//leorouppert
		this.Stockage.put(chocolat, quantite);//Modifie la valeur du stock du chocolat associé
	}

}
