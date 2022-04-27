package abstraction.eq1Producteur1;

import abstraction.eq8Romu.produits.Feve;

public class Producteur1Feve {
	private int age;
	private Feve type;
	private boolean perime;
	private double poids;
	
	
	/**
	 * @param type
	 */
	public Producteur1Feve(Feve type, double poids) {
		this.type = type;
		this.age = 0;
		this.perime = false;
	}

	/**
	 * @param age
	 * @param type
	 * @param perime
	 */
	public Producteur1Feve(int age, Feve type, boolean perime,double poids) {
		this.age = age;
		this.type = type;
		this.perime = perime;
	}

	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age=age;
	}
	public Feve getType() {
		return this.type;
	}
	
	/**
	 * @return the poids
	 */
	public double getPoids() {
		return this.poids;
	}

	/**
	 * @param poids the poids to set
	 */
	public void setPoids(double poids) {
		this.poids = poids;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Feve type) {
		this.type = type;
	}

	/**
	 * @return the perime
	 */
	public boolean isPerime() {
		return this.perime;
	}
	/**
	 * @param perime the perime to set
	 */
	public void setPerime(boolean perime) {
		this.perime = perime;
	}
	
	public void Peremption() {
		if (this.age>24) {
			this.setPerime(true);
		}
	}
	
}
