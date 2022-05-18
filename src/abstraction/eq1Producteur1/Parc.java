package abstraction.eq1Producteur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.filiere.IActeur;

public class Parc {
	private ArrayList<MilleArbre> cacaoyers;
	private String nom;
	private int nombre_BE_moyenne;
	private int nombre_BE_haute;
	private int nombre_non_BE_basse;
	private int nombre_non_BE_moyenne;
	private int nombre_non_BE_haute;
	private boolean guerre;
	private int ut_debut_guerre;
	private int ut_fin_guerre;
	private int fin_aleas;
	protected Journal RetourMAJParc;
	protected Journal RetourGuerre;
	protected Journal RetourAléas;
	protected Journal RetourMaladie;
	protected Journal RetourRécolte;
	private int mecontentementBQ;
	private int mecontentementMQ;
	private int mecontentementHQ;
	
	public Parc(String nom, Object producteur) { //Écrit par Antoine
		this.cacaoyers = new ArrayList<MilleArbre>();
		this.nom = nom;
		this.nombre_BE_moyenne = 0;
		this.nombre_BE_haute = 0;
		this.nombre_non_BE_basse = 0;
		this.nombre_non_BE_moyenne = 0;
		this.nombre_non_BE_haute = 0;
		this.guerre = false;
		this.ut_debut_guerre = 0;
		this.ut_fin_guerre = 0;
		this.fin_aleas = 0;
		this.RetourMAJParc = new Journal("CAC'AO40 "+nom+" Le grand remplacement?", ((IActeur)producteur));
		this.RetourGuerre = new Journal("CAC'AO40 "+nom+" Ils font bien la gueguerre?", ((IActeur)producteur));
		this.RetourAléas = new Journal("CAC'AO40 "+nom+" Le réchauffement climatique n'est pas un hoax?", ((IActeur)producteur));
		this.RetourMaladie = new Journal("CAC'AO40 "+ nom + " Prise en compte des maladies?", ((IActeur)producteur));
		this.RetourRécolte = new Journal("CAC'AO40 "+ nom + " On récolte du Khécao?", ((IActeur)producteur));
		this.mecontentementBQ=0;
		this.mecontentementMQ=0;
		this.mecontentementHQ=0;
	}
	
	public List<MilleArbre> getCacaoyers() { //Écrit par Antoine
		return this.cacaoyers;
	}
	
	
	public Journal getRetourMAJParc() {
		return RetourMAJParc;
	}

	public Journal getRetourGuerre() {
		return RetourGuerre;
	}
	
	public Journal getRetourAléas() {
		return RetourAléas;
	}
	
	public Journal getRetourMaladie() {
		return RetourMaladie;
	}
	
	public Journal getRetourRécolte() {
		return RetourRécolte;
	}
	public String getNom() { //Écrit par Antoine
		return this.nom;
	}
	
	public int getNombre_BE_moyenne() { //Écrit par Antoine
		return this.nombre_BE_moyenne;
	}
	
	public int getNombre_BE_haute() { //Écrit par Antoine
		return this.nombre_BE_haute;
	}
	
	public int getNombre_non_BE_basse() { //Écrit par Antoine
		return this.nombre_non_BE_basse;
	}
	
	public int getNombre_non_BE_moyenne() { //Écrit par Antoine
		return this.nombre_non_BE_moyenne;
	}
	
	public int getNombre_non_BE_haute() { //Écrit par Antoine
		return this.nombre_non_BE_haute;
	}
	
	public boolean getGuerre() { //Écrit par Antoine
		return this.guerre;
	}
	
	public int getUt_debut_guerre() { //Écrit par Antoine
		return this.ut_debut_guerre;
	}
	
	public int getUt_fin_guerre() { //Écrit par Antoine
		return this.ut_fin_guerre;
	}
	
	public int getfin_aleas() { //Écrit par Antoine
		return this.fin_aleas;
	}
	
	public int getMecontentementBQ() {
		return this.mecontentementBQ;
	}
	
	public int getMecontentementMQ() {
		return this.mecontentementMQ;
	}
	
	public int getMecontentementHQ() {
		return this.mecontentementHQ;
	}

