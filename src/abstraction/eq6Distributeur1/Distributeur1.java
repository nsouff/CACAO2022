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
	private double prixStockageActuel;
	private Map<Chocolat, Double> teteGondole = new HashMap<Chocolat, Double>(); // (nom du chocolat,% en tête de gondole) 
	private int qteStockage;
	private Map<Chocolat, ArrayList<Double>[][] > Stockage = new HashMap<Chocolat, ArrayList<Double>[][] >(); //(nom choco, table 2 entrées prix et qté)
	//private Map<Chocolat, Integer> Achat = new HashMap<Chocolat, Integer>();


//github.com/nsouff/CACAO2022
	public Distributeur1() {
		super();
	}
	
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
