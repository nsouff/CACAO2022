package abstraction.eq7Distributeur2.tools;

import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Demande implements IDemande {

	private HashMap<ChocolatDeMarque,Double> demandeRestante;
	private boolean FONCTIONEL;
	
	public Demande(List<ChocolatDeMarque> listeChocolatsProduits) {
		//Si aucun chocolat n'est vendu sur le marché
		if (listeChocolatsProduits.isEmpty()) {
			this.FONCTIONEL= false; 
		} else {
			this.FONCTIONEL = true;
		}
		
		//Initialisation des stocks physique
		this.demandeRestante = new HashMap<ChocolatDeMarque,Double>();
		this.initialiserZero(listeChocolatsProduits);
	}
	
	@Override
	public double get(ChocolatDeMarque choco) {
		if (!this.FONCTIONEL) {return 0.0;}
		return demandeRestante.get(choco);
	}

	@Override
	public void set(ChocolatDeMarque choco, double valeur) {
		if (!this.FONCTIONEL) {return ;}
		demandeRestante.replace(choco, valeur);
		
	}

	@Override
	public void remove(ChocolatDeMarque choco, double valeur) {
		if (!this.FONCTIONEL) {return ;}
		demandeRestante.remove(choco);
		
	}

	@Override
	public void initialiserZero(List<ChocolatDeMarque> listeChocolatsProduits) {
		if (!this.FONCTIONEL) {return ;}
		//Initialisation des deux à 0
		for (ChocolatDeMarque choco : listeChocolatsProduits) {
			this.demandeRestante.put(choco, 0.);
		}
	}

}
