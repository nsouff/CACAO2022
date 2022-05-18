package abstraction.eq4Transformateur2; 

import abstraction.eq8Romu.appelsOffres.FiliereTestAO;
import abstraction.eq8Romu.appelsOffres.IVendeurAO;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;
import abstraction.eq8Romu.appelsOffres.SuperviseurVentesAO;
import abstraction.eq8Romu.bourseCacao.FiliereTestBourse;
import abstraction.eq8Romu.clients.FiliereTestClientFinal;
import abstraction.eq8Romu.contratsCadres.FiliereTestContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Transformateur2Acteur implements IActeur {
	
	//pas sur de celles ci, mais je les laisse au cas ou...
	private Variable coutStockage;
	protected Variable prixSeuil; // au dela duquel nous n'achetons pas
	private Variable rendementTransfoLongue;
	private Variable prixTransformation; // a renseigner (0? On considere juste le rendement pour le pb des transfolongue, ainsi, un seul parametre à gerer.)
	private Variable TransformationSeuil;
	private Variable prixChocoOriginal;
	protected Variable capaciteStockage;
	private Variable capaciteStockageFixe;// stock que l'on souhaite en permanence
	private Variable expirationFeve; //a considerer dans une v1 ?
	private Variable expirationChoco;//a considerer dans une v1?
	private double marge;

	
	// variables pour les Appel d'Offres
	
	
	protected SuperviseurVentesAO superviseur;
	
	
	
	
	protected Journal journal;

	
	//autres
	protected int cryptogramme;
	protected double NewCap;//à réinitialiser=cpacité de production au début de chaque tour



	

	
	
	//Nawfel
	public Transformateur2Acteur() { //valeurs des min, max, et init (3 derniers parametres) à changer plus tard.
	
		//pas sur de celles ci, mais je les laisse au cas ou...
		this.coutStockage = new Variable("cout stockage", "<html>Cout de stockage</html>",this, 0.0, 10.0, 3.0);
		this.prixSeuil = new Variable("prix seuil", "<html>Prix Seuil</html>",this, 0.0, 10.0, 3.0);
		this.rendementTransfoLongue=new Variable("rendement transfo longue", "<html>Rendement d'une transformation longue</html>",this, 0.0, 10.0, 3.0);
		this.prixTransformation = new Variable("prix transfo", "<html>Cout d'une transformation longue</html>",this, 0.0, 10.0, 3.0);
		this.TransformationSeuil=new Variable("sddsqfqs", "<html>Cout pour passer à la gamme original</html>",this, 0.0, 10.0, 3.0);
		this.prixChocoOriginal=new Variable("cout passage original", "<html>Cout pour passer à la gamme original</html>",this, 0.0, 10.0, 3.0);
		this.capaciteStockage=new Variable("capacite stockage", "<html>Capacite max de stockage</html>",this, 0.0, 10.0, 3.0);
		this.capaciteStockageFixe=new Variable("stock theorique desire", "<html>Stock Theorique désiré en permanence</html>",this, 0.0, 10.0, 3.0);
		this.expirationFeve=new Variable("expiration feve", "<html>Duree avant expiration d'une feve</html>",this, 0.0, 10.0, 3.0);
		this.expirationChoco=new Variable("expiration choco", "<html>Duree avant expiration du chocolat</html>",this, 0.0, 10.0, 3.0);
		this.marge = 1.1;
		this.journal=new Journal("Opti'Cacao activités", this);
		
		

		
		
		
		
		this.NewCap=this.TransformationSeuil.getValeur();

	}
	
	













	public void initialiser() {
		
	}
	
	public String getNom() {
		return "Opti'Cacao";
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
		filiere.add("TESTAOOPTI'CACAO");  
		return filiere;
	}

	public Filiere getFiliere(String nom) {
		switch (nom) { 
		case "TESTAOOPTI'CACAO" : return new CopieFiliereTestAO();
	    default : return null;
		}
	}
	
	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
		return res;
		
	}
	
	public List<Variable> getParametres() { 
		List<Variable> p= new ArrayList<Variable>();
//		p.add(this.expirationChoco);
		return p;
	} 
	

	public List<Journal> getJournaux() {
		List<Journal> j= new ArrayList<Journal>();
		j.add(this.journal);
		return j;
	}
	public double getCout() {
		return this.coutStockage.getValeur();
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

	public double getMarge() {
		return this.marge;
	}


	public int getCryptogramme() {
		return cryptogramme;
	}


	public Variable getCoutStockage() {
		return coutStockage;
	}


	public Variable getPrixSeuil() {
		return prixSeuil;
	}


	public Variable getRendementTransfoLongue() {
		return rendementTransfoLongue;
	}


	public Variable getPrixTransformation() {
		return prixTransformation;
	}


	public Variable getPrixChocoOriginal() {
		return prixChocoOriginal;
	}


	public Variable getCapaciteStockage() {
		return capaciteStockage;
	}


	public Variable getCapaciteStockageFixe() {
		return capaciteStockageFixe;
	}


	public Variable getExpirationFeve() {
		return expirationFeve;
	}


	public Variable getExpirationChoco() {
		return expirationChoco;
	}



	public Variable getTransformationSeuil() {
		return TransformationSeuil;
	}





	
	





































	

}