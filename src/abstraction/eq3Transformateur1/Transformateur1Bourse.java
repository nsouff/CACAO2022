package abstraction.eq3Transformateur1;

import abstraction.eq8Romu.bourseCacao.IAcheteurBourse;
import abstraction.eq8Romu.produits.Feve;

public class Transformateur1Bourse extends Transformateur1Acteur implements IAcheteurBourse{

	@Override
	public double demande(Feve f, double cours) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void notificationAchat(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		// TODO Auto-generated method stub
		stockFeve.put(f, quantiteEnKg + stockFeve.get(f)) ;
	}

	@Override
	public void notificationBlackList(int dureeEnStep) {
		// TODO Auto-generated method stub
		
	}

}
