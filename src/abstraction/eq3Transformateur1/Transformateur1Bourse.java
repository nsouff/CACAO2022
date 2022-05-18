package abstraction.eq3Transformateur1;

import java.util.HashMap;

import abstraction.eq8Romu.bourseCacao.IAcheteurBourse;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;

public class Transformateur1Bourse extends Transformateur1Acteur implements IAcheteurBourse{
	
	protected DicoFeve quantiteAchatFeve;            /** quantité de fève qu'on souhaite acheter */
	protected DicoChoco quantiteDemandeeChoco;       /** quantité demandée au tour précédent */
	protected dernierPrixVenteChoco dernierPrixVenteChoco;
	protected dernierPrixVenteChoco dernierPrixVenteChocoReset; /** prix minimum (par unité) négocié au dernier tour auquel on a vendu le chocolat avec tel distributeur - c'est un dictionnaire de dictionnaire dont le premier dictionnaire a pour clé les distributeurs et le deuixème les chocolats */
	protected DicoFeve prixAchatFeve;
	protected DicoFeve stockFeve;               /** Integer --> Double*/
	protected DicoChoco stockChoco;           /** Integer --> Double*/
	protected DicoChoco prixVenteMin;        // prix minimal de vente pour chaque chocolat à ce tour (à mettre à jour avec prixVenteMin())
	
	// Alexandre
	public Transformateur1Bourse() {
		quantiteAchatFeve = new DicoFeve();
		quantiteDemandeeChoco = new DicoChoco();
		dernierPrixVenteChoco = new dernierPrixVenteChoco();
		prixAchatFeve = new DicoFeve();
		stockFeve = new DicoFeve();
		stockChoco = new DicoChoco();
		prixVenteMin = new DicoChoco();
	}
	
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
