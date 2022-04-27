package abstraction.eq4Transformateur2;

public class Transformateur2Vente extends Transformateur2Achat {
 private double marge;
 private int prix_transfo;
 private int cout_stockage;
 
 
 
 public double prixVoulu(double marge) {
	 return 0; // Calcul du prix de vente voulu en fonction du prix d'achat moyen, du prix de trransformation,
	 // du coup de stockage, de l'origininalité et de la marge voulu
 }
 public boolean StockDispo() {
	 return false; // getStock() - quantitéVoulue 
		 
	 }
 public boolean prixAcceptable() {
	 int prixAchatDistrib = 0;
	 return false; // Prix d'achat du distributeur - prix_voulu > 0
 }
	 
}
