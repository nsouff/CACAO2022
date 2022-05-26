package abstraction.eq4Transformateur2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.Romu;
import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.bourseCacao.ExempleAcheteurBourseCacao;
import abstraction.eq8Romu.bourseCacao.ExempleVendeurBourseCacao;
import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IMarqueChocolat;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;

//Nawfel




public class Transformateur2 extends Transformateur2VenteAO{

	
	public void next() {
		super.next();
		journal.ajouter("Cours Feve Basse : "+Filiere.LA_FILIERE.getIndicateur("BourseCacao cours B").getValeur() +" €/kg");
		journal.ajouter("Cours Feve Moyenne : "+Filiere.LA_FILIERE.getIndicateur("BourseCacao cours M").getValeur() +" €/kg");
	}
	public void initialiser() {
		super.initialiser();
	}

	public Transformateur2 () {
		super();
		
		
	}
	

	public LinkedList<String> getMarquesChocolat() {
		LinkedList<String> res = new LinkedList<String>();
		res.add("O'ptella");
		res.add("O'max");
//		res.add("O'max");
		return res;
	}

}
	

