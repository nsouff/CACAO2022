package abstraction.eq1Producteur1;

import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.filiere.Filiere;

public class parc {
	private ArrayList<arbre> cacaoyers;
	private int nombre_BE;
	private int nombre_non_BE;
	private boolean guerre;
	private int ut_debut_guerre;
	private int ut_fin_guerre;
	private int fin_aleas;
	
	public parc() { //Écrit par Antoine
		this.cacaoyers = new ArrayList<arbre>();
		this.nombre_BE = 0;
		this.nombre_non_BE = 0;
	}
	
	public List<arbre> getCacaoyers() { //Écrit par Antoine
		return this.cacaoyers;
	}
	
	public int getNombre_BE() { //Écrit par Antoine
		return this.nombre_BE;
	}
	
	public int getNombre_non_BE() { //Écrit par Antoine
		return this.nombre_non_BE;
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
	
	public void setNombre_BE(int i) { //Écrit par Antoine
		this.nombre_BE = i;
	}
	
	public void setNombre_non_BE(int i) { //Écrit par Antoine
		this.nombre_non_BE = i;
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
	}
	
	public void MAJGuerre() { //Écrit par Antoine
		if (Filiere.LA_FILIERE.getEtape()>this.getUt_fin_guerre()+Math.ceil((this.getUt_fin_guerre()-this.getUt_debut_guerre())*1.5)) {
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
		double d = Math.random();
		while (d==0) {
			d = Math.random();
		}
		this.setfin_aleas((int)(Filiere.LA_FILIERE.getEtape()+Math.ceil((d+1)*2)));
	}
	
	public void MAJParc() { //Écrit par Antoine
		for (int i=0; i<this.getCacaoyers().size(); i++) {
			arbre arbre_i = this.getArbre(i);
			arbre_i.MAJMaladie();
			if ((arbre_i.getMaladie() == 5)
					|| (arbre_i.Age() == arbre_i.getUt_esperance_vie())) {
				this.getCacaoyers().remove(arbre_i);
				if (arbre_i.getBioequitable()) {
					nombre_BE--;
				}
				else {
					nombre_non_BE--;
				}
			}
		}
	}
	
	public double ParasitesBE() { //Fait par Antoine
		double d = Math.random();
		if (d<=0.2) {
			double dd = Math.random();
			if (dd<=0.7) {
				return 0.1;
			}
			if ((dd>0.7) && (dd<=0.95)) {
				return 0.5;
			}
			if ((dd>0.95) && (dd<=1)) {
				return 0.8;
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
				return 0.1;
			}
			if ((dd>0.7) && (dd<=0.95)) {
				return 0.5;
			}
			if ((dd>0.95) && (dd<=1)) {
				return 0.8;
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
		double BE = 0;
		double non_BE = 0;
		if (Filiere.LA_FILIERE.getEtape()%24==20) {
			this.MAJAleas();
		}
		if (Filiere.LA_FILIERE.getEtape()>=this.fin_aleas) {
			for (int i=0; i<this.getCacaoyers().size(); i++) {
				arbre arbre_i = this.getArbre(i);
				boolean isBE = arbre_i.getBioequitable();
				double recolte = arbre_i.getProductivite_max();
				if (isBE) {
					BE+=recolte;
				}
				else {
					non_BE+=recolte;
				}
			}
		}
		BE=BE*ParasitesBE();
		non_BE=non_BE*Parasites_non_BE();
		
	}
}
