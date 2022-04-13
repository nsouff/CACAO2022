package abstraction.eq7Distributeur2;

import abstraction.eq8Romu.produits.ChocolatDeMarque;

public interface IStock {
	
	//Stocks
	
	public double getQuantite(ChocolatDeMarque chocolat);
	
	public double addProduit(ChocolatDeMarque chocolat, Double quantite);
	
	public void remove(ChocolatDeMarque chocolat,Double quantite);
	
	
	//Seuil de rachat
	
	public double getSeuilRachat(ChocolatDeMarque chocolat);
	
	public double setSeuilRachat(ChocolatDeMarque chocolat, Double seuil);
}
