package abstraction.eq3Transformateur1;

import java.util.HashMap;

import abstraction.eq8Romu.produits.Chocolat;

public class dernierPrixVenteChoco { 
	/** prix minimum (par unité) négocié au dernier tour auquel on a vendu le chocolat avec tel distributeur 
	 * c'est un dictionnaire de dictionnaire dont le premier dictionnaire a pour clé les distributeurs et le deuxième les chocolats 
	 * Alexandre */
	private HashMap<String, HashMap<Chocolat, Double>> PrixVente;
	
	public dernierPrixVenteChoco() {
		HashMap<Chocolat, Double> dernierPrixVenteDistrib1 = new HashMap<Chocolat, Double>();
		for (Chocolat c : Chocolat.values()) {
			dernierPrixVenteDistrib1.put(c, 0.);
		}
		HashMap<Chocolat, Double> dernierPrixVenteDistrib2 = new HashMap<Chocolat, Double>();
		for (Chocolat c : Chocolat.values()) {
			dernierPrixVenteDistrib2.put(c, 0.);
		}
		this.PrixVente = new HashMap<String, HashMap<Chocolat, Double>>();
		this.PrixVente.put("distributeur1", dernierPrixVenteDistrib1);
		this.PrixVente.put("distributeur2", dernierPrixVenteDistrib2);
	}
	
	public double getPrix(String distributeur, Chocolat chocolat) {
		return this.PrixVente.get(distributeur).get(chocolat);
	}
	
	public void setPrix(String distributeur, Chocolat chocolat, double prix) {
		this.PrixVente.get(distributeur).put(chocolat, prix);
	}

}
