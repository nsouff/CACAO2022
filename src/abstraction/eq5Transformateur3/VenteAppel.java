package abstraction.eq5Transformateur3;

import java.awt.Color;
import java.util.List;

import abstraction.eq8Romu.appelsOffres.IVendeurAO;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;
import abstraction.eq8Romu.appelsOffres.SuperviseurVentesAO;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class VenteAppel extends AcheteurContrat implements IVendeurAO {
	public SuperviseurVentesAO superviseur = (SuperviseurVentesAO)(Filiere.LA_FILIERE.getActeur("Sup.AO"));

	@Override
	public PropositionAchatAO choisir(List<PropositionAchatAO> propositions) {
		// TODO Auto-generated method stub
		//for (PropositionAchatAO proposition: propositions) {}; on pourait comparer les achetteur en fonction de la fidélité par ex
		int original=0;
		if (propositions.get(1).getOffre().getChocolat().isOriginal()) { original=1   ;}
		
		
		if (propositions.get(1).getPrixKg()>= this.seuilMaxAchat + this.coutOriginal.getValeur()*original) {
		return propositions.get(1) ;	}
		else return null;
}
	public void next() {
		super.next();
		
		for (Chocolat c: this.stockChocolat.getProduitsEnStock()) {
			
			PropositionAchatAO retenue = 
					superviseur.vendreParAO(this, this.cryptogramme, new ChocolatDeMarque(c,"Bio'riginal"+c), this.stockChocolat.getstock(c)/2, true);
			
			
		;}
		
		}
}