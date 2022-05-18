package abstraction.eq3Transformateur1;
import java.util.ArrayList;
import java.util.List;




import abstraction.eq8Romu.produits.Gamme;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq1Producteur1.Producteur1;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.filiere.IFabricantChocolatDeMarque;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.filiere.IMarqueChocolat;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

import java.util.HashMap;
import java.util.LinkedList;

import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.ChocolatDeMarque;


public class Transformateur1 extends Transformateur1AppelsOffres implements IMarqueChocolat, IFabricantChocolatDeMarque{
	private static final double rendementHaute=Filiere.LA_FILIERE.getParametre("rendement").getValeur();                  /** rendement de la transformation haute à définir*/
	private static final double coutTransfo=Filiere.LA_FILIERE.getParametre("coutTransformateur").getValeur();                     /** rappel : seul le rendement varie entre la trasnforamtion haute et celle basse ; à remplacer par this.Filiere.LA_FILIERE.getIndicateurs(coutTransfo)*/
	private static final double coutTransfoOriginal=coutTransfo
			+ Filiere.LA_FILIERE.getParametre("coutOrginial").getValeur(); /** somme de couTransfo et du supplément pour l'original*/
	private static final double coutStockage=4*Filiere.LA_FILIERE.getParametre("Prix Stockage").getValeur();                  /** coutStockageTransfo = 4*coutStockageProd */


	public Transformateur1() { 
		super();
		stockFeve = new DicoFeve();
		stockChoco = new DicoChoco();
		
		for (Feve f : Feve.values()) {
			stockFeve.put(f, 10000.);
		}
		for (Chocolat c : Chocolat.values()) {
			stockChoco.put(c, 0.);
		}
	}

	/**
	 * @return the stockFeve
	 * Alexandre
	 */
	public HashMap<Feve, Double> getStockFeve() {
		return stockFeve;
	}
	/** getter dernierPrixVenteChoco*/
	public dernierPrixVenteChoco getDernierPrixVenteChoco() {
		return this.dernierPrixVenteChoco;
	}

