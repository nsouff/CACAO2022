package abstraction.eq7Distributeur2;

import java.util.List;
import java.util.HashMap;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

/**
 * Classe Stock Biofour -
 * Utilise la classe Variable afin de suivre l'état des stocks
 * @author mgloc
 * 
 */
public class Stock implements IStock{

	private HashMap<ChocolatDeMarque,Variable> reserve;
	private HashMap<ChocolatDeMarque,Variable> seuilRachat;
	private IActeur acteur;
	
	/**Méthode constructeur, initialise le stock vide
	 * @param acteur
	 */
	public Stock(IActeur acteur) {
		List<ChocolatDeMarque> listeChocolatsProduits = Filiere.LA_FILIERE.getChocolatsProduits();
		
		//Initialisation des stocks physique
		reserve = new HashMap<ChocolatDeMarque,Variable>();
		//Initialisation du seuil de rachat de stock
		seuilRachat = new HashMap<ChocolatDeMarque,Variable>();
		
		//Initialisation des deux à 0
		for (ChocolatDeMarque choco : listeChocolatsProduits) {
			reserve.put(choco, new Variable("Stock"+choco.toString(),"Valeur du stock pour "+choco.toString(),acteur,0.,Double.POSITIVE_INFINITY,0));
			seuilRachat.put(choco,new Variable("SeuilRachat"+choco.toString(),"Seuil de rachat défini pour "+choco.toString(),acteur,-1.,Double.POSITIVE_INFINITY,0));
		}
		this.acteur = acteur;
	}
	public Stock() {
		this(null);
	}
	
	public IActeur getActeur() {
		return this.acteur;
	}
	
	
	public double getQuantite(ChocolatDeMarque chocolat) {
		Variable v = reserve.get(chocolat);
		return v.getValeur();
	}
	
	public void setQuantite(ChocolatDeMarque chocolat,double quantite) {
		Variable v = reserve.get(chocolat);
		v.setValeur(this.acteur, quantite);
	}
	
	public void addProduit(ChocolatDeMarque chocolat, Double quantite) {
		double quantiteActuelle = this.getQuantite(chocolat);
		this.setQuantite(chocolat, quantiteActuelle + quantite);
	}

	public void remove(ChocolatDeMarque chocolat, Double quantite) {
		//This method allow negative stock
		this.addProduit(chocolat, -quantite);
	}

	public double getSeuilRachat(ChocolatDeMarque chocolat) {
		return seuilRachat.get(chocolat).getValeur();
	}

	public void setSeuilRachat(ChocolatDeMarque chocolat, Double seuil) {
		seuilRachat.get(chocolat).setValeur(this.acteur, seuil);
	}
	
}
