package abstraction.eq5Transformateur3;

import java.util.HashMap;
import java.util.Set;

//julien
public class DicoPeremption<Produit> {
	
	 

	private HashMap<DateProd<Produit> ,Double> peremptions ;
	
	public DicoPeremption(){
		this.peremptions=new HashMap<DateProd<Produit> ,Double>();
	}
	
	public Set<DateProd<Produit>> getDateProd() {
		return this.peremptions.keySet();
	}
	
	public double getQtt(double date,Produit produit) {	
		
		DateProd<Produit>d = new DateProd<Produit>(date,produit) ;
		if (this.getDateProd().contains(d)){
		return this.peremptions.get(d);
		}
		else {
			return 0.0;
		}
	}
	

	
	public void ajouterQtt(double date,Produit p, Double qtt) {
		DateProd<Produit> d =new DateProd<Produit>(date,p);
		if (qtt > 0) {
			
			if (this.getDateProd().contains(d)) {	
				this.peremptions.put(d, this.getQtt(date,p)+qtt);
				}
			else {
				this.peremptions.put(d, qtt);
				
				}
			}
	}
	
	public void utiliserQttDate(double date,Produit p, Double qtt) { 
		/* on vérifiera a l avance que l on peut retirer qtt ie qu il y a assez de stock */
		
		DateProd<Produit> d =new DateProd<Produit>(date,p);
		if (qtt >= 0) {

				
				if (this.getDateProd().contains(d)&& qtt<this.getQtt(date,p) ) {	
					this.peremptions.put(d, this.getQtt(date,p)-qtt);
					}
				if (this.getDateProd().contains(d)&& qtt>=this.getQtt(date,p) ) {	
					this.peremptions.remove(d);
				
			}
		}
		
		}
	
	
	public void perime(double step) {
		for (DateProd<Produit> d : this.getDateProd()) {
			if (d.getDate()<step || this.peremptions.get(d)==0) {
				this.peremptions.remove(d) ;
			}
		}
	}
public double trouverPlusUrgent(Produit p) {
	double date = 0;
	
	for (DateProd<Produit> d : this.getDateProd()) {
		double step=d.getDate();
		if ((date==0  || step<date )&& d.getProduit()==p) {
			date=step;
			
	}
		
	}
	return date;
}
	public void utiliserQtt(Produit p, double qtt) {
		
		if (qtt<=0) {} //on ne fait rien;
		
		else {
		
			double date=this.trouverPlusUrgent(p);
			double qC=this.getQtt(date, p);
			if (qC<=0) {
				DateProd<Produit>d=new DateProd<Produit>(date,p);
				this.peremptions.remove(d);
			}
			
			else {
			if (qC<=qtt) {
			utiliserQttDate(date,p,qC);
			utiliserQtt(p,qtt-qC);
			
		}
			else {
				utiliserQttDate(date,p,qtt);
			}
		}

		}	
	}
}
