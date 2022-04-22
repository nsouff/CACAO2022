package abstraction.eq3Transformateur1;
import java.util.ArrayList;
import java.util.List;




import abstraction.eq8Romu.produits.Gamme;
import abstraction.eq8Romu.produits.Chocolat;
import java.util.HashMap;
import abstraction.eq8Romu.produits.Feve;

public class Transformateur1 extends Transformateur1Acteur {

	
	private List<Double> prixtransfo ;
	private HashMap<Feve, Integer> quantiteAchat;
	private HashMap<Chocolat, Integer> quantiteDemandee;
	private HashMap<Chocolat, Integer> quantiteVendue;
	private HashMap<Chocolat, Double> dernierPrixVente;
	private HashMap<Feve, Double> prixAchat;
	private HashMap<Feve, Integer> stockFeve;
	private HashMap<Chocolat,Integer> stockChoco;
	

	public Transformateur1() { 
		super();
	}

	/** détermine le prix d'achat max; auteur Julien  */
	public void prixMaxAchat() {		
			prixAchat.put(Feve.FEVE_BASSE,dernierPrixVente.get(Chocolat.MQ) / prixtransfo.get(1));	
			prixAchat.put(Feve.FEVE_MOYENNE,dernierPrixVente.get(Chocolat.MQ) / prixtransfo.get(0));
			prixAchat.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE,dernierPrixVente.get(Chocolat.MQ_BE) / prixtransfo.get(0));
	}
	
	/** détermine la quantité de fèves à acheter; auteur Julien */
	public void determinationQuantiteAchat() {		
		quantiteAchat.put(Feve.FEVE_BASSE,((quantiteDemandee.get(Chocolat.MQ)-stockChoco.get(Chocolat.MQ))/2));	
		quantiteAchat.put(Feve.FEVE_MOYENNE,((quantiteDemandee.get(Chocolat.MQ)-stockChoco.get(Chocolat.MQ))/2));
		quantiteAchat.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE,(quantiteDemandee.get(Chocolat.MQ_BE)-stockChoco.get(Chocolat.MQ_BE)));
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




