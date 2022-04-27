package abstraction.eq2Producteur2;

//auteur Fiona Martin 

import java.util.HashMap;

public class Plantation {
	
	private HashMap<Arbre, Double> Proportions;
	
	public Plantation () {
		/*
		 
		NB : on part avec des plantations ! 
		
		Nombre total d'arbre nécessaire = 1 milliard (en prenant en compte la quantité de chocolat nécessaire pour l'ensemble des 3 transformateurs, 
		le rendement moyen de nos arbres et en considérant qu'on produit un peu plus de la moitié de la quantité nécessaire pour les transformateurs 
		(ATTENTION : à uniformiser avec les autres producteurs).
		
		D'après les valeurs de la filière par défaut, il y avait 18,75% (arrondi à 20%) de chocolat HGB (original et non original confondus), 
		donc comme on a 20% de HG (B et nonB), il nous faut 4% de chocolat HGB
		
		Même raisonnement à partir des valeurs par défaut de la filière pour chaque type d'arbre.
		
		MAIS ATTENTION !!!!!!!! : potentiellement augmenter la proportion de fèves bio vu le marché choisi par les autres acteurs de la filière. 
		 * 
		 */
		
		Proportions = new HashMap<Arbre, Double>(); 
		Proportions.put(Arbre.ARBRE_HGB, 4000000.0);	
		Proportions.put(Arbre.ARBRE_HG, 16000000.0);
		Proportions.put(Arbre.ARBRE_MGB, 8000000.0);
		Proportions.put(Arbre.ARBRE_MG, 32000000.0);
		Proportions.put(Arbre.ARBRE_BG, 40000000.0);
		
	}
	
		
}
