package abstraction.eq3Transformateur1;
import java.util.ArrayList;
import java.util.List;




import abstraction.eq8Romu.produits.Gamme;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

import java.util.HashMap;
import abstraction.eq8Romu.produits.Feve;



public class Transformateur1 extends Transformateur1ContratCadreAcheteur {
	private static final double rendementHaute=1;                  /** rendement de la transformation haute à définir*/
	private static final double coutTransfo=1;                     /** rappel : seul le rendement varie entre la trasnforamtion haute et celle basse ; à remplacer par this.Filiere.LA_FILIERE.getIndicateurs(coutTransfo)*/
	private static final double coutTransfoOriginal=coutTransfo+1; /** somme de couTransfo et du supplément pour l'original*/
	private static final double coutStockage=4*1;                  /** coutStockageTransfo = 4*coutStockageProd */


	public Transformateur1() { 
		super();
	}

	/**
	 * @return the stockFeve
	 * Alexandre
	 */
	public HashMap<Feve, Double> getStockFeve() {
		return stockFeve;
	}
	/** getter dernierPrixVenteChoco*/
	public dernierPrixVenteChoco getDernierPrixVenteChoco() {
		return this.getDernierPrixVenteChoco();
	}

	/** détermine le prix d'achat max; pas de prise en compte du rendement auteur Julien  */
	public void prixMaxAchat() {		
			prixAchatFeve.put(Feve.FEVE_BASSE, Math.min(dernierPrixVenteChoco.getPrix("distributeur1", Chocolat.MQ), dernierPrixVenteChoco.getPrix("distributeur2", Chocolat.MQ)) - coutTransfo);	
			prixAchatFeve.put(Feve.FEVE_MOYENNE,Math.min(dernierPrixVenteChoco.getPrix("distributeur1", Chocolat.MQ), dernierPrixVenteChoco.getPrix("distributeur2", Chocolat.MQ)) - coutTransfo);
			prixAchatFeve.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE,Math.min(dernierPrixVenteChoco.getPrix("distributeur1", Chocolat.MQ_BE), dernierPrixVenteChoco.getPrix("distributeur2", Chocolat.MQ_BE)) - coutTransfo);
	}
	
	/** détermine la quantité de fèves à acheter; auteur Julien */
	public void determinationQuantiteAchat() {		
		quantiteAchatFeve.put(Feve.FEVE_BASSE,((quantiteDemandeeChoco.get(Chocolat.MQ)-stockChoco.get(Chocolat.MQ))/2)); 	
		quantiteAchatFeve.put(Feve.FEVE_MOYENNE,((quantiteDemandeeChoco.get(Chocolat.MQ)-stockChoco.get(Chocolat.MQ))/2));
		quantiteAchatFeve.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE,(quantiteDemandeeChoco.get(Chocolat.MQ_BE)-stockChoco.get(Chocolat.MQ_BE)));
	}
	
	/** _______________________________________________LOT TRANSFORMATION DES FEVES ____________________________________________________________*/
	
	/** détermine la quantité à transformer 
	 * Alexandre */ 
	public int transfoQt(int stockFeveQt) {
		return stockFeveQt ;
	}
	
	/** détermine le type de transformation 
	 * Alexandre */
	public String choixTypeTransfo(Gamme stockFeveType) {
		if (stockFeveType.equals(Gamme.BASSE)) {
			return "transfoHaute" ;
		} /** else if(stockFeveType.equals(Gamme.MOYENNE) // stockFeveType.equals(Gamme.MOYENNE)){
			
		} */
		return "transfoBasse";
	}
	
	/** calcule le coût de la transformation et la quantité de chocolat produite
	 * Alexandre */
	public ArrayList<Double> coutQuantiteTransfo(String typeTransfo, double quantiteFeve, boolean original) {
		ArrayList<Double> prixQuantite = new ArrayList<Double>();
		if (original) {
			prixQuantite.add(quantiteFeve*coutTransfoOriginal);
		} else {
			prixQuantite.add(quantiteFeve*coutTransfo);
		}
		if (typeTransfo.contentEquals("transfoHaute")) {
			prixQuantite.add(quantiteFeve*rendementHaute);
			return prixQuantite;
		}
		prixQuantite.add(quantiteFeve);
		return prixQuantite;
	}
	
	/** modifie les stocks suite à la transformation 
	 * (penser à ajouter l'exception si quantitefeve>quantiteStockFeve) 
	 * Alexandre */
	public void transfo(int quantiteFeveTransformee, Feve feve, boolean original) {
		for (Feve f : stockFeve.keySet()) {
			if (f == feve) {
				stockFeve.put(feve, stockFeve.get(feve)-quantiteFeveTransformee);
			}
		}
		ArrayList<Double> coutQuantiteTransfo = this.coutQuantiteTransfo(this.choixTypeTransfo(feve.getGamme()), quantiteFeveTransformee, original);
		for (Chocolat c : stockChoco.keySet()) {
			if (c.getGamme()==Gamme.MOYENNE) {
				if ( c.isBioEquitable()==feve.isBioEquitable() && c.isOriginal()==original ) {
					stockChoco.put(c, stockChoco.get(c)-coutQuantiteTransfo.get(1));
				}
			}
		}
	}
	
	
	
	/** _________________________________________________GESTION DES STOCKS______________________________________________________
	 *  pas de péremption en V1 */
	
	/** Calcule le coût de stockage pour le tour (à exécuter en fin de tour)
	 *  Alexandre */
	public double coutStockage() {
		double cout = 0.;
		for (Feve f : stockFeve.keySet()) {
			cout = cout + stockFeve.get(f)*coutTransfo;
		}
		for (Chocolat c : stockChoco.keySet()) {
			cout = cout + stockChoco.get(c)*coutStockage;
		}
		return cout;
	}
	
	/** _________________________________________________VENTE DE CHOCOLAT_______________________________________________________*/
	
	/** Détermine le prix de vente minimum
	 *  Alexandre*/
	public void prixVenteMin() {
		double prixFeve = Math.max(prixAchatFeve.get(Feve.FEVE_BASSE), prixAchatFeve.get(Feve.FEVE_MOYENNE));
		double prixFeveBio = prixAchatFeve.get(Feve.FEVE_MOYENNE_BIO_EQUITABLE);
		for (Chocolat c : Chocolat.values()) {
			prixVenteMin.put(c, null);
			if (c.getGamme() == Gamme.MOYENNE) {
				if (c.isBioEquitable()) {
					prixVenteMin.put(c, prixFeveBio+coutTransfo);
				} else if (c.isOriginal()) {
					prixVenteMin.put(c, prixFeve+coutTransfoOriginal);
				} else {
					prixVenteMin.put(c, prixFeve+coutTransfo);
				}
			}
		}
	}

}
