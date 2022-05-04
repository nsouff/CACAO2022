package abstraction.eq4Transformateur2;

import abstraction.eq8Romu.filiere.Filiere;

public class Transformateur2Vente extends Transformateur2Achat {
 private double marge;
 private int prix_transfo;
 private int prix_ori;
 
 
 // Gabriel
 public double prixVoulu(double prix_achat) { 
	 return (prix_achat + prix_transfo + Transformateur2Acteur.Test.getCout()*+ prix_ori)*marge; 
	 // Calcul du prix de vente voulu en fonction du prix d'achat précédent, du prix de transformation,
	 // du cout de stockage, de l'origininalité et de la marge voulue
 }
 // Gabriel
 public boolean StockDispo() {
	 return false; // getStock() - quantitéVoulue > 0

 }
 // Gabriel
 public boolean prixAcceptable(double prix_demande, double prix_achat) {
	 return (prix_demande - this.prixVoulu(prix_achat)>0); // Prix d'achat du distributeur - prix_voulu > 0
 }
 // Gabriel
 public boolean vente() {
	 return this.StockDispo() & this.prixAcceptable(prix_ori, prix_ori);
 }

 // Gabriel
 //public void enlever() {
	 //if (this.vente()){
		 //this.quantite.replace("Fèves", this.quantite("Fèves")-qt);///
		 
	 //}
 //} 
private double test(Transformateur2Achat izi) {
	return this.prix_seuil;
}
}

