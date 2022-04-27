package abstraction.eq1Producteur1;

import abstraction.eq8Romu.produits.Feve;

public class Producteur1Feve {
	private int age;
	private boolean perime;
	private double poids;
	
	

	public Producteur1Feve(double poids) {
		this.age = 0;
		this.perime = false;
	}

	/**
	 * @param age
	 * @param perime
	 */
	public Producteur1Feve(int age, boolean perime,double poids) {
		this.age = age;
		this.perime = perime;
	}

	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age=age;
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
