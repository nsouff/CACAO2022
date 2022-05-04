package abstraction.eq6Distributeur1;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.filiere.IDistributeurChocolatDeMarque;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class DistributeurChocolatDeMarque extends Distributeur1Acteur implements IDistributeurChocolatDeMarque{

	//NOLANN
	public double prix(ChocolatDeMarque choco) {
		double prix = 0;
		if(choco==null) {
			throw new IllegalArgumentException("argument choco = null lors de l'appel de la fonction prix de DistributeurChocolatDeMarque");
		}
		else {
			prix = prixVente.get(choco);
		}
		return prix ;
	}

	//EMMA
	public double quantiteEnVente(ChocolatDeMarque choco, int crypto) {
		return (Stock.StockageQte).get(choco);
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
