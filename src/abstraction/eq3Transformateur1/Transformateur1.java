package abstraction.eq3Transformateur1;
import java.util.ArrayList;
import java.util.List;




public class Transformateur1 extends Transformateur1Acteur {

	private List<Double> dernierprixvente ;
	private List<Double> prixtransfo ;
	private List<Integer> stock;
	private List<Integer> quantitévendue ;


	public Transformateur1() { 
		super();
	}

	public List<Double> prixmaxachat(List<Double> dernierprixvente ) {
		List<Double> prixmaxachat = new ArrayList<Double>() ;
		for (int i=0;i<this.dernierprixvente.size();i++) {
			prixmaxachat.add(dernierprixvente.get(i) / (prixtransfo.get(i)*quantitévendue.get(i)*1.0));	
		}
		return prixmaxachat;
	}
}




