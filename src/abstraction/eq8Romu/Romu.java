package abstraction.eq8Romu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.appelsOffres.FiliereTestAO;
import abstraction.eq8Romu.appelsOffres.IAcheteurAO;
import abstraction.eq8Romu.appelsOffres.IVendeurAO;
import abstraction.eq8Romu.appelsOffres.OffreVente;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;
import abstraction.eq8Romu.appelsOffres.SuperviseurVentesAO;
import abstraction.eq8Romu.bourseCacao.FiliereTestBourse;
import abstraction.eq8Romu.bourseCacao.IAcheteurBourse;
import abstraction.eq8Romu.bourseCacao.IVendeurBourse;
import abstraction.eq8Romu.clients.FiliereTestClientFinal;
import abstraction.eq8Romu.contratsCadres.FiliereTestContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.filiere.IFabricantChocolatDeMarque;
import abstraction.eq8Romu.filiere.IMarqueChocolat;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.general.VariablePrivee;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;

public class Romu implements IActeur, IVendeurBourse, IAcheteurBourse, IMarqueChocolat, IFabricantChocolatDeMarque, IVendeurAO, IAcheteurAO {

	@SuppressWarnings("unused")
	public static Color COLOR_LLGRAY = new Color(238,238,238);
	public static Color COLOR_BROWN  = new Color(141,100,  7);
	public static Color COLOR_PURPLE = new Color(100, 10,115);
	public static Color COLOR_LPURPLE= new Color(155, 89,182);
	public static Color COLOR_GREEN  = new Color(  6,162, 37);
	public static Color COLOR_LGREEN = new Color(  6,255, 37);
	private Integer cryptogramme;
	private Variable qualiteHaute;  // La qualite d'un chocolat de gamme haute 
	private Variable qualiteMoyenne;// La qualite d'un chocolat de gamme moyenne  
	private Variable qualiteBasse;  // La qualite d'un chocolat de gamme basse
	private Variable gainQualiteBioEquitable;// Le gain en qualite des chocolats bio equitables
	private Variable gainQualiteOriginal;// Le gain en qualite des chocolats originaux
	private Variable partDeLaMarqueDansLaQualitePercu;// Le gain en qualite des chocolats originaux
	private List<Feve> lesFeves;
	private HashMap<Feve, Double> stockFeves;
	private Variable totalStocksFeves;  // La qualite totale de stock de feves 
	private HashMap<Chocolat, Double> stockChoco;
	private Variable totalStocksChoco;  // La qualite totale de stock de chocolat 
	private HashMap<ChocolatDeMarque, Double> stockChocoMarque;
	private Variable totalStocksChocoMarque;  // La qualite totale de stock de chocolat de marque 
	private HashMap<Feve, HashMap<Chocolat, Double>> pourcentageTransfo; // pour les differentes feves, le chocolat qu'elle peuvent contribuer a produire avec le ratio
	private SuperviseurVentesAO superviseurVentesAO;
	private Journal journal;
	private List<ChocolatDeMarque>chocosProduits;


	public Romu() {
		this.qualiteHaute   = new Variable("qualite haute", "<html>Qualite du chocolat<br>de gamme haute</html>",this, 0.0, 10.0, 3.0);
		this.qualiteMoyenne = new Variable("qualite moyenne", "<html>Qualite du chocolat<br>de gamme moyenne</html>",this, 0.0, 10.0, 2.0);
		this.qualiteBasse   = new Variable("qualite basse", "<html>Qualite du chocolat<br>de gamme basse</html>",this, 0.0, 10.0, 1.0);
		this.gainQualiteBioEquitable  = new Variable("gain qualite bioequitable", "<html>Gain en qualite des<br>chocolats bio equitables</html>",this, 0.0, 5.0, 0.5);
		this.gainQualiteOriginal  = new Variable("gain qualite original", "<html>Gain en qualite des<br>chocolats originaux</html>",this, 0.0, 5.0, 0.5);
		this.partDeLaMarqueDansLaQualitePercu  = new Variable("impact marque qualite percue", "<html>% de la qualite percue de la marque dans la qualite percue du chocolat</html>",this, 0.0, 0.5, 0.3);
		this.journal = new Journal("Journal "+this.getNom(), this);
		this.totalStocksFeves = new VariablePrivee("Eq8StockFeves", "<html>Quantite totale de feves en stock</html>",this, 0.0, 1000000.0, 0.0);
		this.totalStocksChoco = new VariablePrivee("Eq8StockChoco", "<html>Quantite totale de chocolat en stock</html>",this, 0.0, 1000000.0, 0.0);
		this.totalStocksChocoMarque = new VariablePrivee("Eq8StockChocoMarque", "<html>Quantite totale de chocolat de marque en stock</html>",this, 0.0, 1000000.0, 0.0);
		this.chocosProduits = new LinkedList<ChocolatDeMarque>();
	}

