package abstraction.eq7Distributeur2;

import java.util.List;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.filiere.IDistributeurChocolatDeMarque;
import abstraction.eq8Romu.produits.ChocolatDeMarque;


public class Distributeur2ChocolatDeMarque extends Distributeur2Acteur implements IDistributeurChocolatDeMarque  {
	

	private double capaciteDeVente;
	private double[] prix;

	public Distributeur2ChocolatDeMarque(double[] prix, double capaciteDeVente) {
		super();
		this.prix=prix;
		this.capaciteDeVente=capaciteDeVente;
	}
	
	
	public double prix(ChocolatDeMarque choco) {
		// TODO Auto-generated method stub
		int pos= (chocolats.indexOf(choco));
		if (pos<0) {
			return 0.0;
		} else {
			return prix[pos];
		}
	}

	public double quantiteEnVente(ChocolatDeMarque choco, int crypto) {
		// TODO Auto-generated method stub
		if (crypto!=this.cryptogramme) {
			journal.ajouter("Quelqu'un essaye de me pirater !");
			return 0.0;
		} else {
			int pos= (chocolats.indexOf(choco));
			if (pos<0) {
				return 0.0;
			} else {
				return Math.min(capaciteDeVente, super.stock.getQuantite(choco));
			}
		}
	}

	public double quantiteEnVenteTG(ChocolatDeMarque choco, int crypto) {
		// TODO Auto-generated method stub
		if (crypto!=this.cryptogramme) {
			journal.ajouter("Quelqu'un essaye de me pirater !");
			return 0.0;
		} else {
			int pos= (chocolats.indexOf(choco));
			if (pos<0) {
				return 0.0;
			} else {
				return Math.min(capaciteDeVente, super.stock.getQuantite(choco))/10.0;
			}
		}
	}

	public void vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant, int crypto) {
		// TODO Auto-generated method stub
		int pos= (chocolats.indexOf(choco));
		if (pos>=0) {
			super.stock.remove(choco, quantite);
		}
		
	}

	public void notificationRayonVide(ChocolatDeMarque choco, int crypto) {
		// TODO Auto-generated method stub
		if (crypto!=this.cryptogramme) {
			journal.ajouter("Quelqu'un essaye de me pirater !");
		} else {
			journal.ajouter("Je n'ai pas assez mis en vente de "+choco);
		}
	}


}