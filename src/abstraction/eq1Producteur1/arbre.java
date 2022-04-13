package abstraction.eq1Producteur1;

public class arbre {
	private int ut_plantation;
	private int ut_esperance_vie;
	private int maladie;
	private int qualite;
	private boolean bioequitable;
	private boolean transition_bio;
	private int stade_transition;
	
	
	public void set_ut_plantation(int ut_plantation) {
		this.ut_plantation = ut_plantation;
	}
	

	public void setMaladie(int maladie) {
		this.maladie=maladie;
	}
	public void setQualite() {
		this.qualite=qualite;
	
	}
	public void setBioequitable() {
	
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
}
