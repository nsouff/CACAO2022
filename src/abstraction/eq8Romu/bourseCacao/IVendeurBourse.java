package abstraction.eq8Romu.bourseCacao;

import abstraction.eq8Romu.produits.Feve;

public interface IVendeurBourse {

	/**
	 * Retourne la quantite en kg de feves de type f que le vendeur 
	 * souhaite vendre a cette etape sachant que le cours actuel de 
	 * la feve f est cours
	 * @param f le type de feve
	 * @param cours le cours actuel des feves de type f
	 * @return la quantite en kg de feves de type f que this souhaite vendre 
	 */
	public double offre(Feve f, double cours);

	/**
	 * Methode appelee par la bourse pour avertir le vendeur qu'il est parvenu
	 * a vendre quantiteEnKg Kg de feve f au prix de coursEnEuroParKg euros par kg.
	 * L'acteur this doit diminuer son stock de feves de type f de la 
	 * quantite quantiteEnKg.
	 * Lorsque cette methode est appelee la transaction bancaire a eu lieu 
	 * (vendeurs et acheteurs n'ont pas a s'occuper du virement)
	 */
	public void notificationVente(Feve f, double quantiteEnKg, double coursEnEuroParKg);

}
