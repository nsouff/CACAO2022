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
	}




	//Auteur : Khéo
	public double offre(Feve f, double cours) {
		//On met à jour les prix de la HashMap
		if(f!=Feve.FEVE_HAUTE_BIO_EQUITABLE) { //Pas de bourse pour le HAUT_BE
			
		if (Filiere.LA_FILIERE.getEtape()>1) { //Petite dijonction de cas pour le premier tour afin d'éviter d'aller chercher dans une hashmap vide
			this.getPrixmoyenFeve().put(f, this.getPrixmoyenFeve().get(f)+cours);
			
		} else {
			this.getPrixmoyenFeve().put(f, cours);
			
		}
		return 0.0 ; 
		
		}
		
		
		
		//On vends en fonction du prix
		if (Filiere.LA_FILIERE.getEtape()>=1) {

			if ((this.getPrixmoyenFeve().get(f)/(Filiere.LA_FILIERE.getEtape())) <= cours) {
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
