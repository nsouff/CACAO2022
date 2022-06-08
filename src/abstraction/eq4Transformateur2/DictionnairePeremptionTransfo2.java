package abstraction.eq4Transformateur2;

import java.util.HashMap;
import java.util.Set;

import abstraction.eq5Transformateur3.DateProd;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;

//Marie
public class DictionnairePeremptionTransfo2<Produit>{
	
	private HashMap<DateProdTransfo2<Produit> ,Double> peremption ;
	
	public Set<DateProdTransfo2<Produit>> getDates() {
		return this.peremption.keySet(); //renvoie (date,produit)
	}
	public double getQuant(double date, Produit produit) {
		DateProdTransfo2<Produit>d=new DateProdTransfo2<Produit>(date,produit);
		return this.peremption.get(d); //renvoie la quantité associé à (date,produit)
	}
	
	public void next() {
		super.next();
		this.modifDateProd();
		
	}
	
	public void initialiser() {
		super.initialiser();
		
	}
	
	//Marie
	
// ajoute un nouveau produit à la Hashmap
	public void ajouterQuant(double date, Produit p, Double quant) {
		DateProdTransfo2<Produit>d=new DateProdTransfo2<Produit>(date,p);
		if(quant>0) {
			if(this.getDates().contains(d)) {
				this.peremption.put(d, this.getQuant(date, p)+quant);	// si produit à la même date existe déjà, on ajoute seulement la quantité du produit à la quantité déjà présente	
			}else {
				this.peremption.put(d, quant); // s'il n'existe pas de produit à la même date, on rajoute un élément à la Hashmap
			}
		}
	}
//modifie la date des produits à chaque step
	public void modifDateProd() {
		for(DateProdTransfo2<Produit>d:this.getDates()) {
			d.setDate(d.getDate()+1);
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

//retire le produit quand il a dépassé la date de péremption
public void perime() {
	for(DateProdTransfo2<Produit>d:this.getDates()) {
		if(d.getDate()>12) {
			this.peremption.remove(d);
		}
	}
}	

	/*double step=12;
	 public void perime(double step) {
		for(DateProdTransfo2<Produit>d:this.getDates()) {
			if(d.getDate()<step||this.peremption.get(d)==0) {
				this.peremption.remove(d);
			}
		}
	}*/
	
//modifications lors d'un achat
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
	//Marie
	public double quantTotaleProduit(Produit p) { //calcule la quantité totale d'un produit
		double qtt=0;
		for(DateProdTransfo2<Produit>d:this.getDates()) {
			if(d.getProduit()==p) {
				qtt+=this.peremption.get(d);
			}
		}
		return qtt;
	}
	//Marie
	public double quantTotFeve() { //calcul la quantité totale de fèves
		double qtt=0;
		for(DateProdTransfo2<Produit>d:this.getDates()) {
			if(d.getProduit()==Feve.FEVE_BASSE || d.getProduit()==Feve.FEVE_HAUTE || d.getProduit()==Feve.FEVE_HAUTE_BIO_EQUITABLE ||d.getProduit()==Feve.FEVE_MOYENNE || d.getProduit()==Feve.FEVE_MOYENNE_BIO_EQUITABLE) {
				qtt+=quantTotaleProduit(d.getProduit());
			}
		return qtt;
	}
		return qtt;
	}
		
	//Marie
	public double quantTotChoco() { //calcul la quantité totale de chocolats 
		double qtt=0;
		for(DateProdTransfo2<Produit>d:this.getDates()) {
			if(d.getProduit()==Chocolat.BQ || d.getProduit()==Chocolat.BQ_O || d.getProduit()==Chocolat.HQ ||d.getProduit()==Chocolat.HQ_BE ||d.getProduit()==Chocolat.MQ ||d.getProduit()==Chocolat.MQ_BE  ) {
				qtt+=quantTotaleProduit(d.getProduit());
			}
		return qtt;
	}
		return qtt;
			}
	
	
	@Override
	public Stock<Chocolat> getStockchocolat() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Journal getJournalcours() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Journal getJournalStock() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Journal getJournalTransfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Journal getJournalAchat() {
		// TODO Auto-generated method stub
		return null;
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


