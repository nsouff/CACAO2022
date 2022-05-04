// julien 27/04

package abstraction.eq5Transformateur3;

import java.awt.Color;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;

public class AcheteurContrat extends Transformateur3Acteur implements IAcheteurContratCadre {

	
/* on regarde l etat de nos stocks et on lance la procédure demande 
	acheteur + get vendeur de la classe supperviseur vente cadre */
	public void next() {
		// TODO Auto-generated method stub
		super.next();
	}

	// Julien & Karla
	public boolean achete(Object produit) {
		// TODO Auto-generated method stub.
		if  (!( produit instanceof Feve) ) {
			return false;
		}
		if (( (Feve) produit).isBioEquitable() && 
				(( (Feve) produit).getGamme()==Gamme.MOYENNE ||
						( (Feve) produit).getGamme()==Gamme.HAUTE)) {
			return true;
		}
		return false;
	}

	// Julien & Karla
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		List<Echeancier> listeEcheanciers=contrat.getEcheanciers();
		int l = listeEcheanciers.size();
		return listeEcheanciers.get(l-1);
	}

	// Julien & Karla
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		double prixT = contrat.getPrix();
		if (prixT < this.seuilMaxAchat) {
			return prixT;
		}
		else {
			double nouveauprix = 0.7*prixT;
			if (nouveauprix< this.seuilMaxAchat) {
				return nouveauprix;
			}
			return 0.0;
		}
	}

// on pourra par la suite mettre fin aux autres négociations pour un même produit : on achete tout pour l'instant
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
	}

	// Julien & Karla
	// si la quantité reçue est inférieure à celle prévue : en acheter à la bourse ?
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		Feve f= ((Feve) produit);
		this.stockFeves.ajouter(f, quantite);
	}
	
}
	
