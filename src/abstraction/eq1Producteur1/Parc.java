package abstraction.eq1Producteur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Feve;


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
	
	public Parc(String nom) { //Écrit par Antoine
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
	}
	
	public List<MilleArbre> getCacaoyers() { //Écrit par Antoine
		return this.cacaoyers;
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
		if (Filiere.LA_FILIERE.getEtape()>=this.getUt_fin_guerre()) {
			this.setGuerre(false);
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
			}
		}
	}
	
	public void MAJAleas() { //Écrit par Antoine
		if (Filiere.LA_FILIERE.getEtape()%24==20) {
			double aléa_durée_aléas = Math.random();
			while (aléa_durée_aléas==0) {
				aléa_durée_aléas = Math.random();
			}
		this.setfin_aleas((int)(Filiere.LA_FILIERE.getEtape()+Math.ceil((aléa_durée_aléas+1)*2)));
		}
	}
	
	public void MAJParc() { //Écrit par Antoine
		for (int i=0; i<this.getCacaoyers().size(); i++) {
			MilleArbre arbre_i = this.getArbre(i);
			arbre_i.MAJMaladie();
			if (arbre_i.getStade_maladie() == 5) { //Si un arbre meurt à cause de la maladie, on le remplace
				int qualite = arbre_i.getQualite();
				boolean BE = arbre_i.getBioequitable();
				this.Planter(new MilleArbre(qualite,BE,Filiere.LA_FILIERE.getEtape()));
				this.getCacaoyers().remove(arbre_i);
			}
			if (arbre_i.Age() == arbre_i.getUt_esperance_vie()) {
				this.getCacaoyers().remove(arbre_i);
				this.MAJCompteur(arbre_i,-1);
			}
			if ((arbre_i.getUt_esperance_vie()-arbre_i.Age())==120) {
				int qualite = arbre_i.getQualite();
				boolean BE = arbre_i.getBioequitable();
				this.Planter(new MilleArbre(qualite,BE,Filiere.LA_FILIERE.getEtape()));
				this.MAJCompteur(arbre_i,1);
			}
		}
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
		HashMap<Feve, Double> dicorecolte = new HashMap<Feve, Double>();
		if (Filiere.LA_FILIERE.getEtape()>=this.fin_aleas) {
			for (int i=0; i<this.getCacaoyers().size(); i++) {
				MilleArbre arbre_i = this.getArbre(i);
				boolean isBE = arbre_i.getBioequitable();
				int qualite = arbre_i.getQualite();
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
		double parasitesBE = ParasitesBE();
		double parasites_non_BE = Parasites_non_BE();
		BE_moyenne = BE_moyenne*parasitesBE;
		BE_haute = BE_haute*parasitesBE;
		non_BE_basse = non_BE_basse*parasites_non_BE;
		non_BE_moyenne = non_BE_moyenne*parasites_non_BE;
		non_BE_haute = non_BE_haute*parasites_non_BE;
		dicorecolte.put(Feve.FEVE_BASSE,non_BE_basse);
		dicorecolte.put(Feve.FEVE_MOYENNE,non_BE_moyenne);
		dicorecolte.put(Feve.FEVE_HAUTE,non_BE_haute);
		dicorecolte.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE,BE_moyenne);
		dicorecolte.put(Feve.FEVE_HAUTE_BIO_EQUITABLE,BE_haute);
		return dicorecolte;
	}
}
