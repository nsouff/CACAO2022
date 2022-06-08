package abstraction.eq7Distributeur2.tools;

import abstraction.eq8Romu.produits.ChocolatDeMarque;

public interface IDemande {
	
	
	/**
	 * Retourne la demande restante à combler pour le chocolat choco
	 * @param choco
	 */
	double getDemande(ChocolatDeMarque choco);
	
	/**
	 * Définie la demande restante à combler pour le chocolat choco à valeur
	 * @param choco
	 * @param valeur
	 */
	void setDemande(ChocolatDeMarque choco,double valeur);
	
	/**
	 * Retire de la demande restante à combler pour le chocolat choco la valeur valeur
	 * @param choco
	 * @param valeur
	 */
	void removeDemande(ChocolatDeMarque choco,double valeur);
	
	/**
	 * Initialise la demande
	 */
	void initialiserDemande();
	
}
