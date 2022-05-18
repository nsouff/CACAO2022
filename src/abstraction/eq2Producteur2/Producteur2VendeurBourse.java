package abstraction.eq2Producteur2;

import java.util.LinkedList;


// @author SAIDI Mohamed
 

import abstraction.eq8Romu.bourseCacao.IVendeurBourse;
import abstraction.eq8Romu.produits.Feve;

public class Producteur2VendeurBourse extends Producteur2Vendeur implements IVendeurBourse {

	private double coutProduction;
	@Override
	public double offre(Feve f, double cours) {
		double quantiteAVendre = 0;
		if (cours> 1.3*coutProduction) {
			 quantiteAVendre= this.StockTot.get((f)); // on vend 80% du stock;
		}
		if (cours> 1.2*coutProduction) {
			 quantiteAVendre  = this.StockTot.get((f)); // on vend 60% du stock;
		}
		if (cours> 1.1*coutProduction) {
			  quantiteAVendre = this.StockTot.get((f)); // on vend 40% du stock ;
		}
		return quantiteAVendre;
	}

	@Override
	public void notificationVente(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		// TODO Auto-generated method stub
		this.removeQuantite(quantiteEnKg, f);

	}

}
