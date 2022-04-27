package abstraction.eq1Producteur1;

import java.util.ArrayList;
import java.util.List;

public class parc {
	private ArrayList<arbre> cacaoyers;
	private int nombre_BE;
	private int nombre_non_BE;
	
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
	
	public void setNombre_BE(int i) {
		this.nombre_BE = i;
	}
	
	public void setNombre_non_BE(int i) {
		this.nombre_non_BE = i;
	}
	
	public arbre getArbre(int i) {
		return this.getCacaoyers().get(i);
	}
	
	public void Planter(arbre a) {
			this.getCacaoyers().add(a);
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
