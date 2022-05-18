package abstraction.eq2Producteur2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Feve;

//auteure : Fiona 

public class Producteur2Plantation {
	
	private HashMap<Arbre, List<Parcelle>> NbParcelles;
	
	public Producteur2Plantation () {
		//auteure : Fiona
		
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
		
		this.NbParcelles = new HashMap<Arbre, List<Parcelle>>();
		
		// 1 parcelle = 100 000 arbres 
		
		
		this.NbParcelles.put(Arbre.ARBRE_HGB, new ArrayList<Parcelle>());	
		this.NbParcelles.put(Arbre.ARBRE_HG, new ArrayList<Parcelle>());
		this.NbParcelles.put(Arbre.ARBRE_MGB, new ArrayList<Parcelle>());
		this.NbParcelles.put(Arbre.ARBRE_MG, new ArrayList<Parcelle>());
		this.NbParcelles.put(Arbre.ARBRE_BG, new ArrayList<Parcelle>());
		
		Arbre[] arbres = {Arbre.ARBRE_HGB,Arbre.ARBRE_HG,Arbre.ARBRE_MGB,Arbre.ARBRE_MG,Arbre.ARBRE_BG};
		ArrayList<Integer> qt = ListeQt(4);
		
		for (int i=0; i<arbres.length; i++) {
			for (int j=0; j<qt.get(i); j++) {
			this.NbParcelles.get(arbres[i]).add(new Parcelle(arbres[i], 100));
			// on considère qu'au début de la simulation, les arbres ont tous 100 UT.
			}
		}
	}
	
	public ArrayList<Integer> ListeQt(int NbTotalArbres) {
		//auteure : Fiona
		
		// NbTotalArbres est en centaines de millions d'arbres 
		
		/*
		 * J'avais calculé le nombre de parcelles pour chaque type d'arbre en fonction de la 
		 * filière par défaut en considérant qu'on aurait 500 millions d'arbres. 
		 * Finalement, nous aurons 400 millions d'arbres. Je me sers donc d'un produit en croix
		 * à partir de mes calculs précédents pour obtenir les nouveaux nombres de parcelles. 
		 */
		
		ArrayList<Integer> qt = new ArrayList<Integer>();
		
		qt.add((int) (Math.ceil((20*NbTotalArbres)/5))); // HGB
		qt.add((int) (Math.ceil((80*NbTotalArbres)/5))); // HG
		qt.add((int) (Math.ceil((40*NbTotalArbres)/5))); // MGB
		qt.add((int) (Math.ceil((160*NbTotalArbres)/5))); //MG 
		qt.add((int) (Math.ceil((200*NbTotalArbres)/5))); //BG
		
		return qt;
	}
	
	
	public void next() {
		this.renouvellement();
		
	}
	
	
	public void nextPlantation() {
		//auteure : Fiona
		
		// faire vieillir les arbres à chaque UT 
		
		
		
		Arbre[] arbres = {Arbre.ARBRE_HGB,Arbre.ARBRE_HG,Arbre.ARBRE_MGB,Arbre.ARBRE_MG,Arbre.ARBRE_BG};
		for (Arbre a: arbres) {
			
			List<Parcelle> ListeParcelles = this.NbParcelles.get(a);
			for (Parcelle p : ListeParcelles) {
				p.setAge(p.getAge()+ 1);
			}
			
		}	
		
	}
	
	
	
