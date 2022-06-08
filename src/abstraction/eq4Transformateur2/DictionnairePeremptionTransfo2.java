package abstraction.eq4Transformateur2;

import java.util.HashMap;
import java.util.Set;

import abstraction.eq5Transformateur3.DateProd;

//Marie
public class DictionnairePeremptionTransfo2<Produit>{
	
	private HashMap<DateProdTransfo2<Produit> ,Double> peremption ;
	
	public Set<DateProdTransfo2<Produit>> getDates() {
		return this.peremption.keySet();
	}
	public double getQuant(double date, Produit produit) {
		DateProdTransfo2<Produit>d=new DateProdTransfo2<Produit>(date,produit);
		return this.peremption.get(d);
	}
	
	public void ajouterQuant(double date, Produit p, Double quant) {
		DateProdTransfo2<Produit>d=new DateProdTransfo2<Produit>(date,p);
		if(quant>0) {
			if(this.getDates().contains(d)) {
				this.peremption.put(d, this.getQuant(date, p)+quant);		
			}else {
				this.peremption.put(d, quant);
			}
		}
	}
	
	public void utiliserQuantDate(double date, Produit p, Double quant) {
		DateProdTransfo2<Produit>d=new DateProdTransfo2<Produit>(date,p);
		if(quant>=0) {
			if(this.getDates().contains(d)&&quant*(-1)<this.getQuant(date, p)) {
				this.peremption.put(d, this.getQuant(date, p)-quant);
			}
			if(this.getDates().contains(d)&&quant*(-1)==this.getQuant(date, p)) {
				this.peremption.remove(d);
			}
			}
		}

double step=12;
	
	public void perime(double step) {
		for(DateProdTransfo2<Produit>d:this.getDates()) {
			if(d.getDate()<step||this.peremption.get(d)==0) {
				this.peremption.remove(d);
			}
		}
	}
		
	public void utiliserQuant(Produit p, double quant) {
		double quantRestante=quant; //quant restante
		
		while(quantRestante>0) {
			double date=0;
			double qC=0;
			
			for(DateProdTransfo2<Produit>d:this.getDates()) {
				double step=d.getDate();
				if(date==0||(step<date && d.getProduit()==p)){
					date=step;
					qC=this.getQuant(date, p);
				}
			}
			if(qC<=quantRestante) {
				utiliserQuantDate(date,p,qC);
				quantRestante=quantRestante-qC;
			}
			else {
				utiliserQuantDate(date,p,quantRestante);
				quantRestante=0;
			}
		}
	}
	
	/*public void utiliserQtt(int step,Produit p, Double qtt) {
		DateProdTransfo2 d =new DateProdTransfo2(step,p);
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
		for (DateProdTransfo2 d : this.getDates()) {
			if (d.getStep()<step || this.peremption.get(d)==0) {
				this.peremption.remove(d) ;
			}
		}
	}
	public void utiliser(Produit p, double qtt) {
		int date = 0;
		
		for (DateProdTransfo2 d : this.getDates()) {
			int step=d.getStep();
			if (date==0  || (step<date && d.getProduit()==p) ){
				date=step;
			};
		}
		
		//utiliserQtt(date,p,qtt)
		
	}*/

}
