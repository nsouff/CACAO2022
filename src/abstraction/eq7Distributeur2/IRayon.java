package abstraction.eq7Distributeur2;

import abstraction.eq8Romu.produits.ChocolatDeMarque;

public interface IRayon {
	
	//Rayon classique
	
	public double getQuantiteClassique(ChocolatDeMarque chocolat);
	
	public void rechargerClassique(ChocolatDeMarque chocolat, Double quantite);
	
	public void venteClassique(ChocolatDeMarque chocolat,Double quantite);
	
	public void renvoyerEnStockClassique(ChocolatDeMarque chocolat,Double quantite);
	
	//Rayon tête de gondole
	
	public double getQuantiteGondole(ChocolatDeMarque chocolat);
	
	public void rechargerGondole(ChocolatDeMarque chocolat, Double quantite);
	
	public void venteGondole(ChocolatDeMarque chocolat,Double quantite);
	
	public void renvoyerEnStockGondole(ChocolatDeMarque chocolat,Double quantite);
	
	//Rayons total
	
	public double getQuantiteTotaleChocolat(ChocolatDeMarque chocolat);
	
	public double getQuantiteTotale();
	
	public double getQuantiteTotaleClassique();
	
	public double getQuantiteTotaleGondole();
	
}