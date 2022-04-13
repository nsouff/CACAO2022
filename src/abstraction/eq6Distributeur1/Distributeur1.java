package abstraction.eq6Distributeur1;

import java.util.Map;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

public class Distributeur1 extends Distributeur1Acteur {
	private double prixInstantane;
	private double prixStockage;
	private double prixStockageActuel;
	private Map<String, Double> teteGondole = new HashMap<String, Double>(); // (nom du chocolat,% en tête de gondole) 
	
	public Distributeur1() {
		super();
	}
	
	/** @author Nolann
	 * 
	 */
	
	
	/* FONCTION POUR FIXER LES PRIX DE VENTE :
	 * 
	 * ENTREES : prixAchat, quantiteAchetee
	 * SORTIES : HashMap(chocolat,prix) 
	 * 
	 * V1.0 : 
	 * - prix fixe 
	 * - marge fixe (différente pour chaque produit)
	 * - raisonner en marge du prix d'achat
	 * 
	 * V1.1 :
	 * - marge en fonction de la stratégie d'équipe : 
	 *  	faible marge sur tous les produits faible 
	 *  	
	 *  	
	 * - coût des stocks anticipation
	 *  
	 * 
	 * 
	 * V2 :
	 * - 
	 * 
	 */
	private Map<String, Double> prixVente = new HashMap<String, Double>();
	public Map<String, Double> prixvente()
	
	
}
