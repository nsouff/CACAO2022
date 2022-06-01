package abstraction.eq5Transformateur3;

import java.util.HashMap;
import java.util.Set;

//julien
public class DicoPeremption<Produit> {
	
	 

	private HashMap<DateProd<Produit> ,Double> peremptions ;
	
	public Set<DateProd<Produit>> getDateProd() {
		return this.peremptions.keySet();
	}
	public double getQtt(int date,Produit produit) {	
		DateProd<Produit>d = new DateProd<Produit>(date,produit) ;
		return this.peremptions.get(d);
	}
	

	
	public void ajouterQtt(int date,Produit p, Double qtt) {
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
		
	public void utiliserQttDate(int date,Produit p, Double qtt) { 
		/* on vérifiera a l avance que l on peut retirer qtt ie qu il y a assez de stock */
		
		DateProd<Produit> d =new DateProd<Produit>(date,p);
		if (qtt >= 0) {

				
				if (this.getDateProd().contains(d)&& qtt*(-1)<this.getQtt(date,p) ) {	
					this.peremptions.put(d, this.getQtt(date,p)-qtt);
					}
				if (this.getDateProd().contains(d)&& qtt*(-1)==this.getQtt(date,p) ) {	
					this.peremptions.remove(d);
				
			}
		}
		
		}
	
	
	public void perime(int step) {
		for (DateProd<Produit> d : this.getDateProd()) {
			if (d.getDate()<step || this.peremptions.get(d)==0) {
				this.peremptions.remove(d) ;
			}
		}
	}
	
	public void utiliserQtt(Produit p, double qtt) {
		
		double qttR=qtt; // qtt restante
		
		while (qttR>0) {
			
			int date = 0;
			double qC=0;
			
		
			for (DateProd<Produit> d : this.getDateProd()) {
				int step=d.getDate();
				if (date==0  || (step<date && d.getProduit()==p) ){
					date=step;
					qC=this.getQtt(date, p);
			}
		}
			if (qC<=qttR) {
			utiliserQttDate(date,p,qC);
			qttR=qttR-qC;
		}
			else {
				utiliserQttDate(date,p,qttR);
				qttR=0;
				
			}
		}

		
	}
}
