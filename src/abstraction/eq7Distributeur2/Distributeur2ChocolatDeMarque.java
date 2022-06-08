package abstraction.eq7Distributeur2;


import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IDistributeurChocolatDeMarque;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Gamme;


public class Distributeur2ChocolatDeMarque extends Distributeur2Achat implements IDistributeurChocolatDeMarque  {
	
	private double capaciteDeVente= Double.MAX_VALUE;

//edgard: 
//======fixation des prix minimaux pour chaque chocolat en vue de potentielles réduction en fonction de la demande=======
	private double prixMinBQ = 7.0;
	private double prixMinBQ_O = 7.5;
	private double prixMinMQ = 8;
	private double prixMinMQ_O = 8.5;
	private double prixMinMQ_B = 9.5;
	private double prixMinMQ_B_O = 10.5;
	private double prixMinHQ = 11.5;
	private double prixMinHQ_O = 12;
	private double prixMinHQ_B = 13;
	private double prixMinHQ_B_O = 14;
	

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
	
	//edgard: prix depend de la marque et du type de choco
	public double prix(ChocolatDeMarque choco) {
		if(choco.getGamme()==Gamme.HAUTE && choco.isBioEquitable() && choco.isOriginal()) {
			return 15.0;
		}
		if(choco.getGamme()==Gamme.HAUTE && choco.isBioEquitable()) {
			return 14.0;
		}
		if(choco.getGamme()==Gamme.HAUTE && choco.isOriginal()) {
			return 13.0;
		}
		if(choco.getGamme()==Gamme.HAUTE) {
			return 12.5;
		}
		if(choco.getGamme()==Gamme.MOYENNE && choco.isBioEquitable() && choco.isOriginal()) {
			return 11.5;
		}
		if(choco.getGamme()==Gamme.MOYENNE && choco.isBioEquitable()) {
			return 10.5;
		}
		if(choco.getGamme()==Gamme.MOYENNE && choco.isOriginal()) {
			return 9.5;
		}
		if(choco.getGamme()==Gamme.MOYENNE) {
			return 9.0;
		}
		if(choco.getGamme()==Gamme.BASSE && choco.isOriginal()) {
			return 8.0;
		}
		if(choco.getGamme()==Gamme.BASSE) {
			return 7.5;
		}
		return 0.0;
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