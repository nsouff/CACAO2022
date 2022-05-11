package abstraction.eq6Distributeur1;


import abstraction.eq8Romu.appelsOffres.IAcheteurAO;
import abstraction.eq8Romu.appelsOffres.OffreVente;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;
import abstraction.eq8Romu.general.Journal;


/**
 * @author Nathan
 */
public class AcheteurAO extends Acheteur_Contrat implements IAcheteurAO {

    private Journal journalAO;
    
    public AcheteurAO() {
        super();
        journalAO = new Journal("Journal pour les AO", this);
        journaux.add(journalAO);
    }
    
    /** 
     * @author Nathan
     */
    @Override
    public double proposerPrix(OffreVente offre) {
        if (this.getNotreStock().getStock(offre.getChocolat()) < 1) {
            journalAO.ajouter("Nous refusons proposition: " + offre);
            return 0.0;
        }
        if (offre.enTG()) {
            journalAO.ajouter("Nous proposons un prix de 80 pour l'offre " + offre);
            return 80.0;
        }
        journalAO.ajouter("Nous proposons un prux de 100 pour l'offre " + offre);
        return 100.0;
    }

    /**
     * @author Nathan
     */
    @Override
    public void notifierAchatAO(PropositionAchatAO propositionRetenue) {
        journalAO.ajouter("Note proposition a été accepté, la prop retenue est " + propositionRetenue);
        this.getNotreStock().addQte(propositionRetenue.getOffre().getChocolat(), (Double) propositionRetenue.getOffre().getQuantiteKG());
    }

    /**
     * @author Nathan
     * 
     */
    @Override
    public void notifierPropositionNonRetenueAO(PropositionAchatAO propositionNonRetenue) {
        journalAO.ajouter("Notre proposition " + propositionNonRetenue + " n'a pas été accepté.");
    }

}
