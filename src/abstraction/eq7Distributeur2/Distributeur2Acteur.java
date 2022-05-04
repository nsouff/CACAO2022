package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

import abstraction.eq8Romu.bourseCacao.FiliereTestBourse;
import abstraction.eq8Romu.clients.FiliereTestClientFinal;
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.FiliereTestContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Distributeur2Acteur implements IActeur{

	public static final int EPS_ECH_OK=2;
	public static final int ECH_MAX=5;
	public static final Double PRIX_MAX=100.0;
	public static final Double PRIX_OK=50.0;
	public static final Double EPSILON_PRIX=5.0;
	protected int cryptogramme;
	protected IStock stock;
	protected List<ChocolatDeMarque> chocolats;
	protected Journal journal;

	public Distributeur2Acteur() {
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
		this.stock = new Stock(this);
		this.journal = new Journal(this.getNom()+" activites", this);
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
		List<Journal> res=new ArrayList<Journal>();
		return res;
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
