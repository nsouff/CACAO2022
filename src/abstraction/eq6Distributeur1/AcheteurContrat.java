package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class AcheteurContrat extends DistributeurChocolatDeMarque implements IAcheteurContratCadre{//leorouppert
	protected Journal jounralContratCadre;
	protected List<ExemplaireContratCadre> mesContrats;
	private final double SEUIL_DELTA_ECHEANCE_PROPOSEE = 0.2;
	private final double SEUIL_AJOUT_ECHEANCE = 0.3;
	
	public AcheteurContrat() {
		super();
		mesContrats = new ArrayList<ExemplaireContratCadre>();
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
			e.ajouter( this.partCC*(Filiere.LA_FILIERE.getVentes(c, i-24)*partDuMarcheVoulu(c.getChocolat()) - aCombler.getQuantite(i)));
		}
		return e;
	}

	

	public Map<ChocolatDeMarque, Echeancier> nouveauxEcheanciersVoulus() {
		Map<ChocolatDeMarque, Echeancier> res = new HashMap<ChocolatDeMarque, Echeancier>();
		Map<ChocolatDeMarque, Echeancier> echeancierTotal = getEchenaceParChoco();
		for (ChocolatDeMarque choco : Filiere.LA_FILIERE.getChocolatsProduits()) {
			Echeancier eChoco = echeancierTotal.get(choco);
			Echeancier e = createEcheancier(eChoco, Filiere.LA_FILIERE.getEtape()+1, choco);
			if (e.getQuantiteTotale() > SEUIL_AJOUT_ECHEANCE*attenduNProchainesEtapes(24, choco)*partDuMarcheVoulu(choco.getChocolat())) {// Sinon cela ne sert à rien de faire un nouveau contrat cadre
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
			Echeancier aAjouterChoco = aAjouter.get(choco);
			if (aAjouterChoco != null) {
				for (IVendeurContratCadre vendeur : supCCadre.getVendeurs(choco)) {
					supCCadre.demandeAcheteur((IAcheteurContratCadre)this, vendeur, choco, aAjouter.get(choco), this.cryptogramme, false);
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

