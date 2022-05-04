package abstraction.eq5Transformateur3;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;

public class Transformation extends AcheteurBourse{

	public void transformationClassique (Feve f, double quantité, boolean B) {
		if (stockFeves.getStockDico().keySet().contains(f) && stockFeves.getStockDico().get(f)-quantité >=0) {
			stockFeves.utiliser(f, quantité);
			Chocolat choco = Chocolat(f.getGamme(), f.isBioEquitable(), B);
			stockChocolat.ajouter(choco,  quantité);
		}
	}
}
