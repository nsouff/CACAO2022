package abstraction.eq5Transformateur3;

import abstraction.eq8Romu.bourseCacao.IAcheteurBourse;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.produits.Feve;

public class AcheteurBourse  extends Transformateur3Acteur implements IAcheteurBourse{

	// Karla 
	public double demande(Feve f, double cours) {
		Double besoin = 0.00;
		for (ExemplaireContratCadre contrat : this.contratsEnCoursVente) {
			if (contrat.getProduit() == f) {
				besoin += contrat.getQuantiteALivrerAuStep();
			}
		}
		Double difference = besoin - this.stockFeves.getstock(f) ;
		if (difference > 0 ) {
			this.achats.ajouter("demande de" + difference + " kg de feve" + f.getGamme().toString() + "à un cours" + cours );
		}
		return difference ;
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
