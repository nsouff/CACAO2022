package abstraction.eq5Transformateur3;

import abstraction.eq8Romu.filiere.Filiere;

public class Transformateur3 extends VenteAppel {
	
	// julien 10/05
	public double prixStockage() {
		double qttTotale = 0;    // on gère le chocolat et les fèves de la même manière pour les prix de stockage -> poids
		qttTotale+= this.stockChocolat.getstocktotal()+this.stockFeves.getstocktotal() ;
		return qttTotale*4*(Filiere.LA_FILIERE.getIndicateur("prixstockageVariable").getValeur()) ; 
		}

	public Transformateur3() {
		super();
	}

	public void next() {
		super.next();
		double montant= prixStockage();
		 Filiere.LA_FILIERE.getBanque().virer(Filiere.LA_FILIERE.getActeur("EQ5"), this.cryptogramme, Filiere.LA_FILIERE.getActeur("EQ8"), montant);
		
	}
	
}
