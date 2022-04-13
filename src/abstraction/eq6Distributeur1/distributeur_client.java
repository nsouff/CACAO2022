package abstraction.eq6Distributeur1;

public class distributeur_client extends Distributeur1{
	private float duree_contrat	;
	private float prix_vente	;
	private int quantite_vente	;
	private int stock_instantane ;
	private int prevision_stock	;
	/**
	 * @param duree_contrat
	 * @param prix_vente
	 * @param quantite_vente
	 * @param stock_instantane
	 * @param prevision_stock
	 */
	public distributeur_client(float duree_contrat, float prix_vente, int quantite_vente, int stock_instantane,
			int prevision_stock) {
		this.duree_contrat = duree_contrat;
		this.prix_vente = prix_vente;
		this.quantite_vente = quantite_vente;
		this.stock_instantane = stock_instantane;
		this.prevision_stock = prevision_stock;
	}
	/**
	 * @return the duree_contrat
	 */
	public float getDuree_contrat() {
		return duree_contrat;
	}
	/**
	 * @param duree_contrat the duree_contrat to set
	 */
	public void setDuree_contrat(float duree_contrat) {
		this.duree_contrat = duree_contrat;
	}
	/**
	 * @return the prix_vente
	 */
	public float getPrix_vente() {
		return prix_vente;
	}
	/**
	 * @param prix_vente the prix_vente to set
	 */
	public void setPrix_vente(float prix_vente) {
		this.prix_vente = prix_vente;
	}
	/**
	 * @return the quantite_vente
	 */
	public int getQuantite_vente() {
		return quantite_vente;
	}
	/**
	 * @param quantite_vente the quantite_vente to set
	 */
	public void setQuantite_vente(int quantite_vente) {
		this.quantite_vente = quantite_vente;
	}
	/**
	 * @return the stock_instantane
	 */
	public int getStock_instantane() {
		return stock_instantane;
	}
	/**
	 * @param stock_instantane the stock_instantane to set
	 */
	public void setStock_instantane(int stock_instantane) {
		this.stock_instantane = stock_instantane;
	}
	/**
	 * @return the prevision_stock
	 */
	public int getPrevision_stock() {
		return prevision_stock;
	}
	/**
	 * @param prevision_stock the prevision_stock to set
	 */
	public void setPrevision_stock(int prevision_stock) {
		this.prevision_stock = prevision_stock;
	}

	
}
