package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.produits.ChocolatDeMarque;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

public class Stock extends Distributeur1{
	
	private Map<ChocolatDeMarque,Double> prixInstantanneChoco = new HashMap<ChocolatDeMarque, Double>();
	private double prixStockageActuel;
	private Map<ChocolatDeMarque, Double> teteGondole = new HashMap<ChocolatDeMarque, Double>(); // (nom du ChocolatDeMarque,% en tête de gondole) 
	private int qteStockage;
	private Map<ChocolatDeMarque, Double> Stockage = new HashMap<ChocolatDeMarque, Double>();
	//private Map<ChocolatDeMarque, Integer> Achat = new HashMap<ChocolatDeMarque, Integer>();
	
	public void main() {
		for (Map.Entry<ChocolatDeMarque, Double > entry : prixInstantanneChoco.entrySet()) {
            ChocolatDeMarque keyPrix = entry.getKey();
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
	
	public Map<ChocolatDeMarque, Double> getMapStock() {//leorouppert
		return this.Stockage;//Retourne toute la hashmap de notre stock
	}
	public Double getStockage(ChocolatDeMarque chocolat) {//leorouppert
		return this.Stockage.get(chocolat);//Retourne le stock du chocolat demandé
	}
	public void setStockage(ChocolatDeMarque chocolat, Double quantite) {//leorouppert
		this.Stockage.put(chocolat, quantite);//Modifie la valeur du stock du chocolat associé
	}

}
