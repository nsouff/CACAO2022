package abstraction.eq2Producteur2;

//auteure : Fiona Martin 

import java.util.HashMap;
import java.util.Map;

public class Plantation {
	
	private HashMap<Parcelle, Double> NbParcelles;
	
	public Plantation () {
		/*
		 
		PARC INITIAL :  
		
		Nombre total d'arbre nécessaire = 1 milliard (en prenant en compte la quantité de chocolat nécessaire pour l'ensemble des 3 transformateurs, 
		le rendement moyen de nos arbres et en considérant qu'on produit un peu plus de la moitié de la quantité nécessaire pour les transformateurs 
		(ATTENTION : à uniformiser avec les autres producteurs).
		
		On considère que la quantité initiale d'arbres (au début de la simulation) est de 500 millions d'arbres. 
			
		D'après les valeurs de la filière par défaut, il y avait 18,75% (arrondi à 20%) de chocolat HGB (original et non original confondus), 
		donc comme on a 20% de HG (B et nonB), il nous faut 4% de chocolat HGB
		
		Même raisonnement à partir des valeurs de la filière par défaut pour chaque type d'arbre.
		
		 */
		
		NbParcelles = new HashMap<Parcelle, Double>(); 
		NbParcelles.put(new Parcelle(Arbre.ARBRE_HGB), 20.0);	
		NbParcelles.put(new Parcelle(Arbre.ARBRE_HG), 80.0);
		NbParcelles.put(new Parcelle(Arbre.ARBRE_MGB), 40.0);
		NbParcelles.put(new Parcelle(Arbre.ARBRE_MG), 160.0);
		NbParcelles.put(new Parcelle(Arbre.ARBRE_BG), 200.0);

	}
	
	public void renouvellement() {
		/* 
		 * Pour le moment on replante automatiquement les arbres qui sont arrivés au bout de leur durée de vie en conservant le type d'arbre.  
		 */	
		for (Map.Entry m :NbParcelles.entrySet()) {
			if ( ((Parcelle) m.getKey()).getAge() == ((Parcelle) m.getKey()).getTypeArbre().getDureeVie() ) {
				NbParcelles.replace((Parcelle) m.getKey(), (double)m.getValue() - 1);
				NbParcelles.put(new Parcelle(((Parcelle) m.getKey()).getTypeArbre()), 1.0);
				// on supprime l'ancienne parcelle et on en crée une nouvelle qui repart de 0 UT (âge)
				
								
			}
		}
	}
	
	
	
	
	
		
}