	/** détermine le prix d'achat max; pas de prise en compte du rendement auteur Julien  */
	public void prixMaxAchat() {		
			prixAchatFeve.put(Feve.FEVE_BASSE, Math.min(dernierPrixVenteChoco.getPrix("distributeur1", Chocolat.MQ), dernierPrixVenteChoco.getPrix("distributeur2", Chocolat.MQ)) - coutTransfo);	
			prixAchatFeve.put(Feve.FEVE_MOYENNE,Math.min(dernierPrixVenteChoco.getPrix("distributeur1", Chocolat.MQ), dernierPrixVenteChoco.getPrix("distributeur2", Chocolat.MQ)) - coutTransfo);
			prixAchatFeve.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE,Math.min(dernierPrixVenteChoco.getPrix("distributeur1", Chocolat.MQ_BE), dernierPrixVenteChoco.getPrix("distributeur2", Chocolat.MQ_BE)) - coutTransfo);
	}
	
	/** détermine la quantité de fèves totale qu'on souhaite avoir cette étape ; auteur Julien
	 *  il faudrait prendre en compte stockFeve car il n'est pas toujours vide si on n'avait pas les fonds pour tout transformer */
	public void determinationQuantiteAchat() {		
		quantiteAchatFeve.put(Feve.FEVE_BASSE,((quantiteDemandeeChoco.get(Chocolat.MQ)-stockChoco.get(Chocolat.MQ))/2)); 	
		quantiteAchatFeve.put(Feve.FEVE_MOYENNE,((quantiteDemandeeChoco.get(Chocolat.MQ)-stockChoco.get(Chocolat.MQ))/2));
		quantiteAchatFeve.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE,(quantiteDemandeeChoco.get(Chocolat.MQ_BE)-stockChoco.get(Chocolat.MQ_BE)));
	}
	
	/** _______________________________________________LOT TRANSFORMATION DES FEVES ____________________________________________________________*/
	
	/** détermine la quantité à transformer 
	 * Alexandre */ 
	public double transfoQt(double stockFeveQt) {
		return stockFeveQt ;
	}
	
	/** détermine le type de transformation
	 *  on note que le type de transfromation influe sur la qualite du chocolat.
	 *  Il est donc independant du BE
	 *  Alexandre */
	public String choixTypeTransfo(Gamme stockFeveType) {
		if (stockFeveType.equals(Gamme.BASSE)) {
			return "transfoHaute" ;
		} /** else if(stockFeveType.equals(Gamme.MOYENNE) // stockFeveType.equals(Gamme.MOYENNE)){
			
		} */
		return "transfoBasse";
	}
	
	/** calcule le coût de la transformation et la quantité de chocolat produite
	 * Alexandre */
	public ArrayList<Double> coutQuantiteTransfo(String typeTransfo, double quantiteFeve, boolean original) {
		ArrayList<Double> prixQuantite = new ArrayList<Double>();
		if (original) {
			prixQuantite.add(quantiteFeve*coutTransfoOriginal);
		} else {
			prixQuantite.add(quantiteFeve*coutTransfo);
		}
		if (typeTransfo.contentEquals("transfoHaute")) {
			prixQuantite.add(quantiteFeve*rendementHaute);
			return prixQuantite;
		}
		prixQuantite.add(quantiteFeve);
		return prixQuantite;
	}
	
	/** modifie les stocks suite à la transformation 
	 *  (penser à ajouter l'exception si quantitefeve>quantiteStockFeve) 
	 *  doit modifier le solde pour payer le cout de transfo
	 *  Alexandre */
	public void transfo(double quantiteFeveTransformee, Feve feve, boolean original) {
		for (Feve f : stockFeve.keySet()) {
			if (f == feve) {
				stockFeve.put(feve, stockFeve.get(feve)-quantiteFeveTransformee);
			}
		}
		ArrayList<Double> coutQuantiteTransfo = this.coutQuantiteTransfo(this.choixTypeTransfo(feve.getGamme()), quantiteFeveTransformee, original);
		for (Chocolat c : stockChoco.keySet()) {
			if (c.getGamme()==Gamme.MOYENNE) {
				if ( c.isBioEquitable()==feve.isBioEquitable() && c.isOriginal()==original ) {
					stockChoco.put(c, stockChoco.get(c)-coutQuantiteTransfo.get(1));
				}
			}
		}
		// on paie le cout de transformation
		if (coutQuantiteTransfo.get(0)>0.) {
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getActeur("Banque"), coutQuantiteTransfo.get(0));
		}
	}
	
	
	
	/** _________________________________________________GESTION DES STOCKS______________________________________________________
	 *  pas de péremption en V1 */
	
	/** Calcule le coût de stockage pour le tour (à exécuter en fin de tour)
	 *  Alexandre */
	public double coutStockage() {
		double cout = 0.;
		for (Feve f : stockFeve.keySet()) {
			cout = cout + stockFeve.get(f)*coutTransfo;
		}
		for (Chocolat c : stockChoco.keySet()) {
			cout = cout + stockChoco.get(c)*coutStockage;
		}
		return cout;
	}
	
	/** Modifie le solde pour correspondre a la depense liee aux frais de stockage
	 *  Alexandre*/
	public void payerStockage(double coutStockage) {
		if(coutStockage>0.) {
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getActeur("Banque"), coutStockage);
		}
	}
	
	/** _________________________________________________VENTE DE CHOCOLAT_______________________________________________________*/
	
	/** Détermine le prix de vente minimum
	 *  Alexandre*/
	public void prixVenteMin() {
		double prixFeve = Math.max(prixAchatFeve.get(Feve.FEVE_BASSE), prixAchatFeve.get(Feve.FEVE_MOYENNE));
		double prixFeveBio = prixAchatFeve.get(Feve.FEVE_MOYENNE_BIO_EQUITABLE);
		for (Chocolat c : Chocolat.values()) {
			prixVenteMin.put(c, null);
			if (c.getGamme() == Gamme.MOYENNE) {
				if (c.isBioEquitable()) {
					prixVenteMin.put(c, prixFeveBio+coutTransfo);
				} else if (c.isOriginal()) {
					prixVenteMin.put(c, prixFeve+coutTransfoOriginal);
				} else {
					prixVenteMin.put(c, prixFeve+coutTransfo);
				}
			}
		}
	}
	
	/**  _________________________________________________TOUR ET INITIALISATION_________________________________________________*/
	
	/** Alexandre*/
	public void initialiser() {
		super.initialiser();
	}
	
	/** 
	 *  Alexandre*/
	public void next() {
		super.next();
		
		/** ____________________MAJ de variabales au debut / Initialisation____________________
		 *  dernierPrixVenteChoco*/
		
		/** MISE A JOUR DE dernierPrixVenteChoco 
		 *  doit se faire au debut car elle prend en compte toutes les ventes du tour precedent.
		 *  Or, le next() du tour precedent se passe avant les CC avec les distrib*/
		// les maj de dernierPrixVenteChocoReset se font dans les notifications
		
		// fusion de l'ancinne liste dernierPrixVenteChoco avec la nouvelle denierPrixVenteChocoReset
		for (String distrib : dernierPrixVenteChoco.getDistributeurs()) {
			for (Chocolat c : dernierPrixVenteChoco.getChocolats()) {
				/** si aucune vente de ce chocolat avec ce distributeur n'a ete effectuee, on garde les valeurs precedentes 
				 *  au sinon, on ecrase*/
				if (Double.compare(dernierPrixVenteChoco.getPrix(distrib, c), 0.) != 0) {
					dernierPrixVenteChoco.setPrix(distrib, c, dernierPrixVenteChocoReset.getPrix(distrib, c));
				}
			}
		}
		
		// reset liste des ventes du tour precedent
		dernierPrixVenteChocoReset = new dernierPrixVenteChoco();
		
		
		/** ____________________Choix quantite et prix Feve____________________ */
		
		this.prixMaxAchat();
		this.determinationQuantiteAchat();
		
		
		/** ____________________Contrat cadre Feve eventuel____________________*/
		
		double quantiteFeveContrat = 0. ;
		for (Feve f : Feve.values()) {
			
			// calcul de la quantite de feves qui proviennent de contrat deja existant
			for (ExemplaireContratCadre c : this.mesContratEnTantQueAcheteur) { 
				if (f == c.getProduit()) {
					quantiteFeveContrat = quantiteFeveContrat + c.getQuantiteALivrerAuStep();
				}
			}
			// choisit le producteur de feve selon le type de feve desire
			if (f == Feve.FEVE_BASSE || f == Feve.FEVE_MOYENNE) {  // B et M => prod1
				// desire t on un CC ?
				if (this.achete(f)) {
					// creation de l'echeancier
					Echeancier echeancier = new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 5, this.quantiteAchatFeve.get(f)*0.5-quantiteFeveContrat);
					// initiation d'un contrat cadre avec producteur 1
					((SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre"))).demandeAcheteur(
							(IAcheteurContratCadre)this, 
							(IVendeurContratCadre)Filiere.LA_FILIERE.getActeur("EQ1"), 
							f, 
							echeancier, 
							cryptogramme, 
							false);
				}
			} else if (f == Feve.FEVE_MOYENNE_BIO_EQUITABLE) { // BE => prod 2
				// desire t on un CC  ?
				if (this.achete(f)) {
					// creation de l'echeancier
					Echeancier echeancier = new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 5, this.quantiteAchatFeve.get(f)*0.5-quantiteFeveContrat);
					// initiation d'un contrat cadre avec producteur 2
					((SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre"))).demandeAcheteur(
							(IAcheteurContratCadre)this, 
							(IVendeurContratCadre)Filiere.LA_FILIERE.getActeur("EQ2"), 
							f, 
							echeancier, 
							cryptogramme, 
							false);
				}
			}
		}
		
		
		/** ____________________Transformation____________________
		 *  on transforme tout nos stocks puisque 1kg de chocolat coute aussi chère a stocker qu1 kg de feve
		 *  et que le rendement de la transformation ne peut etre qu'inferieur ou egal a 1*/
		
		for (Feve f : stockFeve.keySet()) {
			
			// on ne transforme que ces types de feves pour obtenir les chocolats qu'on a prevu de produire
			if (f == Feve.FEVE_BASSE || f == Feve.FEVE_MOYENNE || f == Feve.FEVE_MOYENNE_BIO_EQUITABLE) {
				
				// on determine la quantite de ce type de feve a transformer
				double quantiteATransformer = this.transfoQt(stockFeve.get(f));
				
				// on calcule le cout et la quantite de chocolat obtenu
				if (f.isBioEquitable()) {
					ArrayList<Double> prixQtBE = this.coutQuantiteTransfo(
							this.choixTypeTransfo(f.getGamme()), 
							quantiteATransformer, 
							false);
					
					// on verifie qu'on a l'argent pour payer la transformation
					// defaut de cette condition : il peut arriver qu'on ait assez pour transformer mais plus assez pour ensuite stocker
					if (prixQtBE.get(0) < this.getSolde() ) { 
						this.transfo(quantiteATransformer, f, false);
					}
				} else {
					ArrayList<Double> prixQtO = this.coutQuantiteTransfo(this.choixTypeTransfo(f.getGamme()), 
							0.25*quantiteATransformer, 
							true);                                // 20% de choco M original
					ArrayList<Double> prixQtSt = this.coutQuantiteTransfo(this.choixTypeTransfo(f.getGamme()), 
							0.75*quantiteATransformer, 
							false);                               // 60% de choco M standard
					
					// on verifie qu'on a l'argent pour payer la transformation
					// defaut de cette condition : il peut arriver qu'on ait assez pour transformer mais plus assez pour ensuite stocker
					if (prixQtO.get(0) + prixQtSt.get(0) < this.getSolde() ) {
						this.transfo(0.25*quantiteATransformer, f, true);            // 20% de choco M original
						this.transfo(0.75*quantiteATransformer, f, false);           // 60% de choco M standard
					}
				}
				
				
			}
		}
		
		
		/** ____________________Choix prix de Chocolat____________________*/
		
		this.prixVenteMin();
		
		
		/** ____________________Appels d'offres____________________
		 *  Ilyas puis modif apportées par Alexandre*/
		
		if (Filiere.LA_FILIERE.getEtape()>=1) {
			for (Chocolat c : stockChoco.keySet()) {
				// calcule la quantite qu'on doit livrer de ce chocolat
				double aLivrer =0.;
				for (ExemplaireContratCadre cc : this.mesContratEnTantQueVendeur) {
					if (cc.getProduit() instanceof ChocolatDeMarque && ((ChocolatDeMarque)cc.getProduit()).getChocolat()==c) {
						aLivrer = aLivrer + cc.getQuantiteALivrerAuStep();
					}
				}
				// determine si on lance un appel d'offre ou non
				if (stockChoco.get(c)>=aLivrer + 250) {
					// on vend notre surplus de chocolat
					ChocolatDeMarque coco= new ChocolatDeMarque(c, "cote d'or");
					double stockDispo= stockChoco.get(c) - aLivrer;
					PropositionAchatAO retenue = superviseurAO.vendreParAO(this, cryptogramme, coco, stockDispo, false);
					stockChoco.put(c, stockChoco.get(c)-retenue.getOffre().getQuantiteKG());	
				}
				
			}
		}
		
		/** ____________________Stocks____________________*/
		
		this.payerStockage(this.coutStockage());
		
		
		/** ____________________Mise a jour des variables decisionnelles a la fin____________________
		 *  quantiteDemandeeChoco 
		 *  pour le moment, je ne prends pas en compte les appels d'offre */
		
		// MSIE A JOUR DE quantiteDemandeeChoco
		
		// calcule la quantite demandee au tour precedent par chocolat
		for (Chocolat c : quantiteDemandeeChoco.keySet()) {
			double qt = 0.;
			
			// demande provenant des contrats cadre
			for (ExemplaireContratCadre cc : mesContratEnTantQueVendeur) {
				qt = qt + cc.getQuantiteALivrerAuStep();
			}
			
			// demande provenant des appels d'offres                                          a completer
			
			// mise a jour de la qt demandee pour le choco c
			quantiteDemandeeChoco.put(c, qt);
		}
		
		
		
	}
	

	// donne nos marques de chocolat, auteur Julien */
	public List<String> getMarquesChocolat() {
		LinkedList<String> result = new LinkedList<String>();
		result.add("cote d'or");
		return result;
	}
	
	// déclaration de nos chocolats */
	private ChocolatDeMarque moyen = new ChocolatDeMarque(Chocolat.MQ, "cote d'or");
	private ChocolatDeMarque moyen_bio = new ChocolatDeMarque(Chocolat.MQ_BE, "cote d'or");
	private ChocolatDeMarque moyen_original = new ChocolatDeMarque(Chocolat.MQ_O, "cote d'or");
	
	// donne nos chocolats produits, auteur Julien */
	public List<ChocolatDeMarque> getChocolatsProduits() {
		LinkedList<ChocolatDeMarque> result = new LinkedList<ChocolatDeMarque>();
		result.add(moyen);
		result.add(moyen_bio);
		result.add(moyen_original);
		return result;
	}

}
