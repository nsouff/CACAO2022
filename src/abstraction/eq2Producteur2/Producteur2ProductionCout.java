package abstraction.eq2Producteur2;

import abstraction.eq8Romu.produits.Feve;
import java.util.HashMap;

/**
 * 
 * @author Jules DORE
 *
 */

public class Producteur2ProductionCout extends Plantation {

	public double coutHQ_BE;//cout par arbre
	public double coutMQ_BE;
	public double coutHQ;
	public double coutMQ;
	public double coutBQ;
	private HashMap<Feve,Double> coutParKg;

	public Producteur2ProductionCout() {
		this.coutParKg.put(Feve.FEVE_HAUTE_BIO_EQUITABLE, 0.0);
		this.coutParKg.put(Feve.FEVE_HAUTE, 0.0);
		this.coutParKg.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE, 0.0);
		this.coutParKg.put(Feve.FEVE_MOYENNE, 0.0);
		this.coutParKg.put(Feve.FEVE_BASSE, 0.0);
	}



}
