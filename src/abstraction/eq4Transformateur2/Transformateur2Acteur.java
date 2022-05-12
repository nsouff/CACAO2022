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
import java.util.List;

public class Transformateur2Acteur implements IActeur,IVendeurAO {
	
	//pas sur de celles ci, mais je les laisse au cas ou...
	private Variable coutStockage;
	protected Variable prixSeuil; // au dela duquel nous n'achetons pas
	private Variable rendementTransfoLongue;
	private Variable prixTransformation; // a renseigner (0? On considere juste le rendement pour le pb des transfolongue, ainsi, un seul parametre à gerer.)
	private Variable prixChocoOriginal;
	private Variable capaciteStockage;
	private Variable capaciteStockageFixe;// stock que l'on souhaite en permanence
	private Variable expirationFeve; //a considerer dans une v1 ?
	private Variable expirationChoco;//a considerer dans une v1?
	private double marge;

	
	// variables pour les Appel d'Offres
	private Stock<Feve> stockfeve;
	private Stock<Chocolat> stockchocolat;
	private Stock<ChocolatDeMarque> stockchocolatdemarque;
	protected double prixInit;// Lorsque l'on est acheteur d'une Appel d'Offre
	protected double prixMin; //Lorsque l'on est vendeur d'une Appel d'Offre
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
		this.prixChocoOriginal=new Variable("cout passage original", "<html>Cout pour passer à la gamme original</html>",this, 0.0, 10.0, 3.0);
		this.capaciteStockage=new Variable("capacite stockage", "<html>Capacite max de stockage</html>",this, 0.0, 10.0, 3.0);
		this.capaciteStockageFixe=new Variable("stock theorique desire", "<html>Stock Theorique désiré en permanence</html>",this, 0.0, 10.0, 3.0);
		this.expirationFeve=new Variable("expiration feve", "<html>Duree avant expiration d'une feve</html>",this, 0.0, 10.0, 3.0);
		this.expirationChoco=new Variable("expiration choco", "<html>Duree avant expiration du chocolat</html>",this, 0.0, 10.0, 3.0);
		this.marge = 1.1;

		
		
		
		this.prixInit=15; //arbitraire
		this.prixMin=5;
		this.journal=new Journal("Opti'Cacao activités", this);
		
		
		//LES STOCKS INITIAUX----VALEURS A CHOISIR
		this.stockfeve=new Stock();
		this.stockfeve.ajouter(Feve.FEVE_BASSE, 15000);
		this.stockfeve.ajouter(Feve.FEVE_MOYENNE, 9000);
		
		//On se fixe une marque pour un type de chocolat
		ChocolatDeMarque chocomax=new ChocolatDeMarque(Chocolat.MQ,"Omax");
		ChocolatDeMarque chocoptella=new ChocolatDeMarque(Chocolat.BQ,"Optella");
		ChocolatDeMarque chocoriginal=new ChocolatDeMarque(Chocolat.MQ_O,"Chocoriginal");
		this.stockchocolatdemarque=new Stock();
		this.stockchocolatdemarque.ajouter(chocomax, 20000);
		this.stockchocolatdemarque.ajouter(chocoptella, 30000);
		this.stockchocolatdemarque.ajouter(chocoriginal, 3000);
		
		
		
		//this.NewCap=Filiere.LA_FILIERE.getIndicateur("seuilTransformation").getValeur();

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
		//Nawfel
		
		//quelques infos d'abord
		this.superviseur = (SuperviseurVentesAO)(Filiere.LA_FILIERE.getActeur("Sup.AO"));
		journal.ajouter("PrixMin=="+this.prixMin);
		
		//ON implemente le journal avec des infos sur nos stocks à chaque tour
		if (this.stockfeve.getStock().keySet().size()>0) {
			for (Feve f : this.stockfeve.getStock().keySet()) {
				this.journal.ajouter("stock de feve "+f+" : "+this.stockfeve.getStock().get(f));
			}
		}
		if (this.stockchocolatdemarque.getStock().keySet().size()>0) {
			for (ChocolatDeMarque c : this.stockchocolatdemarque.getStock().keySet()) {
				this.journal.ajouter("stock de chocolat de marque "+c+" : "+this.stockchocolatdemarque.getStock().get(c));
			}
		}
		
		
		
		//Pour les Vente en Appel d'Offre. On appelle une offre lorsque un stock de chocolatdemarque depasse les 2000kg, on en propose 2000kg.
		for(ChocolatDeMarque c :this.stockchocolatdemarque.getStock().keySet()) {
			if(this.stockchocolatdemarque.getStock().get(c)>2000) {
				PropositionAchatAO retenue = superviseur.vendreParAO(this, cryptogramme, c, 2000.0, false);
				if (retenue!=null) {
					this.stockchocolatdemarque.enlever(retenue.getOffre().getChocolat(), retenue.getOffre().getQuantiteKG());
					journal.ajouter("vente de "+retenue.getOffre().getQuantiteKG()+" kg a "+retenue.getAcheteur().getNom());
				} else {
					journal.ajouter("pas d'offre retenue");
				}
			}
		}
		
		
		
		
	}
	
	//Nawfel
	//Vente en AO : comment choisir parmi les offres
	public PropositionAchatAO choisir(List<PropositionAchatAO> propositions) {
		this.journal.ajouter("propositions : "+propositions);
		if (propositions==null) {
			return null;
		} else {
			PropositionAchatAO meilleur_proposition=propositions.get(0);
			for(PropositionAchatAO p : propositions) {
				if (p.getPrixKg()>meilleur_proposition.getPrixKg()){
					meilleur_proposition=p;
				}
			}
			PropositionAchatAO retenue = meilleur_proposition;
			if (retenue.getPrixKg()>this.prixMin) {
				this.journal.ajouter("  --> je choisis "+retenue);
				return retenue;
			} else {
				this.journal.ajouter("  --> je ne retiens rien");
				return null;
			}
		}
	}
	

	
	
	
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filiere = new ArrayList<String>();
		filiere.add("TESTAO");  
		return filiere;
	}

	public Filiere getFiliere(String nom) {
		switch (nom) { 
		case "TESTAO" : return new CopieFiliereTestAO();
	    default : return null;
		}
	}
	
	//rajouter les stocks de feves aussi (de chaque type)
	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
		for (Feve f : this.stockfeve.getStock().keySet()) {
			res.add(new Variable("Opti'Cacao STOCK"+f, this, 0.0, 1000000000.0,this.stockfeve.getStock().get(f)));
		}
		for (ChocolatDeMarque c : this.stockchocolatdemarque.getStock().keySet()) {
			res.add(new Variable("Opti'Cacao STOCK"+c, this, 0.0, 1000000000.0,this.stockchocolatdemarque.getStock().get(c)));
		}
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




	public Stock<Feve> getStockfeve() {
		return this.stockfeve;
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















	public Stock<Chocolat> getStockchocolat() {
		return stockchocolat;
	}















	public Stock<ChocolatDeMarque> getStockchocolatdemarque() {
		return stockchocolatdemarque;
	}















	

}