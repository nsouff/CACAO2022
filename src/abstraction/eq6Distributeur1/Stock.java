package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.produits.Chocolat;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

public class Stock extends Distributeur1{
	
	private Map<Chocolat,Double> prixInstantanneChoco = new HashMap<Chocolat, Double>();
	private double prixStockageActuel;
	private Map<Chocolat, Double> teteGondole = new HashMap<Chocolat, Double>(); // (nom du chocolat,% en tête de gondole) 
	private Map<Chocolat,Double> qteInstantanneChoco = new HashMap<Chocolat, Double>();
	private double qteStockageActuel;
	private ArrayList<Double> CouplePrixQte = new ArrayList();//[prixInstantanneChoco.size()][prixInstantanneChoco.size()];
	private Map<Chocolat,  CouplePrixQte > Stockage = new HashMap<Chocolat,  CouplePrixQte >(); //(nom choco, table 2 entrées prix et qté)
	//private Map<Chocolat, Integer> Achat = new HashMap<Chocolat, Integer>();
	
	public void main() {
		double sumprix=0;
		double sumqte=0;
	
		for (Map.Entry<Chocolat, Double > entry : prixInstantanneChoco.entrySet()) {
            Chocolat KeyPrix = entry.getKey();
            Double ValuePrix = entry.getValue();
            sumprix += ValuePrix;
            CouplePrixQte.add(ValuePrix);
           }
	
		this.prixStockageActuel = sumprix;
		
		for (Map.Entry<Chocolat, Double > entry : prixInstantanneChoco.entrySet()) {
            Chocolat KeyPrix = entry.getKey();
            Double ValuePrix = entry.getValue();
            sumqte += ValuePrix;
		}
		
		this.qteStockageActuel = sumqte;


	}
		
}	
	
//Emma Humeau 
	
	
