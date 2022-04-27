package abstraction.eq2Producteur2;

import java.util.ArrayList;
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
	
	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeurNonBio;
	
	/**
	 * @param transformateur
	 * @return Un nombre de point qui représente la quantité de fèves achetée par ce transformateur
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

	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		return 0.95*cours;
	}

	double cours = 0.1; // cours de la bourse à préciser 
	
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {	
		if (contrat.getQuantiteTotale()>12){
			if (contrat.getPrix()>0.8 ) {
				return contrat.getPrix();}
			else {
				return 0.85*cours ; }
		}
		else { if(!(contrat.getListePrix().size()>4)) {
			return 0.90*cours ;}
		}
		return -1.0;
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
