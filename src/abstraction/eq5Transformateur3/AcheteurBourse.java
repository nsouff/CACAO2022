package abstraction.eq5Transformateur3;

import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.bourseCacao.IAcheteurBourse;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Feve;

public class AcheteurBourse  extends Transformateur3Acteur implements IAcheteurBourse{

	// Karla 
	public double demande(Feve f, double cours) {
		// Si on a moins d'un certain seuil de fèves, on cherche à en acheter via la bourse
		if (this.stockFeves.getstock(f)<this.SeuilMinFeves) {
			BourseCacao bourse = (BourseCacao)(Filiere.LA_FILIERE.getActeur("BourseCacao"));
			double pourcentage = (bourse.getCours(f).getMax()-cours)/(bourse.getCours(f).getMax()-bourse.getCours(f).getMin());
			// on en achete selon le prix actuel de la bourse d'où *pourcentage
			return this.achatMaxFeves;//on achete le plus possible
		} else {
			return 0.0;
		}
	}

	// Karla
	public void notificationAchat(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		this.stockFeves.ajouter(f, quantiteEnKg);
		this.achats.ajouter("Achat a la bourse de "+ quantiteEnKg +"kg  de " + f + " a "+ coursEnEuroParKg+ " euro par Kg");
	}

	// Karla
	public void notificationBlackList(int dureeEnStep) {	
	}

	public void next () {
		super.next();
		
	}
}
