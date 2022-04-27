package abstraction.eq6Distributeur1;

import abstraction.eq7Distributeur2.IAcheteurContratCadre;

public class Acheteur_Contrat extends Distributeur1Acteur implements IAcheteurContratCadre{

private float duree_contrat;
private float prix_achat;
private float qte_achat;
private Object produit;

public Acheteur_Contrat(float duree_contrat, float prix_achat, float qte_achat) { //leorouppert
	this.duree_contrat = duree_contrat;
	this.prix_achat = prix_achat;
	this.qte_achat = qte_achat;
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
}
