package abstraction.eq4Transformateur2;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;

//Marie
public class Transformateur2ContratCadre extends Transformateur2Acteur implements IVendeurContratCadre{
	
	
	public void next() {
		super.next();
	}
	public void initialiser() {
		super.initialiser();
	}
	
	
	public Transformateur2ContratCadre() {
		super();
	}


	public boolean vend(Object produit) {
		/*if (this.getQuantite_stock().keySet().contains(produit)) {
			return true
		}else {*/
			return false;
	} 


	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		return null;
	}


	public double propositionPrix(ExemplaireContratCadre contrat) {
		return 0;
	}


	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		return 0;
	}


	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		
	}


	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {

		return 0;
	}


}
