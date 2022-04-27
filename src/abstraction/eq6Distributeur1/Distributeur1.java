package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.produits.Chocolat;

<<<<<<< HEAD
=======
import java.util.ArrayList;

>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

public class Distributeur1 extends Distributeur1Acteur {
<<<<<<< HEAD
	private Map<Chocolat,Double> prixInstantanneChoco = new HashMap<Chocolat, Double>();
=======
	private static final ArrayList<Double> NULL = null;
	private double prixInstantane;
>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022
	private double prixStockageActuel;
	private Map<Chocolat, Double> teteGondole = new HashMap<Chocolat, Double>(); // (nom du chocolat,% en tête de gondole) 
	private int qteStockage;
<<<<<<< HEAD
	private Map<Chocolat, ArrayList<Double>[][] > Stockage = new HashMap<Chocolat, ArrayList<Double>[][] >(); //(nom choco, table 2 entrées prix et qté)
	//private Map<Chocolat, Integer> Achat = new HashMap<Chocolat, Integer>();
=======
	private Map<Chocolat, Double> Stockage = new HashMap<Chocolat, Double>(); 
	private Map<Chocolat, Integer> Achat = new HashMap<Chocolat, Integer>();
	private Map<Chocolat, ArrayList<Double>> prixVente = new HashMap<Chocolat, ArrayList<Double>>();
>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022


//github.com/nsouff/CACAO2022
	public Distributeur1() {
		super();
	}
	


<<<<<<< HEAD
=======
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
>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022
		
<<<<<<< HEAD
=======
/*@author Nolann

=======
>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022
>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022
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
//V1 : 
	public Map<Chocolat, ArrayList<Double>> prixVente( Map<Chocolat,Double> prixAchat,  Map<Chocolat,Double> quantiteAchete){
		prixAchat.forEach((key,value)->{
			prixVente.put(key, NULL);
			(prixVente.get(key)).add(prixAchat.get(key)*2);
			(prixVente.get(key)).add(quantiteAchete.get(key));
				
		});
		
		return prixVente;
	}

	
	
	
}
