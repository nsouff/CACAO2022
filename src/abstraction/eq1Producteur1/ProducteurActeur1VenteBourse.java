package abstraction.eq1Producteur1;

import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.bourseCacao.IVendeurBourse;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.Feve;

public class ProducteurActeur1VenteBourse extends Producteur1Acteur implements IVendeurBourse{
	
	//Auteur : Khéo
	private HashMap<Feve, Double> prixmoyenFeve ;
	/**
	 * @param feve
	 * @param stock
	 */
	public ProducteurActeur1VenteBourse() {
		super();
		this.prixmoyenFeve = new HashMap<Feve, Double>() ;
		for (Feve f : Feve.values()){
			this.getPrixmoyenFeve().put(f, 0.0); //On initialise la HashMap avec des prix à 0 
		}

	}




	//Auteur : Khéo
	public double offre(Feve f, double cours) {
		//On met à jour les prix de la HashMap
		this.getPrixmoyenFeve().put(f, this.getPrixmoyenFeve().get(f)+cours);
		
		
		//On vends en fonction du prix
		if (Filiere.LA_FILIERE.getEtape()>=1) {
			if ((this.getPrixmoyenFeve().get(f)/Filiere.LA_FILIERE.getEtape()) < cours) {
				return this.getStock(f);
			}
		}
		
		return 0.0 ;
	}



	/**
	 * @return the prixmoyenFeve
	 */
	//Auteur : Khéo
	public HashMap<Feve, Double> getPrixmoyenFeve() {
		return prixmoyenFeve;
	}



	//Auteur : Khéo
	public void notificationVente(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		// TODO Auto-generated method stub
		this.retirerQuantite(f, quantiteEnKg);
	}
}
