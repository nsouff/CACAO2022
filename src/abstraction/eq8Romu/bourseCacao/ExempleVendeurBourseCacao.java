package abstraction.eq8Romu.bourseCacao;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Feve;

public class ExempleVendeurBourseCacao extends ExempleAbsVendeurBourseCacao implements IVendeurBourse{

	public ExempleVendeurBourseCacao(Feve feve, double stock) {
		super(feve, stock);
	}

	public double offre(Feve f, double cours) {
		if (this.getFeve().equals(f)) {
			BourseCacao bourse = (BourseCacao)(Filiere.LA_FILIERE.getActeur("BourseCacao"));
			double pourcentage = (bourse.getCours(getFeve()).getValeur()-bourse.getCours(getFeve()).getMin())/(bourse.getCours(getFeve()).getMax()-bourse.getCours(getFeve()).getMin());
			return this.stockFeve.getValeur()*pourcentage;
		} else {
			return 0.0;
		}
	}

	public void notificationVente(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		this.stockFeve.setValeur(this, this.stockFeve.getValeur()-quantiteEnKg);
	}
}
