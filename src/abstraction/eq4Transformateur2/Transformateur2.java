package abstraction.eq4Transformateur2;

import java.awt.Color;
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
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;

//Nawfel




public class Transformateur2 extends Transformateur2VenteAO{

	private Journal journalcours;
	
	public void next() {
		super.next();
		journalcours.ajouter(Color.white,Color.red,"Cours Feve Basse : "+Filiere.LA_FILIERE.getIndicateur("BourseCacao cours B").getValeur() +" €/kg");
		journalcours.ajouter(Color.white,Color.red,"Cours Feve Moyenne : "+Filiere.LA_FILIERE.getIndicateur("BourseCacao cours M").getValeur() +" €/kg");
		journalcours.ajouter(Color.white,Color.red,"Cours Feve Moyenne BIO : "+Filiere.LA_FILIERE.getIndicateur("BourseCacao cours MBE").getValeur() +" €/kg");
		journalcours.ajouter(Color.white,Color.red,"Cours Feve Haute : "+Filiere.LA_FILIERE.getIndicateur("BourseCacao cours H").getValeur() +" €/kg");
		journalcours.ajouter(Color.white,Color.red,"Cours Feve Haute BIO : "+Filiere.LA_FILIERE.getIndicateur("BourseCacao cours HBE").getValeur() +" €/kg");
		journalcours.ajouter(Color.LIGHT_GRAY,Color.magenta,"-----------------------------------------------------------------------------------------------------------------------");
	}
	public void initialiser() {
		super.initialiser();
	}

	public Transformateur2 () {
		super();
		this.journalcours=new Journal("O'ptiCours", this);
		
		
	}
	

	public LinkedList<String> getMarquesChocolat() {
		LinkedList<String> res = new LinkedList<String>();
		res.add("O'ptella");//bas de gamme
		res.add("O'max");//moyen de gamme
		res.add("O'lait");//moyen de gamme bio
		res.add("O'Chock");//haut de gamme 
		res.add("O'vert");//haut de gamme bio
		return res;
	}
	public Journal getJournalcours() {
		return journalcours;
	}
	@Override
	public Stock<Chocolat> getStockchocolat() {
		// TODO Auto-generated method stub
		return null;
	}

}
	

