package abstraction.eq5Transformateur3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.ContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.general.VariableReadOnly;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;

public class Transformateur3Acteur implements IActeur {
	
	protected int cryptogramme;
	
	//Karla
	protected Double seuilMaxAchat; // par kg en dollars, au dessus de ce prix, on n'achète pas de fèves
	protected Double SeuilMinFeves; // en kg : En dessous de ce seuil, on achète des fèves car stock trop "bas"
	protected Double SeuilMinChocolat; // en kg : Au dessus de ce seuil, on vend du chocolat car stock trop "haut"
	protected Double achatMaxFeves; // en kg, quantité de fèves max qu'on peut acheter en 1 tour
	protected Variable seuilTransformation;
	protected Variable rendement;
	protected Variable coutTransformation;
	protected Variable coutOriginal;
	protected Stock<Feve> stockFeves;
	protected Stock<Chocolat> stockChocolat;
	
	//julien 04/05 : 
	
	protected HashMap<Double ,ContratCadre> contratsEnCours;
	
	//Karla
	public Transformateur3Acteur() {
		this.seuilTransformation = new VariableReadOnly ("seuiTransformation", "seuil de transformation par etape en tonne", this,  0, 100000, 100000);
		this.rendement = new VariableReadOnly ("rendement", "rendement de la transformation longue", this,  0, 0.99, 0.7);
		this.coutTransformation = new VariableReadOnly ("coutTransformation", "cout de transformation en milliers de dollars par etape par kg", this,  0, 1000, 5);
		this.coutOriginal = new VariableReadOnly ("coutOriginal", "cout supplementaire pour un produire un chocolat orginal en milliers de dollars par etape par tonne", this, 0, 100, 1);
		this.stockFeves = new Stock<Feve> ();
		this.stockChocolat = new Stock<Chocolat> ();
		this.seuilMaxAchat = 2900.00;
		this.SeuilMinFeves = 10000.00;
		this.SeuilMinChocolat = 100.00;
		this.achatMaxFeves = 5000.00;

	}

	//julien
	public String getNom() {
		return "EQ5";
	}

	//julien
	public String getDescription() {
		return "Nous sommes BIO'riginal. Venez goûter notre bon chocolat";
	}

	public Color getColor() {
		return new Color(231, 76, 60);
	}


	public void initialiser() {
	}

	public void next() {
	}

	
	// Renvoie la liste des filières proposées par l'acteur
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		return(filieres);
	}

	// Renvoie une instance d'une filière d'après son nom
	public Filiere getFiliere(String nom) {
		return Filiere.LA_FILIERE;
	}

	// Renvoie les indicateurs
	public List<Variable> getIndicateurs() {
		List<Variable> res = new ArrayList<Variable>();
		res.add(rendement);
		res.add(seuilTransformation);
		res.add(coutOriginal);
		res.add(coutTransformation);
		return res;
	}

	// Renvoie les paramètres
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		res.add(this.seuilTransformation);
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
	}

	public void notificationOperationBancaire(double montant) {
	}
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(Filiere.LA_FILIERE.getActeur(getNom()), this.cryptogramme);
	}

}

