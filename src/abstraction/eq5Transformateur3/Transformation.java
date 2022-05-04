package abstraction.eq5Transformateur3;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.Banque;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;

public class Transformation extends AcheteurBourse {

	public boolean payer(double montant) {
		return Filiere.LA_FILIERE.getBanque().virer(Filiere.LA_FILIERE.getActeur("EQ5"), this.cryptogramme, Filiere.LA_FILIERE.getActeur("EQ8"), montant);
	}
	
	public void transformationClassique (Feve f, double quantité, boolean B) {
		if (stockFeves.getProduitsEnStock().contains(f) && stockFeves.getStockDico().get(f)-quantité >=0) {
			stockFeves.utiliser(f, quantité);
		}
	}
}
