package abstraction.eq7Distributeur2;

import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.ExempleTransformateurContratCadreVendeurAcheteur;
import abstraction.eq8Romu.filiere.IFabricantChocolatDeMarque;
import abstraction.eq8Romu.filiere.IMarqueChocolat;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class ExempleTransfo extends ExempleTransformateurContratCadreVendeurAcheteur implements IMarqueChocolat,IFabricantChocolatDeMarque{
		
		protected Object produit;

		public ExempleTransfo(Object produit) {
			super(produit);
			// TODO Auto-generated constructor stub
		}
		public List<ChocolatDeMarque> getChocolatsProduits() {
			// TODO Auto-generated method stub
			List<ChocolatDeMarque> marque = new ArrayList<ChocolatDeMarque>();
			marque.add(new ChocolatDeMarque(Chocolat.HQ_BE_O,"Biofour"));
			return marque;
		}
		public boolean vend(Object produit) {
			return true;
		}
		@Override
		public List<String> getMarquesChocolat() {
			List<String> marque = new ArrayList<String>();
			marque.add("Biofour");
			return marque;
		}
}
