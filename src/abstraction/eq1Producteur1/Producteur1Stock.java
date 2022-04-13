package abstraction.eq1Producteur1;
import java.util.concurrent.ThreadLocalRandom;

public class Producteur1Stock extends Producteur1Feve {
	private int nombreFeves;

	
	
	public Producteur1Stock(int age, String type, boolean perime) {
		super(age, type, perime);
		
		
		// TODO Auto-generated constructor stub
	}

	public void DevientMalade() {
		int probaMalade;
		int min_val = 1;
        int max_val = 100;
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
		probaMalade =tlr.nextInt(min_val, max_val + 1);
		if (probaMalade<15) {
			int ForceMaladie;
			ThreadLocalRandom tlr2 = ThreadLocalRandom.current();
			ForceMaladie =tlr2.nextInt(min_val, max_val + 1);
			if (ForceMaladie<70) {
				System.out.println("-10% du stock");
			}
			else if (ForceMaladie<90) {
				System.out.println("-50% du stock");
			}
			else {
				System.out.println("-80% du stock");
			}
		} else {
			System.out.println("pas malade");
		}
	}
}
