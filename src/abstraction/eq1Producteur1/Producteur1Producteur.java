package abstraction.eq1Producteur1;

import java.util.HashMap;
import java.util.LinkedList;

import abstraction.eq8Romu.filiere.Filiere;


import abstraction.eq8Romu.general.Journal;

import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Feve;

public class Producteur1Producteur extends Producteur1Stock{
	private LinkedList<Parc> ListeParc;
	private Parc ParcAfrique;
	private HashMap<Feve, Double> recolte;
	
	public Producteur1Producteur() {
		super();
		ListeParc = new LinkedList<Parc>();
		Parc afrique = new Parc("Afrique", this);
		Parc ghana = new Parc("Ghana", this);
		Parc cote_divoire = new Parc ("Côte d'Ivoire", this);
		Parc nigeria = new Parc("Nigéria", this);
		Parc cameroun = new Parc("Cameroun", this);
		ListeParc.add(ghana);
		ListeParc.add(cote_divoire);
		ListeParc.add(nigeria);
		ListeParc.add(cameroun);
		this.ParcAfrique = afrique;
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

	public void next() { //Écrit par Antoine
		super.next();
		for (int j=0; j<ListeParc.size();j++) {
			this.getParc(j).MAJAleas();
			this.recolte = this.getParc(j).Recolte();
			this.addLot(Feve.FEVE_BASSE, this.recolte.get(Feve.FEVE_BASSE));
			this.addLot(Feve.FEVE_MOYENNE, this.recolte.get(Feve.FEVE_MOYENNE));
			this.addLot(Feve.FEVE_HAUTE, this.recolte.get(Feve.FEVE_HAUTE));
			this.addLot(Feve.FEVE_MOYENNE_BIO_EQUITABLE, this.recolte.get(Feve.FEVE_MOYENNE_BIO_EQUITABLE));
			this.addLot(Feve.FEVE_HAUTE_BIO_EQUITABLE, this.recolte.get(Feve.FEVE_HAUTE_BIO_EQUITABLE));
			this.getParc(j).MAJParc();
			this.getParc(j).MAJGuerre();
		}
	
		
		
		
		double prixTotal = 0 ;
		//Calcul du Prix Total de Stockage
		for (Feve f : this.getFeves().keySet()) {
			prixTotal = prixTotal + (this.getStock(f, true)*Filiere.LA_FILIERE.getParametre("Prix Stockage").getValeur()) ;
		}
		
		//Calcul Prix Entretien Arbre
		
		prixTotal = prixTotal 
				+ this.getAfrique().getNombre_BE_haute()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur() 
				+ this.getAfrique().getNombre_non_BE_haute()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*1.1 
				+ this.getAfrique().getNombre_non_BE_moyenne()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()
				+ this.getAfrique().getNombre_BE_moyenne()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*1.1 
				+ this.getAfrique().getNombre_non_BE_basse()*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*0.9 ;
		
		
		//Retirer l'argent 
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), prixTotal);
	}
	
	public Parc getAfrique() {
		return this.ParcAfrique;
	}
	
	public HashMap<Feve, Double> getRecolte(){
		return this.recolte;
	}

}
