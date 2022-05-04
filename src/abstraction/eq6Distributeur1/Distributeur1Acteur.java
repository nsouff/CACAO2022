package abstraction.eq6Distributeur1;

import java.awt.Color;
import java.util.ArrayList;
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
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Distributeur1Acteur implements IActeur {
	protected int cryptogramme;
	protected SuperviseurVentesContratCadre supCCadre;
	protected Stock NotreStock = new Stock();
	Random ran = new Random();
	protected List<ExemplaireContratCadre> mesContrats;
	protected Map<ChocolatDeMarque,Variable> stockageQte;
	protected Journal journal1 = new Journal("journal1",this);
	
	protected List<Variable> stock = new ArrayList<Variable>(); 
	protected List<Variable> prix = new ArrayList<Variable>(); 
	
			
			
			
			
			
	
	
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
		for(ChocolatDeMarque c : this.getNotreStock().getMapStock().keySet()) 
		{
			stock.add(new Variable(c+"",this,this.getNotreStock().getStock(c)));
			prix.add(new Variable(c+"",this,0));
		}	
		journal1.ajouter("liste de variable prix créée");
		journal1.ajouter("liste de variable stock créée");

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
		supCCadre = ((SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre")));
	}
	
	public void suppAnciensContrats() {//leorouppert
		for (ExemplaireContratCadre contrat : mesContrats) {
			if (contrat.getQuantiteRestantALivrer() == 0.0 && contrat.getMontantRestantARegler() == 0.0) {
				mesContrats.remove(contrat);
			}
		}
	}
	
	public void next() {
		//leorouppert
		this.suppAnciensContrats();
		this.getNotreStock().getMapStock().forEach((key,value)->{
			if (value <= 50) {
				journal1.ajouter("Recherche d'un vendeur aupres de qui acheter");
				List<IVendeurContratCadre> ListeVendeurs = supCCadre.getVendeurs(key);
				IVendeurContratCadre Vendeur = ListeVendeurs.get(ran.nextInt(ListeVendeurs.size()));
				journal1.ajouter("Demande au superviseur de debuter les negociations pour un contrat cadre de "+key+" avec le vendeur "+Vendeur);
				ExemplaireContratCadre CC = supCCadre.demandeAcheteur((IAcheteurContratCadre)this,Vendeur, value, new Echeancier(Filiere.LA_FILIERE.getEtape()+1,12,100), cryptogramme, false);
				if (CC == null) {
					journal1.ajouter("-->aboutit au contrat "+ CC);
				}
				else {journal1.ajouter("échec des négociations");}
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
	/**
	 * @author Nolann
	 */
	public List<Variable> getIndicateurs() {
		
		List<Variable> res = new ArrayList<Variable>();
		this.getNotreStock().getMapStock().forEach((key,value)->{
			for( Variable v : stock) {
				res.add(v);
			}
			for(Variable v : prix) {
				res.add(v);
			}
		});
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
 
	protected Map<ChocolatDeMarque, Double> prixVente = new HashMap<ChocolatDeMarque, Double>();
	private static final ArrayList<Double> NULL = null;
	
	
	/**
	 * 
	 * @author Nolann
	 * @return prixVente
	 */
	public Map<ChocolatDeMarque, Double> prixVente( Map<ChocolatDeMarque,Double> prixAchat,  Map<ChocolatDeMarque,Double> quantiteAchete){
		prixAchat.forEach((key,value)->{
			prixVente.put(key, (prixAchat.get(key))*2);	
				
		});
		
		return prixVente;
	}
	
	/**
	 * @author Nolann
	 *  ajout des indicateurs + fonction actualiser indicateurs :
	 */
	/*public void actualiserIndicateurs(){
		for(Variable v : prix) {
			v = 
		};
		
		
		
		journal1.ajouter("l'indicateur prix à été actualisé");
		journal1.ajouter("l'indicateur prix à été actualisé");
		journal1.ajouter("l'indicateur prix à été actualisé");
	}*/
	
	
}

















