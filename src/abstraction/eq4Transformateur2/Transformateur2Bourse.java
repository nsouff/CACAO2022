package abstraction.eq4Transformateur2;

import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.bourseCacao.ExempleAbsAcheteurBourseCacao;
import abstraction.eq8Romu.bourseCacao.IAcheteurBourse;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Feve;

// Marie

public class Transformateur2Bourse extends ExempleAbsAcheteurBourseCacao implements IAcheteurBourse{
	
	private double quantiteEnKg;
	protected Variable prixSeuil;

	public Transformateur2Bourse(Feve feve, double stock, double quantiteEnKg) {
		super(feve, stock);
		this.quantiteEnKg=quantiteEnKg;


	}
//Marie
	public double demande(Feve f, double cours) {
		/*if (cours < prixSeuil) {
			return ;
			
		} else {*/
			return 0.0;
		}


//Marie
	public void notificationAchat(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		this.stockFeve.setValeur(this, this.stockFeve.getValeur()+quantiteEnKg);
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), coursEnEuroParKg*quantiteEnKg);
		
	}

//Marie
	public void notificationBlackList(int dureeEnStep) {
		this.journal.ajouter("Aie... je suis blackliste... Nous sommes pauvres... :(");
		
	}

}
