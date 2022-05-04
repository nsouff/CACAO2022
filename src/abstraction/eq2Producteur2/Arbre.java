package abstraction.eq2Producteur2;

//auteure : Fiona Martin

import abstraction.eq8Romu.produits.Feve;

public enum Arbre {
	
	ARBRE_HGB(60, 1/60, 960,  0.2 + Math.random()*0.5, 3, Feve.FEVE_HAUTE_BIO_EQUITABLE),
	ARBRE_HG(75, 1/75, 960, 0.2 + Math.random()*0.5, 3, Feve.FEVE_HAUTE),
	ARBRE_MGB(60, 1/60, 960, 0.2 + Math.random()*0.5, 3, Feve.FEVE_MOYENNE_BIO_EQUITABLE),
	ARBRE_MG(75, 1/75, 960, 0.2 + Math.random()*0.5, 3, Feve.FEVE_MOYENNE),
	ARBRE_BG(75, 1/75, 960, 0.2 + Math.random()*0.5, 3, Feve.FEVE_BASSE);
	
	private double DureeCroissance;
	private double RendementProgressif;
	private double DureeVie;
	private double RendementFinal;
	private double DureeAffinage;
	private Feve FeveArbre; 

	
	Arbre (double DC, double RP, double DV, double RF, double DA, Feve FA) {
		this.setDureeCroissance(DC);
		this.setRendementProgressif(RP); 
		this.setDureeVie(DV); 
		this.setRendementFinal(RF);
		this.setDureeAffinage(DA);
		this.setFeveArbre(FA);
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
	
	public Feve getFeveArbre() {
		return FeveArbre;
	}
	
	
	
	

}
