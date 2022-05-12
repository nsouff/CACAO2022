package abstraction.eq2Producteur2;

import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

import abstraction.eq1Producteur1.FeveProducteur1;
import abstraction.eq2Producteur2.Stock;
import abstraction.eq8Romu.produits.Feve;


// auteur Clément //

public class Producteur2Stockage extends Producteur2Couts {
	
	protected HashMap<Feve,LinkedList<Stock>> Stocks;
	protected HashMap<Feve,Double> StockTot;
	
	public Producteur2Stockage () {
		super();
		
		this.Stocks = new HashMap<Feve,LinkedList<Stock>>();
		for (Feve f : Feve.values()) {
			this.Stocks.put(f,new LinkedList<Stock>());
		}
		
		this.StockTot = new HashMap<Feve, Double>();
		for (Feve f : Feve.values()) {
			this.StockTot.put(f, SommeQuantite(new LinkedList<Stock>()));
		}

		
	}
	public double SommeQuantite(LinkedList<Stock> L) {
		double s = 0 ;
		for (int i=0 ; i<L.size() ; i++) {
			s = s + (L.get(i)).getQuantite();
		}
		return s;	
	}
	public void removeQuantite(double q, Feve f) {
		LinkedList<Stock> L=Stocks.get(f);
		while (q>0) {
			int m=0;
			for (int i=0 ; i<L.size() ; i++) {
				if ((L.get(i)).getStep_arrivee()<L.get(m).getStep_arrivee()) {
					m=i;
				}			
			}
			if ((L.get(m)).getQuantite()>q) {
				(L.get(m)).removequantite(q);
			}else {
				(L.get(m)).removequantite((L.get(m)).getQuantite());
				q=q-(L.get(m)).getQuantite();				
			}
		
		}
	}
	public void addQuantite(double q, Feve f) {
		this.getStock().get(f).add(new Stock(q));
	}
	
	public void initialiser() {
		super.initialiser();
		this.addQuantite(1000001,Feve.FEVE_BASSE);
		this.addQuantite(1000001,Feve.FEVE_MOYENNE);
		this.addQuantite(1000001,Feve.FEVE_MOYENNE_BIO_EQUITABLE);
		this.addQuantite(1000001,Feve.FEVE_HAUTE);
		this.addQuantite(1000001,Feve.FEVE_HAUTE_BIO_EQUITABLE);
		
	}
	public void next() {
		super.next();
	//for (Feve f : Feve.values()) {
		//this.get
			
		//}
	}
	
	/**
	 * @author Jules DORE
	 */
	/*public void next() {
		super.next();
		for(Feve feve : Feve.values()) {
			this.addQuantite(this.production(feve), feve);
			this.getStockTot().put(feve, SommeQuantite(this.getStock().get(feve)));
		}
	}*/
	public HashMap<Feve, LinkedList<Stock>> getStock() {
		return this.Stocks;
	}
	public  HashMap<Feve,Double> getStockTot(){
		return this.StockTot;
	}
	
}