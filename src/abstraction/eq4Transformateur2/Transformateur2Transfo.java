package abstraction.eq4Transformateur2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
//auteur Jad
public abstract class Transformateur2Transfo<I> extends Transformateur2Stock {
	
//	protected double rdt=Filiere.LA_FILIERE.getIndicateur("rendement").getValeur();
//	protected double prix_transfo= Filiere.LA_FILIERE.getIndicateur("coutTransformation").getValeur();
//	protected double prix_ori=Filiere.LA_FILIERE.getIndicateur("coutOriginal").getValeur();
//	protected double cap=Filiere.LA_FILIERE.getIndicateur("seuilTransformation").getValeur();
	
	private Stock<ChocolatDeMarque> commandes;
	protected double rdt;
	protected double prix_transfo;
	protected double prix_ori;
	protected double cap;
	
	
	
	public void next() {//EN V1 on ne transforme que de façon arbitraire
		
		super.next();
		NewCap=cap;
		this.GetCommandes(mesContratEnTantQueVendeur);
		//il faut régler les qauntités transformées pour chaque types de fèves
		
		//Les transformations non originales courtes
		this.transfo(0.1*cap, false, "courte",Feve.FEVE_BASSE);
		this.transfo(0.1*cap,false,"courte",Feve.FEVE_MOYENNE);
		this.transfo(0.1*cap,false,"courte",Feve.FEVE_MOYENNE_BIO_EQUITABLE);
		this.transfo(0.1*cap,false,"courte",Feve.FEVE_HAUTE);
		this.transfo(0.1*cap,false,"courte",Feve.FEVE_MOYENNE_BIO_EQUITABLE);
		
		//les transformations originales courtes
		this.transfo(0.1*cap, true, "courte",Feve.FEVE_BASSE);
		this.transfo(0.1*cap,true,"courte",Feve.FEVE_MOYENNE);
		this.transfo(0.1*cap,true,"courte",Feve.FEVE_MOYENNE_BIO_EQUITABLE);
		this.transfo(0.1*cap,true,"courte",Feve.FEVE_HAUTE);
		this.transfo(0.1*cap,true,"courte",Feve.FEVE_MOYENNE_BIO_EQUITABLE);
		
		//les transformations originales longues
		this.transfo(0.1*cap, true, "longue",Feve.FEVE_BASSE);
		this.transfo(0.1*cap,true,"longue",Feve.FEVE_MOYENNE);
		this.transfo(0.1*cap,true,"longue",Feve.FEVE_MOYENNE_BIO_EQUITABLE);
		
		//les transformations non originales longues
		this.transfo(0.1*cap, false, "courte",Feve.FEVE_BASSE);
		this.transfo(0.1*cap,false,"courte",Feve.FEVE_MOYENNE);
		this.transfo(0.1*cap,false,"courte",Feve.FEVE_MOYENNE_BIO_EQUITABLE);
		
				
	}
	public void initialiser() {
		super.initialiser();
		cap=Filiere.LA_FILIERE.getIndicateur("seuiTransformation").getValeur();
		rdt=Filiere.LA_FILIERE.getIndicateur("rendement").getValeur();
		prix_transfo=Filiere.LA_FILIERE.getIndicateur("coutTransformation").getValeur();
		prix_ori=Filiere.LA_FILIERE.getIndicateur("coutOriginal").getValeur();
		
	}
	
	public Transformateur2Transfo() {
		super();
		this.commandes = new Stock<ChocolatDeMarque>();
		
	}

	//renvoie une HashMap des chocolats de marques et de la quatité à livrer de ces derniers 
	public void GetCommandes(List<ExemplaireContratCadre> CC) {///POUR LA V2
		Stock comm=new Stock<ChocolatDeMarque>();
		for(ExemplaireContratCadre c:CC) {
			
			comm.ajouter(((ChocolatDeMarque)(c.getProduit())), c.getQuantiteALivrerAuStep());
			this.commandes=comm;
		}
		
		
		
	}
	
	
	//trouve la meilleur combinaison (qui minimise les coûts et si possible a une stratégie)
	//de transformation (types de fèves et de tranfos)
	//pour honorer les commandes
	//Commence par remplir less commandes les plus anciennes
	//honnore les commande par date croissante jusqu'à que ce ne soit plus possible (stock ou capacité de production)