	public String getNom() {
		return "EQ8";
	}

	public String getDescription() {
		return "createur des autres acteurs de la filiere";
	}

	public Color getColor() {
		return new Color(96, 125, 139);
	}

	public void initialiser() {
		this.lesFeves = new LinkedList<Feve>();
		this.journal.ajouter("Les Feves sont :");
		for (Feve f : Feve.values()) {
			this.lesFeves.add(f);
			this.journal.ajouter("   - "+f);
		}
		this.stockFeves=new HashMap<Feve,Double>();
		for (Feve f : this.lesFeves) {
			this.stockFeves.put(f, 10000.0);
			this.totalStocksFeves.ajouter(this, 10000.0, this.cryptogramme);
			this.journal.ajouter("ajout de 10000 au stock de feves -->"+this.totalStocksFeves.getValeur(this.cryptogramme));
		}
		this.stockChoco=new HashMap<Chocolat,Double>();
		for (Chocolat c : Chocolat.values()) {
			this.stockChoco.put(c, 1000.0);
			this.totalStocksChoco.ajouter(this, 1000.0, this.cryptogramme);
			this.journal.ajouter("ajout de 1000 au stock de chocolat -->"+this.totalStocksFeves.getValeur(this.cryptogramme));
		}
		this.stockChocoMarque=new HashMap<ChocolatDeMarque,Double>();
		this.pourcentageTransfo = new HashMap<Feve, HashMap<Chocolat, Double>>();
		this.pourcentageTransfo.put(Feve.FEVE_HAUTE_BIO_EQUITABLE, new HashMap<Chocolat, Double>());
		this.pourcentageTransfo.get(Feve.FEVE_HAUTE_BIO_EQUITABLE).put(Chocolat.HQ_BE_O, 1.17);// choco 85%
		this.pourcentageTransfo.get(Feve.FEVE_HAUTE_BIO_EQUITABLE).put(Chocolat.HQ_BE, 1.17);// choco 85%
		this.pourcentageTransfo.put(Feve.FEVE_HAUTE, new HashMap<Chocolat, Double>());
		this.pourcentageTransfo.get(Feve.FEVE_HAUTE).put(Chocolat.HQ_O, 1.33);// choco 75%
		this.pourcentageTransfo.get(Feve.FEVE_HAUTE).put(Chocolat.HQ, 1.33);// choco 75%
		this.pourcentageTransfo.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE, new HashMap<Chocolat, Double>());
		this.pourcentageTransfo.get(Feve.FEVE_MOYENNE_BIO_EQUITABLE).put(Chocolat.MQ_BE_O, 1.54);// choco 65%
		this.pourcentageTransfo.get(Feve.FEVE_MOYENNE_BIO_EQUITABLE).put(Chocolat.MQ_BE, 1.54);// choco 65%
		this.pourcentageTransfo.put(Feve.FEVE_MOYENNE, new HashMap<Chocolat, Double>());
		this.pourcentageTransfo.get(Feve.FEVE_MOYENNE).put(Chocolat.MQ_O, 1.67);// choco 60%
		this.pourcentageTransfo.get(Feve.FEVE_MOYENNE).put(Chocolat.MQ, 1.67);// choco 60%
		this.pourcentageTransfo.put(Feve.FEVE_BASSE, new HashMap<Chocolat, Double>());
		this.pourcentageTransfo.get(Feve.FEVE_BASSE).put(Chocolat.BQ_O, 1.82);// choco 55%
		this.pourcentageTransfo.get(Feve.FEVE_BASSE).put(Chocolat.BQ, 1.82);// choco 55%
		this.superviseurVentesAO = (SuperviseurVentesAO)(Filiere.LA_FILIERE.getActeur("Sup.AO"));
	}

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}

	public void next() {
		this.journal.ajouter("=== STOCKS === ");
		for (Feve f : this.lesFeves) {
			this.journal.ajouter(COLOR_LLGRAY, COLOR_BROWN,"Stock de "+Journal.texteSurUneLargeurDe(f+"", 15)+" = "+this.stockFeves.get(f));
		}
		for (Chocolat c : Chocolat.values()) {
			this.journal.ajouter(COLOR_LLGRAY, COLOR_BROWN,"Stock de "+Journal.texteSurUneLargeurDe(c+"", 15)+" = "+this.stockChoco.get(c));
		}
		if (this.stockChocoMarque.keySet().size()>0) {
			for (ChocolatDeMarque cm : this.stockChocoMarque.keySet()) {
				this.journal.ajouter(COLOR_LLGRAY, COLOR_BROWN,"Stock de "+Journal.texteSurUneLargeurDe(cm+"", 15)+" = "+this.stockChocoMarque.get(cm));
			}
		}
		for (Feve f : this.pourcentageTransfo.keySet()) {
			for (Chocolat c : this.pourcentageTransfo.get(f).keySet()) {
				int transfo = (int) (Math.min(this.stockFeves.get(f), Math.random()*30));
				if (transfo>0) {
					this.stockFeves.put(f, this.stockFeves.get(f)-transfo);
					this.totalStocksFeves.retirer(this, transfo, this.cryptogramme);
					this.stockChoco.put(c, this.stockChoco.get(c)+(transfo*this.pourcentageTransfo.get(f).get(c)));
					this.totalStocksChoco.ajouter(this, (transfo*this.pourcentageTransfo.get(f).get(c)), this.cryptogramme);
					this.journal.ajouter(COLOR_LLGRAY, Color.PINK, "Transfo de "+(transfo<10?" "+transfo:transfo)+" Kg de "+f+" en "+Journal.doubleSur(transfo*this.pourcentageTransfo.get(f).get(c),3,2)+" Kg de "+c);
					this.journal.ajouter(COLOR_LLGRAY, COLOR_BROWN," stock("+f+")->"+this.stockFeves.get(f));
					this.journal.ajouter(COLOR_LLGRAY, COLOR_BROWN," stock("+c+")->"+this.stockChoco.get(c));
				}
			}
		}
		boolean tg = Math.random()*100.0 >=50.0 ? true : false;
		List<Chocolat> chocoEnStock = new LinkedList<Chocolat>();
		for (Chocolat c : this.stockChoco.keySet()) {
			if (this.stockChoco.get(c)>=250.0) {
				chocoEnStock.add(c);
			}
		}
		if (chocoEnStock.size()>0) {
			int alea = (int)(Math.random()*chocoEnStock.size());
			Chocolat cho = chocoEnStock.get(alea);
			ChocolatDeMarque choc = new ChocolatDeMarque(cho, "Villors");
			PropositionAchatAO propositionRetenue = this.superviseurVentesAO.vendreParAO(this, this.cryptogramme, choc, 250, tg);
			this.journal.ajouter(COLOR_LLGRAY, COLOR_GREEN, "   AOV : vente de 250Kg de "+propositionRetenue.getOffre().getChocolat()+" a "+propositionRetenue.getAcheteur().getNom());
			this.stockChoco.put(cho, this.stockChoco.get(cho)-250.0);
			this.journal.ajouter(COLOR_LLGRAY, COLOR_GREEN, "   AOV : stock("+cho+") -->"+this.stockChoco.get(cho));
		}
	}

	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		filieres.add("TESTCLIENT"); 
		filieres.add("TESTCC"); 
		filieres.add("TESTBOURSE"); 
		filieres.add("TESTAO"); 
		return filieres;
	}

	public Filiere getFiliere(String nom) {
		switch (nom) { 
		case "TESTCLIENT" : return new FiliereTestClientFinal();
		case "TESTCC" : return new FiliereTestContratCadre();
		case "TESTBOURSE" : return new FiliereTestBourse();
		case "TESTAO" : return new FiliereTestAO();
		default : return null;
		}
	}

	public List<Variable> getIndicateurs() {
		List<Variable> res =  new ArrayList<Variable>();
		res.add(this.totalStocksFeves);
		res.add(this.totalStocksChoco);
		return res;
	}

	public List<Variable> getParametres() {
		List<Variable> p= new ArrayList<Variable>();
		p.add(this.qualiteHaute);
		p.add(this.qualiteMoyenne);
		p.add(this.qualiteBasse);
		p.add(this.gainQualiteBioEquitable);
		p.add(this.gainQualiteOriginal); 
		p.add(this.partDeLaMarqueDansLaQualitePercu);
		return p;
	}

	public List<Journal> getJournaux() {
		List<Journal> res = new LinkedList<Journal>();
		res.add(this.journal);
		return res;
	}

	public void notificationFaillite(IActeur acteur) {
		if (this==acteur) {
			System.out.println("They killed Romu... ");
		} else {
			System.out.println("Poor "+acteur.getNom()+"... We will miss you. "+this.getNom());
		}
	}

	public void notificationOperationBancaire(double montant) {
	}
	//========================================================
	//                      VENTE EN BOURSE
	//========================================================

	public double offre(Feve f, double cours) {
		double offre =  Math.min(this.stockFeves.get(f), Math.random()*50.0);
		this.journal.ajouter(COLOR_LLGRAY, COLOR_PURPLE,"   BOURSEV: offre "+offre+" Kg de "+f+" en bourse ");
		return offre;
	}

	public void notificationVente(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		this.stockFeves.put(f,  this.stockFeves.get(f) - quantiteEnKg);
		this.totalStocksFeves.retirer(this, quantiteEnKg, this.cryptogramme);
		this.journal.ajouter(COLOR_LLGRAY, COLOR_PURPLE,"   BOURSEV: vente de "+quantiteEnKg+" kg de "+f+" en bourse. Stock -> "+this.stockFeves.get(f));
	}

	//========================================================
	//                    ACHATS EN BOURSE
	//========================================================

	public double demande(Feve f, double cours) {
		double solde = Filiere.LA_FILIERE.getBanque().getSolde(this, this.cryptogramme);
		double demande = Math.max(0, Math.min( Math.random()*50, solde));
		this.journal.ajouter(COLOR_LLGRAY, COLOR_LPURPLE,"   BOURSEA: demande en bourse de "+demande+" de "+f);
		return demande;
	}

	public void notificationAchat(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		this.stockFeves.put(f,  this.stockFeves.get(f) + quantiteEnKg);
		this.totalStocksFeves.ajouter(this, quantiteEnKg, this.cryptogramme);
		this.journal.ajouter(COLOR_LLGRAY, COLOR_LPURPLE,"   BOURSEA: obtenu "+quantiteEnKg+" kg de "+f+" en bourse. Stock -> "+this.stockFeves.get(f));
	}

	public void notificationBlackList(int dureeEnStep) {
		this.journal.ajouter(" aie aie aie ... blackliste de la bourse pendant "+dureeEnStep+" tour");
	}

	//========================================================
	//                    IMarqueChocolat
	//========================================================

	public List<String> getMarquesChocolat() {
		LinkedList<String> marques = new LinkedList<String>();
		marques.add("Villors");
		return marques;
	}

	//========================================================
	//               FabricantChocolatDeMarque
	//========================================================

	public List<ChocolatDeMarque> getChocolatsProduits() {
		if (this.chocosProduits.size()==0) {
			for (Chocolat c : Chocolat.values()) {
				this.chocosProduits.add(new ChocolatDeMarque(c, "Villors"));
			}
		}
		return this.chocosProduits;
	}

	//========================================================
	//                        IVendeurAO
	//========================================================
	public PropositionAchatAO choisir(List<PropositionAchatAO> propositions) {
		this.journal.ajouter(COLOR_LLGRAY, COLOR_GREEN, "   AOV : propositions recues : "+propositions);
		return propositions.size()>0 ? propositions.get(0) : null;
	}

	//========================================================
	//                        IAcheteurAO
	//========================================================
	public double proposerPrix(OffreVente offre) {
		Chocolat c = offre.getChocolat().getChocolat();
		double prix=0.0;
		switch (c) {
		case HQ_BE_O : prix= 14.0;break;
		case HQ_BE   : prix= 13.0;break;
		case HQ_O    : prix= 12.0;break;
		case HQ      : prix= 11.0;break;
		case MQ_BE_O : prix= 10.0;break;
		case MQ_BE   : prix=  9.0;break;
		case MQ_O    : prix=  8.5;break;
		case MQ      : prix=  8.0;break;
		case BQ_O    : prix=  7.5;break;
		case BQ      : prix=  7.0;break;
		}
		double solde = Filiere.LA_FILIERE.getBanque().getSolde(this, this.cryptogramme);
		double res =  prix>0 && solde> offre.getQuantiteKG()*prix ? prix : 0;
		this.journal.ajouter(COLOR_LLGRAY, COLOR_LGREEN, "   AOA : propose prix de "+res+" pour chocolat "+c+ " (solde = "+solde+")");
		return res;
	}

	public void notifierAchatAO(PropositionAchatAO propositionRetenue) {
		ChocolatDeMarque cm = propositionRetenue.getOffre().getChocolat();
		this.journal.ajouter(COLOR_LLGRAY, COLOR_LGREEN, "   AOA : offre retenue de "+propositionRetenue.getOffre().getQuantiteKG()+"Kg de "+cm);
		if (this.stockChocoMarque.keySet().contains(cm)) {
			this.stockChocoMarque.put(cm, this.stockChocoMarque.get(cm)+propositionRetenue.getOffre().getQuantiteKG());
		} else {
			this.stockChocoMarque.put(cm, propositionRetenue.getOffre().getQuantiteKG());
		}
		this.totalStocksChocoMarque.ajouter(this, propositionRetenue.getOffre().getQuantiteKG(), this.cryptogramme);
		this.journal.ajouter(COLOR_LLGRAY, COLOR_LGREEN, "   AOA : -> stock("+cm+") = "+this.stockChocoMarque.get(cm));
	}

	public void notifierPropositionNonRetenueAO(PropositionAchatAO propositionNonRetenue) {
		this.journal.ajouter(COLOR_LLGRAY, COLOR_LGREEN, "   AOA : offre non retenue ");
	}

}
