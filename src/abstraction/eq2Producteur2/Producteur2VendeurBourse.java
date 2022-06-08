package abstraction.eq2Producteur2;

import abstraction.eq8Romu.bourseCacao.IVendeurBourse;
import abstraction.eq8Romu.produits.Feve;

public class Producteur2VendeurBourse extends Producteur2VendeurContratCadre implements IVendeurBourse {

	public Producteur2VendeurBourse() {
		super();
	}

	public double offre(Feve f, double cours) {
		double quantiteAVendre = 0;
		if (cours> 1.3*this.getCout(f)) {
			quantiteAVendre= 0.8*this.getStock(f); // on vend 80% du stock;
		}
		if (cours> 1.2*this.getCout(f)) {
			quantiteAVendre  = 0.6*this.getStock(f); // on vend 60% du stock;
		}
		if (cours> 1.1*this.getCout(f)) {
			quantiteAVendre = 0.4*this.getStock(f); // on vend 40% du stock ;
		}
		return  quantiteAVendre;
	}

	public void notificationVente(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		this.removeQuantite(quantiteEnKg, f);

	}
}
