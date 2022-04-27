package abstraction.eq1Producteur1;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Producteur1Stock {
	private  Map<Feve,List<Producteur1Feve>> ;
	
	
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
		for (int i=0; i<(liste_feves.size()*StockMalade/100); i=i+1) {
			liste_feves.remove(i);
		}
		
		// Auteur : Laure //
		// Simule l'évolution globale du stock
		// A faire tourner à chaque UT
		// Renvoie le stock mis à jour //
		
		
	}
}
