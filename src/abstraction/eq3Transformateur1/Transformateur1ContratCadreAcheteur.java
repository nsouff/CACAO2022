package abstraction.eq3Transformateur1;

import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Feve;

public class Transformateur1ContratCadreAcheteur extends Transformateur1ContratCadreVendeur implements IAcheteurContratCadre {
	
	protected List<ExemplaireContratCadre> mesContratEnTantQueAcheteur;
	
	public Transformateur1ContratCadreAcheteur() {
		super();
		this.mesContratEnTantQueVendeur=new LinkedList<ExemplaireContratCadre>();
	}
	/** On décide d'initier un nouveau contrat cadre lorsque moins de 50% de la quantité de fèves désrirées provient de contrats cadre.
	 *  Alexandre*/
	public boolean achete(Object produit) {
		//calcule la quantité de fèves reçues ce tour-ci par les contrats cadres déjà signés
		if (produit instanceof Feve) {
			if (((Feve)produit) == Feve.FEVE_BASSE || ((Feve)produit) == Feve.FEVE_MOYENNE || ((Feve)produit) == Feve.FEVE_MOYENNE_BIO_EQUITABLE) {
				double quantiteFeveContrat = 0. ;
				for (ExemplaireContratCadre c : this.mesContratEnTantQueAcheteur) {
					if (produit == c.getProduit()) {
						quantiteFeveContrat = quantiteFeveContrat + c.getQuantiteALivrerAuStep();
					}
				}
				// décide si on est ouvert ou non à des propositions
				if (quantiteFeveContrat < 0.5*this.quantiteAchatFeve.get(produit)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		
	}
	
	

}