	public void renouvellement() {
		//auteure : Fiona
		
		/* 
		 * Pour le moment on replante automatiquement les arbres qui sont arrivés au bout de leur durée de vie en conservant le type d'arbre.  
		 */	
		Arbre[] arbres = {Arbre.ARBRE_HGB,Arbre.ARBRE_HG,Arbre.ARBRE_MGB,Arbre.ARBRE_MG,Arbre.ARBRE_BG};
		for (Arbre a: arbres) {
			
			List<Parcelle> ListeParcelles = this.NbParcelles.get(a);
			List<Parcelle> ParcellesASupprimer = new ArrayList<Parcelle>();
			
			for (Parcelle p : ListeParcelles) {
				if (p.getAge() == a.getDureeVie()) {
					ParcellesASupprimer.add(p);	
				}
			}
			for (int i=0; i < ParcellesASupprimer.size(); i++) {
				this.NbParcelles.get(a).remove(ParcellesASupprimer.get(i));
				this.NbParcelles.get(a).add(new Parcelle(a, 0));
			}						
	}
		
	}	
	

	
	public Arbre conversion(Feve typefeve) {
		//auteure : Fiona
		
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
	
	public int RendementParcelle(Parcelle p) {
		if (p.getStadeMaladie() == 1 || Filiere.LA_FILIERE.getEtape()-p.getDebutMaladie()<=2) {
			if (p.getAge() > p.getTypeArbre().getDureeCroissance()) {
				return (int) (p.getTypeArbre().getRendementFinal() * 0.85);
			}
			else {
				return (int) (p.getRendementProgressif()  * 0.85);
			} 		
		}
		
		else if (p.getStadeMaladie() == 2 || Filiere.LA_FILIERE.getEtape()-p.getDebutMaladie()<=5) {
			if (p.getAge() > p.getTypeArbre().getDureeCroissance()) {
				return (int) (p.getTypeArbre().getRendementFinal() * 0.85);
			}
			else {
				return (int) (p.getRendementProgressif()  * 0.85);
			} 		
		}
		
		else if (p.getStadeMaladie() == 3 || Filiere.LA_FILIERE.getEtape()-p.getDebutMaladie()<=4) {
			if (p.getAge() > p.getTypeArbre().getDureeCroissance()) {
				return (int) (p.getTypeArbre().getRendementFinal() * 0.8);
			}
			else {
				return (int) (p.getRendementProgressif()  * 0.8);
			} 		
		}
		
		else if (p.getStadeMaladie() == 4 || Filiere.LA_FILIERE.getEtape()-p.getDebutMaladie()<=4) {
			if (p.getAge() > p.getTypeArbre().getDureeCroissance()) {
				return (int) (p.getTypeArbre().getRendementFinal() * 0.85);
			}
			else {
				return (int) (p.getRendementProgressif()  * 0.85);
			} 		
		}
		
		else if (p.getStadeMaladie() == 5 || Filiere.LA_FILIERE.getEtape()-p.getDebutMaladie()<=2) {
			return 0; 		
		}
		
		else if (p.getStadeTensionGeopolitique() != 0) {
			return 0;
		}
		
		else {
				if (p.getAge() > p.getTypeArbre().getDureeCroissance()) {
					return (int) (p.getTypeArbre().getRendementFinal());
				}
				else {
					return (int) (p.getRendementProgressif());
				} 		
			}
		}
		
		
		

	/*public double production(Feve feve) {
		return this.getNbArbre(feve)*3.0; //methode qui ne fait pas planter la fillière Test seulement avec 2 et qui ne s'affiche correctement dans le journal que pour 3/5 type de feves
	}*/
		
	
	
	//Problème sur cette méthode
	public double production(Feve typefeve) {
		//auteure : Fiona
		
		
		// permet de connaître la production, en kg, pour un type de fève donné 
		
		Arbre typearbre = conversion(typefeve);
		
		double ProductionFinale = 0.0 ;
		List<Parcelle> ListeParcelles = this.NbParcelles.get(typearbre);
		
		for (Parcelle p : ListeParcelles) {
			
			ProductionFinale = ProductionFinale + RendementParcelle(p)*p.getNbArbres();
		}
		return ProductionFinale;
	}
	
	public int getNbArbre(Feve feve) {
		//auteure : Fiona
		
		Arbre arbre = conversion(feve);
		return this.NbParcelles.get(arbre).size()*1000000 ;
	}

	
	public void initialiser() {
		this.NbParcelles = new HashMap<Arbre, List<Parcelle>>();
		
		// 1 parcelle = 100 000 arbres 
		
		
		this.NbParcelles.put(Arbre.ARBRE_HGB, new ArrayList<Parcelle>());	
		this.NbParcelles.put(Arbre.ARBRE_HG, new ArrayList<Parcelle>());
		this.NbParcelles.put(Arbre.ARBRE_MGB, new ArrayList<Parcelle>());
		this.NbParcelles.put(Arbre.ARBRE_MG, new ArrayList<Parcelle>());
		this.NbParcelles.put(Arbre.ARBRE_BG, new ArrayList<Parcelle>());
		
		Arbre[] arbres = {Arbre.ARBRE_HGB,Arbre.ARBRE_HG,Arbre.ARBRE_MGB,Arbre.ARBRE_MG,Arbre.ARBRE_BG};
		ArrayList<Integer> qt = ListeQt(4);
		
		for (int i=0; i<arbres.length; i++) {
			for (int j=0; j<qt.get(i); j++) {
			this.NbParcelles.get(arbres[i]).add(new Parcelle(arbres[i], 100));
			// on considère qu'au début de la simulation, les arbres ont tous 100 UT.
		
				}
			}
	}
}		
	
	
	
	
	

