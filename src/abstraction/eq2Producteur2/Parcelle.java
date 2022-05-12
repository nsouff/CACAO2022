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
	private int StadeTensionGeopolitique;
	
	
	
	
	public Parcelle(Arbre typearbre, int age) {
		
		//auteure : Fiona	
		
		this.setTypeArbre(typearbre);
		this.setAge(age);
		this.setRendementProgressif(0.2 + Math.random()*0.5);
		this.setStadeMaladie(0);
		this.setNbArbres(100000);
		
		
	}
	
	public void next() {
		this.MAJMaladie();
		this.MAJTensionGeopo();
	}

	public Arbre getTypeArbre() {
		return this.TypeArbre;
	}

	public void setTypeArbre(Arbre typeArbre) {
		this.TypeArbre = typeArbre;
	}

	public int getAge() {
		return this.Age;
	}

	public void setAge(int age) {
		this.Age = age;
	}

	public int getNbArbres() {
		return this.NbArbres;
	}

	public void setNbArbres(int nbArbres) {
		this.NbArbres = nbArbres;
	}

	public int getStadeMaladie() {
		return this.StadeMaladie;
	}

	public void setStadeMaladie(int stadeMaladie) {
	this.StadeMaladie =stadeMaladie;
	}

	public boolean isCooperative() {
		return this.Cooperative;
	}

	public void setCooperative(boolean cooperative) {
		this.Cooperative = cooperative;
	}

	public int getStadeTransition() {
		return this.StadeTransition;
	}

	public void setStadeTransition(int stadeTransition) {
		this.StadeTransition = stadeTransition;
	}

	public double getRendementProgressif() {
		return this.RendementProgressif;
	}

	public void setRendementProgressif(double rendementProgressif) {
		this.RendementProgressif = rendementProgressif;
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
		if ((this.StadeMaladie == 1) && (Filiere.LA_FILIERE.getEtape() - this.getDebutMaladie() == 2)) {
			this.setStadeMaladie(0);
		}
		if ((this.StadeMaladie == 2) && (Filiere.LA_FILIERE.getEtape() - this.getDebutMaladie() == 5)) {
			this.setStadeMaladie(0);
		}
		if ((this.StadeMaladie == 3) && (Filiere.LA_FILIERE.getEtape() - this.getDebutMaladie() == 6)) {
			this.setStadeMaladie(0);
		}
		if ((this.StadeMaladie == 4) && (Filiere.LA_FILIERE.getEtape() - this.getDebutMaladie() == 8)) {
			this.setStadeMaladie(0);
		}
	}

	public int getDebutMaladie() {
		return this.DebutMaladie;
	}

	public void setDebutMaladie(int debutMaladie) {
		this.DebutMaladie = debutMaladie;
	}

	public int getStadeTensionGeopolitique() {
		return this.StadeTensionGeopolitique;
	}

	public void setStadeTensionGeopolitique(int STG) {
		this.StadeTensionGeopolitique = STG;
	}
	
	public void MAJTensionGeopo() {
		if (this.StadeTensionGeopolitique != 0) {
			this.StadeTensionGeopolitique = this.StadeTensionGeopolitique - 1;
		}
		
		else {
			double d = Math.random();
			if (d < 0.05) {
				
				double d2 = Math.random();
				while (d2 == 0) {
					d2 = Math.random();
				}
				int dureeTG = (int) Math.ceil(d2*6);
				this.StadeTensionGeopolitique = dureeTG;
			}
		}
	}

	

}
