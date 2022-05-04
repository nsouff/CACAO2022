package abstraction.eq2Producteur2;

//auteure : Fiona Martin

import abstraction.eq8Romu.produits.Feve;

public enum Arbre {
	
	ARBRE_HGB(60, 1/60, 960,  0.2 + Math.random()*0.5, 3, Feve.FEVE_HAUTE_BIO_EQUITABLE, 0, false, true, 0),
	ARBRE_HG(75, 1/75, 960, 0.2 + Math.random()*0.5, 3, Feve.FEVE_HAUTE, 0, false, true, 0),
	ARBRE_MGB(60, 1/60, 960, 0.2 + Math.random()*0.5, 3, Feve.FEVE_MOYENNE_BIO_EQUITABLE, 0, false, true, 0),
	ARBRE_MG(75, 1/75, 960, 0.2 + Math.random()*0.5, 3, Feve.FEVE_MOYENNE, 0, false, true, 0),
	ARBRE_BG(75, 1/75, 960, 0.2 + Math.random()*0.5, 3, Feve.FEVE_BASSE, 0, false, true, 0);
	
	private double DureeCroissance;
	private double RendementProgressif;
	private double DureeVie;
	private double RendementFinal;
	private double DureeAffinage;
	private Feve FeveArbre; 
	private int StadeTransition;
	private boolean Cooperative;
	private boolean Vivant; 
	private int Age; 

	
	Arbre (double DC, double RP, double DV, double RF, double DA, Feve FA, int ST, boolean B1, boolean B2, int A) {
		this.setDureeCroissance(DC);
		this.setRendementProgressif(RP); 
		this.setDureeVie(DV); 
		this.setRendementFinal(RF);
		this.setDureeAffinage(DA);
		this.setFeveArbre(FA);
		this.setStadeTransition(ST);
		this.setCooperative(B1);
		this.setVivant(B2);
		this.setAge(A);
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
	
	public void setFeveArbre(Feve FA) {
		FeveArbre = FA ;
	}
	
	public void setStadeTransition(int ST) {
		StadeTransition = ST;
	}



	public boolean isCooperative() {
		return Cooperative;
	}



	public void setCooperative(boolean cooperative) {
		Cooperative = cooperative;
	}



	public boolean isVivant() {
		return Vivant;
	}



	public void setVivant(boolean vivant) {
		Vivant = vivant;
	}



	public int getAge() {
		return Age;
	}



	public void setAge(int age) {
		Age = age;
	}

	
	
	

}
