package abstraction.eq8Romu.produits;

public enum Feve {

	FEVE_HAUTE_BIO_EQUITABLE(Gamme.HAUTE, true),
	FEVE_HAUTE(Gamme.HAUTE, false),
	FEVE_MOYENNE_BIO_EQUITABLE(Gamme.MOYENNE, true),
	FEVE_MOYENNE(Gamme.MOYENNE, false),
	FEVE_BASSE(Gamme.BASSE, false);
	
	private Gamme gamme;
	private boolean bioequitable;
	
	Feve(Gamme gamme, boolean bioEquitable) {
		this.gamme = gamme;
		this.bioequitable = bioEquitable;
	}
	public Gamme getGamme() {
		return this.gamme;
	}
	public boolean isBioEquitable() {
		return this.bioequitable;
	}

	public static void main(String[] args) {
		for (Feve f : Feve.values()) {
			System.out.println(f);
		}
	}
}
