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
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.FiliereTestContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
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

public class Romu implements IActeur, IVendeurBourse, IAcheteurBourse, IMarqueChocolat, IFabricantChocolatDeMarque, IVendeurAO, IAcheteurAO, IAcheteurContratCadre, IVendeurContratCadre {

	@SuppressWarnings("unused")
	public static Color COLOR_LLGRAY = new Color(238,238,238);
	public static Color COLOR_BROWN  = new Color(141,100,  7);
	public static Color COLOR_PURPLE = new Color(100, 10,115);
	public static Color COLOR_LPURPLE= new Color(155, 89,182);
	public static Color COLOR_GREEN  = new Color(  6,162, 37);
	public static Color COLOR_LGREEN = new Color(  6,255, 37);
	public static Color COLOR_LBLUE = new Color(  6,130,230);
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
	private SuperviseurVentesContratCadre superviseurVentesCC;
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
		this.superviseurVentesCC = (SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre"));
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

		// === Lancement si possible d'un appel d'offre
		boolean tg = false;//Math.random()*100.0 >=50.0 ? true : false;
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
			if (propositionRetenue!=null) {
				this.journal.ajouter(COLOR_LLGRAY, COLOR_GREEN, "   AOV : vente de 250Kg de "+propositionRetenue.getOffre().getChocolat()+" a "+propositionRetenue.getAcheteur().getNom());
				this.stockChoco.put(cho, this.stockChoco.get(cho)-250.0);
				this.journal.ajouter(COLOR_LLGRAY, COLOR_GREEN, "   AOV : stock("+cho+") -->"+this.stockChoco.get(cho));
			}
		}

