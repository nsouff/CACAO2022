package abstraction.eq2Producteur2;

import abstraction.eq8Romu.filiere.Filiere;

//auteur Clément//

public class Stock {
	
	private double quantite;
	private int step_arrivee;
	private boolean perime;
	
	public Stock(double quantite, int duree, boolean perime) {
		this.quantite = quantite;
		this.step_arrivee = duree;
		this.perime = perime;
	}
	
	public Stock(double quantite) {
		this.quantite = quantite;
		this.step_arrivee = Filiere.LA_FILIERE.getEtape();
		this.perime = false;
	}

	public double getQuantite() {
		return this.quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public int getStep_arrivee() {
		return this.step_arrivee;
	}

	public void setStep_arrivee(int date_arrivee) {
		this.step_arrivee = date_arrivee;
	}
	
	public void addquantite(double q) {
		this.quantite = this.quantite + q;		
	}
	public void removequantite(double q) {
		this.quantite = this.quantite - q;
	}
	public int getAge() {
		return (Filiere.LA_FILIERE.getEtape()- this.getStep_arrivee());
	}

	public boolean isPerime() {
		return this.perime;
	}

	public void setPerime(boolean perime) {
		this.perime = perime;
	}
	
	public void Peremption() {
		if(this.getAge()>48) {
			this.setPerime(true);
		}
	}	
}
