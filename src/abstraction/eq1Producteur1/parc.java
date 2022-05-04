package abstraction.eq1Producteur1;

import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Feve;

public class parc extends Producteur1Stock{
	private ArrayList<arbre> cacaoyers;
	private int nombre_BE_moyenne;
	private int nombre_BE_haute;
	private int nombre_non_BE_basse;
	private int nombre_non_BE_moyenne;
	private int nombre_non_BE_haute;
	private boolean guerre;
	private int ut_debut_guerre;
	private int ut_fin_guerre;
	private int fin_aleas;
	
	public parc() { //Écrit par Antoine
		this.cacaoyers = new ArrayList<arbre>();
		this.nombre_BE_moyenne = 0;
		this.nombre_BE_haute = 0;
		this.nombre_non_BE_basse = 0;
		this.nombre_non_BE_moyenne = 0;
		this.nombre_non_BE_haute = 0;
		this.guerre = false;
		this.ut_debut_guerre = 0;
		this.ut_fin_guerre = 0;
		fin_aleas = 0;
	}
	
	public List<arbre> getCacaoyers() { //Écrit par Antoine
		return this.cacaoyers;
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
	public arbre getArbre(int i) { //Écrit par Antoine
		return this.getCacaoyers().get(i);
	}
	
	public void Planter(arbre a) { //Écrit par Antoine
			this.getCacaoyers().add(a);
			MAJCompteur(a,1);
	}
	
	public void MAJCompteur(arbre a, int i) { //Fait par Antoine
		if ((i==1) || (i==-1)) {
			if (a.getBioequitable()) {
				if (a.getQualite()==2) {
					this.setNombre_BE_moyenne(this.getNombre_BE_moyenne()+1*i);
				}
				if (a.getQualite()==3) {
					this.setNombre_BE_haute(this.getNombre_BE_haute()+1*i);
				}
			}
			else {
				if (a.getQualite()==1) {
					this.setNombre_non_BE_basse(this.getNombre_non_BE_basse()+1*i);
				}
				if (a.getQualite()==2) {
					this.setNombre_non_BE_moyenne(this.getNombre_non_BE_moyenne()+1*i);
				}
				if (a.getQualite()==3) {
					this.setNombre_non_BE_haute(this.getNombre_non_BE_haute()+1*i);
				}
			}
		}
	}
	
	public void MAJGuerre() { //Écrit par Antoine
		if (Filiere.LA_FILIERE.getEtape()>=this.getUt_fin_guerre()) {
			this.setGuerre(false);
		}
		if (Filiere.LA_FILIERE.getEtape()>=this.getUt_fin_guerre()+Math.ceil((this.getUt_fin_guerre()-this.getUt_debut_guerre())*1.5)) {
				double d = Math.random();
				if (d<=0.15) {
					this.setGuerre(true);
					this.setUt_debut_guerre(Filiere.LA_FILIERE.getEtape());
					double dd = Math.random();
					while (dd==0) {
						dd = Math.random();
					}
					int temps = (int)Math.ceil(dd*6);
					this.setUt_fin_guerre(this.getUt_debut_guerre()+temps);
			}
		}
	}
	
	public void MAJAleas() { //Fait par Antoine
		if (Filiere.LA_FILIERE.getEtape()%24==20) {
			double d = Math.random();
			while (d==0) {
				d = Math.random();
			}
		this.setfin_aleas((int)(Filiere.LA_FILIERE.getEtape()+Math.ceil((d+1)*2)));
		}
	}
	
	public void MAJParc() { //Écrit par Antoine
		for (int i=0; i<this.getCacaoyers().size(); i++) {
			arbre arbre_i = this.getArbre(i);
			arbre_i.MAJMaladie();
			if ((arbre_i.getStade_maladie() == 5)
					|| (arbre_i.Age() == arbre_i.getUt_esperance_vie())) {
				this.getCacaoyers().remove(arbre_i);
				this.MAJCompteur(arbre_i,-1);
			}
		}
	}
	
	public double ParasitesBE() { //Fait par Antoine
		double d = Math.random();
		if (d<=0.2) {
			double dd = Math.random();
			if (dd<=0.7) {
				return 0.9;
			}
			if ((dd>0.7) && (dd<=0.95)) {
				return 0.5;
			}
			if ((dd>0.95) && (dd<=1)) {
				return 0.2;
			}
			return 1;
		}
		else {
			return 1;
		}
	}
	
	public double Parasites_non_BE() { //Fait par Antoine
		double d = Math.random();
		if (d<=0.04) {
			double dd = Math.random();
			if (dd<=0.7) {
				return 0.9;
			}
			if ((dd>0.7) && (dd<=0.95)) {
				return 0.5;
			}
			if ((dd>0.95) && (dd<=1)) {
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
	
	public void Recolte() { //Fait par Antoine
		double BE_moyenne = 0;
		double BE_haute = 0;
		double non_BE_basse = 0;
		double non_BE_moyenne = 0;
		double non_BE_haute = 0;
		if (Filiere.LA_FILIERE.getEtape()>=this.fin_aleas) {
			for (int i=0; i<this.getCacaoyers().size(); i++) {
				arbre arbre_i = this.getArbre(i);
				boolean isBE = arbre_i.getBioequitable();
				int qualite = arbre_i.getQualite();
				double recolte = arbre_i.Recolte();
				if ((isBE) && (qualite==2)) {
					BE_moyenne+=recolte;
				}
				if ((isBE) && (qualite==3)) {
					BE_haute+=recolte;
				}
				else {
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
		addLot(Feve.FEVE_BASSE,non_BE_basse);
		addLot(Feve.FEVE_MOYENNE,non_BE_moyenne);
		addLot(Feve.FEVE_HAUTE,non_BE_haute);
		addLot(Feve.FEVE_MOYENNE_BIO_EQUITABLE,BE_moyenne);
		addLot(Feve.FEVE_HAUTE_BIO_EQUITABLE,BE_haute);
	}
	
	public void next() { //Fait par Antoine
		this.MAJAleas();
		this.Recolte();
		this.MAJParc();
		this.MAJGuerre();
	}
}
