package abstraction.eq5Transformateur3;

// julien
public class DateProd<Produit>{
	private Produit produit; 
	private int date; // la date est en fait le step auquel le produit sera perdu 
	
	public DateProd(int date,Produit produit) {
		this.produit=produit;
		this.date=date;
	}
	
	public Produit getProduit() {
		return this.produit;
	}
	public int getDate() {
		return this.date;
	}

}
