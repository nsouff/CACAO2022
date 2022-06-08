package abstraction.eq1Producteur1;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;

public class FeveProducteur1 {
	private int ut_debut; //Plus pratique puisqu'on a pas besoin d'actualiser
	private boolean perime;
	private double poids;
	private boolean affine;
	private Parc provenance;
	
	
//Auteur : Laure ; Modificateur : Khéo
	public FeveProducteur1(double poids, Parc provenance) {
		this.ut_debut = Filiere.LA_FILIERE.getEtape();
		this.perime = false;
		this.poids = poids;
		this.affine=false;
		this.provenance=provenance;
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
		return this.ut_debut;
	}
	

	/**
	 * @return the provenance
	 */
	public Parc getProvenance() {
		return provenance;
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
	
	public boolean isAffine() {
		return this.affine;
	}
	
	//Auteur : Khéo

	public void MAJAffinage(Feve f) {
		if (f.getGamme().equals(Gamme.HAUTE)) {
			if (this.getAge()>3) {
				this.setAffine(true);
			}
		}
		
		if (f.getGamme().equals(Gamme.MOYENNE)) {
			if (this.getAge()>2) {
				this.setAffine(true);
			}
		}
		
		if (f.getGamme().equals(Gamme.BASSE)) {
			if (this.getAge()>1) {
				this.setAffine(true);
			}
		}
	}
	
	
	//Auteur : Khéo
	//Modifié par : Antoine 
	//La durée de péremption des fèves est de 2 ans
	public void MajPeremption() {  
		if (this.getAge()>48) {
			this.setPerime(true);
		}
	}
	
	/**
	 * @param affine the affine to set
	 */
	public void setAffine(boolean affine) {
		this.affine = affine;
	}
	
}