		// === Lancement si possible d'un contrat cadre
		List<Object> produits = new LinkedList<Object>();
		produits.addAll(Filiere.LA_FILIERE.getChocolatsProduits());
		for (Feve f : Feve.values()) {
			produits.add(f);
		}
		for (Chocolat c : Chocolat.values()) {
			produits.add(c);
		}
		this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, " CCA : Tentative de lancer un contrat cadre");
		this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, " CCA : Liste de tous les produits "+produits);
		List<Object> produitsVendus = new LinkedList<Object>();
		List<Object> produits2Vendeurs = new LinkedList<Object>();
		for (Object prod : produits) {
			if (superviseurVentesCC.getVendeurs(prod).size()>0) {
				produitsVendus.add(prod);
				if (superviseurVentesCC.getVendeurs(prod).size()>1) {
					produits2Vendeurs.add(prod);
				}
			}
		}
		this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, " CCA : Liste de tous les produits pour lesquels il existe au moins 1 vendeur  "+produitsVendus);
		this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, " CCA : Liste de tous les produits pour lesquels il existe au moins 2 vendeurs "+produits2Vendeurs);
		if (produitsVendus.size()>0) {
			Object produit = produitsVendus.get((int)(Math.random()*produitsVendus.size()));
			this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, " CCA : Produit tire au sort = "+produit);
			List<IVendeurContratCadre> vendeurs = superviseurVentesCC.getVendeurs(produit);
			this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, " CCA : Les vendeurs de "+produit+" sont : "+vendeurs);
			if (vendeurs.size()>0) {
				IVendeurContratCadre vendeur = vendeurs.get((int)(Math.random()*vendeurs.size()));
				if (vendeur!=this) { // on ne peut pas passer de contrat avec soi meme
					this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, " CCA : Vendeur tire au sort = "+vendeur);
					Echeancier echeancier = new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, 100);
					ExemplaireContratCadre contrat = superviseurVentesCC.demandeAcheteur(this, vendeur, produit, echeancier, this.cryptogramme, false);
					if (contrat!=null) {
						this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, " CCA : contrat signe = "+contrat);
					}
				}
			}
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
		PropositionAchatAO retenue = propositions.size()>0 ? propositions.get(0) : null;
		int index=0;
		while (retenue!=null && index<propositions.size()-1 && retenue.getAcheteur()==this) {
			index++;
			retenue = propositions.get(index);
		}
		return retenue; 
	}

	//========================================================
	//                        IAcheteurAO
	//========================================================
	public double proposerPrix(OffreVente offre) {
		if (offre.getVendeur()==this) {
			return 0.0;
		}
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

	//========================================================
	//                  IAcheteurContratCadre
	//========================================================
	public boolean achete(Object produit) {
		this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, "  CCA : j'affirme acheter le produit "+produit);
		return true;
	}

	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, "  CCA : j'accepte l'echeancier "+contrat.getEcheancier());
		return contrat.getEcheancier();
	}

	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		double prix=0.0;
		double solde = Filiere.LA_FILIERE.getBanque().getSolde(this, this.cryptogramme);
		Object produit = contrat.getProduit();
		if (produit instanceof ChocolatDeMarque) {
			produit = ((ChocolatDeMarque)produit).getChocolat();
		}
		if (produit instanceof Chocolat) {
			switch ((Chocolat)produit) {
			case HQ_BE_O : prix= 12.0;break;
			case HQ_BE   : prix= 11.0;break;
			case HQ_O    : prix= 10.0;break;
			case HQ      : prix=  9.0;break;
			case MQ_BE_O : prix=  8.0;break;
			case MQ_BE   : prix=  7.0;break;
			case MQ_O    : prix=  6.5;break;
			case MQ      : prix=  6.0;break;
			case BQ_O    : prix=  5.5;break;
			case BQ      : prix=  5.0;break;
			}
		} else if (produit instanceof Feve) {
			switch ((Feve)produit) {
			case FEVE_HAUTE_BIO_EQUITABLE : prix= 3.5;break;
			case FEVE_HAUTE   : prix= 3.0;break;
			case FEVE_MOYENNE_BIO_EQUITABLE    : prix= 2.7;break;
			case FEVE_MOYENNE      : prix= 2.5;break;
			case FEVE_BASSE : prix= 1.5;break;
			}
		}
		while (prix*contrat.getQuantiteTotale()>(solde/10.0)) {
			prix = 0.75*prix;
		};
		prix = Math.min(prix, contrat.getPrix());
		this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, "  CCA : on me propose le prix "+contrat.getPrix()+" -> ma proposition ="+prix);
		return prix;
	}

	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, "  CCA : nouveau cc conclu "+contrat);
	}

	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		if (produit instanceof ChocolatDeMarque) {
			if (this.stockChocoMarque.keySet().contains(produit)) {
				this.stockChocoMarque.put((ChocolatDeMarque)produit, this.stockChocoMarque.get(produit)+quantite);
			} else {
				this.stockChocoMarque.put((ChocolatDeMarque)produit, quantite);
			}
			this.totalStocksChocoMarque.ajouter(this, quantite, this.cryptogramme);
			this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, "  CCA : reception "+quantite+" Kg de C.M. "+produit+". Stock->  "+this.stockChocoMarque.get(produit));
		} else if (produit instanceof Chocolat) {
			if (this.stockChoco.keySet().contains(produit)) {
				this.stockChoco.put((Chocolat)produit, this.stockChoco.get(produit)+quantite);
			} else {
				this.stockChoco.put((Chocolat)produit, quantite);
			}
			this.totalStocksChoco.ajouter(this, quantite, this.cryptogramme);
			this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, "  CCA : reception "+quantite+" Kg de Choco "+produit+". Stock->  "+this.stockChoco.get(produit));
		} else if (produit instanceof Feve) {
			if (this.stockFeves.keySet().contains(produit)) {
				this.stockFeves.put((Feve)produit, this.stockFeves.get(produit)+quantite);
			} else {
				this.stockFeves.put((Feve)produit, quantite);
			}
			this.totalStocksFeves.ajouter(this, quantite, this.cryptogramme);
			this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, "  CCA : reception "+quantite+" Kg de feves "+produit+". Stock->  "+this.stockFeves.get(produit));
		} else {
			this.journal.ajouter(COLOR_LLGRAY, Color.BLUE, "  CCA : reception d'un produit de type surprenant... "+produit);
		}
	}

	//========================================================
	//                  IVendeurContratCadre
	//========================================================

	public boolean vend(Object produit) {
		boolean res=false;
		if (produit instanceof ChocolatDeMarque) {
			res=this.stockChocoMarque.keySet().contains(produit) && this.stockChocoMarque.get(produit)>1000;
		} else if (produit instanceof Chocolat) {
			res=this.stockChoco.keySet().contains(produit) && this.stockChoco.get(produit)>1000;
		} else if (produit instanceof Feve) {
			res=this.stockFeves.keySet().contains(produit) && this.stockFeves.get(produit)>1000;
		} 
		this.journal.ajouter(COLOR_LLGRAY, COLOR_LBLUE, "  CCV : vend("+produit+") --> "+res);
		return res;
	}

	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		Object produit = contrat.getProduit();
		double qtok=0;
		this.journal.ajouter(COLOR_LLGRAY, COLOR_LBLUE, "  CCV : contrepropovend(prod="+produit+"  ech="+contrat.getEcheancier());

		if (produit instanceof ChocolatDeMarque) {
			if (this.stockChocoMarque.keySet().contains(produit)) {
				qtok= this.stockChocoMarque.get(produit);
			}
		} else if (produit instanceof Chocolat) {
			if (this.stockChoco.keySet().contains(produit)) {
				qtok= this.stockChoco.get(produit);
			}
		} else if (produit instanceof Feve) {
			if (this.stockFeves.keySet().contains(produit)) {
				qtok= this.stockFeves.get(produit);
			}
		} 
		if (qtok<1000.0) {
			qtok=0.0;
		} else {
			if (contrat.getEcheancier().getQuantiteTotale()<qtok) {
				this.journal.ajouter(COLOR_LLGRAY, COLOR_LBLUE, "  CCV : contrepropovend --> meme echeancier");
				return contrat.getEcheancier();
			} else {
				this.journal.ajouter(COLOR_LLGRAY, COLOR_LBLUE, "  CCV : contrepropovend --> nouvel echeancier="+new Echeancier(contrat.getEcheancier().getStepDebut(), 10, qtok/10.0));
				return new Echeancier(contrat.getEcheancier().getStepDebut(), 10, qtok/10.0);
			}
		}
		this.journal.ajouter(COLOR_LLGRAY, COLOR_LBLUE, "  CCV : contrepropovend --> return null");
		return null;
	}


	public double propositionPrix(ExemplaireContratCadre contrat) {
		double prix=0.0;
		Object produit = contrat.getProduit();
		if (produit instanceof ChocolatDeMarque) {
			produit = ((ChocolatDeMarque)produit).getChocolat();
		}
		if (produit instanceof Chocolat) {
			switch ((Chocolat)produit) {
			case HQ_BE_O : prix= 12.0;break;
			case HQ_BE   : prix= 11.0;break;
			case HQ_O    : prix= 10.0;break;
			case HQ      : prix=  9.0;break;
			case MQ_BE_O : prix=  8.0;break;
			case MQ_BE   : prix=  7.0;break;
			case MQ_O    : prix=  6.5;break;
			case MQ      : prix=  6.0;break;
			case BQ_O    : prix=  5.5;break;
			case BQ      : prix=  5.0;break;
			}
		} else if (produit instanceof Feve) {
			switch ((Feve)produit) {
			case FEVE_HAUTE_BIO_EQUITABLE : prix= 3.5;break;
			case FEVE_HAUTE   : prix= 3.0;break;
			case FEVE_MOYENNE_BIO_EQUITABLE    : prix= 2.7;break;
			case FEVE_MOYENNE      : prix= 2.5;break;
			case FEVE_BASSE : prix= 1.5;break;
			}
		}
		this.journal.ajouter(COLOR_LLGRAY, COLOR_LBLUE, "  CCV : propose prix de "+prix+" pour "+produit);
		return prix;
	}

	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		double prixInit=contrat.getListePrix().get(0);
		double prix = contrat.getPrix();
		if (prix>0.0 && (prixInit-prix)/prixInit<=0.05) {
			return prix;
		} else {
			return prixInit;
		}
	}

	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		double stock=0.0;
		double livre=0.0;
		if (produit instanceof ChocolatDeMarque) {
			if (this.stockChocoMarque.keySet().contains(produit)) {
				stock= this.stockChocoMarque.get(produit);
				livre = Math.min(stock, quantite);
				if (livre>0) {
					this.stockChocoMarque.put((ChocolatDeMarque)produit, this.stockChocoMarque.get(produit)-livre);
				}
			}
		} else if (produit instanceof Chocolat) {
			if (this.stockChoco.keySet().contains(produit)) {
				stock= this.stockChoco.get(produit);
				livre = Math.min(stock, quantite);
				if (livre>0) {
					this.stockChoco.put((Chocolat)produit, this.stockChoco.get(produit)-livre);
				}
			}
		} else if (produit instanceof Feve) {
			if (this.stockFeves.keySet().contains(produit)) {
				stock= this.stockFeves.get(produit);
				livre = Math.min(stock, quantite);
				if (livre>0) {
					this.stockFeves.put((Feve)produit, this.stockFeves.get(produit)-livre);
				}

			}
		} 
		this.journal.ajouter(COLOR_LLGRAY, COLOR_LBLUE, "  CCV : doit livrer "+quantite+" de "+produit+" --> livre "+livre);

		return livre;
	}

}
