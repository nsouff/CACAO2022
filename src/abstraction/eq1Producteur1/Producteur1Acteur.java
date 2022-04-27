package abstraction.eq1Producteur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Feve;

public class Producteur1Acteur implements IActeur {
	protected int cryptogramme;
	protected Journal journal;
	protected Variable stockFeve;
	public Variable prixstockageVariable ;
	public Variable prixstockageFixe ;
	
	
	

	public Producteur1Acteur() {
		this.journal = new Journal(this.getNom()+" activites", this);
		this.prixstockageVariable=new Variable("prixStockageVariable", this, 0.0, 1000000000.0,0.01);
		this.prixstockageFixe=new Variable("prixStockageFixe", this, 0.0, 1000000000.0,100);
		this.stockFeve=new Variable(this.getNom()+"Stock", this, 0.0, 1000000000.0,1000000);
	}

	public void initialiser() {
	}
	
	public String getNom() {
		return "CAC'AO40";
	}

	public String getDescription() {
		return "On est pas ici pour enfiler des perles. On va mettre les points sur les I et les barres sur leur mère";
	}

	public Color getColor() {
		return new Color(26, 188, 156);
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
			System.out.println("Poor "+acteur.getNom()+"... Why so serious ? "+this.getNom());
		}
	}
	
	public void notificationOperationBancaire(double montant) {
	}
	
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(this, this.cryptogramme);
	}
}