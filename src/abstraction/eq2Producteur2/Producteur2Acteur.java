package abstraction.eq2Producteur2;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.general.VariableReadOnly;

public class Producteur2Acteur implements IActeur {
	protected int cryptogramme;


	public double prixstockageVariable = 0.01 ;
	public double prixstockageFixe = 100;
	private Variable prixstockage ;
	private Variable dureeaffinageBQ ;
	private Variable dureeaffinageMQ ;
	private Variable dureeaffinageHQ ;
	
	
	public Producteur2Acteur() {
		this.prixstockage= new VariableReadOnly("Prix Stockage", "Prix en euros par kilo par step", this,  0.0, 1000000000, 0.01) ;
		this.dureeaffinageBQ= new VariableReadOnly("Durée affinage BQ","", this,  0.0, 1000000000, 1) ;
		this.dureeaffinageMQ= new VariableReadOnly("Durée affinage MQ","", this,  0.0, 1000000000, 2) ;
		this.dureeaffinageHQ= new VariableReadOnly("Durée affinage HQ","", this,  0.0, 1000000000, 3) ;
		
	}

	public void initialiser() {
	}
	
	public String getNom() {
		return "EQ2";
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
	}
	
	public List<String> getNomsFilieresProposees() {
		return new ArrayList<String>();
	}

	public Filiere getFiliere(String nom) {
		return null;
	}
	
	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
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
