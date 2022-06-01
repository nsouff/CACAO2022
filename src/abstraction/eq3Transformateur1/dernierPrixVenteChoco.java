
package abstraction.eq3Transformateur1;

import java.util.ArrayList;
import java.util.HashMap;

import abstraction.eq8Romu.produits.Chocolat;

/** prix minimum (par unité) négocié au dernier tour auquel on a vendu le chocolat avec tel distributeur 
 * c'est un dictionnaire de dictionnaire dont le premier dictionnaire a pour clé les distributeurs et le deuxième les chocolats 
 * Alexandre */
public class dernierPrixVenteChoco { 
	private HashMap<String, HashMap<Chocolat, Double>> prixVente;
	
	/** Constructeur, remplis les clés
	 *  Alexandre */
	public dernierPrixVenteChoco() {
		HashMap<Chocolat, Double> dernierPrixVenteDistrib1 = new HashMap<Chocolat, Double>();
		for (Chocolat c : Chocolat.values()) {
			dernierPrixVenteDistrib1.put(c, 0.);
		}
		HashMap<Chocolat, Double> dernierPrixVenteDistrib2 = new HashMap<Chocolat, Double>();
		for (Chocolat c : Chocolat.values()) {
			dernierPrixVenteDistrib2.put(c, 0.);
		}
		this.prixVente = new HashMap<String, HashMap<Chocolat, Double>>();
		this.prixVente.put("distributeur1", dernierPrixVenteDistrib1);
		this.prixVente.put("distributeur2", dernierPrixVenteDistrib2);
	}
	
	private HashMap<String, HashMap<Chocolat, Double>> getPrixVente() {
		return this.prixVente;
	}
	
	/** Getter
	 *  Alexandre*/
	public double getPrix(String distributeur, Chocolat chocolat) {
		return this.getPrixVente().get(distributeur).get(chocolat);
	}
	
	/** Setter 
	    Alexandre */
	public void setPrix(String distributeur, Chocolat chocolat, double prix) {
		this.getPrixVente().get(distributeur).put(chocolat, prix);
	}
	
	/** get la liste des distributeurs
	 *  Alexandre */
	public ArrayList<String> getDistributeurs() {
		ArrayList<String> distrib = new ArrayList<String>();
		for (String s : this.getPrixVente().keySet()) {
			distrib.add(s);
		}
		return distrib;
	}
	
	/** get la liste des chocolats dans dernierPrixVenteChoco
	 *  Alexandre */
	public ArrayList<Chocolat> getChocolats() {
		ArrayList<Chocolat> chocolats = new ArrayList<Chocolat>();
		for (Chocolat c : this.getPrixVente().get(this.getDistributeurs().get(0)).keySet()) {
			chocolats.add(c);
		}
		return chocolats;
	}
	
	/** renvoie une copie de l'objet dernierPrixVenteChoco
	 *  Alexandre */
	public dernierPrixVenteChoco copy() {
		dernierPrixVenteChoco copy = new dernierPrixVenteChoco();
		for (String distrib : this.getDistributeurs()) {
			for (Chocolat c : this.getChocolats()) {
				copy.setPrix(distrib, c, this.getPrix(distrib, c));
			}
		}
		return copy;
	}

}
