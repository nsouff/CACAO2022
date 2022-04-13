package abstraction.eq1Producteur1;

public class Producteur1Feve {
	private int age;
	private String type;
	private boolean perime;
	
	
	
	/**
	 * @param type
	 */
	public Producteur1Feve(String type) {
		this.type = type;
		this.age = 0;
		this.perime = false;
	}

	/**
	 * @param age
	 * @param type
	 * @param malade
	 * @param perime
	 */
	public Producteur1Feve(int age, String type, boolean perime) {
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
	public String getType() {
		return this.type;
	}
	
	/**
	 * @return the perime
	 */
	public boolean isPerime() {
		return perime;
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
