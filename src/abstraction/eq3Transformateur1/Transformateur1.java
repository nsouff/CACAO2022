package abstraction.eq3Transformateur1;
import java.util.ArrayList;
import java.util.List;




import abstraction.eq8Romu.produits.Gamme;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

import java.util.HashMap;
import abstraction.eq8Romu.produits.Feve;

public class Transformateur1 extends Transformateur1Acteur {
	public static final int rendementHaute=1;
	public static final int coutTransfoNormal=1;
	public static final int coutTransfoOriginal=2;
	
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

	/**
	 * @return the stockFeve
	 */
	public HashMap<Feve, Integer> getStockFeve() {
		return stockFeve;
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

	/**
	 * @param stockChoco the stockChoco to set
	 */
	public void setStockChoco(HashMap<Chocolat, Integer> stockChoco) {
		this.stockChoco = stockChoco;
	}
	
	/** détermine la quantité à transformer 
	 * Alexandre */ 
	public int transfoQt(int stockFeveQt) {
		return stockFeveQt ;
	}
	
	/** détermine le type de transformation 
	 * Alexandre */
	public String choixTypeTransfo(Gamme stockFeveType) {
		if (stockFeveType.equals(Gamme.BASSE)) {
			return "transfoHaute" ;
		} /** else if(stockFeveType.equals(Gamme.MOYENNE) // stockFeveType.equals(Gamme.MOYENNE)){
			
		} */
		return "transfoBasse";
	}
	
	/** calcule le coût de la transformation et la quantité de chocolat produite
	 * Alexandre */
	public ArrayList<Integer> coutQuantiteTransfo(String typeTransfo, int quantiteFeve, boolean original) {
		ArrayList<Integer> prixQuantite = new ArrayList<Integer>();
		if (original) {
			prixQuantite.add(quantiteFeve*coutTransfoOriginal);
		}
		prixQuantite.add(quantiteFeve*coutTransfoNormal);
		if (typeTransfo.contentEquals("transfoHaute")) {
			prixQuantite.add(quantiteFeve*rendementHaute);
			return prixQuantite;
		}
		prixQuantite.add(quantiteFeve);
		return prixQuantite;
	}
	
	/** modifie les stocks suite à la transformation 
	 * (penser à ajouter l'exception si quantitefeve>quantiteStockFeve) 
	 * Alexandre */
	public void transfo(int quantiteFeveTransformee, Feve feve, boolean original) {
		for (Feve f : stockFeve.keySet()) {
			if (f == feve) {
				stockFeve.put(feve, stockFeve.get(feve)-quantiteFeveTransformee);
			}
		}
		ArrayList<Integer> coutQuantiteTransfo = this.coutQuantiteTransfo(this.choixTypeTransfo(feve.getGamme()), quantiteFeveTransformee, original);
		for (Chocolat c : stockChoco.keySet()) {
			if (c.getGamme()==Gamme.MOYENNE && c.isBioEquitable()==feve.isBioEquitable() && c.isOriginal()==original) {
				stockChoco.put(c, stockChoco.get(c)-coutQuantiteTransfo.get(1));
			}
		}
	}
}
