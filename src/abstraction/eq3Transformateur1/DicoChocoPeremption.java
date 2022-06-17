package abstraction.eq3Transformateur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Chocolat;
/*création d'une hashmap avec type de choclat, liste de lots (lot = quantité, date de péremption), auteur : anna*/
public class DicoChocoPeremption extends HashMap<Chocolat, ArrayList<Lot> >{

	
	public DicoChocoPeremption() {
		super();
		
		for (Chocolat c : Chocolat.values()) {
			this.put(c,  new ArrayList<Lot>() );//listelots);
			System.out.println(c+" <<<<<");
		}
	}
	
	// ajouter un lot juste après la transfo ; Julien
	public void ajoutLot(Chocolat c, Lot lot) {
		ArrayList<Lot> liste;//= new ArrayList<Lot>() ;
		liste = this.get(c);
		liste.add(lot);
		this.put(c, liste);
		System.out.println("++++++"+c+" "+lot.getQuantite());
	}
	
	// supprimer le chocolat des ventes ; Julien
	public void venteLot(Chocolat c, double nbre) {
		ArrayList<Lot> liste;//= new ArrayList<Lot>() ;
		liste = this.get(c);
		while (nbre>0.) {
			if (liste.size()==0) System.out.println(" >>>>"+c+" "+nbre);
			if (liste.get(0).getQuantite()>nbre) {
				double res=liste.get(0).getQuantite()-nbre;
				liste.get(0).setQuantite(res);
			//	this.put(c, liste);	
				nbre = 0.;
			} else { 
				nbre = nbre-liste.get(0).getQuantite();
				liste.remove(0);
			//	this.put(c, liste);
			}			
		}		
	}
	// si la date de préremption est dépassée , supprime le lot du stock , auteur : anna
	public void supprimeLot(int etape, DicoChoco stockchoco) {
		for (Chocolat c : Chocolat.values()) {
			ArrayList<Lot> liste= new ArrayList<Lot>() ;
			liste = this.get(c); 
			for (int i = 0; i < liste.size(); i++) {
				if (Filiere.LA_FILIERE.getIndicateur("dureePeremption").getValeur()<=  Filiere.LA_FILIERE.getEtape()-liste.get(i).getDate()) {
					liste.remove(i);
					stockchoco.put(c, stockchoco.get(c)-liste.get(i).getQuantite());
			}
		}
			this.put(c,liste);
	}
	}
	public double getTotal(Chocolat c) {
		double sommequantite=0.;
		ArrayList<Lot> liste= new ArrayList<Lot>() ;
		liste = this.get(c); 
		for (int i = 0; i < liste.size(); i++) {
			sommequantite+=liste.get(i).getQuantite();
		}
		return sommequantite;
		
	}
}

