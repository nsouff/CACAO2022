package abstraction.eq2Producteur2;

import java.util.LinkedList;


// @author SAIDI Mohamed
 

import abstraction.eq8Romu.bourseCacao.IVendeurBourse;
import abstraction.eq8Romu.produits.Feve;

public class Producteur2VendeurBourse extends Producteur2Vendeur implements IVendeurBourse {

	@Override
	public double offre(Feve f, double cours) {
		double quantiteAVendre = 0;
		if (cours> 1.3*this.getCout(f)) {
			 quantiteAVendre= this.getStock(f); // on vend 80% du stock;
		}
		if (cours> 1.2*this.getCout(f)) {
			 quantiteAVendre  = this.getStock(f); // on vend 60% du stock;
		}
		if (cours> 1.1*this.getCout(f)) {
			  quantiteAVendre = this.getStock(f); // on vend 40% du stock ;
		}
		return quantiteAVendre;
	}

	@Override
	public void notificationVente(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		// TODO Auto-generated method stub
		this.removeQuantite(quantiteEnKg, f);

	}
	public void initialiser() {
		super.initialiser();
	}
	public void next() {
	super.next();

	}
}