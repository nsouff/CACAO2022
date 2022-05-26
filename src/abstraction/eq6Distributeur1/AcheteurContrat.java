package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class AcheteurContrat extends DistributeurChocolatDeMarque implements IAcheteurContratCadre{//leorouppert
	protected Journal journalContratCadre;
	protected List<ExemplaireContratCadre> mesContrats;
	private final double SEUIL_DELTA_ECHEANCE_PROPOSEE = 0.2;
	private final double SEUIL_AJOUT_ECHEANCE = 0.3;
	private final int STEP_INTERSECTION_MIN = 6;
	private final double PRIX_LIMITE = 1.2;
	
	public AcheteurContrat() {
		super();
		mesContrats = new ArrayList<ExemplaireContratCadre>();
		journalContratCadre = new Journal("Journal pour les contrat cadre", this);
	}


	/**
	 * @author Nathan,
	 * @return La liste des journaux renvoyé par distributeurChocolatDeMarque en ajoutant le journal pour les contrats cadre
	 */
	@Override
	public List<Journal> getJournaux() {
		List<Journal> l = super.getJournaux();
		l.add(journalContratCadre);
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
					return true;
				default:
					return false;
			}
		}
		return false;
	}

	public int[] interesctionEtapes(Echeancier e1, Echeancier e2) {
		int[] res = {0, 0};
		res[0] = (e1.getStepDebut() < e2.getStepDebut()) ? e2.getStepDebut() : e1.getStepDebut();
		res[1] = (e1.getStepFin() < e2.getStepFin()) ? e1.getStepFin() : e2.getStepFin();
		if (res[0] > res[1]) {
			return null;
		}
		return res;
	}

	public double echenacierDelta(Echeancier e1, Echeancier e2) {
		int[] intersection = interesctionEtapes(e1, e2);
		if (intersection == null || intersection[1] - intersection[0] < STEP_INTERSECTION_MIN) {
			return 1.0;
		}
		double delta = 0.0;
		for (int i = intersection[0]; i <= intersection[1]; i++) {
			delta += e2.getQuantite(i) - e1.getQuantite(i);
		}
		return delta / getSomme(intersection[0], intersection[1], e2);
	}
	
	@Override
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		Echeancier voulu = nouveauxEcheanciersVoulus().get(contrat.getProduit());
		if (voulu == null) return null;
		double delta = echenacierDelta(contrat.getEcheancier(), voulu);
		System.out.println(delta);
		if (delta <= SEUIL_DELTA_ECHEANCE_PROPOSEE) return contrat.getEcheancier();
		return null;


		// if (contrat.getPrix() < 15) {
		// 	return contrat.getEcheancier();
		// }
		// if (contrat.getPrix() < 5) {
		// 	journalContratCadre.ajouter("Nous acceptons l'échenacier proposé " + contrat.getEcheancier());
		// 	return contrat.getEcheancier();
		// }
		// Echeancier ech = contrat.getEcheancier();
		// if ((ech.getQuantite(ech.getStepDebut()) < 10000)) {
		// 	ech.set(ech.getStepDebut(), 10000);
		// 	ech.set(ech.getStepDebut()+1, 10000);
		// 	ech.set(ech.getStepDebut()+2, 10000);
		// }
		// journalContratCadre.ajouter("Nous faisons une contre proposition pour le contrat" + contrat + ". Le nouvel écheancier est " + ech);
		// return ech;
	}

	@Override
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		if (contrat.getPrix() > PRIX_LIMITE * 7.5* facteurPrixChocolat(((ChocolatDeMarque) contrat.getProduit()).getChocolat())) {
			return 7.5 * facteurPrixChocolat(((ChocolatDeMarque) contrat.getProduit()).getChocolat());
		}
		else {
			return contrat.getPrix();
		}

		// if (Math.random() < 0.5) {
		// 	journalContratCadre.ajouter("Nous acceptons le prix proposé pour " + contrat);
		// 	return contrat.getPrix();
		// }
		// journalContratCadre.ajouter("Contre proposition en proposant 0.95 du prix pour " + contrat);
		// return 0.95*contrat.getPrix();
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		journalContratCadre.ajouter("Negociation réussie pour le contrat " + contrat);
		this.setPrixVente((ChocolatDeMarque)contrat.getProduit(), contrat.getPrix());
		this.mesContrats.add(contrat);
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
	 * Permet d'avoir un echenacier additionnant tous les echeancier d'un chocolat 
	 * @return un echeancier de ce qu'il manque à fournir dans nos prévision
	 */
	public Map<ChocolatDeMarque, Echeancier> getEchenaceParChoco() {
		Map<ChocolatDeMarque, Echeancier> res = new HashMap<ChocolatDeMarque, Echeancier>();
		for (ChocolatDeMarque choco : Filiere.LA_FILIERE.getChocolatsProduits()) {
			res.put(choco, new Echeancier());
		}
		for (ExemplaireContratCadre ecd : mesContrats) {
			ChocolatDeMarque cm = (ChocolatDeMarque) ecd.getProduit();
			Echeancier e = ecd.getEcheancier();
			int start = (e.getStepDebut() < Filiere.LA_FILIERE.getEtape()+1) ? Filiere.LA_FILIERE.getEtape()+1 : e.getStepDebut();
			for (int i = start; i < e.getStepFin(); i++) {
				res.get(cm).set(i, res.get(cm).getQuantite(i) + e.getQuantite(i));
			}
		}
		return res;
	}

	public double getSomme(int stepDebut, int stepFin, Echeancier e) {
		double res = 0;
		for (int i = stepDebut; i <= stepFin; i++) {
			res += e.getQuantite(i);
		}
		return res;
	}

	public double attenduNProchainesEtapes(int n, ChocolatDeMarque choco) {
		double res = 0;
		int ajd = Filiere.LA_FILIERE.getEtape();
		for (int i = ajd+1; i <= n+ajd; i++) {
			res += Filiere.LA_FILIERE.getVentes(choco, i-24);
		}
		return res;
	}

	private Echeancier createEcheancier(Echeancier aCombler, int stepDebut, ChocolatDeMarque c) {
		Echeancier e = new Echeancier(stepDebut);
		for (int i = stepDebut; i < stepDebut + 24; i++) {
			double aComblerI = (aCombler == null) ? 0 : aCombler.getQuantite(i);
			e.ajouter(getPartMarque(c) * partCC*Filiere.LA_FILIERE.getVentes(c, i-24) * partDuMarcheVoulu(c.getChocolat()) - aComblerI);
		}
		return e;
	}

	

	public Map<ChocolatDeMarque, Echeancier> nouveauxEcheanciersVoulus() {
		Map<ChocolatDeMarque, Echeancier> res = new HashMap<ChocolatDeMarque, Echeancier>();
		Map<ChocolatDeMarque, Echeancier> echeancierTotal = getEchenaceParChoco();
		for (ChocolatDeMarque choco : Filiere.LA_FILIERE.getChocolatsProduits()) {
			Echeancier eChoco = echeancierTotal.get(choco);
			Echeancier e = createEcheancier(eChoco, Filiere.LA_FILIERE.getEtape()+1, choco);
			if (e.getQuantiteTotale() >= SuperviseurVentesContratCadre.QUANTITE_MIN_ECHEANCIER && e.getQuantiteTotale() > SEUIL_AJOUT_ECHEANCE*attenduNProchainesEtapes(24, choco)*partDuMarcheVoulu(choco.getChocolat())*partCC*getPartMarque(choco)) {// Sinon cela ne sert à rien de faire un nouveau contrat cadre
				res.put(choco, e);
			}
		}
		return res;
	}


	/**
	 * @author Nathan
	 */
	@Override
	public void next() {
		super.next();
		this.suppAnciensContrats();
		Map<ChocolatDeMarque, Echeancier> aAjouter = nouveauxEcheanciersVoulus();
		for (ChocolatDeMarque choco : Filiere.LA_FILIERE.getChocolatsProduits()) {
			for (IVendeurContratCadre vendeur : supCCadre.getVendeurs(choco)) {
				Echeancier aAjouterChoco = aAjouter.get(choco);
				if (aAjouterChoco != null) {
					ExemplaireContratCadre ecc = supCCadre.demandeAcheteur((IAcheteurContratCadre)this, vendeur, choco, aAjouterChoco, this.cryptogramme, false);
					if (ecc != null) {
						mesContrats.add(ecc);
						aAjouter = nouveauxEcheanciersVoulus();
						System.out.println(ecc.getPrix());
						this.setPrixVente(choco, ecc.getPrix());
					}
				}
			}
		}
		//nouveauContrat();
		// this.getNotreStock().getMapStock().forEach((key,value)->{
		// 	if (value <= 10000) {
		// 		journal1.ajouter("Recherche d'un vendeur aupres de qui acheter");
		// 		List<IVendeurContratCadre> ListeVendeurs = supCCadre.getVendeurs(key);
		// 		if (ListeVendeurs.size() != 0) {
		// 			IVendeurContratCadre Vendeur = ListeVendeurs.get(ran.nextInt(ListeVendeurs.size()));
		// 			journal1.ajouter("Demande au superviseur de debuter les negociations pour un contrat cadre de "+key+" avec le vendeur "+Vendeur);
		// 			ExemplaireContratCadre CC = supCCadre.demandeAcheteur((IAcheteurContratCadre)this,Vendeur, value, new Echeancier(Filiere.LA_FILIERE.getEtape()+1,12,10000), cryptogramme, false);
		// 			if (CC == null) {
		// 				journal1.ajouter("-->aboutit au contrat "+ CC);
		// 			}
		// 			else {
		// 				journal1.ajouter("échec des négociations");
		// 			}
		// 		}
		// 	}	
		// });
	}
}

