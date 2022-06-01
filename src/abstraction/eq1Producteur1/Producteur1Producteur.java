package abstraction.eq1Producteur1;

import java.util.HashMap;
import java.util.LinkedList;

import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.filiere.Filiere;


import abstraction.eq8Romu.general.Journal;

import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Feve;

public abstract class Producteur1Producteur extends Producteur1Stock{
	private LinkedList<Parc> ListeParc;
	private HashMap<Feve, Double> recolte;
	private int mecontentement_global_basse;
	private int mecontentement_global_moyenne;
	private int mecontentement_global_haute;
	
	public Producteur1Producteur() {
		super();
		ListeParc = new LinkedList<Parc>();
		Parc ghana = new Parc("Ghana", this);
		Parc cote_divoire = new Parc ("Côte d'Ivoire", this);
		Parc nigeria = new Parc("Nigéria", this);
		Parc cameroun = new Parc("Cameroun", this);
		ListeParc.add(ghana);
		ListeParc.add(cote_divoire);
		ListeParc.add(nigeria);
		ListeParc.add(cameroun);
		this.mecontentement_global_basse = 0;
		this.mecontentement_global_moyenne = 0;
		this.mecontentement_global_haute =0;
	}
	
	public Parc getGhana() {
		return ListeParc.get(0);
	}
	
	public Parc getCote_divoire() {
		return ListeParc.get(1);
	}
	
	public Parc getNigeria() {
		return ListeParc.get(2);
	}
	
	public Parc getCameroun() {
		return ListeParc.get(3);
	}
	
	public Parc getParc(int i) {
		return ListeParc.get(i);
	}

	public LinkedList<Parc> getListeParc() {
		return this.ListeParc;
	}
	
	public int getMecontentement_basse() {
		return this.mecontentement_global_basse;
	}
	
	public int getMecontentement_moyenne() {
		return this.mecontentement_global_moyenne;
	}
	
	public int getMecontentement_haute() {
		return this.mecontentement_global_haute;
	}
	
	public void setMecontentement_basse(int i) {
		if (i<0) {
			this.mecontentement_global_basse = 0;
		}
		else {
			this.mecontentement_global_basse = i;
		}
	}
	
	public void setMecontentement_moyenne(int i) {
		if (i<0) {
			this.mecontentement_global_moyenne = 0;
		}
		else {
			this.mecontentement_global_moyenne = i;
		}
	}
	
	public void setMecontentement_haute(int i) {
		if (i<0) {
			this.mecontentement_global_haute = 0;
		}
		else {
			this.mecontentement_global_haute = i;
		}
	}
	
	//Écrit par Antoine
	public void initialiser() {
		super.initialiser();
		int nombre_arbre_debut = 600000;
		double pourcentage_nBE_basse = 0.63;
		double pourcentage_nBE_moyenne = 0.27;
		double pourcentage_nBE_haute = 0;
		double pourcentage_BE_moyenne = 0.05;
		int nombre_arbre_ghana = (int)Math.floor(nombre_arbre_debut*0.62);
		int nombre_arbre_cote_divoire = (int)Math.floor(nombre_arbre_debut*0.23);
		int nombre_arbre_nigeria = (int)Math.floor(nombre_arbre_debut*0.07);
		int nombre_arbre_cameroun = (int)Math.floor(nombre_arbre_debut*0.08);
		LinkedList<Integer> NbArbres = new LinkedList<Integer>();
		NbArbres.add(nombre_arbre_ghana);
		NbArbres.add(nombre_arbre_cote_divoire);
		NbArbres.add(nombre_arbre_nigeria);
		NbArbres.add(nombre_arbre_cameroun);
		int ut_debut = -500;
		int écart_moyenne = 100;
		for (int j=0;j<ListeParc.size();j++) {
			Parc Parc_j = this.getParc(j);
			int nombre_arbre_nBE_basse = (int)Math.floor((pourcentage_nBE_basse*NbArbres.get(j)));
			int nombre_arbre_nBE_moyenne = (int)Math.floor((pourcentage_nBE_basse+pourcentage_nBE_moyenne)*NbArbres.get(j));
			int nombre_arbre_nBE_haute = (int)Math.floor((pourcentage_nBE_basse+pourcentage_nBE_moyenne+pourcentage_nBE_haute)*NbArbres.get(j));
			int nombre_arbre_BE_moyenne = (int)Math.floor((pourcentage_nBE_basse+pourcentage_nBE_moyenne+pourcentage_nBE_haute+pourcentage_BE_moyenne)*NbArbres.get(j));
			for (int i=0;i<NbArbres.get(j);i++) {
				int d = (int)Math.random()*écart_moyenne;
				if (i<nombre_arbre_nBE_basse) {
					Parc_j.Planter(new MilleArbre(1,false,false,ut_debut-d));
				}
				if ((i>=nombre_arbre_nBE_basse) && (i<nombre_arbre_nBE_moyenne)) {
					Parc_j.Planter(new MilleArbre(2,false,false,ut_debut-d));
				}
				if ((i>=nombre_arbre_nBE_moyenne) && (i<nombre_arbre_nBE_haute)) {
					Parc_j.Planter(new MilleArbre(3,false,false,ut_debut-d));
				}
				if ((i>=nombre_arbre_nBE_haute) && (i<nombre_arbre_BE_moyenne)) {
					Parc_j.Planter(new MilleArbre(2,false,true,ut_debut-d));
				}
				if ((i>=nombre_arbre_BE_moyenne)) {
					Parc_j.Planter(new MilleArbre(3,false,true,ut_debut-d));
				}
			}
		}
	}
	
