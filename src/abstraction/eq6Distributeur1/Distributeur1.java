package abstraction.eq6Distributeur1;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.produits.ChocolatDeMarque;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

=======
>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022
public class Distributeur1 extends Distributeur1Acteur {
<<<<<<< HEAD

	private Map<ChocolatDeMarque,Double> prixInstantanneChoco = new HashMap<ChocolatDeMarque, Double>();

	private double prixInstantane;
	private double prixStockageActuel;
	private Map<ChocolatDeMarque, Double> teteGondole = new HashMap<ChocolatDeMarque, Double>(); // (nom du ChocolatDeMarque,% en tête de gondole) 
	private int qteStockage;
	private Map<ChocolatDeMarque, ArrayList<Double>> Stockage = new HashMap<ChocolatDeMarque, ArrayList<Double>>(); //(nom choco, table 2 entrées prix et qté)

	private Map<ChocolatDeMarque, Double> Stockage = new HashMap<ChocolatDeMarque, Double>(); 
	private Map<ChocolatDeMarque, Integer> Achat = new HashMap<ChocolatDeMarque, Integer>();

	private Map<ChocolatDeMarque, ArrayList<Double>> prixVente = new HashMap<ChocolatDeMarque, ArrayList<Double>>();


=======
>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022
	public Distributeur1() {
		super();
	}

	



}
