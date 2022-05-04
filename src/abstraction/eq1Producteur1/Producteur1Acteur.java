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
	private static int NB_INSTANCES = 0; // Afin d'attribuer un nom different a toutes les instances
	protected int numero;
	protected Integer cryptogramme;
	protected Journal journal;
	protected Variable stockFeve;
	protected Feve feve;
	public Variable prixstockageVariable ;
	public Variable prixstockageFixe ;
	private List<Double> prixmoyenFeve ;
	
	
	

	public Producteur1Acteur(Feve feve, double stock) {
		if (feve==null ||stock<=0) {
			throw new IllegalArgumentException("creation d'une instance de ExempleAbsVendeurBourseCacao avec des arguments non valides");
		}		
		NB_INSTANCES++;
		this.numero=NB_INSTANCES;
		this.stockFeve=new Variable(this.getNom()+"Stock"+feve, this, 0.0, 1000000000.0,stock);
		this.feve = feve;
		this.journal = new Journal(this.getNom()+" activites", this);
		
		this.prixstockageVariable=new Variable("prixStockageVariable", this, 0.0, 1000000000.0,0.01);
		this.prixstockageFixe=new Variable("prixStockageFixe", this, 0.0, 1000000000.0,100);
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
		res.add(prixstockageFixe);
		res.add(stockFeve);
		res.add(prixstockageVariable);
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