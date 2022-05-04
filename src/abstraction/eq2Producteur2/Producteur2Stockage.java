package abstraction.eq2Producteur2;

import java.util.LinkedList;
import java.util.HashMap;
import abstraction.eq2Producteur2.Stock;


// auteur Clément //

public class Producteur2Stockage extends Producteur2Acteur {
	public Producteur2Stockage(LinkedList<Stock> stockBQ, LinkedList<Stock> stockMQ, LinkedList<Stock> stockMQ_BE,
			LinkedList<Stock> stockHQ, LinkedList<Stock> stockHQ_BE, LinkedList<Stock> chocoBQ,
			LinkedList<Stock> chocoMQ, LinkedList<Stock> chocoMQ_BE, LinkedList<Stock> chocoHQ,
			LinkedList<Stock> chocoHQ_BE, HashMap<LinkedList<Stock>, Stock> stocks) {
		super();
		StockBQ = stockBQ;
		StockMQ = stockMQ;
		StockMQ_BE = stockMQ_BE;
		StockHQ = stockHQ;
		StockHQ_BE = stockHQ_BE;
		ChocoBQ = chocoBQ;
		ChocoMQ = chocoMQ;
		ChocoMQ_BE = chocoMQ_BE;
		ChocoHQ = chocoHQ;
		ChocoHQ_BE = chocoHQ_BE;
		Stocks = stocks;
	}
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
	private HashMap<LinkedList<Stock>, Stock> Stocks;
	
	
	

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
		Stocks = new HashMap<LinkedList<Stock>,Stock>();
//		Stocks.put(StockBQ, Stock(0.0,0));
	}





}
