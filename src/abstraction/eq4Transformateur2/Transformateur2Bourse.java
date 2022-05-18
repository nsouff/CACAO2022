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
	

	public Transformateur2Bourse() {
		super();
		this.quantiteEnKg=quantiteEnKg;


	}

	public void next() {
		super.next();
	}
	public void initialiser() {
		super.initialiser();
	}
	
//Marie et Jad
	public double demande(Feve f, double cours) {
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


//Marie et Jad
	public void notificationAchat(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		this.getStockfeve().ajouter(f,quantiteEnKg);
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), coursEnEuroParKg*quantiteEnKg);
		
	}

//Marie et Jad
	public void notificationBlackList(int dureeEnStep) {
		//this.journal.ajouter("Aie... je suis blackliste... Nous sommes pauvres... :(");
		
	}

	
}
