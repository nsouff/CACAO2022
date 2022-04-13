package abstraction.eq4Transformateur2;

public class Transformateur2Achat extends Transformateur2{
	
	private double prix_seuil;
	private int stock_theorique;
	
	public boolean comparer_prix(double prix_vente) {
		return prix_vente<prix_seuil; //On achete pas au dessus du prix seuil
	}
	
	public int quantite(int stock) { //Quantite a acheter pour garder le stock theorique
		return stock_theorique-StockFeve.getStock();//La classe StockFeve n'est pas encore créée
	}
	
	public boolean deficit(int quantite, double capital, double prix_vente) { //True si l'achat nous mettrait en deficit
		return capital-quantite*prix_vente>0;
	}
	
	public boolean Achat(double prix_vente,int stock,double capital) {//on achete ou pas
		return comparer_prix(prix_vente)&&deficit(quantite(stock), double capital, double prix_vente);
	}

}
