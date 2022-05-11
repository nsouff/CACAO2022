package abstraction.eq2Producteur2;

import java.util.LinkedList;
import java.util.HashMap;
import abstraction.eq2Producteur2.Stock;
import abstraction.eq8Romu.produits.Feve;


// auteur Clément //

public class Producteur2Stockage extends Producteur2Acteur {
	
	protected HashMap<Feve,LinkedList<Stock>> Stocks;
	protected HashMap<Feve,Double> StockTot;
	
	public Producteur2Stockage () {
		super();
		
		this.Stocks = new HashMap<Feve,LinkedList<Stock>>();
		for (Feve f : Feve.values()) {
			Stocks.put(f,new LinkedList<Stock>());
		}
		
		this.StockTot = new HashMap<Feve, Double>();
		for (Feve f : Feve.values()) {
			StockTot.put(f, SommeQuantite(new LinkedList<Stock>()));
		}

		
	}
	public Double SommeQuantite(LinkedList<Stock> L) {
		double s = 0 ;
		for (int i=0 ; i<L.size() ; i++) {
			s = s + (L.get(i)).getQuantite();
		}
		return s;	
	}
	public void removeQuantite(double q, Feve f ) {
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
	public void initialiser() {
	}
}