package abstraction.eq5Transformateur3;

// julien
public class DateProd<Produit>{
	private Produit p;
	private int date;
	
	public DateProd(int date,Produit p) {
		this.p=p;
		this.date=date;
	}
	
	public Produit getProduit() {
		return this.p;
	}
	public int getDate() {
		return this.date;
	}

}
