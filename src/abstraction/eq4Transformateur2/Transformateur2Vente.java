package abstraction.eq4Transformateur2;



public class Transformateur2Vente extends Transformateur2Achat {
 private double marge;
 private int prix_transfo;
 private int cout_stockage;
 private int prix_ori;
 
 
 
 public double prixVoulu(double prix_achat) {
	 return prix_achat + prix_transfo + cout_stockage + prix_ori + marge; 
	 // Calcul du prix de vente voulu en fonction du prix d'achat précédent, du prix de transformation,
	 // du cout de stockage, de l'origininalité et de la marge voulu
 }
 public boolean StockDispo() {
	 return false; // getStock() - quantitéVoulue > 0

 }
 public boolean prixAcceptable(double prix_demande, double prix_achat) {
	 return (prix_demande - this.prixVoulu(prix_achat)>0); // Prix d'achat du distributeur - prix_voulu > 0
 }
 public boolean vente() {
	 return this.StockDispo() & this.prixAcceptable(prix_ori, prix_ori);
 }
}//BLA
