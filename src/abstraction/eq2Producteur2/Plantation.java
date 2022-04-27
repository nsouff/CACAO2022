package abstraction.eq2Producteur2;

public class Plantation {
	private double NbArbres; 
	private Arbre ArbrePlante;
	
	public void plantation(double NA, Arbre AP) {
		this.setNbArbres(NA); 
		this.setArbrePlante(AP);
	}

	public double getNbArbres() {
		return NbArbres;
	}

	public void setNbArbres(double nbArbres) {
		NbArbres = nbArbres;
	}

	public Arbre getArbrePlante() {
		return ArbrePlante;
	}

	public void setArbrePlante(Arbre arbrePlante) {
		ArbrePlante = arbrePlante;
	}
	
	
	
	
	
}
