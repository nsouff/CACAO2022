package abstraction.eq4Transformateur2;

import java.util.List;

import abstraction.eq8Romu.appelsOffres.IVendeurAO;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;
import abstraction.eq8Romu.appelsOffres.SuperviseurVentesAO;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Transformateur2Vente extends Transformateur2Achat implements IVendeurAO {
	protected SuperviseurVentesAO superviseur;
 
 
 public Transformateur2Vente(double stocktotalfeve, double stocktotalchoco) {
		super(stocktotalfeve, stocktotalchoco);
		// TODO Auto-generated constructor stub
	}
	public void initialiser() {
		this.superviseur = (SuperviseurVentesAO)(Filiere.LA_FILIERE.getActeur("Sup.AO"));

	}
	public void next() {
		if (Filiere.LA_FILIERE.getEtape()>=1) {
			if (this.getStockchocolat().quantiteStockTotale()>5000) {
				PropositionAchatAO vente = superviseur.vendreParAO(this, cryptogramme, new ChocolatDeMarque(Chocolat.BQ,this.getMarquesChocolat().get(0)),(this.getStockchocolat().quantiteStockTotale()-5000)/3 , false);
				if (vente!=null) {
					this.setStock(getStockchocolat().quantiteStockTotale()-vente.getOffre().getQuantiteKG());
				
				}
			}
		}
	}
@Override
public PropositionAchatAO choisir(List<PropositionAchatAO> propositions) {
	// TODO Auto-generated method stub
	if (propositions==null) {
		return null;
	} else {
		PropositionAchatAO MeilleureOffre = propositions.get(0);
		if (MeilleureOffre.getPrixKg()>0) { // il faut mettre à la place de 0 qui est une valeur indicative de test, le prix d'achat
			return MeilleureOffre;
		} else {
			return null;
		}
	}
}
//Gabriel
public double prixVoulu(double prix_achat) { 
	 return (prix_achat + Filiere.LA_FILIERE.getParametre("coutTransformation").getValeur() + this.getCout()*
			 (this.getStockchocolat().quantiteStockTotale()+ this.getStockfeve().quantiteStockTotale()))
			 *this.getMarge(); 
	 // Calcul du prix de vente voulu en fonction du prix d'achat précédent, du prix de transformation,
	 // du cout de stockage, de l'origininalité et de la marge voulue
}
public double prixVouluOri(double prix_achat) { 
	 return (prix_achat + Filiere.LA_FILIERE.getParametre("coutTransformation").getValeur() + this.getCout()*
			 (this.getStockchocolat().quantiteStockTotale()+ this.getStockfeve().quantiteStockTotale())+ Filiere.LA_FILIERE.getParametre("coutOriginal").getValeur())
			 *this.getMarge(); 
	 // Calcul du prix de vente voulu en fonction du prix d'achat précédent, du prix de transformation,
	 // du cout de stockage, de l'origininalité et de la marge voulue
}
// Gabriel
public boolean StockDispo() {
	 return false; // getStock() - quantitéVoulue > 0

}
// Gabriel
public boolean prixAcceptable(double prix_demande, double prix_achat) {
	 return (prix_demande - this.prixVoulu(prix_achat)>0); // Prix d'achat du distributeur - prix_voulu > 0
}
// Gabriel
public boolean vente() {
	 return false;//this.StockDispo() & this.prixAcceptable(prix_ori, prix_ori);

 // Gabriel
 //public void enlever() {
	 //if (this.vente()){
		 //this.quantite.replace("Fèves", this.quantite("Fèves")-qt);///
		 
	 //}
 //} 

}
}

