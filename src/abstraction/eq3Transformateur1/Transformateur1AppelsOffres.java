package abstraction.eq3Transformateur1;

import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.appelsOffres.IVendeurAO;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;
import abstraction.eq8Romu.appelsOffres.SuperviseurVentesAO;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Chocolat;

import abstraction.eq8Romu.produits.ChocolatDeMarque;




public class Transformateur1AppelsOffres extends Transformateur1ContratCadreAcheteur implements IVendeurAO {

	

	protected String marque;
	protected double prixMin;
	protected SuperviseurVentesAO superviseur;
	protected double stockRestant;
	protected HashMap<Chocolat,Double> stockChoco;
	
	/** auteur Ilyas */
	public void initialiser() {
		this.superviseur = (SuperviseurVentesAO)(Filiere.LA_FILIERE.getActeur("Sup.AO"));}
	
	public void next() {
		
		if (Filiere.LA_FILIERE.getEtape()>=1) {
			for (Chocolat c : stockChoco.keySet()) {

				if (stockChoco.get(c)>=0) {
					ChocolatDeMarque coco= new ChocolatDeMarque(c, "cote d'or");
					double stock= stockChoco.get(c);
					PropositionAchatAO retenue = superviseur.vendreParAO(this, cryptogramme, coco, stockChoco.get(c), false);
					stockChoco.put(c, stock-retenue.getOffre().getQuantiteKG());
				
				
					
				}
				
			}
		}
	}

	/** renvoie la meilleure proposition si celle-ci satisfait au vendeur; auteur Ilyas 
	/** on veut faire par type de chocolat */

	public PropositionAchatAO choisir(List<PropositionAchatAO> propositions) {
		
		if (propositions==null) {
			return null;
		} else {
			PropositionAchatAO retenue = propositions.get(0);
			if (retenue.getPrixKg()>this.prixMin) {
				
				return retenue;
			} else {
				
				return null;
			}
		}
	}
		}


