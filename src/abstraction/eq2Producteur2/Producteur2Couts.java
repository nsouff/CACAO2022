package abstraction.eq2Producteur2;

import abstraction.eq8Romu.produits.Feve;
import java.util.HashMap;

/**
 * 
 * @author Jules DORE
 *
 */

public class Producteur2Couts extends Producteur2Plantation {

	public static double coutHQ_BE=0.5; //coût par arbre
	public static double coutMQ_BE=0.4;
	public static double coutHQ=0.3;
	public static double coutMQ=0.2;
	public static double coutBQ=0.1;
	public HashMap<Feve,Double> coutParKg;

	public Producteur2Couts() {
		super();
		this.coutParKg=new HashMap<Feve,Double>();;
	}

	public double getCout(Feve feve) {
		return this.coutParKg.get(feve);
	}

	public void setCoutParKg() {
		this.coutParKg.put(Feve.FEVE_HAUTE_BIO_EQUITABLE, (this.coutHQ_BE*this.getNbArbre(Feve.FEVE_HAUTE_BIO_EQUITABLE))/this.production(Feve.FEVE_HAUTE_BIO_EQUITABLE));
		this.coutParKg.put(Feve.FEVE_HAUTE, (this.coutHQ*this.getNbArbre(Feve.FEVE_HAUTE))/this.production(Feve.FEVE_HAUTE));
		this.coutParKg.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE, (this.coutMQ_BE*this.getNbArbre(Feve.FEVE_MOYENNE_BIO_EQUITABLE))/this.production(Feve.FEVE_MOYENNE_BIO_EQUITABLE));
		this.coutParKg.put(Feve.FEVE_MOYENNE, (this.coutMQ*this.getNbArbre(Feve.FEVE_MOYENNE))/this.production(Feve.FEVE_MOYENNE));
		this.coutParKg.put(Feve.FEVE_BASSE, (this.coutBQ*this.getNbArbre(Feve.FEVE_BASSE))/this.production(Feve.FEVE_BASSE));
	}
	
	public void next() {
		super.next();
		this.setCoutParKg();
	}
	
	public void initialiser() {
		super.initialiser();
	}
}