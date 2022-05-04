package abstraction.eq1Producteur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Feve;

public class Producteur1Acteur extends Producteur1Producteur implements IActeur {
	private static int NB_INSTANCES = 0; // Afin d'attribuer un nom different a toutes les instances
	protected int numero;
	protected Journal journal;
	private List<Double> prixmoyenFeve ;
	private Variable StockBasse;
	private Variable StockMoyenne;
	private Variable StockHaut_BE;
	
	
	
	//Auteur : Khéo
	public Producteur1Acteur() {
		super();
		NB_INSTANCES++;
		this.numero=NB_INSTANCES;
		this.journal = new Journal(this.getNom()+" activites", this);
		this.StockBasse= new Variable(this.getNom()+"StockBasse", "Stock de Fèves Basse", 
				this, 0, 1000000000, this.getStock(Feve.FEVE_BASSE));
		this.StockMoyenne= new Variable(this.getNom()+"StockMoyenne", "Stock de Fèves Moyenne", 
				this, 0, 1000000000, this.getStock(Feve.FEVE_MOYENNE));
		this.StockHaut_BE= new Variable(this.getNom()+"StockHautBE", "Stock de Fèves Haut Bio équitable", 
				this, 0, 1000000000, this.getStock(Feve.FEVE_HAUTE_BIO_EQUITABLE));
	}

	public void initialiser() {
		super.initialiser();
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
	
	//Auteur : Khéo
	public void next() {
		super.next();
		
		//Calcul du Prix Total de Stockage
		double prixTotal = 0 ;
		for (Feve f : this.getFeves().keySet()) {
			prixTotal = prixTotal + (this.getStock(f)*Filiere.LA_FILIERE.getParametre("prixstockage").getValeur()) ;
		}
		
		//Calcul Prix Entretien Arbre 
		
		
		
		//Retirer l'argent 
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), prixTotal);
		
		
		//Mis à jour Variable
		this.getStockBasse().setValeur(this, this.getStock(Feve.FEVE_BASSE));
		this.getStockHaut_BE().setValeur(this, this.getStock(Feve.FEVE_HAUTE_BIO_EQUITABLE));
		this.getStockMoyenne().setValeur(this, this.getStock(Feve.FEVE_MOYENNE));
	}
	

	public List<String> getNomsFilieresProposees() {
		return new ArrayList<String>();
	}

	public Filiere getFiliere(String nom) {
		return null;
	}
	
	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
		res.add(StockBasse);
		res.add(StockHaut_BE);
		res.add(StockMoyenne);
		
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
	
	
	//Auteur : Khéo
	/**
	 * @return the stockBasse
	 */
	public Variable getStockBasse() {
		return StockBasse;
	}

	/**
	 * @return the stockMoyenne
	 */
	public Variable getStockMoyenne() {
		return StockMoyenne;
	}

	/**
	 * @return the stockHaut_BE
	 */
	public Variable getStockHaut_BE() {
		return StockHaut_BE;
	}
}