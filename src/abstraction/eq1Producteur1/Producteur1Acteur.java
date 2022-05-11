package abstraction.eq1Producteur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.appelsOffres.FiliereTestAO;
import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.bourseCacao.FiliereTestBourse;
import abstraction.eq8Romu.clients.FiliereTestClientFinal;
import abstraction.eq8Romu.contratsCadres.FiliereTestContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Feve;

public class Producteur1Acteur extends Producteur1Producteur implements IActeur {
	private static int NB_INSTANCES = 0; // Afin d'attribuer un nom different a toutes les instances
	protected int numero;
	protected Journal journal;
	private Variable StockBasse;
	private Variable StockMoyenne;
	private Variable StockHaut_BE;
	private Variable StockMoyenne_BE;
	private Variable StockBasse_NA; //on compte les fèves non affinées
	private Variable StockMoyenne_NA;
	private Variable StockHaut_BE_NA;
	private Variable StockMoyenne_BE_NA;
	
	
	private Variable PrixEntretienArbre;
	
	
	
	//Auteur : Khéo
	public Producteur1Acteur() {
		super();
		NB_INSTANCES++;
		this.numero=NB_INSTANCES;
		this.journal = new Journal(this.getNom()+" activites", this);
		this.StockBasse_NA= new Variable(this.getNom()+"StockBasse_NA", "Stock de Fèves Basse avec sans Affinage", 
				this, 0, 1000000000, this.getStock(Feve.FEVE_BASSE, true));
		this.StockMoyenne_NA= new Variable(this.getNom()+"StockMoyenne_NA", "Stock de Fèves Moyenne avec sans Affinage", 
				this, 0, 1000000000, this.getStock(Feve.FEVE_MOYENNE, true));
		this.StockHaut_BE_NA= new Variable(this.getNom()+"StockHautBE_NA", "Stock de Fèves Haut Bio équitable avec sans Affinage", 
				this, 0, 1000000000, this.getStock(Feve.FEVE_HAUTE_BIO_EQUITABLE, true));
		this.StockMoyenne_BE_NA= new Variable(this.getNom()+"StockMoyenne_BE_NA", "Stock de Fèves Moyenne Bio équitable avec sans Affinage", 
				this, 0, 1000000000, this.getStock(Feve.FEVE_MOYENNE_BIO_EQUITABLE, true));
		
		this.StockBasse= new Variable(this.getNom()+"StockBasse", "Stock de Fèves Basse sans non Affinage", 
				this, 0, 1000000000, this.getStock(Feve.FEVE_BASSE, false));
		this.StockMoyenne= new Variable(this.getNom()+"StockMoyenne", "Stock de Fèves Moyenne sans non Affinage", 
				this, 0, 1000000000, this.getStock(Feve.FEVE_MOYENNE, false));
		this.StockHaut_BE= new Variable(this.getNom()+"StockHautBE", "Stock de Fèves Haut Bio équitable sans non Affinage", 
				this, 0, 1000000000, this.getStock(Feve.FEVE_HAUTE_BIO_EQUITABLE, false));
		this.StockMoyenne_BE= new Variable(this.getNom()+"StockMoyenne_BE", "Stock de Fèves Moyenne Bio équitable sans non Affinage", 
				this, 0, 1000000000, this.getStock(Feve.FEVE_MOYENNE_BIO_EQUITABLE, false));
		
		this.PrixEntretienArbre= new Variable(this.getNom()+"Prix Entretien Arbre", "Prix Entretien des Arbres", 
				this, 0, 1000000000, 0.001);
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
		
		//Mis à jour Variable
		this.getStockBasse().setValeur(this, this.getStock(Feve.FEVE_BASSE, false));
		this.getStockHaut_BE().setValeur(this, this.getStock(Feve.FEVE_HAUTE_BIO_EQUITABLE, false));
		this.getStockMoyenne().setValeur(this, this.getStock(Feve.FEVE_MOYENNE, false));
		this.getStockMoyenne_BE().setValeur(this, this.getStock(Feve.FEVE_MOYENNE_BIO_EQUITABLE, false));
		
		this.getStockBasse_NA().setValeur(this, this.getStock(Feve.FEVE_BASSE, true));
		this.getStockHaut_BE_NA().setValeur(this, this.getStock(Feve.FEVE_HAUTE_BIO_EQUITABLE, true));
		this.getStockMoyenne_NA().setValeur(this, this.getStock(Feve.FEVE_MOYENNE, true));
		this.getStockMoyenne_BE_NA().setValeur(this, this.getStock(Feve.FEVE_MOYENNE_BIO_EQUITABLE, true));
		
		
		double prixTotal = 0 ;
		//Calcul du Prix Total de Stockage
		for (Feve f : this.getFeves().keySet()) {
			prixTotal = prixTotal + (this.getStock(f, true)*Filiere.LA_FILIERE.getParametre("Prix Stockage").getValeur()) ;
		}
		
		//Calcul Prix Entretien Arbre
		
		prixTotal = prixTotal 
				+ this.getAfrique().getNombre_BE_haute()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur() 
				+ this.getAfrique().getNombre_non_BE_haute()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*1.1 
				+ this.getAfrique().getNombre_non_BE_moyenne()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()
				+ this.getAfrique().getNombre_BE_moyenne()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*1.1 
				+ this.getAfrique().getNombre_non_BE_basse()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*0.9 ;
		
		
		//Retirer l'argent 
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), prixTotal);
		

	}
	

	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		filieres.add("TESTBOURSEEQ1"); 
		return filieres;
	}

	public Filiere getFiliere(String nom) {
		switch (nom) { 
		case "TESTBOURSEEQ1" : return new FiliereTestBourseEq1();
	    default : return null;
		}
	}
	

	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
		res.add(this.getStockBasse());
		res.add(this.getStockHaut_BE());
		res.add(this.getStockMoyenne());
		res.add(this.getStockMoyenne_BE());
		
		res.add(this.getStockBasse_NA());
		res.add(this.getStockHaut_BE_NA());
		res.add(this.getStockMoyenne_NA());
		res.add(this.getStockMoyenne_BE_NA());
		
		return res;
	}
	
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		res.add(this.getPrixEntretienArbre());
	
		return res; 
	}



	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		return res;
	}

	public void notificationFaillite(IActeur acteur) {
		if (this==acteur) {
		System.out.println("Le Stonks n'était qu'un rêve "+this.getNom());
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
	
	/**
	 * @return the stockMoyenne_BE
	 */
	public Variable getStockMoyenne_BE() {
		return StockMoyenne_BE;
	}
	
	/**
	 * @return the prixEntretienArbre
	 */
	public Variable getPrixEntretienArbre() {
		return PrixEntretienArbre;
	}

	/**
	 * @return the nB_INSTANCES
	 */
	public static int getNB_INSTANCES() {
		return NB_INSTANCES;
	}


	/**
	 * @return the stockBasse_NA
	 */
	public Variable getStockBasse_NA() {
		return StockBasse_NA;
	}

	/**
	 * @return the stockMoyenne_NA
	 */
	public Variable getStockMoyenne_NA() {
		return StockMoyenne_NA;
	}

	/**
	 * @return the stockHaut_BE_NA
	 */
	public Variable getStockHaut_BE_NA() {
		return StockHaut_BE_NA;
	}

	/**
	 * @return the stockMoyenne_BE_NA
	 */
	public Variable getStockMoyenne_BE_NA() {
		return StockMoyenne_BE_NA;
	}
	
}