package abstraction.eq4Transformateur2;

import java.util.List;

import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.bourseCacao.ExempleAbsAcheteurBourseCacao;
import abstraction.eq8Romu.bourseCacao.IAcheteurBourse;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IFabricantChocolatDeMarque;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;

// Marie et JAd

public class Transformateur2Bourse extends Transformateur2Transfo implements IAcheteurBourse{
	
	private double quantiteEnKg;
	private double prixseuilBG;
	private double prixseuilMG;
	private double minBG;
	private double minMG;
	

	public Transformateur2Bourse() {
		super();
		this.quantiteEnKg=quantiteEnKg;
		this.prixseuilBG=prixseuilBG;
		this.prixseuilMG=prixseuilMG;
		this.minBG=minBG;
		this.minMG=minMG;
	}

	public void next() {
		super.next();
	}
	public void initialiser() {
		super.initialiser();
	}
	
// Marie 
	
/*public double prixSeuilBG(double prixseuilBG, double minBG) {
	BourseCacao b	=(BourseCacao)(Filiere.LA_FILIERE.getActeur("BourseCacao"));
	if(Filiere.LA_FILIERE.getEtape()<27) {
		for(int i=0;i<5;i++) {
			if(b.getCours(Feve.FEVE_BASSE).getMin()<minBG) {
				this.minBG=
			}
			
		}
		prixseuilBG= prixseuilBG + b.getCours(Feve.FEVE_BASSE).getMin();	
		Filiere.LA_FILIERE.getEtape()
		bourse.getCours(getFeve()).getMin()
		}else {
		
	}
}

public double prixSeuilMG() {
	BourseCacao b	=(BourseCacao)(Filiere.LA_FILIERE.getActeur("BourseCacao"));
	if(Filiere.LA_FILIERE.getEtape()<27) {
		b.getCours(Feve.FEVE_MOYENNE);
	}
	
	
}

public double demande(Feve f, double cours) {
	if ((f.getGamme().equals(Gamme.MOYENNE) && !f.isBioEquitable()) && (cours < prixSeuilMG())) {
			return this.getCapaciteStockageFixe().getValeur()*0.4-this.getStockfeve().getQuantite(f);
			
	  }	else if ((f.getGamme().equals(Gamme.BASSE) && !f.isBioEquitable())&& (cours< prixSeuilBG())) {
			return this.getCapaciteStockageFixe().getValeur()*0.6-this.getStockfeve().getQuantite(f);	
		}
			
	 else {
		return 0.0;
	}
	
}
*/
	
//Marie et Jad
/*	public double demande(Feve f, double cours) {
		BourseCacao b	=(BourseCacao)(Filiere.LA_FILIERE.getActeur("BourseCacao"));
		b.getCours(Feve.FEVE_BASSE);
		if (cours < this.getPrixSeuil().getValeur()) {
			if(f.getGamme().equals(Gamme.MOYENNE) && !f.isBioEquitable()) {
				return this.getCapaciteStockageFixe().getValeur()*0.4-this.getStockfeve().getQuantite(f);
			}
			else if (f.getGamme().equals(Gamme.BASSE) && !f.isBioEquitable()) {
				return this.getCapaciteStockageFixe().getValeur()*0.6-this.getStockfeve().getQuantite(f);	
			}
			
		
		} 
		else {
			return 0.0;
		}
		return 0.0;
		}
*/

//Marie et Jad
	public void notificationAchat(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		this.getStockfeve().ajouter(f,quantiteEnKg);
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), coursEnEuroParKg*quantiteEnKg);
		
	}

//Marie et Jad
	public void notificationBlackList(int dureeEnStep) {
		//this.journal.ajouter("Aie... je suis blackliste... Nous sommes pauvres... :(");
		
	}

	@Override
	public double demande(Feve f, double cours) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
