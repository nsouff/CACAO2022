package abstraction.eq1Producteur1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.Feve;

public class Producteur1ContratCadre extends ProducteurActeur1VenteBourse implements IVendeurContratCadre {
	
	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeur;
	private Journal contratCadre;

	//Auteur : Khéo (sur toute la partie)
	// Pour l'instant selon ma seule décision, le but d'un condrat cadre sera d'écouler les stocks non vendu. Donc on va partir sur la vente
	//d'une quantité de 100000 tonnes à un prix pouvant aller jusuqu'a 25% inférieur au prix moyen de la bourse
	public Producteur1ContratCadre() {	
		super();
		this.mesContratEnTantQueVendeur=new LinkedList<ExemplaireContratCadre>();
		this.contratCadre = new Journal(this.getNom()+" ContratCadre", this);
	}
	
	@Override
	//Auteur : Khéo
	public boolean vend(Object produit) {
		if(produit instanceof Feve) {
			if(this.getStock((Feve)produit, false)>100000) { //On peut initier la vente si on a les bonnes quantités
				this.getContratCadre().ajouter("Ready to sell");
				return true;
			}
		}
		return false; 
	}

	@Override
	//Auteur : Khéo
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if (contrat.getEcheancier().getQuantiteTotale()<this.getStock((Feve)contrat.getProduit(), false)) {
			if (contrat.getEcheancier().getQuantiteTotale()<100000) {
				contrat.getEcheancier().ajouter(100000-contrat.getEcheancier().getQuantiteTotale());
				this.getContratCadre().ajouter("On ajoute une quantité pour un nouveau contrat");
				return contrat.getEcheancier();
			} else { //Quantité demandé acceptable
				this.getContratCadre().ajouter("Quantité demandé acceptable");
				return contrat.getEcheancier(); 
			}
			
		} else { //Pas assez de quantité dans le stock présent
			return null;
		}
		
	}

	@Override
	//Auteur : Khéo
	public double propositionPrix(ExemplaireContratCadre contrat) {
		if (this.getPrixmoyenFeve().keySet().contains(contrat.getProduit())) {
			return this.getPrixmoyenFeve().get(contrat.getProduit())*1000;
		} else {
			System.out.println(" keyset ===>"+this.getPrixmoyenFeve().keySet());
			System.out.println(" produit===>"+contrat.getProduit());
			return 0.0;
		}
		// mis en commentaire par Romu car l'acces a la hashmap avec une cle ne figurant pas dans le keyset de la hashmap leve une exception et empeche tout acteur voulant vous avoir pour vendeur de contrat cadre a ne pas pouvoir faire de pull request.
		// return this.getPrixmoyenFeve().get(contrat.getProduit())*1000; //On propose le prix moyen au départ
	}

	@Override
	//Auteur : Khéo
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		double prixmoyen = this.getPrixmoyenFeve().get(contrat.getProduit());
		if(contrat.getPrix()<prixmoyen*0.75*1000) {
			this.getContratCadre().ajouter("Prix qui passe pas");
			return prixmoyen*0.75*1000;	
		}
		this.getContratCadre().ajouter("Prix qui passe");
		return contrat.getPrix();
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		this.getContratCadre().ajouter("Mashallah ce nouveau contrat");
		
	}



	@Override
	//Copié sur le code de l'équipe 8 on veut livrer le plus possible dans tout les cas 
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		double livre = Math.min(this.getStock((Feve)contrat.getProduit(), false), quantite);
		if (livre>0.0) {
			this.retirerQuantite((Feve)contrat.getProduit(), livre);;
		}
		this.getContratCadre().ajouter("Livraison faite zebi");
		return livre;
		
	}
	
	/**
	 * @return the contratCadre
	 */
	public Journal getContratCadre() {
		return contratCadre;
	}
	
	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		res.add(this.getContratCadre());
		res.add(this.getAfrique().getRetourMAJParc());
		res.add(this.getAfrique().getRetourRécolte());
		res.add(this.getAfrique().getRetourGuerre());
		res.add(this.getAfrique().getRetourAléas());
		res.add(this.getAfrique().getRetourMaladie());
		return res;
	}

}
