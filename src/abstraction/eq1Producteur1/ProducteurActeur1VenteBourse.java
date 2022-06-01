package abstraction.eq1Producteur1;

import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.bourseCacao.IVendeurBourse;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.Feve;

public class ProducteurActeur1VenteBourse extends Producteur1Producteur implements IVendeurBourse{
	
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
			
			if (Filiere.LA_FILIERE.getEtape()>=1) { //Petite dijonction de cas pour le premier tour afin d'éviter d'aller chercher dans une hashmap vide
				this.getPrixmoyenFeve().put(f, this.getPrixmoyenFeve().get(f)+cours);
			
			} else {
				this.getPrixmoyenFeve().put(f, cours);
			
			}
		
			//On vends en fonction du prix
			if(f!=Feve.FEVE_HAUTE_BIO_EQUITABLE) { //Pas de bourse pour le HAUT_BE
			if (Filiere.LA_FILIERE.getEtape()>=1) {
				// Ici, étape + 1 car la 1e etape est l'étape 0, et on y rentre le cours
				
				if (this.getStock(f, false)<1000000) {
					double tauxdecroit = ((0.75-1)/1000000)*this.getStock(f, false) + 1 ; //Taux décroissant sur 1 million jusqu'à 75 %
				if ((this.getPrixmoyenFeve().get(f)/(Filiere.LA_FILIERE.getEtape()+1))*tauxdecroit <= cours) {
					return this.getStock(f, false);
					}
			
				}
				else {
					if ((this.getPrixmoyenFeve().get(f)/(Filiere.LA_FILIERE.getEtape()+1))*0.75 <= cours) {
					return this.getStock(f, false);
					}
				}
			}
		}
		
		return 0.0 ;
	}



	/**
	 * @return the prixmoyenFeve
	 */
	//Auteur : Khéo
	public HashMap<Feve, Double> getPrixmoyenFeve() {
		
		return this.prixmoyenFeve;
	}



	//Auteur : Khéo
	public void notificationVente(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		// TODO Auto-generated method stub
		this.retirerQuantite(f, quantiteEnKg);
	}
}
