package abstraction.eq3Transformateur1;

import java.util.HashMap;

import abstraction.eq8Romu.bourseCacao.IAcheteurBourse;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;

public class Transformateur1Bourse extends Transformateur1Acteur implements IAcheteurBourse{
	
	protected HashMap<Feve, Double> quantiteAchatFeve;            /** quantité de fève qu'on souhaite acheter */
	protected HashMap<Chocolat, Double> quantiteDemandeeChoco;       /** quantité demandée au tour précédent */
	protected dernierPrixVenteChoco dernierPrixVenteChoco;        /** prix minimum (par unité) négocié au dernier tour auquel on a vendu le chocolat avec tel distributeur - c'est un dictionnaire de dictionnaire dont le premier dictionnaire a pour clé les distributeurs et le deuixème les chocolats */
	protected HashMap<Feve, Double> prixAchatFeve;
	protected HashMap<Feve, Double> stockFeve;               /** Integer --> Double*/
	protected HashMap<Chocolat,Double> stockChoco;           /** Integer --> Double*/
	protected HashMap<Chocolat, Double> prixVenteMin;        // prix minimal de vente pour chaque chocolat à ce tour (à mettre à jour avec prixVenteMin())
	
	/** renvoie la quantité de fève voulue; auteur Julien 
	 * Pas de prise en compte pour l'instant des contrats */
	public double demande(Feve f, double cours) {
		if (cours<prixAchatFeve.get(f)) {
			return quantiteAchatFeve.get(f)*0.5;
		}
		return 0.;
	}

	/** modification du stock de fèves; auteur Anna */
	public void notificationAchat(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		stockFeve.put(f, quantiteEnKg + stockFeve.get(f)) ;
	}

	/** */
	public void notificationBlackList(int dureeEnStep) {
	
	}

}
