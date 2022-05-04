package abstraction.eq6Distributeur1;


import abstraction.eq8Romu.appelsOffres.IAcheteurAO;
import abstraction.eq8Romu.appelsOffres.OffreVente;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;


/**
 * @author Nathan
 */
public class AcheteurAO extends Acheteur_Contrat implements IAcheteurAO {

    
    public AcheteurAO() {
        super();
    }
    
    /** 
     * @author Nathan
     */
    @Override
    public double proposerPrix(OffreVente offre) {
        if (this.getNotreStock().getStock(offre.getChocolat()) < 1) {
            return 0.0;
        }
        if (offre.enTG()) {
            return 80.0;
        }
        return 100.0;
    }

    /**
     * @author Nathan
     */
    @Override
    public void notifierAchatAO(PropositionAchatAO propositionRetenue) {
        this.getNotreStock().addQte(propositionRetenue.getOffre().getChocolat(), (Double) propositionRetenue.getOffre().getQuantiteKG());
    }

    /**
     * @author Nathan
     * 
     */
    @Override
    public void notifierPropositionNonRetenueAO(PropositionAchatAO propositionNonRetenue) {
        // Rien pour l'instant
    }

}
