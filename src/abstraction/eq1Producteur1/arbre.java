package abstraction.eq1Producteur1;

public class arbre {
	private int ut_plantation;
	private int ut_esperance_vie;
	private int maladie;
	private int qualite ;
	private boolean bioequitable;
	private boolean transition_bio;
	private int stade_transition;
	
	public arbre() {
		this.ut_plantation=0;
		this.ut_esperance_vie= this.Esperance_vie();
		this.maladie=0;
		this.qualite=0;
		this.bioequitable=false;
		this.transition_bio=false;
		this.stade_transition=0;
	}
	

	public void setUt_plantation(int ut_plantation) {
		this.ut_plantation = ut_plantation;
	}
	public void setUt_esperance_vie(int ut_esperance_vie) {
		this.ut_esperance_vie=ut_esperance_vie;
	}
	public void setMaladie(int maladie) {
		this.maladie=maladie;
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
	public int getUt_plantation() {
		return this.ut_plantation;
	}
	public int getUt_esperance_vie() {
		return this.ut_esperance_vie;
	}
	public int getMaladie() {
		return this.maladie;
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
	
	public void isMalade() {
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
}
