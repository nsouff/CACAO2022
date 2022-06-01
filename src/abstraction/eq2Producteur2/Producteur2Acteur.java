package abstraction.eq2Producteur2;


import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq1Producteur1.FiliereTestBourseEq1;
import abstraction.eq8Romu.bourseCacao.IVendeurBourse;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.general.VariableReadOnly;
import abstraction.eq8Romu.produits.Feve;

public class Producteur2Acteur extends Producteur2Stockage2 implements IActeur,IVendeurBourse {
	
	protected int cryptogramme;
	protected Journal journal;
	private Variable StockFeveBasse;
	private Variable StockFeveMoyenne;
	private Variable StockFeveMoyenne_BE;
	private Variable StockFeveHaute;
	private Variable StockFeveHaute_BE; 

	private Variable prixstockage ;
//	private Variable dureeaffinageBQ ;
//	private Variable dureeaffinageMQ ;
//	private Variable dureeaffinageHQ ;
	
	// Auteur : Clément
	
	public Producteur2Acteur() {
		super();
		this.prixstockage= new Variable("Prix Stockage", "Prix en euros par kilo par step", this,  0.0, 1000000000, 0.01) ;
//		this.dureeaffinageBQ= new VariableReadOnly("Durée affinage BQ","", this,  0.0, 1000000000, 1) ;
//		this.dureeaffinageMQ= new VariableReadOnly("Durée affinage MQ","", this,  0.0, 1000000000, 2) ;
//		this.dureeaffinageHQ= new VariableReadOnly("Durée affinage HQ","", this,  0.0, 1000000000, 3) ;
		this.journal = new Journal(this.getNom()+" activites", this);
		this.StockFeveBasse= new Variable("StockFeveBasse", "Stock de Fèves Basse", this, 0.0, 1000000000, this.getStock(Feve.FEVE_BASSE));
		this.StockFeveMoyenne= new Variable("StockFeveMoyenne", "Stock de Fèves Moyenne", this, 0.0, 1000000000, this.getStock(Feve.FEVE_MOYENNE));
		this.StockFeveMoyenne_BE= new Variable("StockFeveMoyenne_BE", "Stock de Fèves Moyenne BE", this, 0.0, 1000000000, this.getStock(Feve.FEVE_MOYENNE_BIO_EQUITABLE));
		this.StockFeveHaute= new Variable("StockFeveHaute", "Stock de Fèves Haute", this, 0.0, 1000000000, this.getStock(Feve.FEVE_HAUTE));
		this.StockFeveHaute_BE= new Variable("StockFeveHaute_BE", "Stock de Fèves Haute BE", this, 0.0, 1000000000, this.getStock(Feve.FEVE_HAUTE_BIO_EQUITABLE));
		
	}

	public void initialiser() {
		super.initialiser();
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
		super.next();
		System.out.println("debut");
		// Cout de production, Jules DORE
		this.setCoutParKg();
		double coutProduction = 0.0;
		for(Feve f : this.coutParKg.keySet()) {
			coutProduction = coutProduction + this.coutParKg.get(f)*this.production(f);
		}
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), coutProduction);
		// Cout de stockage, Jules DORE
		
		double coutStockage = 0.0 ;
		for (Feve f : this.getStocks().keySet()) {
			coutStockage = coutStockage + (this.getStock(f)*Filiere.LA_FILIERE.getParametre("Prix Stockage").getValeur());
		}
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), coutStockage);
		
		//journal, Jules DORE

		journal.ajouter("Stock Feve Moyenne : "+this.getStock(Feve.FEVE_MOYENNE)+", Production Feve Moyenne : "+this.production(Feve.FEVE_MOYENNE)+", Nombre d'arbre Moyenne : "+this.getNbArbre(Feve.FEVE_MOYENNE)+"");
		journal.ajouter("Stock Feve Haute : "+this.getStock(Feve.FEVE_HAUTE)+", Production Feve Haute : "+this.production(Feve.FEVE_HAUTE)+", Nombre d'arbre Haute : "+this.getNbArbre(Feve.FEVE_HAUTE)+"");
		journal.ajouter("Stock Feve Basse : "+this.getStock(Feve.FEVE_BASSE)+", Production Feve Basse : "+this.production(Feve.FEVE_BASSE)+", Nombre d'arbre Basse : "+this.getNbArbre(Feve.FEVE_BASSE)+"");
		journal.ajouter("Stock Feve Haute BE : "+this.getStock(Feve.FEVE_HAUTE_BIO_EQUITABLE)+", Production Feve Haute BE : "+this.production(Feve.FEVE_HAUTE_BIO_EQUITABLE)+", Nombre d'arbre Haute BE : "+this.getNbArbre(Feve.FEVE_HAUTE_BIO_EQUITABLE)+"");
		journal.ajouter("Stock Feve Moyenne BE : "+this.getStock(Feve.FEVE_MOYENNE_BIO_EQUITABLE)+", Production Feve Moyenne BE : "+this.production(Feve.FEVE_MOYENNE_BIO_EQUITABLE)+", Nombre d'arbre Moyenne BE : "+this.getNbArbre(Feve.FEVE_MOYENNE_BIO_EQUITABLE)+"");

		this.GetStockHaute().setValeur(this, this.getStock(Feve.FEVE_HAUTE));
		this.GetStockMoyenne().setValeur(this, this.getStock(Feve.FEVE_MOYENNE));
		this.GetStockMoyenne_BE().setValeur(this, this.getStock(Feve.FEVE_MOYENNE_BIO_EQUITABLE));
		this.GetStockBasse().setValeur(this, this.getStock(Feve.FEVE_HAUTE));
		this.GetStockHausse_BE().setValeur(this, this.getStock(Feve.FEVE_HAUTE_BIO_EQUITABLE));	
		System.out.println("fin");
	}
	
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		filieres.add("Producteur2TestBourse"); 
		return filieres;
	}

	public Filiere getFiliere(String nom) {
		switch (nom) { 
		case "Producteur2TestBourse" : return new Producteur2TestBourse();
	    default : return null;
		}
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
		res.add(journal);
		return res;
	}

	public void notificationFaillite(IActeur acteur) {
		if (this==acteur) {
		System.out.println("I'll be back... or not... "+this.getNom());
		} else {
			System.out.println("Poor "+acteur.getNom()+"... We will not miss you. "+this.getNom());
		}
	}
	
	public void notificationOperationBancaire(double montant) {
	}
	
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(this, this.cryptogramme);
	}
	public Variable GetStockBasse() {
		return StockFeveBasse;
	}
	public Variable GetStockMoyenne() {
		return StockFeveMoyenne;
	}
	public Variable GetStockMoyenne_BE() {
		return StockFeveMoyenne_BE;
	}
	public Variable GetStockHaute() {
		return StockFeveHaute;
	}
	
	public Variable GetStockHausse_BE() {
		return StockFeveHaute_BE;
	}

	public double offre(Feve f, double cours) {
		double quantiteAVendre = 0;
		if (cours> 1.3*this.getCout(f)) {
			 quantiteAVendre= 0.8*this.getStock(f); // on vend 80% du stock;
		}
		if (cours> 1.2*this.getCout(f)) {
			 quantiteAVendre  = 0.6*this.getStock(f); // on vend 60% du stock;
		}
		if (cours> 1.1*this.getCout(f)) {
			  quantiteAVendre = 0.4*this.getStock(f); // on vend 40% du stock ;
		}
		return  quantiteAVendre;
	}

	public void notificationVente(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		// TODO Auto-generated method stub
		this.removeQuantite(quantiteEnKg, f);

	}


}
