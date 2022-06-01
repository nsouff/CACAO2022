package abstraction.eq1Producteur1;

import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.general.Variable;

public class Producteur1 extends Producteur1ContratCadre{

	public Producteur1() {	
		super();
	}
	
	
	//Auteur : Khéo
		public List<Variable> getIndicateurs() {
			List<Variable> res=new ArrayList<Variable>();
			res.add(this.getStockBasse());
			res.add(this.getStockHaut_BE());
			res.add(this.getStockMoyenne());
			res.add(this.getStockMoyenne_BE());
			
			res.add(this.getStockBasse_NA());
			res.add(this.getStockHaut_BE_NA());
			res.add(this.getStockMoyenne_NA());
			res.add(this.getStockMoyenne_BE_NA());
			
			res.add(this.getStockBQ());
			
			return res;
		}
}
