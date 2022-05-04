package abstraction.eq4Transformateur2;

import abstraction.eq8Romu.filiere.Filiere;

public class Transformateur2Achat extends Transformateur2Transfo{
	
<<<<<<< HEAD
	public Transformateur2Achat(double stocktotalfeve, double stocktotalchoco) {
		super(stocktotalfeve, stocktotalchoco);
		// TODO Auto-generated constructor stub
	}

	public double prix_seuil; // Prix d'achat maximum
	private int stock_theorique; //On souhaite garder ce nombre de feves en stock constamment (opportunisme)
=======
>>>>>>> branch 'main' of https://github.com/Gabeaugosse/CACAO2022.git
	
	public boolean comparer_prix(double prix_vente) {
<<<<<<< HEAD
		return prix_vente<prix_seuil; //On achete pas au dessus du prix seuil
		
=======
		return prix_vente<super.getPrixSeuil().getValeur(); //On achete pas au dessus du prix seuil
>>>>>>> branch 'main' of https://github.com/Gabeaugosse/CACAO2022.git
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
