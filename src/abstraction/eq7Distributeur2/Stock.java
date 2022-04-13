package abstraction.eq7Distributeur2;

import java.util.List;
import java.util.HashMap;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Stock implements IStock{

	private HashMap<ChocolatDeMarque,Double> reserve;
	private HashMap<ChocolatDeMarque,Double> seuilRachat;
	
	public Stock() {
		List<ChocolatDeMarque> listeChocolatsProduits = Filiere.LA_FILIERE.getChocolatsProduits();
		
		//Initialisation des stocks physique
		reserve = new HashMap<ChocolatDeMarque,Double>();
		//Initialisation du seuil de rachat de stock
		seuilRachat = new HashMap<ChocolatDeMarque,Double>();
		
		//Initialisation des deux à 0
		for (ChocolatDeMarque choco : listeChocolatsProduits) {
			reserve.put(choco, 0.);
			seuilRachat.put(choco,0.);
		}
	}
	
	@Override
	public double getQuantite(ChocolatDeMarque chocolat) {
		return reserve.get(chocolat);
	}

	@Override
	public void addProduit(ChocolatDeMarque chocolat, Double quantite) {
		double quantiteActuelle = this.getQuantite(chocolat);
		reserve.replace(chocolat,quantiteActuelle+quantite);
	}

	@Override
	public void remove(ChocolatDeMarque chocolat, Double quantite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getSeuilRachat(ChocolatDeMarque chocolat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setSeuilRachat(ChocolatDeMarque chocolat, Double seuil) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
