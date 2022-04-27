package abstraction.eq2Producteur2;

import abstraction.eq8Romu.produits.Gamme;

public class Arbre {
	private double DureeCroissance;
	private double RendementProgressif;
	private double DureeVie;
	private double RendementFinal;
	private double DureeAffinage;
	private double DureeTransformation;
	private Gamme ArbreGamme;

	
	public void arbre (double DC, double RP, double DV, double RF, double DA, double T, Gamme AG ) {
		this.setDureeCroissance(DC);
		this.setRendementProgressif(RP); 
		this.setDureeVie(DV); 
		this.setRendementFinal(RF);
		this.setDureeAffinage(DA);
		this.setDureeTransformation(T);
		this.setArbreGamme(AG);
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


	public double getDureeTransformation() {
		return DureeTransformation;
	}


	public void setDureeTransformation(double dureeTransformation) {
		DureeTransformation = dureeTransformation;
	}


	public double getDureeAffinage() {
		return DureeAffinage;
	}


	public void setDureeAffinage(double dureeAffinage) {
		DureeAffinage = dureeAffinage;
	}


	public Gamme getArbreGamme() {
		return ArbreGamme;
	}


	public void setArbreGamme(Gamme arbreGamme) {
		ArbreGamme = arbreGamme;
	}
	
	

}
