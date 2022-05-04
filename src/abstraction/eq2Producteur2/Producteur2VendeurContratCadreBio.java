package abstraction.eq2Producteur2;

import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;

/**
 * @author SAIDI Mohamed
 */

public class Producteur2VendeurContratCadreBio extends Producteur2VendeurContratCadre implements IVendeurContratCadre {

	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeurBio;
	
	private double a = 1500; //cout de production/kg
	private double qt = 10000; // qtité produite/kg
	private double stock = 18;
	
	
	@Override
	public boolean vend(Object produit) {
		return true;
	}

	public double qtiteTotaleContratEnCours() {
		double qtiteTotaleContratEnCours = 0;
		for ( int i=0; i<mesContratEnTantQueVendeurBio.size();i++) {
			qtiteTotaleContratEnCours=mesContratEnTantQueVendeurBio.get(i).getQuantiteTotale()/mesContratEnTantQueVendeurBio.get(i).getEcheancier().getNbEcheances();
		}
		return qtiteTotaleContratEnCours;
	}
	
	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if (vend(contrat.getProduit())) {
			if (qtiteTotaleContratEnCours() + contrat.getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() < (qt + stock/contrat.getEcheancier().getNbEcheances())) { 
				return contrat.getEcheancier();
				}
			else {
				Echeancier e = contrat.getEcheancier();
				e.set(e.getStepDebut(), qt + stock/contrat.getEcheancier().getNbEcheances());// on souhaite livrer toute la quatité qu'on a
				return e;
			}
		}	
		 else {
			 	return null;// on ne vend pas de ce produit
		 }
	}
	
	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		return a + (5000.0/contrat.getQuantiteTotale());
	}

	@Override
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		this.mesContratEnTantQueVendeurBio.add(contrat);

		
	}

	@Override
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean peutVendre(Object produit) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void next() {
		List<ExemplaireContratCadre> contratsObsoletes=new LinkedList<ExemplaireContratCadre>();
		for (ExemplaireContratCadre contrat : this.mesContratEnTantQueVendeurBio) {
			if (contrat.getQuantiteRestantALivrer()==0.0 && contrat.getMontantRestantARegler()==0.0) {
				contratsObsoletes.add(contrat);
			}
		}
		this.mesContratEnTantQueVendeurBio.removeAll(contratsObsoletes);
	}
}
