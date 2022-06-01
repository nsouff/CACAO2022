package abstraction.eq1Producteur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;


public class Producteur1StockChocolat extends ProducteurActeur1VenteBourse {
	private  HashMap<Chocolat,List<ChocolatProducteur1>> Chocolats;
	private Variable StockBQ ;
	
	
	public Producteur1StockChocolat() {
		super();
		
		this.Chocolats = new HashMap<Chocolat, List<ChocolatProducteur1>>();
		Chocolats.put(Chocolat.BQ, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.BQ_O, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.HQ, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.HQ_BE, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.HQ_BE_O, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.HQ_O, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.MQ, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.MQ_BE, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.MQ_BE_O, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.MQ_O, new ArrayList<ChocolatProducteur1>());
		
		this.StockBQ= new Variable(this.getNom()+"StockCHOCO_BQ", "Stock de Chocolat BQ", 
				this, 0, 1000000000, this.getStock(Chocolat.BQ));
	}
	
	
	public void next(){
		super.next();
		
		//Mis à jour Variable
		this.StockBQ.setValeur(this, this.getStock(Chocolat.BQ));
		
		
	}
	
	public void initialiser() {
		super.initialiser();
		this.addLot(Chocolat.BQ, 30000);
		}

	public void addLot(Chocolat c, double quantite) {
		this.getChocolats().get(c).add(new ChocolatProducteur1(quantite, this.getGhana()));

	}

	public double getStock(Chocolat c){
		
		double somme = 0.0 ;
		for(ChocolatProducteur1 Lot : this.getChocolats().get(c)) {
			somme = somme + Lot.getPoids() ;
		}
		return somme ;	
	}
	
	
public double getStockParc(Chocolat c, Parc provenance){
		
		double somme = 0.0 ;
		for(ChocolatProducteur1 Lot : this.getChocolats().get(c)) {
			if (Lot.getProvenance() == provenance) {
				somme = somme + Lot.getPoids() ;
			}
		}
		return somme ;	
	}
	
	public void retirerQuantite(Chocolat c, double quantite) {
		while (quantite>0) {
			double poids = this.getChocolats().get(c).get(0).getPoids() ;
			
			if (quantite >= poids) { //On regarde en fonction de la quantite qui reste dans le lot. Si il y'a plus on enlève le lot
				quantite = quantite - poids ;
				this.getChocolats().get(c).remove(0);
			}
			else {
				this.getChocolats().get(c).get(0).setPoids(poids-quantite); //Si non, on ajuste le poids du lot
				quantite = 0 ;
			}
		}
	}
	
	
	
	/**
	 * @return the chocolats
	 */
	public HashMap<Chocolat, List<ChocolatProducteur1>> getChocolats() {
		return Chocolats;
	}


	/**
	 * @return the stockBQ
	 */
	public Variable getStockBQ() {
		return StockBQ;
	}
	
	
	
}
