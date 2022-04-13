package abstraction.eq3Transformateur1;

import abstraction.eq8Romu.produits.Gamme;
import abstraction.eq8Romu.produits.Chocolat;
import java.util.HashMap;
import abstraction.eq8Romu.produits.Feve;

public class Transformateur1 extends Transformateur1Acteur {
	private HashMap<Feve, Integer> stockFeve;
	private HashMap<Chocolat,Integer> stockChoco;

	public Transformateur1() { 
		super();
	}
	
	/** détermine la quantité à transformer */ 
	public int transfoQt(int stockFeveQt) {
		return stockFeveQt ;
	}
	
	/** détermine le type de transformation */
	public String transfoType(int contratCadre, Gamme stockFeveType) {
		if (stockFeveType.equals(Gamme.BASSE)) {
			return "transfoHaute" ;
		} /** else if(stockFeveType.equals(Gamme.MOYENNE)){
			
		} */
		return "transfoBasse";
	}
	
	public Chocolat transfo() {
		return null ;
	}
}
