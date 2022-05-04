package abstraction.eq2Producteur2;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.ExempleTransformateurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;

/**
 * @author SAIDI Mohamed
 */

public class Producteur2VendeurContratCadreBio extends Producteur2VendeurContratCadreNonBio implements IVendeurContratCadre {

	public Producteur2VendeurContratCadreBio() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeurBio;
	protected double coutProduction;
	private double a = 1500; //cout de production/kg
	private double qt = 10000; // qtité produite/kg
	private double stock = 18;

	public boolean vend(Object produit) {
		return true;
	}

	public double qtiteTotaleContratEnCours(Object produit) {
		double qtiteTotaleContratEnCours = 0;
		for ( int i=0; i<mesContratEnTantQueVendeurBio.size();i++) {
			if(mesContratEnTantQueVendeurBio.get(i).getProduit()==produit) {
			qtiteTotaleContratEnCours=mesContratEnTantQueVendeurBio.get(i).getQuantiteTotale()/mesContratEnTantQueVendeurBio.get(i).getEcheancier().getNbEcheances();
			}
		}	
		return qtiteTotaleContratEnCours;
	}
	
	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if (vend(contrat.getProduit())) {
			if (qtiteTotaleContratEnCours(contrat.getProduit()) + contrat.getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() < (qt + stock/contrat.getEcheancier().getNbEcheances())) { 

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
		double prix = 1.4*coutProduction;
		if (getClassementTransformateur( contrat.getAcheteur() )==1){
			prix = 1.25*coutProduction;
		}
		if (getClassementTransformateur( contrat.getAcheteur() )==2){
			prix = 1.3*coutProduction;
		}
		if (getClassementTransformateur( contrat.getAcheteur() )==3){
			prix = 1.35*coutProduction;
		}
		
		return prix;
	}

	@Override
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		double contrepropositionprix = -1.0;
		if (contrat.getPrix()>0.8*propositionPrix(contrat)) {
			if (getClassementTransformateur( contrat.getAcheteur() )==1){
				if (Math.random()<0.6) {
					contrepropositionprix = contrat.getPrix(); // on ne cherche pas a negocier dans 60% des cas
				}
			}
			if (getClassementTransformateur( contrat.getAcheteur() )==2){
				if (Math.random()<0.5) {
					contrepropositionprix = contrat.getPrix(); // on ne cherche pas a negocier dans 40% des cas
				}
			}	
			if (getClassementTransformateur( contrat.getAcheteur() )==3){
				if (Math.random()<0.3) {
					contrepropositionprix = contrat.getPrix(); // on ne cherche pas a negocier dans 20% des cas
				}
			}
			if (getClassementTransformateur( contrat.getAcheteur() )==4){
				if (Math.random()<0.1) {
					contrepropositionprix = contrat.getPrix(); // on ne cherche pas a negocier dans 10% des cas
				}
			}
		}
	
	 return contrepropositionprix;
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
