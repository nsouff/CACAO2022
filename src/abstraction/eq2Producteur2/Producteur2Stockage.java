package abstraction.eq2Producteur2;

import java.util.LinkedList;
import java.util.HashMap;
import abstraction.eq2Producteur2.Stock;


// auteur Clément //

public class Producteur2Stockage extends Producteur2Acteur {
	
	private LinkedList<Stock> StockBQ;
	private LinkedList<Stock> StockMQ;
	private LinkedList<Stock> StockMQ_BE;
	private LinkedList<Stock> StockHQ;
	private LinkedList<Stock> StockHQ_BE;
	private LinkedList<Stock> ChocoBQ;
	private LinkedList<Stock> ChocoMQ;
	private LinkedList<Stock> ChocoMQ_BE;
	private LinkedList<Stock> ChocoHQ;
	private LinkedList<Stock> ChocoHQ_BE;
	private HashMap<LinkedList<Stock>,Stock> Stocks;
	
	

	public LinkedList<Stock> getStockBQ() {
		return StockBQ;
	}

	public LinkedList<Stock> getStockMQ() {
		return StockMQ;
	}

	public LinkedList<Stock> getStockMQ_BE() {
		return StockMQ_BE;
	}

	public LinkedList<Stock> getStockHQ() {
		return StockHQ;
	}

	public LinkedList<Stock> getStockHQ_BE() {
		return StockHQ_BE;
	}

	public LinkedList<Stock> getChocoBQ() {
		return ChocoBQ;
	}

	public LinkedList<Stock> getChocoMQ() {
		return ChocoMQ;
	}

	public LinkedList<Stock> getChocoMQ_BE() {
		return ChocoMQ_BE;
	}

	public LinkedList<Stock> getChocoHQ() {
		return ChocoHQ;
	}

	public LinkedList<Stock> getChocoHQ_BE() {
		return ChocoHQ_BE;
	}
	public Producteur2Stockage () {
		super();
		this.StockBQ = new LinkedList<Stock>();
		this.StockBQ.add(new Stock(0,0));
		this.StockMQ = new LinkedList<Stock>();
		this.StockMQ.add(new Stock(0,0));
		this.StockMQ_BE = new LinkedList<Stock>();
		this.StockMQ_BE.add(new Stock(0,0));
		this.StockHQ = new LinkedList<Stock>();
		this.StockHQ.add(new Stock(0,0));
		this.StockHQ_BE = new LinkedList<Stock>();
		this.StockHQ_BE.add(new Stock(0,0));
		
	}
	public double SommeQuantite(LinkedList<Stock> L) {
		double s = 0 ;
		for (int i=0 ; i<L.size() ; i++) {
			s = s + (L.get(i)).getQuantite();
		}
		return s;
		
		
	}
}
