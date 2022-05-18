package abstraction.eq6Distributeur1;

import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Acheteur_Contrat extends DistributeurChocolatDeMarque implements IAcheteurContratCadre{//leorouppert

	protected Journal jounralContratCadre;
	
	public Acheteur_Contrat() {
		super();
		jounralContratCadre = new Journal("Journal pour les contrat cadre", this);
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

	@Override
	public boolean achete(Object produit) {
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
		if ((ech.getQuantite(ech.getStepDebut()) < 1000)) {
			ech.set(ech.getStepDebut(), 1000);
			ech.set(ech.getStepDebut()+1, 1000);
			ech.set(ech.getStepDebut()+2, 1000);
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
	}

	@Override
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		this.getNotreStock().addQte((ChocolatDeMarque) produit, quantite);
		this.setPrixVente((ChocolatDeMarque) produit, contrat.getPrix(), contrat.getQuantiteTotale());
	}
}

