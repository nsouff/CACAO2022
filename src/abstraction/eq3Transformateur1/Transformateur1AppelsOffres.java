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
	protected SuperviseurVentesAO superviseurAO;
	
	public Transformateur1AppelsOffres() { 
		super();
	}
	/** auteur Ilyas */
	public void initialiser() {
		super.initialiser();
		this.superviseurAO = (SuperviseurVentesAO)(Filiere.LA_FILIERE.getActeur("Sup.AO"));}
	
	public void next() {
		super.next();
	}

	/** renvoie la meilleure proposition si celle-ci satisfait au vendeur; auteur Ilyas 
	/** on veut faire par type de chocolat */

	public PropositionAchatAO choisir(List<PropositionAchatAO> propositions) {
		
		if (propositions==null) {
			return null;
		} else {
			PropositionAchatAO retenue = propositions.get(0);
			if (retenue.getPrixKg()>this.prixVenteMin.get(propositions.get(0).getOffre().getChocolat().getChocolat())) {
				return retenue;
			} else {
				
				return null;
			}
		}
	}
		}
