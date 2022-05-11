package abstraction.eq6Distributeur1;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Gamme;

public class Acheteur_Contrat extends Distributeur1Acteur implements IAcheteurContratCadre{//leorouppert

	public Acheteur_Contrat() {
		super();
		System.out.println("Création Acheteur_Contrat");
	}
	
@Override
public boolean achete(Object produit) {
	if (produit instanceof ChocolatDeMarque && this.getNotreStock().getStock((ChocolatDeMarque) produit) <= 1) {
		if (((ChocolatDeMarque) produit).getGamme() == Gamme.BASSE && !((ChocolatDeMarque) produit).isBioEquitable() && !((ChocolatDeMarque) produit).isOriginal()) {
			return true; }//BQ
		if (((ChocolatDeMarque) produit).getGamme() == Gamme.BASSE && !((ChocolatDeMarque) produit).isBioEquitable() && ((ChocolatDeMarque) produit).isOriginal()) {
			return true; }//BQ-O
		if (((ChocolatDeMarque) produit).getGamme() == Gamme.MOYENNE && !((ChocolatDeMarque) produit).isBioEquitable() && !((ChocolatDeMarque) produit).isOriginal()) {
			return true; }//MQ
		if (((ChocolatDeMarque) produit).getGamme() == Gamme.MOYENNE && ((ChocolatDeMarque) produit).isBioEquitable() && !((ChocolatDeMarque) produit).isOriginal()) {
			return true; }//MQ-BE
		if (((ChocolatDeMarque) produit).getGamme() == Gamme.MOYENNE && !((ChocolatDeMarque) produit).isBioEquitable() && ((ChocolatDeMarque) produit).isOriginal()) {
			return true; }//MQ-O
		if (((ChocolatDeMarque) produit).getGamme() == Gamme.HAUTE && !((ChocolatDeMarque) produit).isBioEquitable() && !((ChocolatDeMarque) produit).isOriginal()) {
			return true; }//HQ
		if (((ChocolatDeMarque) produit).getGamme() == Gamme.HAUTE && ((ChocolatDeMarque) produit).isBioEquitable() && !((ChocolatDeMarque) produit).isOriginal()) {
			return true; }//HQ-BE
	}
	return false;
}
@Override
public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
	Echeancier ech = contrat.getEcheancier();
	if ((ech.getQuantite(ech.getStepDebut()) < 400)) {
		ech.set(ech.getStepDebut(), ech.getQuantite(ech.getStepDebut())*2);
		ech.set(ech.getStepDebut()+1, ech.getQuantite(ech.getStepDebut())*2);
		ech.set(ech.getStepDebut()+2, ech.getQuantite(ech.getStepDebut())*2);
	}
	return ech;
}

@Override
public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
	if (Math.random() < 0.5) {
		return 0.9*contrat.getPrix();
	}
	return 0.95*contrat.getPrix();
}

@Override
public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
	this.mesContrats.add(contrat);
}

@Override
public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
	this.getNotreStock().addQte((ChocolatDeMarque) produit, quantite);
}
}

