package abstraction.eq3Transformateur1;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.ExempleTransformateurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Transformateur1ContratCadreVendeur extends Transformateur1Bourse implements IVendeurContratCadre{
	
	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeur;
	
	public Transformateur1ContratCadreVendeur() {
		super();
		this.mesContratEnTantQueVendeur=new LinkedList<ExemplaireContratCadre>();
	}
	
	// fonction qui détermine quel type de chocolat on vend en contrat cadre; auteur Julien */
	public boolean vend(Object produit) {
		if ((((ChocolatDeMarque)produit).getChocolat()==Chocolat.MQ)||(((ChocolatDeMarque)produit).getChocolat()==Chocolat.MQ_BE)||(((ChocolatDeMarque)produit).getChocolat()==Chocolat.MQ_O)) {
			return true;
		}
		return false;
	}

	// négocie un échancier inférieur à 6 échéances; auteur Julien */
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if (contrat.getEcheancier().getNbEcheances()<6) {
			return contrat.getEcheancier();
		}
		return null;
	}

	/** Prix initial = 1.4 x prixVenteMin 
	 *  Alexandre*/
	public double propositionPrix(ExemplaireContratCadre contrat) {
		return this.prixVenteMin.get(((ChocolatDeMarque)contrat.getProduit()).getChocolat())*1.4;
	}

	// négocie une contreproposition du prix; auteur Julien */
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		if (contrat.getPrix()>prixVenteMin.get(((ChocolatDeMarque)contrat.getProduit()).getChocolat())) {
			return contrat.getPrix();
		} else {
			return Math.max(contrat.getPrix()+prixVenteMin.get(((ChocolatDeMarque)contrat.getProduit()).getChocolat())/2.0,0.0);
		}
		
	}

	/** Ajout du contrat dans la liste de contrats à honorer
	 *  Alexandre*/
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		mesContratEnTantQueVendeur.add(contrat);
		
	}

	
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		double livre = Math.min(stockChoco.get(((ChocolatDeMarque)contrat.getProduit()).getChocolat()), quantite);
		if (livre==quantite) {
			stockChoco.put(((ChocolatDeMarque)contrat.getProduit()).getChocolat(),stockChoco.get(((ChocolatDeMarque)contrat.getProduit()).getChocolat())-quantite);
			return quantite;
		}
		stockChoco.put(((ChocolatDeMarque)contrat.getProduit()).getChocolat(),0.0);
		return livre;
	}

	
}
