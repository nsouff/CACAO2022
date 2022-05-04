package abstraction.eq2Producteur2;

//auteure : Fiona Martin 

import java.util.HashMap;
import java.util.Map;

public class Plantation {
	
	private HashMap<Arbre, Double> Proportions;
	
	public Plantation () {
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
		
		Proportions = new HashMap<Arbre, Double>(); 
		Proportions.put(Arbre.ARBRE_HGB, 20_000_000.0);	
		Proportions.put(Arbre.ARBRE_HG, 80_000_000.0);
		Proportions.put(Arbre.ARBRE_MGB, 40_000_000.0);
		Proportions.put(Arbre.ARBRE_MG, 160_000_000.0);
		Proportions.put(Arbre.ARBRE_BG, 200_000_000.0);

	}
	
	public void renouvellement() {
		/* 
		 * Utiliser la demande des 5 dernières années pour définir la nouvelle répartition des différentes gammes d'arbres. 
		 * Fixer un seuil en termes de nombres d'arbres et / ou de quantité de stock. 
		 * 
		 *  Pas très optimisé : on parcout tous les arbres mais le problème est qu'ils ont chacun une durée de vie aléatoire. 
		 *  Une solution serait plutôt de les planter par groupes et définir un instant de plantation et une durée de vie pour l'ensemble des arbres qui sont plantés à ce moment donné. 
		 */	
		for (Map.Entry m :Proportions.entrySet()) {
			if ( ((Arbre) m.getKey()).getAge() == ((Arbre) m.getKey()).getDureeVie() ) {
								
			}
		}
	}
	
	
	
	
	
		
}
