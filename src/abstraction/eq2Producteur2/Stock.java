package abstraction.eq2Producteur2;

//auteur Clément//
public class Stock {
	
	private double quantite;
	private int step_arrivee;
	
	public Stock(double quantite, int duree) {
		this.quantite = quantite;
		this.step_arrivee = duree;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public int getStep_arrivee() {
		return step_arrivee;
	}

	public void setStep_arrivee(int date_arrivee) {
		this.step_arrivee = date_arrivee;
	}
	
	public void addquantite(double q) {
		this.quantite += q;		
	}
	public void removequantite(double q) {
		this.quantite = this.quantite - q;
	}

	
	
	
}
