package abstraction.eq7Distributeur2;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;

public class AchatContratCadre extends Distributeur2Acteur implements IAcheteurContratCadre{

	public static final int SEUIL = 10000;
	public static final Double MARGE = 0.05;
	
	@Override
	public boolean achete(Object produit) {
		/*if (produit.getPrix()>=SEUIL) {
			return true;
		}*/
		return false;
	}

	@Override
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		/*if (contrat.getPrixALaTonne()>=SEUIL*(1-MARGE)) {
			return contrat.getPrixALaTonne()
		}*/
		return null;
	}

	@Override
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		
	}

}
