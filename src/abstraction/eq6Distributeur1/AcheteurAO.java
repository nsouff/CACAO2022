package abstraction.eq6Distributeur1;


import java.util.List;

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
        journalAO.ajouter("Nous proposons un prix de 100 pour l'offre " + offre);
        return 100.0;
    }

    /**
     * @author Nathan
     */
    @Override
    public void notifierAchatAO(PropositionAchatAO propositionRetenue) {
        journalAO.ajouter("Note proposition a été accepté, la prop retenue est " + propositionRetenue);
        this.getNotreStock().addQte(propositionRetenue.getOffre().getChocolat(), (Double) propositionRetenue.getOffre().getQuantiteKG());
        this.setPrixVente(propositionRetenue.getOffre().getChocolat(), propositionRetenue.getPrixKg());
    }

    /**
     * @author Nathan
     * 
     */
    @Override
    public void notifierPropositionNonRetenueAO(PropositionAchatAO propositionNonRetenue) {
        journalAO.ajouter("Notre proposition " + propositionNonRetenue + " n'a pas été accepté.");
    }

    /**
     * @author Nathan
     * @return La liste des journaux renvoyée dans acheteurContrat et le journal d'appel d'offre
     */
    @Override
    public List<Journal> getJournaux() {
        List<Journal> j = super.getJournaux();
        j.add(journalAO);
        return j;
    }

}
