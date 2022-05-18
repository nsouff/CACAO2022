package abstraction.eq1Producteur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;

public class Producteur1StockChocolat extends Producteur1ContratCadre {
	private  HashMap<Chocolat,List<ChocolatProducteur1>> Chocolats;
	
	
	public Producteur1StockChocolat() {
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
	}
	
	
	public void initialiser() {
		super.initialiser();
		this.addLot(Feve.FEVE_BASSE, 1000000);
		}

	public void addLot(Chocolat c, double quantite) {
		this.getChocolats().get(c).add(new ChocolatProducteur1(quantite));

	}


	/**
	 * @return the chocolats
	 */
	public HashMap<Chocolat, List<ChocolatProducteur1>> getChocolats() {
		return Chocolats;
	}
	
}
