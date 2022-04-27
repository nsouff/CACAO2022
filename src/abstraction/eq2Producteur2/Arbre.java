package abstraction.eq2Producteur2;

public class Arbre {
	private double DureeCroissance;
	private double RendementProgressif;
	private double DureeVie;
	private double RendementFinal;
	private double DureeAffinage;

	
	public Arbre (double DC, double RP, double DV, double RF, double DA) {
		this.setDureeCroissance(DC);
		this.setRendementProgressif(RP); 
		this.setDureeVie(DV); 
		this.setRendementFinal(RF);
		this.setDureeAffinage(DA);
	}


	public double getDureeCroissance() {
		return DureeCroissance;
	}


	public void setDureeCroissance(double dureeCroissance) {
		DureeCroissance = dureeCroissance;
	}


	public double getRendementProgressif() {
		return RendementProgressif;
	}


	public void setRendementProgressif(double rendementProgressif) {
		RendementProgressif = rendementProgressif;
	}


	public double getDureeVie() {
		return DureeVie;
	}


	public void setDureeVie(double dureeVie) {
		DureeVie = dureeVie;
	}


	public double getRendementFinal() {
		return RendementFinal;
	}


	public void setRendementFinal(double rendementFinal) {
		RendementFinal = rendementFinal;
	}

	public double getDureeAffinage() {
		return DureeAffinage;
	}


	public void setDureeAffinage(double dureeAffinage) {
		DureeAffinage = dureeAffinage;
	}

	
	
	

}
