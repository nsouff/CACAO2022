package abstraction.eq5Transformateur3;
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;


public class VenteContrat extends Transformation implements IVendeurContratCadre {

	@Override
	//Yves
	public boolean vend(Object produit) {
		if (stockChocolat.getProduitsEnStock().contains(produit) == true) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
				return null;
	}

	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		return 0;
	}

	@Override
	//Yves
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		if (contrat.getPrix()>(this.seuilMaxAchat+this.coutTransformation.getValeur())) {
			return contrat.getPrix();
		}
		else {
			double Nprix = 1.1*this.seuilMaxAchat+this.coutTransformation.getValeur();
			if (contrat.getPrix()>(this.seuilMaxAchat+this.coutTransformation.getValeur())) {
				return Nprix;
			}
			return 0.0;
		}
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return 0;
	}
	}