package abstraction.eq3Transformateur1;
import java.util.ArrayList;
import java.util.List;




import abstraction.eq8Romu.produits.Gamme;
import abstraction.eq8Romu.produits.Chocolat;
import java.util.HashMap;
import abstraction.eq8Romu.produits.Feve;

public class Transformateur1 extends Transformateur1Acteur {

	
	private List<Double> prixtransfo ;
	
	
	private HashMap<Chocolat, Integer> quantiteVendue;
	private HashMap<Chocolat, Double> dernierPrixVente;
	private HashMap<Feve, Double> prixAchat;
	private HashMap<Feve, Integer> stockFeve;
	private HashMap<Chocolat,Integer> stockChoco;
	

	public Transformateur1() { 
		super();
	}

	/** détermine le prix d'achat max */
	public void prixmaxachat(HashMap<Chocolat, Double> dernierPrixVente) {
		
			prixAchat.put(FEVE_BASSE,dernierPrixVente.get("MQ") / prixtransfo.get(1));	
			prixAchat.put(FEVE_MOYENNE,dernierPrixVente.get("MQ") / prixtransfo.get(0));
			prixAchat.put(FEVE_MOYENNE_BIO_EQUITABLE,dernierPrixVente.get("MQ_BE") / prixtransfo.get(0));
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




