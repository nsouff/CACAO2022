package abstraction.eq8Romu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.bourseCacao.FiliereTestBourse;
import abstraction.eq8Romu.clients.FiliereTestClientFinal;
import abstraction.eq8Romu.contratsCadres.FiliereTestContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;

public class Romu implements IActeur {
	
	@SuppressWarnings("unused")
	private Integer cryptogramme;
	private Variable qualiteHaute;  // La qualite d'un chocolat de gamme haute 
	private Variable qualiteMoyenne;// La qualite d'un chocolat de gamme moyenne  
	private Variable qualiteBasse;  // La qualite d'un chocolat de gamme basse
	private Variable gainQualiteBioEquitable;// Le gain en qualite des chocolats bio equitables
	private Variable gainQualiteOriginal;// Le gain en qualite des chocolats originaux
	private Variable partDeLaMarqueDansLaQualitePercu;// Le gain en qualite des chocolats originaux
	
	public Romu() {
		this.qualiteHaute   = new Variable("qualite haute", "<html>Qualite du chocolat<br>de gamme haute</html>",this, 0.0, 10.0, 3.0);
		this.qualiteMoyenne = new Variable("qualite moyenne", "<html>Qualite du chocolat<br>de gamme moyenne</html>",this, 0.0, 10.0, 2.0);
		this.qualiteBasse   = new Variable("qualite basse", "<html>Qualite du chocolat<br>de gamme basse</html>",this, 0.0, 10.0, 1.0);
		this.gainQualiteBioEquitable  = new Variable("gain qualite bioequitable", "<html>Gain en qualite des<br>chocolats bio equitables</html>",this, 0.0, 5.0, 0.5);
		this.gainQualiteOriginal  = new Variable("gain qualite original", "<html>Gain en qualite des<br>chocolats originaux</html>",this, 0.0, 5.0, 0.5);
		this.partDeLaMarqueDansLaQualitePercu  = new Variable("impact marque qualite percue", "<html>% de la qualite percue de la marque dans la qualite percue du chocolat</html>",this, 0.0, 0.5, 0.3);
	}

	public String getNom() {
		return "EQ8";
	}

	public String getDescription() {
		return "createur des autres acteurs de la filiere";
	}

	public Color getColor() {
		return new Color(96, 125, 139);
	}

	public void initialiser() {
	}

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}
	
	public void next() {
	}

	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		filieres.add("TESTCLIENT"); 
		filieres.add("TESTCC"); 
		filieres.add("TESTBOURSE"); 
		return filieres;
	}

	public Filiere getFiliere(String nom) {
		switch (nom) { 
		case "TESTCLIENT" : return new FiliereTestClientFinal();
		case "TESTCC" : return new FiliereTestContratCadre();
		case "TESTBOURSE" : return new FiliereTestBourse();
	    default : return null;
		}
	}

	public List<Variable> getIndicateurs() {
		List<Variable> res =  new ArrayList<Variable>();
		return res;
	}

	public List<Variable> getParametres() {
		List<Variable> p= new ArrayList<Variable>();
		p.add(this.qualiteHaute);
		p.add(this.qualiteMoyenne);
		p.add(this.qualiteBasse);
		p.add(this.gainQualiteBioEquitable);
		p.add(this.gainQualiteOriginal); 
		p.add(this.partDeLaMarqueDansLaQualitePercu);
		return p;
	}

	public List<Journal> getJournaux() {
		return new ArrayList<Journal>();
	}
	
	public void notificationFaillite(IActeur acteur) {
		if (this==acteur) {
			System.out.println("They killed Romu... ");
		} else {
			System.out.println("Poor "+acteur.getNom()+"... We will miss you. "+this.getNom());
		}
	}
	
	public void notificationOperationBancaire(double montant) {
	}
}