	public void setNom(String nom) { //Écrit par Antoine
		this.nom = nom;
	}
	
	public void setNombre_BE_moyenne(int i) { //Écrit par Antoine
		this.nombre_BE_moyenne = i;
	}
	
	public void setNombre_BE_haute(int i) { //Écrit par Antoine
		this.nombre_BE_haute = i;
	}
	
	public void setNombre_non_BE_basse(int i) { //Écrit par Antoine
		this.nombre_non_BE_basse = i;
	}
	
	public void setNombre_non_BE_moyenne(int i) { //Écrit par Antoine
		this.nombre_non_BE_moyenne = i;
	}
	
	public void setNombre_non_BE_haute(int i) { //Écrit par Antoine
		this.nombre_non_BE_haute = i;
	}
	
	public void setGuerre(boolean b) { //Écrit par Antoine
		this.guerre = b;
	}
	
	public void setUt_debut_guerre(int ut) { //Écrit par Antoine
		this.ut_debut_guerre = ut;
	}
	
	public void setUt_fin_guerre(int ut) { //Écrit par Antoine
		this.ut_fin_guerre = ut;
	}
	
	public void setfin_aleas(int i) { //Écrit par Antoine
		this.fin_aleas = i;
	}
	
	public void setmecontentementBQ(int mecontentementBQ) {
		this.mecontentementBQ=mecontentementBQ;
	}
	
	public void setmecontentementMQ(int mecontentementMQ) {
		this.mecontentementMQ=mecontentementMQ;
	}
	
	public void setmecontentementHQ(int mecontentementHQ) {
		this.mecontentementHQ=mecontentementHQ;
	}

	public MilleArbre getArbre(int i) { //Écrit par Antoine
		return this.getCacaoyers().get(i);
	}
	
	public void Planter(MilleArbre a) { //Écrit par Antoine
			this.getCacaoyers().add(a);
			MAJCompteur(a,1);
	}
	
	public void MAJCompteur(MilleArbre a, int i) { //Écrit par Antoine
		if ((i==1) || (i==-1)) {
			if (a.getBioequitable()) {
				if (a.getQualite()==2) {
					this.setNombre_BE_moyenne(this.getNombre_BE_moyenne()+i);
				}
				if (a.getQualite()==3) {
					this.setNombre_BE_haute(this.getNombre_BE_haute()+i);
				}
			}
			else {
				if (a.getQualite()==1) {
					this.setNombre_non_BE_basse(this.getNombre_non_BE_basse()+i);
				}
				if (a.getQualite()==2) {
					this.setNombre_non_BE_moyenne(this.getNombre_non_BE_moyenne()+i);
				}
				if (a.getQualite()==3) {
					this.setNombre_non_BE_haute(this.getNombre_non_BE_haute()+i);
				}
			}
		}
	}
	
	public void MAJGuerre() { //Écrit par Antoine
		if (this.getGuerre()==true) {
			if (Filiere.LA_FILIERE.getEtape()>=this.getUt_fin_guerre()) {
				this.setGuerre(false);
				this.getRetourGuerre().ajouter("Il est temps d'enterrer la hache de guerre, faisons l'amour et non la guerre");
			}
		}
		if (Filiere.LA_FILIERE.getEtape()>=this.getUt_fin_guerre()+Math.ceil((this.getUt_fin_guerre()-this.getUt_debut_guerre())*1.5)) {
				double chance_guerre = Math.random();
				if (chance_guerre<=0.15) {
					this.setGuerre(true);
					this.setUt_debut_guerre(Filiere.LA_FILIERE.getEtape());
					double aléa_durée_guerre = Math.random();
					while (aléa_durée_guerre==0) {
						aléa_durée_guerre = Math.random();
					}
					int temps = (int)Math.ceil(aléa_durée_guerre*6);
					this.setUt_fin_guerre(this.getUt_debut_guerre()+temps);
					this.getRetourGuerre().ajouter("Nos ennemis n'achètent pas notre cacao, il est temps de les goumer et ce pendant"+temps+"ut");
			}
				else {
					this.getRetourGuerre().ajouter("Ces cancres de Producteur2 ne méritent pas qu'on leur déclare la guerre pour le moment");
				}
		}
		if ((Filiere.LA_FILIERE.getEtape()<this.getUt_fin_guerre()+Math.ceil((this.getUt_fin_guerre()-this.getUt_debut_guerre())*1.5)) && (this.getGuerre()==false)) {
			this.getRetourGuerre().ajouter("C'est la période de paix frérot, on cherche pas les embrouilles");
		}
	}
	
