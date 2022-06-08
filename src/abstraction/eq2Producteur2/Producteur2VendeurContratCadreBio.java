package abstraction.eq2Producteur2;

import java.awt.Color;
import java.util.ArrayList;
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
import abstraction.eq8Romu.produits.Feve;

/**
 * @author SAIDI Mohamed
 */

public class Producteur2VendeurContratCadreBio extends Producteur2VendeurContratCadreNonBio implements IVendeurContratCadre {

	public Producteur2VendeurContratCadreBio() {
		super();
		this.mesContratEnTantQueVendeurBio = new LinkedList<ExemplaireContratCadre>();
		this.journalCC = new Journal("Cacao Doré Contrats Cadres", this);
	}

	protected Journal journalCC;
	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeurBio;

	public boolean vend(Object produit) {
		return (produit instanceof Feve);//(produit instanceof Feve);
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
	

	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if (this.vend(contrat.getProduit())) {
			if (qtiteTotaleContratEnCours(contrat.getProduit()) + contrat.getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() < (this.production((Feve)contrat.getProduit()) + this.getStock((Feve)(contrat.getProduit()))/contrat.getEcheancier().getNbEcheances())) { 

				return contrat.getEcheancier();
				}
			else {
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(),contrat.getEcheancier().getStepFin(),this.production((Feve)(contrat.getProduit())) + (this.getStock((Feve)(contrat.getProduit()))/contrat.getEcheancier().getNbEcheances())- qtiteTotaleContratEnCours(contrat.getProduit()));    
				return e;
			}
		}	
		 else {
			 	return null;// on ne vend pas de ce produit
		 }
	}
	
	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		double prix = 1.4*this.getCout((Feve)contrat.getProduit());
		if (getClassementTransformateur( contrat.getAcheteur() )==1){
			prix = 1.25*this.getCout((Feve)contrat.getProduit());
		}
		if (getClassementTransformateur( contrat.getAcheteur() )==2){
			prix = 1.30*this.getCout((Feve)contrat.getProduit());
		}
		if (getClassementTransformateur( contrat.getAcheteur() )==3){
			prix = 1.35*this.getCout((Feve)contrat.getProduit());
		}
		
		return prix;
	}
//BioEq
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
		this.removeQuantite(quantite, (Feve)(produit));
		return quantite;
	}
	
	public boolean peutVendre(Object produit) {
		return true;
	}

	
	public void next() {
		super.next();
		List<ExemplaireContratCadre> contratsObsoletes=new LinkedList<ExemplaireContratCadre>();
		for (ExemplaireContratCadre contrat : this.mesContratEnTantQueVendeurBio) {
			if (contrat.getQuantiteRestantALivrer()==0.0 && contrat.getMontantRestantARegler()==0.0) {
				contratsObsoletes.add(contrat);
			}
		}
		this.mesContratEnTantQueVendeurBio.removeAll(contratsObsoletes);
		this.journalCC.ajouter("Quantité par step de Feve HAUTE BIO EQUITABLE : "+this.qtiteTotaleContratEnCours(Feve.FEVE_HAUTE_BIO_EQUITABLE));
		this.journalCC.ajouter("Quantité par step de Feve MOYENNE BIO EQUITABLE : "+this.qtiteTotaleContratEnCours(Feve.FEVE_MOYENNE_BIO_EQUITABLE));
	}

	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		res.add(this.classement);
		res.add(this.journalCC);
		return res;
	}
	
	
	public void initialiser() {
		super.initialiser();
	}
}
