package abstraction.eq5Transformateur3;

import java.util.List;

import abstraction.eq8Romu.appelsOffres.IVendeurAO;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;
import abstraction.eq8Romu.appelsOffres.SuperviseurVentesAO;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class VenteAppel extends VenteContrat implements IVendeurAO {

	// Julien 10/05
	public PropositionAchatAO choisir(List<PropositionAchatAO> propositions) {
		/* on pourrait comparer les acheteurs en fonction de la fidélité par ex 
		 * on initie original à 0 de façon à pouvoir calculer le prix qui permet de ne pas vendre à perte
		 * les propositions sont triées par ordre décroissant de prix -> on ne considère que la première pour l'instant
		 */
		int original = 0;
		PropositionAchatAO lameilleure = null;

		PropositionAchatAO proposition1=propositions.get(0);
		
		if (proposition1.getOffre().getChocolat().isOriginal()) { 
			original = 1;
				}
		journal.ajouter("Prix prop "+proposition1.getPrixKg());
		double prixrentable = this.seuilMaxAchat + this.coutOriginal.getValeur()*original + this.coutTransformation.getValeur();
		journal.ajouter("prix rentable "+ prixrentable);

		if (proposition1.getPrixKg()>= this.seuilMaxAchat + this.coutOriginal.getValeur()*original + this.coutTransformation.getValeur()) {
				lameilleure = proposition1 ;	
				}
	
		this.ventes.ajouter(lameilleure.toString()+lameilleure.getPrixKg());
		return lameilleure; // si le prix est trop faible, on prefère garder notre chocolat
		}
	
	//Karla julien
	public void next() {
		super.next();
		SuperviseurVentesAO superviseur = (SuperviseurVentesAO)(Filiere.LA_FILIERE.getActeur("Sup.AO"));
		for (Chocolat c: this.stockChocolat.getProduitsEnStock()) {
			/* Si on a un stock suffisant, on essaie de liquider la moitie */
			if (this.stockChocolat.getstock(c)>this.SeuilMinChocolat) {
				PropositionAchatAO retenueenTG = 
					superviseur.vendreParAO(this, this.cryptogramme, new ChocolatDeMarque(c,"BIO'riginal"), this.stockChocolat.getstock(c)/2, true);
					if (retenueenTG!=null) {
						this.stockChocolat.utiliser(c, retenueenTG.getOffre().getQuantiteKG()); 
						this.ventes.ajouter("vente par AO de "+retenueenTG.getOffre().getQuantiteKG()+"  kg de " + c +"  a "+retenueenTG.getAcheteur().getNom()+" en TG");
					} else {
						// on essaye sans mettre en TG
						PropositionAchatAO retenuepasenTG = 
								superviseur.vendreParAO(this, this.cryptogramme, new ChocolatDeMarque(c,"BIO'riginal"), this.stockChocolat.getstock(c)/2, false);
						if (retenuepasenTG!=null) {
							this.stockChocolat.utiliser(c, retenuepasenTG.getOffre().getQuantiteKG()); 
							this.journal.ajouter("vente par AO de "+retenuepasenTG.getOffre().getQuantiteKG()+"kg  de " + c +" a "+retenuepasenTG.getAcheteur().getNom());
						} else {
							this.journal.ajouter("pas d'offre retenue");
						}
					}
				}
			}
		}
			

}