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
		 * pour honorer nos contrats 
		 * */
		Double difference = this.besoinFeves.get(f) - this.dispoFeves.get(f);		
		/* Si notre stock permet de répondre au besoin, on n'achète pas, 
		 * sinon on achète avec une marge de 20% supplémntaire  
		 */
		if (difference > 0.00 ) {
			difference += 0.20*difference;
			this.achats.ajouter("demande de" + difference + " kg de feve" + f.getGamme().toString() + "à un cours" + cours );
			return difference;
		}
		else {
		/*	if (this.getFeve().equals(f)) {
				BourseCacao bourse = (BourseCacao)(Filiere.LA_FILIERE.getActeur("BourseCacao"));
				double pourcentage = (bourse.getCours(getFeve()).getMax()-bourse.getCours(getFeve()).getValeur())/(bourse.getCours(getFeve()).getMax()-bourse.getCours(getFeve()).getMin());
				return achatMaxParStep*pourcentage;
		}
		*/
		}
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
