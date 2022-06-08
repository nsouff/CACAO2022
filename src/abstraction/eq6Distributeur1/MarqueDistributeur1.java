package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.filiere.IFabricantChocolatDeMarque;
import abstraction.eq8Romu.filiere.IMarqueChocolat;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class MarqueDistributeur1 extends Distributeur1Acteur implements IMarqueChocolat, IFabricantChocolatDeMarque {

    @Override
    public List<String> getMarquesChocolat() {
        List<String> marques = new ArrayList<String>();
        marques.add("FourAll Choc");
        return marques;
    }

    @Override
    public List<ChocolatDeMarque> getChocolatsProduits() {
        List<ChocolatDeMarque> chocProduits = new ArrayList<ChocolatDeMarque>();
        chocProduits.add(new ChocolatDeMarque(Chocolat.BQ, "FourAll Choc"));
        chocProduits.add(new ChocolatDeMarque(Chocolat.BQ_O, "FourAll Choc"));
        return chocProduits;
    }
    
}
