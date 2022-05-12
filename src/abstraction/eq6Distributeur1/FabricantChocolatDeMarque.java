package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.filiere.IFabricantChocolatDeMarque;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class FabricantChocolatDeMarque extends Distributeur1Acteur implements IFabricantChocolatDeMarque {

    private ChocolatDeMarque chocoFourAll;

    public FabricantChocolatDeMarque() {
        super();
        chocoFourAll = new ChocolatDeMarque(Chocolat.BQ, "FourAll");
        NotreStock.addQte(chocoFourAll, 100);
        setPrixVente(chocoFourAll, 5);
    }
    
    @Override
    public List<ChocolatDeMarque> getChocolatsProduits() {
        List<ChocolatDeMarque> res = new ArrayList<ChocolatDeMarque>();
        res.add(chocoFourAll);
        return res;
    }

   
    
}
