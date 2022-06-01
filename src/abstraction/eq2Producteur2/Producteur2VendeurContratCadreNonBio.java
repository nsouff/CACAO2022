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

/**
 * @author Jules DORE
 */

public class Producteur2VendeurContratCadreNonBio extends Producteur2Acteur implements IVendeurContratCadre{
	
	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeurNonBio;
	protected Journal classement;
	public Producteur2VendeurContratCadreNonBio() {
		super();
		this.mesContratEnTantQueVendeurNonBio = new LinkedList<ExemplaireContratCadre>();
		this.classement=new Journal(this.getNom()+" classement", this);
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
	
	/**
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
	
	
	
	@Override
	public boolean vend(Object produit) {
		// TODO Auto-generated method stub
		return false;//((Feve)(produit)==Feve.FEVE_HAUTE||(Feve)(produit)==Feve.FEVE_MOYENNE||(Feve)(produit)==Feve.FEVE_BASSE);
	}

	
			public double quantiteTotaleContratEnCours(Object produit) {
		double quantiteTotaleContratEnCours = 0;
		for ( int i=0; i<mesContratEnTantQueVendeurNonBio.size();i++) {
			if (mesContratEnTantQueVendeurNonBio.get(i).getProduit()==produit) {
			quantiteTotaleContratEnCours=mesContratEnTantQueVendeurNonBio.get(i).getQuantiteTotale()/mesContratEnTantQueVendeurNonBio.get(i).getEcheancier().getNbEcheances();
			}
		}
		return quantiteTotaleContratEnCours;
	}
	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if (vend(contrat.getProduit())) {
			if (quantiteTotaleContratEnCours(contrat.getProduit()) + contrat.getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() < this.production((Feve)(contrat.getProduit()))) { 
				return contrat.getEcheancier();
				}
			else {
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(),contrat.getEcheancier().getStepFin(),this.production((Feve)(contrat.getProduit()))  - quantiteTotaleContratEnCours(contrat.getProduit()));    
				return e;
			}
		}	
		 else {
			 	return null;// on ne vend pas de ce produit
		 }
	}

	public double propositionPrix(ExemplaireContratCadre contrat) {
		BourseCacao bourse = (BourseCacao)(Filiere.LA_FILIERE.getActeur("BourseCacao"));
		return 0.95*bourse.getCours((Feve)(contrat.getProduit())).getValeur();
	}
	double production = 15 ; // production de fève (à preciser selon gamme) 
	
	
	
	
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {	
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

	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
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





}
	
