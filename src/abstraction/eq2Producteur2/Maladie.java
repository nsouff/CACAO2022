package abstraction.eq2Producteur2;

public class Maladie {
	private int NiveauMaladie;
	private boolean IsMalade;
	
	public int getNiveauMaladie() {
		return NiveauMaladie;
	}
	public Maladie() {
		double r = Math.random();
		double i = Math.random();
		if (r<=0.45)  {
			this.NiveauMaladie = 1;
		}
		if (r>0.45 && r<=0.70)  {
			this.NiveauMaladie = 2;
		}
		if (r>0.70 && r<=0.85)  {
			this.NiveauMaladie = 3;
		}
		if (r>0.85 && r<=0.95)  {
			this.NiveauMaladie = 4;
		}
		if (r>0.95)  {
			this.NiveauMaladie = 5;
		}
		if (i<=0.03) {
			this.IsMalade=true;
		}
		if (i>0.03) {
			this.IsMalade=false;
		}
			
		

	
	}
}
