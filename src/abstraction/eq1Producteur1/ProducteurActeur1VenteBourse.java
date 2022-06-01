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
		
			//On vend en fonction du prix
			if(f!=Feve.FEVE_HAUTE_BIO_EQUITABLE) { //Pas de bourse pour le HAUT_BE
			if (Filiere.LA_FILIERE.getEtape()>=1) {
				// Ici, étape + 1 car la 1e etape est l'étape 0, et on y rentre le cours
				
				if (this.getStock(f, false)<1000000) {
					double tauxdecroit = ((0.75-1)/1000000)*this.getStock(f, false) + 1 ; //Taux décroissant sur 1 million jusqu'à 75 %
					System.out.println("tauxdecroissant :" +tauxdecroit);
					System.out.println(((0.75-1)/1000000)*this.getStock(f, false));
					System.out.println(f);
					System.out.println(this.getStock(f, false));
					System.out.println("Comparateur "+(this.getPrixmoyenFeve().get(f)/(Filiere.LA_FILIERE.getEtape()+1))*tauxdecroit);
					System.out.println("Cours :"+cours);
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
	

	/**
	 * offre2 (pour conserver la V1 avant d'être sûr)
	 * @author laure
	 * @param f la fève qu'on veut vendre
	 * @param cours le cours actuel
	 * @return quantité de fèves à vendre
	 */
	public double offre2(Feve f, double cours) {
		double coutStockage=Filiere.LA_FILIERE.getParametre("Prix Stockage").getValeur(); 
		double coutProduction=this.getPrixEntretienArbre().getValeur();
		double coutTotalFeve= coutProduction + coutStockage*this.getFeves().size();
		if(f!=Feve.FEVE_HAUTE_BIO_EQUITABLE) { //Pas de bourse pour le HAUT_BE 
			if (Filiere.LA_FILIERE.getEtape()>=1) { // Ici, étape + 1 car la 1e etape est l'étape 0, et on y rentre le cours
				if(f==Feve.FEVE_BASSE) {
					if (cours>coutTotalFeve) {
						return this.getStock(f, false)*0.3;
					}
				} else 	if (f==Feve.FEVE_MOYENNE) {
					if (cours>coutTotalFeve*1.2) {
						return this.getStock(f, false)*0.2;
					}
				} else if(f==Feve.FEVE_HAUTE) {
					if (cours>coutTotalFeve*1.5) {
						return (this.getStock(f, false)*0.2);
					}
				}
			}
		}
		return 0.0 ; 
	}

}
