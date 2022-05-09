package abstraction.eq5Transformateur3;
import java.util.List;
import java.util.Random;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;


public class VenteContrat extends Transformation implements IVendeurContratCadre {
	
	//Karla
	/* Initier un contrat */
	public void lanceruncontratVendeur(ChocolatDeMarque c) {
		List<IAcheteurContratCadre> L =  ((SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre"))).getAcheteurs(c);
		Echeancier e = new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, 100); //100 kg de chocolat sur 10 steps
		if (L.size()!=0) {
			if (L.size()== 1) {
				((SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre"))).demandeVendeur(L.get(0), (IVendeurContratCadre)Filiere.LA_FILIERE.getActeur("EQ5"), (Object)c, e, this.cryptogramme, true);
				}
			else {
				// On choisit aléatoirement un des distributeurs
				Random randomizer = new Random();
				IAcheteurContratCadre random = L.get(randomizer.nextInt(L.size()));
				((SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre"))).demandeVendeur(random, (IVendeurContratCadre)Filiere.LA_FILIERE.getActeur("EQ5"), (Object)c, e, this.cryptogramme, true);
			}
		}
	}
	
	//Karla
	/* on regarde l etat de nos stocks et on lance la procédure */
	public void next() {
		super.next();
		for (Chocolat c : this.stockChocolat.getProduitsEnStock()) {
			if (this.stockChocolat.getstock(c) > this.SeuilMinChocolat) {
				ChocolatDeMarque choco = new ChocolatDeMarque(c,"BIO'RIGINAL");
				lanceruncontratVendeur(choco);
			}
		}
	}
	
	//Yves
	public boolean vend(Object produit) {
		if (stockChocolat.getProduitsEnStock().contains(produit) == true) {
			return true;
		}
		else {
			return false;
		}
	}

	//Yves
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		List<Echeancier> listeEcheanciers=contrat.getEcheanciers();
		int l = listeEcheanciers.size();
		return listeEcheanciers.get(l-1);
	}

	//Yves
	public double propositionPrix(ExemplaireContratCadre contrat) {
		if (contrat.getProduit() instanceof Chocolat) {
			if (((Chocolat)(contrat.getProduit())).isOriginal() == true ) {
				return 2*(this.seuilMaxAchat+this.coutTransformation.getValeur()+this.coutOriginal.getValeur());
			}
			else {
				return 2*(this.seuilMaxAchat+this.coutTransformation.getValeur());
			}
		}
		else {
			return 0.0;
		}
	}

	@Override
	//Yves
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		if (contrat.getPrix()>(this.seuilMaxAchat+this.coutTransformation.getValeur())) {
			return contrat.getPrix();
		}
		else {
			double Nprix = 1.4*(this.seuilMaxAchat+this.coutTransformation.getValeur());
			if (contrat.getPrix()>(this.seuilMaxAchat+this.coutTransformation.getValeur())) {
				return Nprix;
			}
			return 0.0;
		}
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		
	}

	//Karla
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		Chocolat c = (Chocolat)produit;
		double peutlivrer = Math.min(this.stockChocolat.getstock(c), quantite);
		if (peutlivrer>0.0) {
			this.stockChocolat.utiliser(c, peutlivrer);
		}
		return peutlivrer;
	}
	
}