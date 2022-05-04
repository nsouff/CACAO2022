package abstraction.eq3Transformateur1;

import java.util.List;

import abstraction.eq8Romu.appelsOffres.IVendeurAO;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;
import abstraction.eq8Romu.appelsOffres.SuperviseurVentesAO;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Chocolat;


public class Transformateur1AppelsOffres extends Transformateur1ContratCadreAcheteur implements IVendeurAO {

	protected Variable stock;
	protected Chocolat choco;
	protected String marque;
	protected double prixMin;
	protected SuperviseurVentesAO superviseur;
	protected double stockRestant;
	
	/** auteur Ilyas */
	public void initialiser() {
		this.superviseur = (SuperviseurVentesAO)(Filiere.LA_FILIERE.getActeur("Sup.AO"));}
	
	public void next() {
		
		if (Filiere.LA_FILIERE.getEtape()>=1) {
			if (stockRestant != 0) {
				PropositionAchatAO retenue = superviseur.vendreParAO(this, cryptogramme, getChocolatDeMarque(), stockRestant, false);
				if (retenue!=null) {
					this.stock.setValeur(this, this.stock.getValeur()-retenue.getOffre().getQuantiteKG());
					
				} else {
					
				}
			}
		}
	}
	
	/** renvoie la meilleure proposition si celle-ci satisfait au vendeur; auteur Ilyas */
	
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


