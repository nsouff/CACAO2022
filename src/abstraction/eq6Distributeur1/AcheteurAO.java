package abstraction.eq6Distributeur1;


import abstraction.eq8Romu.appelsOffres.IAcheteurAO;
import abstraction.eq8Romu.appelsOffres.OffreVente;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;


/**
 * @author Nathan
 */
public class AcheteurAO extends Distributeur1Acteur implements IAcheteurAO {

    @Override
    public double proposerPrix(OffreVente offre) {
        if (offre.enTG()) {
            return 80.0;
        }
        return 100.0;
    }

    @Override
    public void notifierAchatAO(PropositionAchatAO propositionRetenue) {
        // Il faudra mettre à jour le stock
    }

    @Override
    public void notifierPropositionNonRetenueAO(PropositionAchatAO propositionNonRetenue) {
        // Rien pour l'instant
    }

}
