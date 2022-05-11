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
	protected HashMap<Feve,Double> coutParKg;

	public Producteur2ProductionCout() {
		this.coutParKg.put(Feve.FEVE_HAUTE_BIO_EQUITABLE, (this.coutHQ_BE*this.getNbArbre(Feve.FEVE_HAUTE_BIO_EQUITABLE))/this.production(Feve.FEVE_HAUTE_BIO_EQUITABLE));
		this.coutParKg.put(Feve.FEVE_HAUTE, (this.coutHQ*this.getNbArbre(Feve.FEVE_HAUTE))/this.production(Feve.FEVE_HAUTE));
		this.coutParKg.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE, (this.coutMQ_BE*this.getNbArbre(Feve.FEVE_MOYENNE_BIO_EQUITABLE))/this.production(Feve.FEVE_MOYENNE_BIO_EQUITABLE));
		this.coutParKg.put(Feve.FEVE_MOYENNE, (this.coutMQ*this.getNbArbre(Feve.FEVE_MOYENNE))/this.production(Feve.FEVE_MOYENNE));
		this.coutParKg.put(Feve.FEVE_BASSE, (this.coutBQ*this.getNbArbre(Feve.FEVE_BASSE))/this.production(Feve.FEVE_BASSE));
	}



}
