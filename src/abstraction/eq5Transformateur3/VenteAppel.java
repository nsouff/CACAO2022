package abstraction.eq5Transformateur3;

import java.util.List;

import abstraction.eq8Romu.appelsOffres.IVendeurAO;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;
import abstraction.eq8Romu.appelsOffres.SuperviseurVentesAO;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class VenteAppel extends AcheteurContrat implements IVendeurAO {
	public SuperviseurVentesAO superviseur = (SuperviseurVentesAO)(Filiere.LA_FILIERE.getActeur("Sup.AO"));

	//Karla et Julien
	public PropositionAchatAO choisir(List<PropositionAchatAO> propositions) {
		/* on pourrait comparer les acheteurs en fonction de la fidélité par ex 
		 * on initie original à 0 de façon à pouvoir calculer le prix qui permet de ne pas vendre à perte
		 * et lameilleure (proposition) à null 
		 */
		int original = 0;
		PropositionAchatAO lameilleure = null;

		for (PropositionAchatAO proposition: propositions) { 
			if (proposition.getOffre().getChocolat().isOriginal()) { 
				original = 1;
				}
			if (proposition.getPrixKg()>= this.seuilMaxAchat + this.coutOriginal.getValeur()*original + this.coutTransformation.getValeur()) {
				lameilleure = proposition ;	
				}
			}
		return lameilleure;
		}
	
	//Karla 
	public void next() {
		super.next();
		
		for (Chocolat c: this.stockChocolat.getProduitsEnStock()) {
			/* Si on a un stock suffisant, on essaie de liquider la moitie */
			if (this.stockChocolat.getstock(c)>this.SeuilMinChocolat) {
				PropositionAchatAO retenueenTG = 
					superviseur.vendreParAO(this, this.cryptogramme, new ChocolatDeMarque(c,"BIO'riginal"), this.stockChocolat.getstock(c)/2, true);
					if (retenueenTG!=null) {
						this.stockChocolat.utiliser(c, retenueenTG.getOffre().getQuantiteKG()); 
						journal.ajouter("vente de "+retenueenTG.getOffre().getQuantiteKG()+" de " + c +" kg a "+retenueenTG.getAcheteur().getNom());
					} else {
						PropositionAchatAO retenuepasenTG = 
								superviseur.vendreParAO(this, this.cryptogramme, new ChocolatDeMarque(c,"BIO'riginal"), this.stockChocolat.getstock(c)/2, false);
						if (retenuepasenTG!=null) {
							this.stockChocolat.utiliser(c, retenuepasenTG.getOffre().getQuantiteKG()); 
							journal.ajouter("vente de "+retenuepasenTG.getOffre().getQuantiteKG()+" de " + c +" kg a "+retenuepasenTG.getAcheteur().getNom());
						} else {
							journal.ajouter("pas d'offre retenue");
						}
					}
				}
			}
		}
			

}