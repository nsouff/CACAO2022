package abstraction.eq7Distributeur2;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.filiere.IDistributeurChocolatDeMarque;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Distributeur2ChocolatDeMarque extends AchatContratCadre implements IDistributeurChocolatDeMarque {

	@Override
	public double prix(ChocolatDeMarque choco) {
		// TODO Auto-generated method stub
		double prixVente = 0;
		if (choco.isBioEquitable()) {
			prixVente = 100;
		} else {
			prixVente = 10;
		}
		return prixVente;
	}

	@Override
	public double quantiteEnVente(ChocolatDeMarque choco, int crypto) {
		// TODO Auto-generated method stub
		return 0;
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