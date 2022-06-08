package abstraction.eq2Producteur2;

import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

import abstraction.eq8Romu.produits.Feve;


/**
 * 
 * @author Clément Modifier par Jules
 *
 */

public class Producteur2Stockage2 extends Producteur2Couts {
	
	protected Integer cryptogramme;
	protected HashMap<Feve,LinkedList<Stock>> Stocks;

	
	public Producteur2Stockage2() {
		super();
		
		this.Stocks = new HashMap<Feve,LinkedList<Stock>>();
		for (Feve f : Feve.values()) {
			this.Stocks.put(f,new LinkedList<Stock>());
		}
	}

	public void removeQuantite(double q, Feve f) {
		LinkedList<Stock> L=this.Stocks.get(f);	

		System.out.print("while debut");
		while (q>0) {
			int m=0;
			for (int i=0 ; i<L.size() ; i++) {
				if ((L.get(i)).getStep_arrivee()<L.get(m).getStep_arrivee() && L.get(i).getQuantite()>0) {
					m=i;
				}			
			}
			if ((L.get(m)).getQuantite()>q) {
				(L.get(m)).removequantite(q);
				q=0;//q=000000
			}else {
				double r = (L.get(m)).getQuantite();
				(L.get(m)).removequantite(r);
				q=q-r;				
			}
		
		}	
		System.out.print("while fin");
	}
	
	public void addQuantite(double q, Feve f) {
		this.Stocks.get(f).add(new Stock(q));
	}
	
	public double getStock(Feve feve) {
		double s = 0.0 ;
		for(Stock stock : this.getStocks().get(feve)) {
			s = s + stock.getQuantite() ;
			}
			return s ;	
		}
	
	
	public void initialiser() {
		super.initialiser();
		for (Feve feve : Feve.values()) {
			int qt = super.getNbArbre(feve);
			this.addQuantite(qt, feve);
		}
		
		
	}

	public void next() {
		super.next();
		for(Feve feve : Feve.values()) {
			this.addQuantite(this.production(feve), feve);
		}
	}


	public HashMap<Feve, LinkedList<Stock>> getStocks() {
		return this.Stocks;
	}
	
	
}
