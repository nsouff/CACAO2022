package abstraction.eq6Distributeur1;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Gamme;

public class Acheteur_Contrat extends Distributeur1Acteur implements IAcheteurContratCadre{

private float duree_contrat;
private float prix_achat;
private float qte_achat;
private int num_vendeur;
private Object produit;

public Acheteur_Contrat(float duree_contrat, float prix_achat, float qte_achat, int num_vendeur) { //leorouppert
	this.duree_contrat = duree_contrat;
	this.prix_achat = prix_achat;
	this.num_vendeur = num_vendeur;
	this.qte_achat = qte_achat;
}
/**
 * @return the num_vendeur
 */
public int getNum_vendeur() {//leorouppert
	return num_vendeur;
}
/**
 * @return the duree_contrat
 */
public float getDuree_contrat() {//leorouppert
	return duree_contrat;
}
/**
 * @return the prix_achat
 */
public float getPrix_achat() {//leorouppert
	return prix_achat;
}
/**
 * @return the qte_achat
 */
public float getQte_achat() {//leorouppert
	return qte_achat;
}
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
	x.setStock(x.getStock()+quantite);
}
}

