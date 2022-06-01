package abstraction.eq7Distributeur2;

import java.util.LinkedList;
import java.util.List;
import java.awt.Color;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

//Classe rédigée par Edgar et Matteo
public class Distributeur2Achat extends Distributeur2Acteur implements IAcheteurContratCadre{
	public static final int EPS_ECH_OK=2;
	public static final int ECH_MAX=15;
	public static final Double PRIX_MAX=100.0;
	public static final Double PRIX_OK=50.0;
	public static final Double EPSILON_PRIX=5.0;
	
	protected List<ExemplaireContratCadre> mesContratEnTantQuAcheteur;
	
	public Distributeur2Achat() {
		super();
		this.mesContratEnTantQuAcheteur = new LinkedList<ExemplaireContratCadre>();
	}
	
	
	//A chaque étape, on créer un contrat cadre pour acheter un produit dont le stock est inférieur au seuil
	//On réalise alors des contrats avec tous les vendeurs qui le propose afin de voir quel est leur prix
	//On compare ces prixs et on réalise finalement le contrat avec le meilleur vendeur.
	//On réalise une moyenne des achats du client final sur les 6 steps précédents pour determiner la quantité achetée par step
	//On compare notre stock au seuil limite pour determiner la quantité à acheter pour remettre au seuil
	//Pour qu'un produit soit en boolTeteGondole, il doit être bioéquitable
	//On réalise un contrat pour chacun des différents chocolatss produits afin de tous les avoir en stock
	//Finalement, on fait un contrat avec le vendeur le plus offrant
	@Override
	public void next() {
		super.next();
		
		//-------------------------------------NEXT CONTRAT------------------------------------------//
		//Initialisation du superviseur de vente
		SuperviseurVentesContratCadre SupVente = ((SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre")));
		
		//Affichage de la liste des vendeurs actifs pour chaque chocolats
		for (ChocolatDeMarque chocProduit : Filiere.LA_FILIERE.getChocolatsProduits()) {
			List<IVendeurContratCadre> vendeurs = SupVente.getVendeurs(chocProduit);
			this.journalContratCadre.ajouter("Liste des vendeurs disponnible pour le produit : "+chocProduit+" "+vendeurs);
		}
		journalContratCadre.ajouter("================================================================================");
		
		
		//Pour chaque chocolat produit sur le marché
		for (ChocolatDeMarque chocProduit : Filiere.LA_FILIERE.getChocolatsProduits()) {
			
			//On pose le chocolat en tête de gondole si il est bio et équitable
			boolean boolTeteGondole = chocProduit.isBioEquitable();
			
			int currentEtape = Filiere.LA_FILIERE.getEtape();
			
			//Si la quantite du chocolat en question est inférieure au seuil auquel on a décidé d'en racheter, alors on va en racheter
			if (stock.getQuantite(chocProduit)<=stock.getSeuilRachat(chocProduit)) {
				
				int nbStepContrat = 10;
				//Retourne le volume le plus judicieux à acheter selon le nombre d'étape sur lequel on reparti le contrat
				double venteParStep = this.volumeParEtapeMoyenne(chocProduit, currentEtape, nbStepContrat);
				
				//On créer un écheancier correspondant à nos besoin
				Echeancier echeancierAchat = new Echeancier(currentEtape+1,nbStepContrat,venteParStep);
				
				//On récupère tout les vendeurs actifs
				List<IVendeurContratCadre> vendeurs = SupVente.getVendeurs(chocProduit);
				
				//Pour chaque vendeur
				for (IVendeurContratCadre vendeur : vendeurs) {
					journalContratCadre.ajouter(this.getNom() + " propose d'initier un CC avec "+ Journal.texteColore(vendeur, vendeur.getNom()) +" avec le produit: "+chocProduit);
					journalContratCadre.ajouter("Echeancier : "+echeancierAchat.toString());
					ExemplaireContratCadre propositionContratCadre = SupVente.demandeAcheteur(this, vendeur, chocProduit, echeancierAchat, cryptogramme,boolTeteGondole);
					if (propositionContratCadre == null) {
						journalContratCadre.ajouter(Journal.texteColore(getColorFaillure(), Color.BLACK, "Le contrat avec "+vendeur.getNom()+" n'a pas abouti"));
						journalContratCadre.ajouter("================================================================================");
					}
				}
			}
		}
	}
	
	public double volumeParEtapeMoyenne(ChocolatDeMarque chocProduit,int currentEtape,int nbEtape) {
		double ventes = 0.0;
		//On ajoute les quantités vendues à chaque étape depuis nbStep
		
		for (int j=currentEtape - nbEtape; j<currentEtape; j++) {
			ventes+=Filiere.LA_FILIERE.getVentes(chocProduit, j);
			this.journalEtudeVente.ajouter("Vente à l'Etape "+ j + " de chocolat "+ chocProduit+ " : " +ventes);
		}
		this.journalEtudeVente.ajouter("==========================================");
		double judicieux = ventes/(10*nbEtape);
		
		//En attente de résolution d'un bug de Romu
		if (judicieux<=1000) {
			return 1001;
		}
		else {
			return judicieux;
		}
		
	}
	

	@Override
	public boolean achete(Object produit) {
		if (produit instanceof ChocolatDeMarque && stock.getQuantite((ChocolatDeMarque)produit)<stock.getSeuilRachat((ChocolatDeMarque)produit)) {
			return true;
		}else {
			return false;
		}
		//return (produit!=null && (produit instanceof ChocolatDeMarque) && this.chocolats.contains(produit));
	}

	//edgard: On vérifie que l'echeancier nous fournit une quantite > Quantite Min avant de valider le contrat
	@Override
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat){
		//On récupère le superviseur des ventes et la quantité min echeancier
		double QuantiteMinEcheancier= SuperviseurVentesContratCadre.QUANTITE_MIN_ECHEANCIER;
		
		//On récupère le dernier écheancier négocié
		Echeancier lastEcheancier = contrat.getEcheancier();
		
		//On récupère le chocolat concerné
		ChocolatDeMarque chocProduit = (ChocolatDeMarque)contrat.getProduit();
		
		//L'étape actuelle
		int currentEtape = Filiere.LA_FILIERE.getEtape();
		

		//Retourne le volume le plus judicieux à acheter selon le nombre d'étape sur lequel on reparti le contrat
		double venteParStep = this.volumeParEtapeMoyenne(chocProduit, currentEtape, 10);
		int nbStepContrat = 10;
		double quantiteTotale = venteParStep*nbStepContrat;

		//On ne va pas réaliser de CC si la quantite achetée est < Quantite Min
		while(quantiteTotale<QuantiteMinEcheancier) {
			venteParStep+=10;
		}
		//On créer un écheancier correspondant à nos besoin
		Echeancier echeancierAchat = new Echeancier(currentEtape,nbStepContrat,venteParStep);
		if (lastEcheancier.getStepFin()>ECH_MAX) {
			return null;
		}else {
			return echeancierAchat;
		}
	}

	@Override
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		double prix = contrat.getPrix();
		if (prix>PRIX_MAX) {
			return -1;
		}else {
			if(Math.abs(prix-EPSILON_PRIX)==PRIX_OK || prix<=PRIX_OK) {
				this.journalContratCadre.ajouter(Journal.texteColore(getColorSuccess(), Color.BLACK, "Contrat négocié : "));
				this.journalContratCadre.ajouter(contrat.toString());
				journalContratCadre.ajouter("================================================================================");
				return prix;
			}else {
				return prix-EPSILON_PRIX;
			}
		}
	}

	@Override
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		this.stock.addProduit((ChocolatDeMarque)produit, quantite);
		this.journalStock.ajouter("+ "+quantite+" kg -"+produit);
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		IVendeurContratCadre v = contrat.getVendeur();
		IAcheteurContratCadre a = contrat.getAcheteur();
		Echeancier currentEtape = contrat.getEcheancier();
		ChocolatDeMarque chocProduit = (ChocolatDeMarque) contrat.getProduit();
		Double q = contrat.getQuantiteTotale();
		this.journalContratCadre.ajouter("Nouveau contrat cadre entre "+ v + "et"+ a + "pour une quantitée" + q + "de" + chocProduit + "étalé sur " + currentEtape);
	}
}