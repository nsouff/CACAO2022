package abstraction.eq5Transformateur3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

//julien
public class DicoPeremption<Produit> {
	
	 

	private HashMap<DateProd<Produit> ,Double> peremption ;
	
	public Set<DateProd<Produit>> getDates() {
		return this.peremption.keySet();
	}
	public double getQtt(DateProd d) {
		return this.getQtt(d);
	}
	

	
	public void utiliserQtt(int date,Produit p, Double qtt) {
		DateProd d =new DateProd(date,p);
		if (qtt > 0) {
			
			if (this.getDates().contains(d)) {	
				this.peremption.put(d, this.getQtt(d)+qtt);
				}
			else {
				this.peremption.put(d, qtt);
				
				}
			}
		else {
			
				if (this.getDates().contains(d)&& qtt*(-1)<=this.peremption.get(d) ) {	
					this.peremption.put(d, this.getQtt(d)-qtt);
					}
			}
		
		}
	
	public void perime(int step) {
		for (DateProd d : this.getDates()) {
			if (d.getDate()<step || this.peremption.get(d)==0) {
				this.peremption.remove(d) ;
			}
		}
	}
	public void utiliser(Produit p, double qtt) {
		int date = 0;
		
		for (DateProd d : this.getDates()) {
			int step=d.getDate();
			if (date==0  || (step<date && d.getProduit()==p) ){
				date=step;
			};
		}
		
		//utiliserQtt(date,p,qtt)
		
	};
}
