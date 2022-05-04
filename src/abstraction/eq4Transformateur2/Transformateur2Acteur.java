package abstraction.eq4Transformateur2; 

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Transformateur2Acteur implements IActeur {
	
	protected int cryptogramme;
	
	private Variable qualiteHaute;  // La qualite d'un chocolat de gamme haute 
	private Variable qualiteMoyenne;// La qualite d'un chocolat de gamme moyenne  
	private Variable qualiteBasse;  // La qualite d'un chocolat de gamme basse
	private Variable gainQualiteBioEquitable;// Le gain en qualite des chocolats bio equitables
	private Variable gainQualiteOriginal;// Le gain en qualite des chocolats originaux
	private Variable partDeLaMarqueDansLaQualitePercu;// Le gain en qualite des chocolats originaux
	
	private Variable coutStockage;
	private Variable prixSeuil; // au dela duquel nous n'achetons pas
	private Variable rendementTransfoLongue;
	private Variable prixTransformation; // a renseigner (0? On considere juste le rendement pour le pb des transfolongue, ainsi, un seul parametre à gerer.)
	private Variable prixChocoOriginal;
	private Variable capaciteStockage;
	private Variable capaciteStockageFixe;// stock que l'on souhaite en permanence
	private Variable expirationFeve; //a considerer dans une v1 ?
	private Variable expirationChoco;//a considerer dans une v1?
	
	

	
	
	
	public Transformateur2Acteur() { //valeurs des min, max, et init (3 derniers parametres) à changer plus tard.
	/*	this.qualiteHaute   = new Variable("qualite haute", "<html>Qualite du chocolat<br>de gamme haute</html>",this, 0.0, 10.0, 3.0);
		this.qualiteMoyenne = new Variable("qualite moyenne", "<html>Qualite du chocolat<br>de gamme moyenne</html>",this, 0.0, 10.0, 2.0);
		this.qualiteBasse   = new Variable("qualite basse", "<html>Qualite du chocolat<br>de gamme basse</html>",this, 0.0, 10.0, 1.0);
		this.gainQualiteBioEquitable  = new Variable("gain qualite bioequitable", "<html>Gain en qualite des<br>chocolats bio equitables</html>",this, 0.0, 5.0, 0.5);
		this.gainQualiteOriginal  = new Variable("gain qualite original", "<html>Gain en qualite des<br>chocolats originaux</html>",this, 0.0, 5.0, 0.5);
		this.partDeLaMarqueDansLaQualitePercu  = new Variable("impact marque qualite percue", "<html>% de la qualite percue de la marque dans la qualite percue du chocolat</html>",this, 0.0, 0.5, 0.3);*/
		
		this.coutStockage = new Variable("cout stockage", "<html>Cout de stockage</html>",this, 0.0, 10.0, 3.0);
		this.prixSeuil = new Variable("prix seuil", "<html>Prix Seuil</html>",this, 0.0, 10.0, 3.0);
		this.rendementTransfoLongue=new Variable("rendement transfo longue", "<html>Rendement d'une transformation longue</html>",this, 0.0, 10.0, 3.0);
		this.prixTransformation = new Variable("prix transfo", "<html>Cout d'une transformation longue</html>",this, 0.0, 10.0, 3.0);
		this.prixChocoOriginal=new Variable("cout passage original", "<html>Cout pour passer à la gamme original</html>",this, 0.0, 10.0, 3.0);
		this.capaciteStockage=new Variable("capacite stockage", "<html>Capacite max de stockage</html>",this, 0.0, 10.0, 3.0);
		this.capaciteStockageFixe=new Variable("stock theorique desire", "<html>Stock Theorique désiré en permanence</html>",this, 0.0, 10.0, 3.0);
		this.expirationFeve=new Variable("expiration feve", "<html>Duree avant expiration d'une feve</html>",this, 0.0, 10.0, 3.0);
		this.expirationChoco=new Variable("expiration choco", "<html>Duree avant expiration du chocolat</html>",this, 0.0, 10.0, 3.0);
		
	}
	
	


	public void initialiser() {
		double rendement =Filiere.LA_FILIERE.getIndicateur("rendement").getValeur();
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
	
	public List<Variable> getParametres() { // A completer avec tous les autres variables d'instances
		List<Variable> p= new ArrayList<Variable>();
//		p.add(this.qualiteHaute);
//		p.add(this.qualiteMoyenne);
//		p.add(this.qualiteBasse);
//		p.add(this.gainQualiteBioEquitable);
//		p.add(this.gainQualiteOriginal); 
//		p.add(this.partDeLaMarqueDansLaQualitePercu);
		return p;
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