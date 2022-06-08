package abstraction.eq7Distributeur2.tools;

import java.util.HashMap;
import java.util.List;

import abstraction.eq7Distributeur2.Distributeur2ChocolatDeMarque;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class VenteTracker extends Distributeur2ChocolatDeMarque{
	private HashMap<ChocolatDeMarque,Double> previousVentes;
	private HashMap<ChocolatDeMarque,List<Double>> ventes;
	
	public VenteTracker(List<ChocolatDeMarque> listeChocolatsProduits) {
		super();
		ventes = new HashMap<ChocolatDeMarque,List<Double>>();
		previousVentes = new HashMap<ChocolatDeMarque,Double>();
		
		for (ChocolatDeMarque choc : listeChocolatsProduits) {
			
		}
		
	}
	
	//chaque vente d'un type de chocolat est ajouté à la HashMap
	public void trackVentes(ChocolatDeMarque choco, Double quantite){
		List<Double> quantites = ventes.get(choco);
		quantites.add(quantite);
		ventes.put(choco, quantites);
	}
	
	public Double getPreviousVente(ChocolatDeMarque choco) {
		List<Double> quantites = ventes.get(choco);
		Double lastQuantite = quantites.get(quantites.size()-1);
		previousVentes.replace(choco, lastQuantite);
		return previousVentes.get(choco);
	}
	
	public HashMap<ChocolatDeMarque,List<Double>> getVentes(){
		return this.ventes;
	}
}
