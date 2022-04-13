package abstraction.eq1Producteur1;

public class arbre {
	private int ut_plantation;
	private int ut_esperance_vie;
	private int maladie;
	private int qualite ;
	private boolean bioequitable;
	private boolean transition_bio;
	private int stade_transition;
	
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
	public void setBioéquitable(boolean bioequitable) {
		this.bioequitable= bioequitable;
	}
	public void setTransition_bio(boolean transition_bio) {
		this.transition_bio=transition_bio;
	}
	public void setStade_transition(int stade_transition) {
		this.stade_transition = stade_transition;
	}

}
