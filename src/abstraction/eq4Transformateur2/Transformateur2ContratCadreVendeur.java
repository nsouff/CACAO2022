package abstraction.eq4Transformateur2;


import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

//Marie

public abstract class Transformateur2ContratCadreVendeur extends Transformateur2Acteur implements IVendeurContratCadre{
	
	public abstract Stock<ChocolatDeMarque> getStockchocolatdemarque();
	
	protected SuperviseurVentesContratCadre supCCadre;
	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeur;
	private Journal journalVente;


	public void next() {
		super.next();
		journalVente.ajouter(Color.black,Color.white,"---------------------------------------------------------------------------------------------------------------");
		journalVente.ajouter(Color.black,Color.white,"----------------------------------CONTRATS CADRES DU STEP------------------------------------------------------");
		journalVente.ajouter(Color.black,Color.white,"---------------------------------------------------------------------------------------------------------------");
		List<ExemplaireContratCadre> contratsObsoletes=new LinkedList<ExemplaireContratCadre>();
		for (ExemplaireContratCadre contrat : this.mesContratEnTantQueVendeur) {
			if (contrat.getQuantiteRestantALivrer()==0.0 && contrat.getMontantRestantARegler()==0.0) {
				contratsObsoletes.add(contrat);
			}
		}
		this.mesContratEnTantQueVendeur.removeAll(contratsObsoletes);
//<<<<<<< HEAD
//		
////		// Proposition d'un nouveau contrat a tous les acheteurs possibles pour tous nos produits (40Millions de kg de choco à chaque step)
////				for (ChocolatDeMarque c : this.getChocolatsProduits()) {
////					for (IActeur acteur : Filiere.LA_FILIERE.getActeurs()) {
////						if (acteur!=this && acteur instanceof IAcheteurContratCadre && ((IAcheteurContratCadre)acteur).achete(c)) {
////							journalVente.ajouter("Négociations avec "+acteur.getNom() +" pour "+c);
////							ExemplaireContratCadre cc = supCCadre.demandeVendeur((IAcheteurContratCadre)acteur, (IVendeurContratCadre)this, (Object)c, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, 500000), cryptogramme,false);
////							journalVente.ajouter("-->aboutit au contrat "+cc);
////						}
////					}
////				}
//=======

				for (ChocolatDeMarque c : this.getChocolatsProduits()) {
					for (IActeur acteur : Filiere.LA_FILIERE.getActeurs()) {
						if (acteur!=this && acteur instanceof IAcheteurContratCadre && ((IAcheteurContratCadre)acteur).achete(c)) {
							journalVente.ajouter("Négociations avec "+acteur.getNom() +" pour "+c);
							ExemplaireContratCadre cc = supCCadre.demandeVendeur((IAcheteurContratCadre)acteur, (IVendeurContratCadre)this, (Object)c, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, 40000), cryptogramme,false);
							journalVente.ajouter("-->aboutit au contrat "+cc);
							if (cc!=null) {
								this.mesContratEnTantQueVendeur.add(cc);
							}
							journalVente.ajouter(Color.white,Color.red,"----------------------------------------------------------------------------------------------");
						}
					}
				}
			

	}
	
	
	public void initialiser() {
		super.initialiser();
	
		this.supCCadre = (SuperviseurVentesContratCadre) (Filiere.LA_FILIERE.getActeur("Sup.CCadre"));
	}
	
	
	public Transformateur2ContratCadreVendeur() {
		super();
		this.mesContratEnTantQueVendeur=new LinkedList<ExemplaireContratCadre>();
		this.journalVente=new Journal("O'ptites Ventes", this);
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
		if (contrat.getEcheancier().getQuantiteTotale()<this.getStockchocolatdemarque().getStocktotal()) {
			journalVente.ajouter("Echeancier accepté");
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
	return 1;
}

@Override
public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
//	// TODO Auto-generated method stub
//	if (contrat.getPrix()<6) {
//		journalVente.ajouter("Prix trop bas : Rupture du contrat");
//		return 0.0; //On arrete les négociations si son prix au kg
//	} else if(contrat.getPrix()>propositionPrix(contrat)) {
//		return contrat.getPrix();
//	} else {
//		return (contrat.getPrix()+propositionPrix(contrat))/2; //On coupe la poire en 2
//	}
	return contrat.getPrix();
}

@Override
public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
	// TODO Auto-generated method stub
	this.mesContratEnTantQueVendeur.add(contrat);
	journalVente.ajouter("Contrat signé avec " +contrat.getAcheteur().getNom()+ ". Livraison prévue de "+contrat.getQuantiteTotale()+" de"+contrat.getProduit());
	
}

@Override
public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
	// TODO Auto-generated method stub
	return quantite;
}


public List<ExemplaireContratCadre> getMesContratEnTantQueVendeur() {
	return mesContratEnTantQueVendeur;
}


public Journal getJournalVente() {
	return journalVente;
}



}
