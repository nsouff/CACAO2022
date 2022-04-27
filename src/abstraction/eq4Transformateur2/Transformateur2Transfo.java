package abstraction.eq4Transformateur2;

import abstraction.eq8Romu.filiere.Filiere;
//auteur Jad
public class Transformateur2Transfo extends Transformateur2Vente {
	
	//private double rdt=Filiere.LA_FILIERE.getIndicateur("rendement_long").getValue();
	//private double prix_transfo= Filiere.LA_FILIERE.getIndicateur("prix_transfo").getValue();
	
	
	
	public void GetStock(String s) {
		if(s.equals("Chocolat")) {
			//return(stock.getChoco())
		}
		else if (s.equals("feves")) {
			//return(stock.getFeves())	
		}
		else {
			//Raise erreur
		}
		
	}
	
	public void GetCommandes() {
		int current =Filiere.LA_FILIERE.getEtape();
		int previous =Filiere.LA_FILIERE.getEtape()-1;
		int comming =Filiere.LA_FILIERE.getEtape()+1;
		//List commandes=new List<()>;
		//List HistoCommandes="".getCommandes();
		//for (javatuples c in commandes){
		//if(c.getValue3()){
		//commandes.add(c);
		//}
		//}
		//return(commandes)
		
		
		
	}
	
	public void bestCombi() {
	//trouve la meilleur combinaison (qui minimise les coûts et si possible a une stratégie)
	//de transformation (types de fèves et de tranfos)
	//pour honorer les commandes
		
	}
	
	
	public void transfoLongue(int qt,boolean ori){
		//Vérifie la capacité bancaire
		//Vérifie le stock de fèves
		//Baisse le stock de fèves de qt
		//Augmente le stock de chocolat de rdt*qt
		//Ajoute ou non l'originalité
		//Débite le compte bancaire
		
		

		
		
	}
	
	public void transfoCourte(int qt,boolean ori) {
		//Vérifie la capacité bancaire
		//Vérifie le stock de fèves
		//Baisse le stock de fèves de qt
		//Augmente le stock de chocolat de qt
		//Ajoute ou non l'originalité
		//Débite le compte bancaire
		
	}
	

	
	

}
