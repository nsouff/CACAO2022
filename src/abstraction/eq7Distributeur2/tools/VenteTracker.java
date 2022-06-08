package abstraction.eq7Distributeur2.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq7Distributeur2.Distributeur2ChocolatDeMarque;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class VenteTracker extends Distributeur2ChocolatDeMarque{
	private HashMap<ChocolatDeMarque,Double> previousVentesQuantite;
	private HashMap<ChocolatDeMarque,Double> previousVentesPrix;
	
	private HashMap<ChocolatDeMarque,List<Double>> ventesQuantite;
	private HashMap<ChocolatDeMarque,List<Double>> ventesPrix;
	
	public VenteTracker(List<ChocolatDeMarque> listeChocolatsProduits) {
		ventesQuantite = new HashMap<ChocolatDeMarque,List<Double>>();
		previousVentesQuantite = new HashMap<ChocolatDeMarque,Double>();
		
		ventesPrix = new HashMap<ChocolatDeMarque,List<Double>>();
		previousVentesPrix = new HashMap<ChocolatDeMarque,Double>();
		
		for (ChocolatDeMarque choc : listeChocolatsProduits) {
			ventesQuantite.put(choc, new ArrayList<Double>());
			previousVentesQuantite.put(choc, 0.0);
			
			ventesPrix.put(choc, new ArrayList<Double>());
			previousVentesPrix.put(choc, 0.0);
		}
	}
	
	//chaque quantite vendue d'un type de chocolat est ajouté à la HashMap
	public void trackVentesQuantite(ChocolatDeMarque choco, Double quantite){
		List<Double> quantites = ventesQuantite.get(choco);
		quantites.add(quantite);
		ventesQuantite.put(choco,quantites);
	}
	
	//chaque prix de vente d'un type de chocolat est ajouté à la HashMap
	public void trackVentePrix(ChocolatDeMarque choco, Double prix){
		List<Double> prixDeVente = ventesPrix.get(choco);
		prixDeVente.add(prix);
		ventesPrix.put(choco,prixDeVente);
	}
	
	//retourne la quantite de la dernière vente du chocolat choco
	public Double getPreviousVenteQuantite(ChocolatDeMarque choco) {
		List<Double> quantites = ventesQuantite.get(choco);
		Double lastQuantite = quantites.get(quantites.size()-1);
		previousVentesQuantite.replace(choco, lastQuantite);
		return previousVentesQuantite.get(choco);
	}
	
	//retourne le prix de la dernière vente du chocolat choco
	public Double getPreviousVentePrix(ChocolatDeMarque choco) {
		List<Double> quantites = ventesPrix.get(choco);
		Double lastQuantite = quantites.get(quantites.size()-1);
		previousVentesPrix.replace(choco, lastQuantite);
		return previousVentesPrix.get(choco);
	}
	
	//retourne la HashMap de toutes les quantites vendues (historique)
	public HashMap<ChocolatDeMarque,List<Double>> getVentesQuantite(){
		return this.ventesQuantite;
	}
	
	//retourne la HashMap de tous les prix de vente (historique)
		public HashMap<ChocolatDeMarque,List<Double>> getVentesPrix(){
			return this.ventesPrix;
		}
}
