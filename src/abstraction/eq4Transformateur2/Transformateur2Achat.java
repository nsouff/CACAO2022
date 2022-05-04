package abstraction.eq4Transformateur2;

import abstraction.eq8Romu.filiere.Filiere;

public class Transformateur2Achat extends Transformateur2Transfo{
	
	public double prix_seuil; // Prix d'achat maximum
	private int stock_theorique; //On souhaite garder ce nombre de feves en stock constamment (opportunisme)
	
	public boolean comparer_prix(double prix_vente) {
		return prix_vente<prix_seuil; //On achete pas au dessus du prix seuil
	}
	
	public int quantite(int stock) { //Quantite a acheter pour garder le stock theorique
		return 0;//La méthode getStock n'est pas encore créée
	}
	
	public boolean deficit(int quantite, double capital, double prix_vente) { //True si l'achat nous mettrait en deficit
		return capital-quantite*prix_vente>0;
	}
	
	public boolean Achat(double prix_vente,int stock,double capital) {//on achete ou pas
		return comparer_prix(prix_vente)&&deficit(quantite(stock), capital, prix_vente);
		
	}

	
}
