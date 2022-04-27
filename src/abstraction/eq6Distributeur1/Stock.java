package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.produits.ChocolatDeMarque;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

public class Stock extends Distributeur1{
	
	private Map<ChocolatDeMarque,Double> prixInstantanneChoco = new HashMap<ChocolatDeMarque, Double>();
	private double prixStockageActuel;

	private Map<ChocolatDeMarque, Double> teteGondole = new HashMap<ChocolatDeMarque, Double>(); // (nom du chocolat,% en tête de gondole) 
	private Map<ChocolatDeMarque,Double> qteInstantanneChoco = new HashMap<ChocolatDeMarque, Double>();
	private double qteStockageActuel;
	private ArrayList<Double> CouplePrixQte = new ArrayList();//[prixInstantanneChoco.size()][prixInstantanneChoco.size()];
	private Map<ChocolatDeMarque,  CouplePrixQte > Stockage = new HashMap<ChocolatDeMarque,  CouplePrixQte >(); //(nom choco, table 2 entrées prix et qté)
	//private Map<Chocolat, Integer> Achat = new HashMap<Chocolat, Integer>();
	
	private Map<ChocolatDeMarque, Double> teteGondole = new HashMap<ChocolatDeMarque, Double>(); // (nom du ChocolatDeMarque,% en tête de gondole) 
	private int qteStockage;
	private Map<ChocolatDeMarque, Double> Stockage = new HashMap<ChocolatDeMarque, Double>();
	//private Map<ChocolatDeMarque, Integer> Achat = new HashMap<ChocolatDeMarque, Integer>();

	public void main() {

		double sumprix=0;
		double sumqte=0;
	
		for (Map.Entry<Chocolat, Double > entry : prixInstantanneChoco.entrySet()) {
            Chocolat KeyPrix = entry.getKey();
            Double ValuePrix = entry.getValue();
            sumprix += ValuePrix;
            CouplePrixQte.add(ValuePrix);
           }
	
		this.prixStockageActuel = sumprix;
		
		for (Map.Entry<Chocolat, Double > entry : qteInstantanneChoco.entrySet()) {
            Chocolat KeyQte = entry.getKey();
            Double ValueQte = entry.getValue();
            sumqte += ValueQte;
		}

		
		this.qteStockageActuel = sumqte;


	}
		
}	
	
//Emma Humeau 
	
	
<<<<<<< HEAD
=======
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
>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022
