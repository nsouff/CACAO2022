package abstraction.eq4Transformateur2;

public class DateProdTransfo2<Produit> {
	protected double date;
	protected Produit p;
	
	public DateProdTransfo2(){
		date=0;
	}
	
	public DateProdTransfo2(double date,Produit p) {
		this.date=date;
		this.p=p;
	}
	
	public Produit getProduit() {
		return this.p;
	}
	public double getDate() {
		return this.date;
	}
	public void setProduit(Produit p) {
		this.p=p;
	}
	public void setDate(double date) {
		this.date=date;
	}
	
}
