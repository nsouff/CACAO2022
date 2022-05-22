package abstraction.eq4Transformateur2;

import java.util.HashMap;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;

//auteur Marie

public class Transformateur2Stock extends Transformateur2ContratCadre {
	
	private Stock<Feve> stockfeve;
	private Stock<Chocolat>  stockchocolat;
	private Stock<ChocolatDeMarque> stockchocolatdemarque;
	
	private double prixstockage;
	
	
	
	public void next() {
		super.next();
		//ON implemente le journal avec des infos sur nos stocks à chaque tour
				if (this.stockfeve.getStock().keySet().size()>0) {
					for (Feve f : this.stockfeve.getStock().keySet()) {
						this.journal.ajouter("stock de feve "+f+" : "+this.stockfeve.getStock().get(f));
					}
				}
				if (this.stockchocolatdemarque.getStock().keySet().size()>0) {
					for (ChocolatDeMarque c : this.stockchocolatdemarque.getStock().keySet()) {
						this.journal.ajouter("stock de chocolat de marque "+c+" : "+this.stockchocolatdemarque.getStock().get(c));
					}
				}
				
				
				
		//On paye le cout de stockage
				Filiere.LA_FILIERE.getBanque().virer(this, super.cryptogramme, Filiere.LA_FILIERE.getBanque(), this.coutStockage());
				journal.ajouter("Le stock nous coûte "+this.coutStockage());
	}
	
	public void initialiser() {
		//double prixstockage=Filiere.LA_FILIERE.getIndicateur("prixstockage").getValeur();
		super.initialiser();
		
	}

	
// Marie	
	public Transformateur2Stock() {
		
		//LES STOCKS INITIAUX----VALEURS A CHOISIR
		this.stockfeve=new Stock();
		this.stockfeve.ajouter(Feve.FEVE_BASSE, 8000);
		this.stockfeve.ajouter(Feve.FEVE_MOYENNE, 5000);
		
		//On se fixe une marque pour un type de chocolat
		ChocolatDeMarque c1=new ChocolatDeMarque(Chocolat.MQ,this.getMarquesChocolat().get(1));
		ChocolatDeMarque c0=new ChocolatDeMarque(Chocolat.BQ,this.getMarquesChocolat().get(0));
		this.stockchocolatdemarque=new Stock();
		this.stockchocolatdemarque.ajouter(c1, 5000);
		this.stockchocolatdemarque.ajouter(c0, 8000);
		this.stockchocolat=new Stock();
		this.stockchocolat.ajouter(Chocolat.MQ,30000);
		this.stockchocolat.ajouter(Chocolat.BQ, 20000);
		
	}
	
public Stock<Feve> getStockfeve(){
	return this.stockfeve;
}
public Stock<Chocolat> getStockchocolat(){
	return this.stockchocolat;
}
public Stock<ChocolatDeMarque> getStockchocolatdemarque(){
	return this.stockchocolatdemarque;
}

// Gabriel 
//public void setStock(double newst) {
//	this.stocktotalchoco = this.stocktotalchoco - newst;
//}

//Marie
public double coutStockage() {
	return 4*0.01*(this.getStockchocolatdemarque().getStocktotal()+this.getStockfeve().getStocktotal());
}


// Marie 
//public double quantiteStockTotaleFeve(Feve produit) {
//	for(Feve f : stockfeve.getStock().keySet()) {
//		this.stocktotalfeve=this.stocktotalfeve+stockfeve.quantite_stock.get(f);
//	}
//	return this.stocktotalfeve;	
//}
//// Marie
//
//public double quantiteStockTotaleChocolat(Chocolat produit) {
//	for(Chocolat c: stockchocolat.quantite_stock.keySet()) {
//		this.stocktotalchoco=this.stocktotalchoco+stockchocolat.quantite_stock.get(c);
//	}
//	return this.stocktotalchoco;
	
//}	

//Marie
/*public boolean capaciteMaxAtteinte() {
	if ((this.capaciteStockage.getValeur()-this.stockfeve-this.stockchocolat)>0 || ) {
		return false;	
	}else {
		return true;
	}
}

public double augmenterCapacite(Variable capaciteStockage) {
	//condition pour augmenter la capacité (par ex. dès qu'on souhaite acheter et que l'on n'a pas de capcité de stockage suffisante, on augmente notre capacité)
	if (capaciteMaxAtteinte=true) {
		capaciteStockage=this.getCapaciteStockage()*1.20;
		
		
	}
	//si validé alors on augmente le stock d'une quantité définie 
	// sinon on garde le même stock
}*/

}

