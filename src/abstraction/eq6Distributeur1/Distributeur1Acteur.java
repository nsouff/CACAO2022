package abstraction.eq6Distributeur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Distributeur1Acteur implements IActeur {
	protected int cryptogramme;
	private SuperviseurVentesContratCadre supCCadre = ((SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre")));
	private Stock NotreStock = new Stock();
	/**
	 * @return the notreStock
	 */
	public Stock getNotreStock() {
		return NotreStock;
	}
	public Distributeur1Acteur() {
	}
	public String getNom() {
		return "EQ6";
	}

	public String getDescription() {
		return "Bla bla bla";
	}

	public Color getColor() {
		return new Color(155, 89, 182);
	}


	public void initialiser() {
	}

	public void next() {//leorouppert
		this.getNotreStock().getMapStock().forEach((key,value)->{
			if (value <= 50) {
				IVendeurContratCadre Vendeur = supCCadre.getVendeurs(key).get(0);
				ExemplaireContratCadre CC = supCCadre.demandeAcheteur((IAcheteurContratCadre)this,Vendeur, value, new Echeancier(Filiere.LA_FILIERE.getEtape()+1,12,100), cryptogramme, false);
			}
		});
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
		return res;
	}

	// Renvoie les paramètres
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
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

	/**@author Nolann
		/* FONCTION POUR FIXER LES PRIX DE VENTE :
		 * 
		 * ENTREES : prixAchat, quantiteAchetee, variable prixVente
	<<<<<<< HEAD
		 * SORTIES : HashMap(ChocolatDeMarque,prix de vente) 
	=======
		 * SORTIES : prix 
	>>>>>>> branch 'main' of https://github.com/nsouff/CACAO2022
		 * 
		 * V1 : 
		 * - prix fixe = prix achat*2
		 * - marge fixe (différente pour chaque produit)
		 * - raisonner en marge du prix d'achat
		*/
	//V2 : 
	//NOLANN
	//Variables : 
	protected Map<ChocolatDeMarque, Double> prixVente = new HashMap<ChocolatDeMarque, Double>();
	private static final ArrayList<Double> NULL = null;
	//Fonction : 
	public Map<ChocolatDeMarque, Double> prixVente( Map<ChocolatDeMarque,Double> prixAchat,  Map<ChocolatDeMarque,Double> quantiteAchete){
		prixAchat.forEach((key,value)->{
			prixVente.put(key, (prixAchat.get(key))*2);	
				
		});
		
		return prixVente;
	}
	/**
	 * @author Nolann
	 *
	 */
	//creation d'un journal "journal":
	
	public Journal journal1 = new Journal("journal", this);
	
	
	/**
	 * @author Nolann
	 *  ajout des indicateurs + fonction actualiser indicateurs :
	 */
		
	
}

















