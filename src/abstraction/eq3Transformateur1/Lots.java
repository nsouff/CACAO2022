package abstraction.eq3Transformateur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* anna*/
import abstraction.eq8Romu.produits.Chocolat;

public class Lots{
	
	private ArrayList<Double> lot;
	
	public Lots() {
		super();
		this.lot = lot ;
}
	public void addQuantité(double quantité ) {
		this.lot.add(quantité);
		
	}
	public void addDate(double date ) {
		this.lot.add(2,date);
		
	}
	public double getQuantite() {
		return this.lot.get(0);
	}
}