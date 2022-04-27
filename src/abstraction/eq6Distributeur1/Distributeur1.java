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
	private static final ArrayList<Double> NULL = null;
	private double prixInstantane;
	private double prixStockageActuel;
	private Map<Chocolat, Double> teteGondole = new HashMap<Chocolat, Double>(); // (nom du chocolat,% en tête de gondole) 
	private int qteStockage;
	private Map<Chocolat, Double> Stockage = new HashMap<Chocolat, Double>(); 
	private Map<Chocolat, Integer> Achat = new HashMap<Chocolat, Integer>();
	private Map<Chocolat, ArrayList<Double>> prixVente = new HashMap<Chocolat, ArrayList<Double>>();


//github.com/nsouff/CACAO2022
	public Distributeur1() {
		super();
	}
	


		
/*@author Nolann
	//@author Nolann

=======
>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022
	/* FONCTION POUR FIXER LES PRIX DE VENTE :
	 * 
	 * ENTREES : prixAchat, quantiteAchetee, variable prixVente
	 * SORTIES : HashMap(chocolat,(prix,quantité)) 
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
