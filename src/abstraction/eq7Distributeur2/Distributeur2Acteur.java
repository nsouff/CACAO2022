package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.ChocolatDeMarque;


public class Distributeur2Acteur implements IActeur{

	protected int cryptogramme;
	
	//Stock
	protected IStock stock;
	
	//Liste des chocolats sur le marché
	protected List<ChocolatDeMarque> chocolats;
	
	//Journaux
	protected Journal journal;
	
	//Indicateurs
	private List<Variable> listeIndicateur;
	private Variable stockTotal;

	public Distributeur2Acteur() {
		this.journal = new Journal(this.getNom()+" activites", this);
		this.initialiserIndicateurs();
	}

	public String getNom() {
		return "Biofour";
	}

	public String getDescription() {
		return "Du bon bio la mmh...";
	}

	public Color getColor() {
		return new Color(1,81,8); 
	}


	public void initialiser() {
		//Initialiser stock
		this.chocolats = Filiere.LA_FILIERE.getChocolatsProduits();
		System.out.println("Liste des chocolats en vente sur le marché : "+chocolats);
		this.stock = new Stock(this,this.chocolats);
		
	}
	
	public void next() {
		this.actualiserIndicateurs();
	}

	
	// Renvoie la liste des filières proposées par l'acteur
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		filieres.add("TESTCCBiofour");
		return(filieres);
	}

	// Renvoie une instance d'une filière d'après son nom
	public Filiere getFiliere(String nom) {
		switch (nom) { 
		case "TESTCCBiofour" : return new FiliereTestCCBiofour();
	    default : return null;
		}
	}

	// Renvoie les indicateurs
	public List<Variable> getIndicateurs() {
		List<Variable> res = new ArrayList<Variable>();
		res.add(stockTotal);
		return res;
	}
	
	//-----------------------------INDICATEURS-------------------------------------------

	public void initialiserIndicateurs() {
		this.listeIndicateur = new LinkedList<Variable>();
		this.stockTotal = new Variable("Stock total",this,0);
		this.listeIndicateur.add(stockTotal);
	}
	
	public void actualiserIndicateurs() {
		this.stockTotal.setValeur(this,this.stock.getQuantiteTotale());
	
	}
		

	// Renvoie les paramètres
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		return res;
	}

	// Renvoie les journaux
	public List<Journal> getJournaux() {
		List<Journal> j= new ArrayList<Journal>();
		j.add(this.journal);
		return j;
	}


	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
		
	}

	public void notificationFaillite(IActeur acteur) {
		System.out.println("F#*! you "+acteur.getNom()+". You won't miss Biofour team");
	}

	public void notificationOperationBancaire(double montant) {
	}
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(Filiere.LA_FILIERE.getActeur(getNom()), this.cryptogramme);
	}

}