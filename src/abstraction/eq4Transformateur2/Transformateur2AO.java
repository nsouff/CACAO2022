package abstraction.eq4Transformateur2;

import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.appelsOffres.IAcheteurAO;
import abstraction.eq8Romu.appelsOffres.IVendeurAO;
import abstraction.eq8Romu.appelsOffres.OffreVente;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;
import abstraction.eq8Romu.appelsOffres.SuperviseurVentesAO;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;



//Nawfel
public class Transformateur2AO extends Transformateur2Acteur implements IAcheteurAO, IVendeurAO {
	
	//achat
	protected HashMap<IVendeurAO, Double> prix;
	
	//vente
	protected SuperviseurVentesAO superviseur;
	
	
	
	
	
	
	public void next() {
		super.next();
	}
	public void initialiser() {
		super.initialiser();
	}
	
	
	
	public Transformateur2AO() {
		super();
		this.prix=new HashMap<IVendeurAO, Double>();
	}

	//achat
	public double proposerPrix(OffreVente offre) {
		journal.ajouter("ProposerPrix("+offre+"):");
		double px = this.prixInit;
		if (this.prix.keySet().contains(offre.getVendeur())) {
			px = this.prix.get(offre.getVendeur());
		}
		journal.ajouter("   je propose "+px);
		return px;
	}

	
	//achat
	//Pour la vrai simulation : changer les chocolats en feve. On achete des feves. Ici pb de stock : les stocks de chocolats vont grossir lorsque l'on achete des feves.
	public void notifierAchatAO(PropositionAchatAO propositionRetenue) {
		double stock = (this.getStockchocolat().getQuantiteStock().keySet().contains(propositionRetenue.getOffre().getChocolat())) ?this.getStockchocolat().getQuantiteStock().get(propositionRetenue.getOffre().getChocolat()) : 0.0;
		this.getStockchocolat().getQuantiteStock().put(propositionRetenue.getOffre().getChocolat(), stock+ propositionRetenue.getOffre().getQuantiteKG());
		this.prix.put(propositionRetenue.getOffre().getVendeur(), propositionRetenue.getPrixKg()-1.0);
		journal.ajouter("   mon prix a ete accepte. Mon prix pour "+propositionRetenue.getOffre().getVendeur()+" passe a "+(propositionRetenue.getPrixKg()-1.0));
	}
	//achat
	public void notifierPropositionNonRetenueAO(PropositionAchatAO propositionNonRetenue) {
		this.prix.put(propositionNonRetenue.getOffre().getVendeur(), propositionNonRetenue.getPrixKg()+0.1);
		journal.ajouter("   mon prix a ete refuse. Mon prix pour "+propositionNonRetenue.getOffre().getVendeur()+" passe a "+(propositionNonRetenue.getPrixKg()+0.1));
	}
	
	
	
	
	
	//vente
	public PropositionAchatAO choisir(List<PropositionAchatAO> propositions) {
		// TODO Auto-generated method stub
		return null;
	}

}
