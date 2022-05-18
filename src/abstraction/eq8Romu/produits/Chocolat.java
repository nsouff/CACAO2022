 package abstraction.eq8Romu.produits;

import abstraction.eq8Romu.filiere.Filiere;

public enum Chocolat {
	HQ_BE_O(Gamme.HAUTE,  true,  true ), // Haute Qualite  ,  Bio-Equitable  , Original
	HQ_BE  (Gamme.HAUTE,  true,  false ),// Haute Qualite  ,  Bio-Equitable  , pas Original 
	HQ_O   (Gamme.HAUTE,  false, true ), // Haute Qualite  ,pas Bio-Equitable, Original
	HQ     (Gamme.HAUTE,  false, false ),// Haute Qualite  ,pas Bio-Equitable, pas Original 
	MQ_BE_O(Gamme.MOYENNE,true,  true ), // Moyenne Qualite,  Bio-Equitable  , Original
	MQ_BE  (Gamme.MOYENNE,true,  false ),// Moyenne Qualite,  Bio-Equitable  , pas Original 
	MQ_O   (Gamme.MOYENNE,false, true ), // Moyenne Qualite,pas Bio-Equitable, Original
	MQ     (Gamme.MOYENNE,false, false ),// Moyenne Qualite,pas Bio-Equitable, pas Original 
	BQ_O   (Gamme.BASSE,  false, true ), // Basse Qualite  ,pas Bio-Equitable, Original
	BQ     (Gamme.BASSE,  false, false );// Basse Qualite  ,pas Bio-Equitable, pas Original 
	
	private Gamme gamme;
	private boolean bioEquitable;
	private boolean original;
	
	Chocolat(Gamme gamme, boolean bioEquitable, boolean original) {
		this.gamme = gamme;
		this.bioEquitable = bioEquitable;
		this.original = original;
	}
	public static Chocolat get(Gamme gamme, boolean bioEquitable, boolean original) {
		for (Chocolat c : Chocolat.values()) {
			if (c.gamme==gamme && c.isBioEquitable()==bioEquitable && c.isOriginal()==original) {
				return c;
			}
		}
		return null;
	}
	public Gamme getGamme() {
		return this.gamme;
	}
	public boolean isBioEquitable() {
		return this.bioEquitable;
	}
	public boolean isOriginal() {
		return this.original;
	}
	public double qualite() {
		double qualite;
		switch (getGamme()) {
		case BASSE : qualite=Filiere.LA_FILIERE.getParametre("qualite basse").getValeur();
		case MOYENNE : qualite=Filiere.LA_FILIERE.getParametre("qualite moyenne").getValeur();
		default : qualite=Filiere.LA_FILIERE.getParametre("qualite haute").getValeur(); //HAUTE
		}
		if (isBioEquitable()) {
			qualite=qualite+Filiere.LA_FILIERE.getParametre("gain qualite bioequitable").getValeur();
		}
		if (isOriginal()) {
			qualite=qualite+Filiere.LA_FILIERE.getParametre("gain qualite original").getValeur();
		}
		return qualite;
	}
	public static void main(String[] args) {
		for (Chocolat c : Chocolat.values()) {
			System.out.println(c);
		}
		System.out.println("get->"+Chocolat.get(Gamme.HAUTE, true, true));
	}
}
