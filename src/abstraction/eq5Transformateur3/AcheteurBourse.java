// julien 27/04

package abstraction.eq5Transformateur3;

import java.util.HashMap;

import abstraction.eq8Romu.bourseCacao.IAcheteurBourse;
import abstraction.eq8Romu.produits.Feve;

public class AcheteurBourse  extends AcheteurContrat implements IAcheteurBourse{

	@Override
	public double demande(Feve f, double cours) {
		// TODO Auto-generated method stub
		return 0;
	};

	@Override
	public void notificationAchat(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificationBlackList(int dureeEnStep) {
		// TODO Auto-generated method stub
		
	}

}
