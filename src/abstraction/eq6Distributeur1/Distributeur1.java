package abstraction.eq6Distributeur1;

import java.util.Map;

import abstraction.eq8Romu.produits.Chocolat;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

public class Distributeur1 extends Distributeur1Acteur {
	private static final ArrayList<Double> NULL = null;
	private double prixInstantane;
	private double prixStockageActuel;
	private Map<String, Double> teteGondole = new HashMap<String, Double>(); // (nom du chocolat,% en tête de gondole) 
	private int qteStockage;
	private Map<String, Double> Stockage = new HashMap<String, Double>(); 
	private Map<String, Integer> Achat = new HashMap<String, Integer>();


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
		

	//@author Nolann

	/* FONCTION POUR FIXER LES PRIX DE VENTE :
	 * 
	 * ENTREES : prixAchat, quantiteAchetee, variable prixVente
<<<<<<< HEAD
	 * SORTIES : HashMap(chocolat,(prix,quantité)) 
=======
	 * SORTIES : prix 
>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022
	 * 
	 * V1.0 : 
	 * - prix fixe = prix achat*2
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

	
	private Map<Chocolat, ArrayList<Double>> prixVente = new HashMap<Chocolat, ArrayList<Double>>();
//V1 : 
	public Map<Chocolat, ArrayList<Double>> prixvente( Map<Chocolat,Double> prixAchat,  Map<Chocolat,Double> quantiteAchete){
		prixAchat.forEach((key,value)->{
			prixVente.put(key, NULL);
			(prixVente.get(key)).add(prixAchat.get(key)*2);
			(prixVente.get(key)).add(quantiteAchete.get(key));
			
			
		});
		
		/*for (Chocolat key : prixAchat.keyEntry()) {
			prixVente.put(prixAchat.getKey());
			prixVente.get(mapentry).add(prixAchat.get(mapentry));
			prixVente.get(mapentry).add(quantiteAchete.get(mapentry));
			
		}*/
		return prixVente;
	}
	//private Map<String, Double> prixVente = new HashMap<String, Double>();

	
	
}
