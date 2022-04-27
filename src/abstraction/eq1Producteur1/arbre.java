package abstraction.eq1Producteur1;

import abstraction.eq8Romu.filiere.Filiere;

public class arbre {
	private int ut_plantation;
	private int ut_esperance_vie;
	private int stade_maladie;
	private int ut_debut_maladie;
	private int qualite ;
	private boolean bioequitable;
	private boolean transition_bio;
	private int stade_transition;
	private double productivite_max;
	
	public arbre() {
		this.ut_plantation=0;
		this.ut_esperance_vie= this.Esperance_vie();
		this.stade_maladie=0;
		this.qualite=0;
		this.bioequitable=false;
		this.transition_bio=false;
		this.stade_transition=0;
		this.productivite_max=this.Production_max();
	}
	
	public void setUt_plantation(int ut_plantation) {
		this.ut_plantation = ut_plantation;
	}
	public void setUt_esperance_vie(int ut_esperance_vie) {
		this.ut_esperance_vie=ut_esperance_vie;
	}
	public void setMaladie(int maladie) {
		this.stade_maladie=maladie;
	}
	public void setUt_debut_maladie(int ut) {
		this.ut_debut_maladie=ut;
	}
	public void setQualite(int qualite) {
		this.qualite=qualite;
	}
	public void setBioequitable(boolean bioequitable) {
		this.bioequitable= bioequitable;
	}
	public void setTransition_bio(boolean transition_bio) {
		this.transition_bio=transition_bio;
	}
	public void setStade_transition(int stade_transition) {
		this.stade_transition = stade_transition;
	}
	public void setProductivite_max(double productivite_max) {
		this.productivite_max = productivite_max;
	}
	public int getUt_plantation() {
		return this.ut_plantation;
	}
	public int getUt_esperance_vie() {
		return this.ut_esperance_vie;
	}
	public int getMaladie() {
		return this.stade_maladie;
	}
	public int getUt_debut_maladie( ) {
		return this.ut_debut_maladie;
	}
	public int getQualite() {
		return this.qualite;
	}
	public boolean getBioequitable() {
		return this.bioequitable;
	}
	public boolean getTransition_bio() {
		return this.transition_bio;
	}
	public int getStade_transition() {
		return this.stade_transition;
	}
	public double getProductivite_max() {
		return this.productivite_max;
	}
	
	public void MAJMaladie() {
		if (stade_maladie == 0) {
			double d = Math.random();
			if (d<=0.03) {
				double stade_maladie = Math.random();
				if (stade_maladie<=0.45) {
					this.setMaladie(1);
				}
				if ((stade_maladie>0.45) && (stade_maladie<=0.7)) {
					this.setMaladie(2);
				}
				if ((stade_maladie>0.7) && (stade_maladie<=0.85)) {
					this.setMaladie(3);
				}
				if ((stade_maladie>0.85) && (stade_maladie<=0.95)) {
					this.setMaladie(4);
				}
				if (stade_maladie>0.95) {
					this.setMaladie(5);
				}
			}
		}
		if ((stade_maladie == 1) && (Filiere.LA_FILIERE.getEtape() - this.getUt_debut_maladie() == 2)) {
			this.setMaladie(0);
		}
		if ((stade_maladie == 2) && (Filiere.LA_FILIERE.getEtape() - this.getUt_debut_maladie() == 5)) {
			this.setMaladie(0);
		}
		if ((stade_maladie == 3) && (Filiere.LA_FILIERE.getEtape() - this.getUt_debut_maladie() == 4)) {
			this.setMaladie(0);
		}
		if ((stade_maladie == 4) && (Filiere.LA_FILIERE.getEtape() - this.getUt_debut_maladie() == 4)) {
			this.setMaladie(0);
		}
	}
	public int Esperance_vie() {
		double d = Math.random();
		if (d<0.5) {
			int esp = 960-(int)Math.floor(d*240);
			return esp;
		}
		else {
			int esp = 960+(int)Math.floor((d-0.5)*240);
			return esp;
		}
	}
	public double Production_max() {
		double d = 0.2 + Math.random()/20;
		if (this.bioequitable) {
			d = 0.8*d;
		}
		return d;
	}
	public int Age() {
		return Filiere.LA_FILIERE.getEtape()-this.getUt_plantation();
	}
	public double Recolte() {
		if (this.Age()<= 72 || this.Age()>=this.getUt_esperance_vie()) {
			return 0;
		}
		else {
			if (this.Age()<= 144) {
				return this.getProductivite_max()*(this.Age()-72)/72;
			}
			else {
				return this.getProductivite_max();
			}
		}
	}
}
