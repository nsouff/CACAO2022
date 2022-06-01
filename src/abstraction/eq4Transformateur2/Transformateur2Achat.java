//package abstraction.eq4Transformateur2;
//
//import java.util.HashMap;
//
//import abstraction.eq8Romu.filiere.Filiere;
//import abstraction.eq8Romu.produits.Chocolat;
//import abstraction.eq8Romu.produits.ChocolatDeMarque;
//import abstraction.eq8Romu.produits.Feve;
//
//public class Transformateur2Achat extends{
//	
//
//	public Transformateur2Achat(Stock<Feve> stockfeve, Stock<Chocolat> stockchocolat,Stock<ChocolatDeMarque> stockchocolatdemarque) {
//		super(stockfeve, stockchocolat,stockchocolatdemarque);
//		// TODO Auto-generated constructor stub
//	}
//
//
//	
//
//
//	
//	public boolean comparer_prix(double prix_vente) {
//
//
//
//		
//
//
//		return prix_vente<super.getPrixSeuil().getValeur(); //On achete pas au dessus du prix seuil
//
//		
//
//
//	}
////	public double quantite() { 
////		return super.getCapaciteStockageFixe().getValeur()-super.getStockfeve().quantiteStockTotale();
////	}
//	
//	public boolean deficit(double quantite, double prix_vente) { //False si l'achat nous mettrait en deficit
//		return (super.getSolde()-quantite*prix_vente)>0;
//	}
//	
//	public boolean Achat(double prix_vente,double quantite) {//on achete ou pas
//		return comparer_prix(prix_vente)&&deficit(quantite,prix_vente);
//		
//	}
//
//
//
//
//
//
//	@Override
//	protected HashMap getCommande() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	
//}
