package abstraction.eq2Producteur2;


import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.general.VariableReadOnly;
import abstraction.eq8Romu.produits.Feve;

public class Producteur2Acteur extends Producteur2Stockage implements IActeur {
	
	private static final LinkedList<Stock> FEVE_BASSE = new LinkedList<Stock>();
	private static final LinkedList<Stock> FEVE_MOYENNE = new LinkedList<Stock>();
	private static final LinkedList<Stock> FEVE_MOYENNE_BE = new LinkedList<Stock>();
	private static final LinkedList<Stock> FEVE_HAUTE = new LinkedList<Stock>();
	private static final LinkedList<Stock> FEVE_HAUTE_BE = new LinkedList<Stock>();
	protected int cryptogramme;
	protected Journal journal;
	private Variable StockFeveBasse;
	private Variable StockFeveMoyenne;
	private Variable StockFeveMoyenne_BE;
	private Variable StockFeveHaute;
	private Variable StockFeveHaute_BE; 


	private Variable prixstockage ;
	private Variable dureeaffinageBQ ;
	private Variable dureeaffinageMQ ;
	private Variable dureeaffinageHQ ;
	
	
	public Producteur2Acteur() {
		this.prixstockage= new Variable("Prix Stockage", "Prix en euros par kilo par step", this,  0.0, 1000000000, 0.01) ;
		this.dureeaffinageBQ= new VariableReadOnly("Durée affinage BQ","", this,  0.0, 1000000000, 1) ;
		this.dureeaffinageMQ= new VariableReadOnly("Durée affinage MQ","", this,  0.0, 1000000000, 2) ;
		this.dureeaffinageHQ= new VariableReadOnly("Durée affinage HQ","", this,  0.0, 1000000000, 3) ;
		this.journal = new Journal(this.getNom()+" activites", this);
		this.StockFeveBasse= new Variable("StockFeveBasse", "Stock de Fèves Basse", this, 0.0, 1000000000, this.SommeQuantite(FEVE_BASSE));
		this.StockFeveBasse= new Variable("StockFeveMoyenne", "Stock de Fèves Moyenne", this, 0.0, 1000000000, this.SommeQuantite(FEVE_MOYENNE));
		this.StockFeveBasse= new Variable("StockFeveMoyenne_BE", "Stock de Fèves Moyenne BE", this, 0.0, 1000000000, this.SommeQuantite(FEVE_MOYENNE_BE));
		this.StockFeveBasse= new Variable("StockFeveHaute", "Stock de Fèves Haute", this, 0.0, 1000000000, this.SommeQuantite(FEVE_HAUTE));
		this.StockFeveBasse= new Variable("StockFeveHaute_BE", "Stock de Fèves Haute BE", this, 0.0, 1000000000, this.SommeQuantite(FEVE_HAUTE_BE));
	
	}

	public void initialiser() {
	}
	
	public String getNom() {
		return "Cacao Doré";
	}

	public String getDescription() {
		return "Cacao Doré";
	}

	public Color getColor() {
		return new Color(46, 204, 113);
	}
	

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}
	

	public void next() {
		// il faut appeler nextPlantation() de la classe plantation
		
	}
	
	public List<String> getNomsFilieresProposees() {
		return new ArrayList<String>();
	}

	public Filiere getFiliere(String nom) {
		return null;
	}
	
	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
		res.add(StockFeveBasse);
		res.add(StockFeveMoyenne);
		res.add(StockFeveMoyenne_BE);
		res.add(StockFeveHaute);
		res.add(StockFeveHaute_BE);
		return res;
	}
	
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		res.add(this.prixstockage);
		return res; 
	}

	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		return res;
	}

	public void notificationFaillite(IActeur acteur) {
		if (this==acteur) {
		System.out.println("I'll be back... or not... "+this.getNom());
		} else {
			System.out.println("Poor "+acteur.getNom()+"... We will miss you. "+this.getNom());
		}
	}
	
	public void notificationOperationBancaire(double montant) {
	}
	
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(this, this.cryptogramme);
	}
	
}
