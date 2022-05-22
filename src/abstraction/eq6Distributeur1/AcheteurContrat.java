package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class AcheteurContrat extends DistributeurChocolatDeMarque implements IAcheteurContratCadre{//leorouppert
	protected Map<ChocolatDeMarque, Echeancier> echeanceTotal;
	protected Journal jounralContratCadre;
	protected List<ExemplaireContratCadre> mesContrats;

	
	public AcheteurContrat() {
		super();
		echeanceTotal = new HashMap<ChocolatDeMarque, Echeancier>();
		mesContrats = new ArrayList<ExemplaireContratCadre>();
		jounralContratCadre = new Journal("Journal pour les contrat cadre", this);
	}

	/**
	 * @author Nathan
	 */
	@Override
	public void initialiser() {
		for (ChocolatDeMarque choco : Filiere.LA_FILIERE.getChocolatsProduits()) {
			System.out.println(choco);
			echeanceTotal.put(choco, new Echeancier());
		}
	}

	/**
	 * @author Nathan,
	 * @return La liste des journaux renvoyé par distributeurChocolatDeMarque en ajoutant le journal pour les contrats cadre
	 */
	@Override
	public List<Journal> getJournaux() {
		List<Journal> l = super.getJournaux();
		l.add(jounralContratCadre);
		return l;
	}
	/**
	 * @author Leo, Emma, Nathan
	 */
	@Override
	public boolean achete(Object produit) {
		if (NotreStock.seuilSecuFaillite() == false) {
			return false;
		}
		if (produit instanceof ChocolatDeMarque && this.getNotreStock().getStock((ChocolatDeMarque) produit) <= 1000) {
			switch (((ChocolatDeMarque) produit).getChocolat()) {
				case BQ:
				case BQ_O:
				case MQ:
				case MQ_BE:
				case MQ_O:
				case HQ:
				case HQ_BE:
					jounralContratCadre.ajouter("Nous cherchons à acheter le chocolat " + ((ChocolatDeMarque)produit));
					return true;
				default:
					jounralContratCadre.ajouter("Le chocolat " + ((ChocolatDeMarque)produit) + " ne correspond pas à ce que nous cherchons");
					return false;
			}
		}
		return false;
	}
	@Override
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		if (contrat.getPrix() < 5) {
			jounralContratCadre.ajouter("Nous acceptons la proposition " + contrat.getEcheancier());
			return contrat.getEcheancier();
		}
		Echeancier ech = contrat.getEcheancier();
		if ((ech.getQuantite(ech.getStepDebut()) < 10000)) {
			ech.set(ech.getStepDebut(), 10000);
			ech.set(ech.getStepDebut()+1, 10000);
			ech.set(ech.getStepDebut()+2, 10000);
		}
		jounralContratCadre.ajouter("Nous faisons une contre proposition pour le contrat" + contrat + ". Le nouvel écheancier est " + ech);
		return ech;
	}

	@Override
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		if (Math.random() < 0.5) {
			jounralContratCadre.ajouter("Nous acceptons le prix proposé pour " + contrat);
			return contrat.getPrix();
		}
		jounralContratCadre.ajouter("Contre proposition en proposant 0.95 du prix pour " + contrat);
		return 0.95*contrat.getPrix();
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		jounralContratCadre.ajouter("Negociation réussie pour le contrat " + contrat);
		this.setPrixVente((ChocolatDeMarque)contrat.getProduit(), contrat.getPrix());
		this.mesContrats.add(contrat);
		Echeancier eContrat = contrat.getEcheancier();
		Echeancier eChocoTotal = echeanceTotal.get(contrat.getProduit()); 
		for (int i = eContrat.getStepDebut(); i <= eContrat.getStepFin(); i++) {
			double qteStepI = eChocoTotal.getQuantite(i);
			eChocoTotal.set(i, eContrat.getQuantite(i) + qteStepI);
		}
	}

	@Override
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		this.getNotreStock().addQte((ChocolatDeMarque) produit, quantite);
		this.setPrixVente((ChocolatDeMarque) produit, contrat.getPrix());
	}

	private void suppAnciensContrats() {//leorouppert
		List<ExemplaireContratCadre> aSupprimer = new ArrayList<ExemplaireContratCadre>();
		for (ExemplaireContratCadre contrat : mesContrats) {
			if (contrat.getQuantiteRestantALivrer() == 0.0 && contrat.getMontantRestantARegler() == 0.0) {
				aSupprimer.add(contrat);
			}
		}
		mesContrats.removeAll(aSupprimer);		
	}


	/**
	 * @author Nathan
	 */
	@Override
	public void next() {
		super.next();
		this.suppAnciensContrats();
	}
}

