package abstraction.eq6Distributeur1;

import java.util.HashMap;
import java.util.Map;
import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.filiere.IDistributeurChocolatDeMarque;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class DistributeurChocolatDeMarque extends Distributeur1Acteur implements IDistributeurChocolatDeMarque{

	private Map<ChocolatDeMarque, Double> teteGondole = new HashMap<ChocolatDeMarque, Double>(); // (nom du chocolat,% en tête de gondole), Emma Humeau 

	private double qteEnVenteTG; //Emma Humeau

	//private List<HashMap<ChocolatDeMarque, Double>> HistoriqueVentes = new LinkedList<HashMap<ChocolatDeMarque, Double>>();
	/**
	 * @return the teteGondole
	 */
	public Map<ChocolatDeMarque, Double> getTeteGondole() { //Emma Humeau
		return teteGondole;
	}

	/**
	 * @param teteGondole the teteGondole to set
	 */
	public void setTeteGondole(Map<ChocolatDeMarque, Double> teteGondole, ChocolatDeMarque choco) { //Emma Humeau
		double qteDispoEnTg = NotreStock.qteStockageTotale()*0.1 - qteEnVenteTG ; 
		if (qteEnVenteTG <= NotreStock.qteStockageTotale()*0.1) {  //la quantité de choco en vente en TG doit etre inférieure à 10% du stock
			teteGondole.put(choco, qteDispoEnTg );}
	}

	@Override
	public double prix(ChocolatDeMarque choco) { //Emma humeau
		return prixVente.get(choco);
	}

	@Override
	public double quantiteEnVente(ChocolatDeMarque choco, int crypto) { //Emma Humeau
		if (NotreStock.qteStockageTotale() <= 200) {  
			return NotreStock.getStock(choco); }  //on met tout le stock en vente
		else {
			return NotreStock.getStock(choco)*0.9;  //on met que 90% du stock en vente
		}
	}

	@Override
	public double quantiteEnVenteTG(ChocolatDeMarque choco, int crypto) { //Emma humeau
		//calcule la qte en vente en tete de gondole de tous les chocolats

				double sumqteTG = 0;

				for (Map.Entry<ChocolatDeMarque, Double > entry : teteGondole.entrySet()) {
		           Double ValueQte = entry.getValue();
		           sumqteTG += ValueQte;
				}

				this.qteEnVenteTG = sumqteTG;

		return qteEnVenteTG;
	}

	//il faudra rajouter ensuite une liste qui mémorise les ventes
	@Override
	public void vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant, int crypto) { //emma Humeau
		NotreStock.addQte(choco, -quantite);
	}

	@Override
	public void notificationRayonVide(ChocolatDeMarque choco, int crypto) { //Emma Humeau
			journal1.ajouter("Rayon vide pour la tête de gondole");

	}

}