package abstraction.eq8Romu.appelsOffres;

public class PropositionAchatAO implements Comparable<PropositionAchatAO>{
	private OffreVente offre;
	private IAcheteurAO acheteur;
	private double prixKg;
	
	public PropositionAchatAO(OffreVente offre, IAcheteurAO acheteur, double prixKg) {
		this.offre = offre;
		this.acheteur = acheteur;
		this.prixKg = prixKg;
	}

	public OffreVente getOffre() {
		return offre;
	}

	public IAcheteurAO getAcheteur() {
		return acheteur;
	}

	public double getPrixKg() {
		return prixKg;
	}

	public String toString() {
		return "["+offre+" a="+acheteur+" px="+prixKg+"]";
	}
	
	public int compareTo(PropositionAchatAO o) {
		return this.getPrixKg()<o.getPrixKg()? -1 : (this.getPrixKg()==o.getPrixKg()? 0 : 1);
	}
}
