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
	private int duree_aleas;
	
	public parc() {
		this.cacaoyers = new ArrayList<arbre>();
		this.nombre_BE = 0;
		this.nombre_non_BE = 0;
	}
	
	public List<arbre> getCacaoyers() {
		return this.cacaoyers;
	}
	
	public int getNombre_BE() {
		return this.nombre_BE;
	}
	
	public int getNombre_non_BE() {
		return this.nombre_non_BE;
	}
	
	public boolean getGuerre() {
		return this.guerre;
	}
	
	public int getUt_debut_guerre() {
		return this.ut_debut_guerre;
	}
	
	public int getUt_fin_guerre() {
		return this.ut_fin_guerre;
	}
	
	public int getDuree_aleas() {
		return this.duree_aleas;
	}
	
	public void setNombre_BE(int i) {
		this.nombre_BE = i;
	}
	
	public void setNombre_non_BE(int i) {
		this.nombre_non_BE = i;
	}
	
	public void setGuerre(boolean b) {
		this.guerre = b;
	}
	
	public void setUt_debut_guerre(int ut) {
		this.ut_debut_guerre = ut;
	}
	
	public void setUt_fin_guerre(int ut) {
		this.ut_fin_guerre = ut;
	}
	
	public void setDuree_aleas(int i) {
		this.duree_aleas = i;
	}
	public arbre getArbre(int i) {
		return this.getCacaoyers().get(i);
	}
	
	public void Planter(arbre a) {
			this.getCacaoyers().add(a);
	}
	
	public void MAJGuerre() {
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
	
	public void MAJParc() { 
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
}