	public void MAJAleas() { //Écrit par Antoine
		if (Filiere.LA_FILIERE.getEtape()%24==20) {
			double aléa_durée_aléas = Math.random();
			while (aléa_durée_aléas==0) {
				aléa_durée_aléas = Math.random();
			}
		int temps = (int)Math.ceil((aléa_durée_aléas+1)*2);
		this.setfin_aleas((int)(Filiere.LA_FILIERE.getEtape()+temps));
		this.getRetourAléas().ajouter("Cette année (ut :"+Filiere.LA_FILIERE.getEtape()+"), les aléas climatiques vont durer"+temps+"ut" );
		}
	}
	
	public int MAJMecontentementBQ() {
		
	}

	
	public void MAJParc() { //Écrit par Antoine
		int malade5 = 0;
		int plantés = 0;
		int mort_vieillesse = 0;
		for (int i=0; i<this.getCacaoyers().size(); i++) {
			MilleArbre arbre_i = this.getArbre(i);
			arbre_i.MAJMaladie();
			if (arbre_i.getStade_maladie() == 5) { //Si un arbre meurt à cause de maladie, on le remplace immédiatement
				malade5+=1;
				int qualite = arbre_i.getQualite();
				boolean BE = arbre_i.getBioequitable();
				boolean cooperative=arbre_i.getCooperative();
				this.Planter(new MilleArbre(qualite,cooperative,BE,Filiere.LA_FILIERE.getEtape()));
				this.Planter(new MilleArbre(qualite,cooperative,BE,Filiere.LA_FILIERE.getEtape()));
				plantés+=1;
				this.getCacaoyers().remove(arbre_i);
			}
			if (arbre_i.Age() == arbre_i.getUt_esperance_vie()) {
				this.getCacaoyers().remove(arbre_i);
				this.MAJCompteur(arbre_i,-1);
				mort_vieillesse+=1;
			}
			if ((arbre_i.getUt_esperance_vie()-arbre_i.Age())==120) {
				int qualite = arbre_i.getQualite();
				boolean BE = arbre_i.getBioequitable();
				boolean cooperative=arbre_i.getCooperative();
				this.Planter(new MilleArbre(qualite,cooperative,BE,Filiere.LA_FILIERE.getEtape()));
				this.MAJCompteur(arbre_i,1);
				plantés+=1;
			}
		}
		this.getRetourMAJParc().ajouter(mort_vieillesse+" MilleArbres sont mort de vieillesse, vive les arbres");
		this.getRetourMAJParc().ajouter(plantés+" bébes MilleArbres sont nés, je pense les appeler Groot1, Groot2...");
		this.getRetourMaladie().ajouter(malade5+" MilleArbres sont morts de maladie");
	}
	
	public double ParasitesBE() { //Écrit par Antoine
		double chance_parasite = Math.random();
		if (chance_parasite<=0.2) {
			double niveau_parasite = Math.random();
			if (niveau_parasite<=0.7) {
				return 0.9;
			}
			if ((niveau_parasite>0.7) && (niveau_parasite<=0.95)) {
				return 0.5;
			}
			if ((niveau_parasite>0.95) && (niveau_parasite<=1)) {
				return 0.2;
			}
			return 1;
		}
		else {
			return 1;
		}
	}
	
	public double Parasites_non_BE() { //Écrit par Antoine
		double chance_parasite = Math.random();
		if (chance_parasite<=0.04) {
			double niveau_parasite = Math.random();
			if (niveau_parasite<=0.7) {
				return 0.9;
			}
			if ((niveau_parasite>0.7) && (niveau_parasite<=0.95)) {
				return 0.5;
			}
			if ((niveau_parasite>0.95) && (niveau_parasite<=1)) {
				return 0.2;
			}
			else {
				return 1;
			}
		}
		else {
			return 1;
		}
	}
	
