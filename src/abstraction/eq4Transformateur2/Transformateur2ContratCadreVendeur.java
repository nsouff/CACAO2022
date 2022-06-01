package abstraction.eq4Transformateur2;


import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

//Marie

public abstract class Transformateur2ContratCadreVendeur extends Transformateur2Acteur implements IVendeurContratCadre {
	
	public abstract Stock<Chocolat> getStockchocolat();
	
	protected SuperviseurVentesContratCadre supCCadre;
	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeur;


	public void next() {
		super.next();
		
		List<ExemplaireContratCadre> contratsObsoletes=new LinkedList<ExemplaireContratCadre>();
		for (ExemplaireContratCadre contrat : this.mesContratEnTantQueVendeur) {
			if (contrat.getQuantiteRestantALivrer()==0.0 && contrat.getMontantRestantARegler()==0.0) {
				contratsObsoletes.add(contrat);
			}
		}
		this.mesContratEnTantQueVendeur.removeAll(contratsObsoletes);
		
		// Proposition d'un contrat a un des achteur choisi aleatoirement
				journal.ajouter("Recherche d'un acheteur aupres de qui vendre");
				List<IAcheteurContratCadre> acheteurs = supCCadre.getAcheteurs(this.getChocolatsProduits().get(0));
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
					journal.ajouter("Demande au superviseur de debuter les negociations pour un contrat cadre de "+this.getChocolatsProduits().get(0)+" avec l'acheteur "+acheteur);
					ExemplaireContratCadre cc = supCCadre.demandeVendeur(acheteur, (IVendeurContratCadre)this, this.getChocolatsProduits().get(0), new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, SuperviseurVentesContratCadre.QUANTITE_MIN_ECHEANCIER/10), cryptogramme,false);
					journal.ajouter("-->aboutit au contrat "+cc);
				}
	}
	
	
	public void initialiser() {
		super.initialiser();
		this.supCCadre = (SuperviseurVentesContratCadre) (Filiere.LA_FILIERE.getActeur("Sup.CCadre"));
	}
	
	
	public Transformateur2ContratCadreVendeur() {
		super();
		this.mesContratEnTantQueVendeur=new LinkedList<ExemplaireContratCadre>();
	}
	
	
@Override
public boolean vend(Object produit) {
	// TODO Auto-generated method stub
	if (!(produit instanceof ChocolatDeMarque)) {
		return false;
	} else {
		return this.getChocolatsProduits().contains(produit);
	}
}

@Override
public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
	
	if (this.getChocolatsProduits().contains(contrat.getProduit())) {
		if (contrat.getEcheancier().getQuantiteTotale()<this.getStockchocolat().getStocktotal()) {
			journal.ajouter("Echeancier accepté");
			return contrat.getEcheancier();
		} else {
			return null; // on est frileux : on ne s'engage dans un contrat cadre que si on a toute la quantite en stock (on pourrait accepter meme si nous n'avons pas tout car nous pouvons produire/acheter pour tenir les engagements) 
		}
	} else {
		return null;// on ne vend pas de ce produit
	}
}

@Override
public double propositionPrix(ExemplaireContratCadre contrat) {
	// TODO Auto-generated method stub
	return 6+ (500/contrat.getQuantiteTotale()); //6€/kg s'il me prend 500kg en tout, 7€/kg s'il m'en prend que 250kg!
}

@Override
public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
	// TODO Auto-generated method stub
	if (contrat.getPrix()<6) {
		journal.ajouter("Prix trop bas : Rupture du contrat");
		return 0.0; //On arrete les négociations si son prix au kg
	} else if(contrat.getPrix()>propositionPrix(contrat)) {
		return contrat.getPrix();
	} else {
		return (contrat.getPrix()+propositionPrix(contrat))/2; //On coupe la poire en 2
	}
}

@Override
public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
	// TODO Auto-generated method stub
	this.mesContratEnTantQueVendeur.add(contrat);
	journal.ajouter("Contrat signé avec " +contrat.getAcheteur().getNom()+ ". Livraison prévue de "+contrat.getQuantiteTotale()+" de"+contrat.getProduit());
	
}

@Override
public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
	// TODO Auto-generated method stub
	return quantite;
}


public List<ExemplaireContratCadre> getMesContratEnTantQueVendeur() {
	return mesContratEnTantQueVendeur;
}
}
