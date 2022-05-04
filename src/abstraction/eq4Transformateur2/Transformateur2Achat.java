package abstraction.eq4Transformateur2;

import abstraction.eq8Romu.filiere.Filiere;

public class Transformateur2Achat extends Transformateur2Transfo{
	
	
	public boolean comparer_prix(double prix_vente) {
		return prix_vente<super.getPrixSeuil().getValeur(); //On achete pas au dessus du prix seuil
	}
	
	public double quantite(int stock) { 
		return super.getCapaciteStockageFixe().getValeur()-super.getStockfeve().quantiteStockTotale();
	}
	
	public boolean deficit(double quantite, double capital, double prix_vente) { //False si l'achat nous mettrait en deficit
		return true;
	}
	
	public boolean Achat(double prix_vente,int stock,double capital) {//on achete ou pas
		return comparer_prix(prix_vente)&&deficit(quantite(stock), capital, prix_vente);
		
	}

	
}
