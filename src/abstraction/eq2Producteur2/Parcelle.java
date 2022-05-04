package abstraction.eq2Producteur2;

//auteure : Fiona Martin 

public class Parcelle {
	
	private Arbre TypeArbre;
	private int Age;
	private int NbArbres;
	private boolean EstMalade;
	private boolean Cooperative;
	private int StadeTransition;
	
	public Parcelle(Arbre typearbre) {
		/*		
		 */
		
		this.setTypeArbre(typearbre);
		this.setAge(0);
		this.setEstMalade(false);
		this.setNbArbres(1_000_000);
		
		
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

	public boolean isEstMalade() {
		return EstMalade;
	}

	public void setEstMalade(boolean estMalade) {
		EstMalade = estMalade;
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
	

}
