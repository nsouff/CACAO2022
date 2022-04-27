package abstraction.eq6Distributeur1;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.filiere.IDistributeurChocolatDeMarque;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class DistributeurChocolatDeMarque extends Distributeur1Acteur implements IDistributeurChocolatDeMarque{

	@Override
	public double prix(ChocolatDeMarque choco) {
		// TODO Auto-generated method stub
		return ;
	}

	@Override
	public double quantiteEnVente(ChocolatDeMarque choco, int crypto) { //Emma Humeau
		return NotreStock.getStockageQte().get(choco);
	}

	@Override
	public double quantiteEnVenteTG(ChocolatDeMarque choco, int crypto) {
		// TODO Auto-generated method stub
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
