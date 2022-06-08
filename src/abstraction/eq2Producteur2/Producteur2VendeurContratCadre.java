package abstraction.eq2Producteur2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.filiere.IFabricantChocolatDeMarque;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.Feve;

public class Producteur2VendeurContratCadre extends Producteur2Acteur implements IVendeurContratCadre{

	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeurNonBio;
	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeurBio;
	private Journal classement;
	protected Journal journalCC;
	
	public Producteur2VendeurContratCadre() {
		super();
		this.mesContratEnTantQueVendeurNonBio = new LinkedList<ExemplaireContratCadre>();
		this.mesContratEnTantQueVendeurBio = new LinkedList<ExemplaireContratCadre>();
		this.classement=new Journal(this.getNom()+" classement", this);
		this.journalCC = new Journal("Cacao Doré Contrats Cadres", this);

	}
	
	/**
	 * @param transformateur
	 * @return Un nombre de point qui représente la quantité de fèves non Bio achetée par ce transformateur
	 */
	public double getPointTransformateur(IActeur transformateur) {
		double point=0.000;
		for (int i=0 ; i<this.mesContratEnTantQueVendeurNonBio.size() ; i++) {
			if(this.mesContratEnTantQueVendeurNonBio.get(i).getAcheteur().equals(transformateur)) //Selectionne les contrats cadres non bio équitable du transformateurs
				{ point+=this.mesContratEnTantQueVendeurNonBio.get(i).getQuantiteTotale(); // Point ajoute en fonction de la quantité
			}
		}	
		return point;
	}
	
	
	public List<IAcheteurContratCadre> getListeTransformateurContratCadre(){
		List<IAcheteurContratCadre> Liste= new ArrayList<IAcheteurContratCadre>();
		for (int i=0 ; i<this.mesContratEnTantQueVendeurNonBio.size() ; i++) {
			if (!Liste.contains(this.mesContratEnTantQueVendeurNonBio.get(i).getAcheteur())) {
				Liste.add(this.mesContratEnTantQueVendeurNonBio.get(i).getAcheteur());
			}
		}
	return Liste;
	}
	/*
	 * @param transformateur
	 * @return un classement du transformateur par rapport aux autres par rapport à l'achat de fèves non Bio
	 */
	public int getClassementTransformateur(IActeur transformateur) {
		int classement=1;
		if(!getListeTransformateurContratCadre().contains(transformateur)) {
			return 4;
		}
		else { for (int i=0 ; i < getListeTransformateurContratCadre().size() ; i++) {
			if(getPointTransformateur(transformateur)<getPointTransformateur(getListeTransformateurContratCadre().get(i))) {
				classement+=1;
				}
			}
		return classement;
		}
	}
	
	
	
	//Toute les fèves
	public boolean vend(Object produit) {
		// TODO Auto-generated method stub
		return ((Feve)(produit)==Feve.FEVE_HAUTE_BIO_EQUITABLE||(Feve)(produit)==Feve.FEVE_MOYENNE_BIO_EQUITABLE||(Feve)(produit)==Feve.FEVE_HAUTE||(Feve)(produit)==Feve.FEVE_MOYENNE||(Feve)(produit)==Feve.FEVE_BASSE);
	}
	
	//methode pour le bio
	public double qtiteTotaleContratEnCours(Object produit) {
		double qtiteTotaleContratEnCours = 0;
		for ( int i=0; i<mesContratEnTantQueVendeurBio.size();i++) {
			if(mesContratEnTantQueVendeurBio.get(i).getProduit()==produit) {
			qtiteTotaleContratEnCours=mesContratEnTantQueVendeurBio.get(i).getQuantiteTotale()/mesContratEnTantQueVendeurBio.get(i).getEcheancier().getNbEcheances();
			}
		}	
		return qtiteTotaleContratEnCours;
	}
	
