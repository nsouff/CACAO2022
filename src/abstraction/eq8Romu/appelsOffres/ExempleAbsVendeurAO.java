package abstraction.eq8Romu.appelsOffres;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.filiere.IFabricantChocolatDeMarque;
import abstraction.eq8Romu.filiere.IMarqueChocolat;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;


public class ExempleAbsVendeurAO implements IActeur, IMarqueChocolat, IFabricantChocolatDeMarque {
	private static int NB_INSTANCES = 0; // Afin d'attribuer un nom different a toutes les instances
	protected int numero;
	protected Integer cryptogramme;
	protected Journal journal;
	protected Variable stock;
	protected Chocolat choco;
	protected String marque;
	protected double prixMin;

	public ExempleAbsVendeurAO(Chocolat choco, String marque, double stock, double prixMin) {	
		if (choco==null || marque==null || stock<=0) {
			throw new IllegalArgumentException("creation d'une instance de ExempleAbsVendeurAO avec des arguments non valides");
		}		
		NB_INSTANCES++;
		this.numero=NB_INSTANCES;
		this.stock=new Variable(this.getNom()+"Stock"+choco, this, 0.0, 1000000000.0,stock);
		this.choco = choco;
		this.marque = marque;
		this.prixMin = prixMin;
		this.journal = new Journal(this.getNom()+" activites", this);
	}

	protected ChocolatDeMarque getChocolatDeMarque() {
		return new ChocolatDeMarque(choco,marque);
	}
	public String toString() {
		return this.getNom();
	}
	public String getNom() {
		return "V.AO"+this.numero;
	}

	public String getDescription() {
		return "Vendeur par appels d'offres "+this.numero;
	}

	public Color getColor() {
		return new Color(128+((numero)*(127/NB_INSTANCES)), 64+((numero)*(191/NB_INSTANCES)), 0);
	}

	public void initialiser() {
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
		res.add(stock);
		return res;
	}

	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		return res;
	}

	public List<Journal> getJournaux() {
		List<Journal> j= new ArrayList<Journal>();
		j.add(this.journal);
		return j;
	}

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}

	public void notificationFaillite(IActeur acteur) {
	}

	public void notificationOperationBancaire(double montant) {
	}
	
	protected Variable getStock() {
		return this.stock;
	}
	public List<String> getMarquesChocolat(){
		List<String> marques= new ArrayList<String>();
		marques.add(this.marque);
		return marques;
	}


	public List<ChocolatDeMarque> getChocolatsProduits() {
		 List<ChocolatDeMarque> res = new LinkedList<ChocolatDeMarque>();
		 res.add(new ChocolatDeMarque(choco, marque));
		return res;
	}
}