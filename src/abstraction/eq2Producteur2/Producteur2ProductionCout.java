package abstraction.eq2Producteur2;

import abstraction.eq8Romu.produits.Feve;
import java.util.HashMap;

/**
 * 
 * @author Jules DORE
 *
 */

public class Producteur2ProductionCout extends Plantation {

	public double coutHQ_BE=0.001;//cout par arbre
	public double coutMQ_BE=0.001;
	public double coutHQ=0.001;
	public double coutMQ=0.001;
	public double coutBQ=0.001;
	public HashMap<Feve,Double> coutParKg;

	public Producteur2ProductionCout() {
		this.coutParKg=new HashMap<Feve,Double>();
		this.coutParKg.put(Feve.FEVE_HAUTE_BIO_EQUITABLE, (this.coutHQ_BE*this.getNbArbre(Feve.FEVE_HAUTE_BIO_EQUITABLE))/this.production(Feve.FEVE_HAUTE_BIO_EQUITABLE));
		this.coutParKg.put(Feve.FEVE_HAUTE, (this.coutHQ*this.getNbArbre(Feve.FEVE_HAUTE))/this.production(Feve.FEVE_HAUTE));
		this.coutParKg.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE, (this.coutMQ_BE*this.getNbArbre(Feve.FEVE_MOYENNE_BIO_EQUITABLE))/this.production(Feve.FEVE_MOYENNE_BIO_EQUITABLE));
		this.coutParKg.put(Feve.FEVE_MOYENNE, (this.coutMQ*this.getNbArbre(Feve.FEVE_MOYENNE))/this.production(Feve.FEVE_MOYENNE));
		this.coutParKg.put(Feve.FEVE_BASSE, (this.coutBQ*this.getNbArbre(Feve.FEVE_BASSE))/this.production(Feve.FEVE_BASSE));
	}



}
