package abstraction.eq6Distributeur1;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

public class Stock extends Distributeur1{ //Emma Humeau
	
	private Map<ChocolatDeMarque,Double> StockagePrix = new HashMap<ChocolatDeMarque, Double>();
	private double prixStockageTotal;

	private Map<ChocolatDeMarque,Double> StockageQte = new HashMap<ChocolatDeMarque, Double>();
	private double qteStockageTotal; 
	
	//abandon map3
	//private ArrayList<Double> CouplePrixQte = new ArrayList();//[prixInstantanneChoco.size()][prixInstantanneChoco.size()];
	//private Map<ChocolatDeMarque,  ArrayList<Double> > Stockage = new HashMap<ChocolatDeMarque, ArrayList<Double> >(); //(choco, table 2 entrées prix et qté)
	
	private Map<ChocolatDeMarque, Double> teteGondole = new HashMap<ChocolatDeMarque, Double>(); // (nom du chocolat,% en tête de gondole) 

	

	public void Distributeur() { //Emma Humeau

		double sumprix=0;
		double sumqte=0;
	
		//calcule le prix du stockage total 
		for (Map.Entry<ChocolatDeMarque, Double > entry : StockagePrix.entrySet()) {
            ChocolatDeMarque KeyPrix = entry.getKey();
            Double ValuePrix = entry.getValue();
            sumprix += ValuePrix;
           }
	
		this.prixStockageTotale = sumprix;
		
		//calcule la qte du stockage total
		for (Map.Entry<ChocolatDeMarque, Double > entry : StockageQte.entrySet()) {
            ChocolatDeMarque KeyQte = entry.getKey();
            Double ValueQte = entry.getValue();
            sumqte += ValueQte;
		}

		
		this.qteStockageTotale = sumqte;


	}

	
	


	/**
	 * @return the stockagePrix
	 */
	public Map<ChocolatDeMarque, Double> getStockagePrix() {
		return StockagePrix;
	}



	/**
	 * @param stockagePrix the stockagePrix to set
	 */
	public void setStockagePrix(ChocolatDeMarque choco, double prix) {
		StockagePrix.put(choco,prix);
	}



	/**
	 * @return the stockageQte
	 */
	public Map<ChocolatDeMarque, Double> getStockageQte() {
		return StockageQte;
	}

	public Double getStockage(ChocolatDeMarque chocolat) {//leorouppert
    return this.StockageQte.get(chocolat);//Retourne le stock du chocolat demandé
	}

	/**
	 * @param stockageQte the stockageQte to set
	 */
	public void setStockageQte(ChocolatDeMarque choco, double qte) {
		StockageQte.put(choco, qte);
	}



	/**
	 * @return the prixStockageTotale
	 */
	public double getPrixStockageTotale() {
		return prixStockageTotale;
	}



	/**
	 * @return the qteStockageTotale
	 */
	public double getQteStockageTotale() {
		return qteStockageTotale;
	}


//Emma Humeau tout ce qui est au dessus
<<<<<<< HEAD


	public Map<ChocolatDeMarque, Double> getMapStock() {//leorouppert
        return this.Stockage;//Retourne toute la hashmap de notre stock
    }
    
    public Double getStockage(ChocolatDeMarque chocolat) {//leorouppert
        return this.Stockage.get(chocolat);//Retourne le stock du chocolat demandé
    }
    
    public void setStockage(ChocolatDeMarque chocolat, Double quantite, Double prix) {//leorouppert
        this.Stockage.put(chocolat, ArrayList<Double>[prix][quantite]);//Modifie la valeur du stock du chocolat associé
    }
		
=======
>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022
}	
	

	
	



