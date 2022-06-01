package abstraction.eq7Distributeur2;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IDistributeurChocolatDeMarque;
import abstraction.eq8Romu.produits.ChocolatDeMarque;


public class Distributeur2ChocolatDeMarque extends Distributeur2Achat implements IDistributeurChocolatDeMarque  {
	
	private double capaciteDeVente= Double.MAX_VALUE;;

	public Distributeur2ChocolatDeMarque() {
		super();
	}
	
	public void next() {
		super.next();
		
		int currentEtape = Filiere.LA_FILIERE.getEtape();
		//Affichage état des stocks
		journalStock.ajouter("==========Etape "+currentEtape+"==========================");
		for (ChocolatDeMarque choco : this.chocolats) {
			double q = this.stock.getQuantite(choco);
			journalStock.ajouter(choco+" : "+ q+ " kg en stock");
		}
		journalStock.ajouter("===========================================");
	}
	
	
	//edgard: prix depend de la marque et du type de choco: Biofour et BE moins cher que le marché
	public double prix(ChocolatDeMarque choco) {
		if (choco.getMarque()=="Biofour" && choco.isBioEquitable()) {
			return 9.0;
		}else {
			return 10.0;
		}
	}

	public double quantiteEnVente(ChocolatDeMarque choco, int crypto) {
		if (crypto!=this.cryptogramme) {
			this.journal.ajouter("Quelqu'un essaye de me pirater !");
			return 0.0;
		} else {
			return Math.min(capaciteDeVente, this.stock.getQuantite(choco));
		}
	}

	public double quantiteEnVenteTG(ChocolatDeMarque choco, int crypto) {
		if(choco.isBioEquitable()) {
			if (crypto!=this.cryptogramme) {
				journal.ajouter("Quelqu'un essaye de me pirater !");
				return 0.0;
			} else {
				return Math.min(capaciteDeVente, this.stock.getQuantite(choco))/20;
			}
		}else {
			return 0.0;
		}
	}
	
	public void vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant, int crypto) {
		//Retire la quantité dans les stocks
		this.stock.remove(choco, quantite);
		
		//Ajout de la quantité vendue
		this.totalVente.ajouter(this, quantite);
		
		//Ajout d'une entrée dans le journal
		this.journalVente.ajouter("vente de "+quantite+" "+choco.name()+" a "+client.getNom()+" pour un prix de "+ montant);
	}

	public void notificationRayonVide(ChocolatDeMarque choco, int crypto) {
		if (crypto!=this.cryptogramme) {
			journal.ajouter("Quelqu'un essaye de me pirater !");
		} else {}
	}
}