	public void bestCombi(String fev) {//POUR LA V2
	//initialisation des variables:
		//transformations courtes originales
		double COBQ=0.0;
		double COMQ=0.0;
		double COMQBE=0.0;
		double COHQ=0.0;
		double COHQBE=0.0;
		//transformations courtes non originales
		double CBQ=0.0;
		double CMQ=0.0;
		double CMQBE=0.0;
		double CHQ=0.0;
		double CHQBE=0.0;
		//transformations longues originales
		double LOBQ=0.0;
		double LOMQ=0.0;
		double LOMQBE=0.0;
		//transformations longues non originales
		double LBQ=0.0;
		double LMQ=0.0;
		double LMQBE=0.0;
		
		
		
		
		

		
		
		
		
		
	}
	
	
	public void transfo(double qt,boolean ori, String trans,Feve f){//qt est la quantité de CHOCOLAT voulue
		//Vérifie quel type de transformation
		//Vérifie la capacité bancaire
		//Vérifie le stock de fèves
		//vérifie capacité de production
		//Baisse le stock de fèves de qt
		//Augmente le stock de chocolat de rdt*qt (et modifie la qualité si longue)
		//Ajoute ou non l'originalité
		//Débite le compte bancaire
		Gamme g=f.getGamme();
		double s=0;
		if(ori) {//indicateur d'originalité
			s=1;
		}
		
		if (trans.equals("longue")){//dans le cas d'une transformation longue
				if(Filiere.LA_FILIERE.getBanque().verifierCapacitePaiement(this, this.cryptogramme, (1/rdt)*qt*(prix_transfo+prix_ori*s))) {//(1/rdt)*qt est la quantité de fève nécessaire pour obtenir qt de chocolat
					if(super.getStockfeve().getStocktotal()>(1/rdt)*qt) {//s'il y a assez de fèves
						if(NewCap>=qt) {//assez de capacité de stockage
							NewCap-=(1/rdt)*qt;//mise à jour de la capacité de production
							this.getStockfeve().enlever(f,(1/rdt)*qt);//baisse le stock de feves	
							this.getStockchocolatdemarque().ajouter(this.fevechocoplus(f), (1/rdt)*qt);//augmente le stock de chocolat de marque
							Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), (1/rdt)*qt*(prix_transfo+s*prix_ori*s));//paye
							this.journal.ajouter("Transformation longue de " +(1/rdt)*qt+" kg de "+f+" en "+qt+"kg de"+this.fevechoco(f).toString()+ " pour "+(1/rdt)*qt*(prix_transfo+prix_ori*s)+"€");
						}
					}
				}
		}
		if (trans.equals("courte")) {
			if(Filiere.LA_FILIERE.getBanque().verifierCapacitePaiement(this, this.cryptogramme, qt*(prix_transfo+prix_ori*s))) {//qt est la quantité de fève nécessaire pour obtenir qt de chocolat
				if(this.getStockfeve().getQuantite(f)>qt) {//s'il y a assez de fèves
					if(NewCap>=qt) {//assez de capacité de production
						NewCap-=qt;//mise à jour de la capacité de production
						this.getStockfeve().enlever(f,qt);//baisse le stock de feves
						this.getStockchocolatdemarque().ajouter(this.fevechoco(f), qt);//augmente le stock de chocolat
						this.journal.ajouter("Transformation Courte de " +qt+" kg de "+f+" en "+this.fevechoco(f).toString()+ " pour "+qt*(prix_transfo+prix_ori*s)+"€");
						Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), qt*(prix_transfo+s*prix_ori*s));//paye
					}
				}
			}
	}
		}
		
	
	//est utile pour les transfor courte : renvoie le chocolat de marque correspondant à la qualité des fèves
	public ChocolatDeMarque fevechoco(Feve f) {
		if(f.getGamme().equals(Gamme.BASSE)) {
			return new ChocolatDeMarque(Chocolat.BQ,this.getMarquesChocolat().get(0));
		} else if (f.getGamme().equals(Gamme.MOYENNE) && !f.isBioEquitable()){
			return new ChocolatDeMarque(Chocolat.MQ,super.getMarquesChocolat().get(1));
		}
		else if(f.getGamme().equals(Gamme.MOYENNE) && f.isBioEquitable()) {
			return new ChocolatDeMarque(Chocolat.MQ,super.getMarquesChocolat().get(2));
		}
		else if(f.getGamme().equals(Gamme.HAUTE) && !f.isBioEquitable()) {
			return new ChocolatDeMarque(Chocolat.MQ,super.getMarquesChocolat().get(3));
		}
		else if(f.getGamme().equals(Gamme.HAUTE) && f.isBioEquitable()) {
			return new ChocolatDeMarque(Chocolat.MQ,super.getMarquesChocolat().get(4));
		}
		return null;
	}
	
	
	//est utile pour la transfo longue : renvoie le chocolat de marque de qualité supérieur à la fève
	public ChocolatDeMarque fevechocoplus(Feve f) {
		if(f.getGamme().equals(Gamme.BASSE)) {
			return new ChocolatDeMarque(Chocolat.MQ,super.getMarquesChocolat().get(1));
		} else if (f.getGamme().equals(Gamme.MOYENNE) && !f.isBioEquitable()){
			return new ChocolatDeMarque(Chocolat.MQ,super.getMarquesChocolat().get(3));			
		}
		else if (f.getGamme().equals(Gamme.MOYENNE) && f.isBioEquitable()) {
			return new ChocolatDeMarque(Chocolat.MQ,super.getMarquesChocolat().get(4));
		}
		return null;
	}
		
		
}
	


	
	


