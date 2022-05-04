package abstraction.eq2Producteur2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.filiere.Filiere;

/**
 * @author Jules DORE
 */

public class Producteur2VendeurContratCadreNonBio extends Producteur2VendeurContratCadre implements IVendeurContratCadre{
	
	public Producteur2VendeurContratCadreNonBio(Object produit) {
		super(produit);
		// TODO Auto-generated constructor stub
	}


	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeurNonBio;
	
	/**
	 * @param transformateur
	 * @return Un nombre de point qui représente la quantité de fèves non Bio achetée par ce transformateur
	 */
	public double getPointTransformateur(IAcheteurContratCadre transformateur) {
		double point=0.0;
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
	public int getClassementTransformateur(IAcheteurContratCadre transformateur) {
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
		return false;
	}

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		
		
		return null;
	}

	public double propositionPrix(ExemplaireContratCadre contrat) {
		return 0.95*cours;
	}
	double production = 15 ; // production de fève (à preciser selon gamme)
	double cours = 0.1; // cours de la bourse à préciser 
	
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {	
		if (contrat.getQuantiteTotale()>12*production){ // Grosse commande, proposition de prix plus bas
			if (contrat.getPrix()>0.8 ) {
				return contrat.getPrix();}
			else {
				return 0.85*cours ; }
		}
		else { if(!(contrat.getListePrix().size()>4)) { // plus petite commande, pris plus élévée (4 negociations maximum)
			return 0.90*cours ;}
		}
		return -1.0;
	}

	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		this.mesContratEnTantQueVendeurNonBio.add(contrat);
		
	}
	
	public void next() {
		List<ExemplaireContratCadre> contratsObsoletes=new LinkedList<ExemplaireContratCadre>();
		for (ExemplaireContratCadre contrat : this.mesContratEnTantQueVendeurNonBio) {
			if (contrat.getQuantiteRestantALivrer()==0.0 && contrat.getMontantRestantARegler()==0.0) {
				contratsObsoletes.add(contrat);
			}
		}
		this.mesContratEnTantQueVendeurNonBio.removeAll(contratsObsoletes);
	}
	@Override
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean peutVendre(Object produit) {
		// TODO Auto-generated method stub
		return false;
	}

}
