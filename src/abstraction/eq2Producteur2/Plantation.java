package abstraction.eq2Producteur2;

import java.util.ArrayList;

//auteure : Fiona Martin 

import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.produits.Feve;

public class Plantation extends Producteur2Acteur {
	
	private HashMap<Arbre, List<Parcelle>> NbParcelles;
	
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
		
		NbParcelles = new HashMap<Arbre, List<Parcelle>>();
		
		// 1 parcelle = 1 million d'arbres 
		
		NbParcelles.put(Arbre.ARBRE_HGB, new ArrayList<Parcelle>());	
		NbParcelles.put(Arbre.ARBRE_HG, new ArrayList<Parcelle>());
		NbParcelles.put(Arbre.ARBRE_MGB, new ArrayList<Parcelle>());
		NbParcelles.put(Arbre.ARBRE_MG, new ArrayList<Parcelle>());
		NbParcelles.put(Arbre.ARBRE_BG, new ArrayList<Parcelle>());
		
		Arbre[] arbres = {Arbre.ARBRE_HGB,Arbre.ARBRE_HG,Arbre.ARBRE_MGB,Arbre.ARBRE_MG,Arbre.ARBRE_BG};
		int[] qt = {20, 80, 40,160,200};
		
		for (int i=0; i<arbres.length; i++) {
			for (int j=0; j<qt[i]; j++) {
			NbParcelles.get(arbres[i]).add(new Parcelle(arbres[i]));
			}
		}

	}
	
	public void renouvellement() {
		/* 
		 * Pour le moment on replante automatiquement les arbres qui sont arrivés au bout de leur durée de vie en conservant le type d'arbre.  
		 */	
		Arbre[] arbres = {Arbre.ARBRE_HGB,Arbre.ARBRE_HG,Arbre.ARBRE_MGB,Arbre.ARBRE_MG,Arbre.ARBRE_BG};
		for (Arbre a: arbres) {
			
			List<Parcelle> ListeParcelles = NbParcelles.get(a);
			List<Parcelle> ParcellesASupprimer = new ArrayList<Parcelle>();
			
			for (Parcelle p : ListeParcelles) {
				if (p.getAge() == a.getDureeVie()) {
					ParcellesASupprimer.add(p);	
				}
			}
			for (int i=0; i < ParcellesASupprimer.size(); i++) {
				NbParcelles.get(a).remove(ParcellesASupprimer.get(i));
				NbParcelles.get(a).add(new Parcelle(a));
			}						
	}
		
	}	
	

	
	public Arbre conversion(Feve typefeve) {
		
		// permet de connnaître le type d'arbre en connaissant le type de fève 
		
		if (typefeve == Feve.FEVE_BASSE ) {
			return Arbre.ARBRE_BG;
		}
		else if (typefeve == Feve.FEVE_HAUTE) {
			return Arbre.ARBRE_HG;
		}
		else if (typefeve == Feve.FEVE_HAUTE_BIO_EQUITABLE) {
			return Arbre.ARBRE_HGB;
		}
		else if (typefeve == Feve.FEVE_MOYENNE) {
			return Arbre.ARBRE_MG;
		}
		else {
			return Arbre.ARBRE_MGB;
		}
			
	}
	
		
	public int production(Feve typefeve) {
		// permet de connaître la production, en kg, pour un type de fève donné 
		
		Arbre typearbre = conversion(typefeve);
		
		int ProductionFinale = 0 ;
		List<Parcelle> ListeParcelles = NbParcelles.get(typearbre);
		
		for (Parcelle p : ListeParcelles) {
			if (p.getAge() > typearbre.getDureeCroissance()) {
				ProductionFinale = (int) (ProductionFinale + typearbre.getRendementFinal())*p.getNbArbres();
			}
			else {
				ProductionFinale = (int) (ProductionFinale + p.getRendementProgressif()*p.getAge()) * p.getNbArbres();
			}
		}
		return ProductionFinale;
	}
	
	public int getNbArbre(Feve feve) {
		Arbre arbre = conversion(feve);
		return this.NbParcelles.get(arbre).size()*100000 ;
	}
	
	
	}
	
	
	
	
	