	public HashMap<Feve, Double> Recolte() { //Écrit par Antoine
		double BE_moyenne = 0;
		double BE_haute = 0;
		double non_BE_basse = 0;
		double non_BE_moyenne = 0;
		double non_BE_haute = 0;
		int malade1 = 0;
		int malade2 = 0;
		int malade3 = 0;
		int malade4 = 0;
		HashMap<Feve, Double> dicorecolte = new HashMap<Feve, Double>();
		if (Filiere.LA_FILIERE.getEtape()>=this.fin_aleas) {
			this.getRetourAléas().ajouter("OKLM le ciel est dégagé, la saison est bonne il est temps de récolter et d'se faire du blé");
			for (int i=0; i<this.getCacaoyers().size(); i++) {
				MilleArbre arbre_i = this.getArbre(i);
				boolean isBE = arbre_i.getBioequitable();
				int qualite = arbre_i.getQualite();
				int maladie = arbre_i.getStade_maladie();
				if (maladie==1) {
					malade1+=1;
				}
				if (maladie==2) { 
					malade2+=1;
				}
				if (maladie==3) {
					malade3+=1;
				}
				if (maladie==4) {
					malade4+=1;
				}
				double recolte = arbre_i.Recolte();
				if ((isBE) && (qualite==2)) {
					BE_moyenne+=recolte;
				}
				if ((isBE) && (qualite==3)) {
					BE_haute+=recolte;
				}
				if (isBE==false) {
					if (qualite==1) {
						non_BE_basse+=recolte;
					}
					if (qualite==2) {
						non_BE_moyenne+=recolte;
					}
					if (qualite==3) {
						non_BE_haute+=recolte;
					}
				}
			}
		}
		else {
			this.getRetourAléas().ajouter("ON RÉCOLTE PAS C'EST LA TEMPÊTE PLANQUEZ VOUS LES GADJOS");
		}
		this.getRetourMaladie().ajouter("Il y a actuellement "+malade1+" MilleArbres malades au stade 1");
		this.getRetourMaladie().ajouter("Il y a actuellement "+malade2+" MilleArbres malades au stade 2");
		this.getRetourMaladie().ajouter("Il y a actuellement "+malade3+" MilleArbres malades au stade 3");
		this.getRetourMaladie().ajouter("Il y a actuellement "+malade4+" MilleArbres malades au stade 4");
		double parasitesBE = ParasitesBE();
		double parasites_non_BE = Parasites_non_BE();
		BE_moyenne = BE_moyenne*parasitesBE;
		BE_haute = BE_haute*parasitesBE;
		non_BE_basse = non_BE_basse*parasites_non_BE;
		non_BE_moyenne = non_BE_moyenne*parasites_non_BE;
		non_BE_haute = non_BE_haute*parasites_non_BE;
		this.getRetourRécolte().ajouter("On a recolté " + non_BE_basse + " kg de Khécao de qualité assez nulle...même pas BE en plus");
		this.getRetourRécolte().ajouter("On a recolté " + non_BE_moyenne + " kg de Khécao de qualité médiocre...même pas BE en plus");
		this.getRetourRécolte().ajouter("On a recolté " + non_BE_haute + " kg de Khécao de qualité exceptionnelle...même pas BE par contre");
		this.getRetourRécolte().ajouter("On a recolté " + BE_moyenne + " kg de Khécao de qualité médiocre...mais BE !");
		this.getRetourRécolte().ajouter("On a recolté " + BE_haute + " kg de Khécao de qualité exceptionnelle...mais BE !");
		dicorecolte.put(Feve.FEVE_BASSE,non_BE_basse);
		dicorecolte.put(Feve.FEVE_MOYENNE,non_BE_moyenne);
		dicorecolte.put(Feve.FEVE_HAUTE,non_BE_haute);
		dicorecolte.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE,BE_moyenne);
		dicorecolte.put(Feve.FEVE_HAUTE_BIO_EQUITABLE,BE_haute);
		return dicorecolte;
	}
}
