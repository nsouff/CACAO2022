package abstraction.eq1Producteur1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import abstraction.eq8Romu.filiere.Banque;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Feve;


public class Producteur1Stock {
	private  HashMap<Feve,List<FeveProducteur1>> Feves;
	protected Integer cryptogramme;

	
	//Auteur : Khéo
	public Producteur1Stock() {
		this.Feves = new HashMap<Feve, List<FeveProducteur1>>() ;
		Feves.put(Feve.FEVE_BASSE, new ArrayList<FeveProducteur1>());
		Feves.put(Feve.FEVE_HAUTE, new ArrayList<FeveProducteur1>());
		Feves.put(Feve.FEVE_HAUTE_BIO_EQUITABLE, new ArrayList<FeveProducteur1>());
		Feves.put(Feve.FEVE_MOYENNE, new ArrayList<FeveProducteur1>());
		Feves.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE, new ArrayList<FeveProducteur1>());
		
	}
	
	//Auteur : Khéo
	public void initialiser() {
	this.addLot(Feve.FEVE_BASSE, 1000000);
	this.addLot(Feve.FEVE_MOYENNE, 1000000);
	this.addLot(Feve.FEVE_MOYENNE_BIO_EQUITABLE, 100000);
	this.addLot(Feve.FEVE_HAUTE_BIO_EQUITABLE, 100000);
	}
	
	//Auteur : Khéo
	public void next(){
	
	}
	
	//Auteur : Khéo
	public double getStock(Feve f){
		double somme = 0.0 ;
		for(FeveProducteur1 Lot : this.getFeves().get(f)) {
			somme = somme + Lot.getPoids() ;
		}
		return somme ;	
	}
	
	//Auteur : Khéo
	public void addLot(Feve f, double quantite) {
		this.getFeves().get(f).add(new FeveProducteur1(quantite));

	}
	
	
	//Auteur : Khéo
	public void retirerQuantite(Feve f, double quantite) {
		while (quantite>0) {
			double poids = this.getFeves().get(f).get(0).getPoids() ;
			
			if (quantite >= poids) { //On regarde en fonction de la quantite qui reste dans le lot. Si il y'a plus on enlève le lot
				quantite = quantite - poids ;
				this.getFeves().get(f).remove(0);
			}
			else {
				this.getFeves().get(f).get(0).setPoids(poids-quantite); //Si non, on ajuste le poids du lot
				quantite = 0 ;
			}
		}
	}
	
	/**
	 * @return the feves
	 */
	public HashMap<Feve, List<FeveProducteur1>> getFeves() {
		return Feves;
	}

	// Auteur : Laure //
	// Si une maladie se lance dans le stock, un pourcentage est perdu. //
	// A faire tourner à chaque UT //
	// Renvoie le stock mis à jour //
	public void EvolutionStockPostMaladie() {
		int probaMalade;
		int StockMalade;
		int min_val = 1;
        int max_val = 100;
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
		probaMalade =tlr.nextInt(min_val, max_val + 1);
		if (probaMalade<15) {
			int ForceMaladie;
			ThreadLocalRandom tlr2 = ThreadLocalRandom.current();
			ForceMaladie =tlr2.nextInt(min_val, max_val + 1);
			if (ForceMaladie<70) {
				StockMalade = 10;
			}
			else if (ForceMaladie<90) {
				StockMalade = 50;
			}
			else {
				StockMalade = 80;
			}
		} else {
			StockMalade = 0;
		}
		for (int i=0; i<(this.Feves.size()*StockMalade/100); i=i+1) {
			this.Feves.remove(i);
		}
		
		// Auteur : Laure //
		// Simule l'évolution globale du stock
		// A faire tourner à chaque UT
		// Renvoie le stock mis à jour //
		
		
	}
}
