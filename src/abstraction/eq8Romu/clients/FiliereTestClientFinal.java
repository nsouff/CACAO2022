package abstraction.eq8Romu.clients;

import java.util.HashMap;

import abstraction.eq8Romu.Romu;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
//import abstraction.eq8Romu.produits.Categorie;
//import abstraction.eq8Romu.chocolatBourse.ExempleVendeurChocolatBourse;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class FiliereTestClientFinal extends Filiere {
	private static final double DISTRIBUTIONS_ANNUELLES[][] = {
			//Jan1 Jan2 Fev1 Fev2 Mar1 Mar2 Avr1 Avr2 Mai1 Mai2 Jui1 Jui2 Jul1 Jul2 Aou1 Aou2 Sep1 Sep2 Oct1 Oct2 Nov1 Nov2 Dec1 Dec2
			{ 4.5, 4.5, 4.5, 4.5, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.5, 4.5, 4.5, 4.5, },			
			{ 5.5, 5.5, 5.0, 5.0, 4.5, 4.0, 4.0, 4.0, 4.0, 3.5, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.5, 4.0, 4.0, 4.5, 5.0, 5.0, 5.5, 5.5, },			
			{ 3.5, 3.5, 6.0, 3.5, 3.5, 3.5, 3.5, 3.5, 9.0, 3.5, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 9.0, 9.0, },			
			{ 3.0, 3.0, 6.0, 3.0, 3.0, 3.0, 3.0, 3.0, 9.0, 3.0, 3.0, 2.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0,15.0,15.0, },			
			{ 3.0, 3.0, 7.0, 3.0, 3.0, 3.0, 3.0, 3.0,10.0, 3.0, 3.0, 2.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0,10.0, 3.0, 3.0,11.0,10.0, },			
			{ 3.0, 3.0,10.0, 3.0, 3.0, 3.0, 3.0, 3.0,12.0, 3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0, 3.0, 3.0, 3.0, 3.0,15.0,15.0, },			
			{ 3.0, 3.0,11.0, 3.0, 3.0, 3.0, 3.0, 3.0,13.0, 3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0, 3.0,10.0, 3.0, 3.0,11.0,10.0, },			
	};

	public FiliereTestClientFinal() {
		super();

	HashMap<Chocolat, Double> repartitionInitiale = new HashMap<Chocolat, Double>();
	repartitionInitiale.put(Chocolat.HQ_BE_O, 2.5); // Haute Qualite  ,  Bio-Equitable  , Original
	repartitionInitiale.put(Chocolat.HQ_BE,   2.5); // Haute Qualite  ,  Bio-Equitable  , pas Original 
	repartitionInitiale.put(Chocolat.HQ_O,   10.0); // Haute Qualite  ,pas Bio-Equitable, Original
	repartitionInitiale.put(Chocolat.HQ,     15.0); // Haute Qualite  ,pas Bio-Equitable, pas Original 
	repartitionInitiale.put(Chocolat.MQ_BE_O, 2.5); // Moyenne Qualite,  Bio-Equitable  , Original
	repartitionInitiale.put(Chocolat.MQ_BE,   2.5); // Moyenne Qualite,  Bio-Equitable  , pas Original 
	repartitionInitiale.put(Chocolat.MQ_O,   10.0); // Moyenne Qualite,pas Bio-Equitable, Original
	repartitionInitiale.put(Chocolat.MQ,     15.0); // Moyenne Qualite,pas Bio-Equitable, pas Original 
	repartitionInitiale.put(Chocolat.BQ_O,   15.0); // Basse Qualite  ,pas Bio-Equitable, Original
	repartitionInitiale.put(Chocolat.BQ,     25.0); // Basse Qualite  ,pas Bio-Equitable, pas Original 

	ClientFinal  cf = new ClientFinal(7200000000.0 , repartitionInitiale, DISTRIBUTIONS_ANNUELLES);

		this.ajouterActeur(cf);
		ChocolatDeMarque[] chocos= {
				new ChocolatDeMarque(Chocolat.MQ, "lindt"),
				new ChocolatDeMarque(Chocolat.BQ, "ivoria"),
				new ChocolatDeMarque(Chocolat.MQ_O, "lindt"),
				new ChocolatDeMarque(Chocolat.BQ_O, "ivoria"),
		};
		this.ajouterActeur(new ExempleTransformateurImproductif(chocos[0]));
		this.ajouterActeur(new ExempleTransformateurImproductif(chocos[1]));
		this.ajouterActeur(new ExempleTransformateurImproductif(chocos[2]));
		this.ajouterActeur(new ExempleTransformateurImproductif(chocos[3]));
		this.ajouterActeur(new Romu());
		ChocolatDeMarque[] chocos1= {
				chocos[0],chocos[1],chocos[2],
		};
		double[] stocks1= {1000000, 1000000, 1000000};
		double[] prix1= {1.6, 1.3, 2.4};
		String[] marques1 = {"lindt"};
		this.ajouterActeur(new ExempleDistributeurChocolatMarque(chocos1, stocks1, 100000, prix1, marques1));

		ChocolatDeMarque[] chocos2= {
				chocos[1],chocos[2],chocos[3],
		};
		double[] stocks2= {1000000, 1000000, 1000000};
		double[] prix2= {1.5, 0.85, 2.3};
		String[] marques2 = {"ivoria"};
		this.ajouterActeur(new ExempleDistributeurChocolatMarque(chocos2, stocks2, 50000, prix2, marques2));
	}
	
	
	public void initialiser() {
		super.initialiser();
		// il est possible de modifier l'attractivite initiale d'un chocolat (impossible d'appeler cette methode plus tard)
		//cf.initAttractiviteChoco(new ChocolatDeMarque(Chocolat.CHOCOLAT_BASSE,"D.Choco1CHOCOLAT_BASSE"), 4.5);
	}

	
	/**
	 * Redefinition afin d'interdire l'acces direct au client final
	 */
	public IActeur getActeur(String nom) {
		if (!nom.startsWith("C.F.")) {
			return super.getActeur(nom); 
		} else {
			return null;
		}
	}
}
