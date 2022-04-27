package abstraction.eq1Producteur1;

import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.bourseCacao.IVendeurBourse;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.Feve;

public class ProducteurActeur1VenteBourse extends Producteur1Acteur implements IVendeurBourse{



	/**
	 * @param feve
	 * @param stock
	 */
	public ProducteurActeur1VenteBourse(Feve feve, double stock) {
		super(feve, stock);
		// TODO Auto-generated constructor stub
	}



	@Override
	public double offre(Feve f, double cours) {
		return this.stockFeve.getValeur();
	}



	@Override
	public void notificationVente(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		// TODO Auto-generated method stub
		this.stockFeve.setValeur(this, this.stockFeve.getValeur()-quantiteEnKg);
	}
}
