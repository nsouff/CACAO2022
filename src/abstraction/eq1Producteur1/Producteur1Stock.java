package abstraction.eq1Producteur1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import abstraction.eq8Romu.produits.Feve;


public class Producteur1Stock {
	private  HashMap<Feve,List<Producteur1Feve>> Feves;
	
	//Auteur : Khéo
	public Producteur1Stock() {
		this.Feves = new HashMap<Feve, List<Producteur1Feve>>() ;
		Feves.put(Feve.FEVE_BASSE, new ArrayList<Producteur1Feve>());
		Feves.put(Feve.FEVE_HAUTE, new ArrayList<Producteur1Feve>());
		Feves.put(Feve.FEVE_HAUTE_BIO_EQUITABLE, new ArrayList<Producteur1Feve>());
		Feves.put(Feve.FEVE_MOYENNE, new ArrayList<Producteur1Feve>());
		Feves.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE, new ArrayList<Producteur1Feve>());
	}
	
	//Auteur : Khéo
	public double getStock(Feve f){
		double somme = 0.0 ;
		for(Producteur1Feve Lot : this.getFeves().get(f)) {
			somme = somme + Lot.getPoids() ;
		}
		return somme ;	
	}
	
	//Auteur : Khéo
	public void addLot(Feve f, double quantite) {
		this.getFeves().get(f).add(new Producteur1Feve(quantite));

	}
	/**
	 * @return the feves
	 */
	public HashMap<Feve, List<Producteur1Feve>> getFeves() {
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
