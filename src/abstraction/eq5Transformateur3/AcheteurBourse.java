package abstraction.eq5Transformateur3;

import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.bourseCacao.IAcheteurBourse;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;

public class AcheteurBourse  extends Transformateur3Acteur implements IAcheteurBourse{

	// Karla 
	public double demande(Feve f, double cours) {
		
		/* on calcule notre besoin en la fève f (en partant du principe que l'on fait que des transformations classiques)
		 * pour honorer nos contrats */
		Double difference = this.besoinFeves.get(f) - this.dispoFeves.get(f);	
		
		/* Si notre stock ne permet pas de répondre au besoin,
		 * on achète avec une marge de 20% supplémntaire */
		if (difference > 0.00 ) {
			difference += 0.20*difference;		
		}
		
		/* Sinon on achète "proportionnellement" au cours de la bourse */
		else {
			/* si on a deux fois plus de stock, on n'achète pas  */
			if (2*this.besoinFeves.get(f) - this.dispoFeves.get(f) > 0) {
				BourseCacao bourse = (BourseCacao)(Filiere.LA_FILIERE.getActeur("BourseCacao"));
				double pourcentage = (bourse.getCours(f).getMax()-bourse.getCours(f).getValeur())/(bourse.getCours(f).getMax()-bourse.getCours(f).getMin());
				difference = this.achatMaxFeves * pourcentage;
			}
		}
		this.achats.ajouter("demande de" + difference + " kg de feve" + f.getGamme().toString() + "à un cours" + cours );
		return difference;
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
