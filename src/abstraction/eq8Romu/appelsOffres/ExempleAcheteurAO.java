package abstraction.eq8Romu.appelsOffres;

import java.util.HashMap;



public class ExempleAcheteurAO extends ExempleAbsAcheteurAO implements IAcheteurAO {

	protected HashMap<IVendeurAO, Double> prix;
	
	public ExempleAcheteurAO(double prixInit) {
		super(prixInit);
		this.prix=new HashMap<IVendeurAO, Double>();
	}

	public double proposerPrix(OffreVente offre) {
		journal.ajouter("ProposerPrix("+offre+"):");
			double px = this.prixInit;
			if (this.prix.keySet().contains(offre.getVendeur())) {
				px = this.prix.get(offre.getVendeur());
			}
			journal.ajouter("   je propose "+px);
			return px;
	}

	public void notifierAchatAO(PropositionAchatAO propositionRetenue) {
		double stock = (this.stock.keySet().contains(propositionRetenue.getOffre().getChocolat())) ?this.stock.get(propositionRetenue.getOffre().getChocolat()) : 0.0;
		this.stock.put(propositionRetenue.getOffre().getChocolat(), stock+ propositionRetenue.getOffre().getQuantiteKG());
		this.prix.put(propositionRetenue.getOffre().getVendeur(), propositionRetenue.getPrixKg()-1.0);
		journal.ajouter("   mon prix a ete accepte. Mon prix pour "+propositionRetenue.getOffre().getVendeur()+" passe a "+(propositionRetenue.getPrixKg()-1.0));
	}

	public void notifierPropositionNonRetenueAO(PropositionAchatAO propositionNonRetenue) {
		this.prix.put(propositionNonRetenue.getOffre().getVendeur(), propositionNonRetenue.getPrixKg()+0.1);
		journal.ajouter("   mon prix a ete refuse. Mon prix pour "+propositionNonRetenue.getOffre().getVendeur()+" passe a "+(propositionNonRetenue.getPrixKg()+0.1));
	}
}
