package abstraction.eq6Distributeur1;

import java.util.HashMap;
import java.util.random.*;
import java.util.Map;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.filiere.IDistributeurChocolatDeMarque;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class DistributeurChocolatDeMarque extends Distributeur1Acteur implements IDistributeurChocolatDeMarque{

	private Map<ChocolatDeMarque, Double> teteGondole = new HashMap<ChocolatDeMarque, Double>(); // (nom du chocolat,% en tête de gondole), Emma Humeau 

	
	
	/**
	 * @return the teteGondole
	 */
	public Map<ChocolatDeMarque, Double> getTeteGondole() {
		return teteGondole;
	}

	/**
	 * @param teteGondole the teteGondole to set
	 */
	public void setTeteGondole(Map<ChocolatDeMarque, Double> teteGondole) {
		this.teteGondole = teteGondole;
	}

	@Override
	public double prix(ChocolatDeMarque choco) {
		// TODO Auto-generated method stub
		return ;
	}

	@Override
	public double quantiteEnVente(ChocolatDeMarque choco, int crypto) { //Emma Humeau
		if (NotreStock.getqteStockageTotale() <= 200) {
			return NotreStock.getStockageQte(choco); }
		else {
			return NotreStock.getStockageQte(choco)*0.9;
		}
	}

	@Override
	public double quantiteEnVenteTG(ChocolatDeMarque choco, int crypto) {
		return 0;
	}

	@Override
	public void vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant, int crypto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificationRayonVide(ChocolatDeMarque choco, int crypto) {
		// TODO Auto-generated method stub
		
	}

}