	//methode pour le non bio 
	public double quantiteTotaleContratEnCours(Object produit) {
		double quantiteTotaleContratEnCours = 0;
		for ( int i=0; i<mesContratEnTantQueVendeurNonBio.size();i++) {
			if (mesContratEnTantQueVendeurNonBio.get(i).getProduit()==produit) {
			quantiteTotaleContratEnCours=mesContratEnTantQueVendeurNonBio.get(i).getQuantiteTotale()/mesContratEnTantQueVendeurNonBio.get(i).getEcheancier().getNbEcheances();
			}
		}
		return quantiteTotaleContratEnCours;
	}
	//non bio
	public Echeancier contrePropositionDuVendeurNonBio(ExemplaireContratCadre contrat) {
		if (vend(contrat.getProduit())) {
			if (quantiteTotaleContratEnCours(contrat.getProduit()) + contrat.getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() < this.production((Feve)(contrat.getProduit()))) { 
				return contrat.getEcheancier();
				}
			else {
				Echeancier e = contrat.getEcheancier();
				e.set(e.getStepDebut(), this.production((Feve)(contrat.getProduit())) );// on souhaite livrer toute la quatité qu'on a
				return e;
			}
		}	
		 else {
			 	return null;// on ne vend pas de ce produit
		 }
	}

	public Echeancier contrePropositionDuVendeurBio(ExemplaireContratCadre contrat) {
		if (vend(contrat.getProduit())) {
			if (qtiteTotaleContratEnCours(contrat.getProduit()) + contrat.getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() < (this.production((Feve)contrat.getProduit()) + this.getStock((Feve)(contrat.getProduit()))/contrat.getEcheancier().getNbEcheances())) { 

				return contrat.getEcheancier();
				}
			else {
				Echeancier e = contrat.getEcheancier();
				e.set(e.getStepDebut(), this.production((Feve)(contrat.getProduit())) + this.getStock((Feve)(contrat.getProduit()))/contrat.getEcheancier().getNbEcheances());// on souhaite livrer toute la quatité qu'on a
				return e;
			}
		}	
		 else {
			 	return null;// on ne vend pas de ce produit
		 }
	}
	
	
	
	
	public double propositionPrixNonBio(ExemplaireContratCadre contrat) {
		BourseCacao bourse = (BourseCacao)(Filiere.LA_FILIERE.getActeur("BourseCacao"));
		return 0.95*bourse.getCours((Feve)(contrat.getProduit())).getValeur();
	}

	
	public double contrePropositionPrixVendeurNonBio(ExemplaireContratCadre contrat) {
		BourseCacao bourse = (BourseCacao)(Filiere.LA_FILIERE.getActeur("BourseCacao"));
		if (contrat.getQuantiteTotale()>12*(this.production((Feve)contrat.getProduit()))){ // Grosse commande, proposition de prix plus bas
			if (contrat.getPrix()>0.8 ) {
				return contrat.getPrix();}
			else {
				return 0.85*bourse.getCours((Feve)(contrat.getProduit())).getValeur() ; }
		}
		else { if(!(contrat.getListePrix().size()>4)) { // plus petite commande, pris plus élévée (4 negociations maximum)
			return 0.90*bourse.getCours((Feve)(contrat.getProduit())).getValeur() ;}
		}
		return -1.0;
	}

	public void notificationNouveauContratCadreNonBio(ExemplaireContratCadre contrat) {
		this.mesContratEnTantQueVendeurNonBio.add(contrat);
		
	}
	
	public void next() {
		super.next();
		List<ExemplaireContratCadre> contratsObsoletes=new LinkedList<ExemplaireContratCadre>();
		for (ExemplaireContratCadre contrat : this.mesContratEnTantQueVendeurNonBio) {
			if (contrat.getQuantiteRestantALivrer()==0.0 && contrat.getMontantRestantARegler()==0.0) {
				contratsObsoletes.add(contrat);
			}
		}
		this.mesContratEnTantQueVendeurNonBio.removeAll(contratsObsoletes);
		for(IActeur a : Filiere.LA_FILIERE.getActeursSolvables()) {
			if (a instanceof IFabricantChocolatDeMarque) {
				this.classement.ajouter(a.getNom()+" : "+this.getClassementTransformateur(a)+", "+this.getPointTransformateur(a));
			}
		}
	
	}
	@Override
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		this.removeQuantite(quantite, (Feve)(produit));
		return quantite;
	}

	public void initialiser() {
		super.initialiser();
	}

