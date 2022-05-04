package abstraction.eq7Distributeur2;

import java.util.List;

import abstraction.eq8Romu.filiere.IFabricantChocolatDeMarque;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Distributeur2 extends Distributeur2Acteur implements IFabricantChocolatDeMarque {
	
	public Distributeur2(List<ChocolatDeMarque> chocos, IStock stock) {
		super(chocos);
	}

	@Override
	public List<ChocolatDeMarque> getChocolatsProduits() {
		return super.chocolats;
	}
}
