package abstraction.eq6Distributeur1;

import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;

import java.awt.Color;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;

public class Contrats implements IVendeurContratCadre, IAcheteurContratCadre{

private float duree_contrat;
private float prix_achat;
private float qte_achat;
private Object produit;

public Contrats(float duree_contrat, float prix_achat, float qte_achat) {
	this.duree_contrat = duree_contrat;
	this.prix_achat = prix_achat;
	this.qte_achat = qte_achat;
}


/**
 * @return the duree_contrat
 */
public float getDuree_contrat() {
	return duree_contrat;
}


/**
 * @return the prix_achat
 */
public float getPrix_achat() {
	return prix_achat;
}


/**
 * @return the qte_achat
 */
public float getQte_achat() {
	return qte_achat;
}


@Override
public String getNom() {
	return "EQ6";
}
@Override
public String getDescription() {
	return null;
}
@Override
public Color getColor() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void initialiser() {
	// TODO Auto-generated method stub
	
}
@Override
public void next() {
	// TODO Auto-generated method stub
	
}
@Override
public List<String> getNomsFilieresProposees() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Filiere getFiliere(String nom) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public List<Variable> getIndicateurs() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public List<Variable> getParametres() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public List<Journal> getJournaux() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void setCryptogramme(Integer crypto) {
	// TODO Auto-generated method stub
	
}
@Override
public void notificationFaillite(IActeur acteur) {
	// TODO Auto-generated method stub
	
}
@Override
public void notificationOperationBancaire(double montant) {
	// TODO Auto-generated method stub
	
}
@Override
public boolean achete(Object produit) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
	// TODO Auto-generated method stub
	
}
@Override
public boolean vend(Object produit) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public double propositionPrix(ExemplaireContratCadre contrat) {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
	// TODO Auto-generated method stub
	
}
@Override
public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
	// TODO Auto-generated method stub
	return 0;
}


}