//	@Override
	public boolean peutVendre(Object produit) {
		// TODO Auto-generated method stub
		return true;
	}


	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		res.add(this.classement);
		res.add(this.journal);
		return res;
	}


	public double propositionPrixBio(ExemplaireContratCadre contrat) {
		double prix = 1.4*this.getCout((Feve)contrat.getProduit());
		if (this.getClassementTransformateur( contrat.getAcheteur() )==1){
			prix = 1.25*this.getCout((Feve)contrat.getProduit());
		}
		if (this.getClassementTransformateur(contrat.getAcheteur() )==2){
			prix = 1.30*this.getCout((Feve)contrat.getProduit());
		}
		if (this.getClassementTransformateur( contrat.getAcheteur() )==3){
			prix = 1.35*this.getCout((Feve)contrat.getProduit());
		}
		
		return prix;
	}

	
	
	public double contrePropositionPrixVendeurBio(ExemplaireContratCadre contrat) {
		double contrepropositionprix = -1.0;
		if (contrat.getPrix()>0.8*propositionPrix(contrat)) {
			if (getClassementTransformateur( contrat.getAcheteur() )==1){
				if (Math.random()<0.65) {
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


	
	
	public void notificationNouveauContratCadreBio(ExemplaireContratCadre contrat) {
		this.mesContratEnTantQueVendeurBio.add(contrat);

		
	}

	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if((contrat.getProduit()==Feve.FEVE_HAUTE_BIO_EQUITABLE)||(contrat.getProduit()==Feve.FEVE_MOYENNE_BIO_EQUITABLE)) {
			return this.contrePropositionDuVendeurBio(contrat);
		}
		
		return this.contrePropositionDuVendeurNonBio(contrat);
	}

	public double propositionPrix(ExemplaireContratCadre contrat) {
		if((contrat.getProduit()==Feve.FEVE_HAUTE_BIO_EQUITABLE)||(contrat.getProduit()==Feve.FEVE_MOYENNE_BIO_EQUITABLE)) {
			return this.propositionPrixBio(contrat);
		}
		return this.propositionPrixNonBio(contrat);
	}
	
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		if((contrat.getProduit()==Feve.FEVE_HAUTE_BIO_EQUITABLE)||(contrat.getProduit()==Feve.FEVE_MOYENNE_BIO_EQUITABLE)) {
			return this.contrePropositionPrixVendeurBio(contrat);
		}
		return this.contrePropositionPrixVendeurNonBio(contrat);
	}

	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		if((contrat.getProduit()==Feve.FEVE_HAUTE_BIO_EQUITABLE)||(contrat.getProduit()==Feve.FEVE_MOYENNE_BIO_EQUITABLE)) {
			 this.notificationNouveauContratCadreBio(contrat);
			 this.journalCC.ajouter("Quantité par step de Feve HAUTE BIO EQUITABLE : "+this.qtiteTotaleContratEnCours(Feve.FEVE_HAUTE_BIO_EQUITABLE ));
			 this.journalCC.ajouter("Quantité par step de Feve MOYENNE BIO EQUITABLE : "+this.qtiteTotaleContratEnCours(Feve.FEVE_MOYENNE_BIO_EQUITABLE));
			 this.journalCC.ajouter("Contrat Cadre Bio : " + "Acheteur " + contrat.getAcheteur()+ " Produit : " + contrat.getProduit() + "Prix : " + contrat.getPrix() );
			 
		}
		this.notificationNouveauContratCadreNonBio(contrat);
		this.journalCC.ajouter("Quantité par step de Feve HAUTE Non Bio : "+this.qtiteTotaleContratEnCours(Feve.FEVE_HAUTE_BIO_EQUITABLE ));
		 this.journalCC.ajouter("Quantité par step de Feve MOYENNE Non BIO  : "+this.qtiteTotaleContratEnCours(Feve.FEVE_MOYENNE_BIO_EQUITABLE));
		 this.journalCC.ajouter("Contrat Cadre Non Bio : " + "Acheteur " + contrat.getAcheteur()+ " Produit : " + contrat.getProduit() + "Prix : " + contrat.getPrix() );
		 
	}

	
}