	public abstract HashMap<Feve, Double> getPrixmoyenFeve();
	
	public void MAJMecontentement() {
		//Récupération prix actuels de la bourse
		BourseCacao bourse = (BourseCacao)(Filiere.LA_FILIERE.getActeur("BourseCacao"));
		double cours_feve_basse = bourse.getCours(Feve.FEVE_BASSE).getValeur();
		double cours_feve_moyenne = bourse.getCours(Feve.FEVE_MOYENNE).getValeur();
		double cours_feve_haute = bourse.getCours(Feve.FEVE_HAUTE).getValeur();
		
		int parametre_min_max = 10;
		
		HashMap<Feve, Double> prixmoyen = this.getPrixmoyenFeve();
			for (Feve f : this.getFeves().keySet()) {
				prixmoyen.replace(f, prixmoyen.get(f)/(Filiere.LA_FILIERE.getEtape()+1));
			}
			
		if (cours_feve_basse <= bourse.getCours(Feve.FEVE_BASSE).getMin()) {
			this.setMecontentement_basse(this.getMecontentement_basse()-parametre_min_max);
		}
		if (cours_feve_moyenne <= bourse.getCours(Feve.FEVE_MOYENNE).getMin()) {
			this.setMecontentement_moyenne(this.getMecontentement_moyenne()-parametre_min_max);
		}
		if (cours_feve_haute <= bourse.getCours(Feve.FEVE_HAUTE).getMin()) {
			this.setMecontentement_haute(this.getMecontentement_haute()-parametre_min_max);
		}
		if (cours_feve_basse >= bourse.getCours(Feve.FEVE_BASSE).getMax()) {
			this.setMecontentement_basse(this.getMecontentement_basse()+parametre_min_max);
		}
		if (cours_feve_moyenne >= bourse.getCours(Feve.FEVE_MOYENNE).getMax()) {
			this.setMecontentement_moyenne(this.getMecontentement_moyenne()+parametre_min_max);
		}
		if (cours_feve_haute >= bourse.getCours(Feve.FEVE_HAUTE).getMax()) {
			this.setMecontentement_haute(this.getMecontentement_haute()+parametre_min_max);
		}
		
		this.setMecontentement_basse(this.getMecontentement_basse()+(int)Math.floor(100*((cours_feve_basse-prixmoyen.get(Feve.FEVE_BASSE)/prixmoyen.get(Feve.FEVE_BASSE)))));
		this.setMecontentement_moyenne(this.getMecontentement_moyenne()+(int)Math.floor(100*((cours_feve_moyenne-prixmoyen.get(Feve.FEVE_MOYENNE)/prixmoyen.get(Feve.FEVE_MOYENNE)))));
		this.setMecontentement_haute(this.getMecontentement_haute()+(int)Math.floor(100*((cours_feve_haute-prixmoyen.get(Feve.FEVE_HAUTE)/prixmoyen.get(Feve.FEVE_HAUTE)))));

	}
	

	public void next() { //Écrit par Antoine
		super.next();
		for (int j=0; j<ListeParc.size();j++) {
			this.getParc(j).MAJAleas();
			this.recolte = this.getParc(j).Recolte();
			
			this.addLot(Feve.FEVE_BASSE, this.recolte.get(Feve.FEVE_BASSE),this.getParc(j));
			this.addLot(Feve.FEVE_MOYENNE, this.recolte.get(Feve.FEVE_MOYENNE),this.getParc(j));
			this.addLot(Feve.FEVE_HAUTE, this.recolte.get(Feve.FEVE_HAUTE),this.getParc(j));
			this.addLot(Feve.FEVE_MOYENNE_BIO_EQUITABLE, this.recolte.get(Feve.FEVE_MOYENNE_BIO_EQUITABLE),this.getParc(j));
			this.addLot(Feve.FEVE_HAUTE_BIO_EQUITABLE, this.recolte.get(Feve.FEVE_HAUTE_BIO_EQUITABLE),this.getParc(j));

			this.getParc(j).MAJParc(this.getMecontentement_basse(),this.getMecontentement_moyenne(),this.getMecontentement_haute());
			this.getParc(j).MAJGuerre();
		}
	
		this.MAJMecontentement();
		
		
		double prixTotal = 0 ;
		//Calcul du Prix Total de Stockage
		for (Feve f : this.getFeves().keySet()) {
			prixTotal = prixTotal + (this.getStock(f, true)*Filiere.LA_FILIERE.getParametre("Prix Stockage").getValeur()) ;
		}
		
		//Calcul Prix Entretien Arbre
		
		for (int j=0;j<4;j++) {
			prixTotal = prixTotal 
					+ this.getParc(j).getNombre_BE_haute()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur() 
					+ this.getParc(j).getNombre_non_BE_haute()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*1.1 
					+ this.getParc(j).getNombre_non_BE_moyenne()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()
					+ this.getParc(j).getNombre_BE_moyenne()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*1.1 
					+ this.getParc(j).getNombre_non_BE_basse()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*0.9 ;
		}
		
		
		
		//Retirer l'argent 
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), prixTotal);
	}

	/**
	 * @return the recolte
	 */
	public HashMap<Feve, Double> getRecolte() {
		return recolte;
	}
	

}
