package abstraction.eq4Transformateur2; 

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Transformateur2Acteur implements IActeur {
	
	private Integer cryptogramme;
	private Variable qualiteHaute;  // La qualite d'un chocolat de gamme haute 
	private Variable qualiteMoyenne;// La qualite d'un chocolat de gamme moyenne  
	private Variable qualiteBasse;  // La qualite d'un chocolat de gamme basse
	private Variable gainQualiteBioEquitable;// Le gain en qualite des chocolats bio equitables
	private Variable gainQualiteOriginal;// Le gain en qualite des chocolats originaux
	private Variable partDeLaMarqueDansLaQualitePercu;// Le gain en qualite des chocolats originaux
	private Variable coutStockage;
	private Variable prixSeuil;
	private Variable pourcentageTranfoLongue;
	private Variable prixTransformation; // a renseigner 
	private Variable prixChocoOriginal;
	private Variable capaciteStockage;
	private Variable capaciteStockageFixe;// stock que l'on souhaite en permanence
	private Variable expirationFeve;
	private Variable expirationChoco;

	
	
	
	public Romu() {
		this.qualiteHaute   = new Variable("qualite haute", "<html>Qualite du chocolat<br>de gamme haute</html>",this, 0.0, 10.0, 3.0);
		this.qualiteMoyenne = new Variable("qualite moyenne", "<html>Qualite du chocolat<br>de gamme moyenne</html>",this, 0.0, 10.0, 2.0);
		this.qualiteBasse   = new Variable("qualite basse", "<html>Qualite du chocolat<br>de gamme basse</html>",this, 0.0, 10.0, 1.0);
		this.gainQualiteBioEquitable  = new Variable("gain qualite bioequitable", "<html>Gain en qualite des<br>chocolats bio equitables</html>",this, 0.0, 5.0, 0.5);
		this.gainQualiteOriginal  = new Variable("gain qualite original", "<html>Gain en qualite des<br>chocolats originaux</html>",this, 0.0, 5.0, 0.5);
		this.partDeLaMarqueDansLaQualitePercu  = new Variable("impact marque qualite percue", "<html>% de la qualite percue de la marque dans la qualite percue du chocolat</html>",this, 0.0, 0.5, 0.3);
		this.coutStockage = new Variable("cout stockage", "<html>Qualite du chocolat<br>de gamme haute</html>",this, 0.0, 10.0, 3.0);
		this.prixSeuil = ;
		this.pourcentageTransfoLongue=;
		this.prixTransformation = ;
		this.prixChocoOriginal=;
		this.capaciteStockage=;
		this.capaciteStockageFixe=;
		this.expirationFeve=;
		this.expirationChoco=;
		
	}
	
	protected int cryptogramme;

	public Transformateur2Acteur() {
	}

	public void initialiser() {
	}
	
	public String getNom() {
		return "EQ4";
	}

	public String getDescription() {
		return "Aux petits soins pour vous";
	}

	public Color getColor() {
		return new Color(230, 126, 34);
	}
	

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}
	
	public void next() {
	}
	
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filiere = new ArrayList<String>();
		filiere.add("OPTI'CACAO");  
		return filiere;
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