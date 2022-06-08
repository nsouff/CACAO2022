package abstraction.eq6Distributeur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.general.VariableReadOnly;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.eq8Romu.produits.Chocolat;

public class Distributeur1Acteur implements IActeur {
	protected int cryptogramme;
	protected SuperviseurVentesContratCadre supCCadre;
	protected Stock NotreStock;
	Random ran;
	protected Map<ChocolatDeMarque,Variable> stockageQte;
	protected Journal journal1;
	protected Journal journalCompte;
	protected List<Variable> prix; 
	protected Double prixTotalTour;
	protected Map<ChocolatDeMarque, Double> prixVente;
	protected Variable QteChocoHQ;
	protected Variable QteChocoMQ;
	protected Variable QteChocoBq;
	protected Integer Compteur;	
	protected Map<ChocolatDeMarque, VariableReadOnly> HistoChoco; // Léo
	protected Double ChocoTotalTour; // variable qui donne ce qui a été vendu l'année précédente pour le tour correspondant
	protected Double TauxTour; // renvoi la part de marché visée par FourAll pour le tour en cours
	protected final double partCC = 0.9;
	
	/**
	 * @return the notreStock
	 */
	
	public Stock getNotreStock() {
		return NotreStock;
	}
	
	/**
	 * @author Nolann
	 */
	public Distributeur1Acteur() {
		HistoChoco = new HashMap<ChocolatDeMarque, VariableReadOnly>(); // Léo
		journal1 = new Journal("journal1",this);
		journalCompte = new Journal("journalCompte",this);

		
		this.prixTotalTour = 100000.0;
		prix = new ArrayList<Variable>();
		prixVente = new HashMap<ChocolatDeMarque, Double>();
		ran = new Random();
		
		this.ChocoTotalTour = 0.0;
		
		journal1 = new Journal("journal1",this);
		journalCompte = new Journal("journalCompte",this);
		NotreStock = new Stock(this);
		for(ChocolatDeMarque c : this.getNotreStock().getMapStock().keySet()) 
		{
			journal1.ajouter("ajout d'une variable stock pour le chocolat" + c + "effectué" );
			prix.add(new Variable(c+"",this,0));
			journal1.ajouter("ajout d'une variable prix pour le chocolat " + c + "effectué");
		}	
		
		journal1.ajouter("création de la liste de variable des prix terminée");
		journal1.ajouter("création de la liste de variable stock terminée");
	}
	
	
	public String getNom() {
		return "EQ6-FourAll";
	}

	public String getDescription() {
		return "Rendre toutes les gammes de produit accessibles à tous !";
	}

	public Color getColor() {
		return new Color(155, 89, 182);
	}


