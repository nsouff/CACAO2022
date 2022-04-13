package abstraction.eq3Transformateur1;
import java.util.ArrayList;
import java.util.List;




import abstraction.eq8Romu.produits.Gamme;
import abstraction.eq8Romu.produits.Chocolat;
import java.util.HashMap;
import abstraction.eq8Romu.produits.Feve;

public class Transformateur1 extends Transformateur1Acteur {

	private List<Double> dernierprixvente ;
	private List<Double> prixtransfo ;
	private List<Integer> stock;
	private List<Integer> quantitévendue ;



	private HashMap<Feve, Integer> stockFeve;
	private HashMap<Chocolat,Integer> stockChoco;

	

	public Transformateur1() { 
		super();
	}


	public List<Double> prixmaxachat(List<Double> dernierprixvente ) {
		List<Double> prixmaxachat = new ArrayList<Double>() ;
		for (int i=0;i<this.dernierprixvente.size();i++) {
			prixmaxachat.add(dernierprixvente.get(i) / (prixtransfo.get(i)*quantitévendue.get(i)*1.0));	
		}
		return prixmaxachat;
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




