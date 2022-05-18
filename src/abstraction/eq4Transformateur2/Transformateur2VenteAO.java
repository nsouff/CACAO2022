package abstraction.eq4Transformateur2;

import java.util.List;

import abstraction.eq8Romu.appelsOffres.IVendeurAO;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;
import abstraction.eq8Romu.appelsOffres.SuperviseurVentesAO;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;

public class Transformateur2VenteAO extends Transformateur2AchatAO implements IVendeurAO {
	protected SuperviseurVentesAO superviseur;
	protected double prixMin; //Lorsque l'on est vendeur d'une Appel d'Offre
 
 
 public Transformateur2VenteAO() {
		super();
		this.prixMin=5;
	}
 
	public void initialiser() {
		super.initialiser();
		this.superviseur = (SuperviseurVentesAO)(Filiere.LA_FILIERE.getActeur("Sup.AO"));
		journal.ajouter("PrixMin=="+this.prixMin);

	}
	public void next() {
//		if (Filiere.LA_FILIERE.getEtape()>=1) {
//			if (this.getStockchocolat().getStocktotal()>5000) {
//				PropositionAchatAO vente = superviseur.vendreParAO(this, cryptogramme, new ChocolatDeMarque(Chocolat.BQ,this.getMarquesChocolat().get(0)),(this.getStockchocolat().getStocktotal()-5000)/3 , false);
//				if (vente!=null) {
//					this.getStockchocolatdemarque().enlever(null, NewCap)
//					this.setStock(getStockchocolat().getStocktotal()-vente.getOffre().getQuantiteKG());
//				
//				}
//			}
//		}
		
		
		//Pour les Vente en Appel d'Offre. On appelle une offre lorsque un stock de chocolatdemarque depasse les 2000kg, on en propose 2000kg.
			super.next();	
		for(ChocolatDeMarque c :this.getStockchocolatdemarque().getStock().keySet()) {
					if(this.getStockchocolatdemarque().getStock().get(c)>2000) {
						PropositionAchatAO retenue = superviseur.vendreParAO(this, cryptogramme, c, 2000.0, false);
						if (retenue!=null) {
							this.getStockchocolatdemarque().enlever(retenue.getOffre().getChocolat(), retenue.getOffre().getQuantiteKG());
							journal.ajouter("vente de "+retenue.getOffre().getQuantiteKG()+" kg a "+retenue.getAcheteur().getNom());
						} else {
							journal.ajouter("pas d'offre retenue");
						}
					}
				}
	}
@Override
//public PropositionAchatAO choisir(List<PropositionAchatAO> propositions) {
//	// TODO Auto-generated method stub
//	if (propositions==null) {
//		return null;
//	} else {
//		PropositionAchatAO MeilleureOffre = propositions.get(0);
//		if (MeilleureOffre.getPrixKg()>0) { // il faut mettre à la place de 0 qui est une valeur indicative de test, le prix d'achat
//			return MeilleureOffre;
//		} else {
//			return null;
//		}
//	}
//}

//Nawfel
	//Vente en AO : comment choisir parmi les offres
	public PropositionAchatAO choisir(List<PropositionAchatAO> propositions) {
		this.journal.ajouter("propositions : "+propositions);
		if (propositions==null) {
			return null;
		} else {
			PropositionAchatAO meilleur_proposition=propositions.get(0);
			for(PropositionAchatAO p : propositions) {
				if (p.getPrixKg()>meilleur_proposition.getPrixKg()){
					meilleur_proposition=p;
				}
			}
			PropositionAchatAO retenue = meilleur_proposition;
			if (retenue.getPrixKg()>this.prixMin) {
				this.journal.ajouter("  --> je choisis "+retenue);
				return retenue;
			} else {
				this.journal.ajouter("  --> je ne retiens rien");
				return null;
			}
		}
	}

//Gabriel
public double prixVoulu(double prix_achat) { 
	 return (prix_achat + Filiere.LA_FILIERE.getParametre("coutTransformation").getValeur() + super.coutStockage()*
			 (this.getStockchocolat().getStocktotal()+ this.getStockfeve().getStocktotal()))
			 *this.getMarge(); 
	 // Calcul du prix de vente voulu en fonction du prix d'achat précédent, du prix de transformation,
	 // du cout de stockage, de l'origininalité et de la marge voulue
}
public double prixVouluOri(double prix_achat) { 
	 return (prix_achat + Filiere.LA_FILIERE.getParametre("coutTransformation").getValeur() + super.coutStockage()*
			 (this.getStockchocolat().getStocktotal()+ this.getStockfeve().getStocktotal())+ Filiere.LA_FILIERE.getParametre("coutOriginal").getValeur())
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

