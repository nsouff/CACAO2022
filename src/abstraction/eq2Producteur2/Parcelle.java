package abstraction.eq2Producteur2;

import abstraction.eq8Romu.filiere.Filiere;

//auteure : Fiona Martin 

public class Parcelle {
	
	//auteure : Fiona
	
	private Arbre TypeArbre;
	private int Age;
	private int NbArbres;
	private double RendementProgressif;
	private int StadeMaladie;
	private boolean Cooperative;
	private int StadeTransition;
	private int DebutMaladie;
	
	
	
	public Parcelle(Arbre typearbre, int age) {
		
		//auteure : Fiona	
		
		this.setTypeArbre(typearbre);
		this.setAge(age);
		this.setRendementProgressif(0.2 + Math.random()*0.5);
		this.setStadeMaladie(0);
		this.setNbArbres(100_000);
		
	}

	public Arbre getTypeArbre() {
		return TypeArbre;
	}

	public void setTypeArbre(Arbre typeArbre) {
		TypeArbre = typeArbre;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public int getNbArbres() {
		return NbArbres;
	}

	public void setNbArbres(int nbArbres) {
		NbArbres = nbArbres;
	}

	public int StadeMaladie() {
		return StadeMaladie;
	}

	public void setStadeMaladie(int stadeMaladie) {
	StadeMaladie =stadeMaladie;
	}

	public boolean isCooperative() {
		return Cooperative;
	}

	public void setCooperative(boolean cooperative) {
		Cooperative = cooperative;
	}

	public int getStadeTransition() {
		return StadeTransition;
	}

	public void setStadeTransition(int stadeTransition) {
		StadeTransition = stadeTransition;
	}

	public double getRendementProgressif() {
		return RendementProgressif;
	}

	public void setRendementProgressif(double rendementProgressif) {
		RendementProgressif = rendementProgressif;
	}
	
	public void MAJMaladie() { //Écrit par Antoine
		if (StadeMaladie == 0) {
			double d = Math.random();
			if (d<=0.03) {
				double stade_maladie = Math.random();
				if (stade_maladie<=0.45) {
					this.setStadeMaladie(1);
					this.setDebutMaladie(Filiere.LA_FILIERE.getEtape());
				}
				if ((stade_maladie>0.45) && (stade_maladie<=0.7)) {
					this.setStadeMaladie(2);
					this.setDebutMaladie(Filiere.LA_FILIERE.getEtape());
				}
				if ((stade_maladie>0.7) && (stade_maladie<=0.85)) {
					this.setStadeMaladie(3);
					this.setDebutMaladie(Filiere.LA_FILIERE.getEtape());
				}
				if ((stade_maladie>0.85) && (stade_maladie<=0.95)) {
					this.setStadeMaladie(4);
					this.setDebutMaladie(Filiere.LA_FILIERE.getEtape());
				}
				if (stade_maladie>0.95) {
					this.setStadeMaladie(5);
					this.setDebutMaladie(Filiere.LA_FILIERE.getEtape());
				}
			}
		}
		if ((StadeMaladie == 1) && (Filiere.LA_FILIERE.getEtape() - this.getDebutMaladie() == 2)) {
			this.setStadeMaladie(0);
		}
		if ((StadeMaladie == 2) && (Filiere.LA_FILIERE.getEtape() - this.getDebutMaladie() == 5)) {
			this.setStadeMaladie(0);
		}
		if ((StadeMaladie == 3) && (Filiere.LA_FILIERE.getEtape() - this.getDebutMaladie() == 6)) {
			this.setStadeMaladie(0);
		}
		if ((StadeMaladie == 4) && (Filiere.LA_FILIERE.getEtape() - this.getDebutMaladie() == 8)) {
			this.setStadeMaladie(0);
		}
	}

	public int getDebutMaladie() {
		return DebutMaladie;
	}

	public void setDebutMaladie(int debutMaladie) {
		DebutMaladie = debutMaladie;
	}

}
