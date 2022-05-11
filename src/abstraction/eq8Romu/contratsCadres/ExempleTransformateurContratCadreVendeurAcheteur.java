package abstraction.eq8Romu.contratsCadres;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.filiere.*;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class ExempleTransformateurContratCadreVendeurAcheteur extends ExempleTransformateurContratCadreVendeur implements IAcheteurContratCadre{
	protected List<ExemplaireContratCadre> mesContratEnTantQuAcheteur;

	public ExempleTransformateurContratCadreVendeurAcheteur(Object produit) {
		super(produit);
		this.mesContratEnTantQuAcheteur=new LinkedList<ExemplaireContratCadre>();
	}

	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		if (Math.random()<0.1) {
			return contrat.getEcheancier(); // on ne cherche pas a negocier sur le previsionnel de livraison
		} else {//dans 90% des cas on fait une contreproposition pour l'echeancier
			Echeancier e = contrat.getEcheancier();
			e.set(e.getStepDebut(), e.getQuantite(e.getStepDebut())*2.5);// on souhaite livrer 2.5 fois plus lors de la 1ere livraison... un choix arbitraire, juste pour l'exemple...
			return e;
		}
	}

	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		if (Math.random()<0.1) {
			return contrat.getPrix(); // on ne cherche pas a negocier dans 10% des cas
		} else {//dans 90% des cas on fait une contreproposition differente
			return contrat.getPrix()*0.95;// 5% de moins.
		}
	}
	public void next() {
		// On enleve les contrats obsolete (nous pourrions vouloir les conserver pour "archive"...)
		List<ExemplaireContratCadre> contratsObsoletes=new LinkedList<ExemplaireContratCadre>();
		for (ExemplaireContratCadre contrat : this.mesContratEnTantQuAcheteur) {
			if (contrat.getQuantiteRestantALivrer()==0.0 && contrat.getMontantRestantARegler()==0.0) {
				contratsObsoletes.add(contrat);
			}
		}
		this.mesContratEnTantQuAcheteur.removeAll(contratsObsoletes);
		
		// Proposition d'un nouveau contrat a tous les vendeurs possibles
		/*
		for (IActeur acteur : Filiere.LA_FILIERE.getActeurs()) {
			if (acteur!=this && acteur instanceof IVendeurContratCadre && ((IVendeurContratCadre)acteur).vend(produit)) {
				Filiere.LA_FILIERE.getSuperviseurContratCadre().demande((IAcheteurContratCadre)this, ((IVendeurContratCadre)acteur), produit, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, 5.0), cryptogramme);
			}
		}*/
		// OU proposition d'un contrat a un des vendeurs choisi aleatoirement
		journal.ajouter("Recherche d'un vendeur aupres de qui acheter");
		List<IVendeurContratCadre> vendeurs = supCCadre.getVendeurs(produit);
		if (vendeurs.contains(this)) {
			vendeurs.remove(this);
		}
		IVendeurContratCadre vendeur = null;
		if (vendeurs.size()==1) {
			vendeur=vendeurs.get(0);
		} else if (vendeurs.size()>1) {
			vendeur = vendeurs.get((int)( Math.random()*vendeurs.size()));
		}
		if (vendeur!=null) {
			journal.ajouter("Demande au superviseur de debuter les negociations pour un contrat cadre de "+produit+" avec le vendeur "+vendeur);
			ExemplaireContratCadre cc = supCCadre.demandeAcheteur((IAcheteurContratCadre)this, vendeur, produit, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, SuperviseurVentesContratCadre.QUANTITE_MIN_ECHEANCIER/10), cryptogramme,false);
			journal.ajouter("-->aboutit au contrat "+cc);
		}
		// Proposition d'un contrat a un des achteur choisi aleatoirement
		journal.ajouter("Recherche d'un acheteur aupres de qui vendre");
		List<IAcheteurContratCadre> acheteurs = supCCadre.getAcheteurs(produit);
		if (acheteurs.contains(this)) {
			acheteurs.remove(this);
		}
		IAcheteurContratCadre acheteur = null;
		if (acheteurs.size()==1) {
			acheteur=acheteurs.get(0);
		} else if (acheteurs.size()>1) {
			acheteur = acheteurs.get((int)( Math.random()*acheteurs.size()));
		}
		if (acheteur!=null) {
			journal.ajouter("Demande au superviseur de debuter les negociations pour un contrat cadre de "+produit+" avec l'acheteur "+acheteur);
			ExemplaireContratCadre cc = supCCadre.demandeVendeur(acheteur, (IVendeurContratCadre)this, produit, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, SuperviseurVentesContratCadre.QUANTITE_MIN_ECHEANCIER/10), cryptogramme,false);
			journal.ajouter("-->aboutit au contrat "+cc);
		}
	}

	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		stock.ajouter(this,  quantite);
	}

	public boolean achete(Object produit) {
		return true;
	}



}
