package abstraction.eq6Distributeur1;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Gamme;

public class Acheteur_Contrat extends Distributeur1Acteur implements IAcheteurContratCadre{ //leorouppert

@Override
public boolean achete(Object produit) {//a modifier avec considération de stockage
	if (produit instanceof Chocolat) {
		if (((Chocolat) produit).getGamme() == Gamme.BASSE && !((Chocolat) produit).isBioEquitable() && !((Chocolat) produit).isOriginal()) {
			return true; }//BQ
		if (((Chocolat) produit).getGamme() == Gamme.BASSE && !((Chocolat) produit).isBioEquitable() && ((Chocolat) produit).isOriginal()) {
			return true; }//BQ-O
		if (((Chocolat) produit).getGamme() == Gamme.MOYENNE && !((Chocolat) produit).isBioEquitable() && !((Chocolat) produit).isOriginal()) {
			return true; }//MQ
		if (((Chocolat) produit).getGamme() == Gamme.MOYENNE && ((Chocolat) produit).isBioEquitable() && !((Chocolat) produit).isOriginal()) {
			return true; }//MQ-BE
		if (((Chocolat) produit).getGamme() == Gamme.MOYENNE && !((Chocolat) produit).isBioEquitable() && ((Chocolat) produit).isOriginal()) {
			return true; }//MQ-O
		if (((Chocolat) produit).getGamme() == Gamme.HAUTE && !((Chocolat) produit).isBioEquitable() && !((Chocolat) produit).isOriginal()) {
			return true; }//HQ
		if (((Chocolat) produit).getGamme() == Gamme.HAUTE && ((Chocolat) produit).isBioEquitable() && !((Chocolat) produit).isOriginal()) {
			return true; }//HQ-BE
	}
	return false;
}
@Override
public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {//pas de négociation
	return contrat.getEcheancier();
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
	this.setStockage((Chocolat) produit, this.getStockage((Chocolat) produit) + quantite);
}
}

