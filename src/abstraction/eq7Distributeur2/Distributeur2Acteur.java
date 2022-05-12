package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.filiere.IMarqueChocolat;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

<<<<<<< main

public class Distributeur2Acteur implements IActeur, IMarqueChocolat{

=======
public class Distributeur2Acteur implements IActeur{

	public static final int EPS_ECH_OK=2;
	public static final int ECH_MAX=5;
	public static final Double PRIX_MAX=100.0;
	public static final Double PRIX_OK=50.0;
	public static final Double EPSILON_PRIX=5.0;
>>>>>>> 96f93d2 indicator
	protected int cryptogramme;
	protected IStock stock;
	protected List<ChocolatDeMarque> chocolats;
	protected Journal journal;

	public Distributeur2Acteur() {
		this.journal = new Journal(this.getNom()+" activites", this);
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
		this.chocolats = Filiere.LA_FILIERE.getChocolatsProduits();
		System.out.println(chocolats);
		this.stock = new Stock(this,this.chocolats);
		
	}
	
	public void next() {
		
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
		return res;
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

	@Override
	public List<String> getMarquesChocolat() {
		// TODO Auto-generated method stub
		List<String> marque = new ArrayList<String>();
		marque.add("Biofour");
		return marque;
	}

}
