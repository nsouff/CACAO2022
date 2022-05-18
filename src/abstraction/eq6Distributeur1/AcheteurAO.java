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
    	
    	if (NotreStock.seuilSecuFaillite() == false) {   //EmmaHumeau
    		return 0.0;
    	}


        if (this.getNotreStock().getStock(offre.getChocolat()) > 5000) {

            journalAO.ajouter("Nous refusons proposition: " + offre + " car nous avons déjà " + this.getNotreStock().getStock(offre.getChocolat())) ;
            return 0.0;
        }
        if (offre.enTG()) {
            double res = 5.1;
            journalAO.ajouter("Nous proposons un prix de 5.1 pour l'offre pour " + res + "Kg de " + offre.getChocolat());
            return res;        
        }
        double res = 7.1;
        journalAO.ajouter("Nous proposons un prix de 7.1 pour l'offre pour " + res + "Kg de " + offre.getChocolat());
        return res;
    }

    /**
     * @author Nathan
     */
    @Override
    public void notifierAchatAO(PropositionAchatAO propositionRetenue) {
        journalAO.ajouter("Note proposition a été accepté, " + propositionRetenue.getOffre().getQuantiteKG() + "kg de " + propositionRetenue.getOffre().getChocolat());
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
