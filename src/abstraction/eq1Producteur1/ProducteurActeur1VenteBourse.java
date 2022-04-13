package abstraction.eq1Producteur1;

import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.bourseCacao.IVendeurBourse;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.Feve;

public class ProducteurActeur1VenteBourse extends Producteur1Acteur implements IVendeurBourse{
	protected Feve feve;
	
	
	
	public void next() {
		super.next();
		journal.ajouter("Etape="+Filiere.LA_FILIERE.getEtape());
		if (Filiere.LA_FILIERE.getEtape()>=1) {
			BourseCacao bourse = (BourseCacao)(Filiere.LA_FILIERE.getActeur("BourseCacao"));
			journal.ajouter("le cours de "+feve+" est : "+Journal.doubleSur(bourse.getCours(feve).getValeur(), 2));
		}
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
