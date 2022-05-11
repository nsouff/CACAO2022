// julien 27/04

package abstraction.eq5Transformateur3;

import java.util.List;
import java.util.Random;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;

public class AcheteurContrat extends AcheteurBourse  implements IAcheteurContratCadre {

	
	//Karla / Julien
	/* Initier un contrat */
	public void lanceruncontratAcheteur(Feve f) {
		SuperviseurVentesContratCadre superviseur = ((SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre")));
		List<IVendeurContratCadre> L = superviseur.getVendeurs(f);
		int qtt = 10000; // qtt de feve par step, à modifier en fonction des ventes 
		
		Echeancier e = new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, qtt); //qtt kg de feves par etape pendant  10 steps
		if (L.size()!=0) {
			if (L.size()== 1) {
				superviseur.demandeAcheteur((IAcheteurContratCadre)Filiere.LA_FILIERE.getActeur("EQ5"), L.get(0), (Object)f,  e, this.cryptogramme, false);
			}
			else {
				// On choisit aléatoirement un des producteurs
				Random randomizer = new Random();
				IVendeurContratCadre random = L.get(randomizer.nextInt(L.size()));
				superviseur.demandeAcheteur((IAcheteurContratCadre)Filiere.LA_FILIERE.getActeur("EQ5"), random, (Object)f,  e, this.cryptogramme, false);
			}
		}
		else {
			
		}
		//Julien else on achete des feve par le biais de la bourse si besoin ( bourse.getCours(f).getValeur() )
	}
	


	// Julien & Karla
	public boolean achete(Object produit) {
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
	// On accepte tout le temps l'echeancier
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		List<Echeancier> listeEcheanciers=contrat.getEcheanciers();
		int l = listeEcheanciers.size();
		return listeEcheanciers.get(l); // le dernier proposé est celui des vendeurs
	}

	// Julien & Karla
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
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

	// Julien & Karla
	/* On met à jour le journal
	 * et on pourra par la suite mettre fin aux autres négociations pour un même produit : on achete tout pour l'instant
	 */
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		this.journal.ajouter("Nouveau Contrat Cadre avec"+ contrat.getVendeur() +"sur une periode de " + contrat.getEcheancier().getNbEcheances() + " pour "+ contrat.getProduit());

	}

	// Julien & Karla
	// si la quantité reçue est inférieure à celle prévue : en acheter à la bourse ?
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		Feve f= ((Feve) produit);
		this.stockFeves.ajouter(f, quantite);
	}

	//Karla
	/* on regarde l etat de nos stocks et on lance la procédure demande 
	acheteur + get vendeur de la classe supperviseur vente cadre */
	public void next() {
		super.next();
		for (Feve f : this.stockFeves.getProduitsEnStock()) {
			if (this.stockFeves.getstock(f) < this.SeuilMinFeves) {
				lanceruncontratAcheteur(f);
			}
		}
	}
}
	
