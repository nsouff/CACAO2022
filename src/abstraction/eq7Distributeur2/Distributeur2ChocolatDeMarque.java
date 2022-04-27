package abstraction.eq7Distributeur2;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.filiere.IDistributeurChocolatDeMarque;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Distributeur2ChocolatDeMarque extends Distributeur2Acteur implements IRayon  {


	public double getQuantiteClassique(ChocolatDeMarque chocolat) {
		// TODO Auto-generated method stub
		return this.stock.getQuantite(chocolat);
	}

	@Override
	public void rechargerClassique(ChocolatDeMarque chocolat, Double quantite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void venteClassique(ChocolatDeMarque chocolat, Double quantite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renvoyerEnStockClassique(ChocolatDeMarque chocolat, Double quantite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getQuantiteGondole(ChocolatDeMarque chocolat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void rechargerGondole(ChocolatDeMarque chocolat, Double quantite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void venteGondole(ChocolatDeMarque chocolat, Double quantite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renvoyerEnStockGondole(ChocolatDeMarque chocolat, Double quantite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getQuantiteTotale(ChocolatDeMarque chocolat) {
		// TODO Auto-generated method stub
		return 0;
	}
}