	public void initialiser() {
		supCCadre = ((SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre")));
		for (ChocolatDeMarque C : Filiere.LA_FILIERE.getChocolatsProduits()) {
			HistoChoco.put(C, new VariableReadOnly(C.toString(), this,0));
		}
		NotreStock.initialiser();
		
	}
	
	public void next() {
		//leorouppert

		journal1.ajouter("entrée dans next pour le tour n° " + Filiere.LA_FILIERE.getEtape());
		this.getNotreStock().getMapStock().forEach((key,value)->{
			double moy = 0;
			if (Filiere.LA_FILIERE.getEtape() > 0) {
				moy = HistoChoco.get(key).getValeur()/Filiere.LA_FILIERE.getEtape();
			}
			if (value <= Math.max(5000.0, moy)) { 
				journal1.ajouter("Recherche d'un vendeur aupres de qui acheter");
				List<IVendeurContratCadre> ListeVendeurs = supCCadre.getVendeurs(key);
				if (ListeVendeurs.size() != 0) {
					IVendeurContratCadre Vendeur = ListeVendeurs.get(ran.nextInt(ListeVendeurs.size()));
					journal1.ajouter("Demande au superviseur de debuter les negociations pour un contrat cadre de "+key+" avec le vendeur "+Vendeur);
					ExemplaireContratCadre CC = supCCadre.demandeAcheteur((IAcheteurContratCadre)this,Vendeur, value, new Echeancier(Filiere.LA_FILIERE.getEtape()+1,12,10000), cryptogramme, false);
					if (CC == null) {
						journal1.ajouter("-->aboutit au contrat "+ CC);
					}
					else {
						journal1.ajouter("échec des négociations");
					}
				}
			}	
		});	

		journal1.ajouter("entrée dans next pour le tour n° " + Filiere.LA_FILIERE.getEtape());
		getChocoTotalTour();
		/**
		 *  
		 * Gestion des compte -> retirer argent :
		 * @author Nolann	
		 * 	
		 */
		//calcul cout sur le tour :
		
		journal1.ajouter(getDescription());
		
		prixTotalTour = NotreStock.getCoûtStockageTotale();
		if (prixTotalTour > 0) {
			Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), prixTotalTour);
			journalCompte.ajouter("le compte a été débité de "+prixTotalTour);
			journalCompte.ajouter("le il reste"+this.getSolde()+"sur le compte");
		}		
		journal1.ajouter("Tour "+ Filiere.LA_FILIERE.getEtape() +" terminé pour "+ this.getNom());
	
	}
	
	
	
	
	/**
	 * @author Nathan
	 * @return La liste des filières proposées par l'acteur
	 */
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		filieres.add("FD1TEST");
		return(filieres);
	}

	/**
	 * @author Nathan
	 * @return Renvoie une instance d'une filière d'après son nom
	 */
	public Filiere getFiliere(String nom) {
		switch (nom) {
			case "FD1TEST":
				return new FiliereTestDistributeur1();
			default:
				return null;
		}
	}

	// Renvoie les indicateurs
	/**
	 * @author Nolann
	 * changement : on ne renvoie que la quantité de chocolat de type HQ, MQ, BQ
	 */
	public List<Variable> getIndicateurs() {
		List<Variable> res = new ArrayList<Variable>();
		res.addAll((Collection<Variable>)NotreStock.stockVar.values());
		return res;
	}
	

	// Renvoie les paramètres
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		return res;
	}

	@Override
	public List<Journal> getJournaux() {
		List<Journal> journaux = new ArrayList<Journal>();
		journaux.add(journal1);
		journaux.add(journalCompte);
		return journaux;
	}

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
		
	}
	
	
	//EmmaHumeau
	public void notificationFaillite(IActeur acteur) {
		if (NotreStock.seuilSecuFaillite() == true) {
			journal1.ajouter("On risque de faire faillite au prochain tour");
		}
		}

	public void notificationOperationBancaire(double montant) {
		journalCompte.ajouter("Une opération vient d'avoir lieu d'un montant de " + montant);
	}

	// Renvoie le solde actuel de l'acteur
	//Nolann
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(this, this.cryptogramme);
	}
	
	/**
	 * @author Nolann
	 * renvoie le nombre de kg de chocolats vendus au l'année précédente à la même période  
	 */
	public void getChocoTotalTour() {
		
		for(ChocolatDeMarque Choco : Filiere.LA_FILIERE.getChocolatsProduits()) {
			this.ChocoTotalTour = this.ChocoTotalTour + Filiere.LA_FILIERE.getVentes(Choco, Filiere.LA_FILIERE.getEtape()-24);
			journal1.ajouter("il y a eu : "+Filiere.LA_FILIERE.getVentes(Choco, Filiere.LA_FILIERE.getEtape()) +" kg de chocolats vendus de type " 
			+ Choco + " au tour : " + (Filiere.LA_FILIERE.getEtape()-24));
		}
		journal1.ajouter("Il y a eu au total : " + this.ChocoTotalTour + "kg de chocolats vendus au total au tour : " + (Filiere.LA_FILIERE.getEtape()-24));
	}
	
	

	/**
	 * @author Nathan
	 * @param prixAchat
	 * @param quantiteAchete
	 */
	public void setPrixVente(ChocolatDeMarque c, double prixAchatKilo) {
		prixVente.put(c, 1.4*prixAchatKilo);
	}
	
	/**
	 * 
	 * @author Nolann
	 * @return prixVente (V1 prix vente = 2*prix achat)
	 *  
	 */
	public void setAllprixVente( Map<ChocolatDeMarque,Double> prixAchat,  Map<ChocolatDeMarque,Double> quantiteAchete){
		prixAchat.forEach((key,value)->{
			prixVente.put(key, (prixAchat.get(key))*2);		
		});
	}

	public double partDuMarcheVoulu(Chocolat c) {
		switch(c) {
			case BQ: return 0.7;
			case BQ_O: return 0.7;
			case MQ: return 0.5;
			case MQ_O: return 0.5;
			case MQ_BE: return 0.5;
			case MQ_BE_O: return 0.5;
			case HQ: return 0.3;
			case HQ_O: return 0.3;
			case HQ_BE: return 0.3;
			case HQ_BE_O: return 0.3;
			default: return 0.0;
		}
	}


	public int getNbChocolatProduit(Chocolat c) {
		int count = 0;
		for (ChocolatDeMarque choco : Filiere.LA_FILIERE.getChocolatsProduits()) {
			if (choco.getChocolat() == c) {
				count++;
			}
		}
		return count;
	}
	
	public double getPartMarque(ChocolatDeMarque choco) {
		return 1.0/getNbChocolatProduit(choco.getChocolat());
	}

	protected double facteurPrixChocolat(Chocolat c) {
		double res = 1.0;
		if (c.getGamme() == Gamme.MOYENNE) {
			res *= 1.2;
		}
		else if (c.getGamme() == Gamme.HAUTE) {
			res *= 1.4;
		}
		if (c.isBioEquitable()) {
			res *= 1.2;
		}
		if (c.isOriginal()) {
			res *= 1.2;
		}
		return res;
	}
}
