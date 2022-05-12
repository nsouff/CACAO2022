package abstraction.eq1Producteur1;

import java.util.LinkedList;
import java.util.List;
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Feve;

public class Producteur1ContratCadre extends ProducteurActeur1VenteBourse implements IVendeurContratCadre {
	
	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeur;

	//Auteur : Khéo (sur toute la partie)
	// Pour l'instant selon ma seule décision, le but d'un condrat cadre sera découler les stocks n'ont vendu. Donc on va partir sur la vente
	//d'une quantité de 100000 tonnes à un prix pouvant aller jusuqu'a 25% inférieur du prix moyen de la bourse
	public Producteur1ContratCadre() {	
		super();
		this.mesContratEnTantQueVendeur=new LinkedList<ExemplaireContratCadre>();
	}
	
	@Override
	public boolean vend(Object produit) {
		if(this.getStock((Feve)produit, false)>100000) { //On peut initier la vente si on a les bonnes quantités
			return true;
		}
		return false; 
	}

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if (contrat.getEcheancier().getQuantiteTotale()<this.getStock((Feve)contrat.getProduit(), false)) {
			if (contrat.getEcheancier().getQuantiteTotale()<100000) {
				contrat.getEcheancier().ajouter(100000-contrat.getEcheancier().getQuantiteTotale());
				return contrat.getEcheancier();
			} else { //Quantité demandé acceptable
				return contrat.getEcheancier(); 
			}
			
		} else { //Pas assez de quantité dans le stock présent
			return null;
		}
		
	}

	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		return this.getPrixmoyenFeve().get(contrat.getProduit())*1000; //On propose le prix moyen au départ
	}

	@Override
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		double prixmoyen = this.getPrixmoyenFeve().get(contrat.getProduit());
		if(contrat.getPrix()<prixmoyen*0.75*1000) {
			return prixmoyen*0.75*1000;
		}
		return contrat.getPrix();
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
