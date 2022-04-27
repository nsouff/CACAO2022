package abstraction.eq2Producteur2;

import java.util.Random;

public class AleaClimatique {
	public int start = 8;
	private int duree;
	
	public AleaClimatique(int start, int duree) {
		this.start = start;
		this.duree = duree;
	}
	
	public void SetDuree() {
		Random random = new Random() ;
		this.duree=(random.nextInt(2)+2);
	}
	public int getDuree() {
		return this.duree;
	}

	public int getStart() {
		return start;
	}
	
}
