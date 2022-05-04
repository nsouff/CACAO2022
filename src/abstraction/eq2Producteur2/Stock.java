package abstraction.eq2Producteur2;
//auteur Clément//
public class Stock {
	
	private double quantite;
	private int duree;
	
	public Stock(double quantite, int duree) {
		this.quantite = quantite;
		this.duree = duree;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}
	public void addquantite(double q) {
		this.quantite += q;		
	}
	public void removequantite(double q) {
		this.quantite = this.quantite - q;
	}
	
	
	
}
