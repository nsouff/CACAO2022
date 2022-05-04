package abstraction.eq2Producteur2;

import java.util.HashMap;

public class Parcelle {
	
private HashMap<Arbre, Double> ParcelleArbre;
	
	public Parcelle () {
		/*
		 
		PARC INITIAL :  
		
		Nombre total d'arbre nécessaire = 1 milliard (en prenant en compte la quantité de chocolat nécessaire pour l'ensemble des 3 transformateurs, 
		le rendement moyen de nos arbres et en considérant qu'on produit un peu plus de la moitié de la quantité nécessaire pour les transformateurs 
		(ATTENTION : à uniformiser avec les autres producteurs).
		
		On considère que la quantité initial d'arbres (au début de la simulation) est de 500 millions d'arbres. 
		
		
		D'après les valeurs de la filière par défaut, il y avait 18,75% (arrondi à 20%) de chocolat HGB (original et non original confondus), 
		donc comme on a 20% de HG (B et nonB), il nous faut 4% de chocolat HGB
		
		Même raisonnement à partir des valeurs de la filière par défaut pour chaque type d'arbre.
		
		MAIS ATTENTION !!!!!!!! : potentiellement augmenter la proportion de fèves bio vu le marché choisi par les autres acteurs de la filière. 
		 * 
		 */
		
		ParcelleArbre = new HashMap<Arbre, Double>(); 
		
		ParcelleArbre.put(Arbre.ARBRE_HGB, 500_000.0);	
		ParcelleArbre.put(Arbre.ARBRE_HG, 500_000.0);
		ParcelleArbre.put(Arbre.ARBRE_MGB, 500_000.0);
		ParcelleArbre.put(Arbre.ARBRE_MG, 500_000.0);
		ParcelleArbre.put(Arbre.ARBRE_BG, 500_000.0);

	}
	

}
