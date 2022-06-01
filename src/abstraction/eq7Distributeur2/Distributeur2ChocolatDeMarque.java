package abstraction.eq7Distributeur2;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.filiere.IDistributeurChocolatDeMarque;
import abstraction.eq8Romu.produits.ChocolatDeMarque;


public class Distributeur2ChocolatDeMarque extends Distributeur2Achat implements IDistributeurChocolatDeMarque  {
	
	private double capaciteDeVente;

	public Distributeur2ChocolatDeMarque() {
		super();
	}
	
	
	public double prix(ChocolatDeMarque choco) {
		return 10.0;
	}

	public double quantiteEnVente(ChocolatDeMarque choco, int crypto) {
		if (crypto!=this.cryptogramme) {
			this.journal.ajouter("Quelqu'un essaye de me pirater !");
			return 0.0;
		} else {
			return Math.min(capaciteDeVente, this.stock.getQuantite(choco)*(9/10));
		}
	}

	public double quantiteEnVenteTG(ChocolatDeMarque choco, int crypto) {
		if (crypto!=this.cryptogramme) {
			journal.ajouter("Quelqu'un essaye de me pirater !");
			return 0.0;
		} else {
			return Math.min(capaciteDeVente, this.stock.getQuantite(choco))/10.0;
			}
	}
	
	public void vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant, int crypto) {
		this.stock.remove(choco, quantite);
		journalVente.ajouter("vente de "+quantite+" "+choco.name()+" a "+client.getNom()+" pour un prix de "+ montant);
		
	}

	public void notificationRayonVide(ChocolatDeMarque choco, int crypto) {
		if (crypto!=this.cryptogramme) {
			journal.ajouter("Quelqu'un essaye de me pirater !");
		} else {
			journalStock.ajouter("Rayon vide : "+choco);
		}
	}


}