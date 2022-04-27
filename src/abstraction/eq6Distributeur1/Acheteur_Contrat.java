package abstraction.eq6Distributeur1;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.ChocolatDeMarqueDeMarque;
import abstraction.eq8Romu.produits.Gamme;

public class Acheteur_Contrat extends Distributeur1Acteur implements IAcheteurContratCadre{ //leorouppert

@Override
public boolean achete(Object produit) {//a modifier avec considération de stockage
	if (produit instanceof ChocolatDeMarqueDeMarque && this.getNotreStock().getStockage((ChocolatDeMarque) produit) <= 1) {
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
public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {//pas de négociation
	return contrat.getEcheancier();
	//return new Echeancier(1,12,100);
}
@Override
public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
	return 0.9*contrat.getPrix();
}
@Override
public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
}
@Override
public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
	this.getNotreStock().setStockage((ChocolatDeMarque) produit, this.getNotreStock().getStockage((ChocolatDeMarque) produit) + quantite);
}
}

