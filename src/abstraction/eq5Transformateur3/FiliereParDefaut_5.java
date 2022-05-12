package abstraction.eq5Transformateur3;

import java.awt.Color;
import java.util.HashMap;

import abstraction.eq1Producteur1.Producteur1;
import abstraction.eq2Producteur2.Producteur2;
import abstraction.eq3Transformateur1.Transformateur1;
import abstraction.eq4Transformateur2.Transformateur2;
import abstraction.eq5Transformateur3.Transformateur3;
import abstraction.eq6Distributeur1.Distributeur1;
import abstraction.eq7Distributeur2.Distributeur2;
import abstraction.eq8Romu.Romu;
import abstraction.eq8Romu.appelsOffres.SuperviseurVentesAO;
import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.Chocolat;


public class FiliereParDefaut_5 extends Filiere {
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
	private ClientFinal cf ;
	private SuperviseurVentesContratCadre superviseurCC;
	private BourseCacao bourse;
	private SuperviseurVentesAO superviseurAO;


	public FiliereParDefaut_5() {
		super();
		HashMap<Chocolat, Double> repartitionInitiale = new HashMap<Chocolat, Double>();
		// Tirage au sort de la repartition
		int hasard = (int)(Math.random()*3); // tirage au hasard d'un nombre dans {0, 1, 2}
		this.journalFiliere.ajouter("Repartition initiale des  achats des clients finaux : "+hasard);

		switch (hasard) {
		case 0 :
			repartitionInitiale.put(Chocolat.HQ_BE_O, 5.0); // Haute Qualite  ,  Bio-Equitable  , Original
			repartitionInitiale.put(Chocolat.HQ_BE,   5.0); // Haute Qualite  ,  Bio-Equitable  , pas Original 
			repartitionInitiale.put(Chocolat.HQ_O,   15.0); // Haute Qualite  ,pas Bio-Equitable, Original
			repartitionInitiale.put(Chocolat.HQ,     20.0); // Haute Qualite  ,pas Bio-Equitable, pas Original 
			repartitionInitiale.put(Chocolat.MQ_BE_O, 5.0); // Moyenne Qualite,  Bio-Equitable  , Original
			repartitionInitiale.put(Chocolat.MQ_BE,   5.0); // Moyenne Qualite,  Bio-Equitable  , pas Original 
			repartitionInitiale.put(Chocolat.MQ_O,    5.0); // Moyenne Qualite,pas Bio-Equitable, Original
			repartitionInitiale.put(Chocolat.MQ,     10.0); // Moyenne Qualite,pas Bio-Equitable, pas Original 
			repartitionInitiale.put(Chocolat.BQ_O,   10.0); // Basse Qualite  ,pas Bio-Equitable, Original
			repartitionInitiale.put(Chocolat.BQ,     20.0); // Basse Qualite  ,pas Bio-Equitable, pas Original 
			break;
		case 1 : 
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
			break;
		default : 
			repartitionInitiale.put(Chocolat.HQ_BE_O, 1.5); // Haute Qualite  ,  Bio-Equitable  , Original
			repartitionInitiale.put(Chocolat.HQ_BE,   1.5); // Haute Qualite  ,  Bio-Equitable  , pas Original 
			repartitionInitiale.put(Chocolat.HQ_O,    5.0); // Haute Qualite  ,pas Bio-Equitable, Original
			repartitionInitiale.put(Chocolat.HQ,     8.0); // Haute Qualite  ,pas Bio-Equitable, pas Original 
			repartitionInitiale.put(Chocolat.MQ_BE_O, 2.0); // Moyenne Qualite,  Bio-Equitable  , Original
			repartitionInitiale.put(Chocolat.MQ_BE,   2.0); // Moyenne Qualite,  Bio-Equitable  , pas Original 
			repartitionInitiale.put(Chocolat.MQ_O,   10.0); // Moyenne Qualite,pas Bio-Equitable, Original
			repartitionInitiale.put(Chocolat.MQ,     15.0); // Moyenne Qualite,pas Bio-Equitable, pas Original 
			repartitionInitiale.put(Chocolat.BQ_O,   20.0); // Basse Qualite  ,pas Bio-Equitable, Original
			repartitionInitiale.put(Chocolat.BQ,     35.0); // Basse Qualite  ,pas Bio-Equitable, pas Original 
		}
		this.cf = new ClientFinal(7200000000.0 , repartitionInitiale, DISTRIBUTIONS_ANNUELLES);
		this.ajouterActeur(cf);
		this.ajouterActeur(new Producteur1());
		this.ajouterActeur(new Producteur2());
		//this.ajouterActeur(new Transformateur1());
		this.ajouterActeur(new Transformateur2());
		this.ajouterActeur(new Transformateur3());
		this.ajouterActeur(new Distributeur1());
		this.ajouterActeur(new Distributeur2());
		this.ajouterActeur(new Romu());
		this.superviseurCC=new SuperviseurVentesContratCadre();
		this.ajouterActeur(this.superviseurCC);
		this.bourse=new BourseCacao();
		this.ajouterActeur(this.bourse);
		this.superviseurAO=new SuperviseurVentesAO();
		this.ajouterActeur(this.superviseurAO);

	}
	/**
	 * Redefinition afin d'interdire l'acces direct a certains superviseurs/acteurs.
	 * Sans cela, il serait possible de contourner l'autentification
	 */
	public IActeur getActeur(String nom) {
		if (!nom.startsWith("C.F.")) {
			return super.getActeur(nom); 
		} else {
			return null;
		}
	}

	public void initialiser() {
		super.initialiser();
	}
}
