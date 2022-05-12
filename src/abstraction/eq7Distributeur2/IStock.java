package abstraction.eq7Distributeur2;

import java.util.List;

import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

/**
 * Classe abstraite de notre gestionnaire de stock
 * @author mgloc
 *
 */
public interface IStock {
	
	//Stocks-------------------------------------------------------------------------------------------
	
	/**
	 * Retourne la quantitée en stock du chocolat selectionné
	 * @param chocolat
	 */
	public double getQuantite(ChocolatDeMarque chocolat);
	
	/**
	 * Ajoute une quantite en stock du chocolat selectionné
	 * @param chocolat
	 * @param quantite
	 */
	public void addProduit(ChocolatDeMarque chocolat, Double quantite);
	
	/**
	 * Enlève une quantite en stock du chocolat selectionné
	 * @param chocolat
	 * @param quantite
	 */
	public void remove(ChocolatDeMarque chocolat,Double quantite);
	
	/**
	 * Retourne la quantité totale dans les stocks
	 */
	public double getQuantiteTotale();
	
	
	//Seuil de rachat-------------------------------------------------------------------------------------------
	
	/**
	 * Retourne le seuil à partir duquel Biofour souhaite racheter du chocolat du type fourni
	 * @param chocolat
	 */
	public double getSeuilRachat(ChocolatDeMarque chocolat);
	
	/**
	 * Défini le seuil à partir duquel Biofour souhaite racheter du chocolat du type fourni
	 * @param chocolat
	 * @param seuil
	 */
	public void setSeuilRachat(ChocolatDeMarque chocolat, Double seuil);
	
}
