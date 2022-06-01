package abstraction.eq4Transformateur2;

import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.appelsOffres.IAcheteurAO;
import abstraction.eq8Romu.appelsOffres.IVendeurAO;
import abstraction.eq8Romu.appelsOffres.OffreVente;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;
import abstraction.eq8Romu.appelsOffres.SuperviseurVentesAO;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;



//Nawfel
public abstract class Transformateur2AchatAO extends Transformateur2Bourse implements IAcheteurAO {
	
	protected double prixInit;// Lorsque l'on est acheteur d'une Appel d'Offre
	protected HashMap<IVendeurAO, Double> prix;
	
	
	
	public void next() {
		super.next();
	}
	public void initialiser() {
		super.initialiser();
		
	}
	
	public Transformateur2AchatAO() {
		super();
		this.prix=new HashMap<IVendeurAO, Double>();
		this.prixInit=15;
	}


	//Nawfel (Decommenter les 2 lignes si un jour on achete par AO)
	
	public double proposerPrix(OffreVente offre) {
		if (offre.getVendeur().getNom().equals(super.getNom())) {
			return 0;
		} else {
		//journal.ajouter("ProposerPrix("+offre+"):");
		double px = this.prixInit;
		if (this.prix.keySet().contains(offre.getVendeur())) {
			px = this.prix.get(offre.getVendeur());
		}
		//journal.ajouter("   je propose "+px);
		return px;
		}
		
	}

	
	//Si un jour on fait des achat par AO, on changera le stock chocolat en bas là en stock feve
	public void notifierAchatAO(PropositionAchatAO propositionRetenue) {
//		double stock = (this.getStockchocolatdemarque().getStock().keySet().contains(propositionRetenue.getOffre().getChocolat())) ?this.getStockchocolatdemarque().getStock().get(propositionRetenue.getOffre().getChocolat()) : 0.0;
//		this.getStockchocolatdemarque().getStock().put(propositionRetenue.getOffre().getChocolat(), stock+ propositionRetenue.getOffre().getQuantiteKG());
//		this.prix.put(propositionRetenue.getOffre().getVendeur(), propositionRetenue.getPrixKg()-1.0);
//		journal.ajouter("   mon prix a ete accepte. Mon prix pour "+propositionRetenue.getOffre().getVendeur()+" passe a "+(propositionRetenue.getPrixKg()-1.0));
	}

	public void notifierPropositionNonRetenueAO(PropositionAchatAO propositionNonRetenue) {
//		this.prix.put(propositionNonRetenue.getOffre().getVendeur(), (propositionNonRetenue.getPrixKg())*1.3);
//		journal.ajouter("   mon prix a ete refuse. Mon prix" +propositionNonRetenue.getOffre().getVendeur()+" passe a "+(propositionNonRetenue.getPrixKg())*1.3);
	}
	
//	protected HashMap getCommande() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	
	
	


}
