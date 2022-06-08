package abstraction.eq4Transformateur2;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.appelsOffres.IVendeurAO;
import abstraction.eq8Romu.appelsOffres.PropositionAchatAO;
import abstraction.eq8Romu.appelsOffres.SuperviseurVentesAO;
import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;

public abstract class Transformateur2VenteAO extends Transformateur2Bourse implements IVendeurAO {
	protected SuperviseurVentesAO superviseur;
	protected double prix_minB ;
	protected double prix_minM; //Lorsque l'on est vendeur d'une Appel d'Offre
 
 
 public Transformateur2VenteAO() {
		super();
		this.prix_minB= 4;
		this.prix_minM = 5;
	}
 
	public void initialiser() {
		super.initialiser();
		this.superviseur = (SuperviseurVentesAO)(Filiere.LA_FILIERE.getActeur("Sup.AO"));
		//journal.ajouter("PrixMin=="+this.prixMin);

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
		
		
		//Pour les Vente en Appel d'Offre. On appelle une offre lorsque un stock de chocolatdemarque depasse les 50000kg, on en propose 8000kg.
		super.next();
		this.getJournalVente().ajouter(Color.black,Color.white,"---------------------------------------------------------------------------------------------------------------");
		this.getJournalVente().ajouter(Color.black,Color.white,"----------------------------------APPELS D'OFFRES DU STEP------------------------------------------------------");
		this.getJournalVente().ajouter(Color.black,Color.white,"---------------------------------------------------------------------------------------------------------------");
		double onDoitLivrer = 0;
		for(ChocolatDeMarque c :this.getStockchocolatdemarque().getStock().keySet()) {
			for ( ExemplaireContratCadre i : this.mesContratEnTantQueVendeur) {
				if(i.getProduit().equals(c)) {
					onDoitLivrer = onDoitLivrer+ i.getQuantiteALivrerAuStep();
				}
			
			}
		double q = 0.8*(this.getStockchocolatdemarque().getQuantite(c))-onDoitLivrer;
		if(q>250) {
			PropositionAchatAO retenue = superviseur.vendreParAO(this, cryptogramme, c, q, false);
		if (retenue!=null) {
			this.getStockchocolatdemarque().enlever(retenue.getOffre().getChocolat(), retenue.getOffre().getQuantiteKG());
			this.getJournalVente().ajouter("Merci à "+retenue.getAcheteur().getNom()+ " ! Faites bon usage de vos "+ retenue.getOffre().getQuantiteKG()+" kg.");
			this.getJournalVente().ajouter(Color.white,Color.red,"-----------------------------------------------------------------------------------------------------------------");
		} 
		else {
			this.getJournalVente().ajouter("Nous ne concluerons pas d'affaire aujourd'hui ! Peut-être la prochaine fois ;)");
			this.getJournalVente().ajouter(Color.white,Color.red,"-----------------------------------------------------------------------------------------------------------------");
	
			}
		}
	}
		
		this.getJournalVente().ajouter("");
		this.getJournalVente().ajouter(Color.black,Color.white,"---------------------------------------------------------------------------------------------------------------");
		this.getJournalVente().ajouter(Color.black,Color.white,"--------------------------------------FIN DU STEP--------------------------------------------------------------");
		this.getJournalVente().ajouter(Color.black,Color.white,"---------------------------------------------------------------------------------------------------------------");
		this.getJournalVente().ajouter("");
		this.getJournalVente().ajouter("");
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
// Gabriel modifications pour prix minimal au kg
	public PropositionAchatAO choisir(List<PropositionAchatAO> propositions) {
		if (propositions==null) {
			return null;
		} else {
			
			this.getJournalVente().ajouter("Oyé Oyé, nous vendons "+propositions.get(0).getOffre().getQuantiteKG()+" kg de "+propositions.get(0).getOffre().getChocolat()+" !");
			this.getJournalVente().ajouter("Nous avons reçu un total de " + (propositions.size()) + " proposition(s) pour notre offre.");
			this.getJournalVente().ajouter("Les prix vont de "+ propositions.get(propositions.size()-1).getPrixKg() +"€ à "+ propositions.get(0).getPrixKg()+ "€ au kg !");
			PropositionAchatAO meilleur_proposition=propositions.get(0);
			for(PropositionAchatAO p : propositions) {
				//On choisit l'offre la plus cher qui ne nous met pas en négatif de stock de chocolatdemarque
				//if (p.getPrixKg()>meilleur_proposition.getPrixKg() && p.getOffre().getQuantiteKG()<super.getStockchocolatdemarque().getQuantite(p.getOffre().getChocolat())){
				//	meilleur_proposition=p;
				//}
			}
			
			PropositionAchatAO retenue = meilleur_proposition;
			if(retenue.getOffre().getChocolat().equals(this.getChocolatsProduits().get(0))) {
				if (retenue.getPrixKg()>(this.prixVouluB())) {
					this.getJournalVente().ajouter("  --> Nous choisissons l'offre de  "+retenue.getAcheteur().getNom());
					
					return retenue;
				} else {
					
					return null;
				}
			}
			if(retenue.getOffre().getChocolat().equals(this.getChocolatsProduits().get(1))) {
				if (retenue.getPrixKg()>(this.prixVouluM())) {
					this.getJournalVente().ajouter("  --> Nous choisissons l'offre de  "+retenue.getAcheteur().getNom());
					
					return retenue;
				} else {
					
					
					return null;
				}
			}
			if(retenue.getOffre().getChocolat().equals(this.getChocolatsProduits().get(2))) {
				if (retenue.getPrixKg()>(this.prixVouluMb())) {
					this.getJournalVente().ajouter("  --> Nous choisissons l'offre de  "+retenue.getAcheteur().getNom());
					
					return retenue;
				} else {
					
					
					return null;
				}
			}
			if(retenue.getOffre().getChocolat().equals(this.getChocolatsProduits().get(3))) {
				if (retenue.getPrixKg()>(this.prixVouluH())) {
					this.getJournalVente().ajouter("  --> Nous choisissons l'offre de  "+retenue.getAcheteur().getNom());
					
					return retenue;
				} else {
					this.getJournalVente().ajouter("  --> je ne retiens rien");
					
					return null;
				}
			}
			if(retenue.getOffre().getChocolat().equals(this.getChocolatsProduits().get(4))) {
				if (retenue.getPrixKg()>(this.prixVouluHb())) {
					this.getJournalVente().ajouter("  --> Nous choisissons l'offre de  "+retenue.getAcheteur().getNom());
					
					return retenue;
				} else {
					this.getJournalVente().ajouter("  --> je ne retiens rien");
					
					return null;
				}
			}
			}
		return null;
		}


//Gabriel
public double prixVouluB() { 
	 return (this.prixMinB.getValeur()+0.12+ Filiere.LA_FILIERE.getIndicateur("coutTransformation").getValeur())*this.marge; 
	 // Calcul du prix de vente voulu pour un chocolat de basse 
	 //qualité en fonction du prix des fèves en bourse, du prix de transformationet du cout de stockage
}
public double prixVouluM() { 
	 return (this.prixMinM.getValeur()+0.12+ Filiere.LA_FILIERE.getIndicateur("coutTransformation").getValeur())*this.marge;
}
public double prixVouluMb() { 
	 return (this.prixMinMb.getValeur()+0.12+ Filiere.LA_FILIERE.getIndicateur("coutTransformation").getValeur())*this.marge;
}
public double prixVouluH() { 
	 return (this.prixMinH.getValeur()+0.12+ Filiere.LA_FILIERE.getIndicateur("coutTransformation").getValeur())*this.marge;
}
public double prixVouluHb() { 
	 return (this.prixMinHb.getValeur()+0.12+ Filiere.LA_FILIERE.getIndicateur("coutTransformation").getValeur())*this.marge;
}
public double prixVouluOri(double prix_achat) { 
	 return (prix_achat + Filiere.LA_FILIERE.getParametre("coutTransformation").getValeur() + super.coutStockage()*
			 (this.getStockchocolatdemarque().getStocktotal()+ this.getStockfeve().getStocktotal())+ Filiere.LA_FILIERE.getParametre("coutOriginal").getValeur())
			 *this.getMarge(); 
	 // Calcul du prix de vente voulu en fonction du prix d'achat précédent, du prix de transformation,
	 // du cout de stockage, de l'origininalité et de la marge voulue
}
// Gabriel
public boolean StockDispo() {
	 return false; // getStock() - quantitéVoulue > 0

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

