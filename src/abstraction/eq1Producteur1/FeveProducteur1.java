package abstraction.eq1Producteur1;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Feve;

public class FeveProducteur1 {
	private int ut_debut; //Plus pratique puisqu'on a pas besoin d'actualiser
	private boolean perime;
	private double poids;
	
	
//Auteur : Laure ; Modificateur : Khéo
	public FeveProducteur1(double poids) {
		this.ut_debut = Filiere.LA_FILIERE.getEtape();
		this.perime = false;
		this.poids = poids;
	}

	/**
	 * @param age
	 * @param perime
	 */
	public FeveProducteur1(int ut_debut, boolean perime,double poids) { 
		this.ut_debut = ut_debut;
		this.perime = perime;
		this.poids = poids;
	}

	public int getAge() {
		return Filiere.LA_FILIERE.getEtape()-this.getUt_debut();
	}
	
	public void setUt_debut(int ut_debut) {
		this.ut_debut=ut_debut;
	}
	
	/**
	 * @return the ut_debut
	 */
	public int getUt_debut() {
		return ut_debut;
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
		if (this.getAge()>24) {
			this.setPerime(true);
		}
	}
	
}
