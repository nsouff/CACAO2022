package abstraction.eq1Producteur1;

import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Chocolat;

public class Producteur1Transfo extends Producteur1StockChocolat {
	
	
	/**
	 * 
	 */
	public Producteur1Transfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author laure
	 * @param FeveProducteur1 feve : type de feve, double quantite : quantité à transformer
	 * @return ChocolatProducteur1 : chocolat produit
	 */
	public void nextTransformation(Feve f, double quantite, boolean original) {
		double quantiteChoco = this.getStock(f, false)*0.10; //On transforme 10% de notre quantité
		
		this.retirerQuantite(f, quantiteChoco);
		this.addLot(Chocolat.get(f.getGamme(), f.isBioEquitable(), original), quantiteChoco);
	}
}
