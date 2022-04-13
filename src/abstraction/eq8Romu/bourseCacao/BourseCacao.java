package abstraction.eq8Romu.bourseCacao;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.filiere.Banque;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.filiere.IAssermente;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.general.VariableReadOnly;
import abstraction.eq8Romu.produits.Feve;

public class BourseCacao implements IActeur, IAssermente {
	private HashMap<IActeur, Integer> cryptos;
	private Integer crypto;
	private HashMap<Feve, Journal> journal;
	private List<IVendeurBourse> vendeurs;
	private List<IAcheteurBourse> acheteurs;
	private HashMap<Feve, Variable> cours;
	private HashMap<IAcheteurBourse, Integer> blackList; //nombre de steps pendant lequel l'acheteur ne peut plus acheter (car a fait une demande qu'il n'a pas pu honorer)

	public BourseCacao() {
		this.journal = new HashMap<Feve, Journal>();
		this.cours=new HashMap<Feve, Variable>();
		// 1472 euros = 1600 dollars prix min du cours du cacao par tonne (1830 sur les 20 dernieres annees)
		// 2417 euros = 2627 dollars prix moy du cours du cacao par tonne de l'annee passee
		// 3221 euros = 3500 dollars prix max du cours du cacao par tonne (3321 sur les 20 dernieres annees)
		this.cours.put(Feve.FEVE_HAUTE_BIO_EQUITABLE, new VariableReadOnly(getNom()+" cours HBE", "<html>le cours actuel<br>de FEVE_HAUTE_BIO_EQUITABLE</html>", this, 1.772, 3.521, 2.717));  
		this.cours.put(Feve.FEVE_HAUTE, new VariableReadOnly(getNom()+" cours H", "<html>le cours actuel<br>de FEVE_HAUTE</html>", this, 1.672, 3.421, 2.617));  
		this.cours.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE, new VariableReadOnly(getNom()+" cours MBE", "<html>le cours actuel<br>de FEVE_MOYENNE_BIO_EQUITABLE</html>", this,1.572, 3.321, 2.517));  
		this.cours.put(Feve.FEVE_MOYENNE, new VariableReadOnly(getNom()+" cours M", "<html>le cours actuel<br>de FEVE_MOYENNE</html>", this,1.472, 3.221, 2.417)); 
		this.cours.put(Feve.FEVE_BASSE, new VariableReadOnly(getNom()+" cours B", "<html>le cours actuel<br>de FEVE_BASSE</html>", this,1.272, 3.021, 2.217));
		this.blackList=new HashMap<IAcheteurBourse, Integer>();
		for (Feve f : Feve.values()) {
			this.journal.put(f, new Journal("Journal "+this.getNom()+" "+f, this));
		}
	}

	public Variable getCours(Feve f) {
		if (f==null || !this.cours.keySet().contains(f)) {
			throw new IllegalArgumentException("Appel de getCours(f) de BourseCacao avec un parametre invalide ("+f+")");
		} else {
			return this.cours.get(f);
		}
	}
	public String getNom() {
		return "BourseCacao";
	}

	public String getDescription() {
		return this.getNom();
	}

	public Color getColor() {
		return new Color(96, 125, 139);
	}

	public List<String> getNomsFilieresProposees() {
		return new LinkedList<String>();
	}

	public Filiere getFiliere(String nom) {
		return null;
	}

	public List<Variable> getIndicateurs() {
		List<Variable> res = new LinkedList<Variable>();
		for (Feve f : Feve.values()) {
			res.add(this.cours.get(f));
		}
		return res;
	}

	public List<Variable> getParametres() {
		return new LinkedList<Variable>();
	}

	public List<Journal> getJournaux() {
		List<Journal> res = new LinkedList<Journal>();
		for (Feve f : Feve.values()) {
			res.add(this.journal.get(f));
		}
		return res;
	}

	public void setCryptogramme(Integer crypto) {
		this.crypto = crypto;
	}

	public void notificationFaillite(IActeur acteur) {
		if (acteur instanceof IVendeurBourse) {
			this.vendeurs.remove((IVendeurBourse)acteur);
		}
		if (acteur instanceof IAcheteurBourse) {
			this.acheteurs.remove((IAcheteurBourse)acteur);
		}
	}

	public void notificationOperationBancaire(double montant) {
	}

	public void setCryptos(HashMap<IActeur, Integer> cryptos) {
		if (this.cryptos==null) { // Les cryptogrammes ne sont indique qu'une fois par la banque : si la methode est appelee une seconde fois c'est que l'auteur de l'appel n'est pas la banque et qu'on cherche a "pirater" l'acteur
			this.cryptos= cryptos;
		}
	}

	public void initialiser() {
		this.vendeurs = new LinkedList<IVendeurBourse>();
		this.acheteurs = new LinkedList<IAcheteurBourse>();
		List<IActeur> acteurs = Filiere.LA_FILIERE.getActeurs();
		for (IActeur acteur : acteurs) {
			if (acteur instanceof IVendeurBourse) {
				this.vendeurs.add(((IVendeurBourse)acteur));
			}
			if (acteur instanceof IAcheteurBourse) {
				this.acheteurs.add(((IAcheteurBourse)acteur));
				this.blackList.put((IAcheteurBourse)acteur, 0);
			}
		}
	}

	public void next() {
		Banque banque = Filiere.LA_FILIERE.getBanque();
		for (Feve f : Feve.values()) {
			HashMap<IAcheteurBourse, Double> demandes=new HashMap<IAcheteurBourse, Double>();
			double totalDemandes=0;
			double cours = this.cours.get(f).getValeur();
			for (IAcheteurBourse acheteur : this.acheteurs) {
				if (blackList.get(acheteur)==0) {
					double demande = acheteur.demande(f, cours);
					journal.get(f).ajouter(Journal.texteColore((IActeur)acheteur, acheteur+" souhaite acheter "+Journal.doubleSur(demande, 2)+" kg de "+f));
					if (demande>0) {
						if (banque.verifierCapacitePaiement((IActeur)acheteur, cryptos.get((IActeur)acheteur), cours*demande)) {
							demandes.put(acheteur, demande);
							totalDemandes+=demande;
						} else {
							acheteur.notificationBlackList(6);
							blackList.put(acheteur,6);
						}
					}
				} else {
					blackList.put(acheteur,blackList.get(acheteur)-1);
				}
			}
			HashMap<IVendeurBourse, Double> offres=new HashMap<IVendeurBourse, Double>();
			double totalOffres=0;
			for (IVendeurBourse vendeur : this.vendeurs) {
				double offre = vendeur.offre(f, cours);
				journal.get(f).ajouter(Journal.texteColore((IActeur)vendeur, vendeur+" souhaite vendre  "+Journal.doubleSur(offre, 2)+" kg de "+f));
				if (offre>0) {
					offres.put(vendeur, offre);
					totalOffres+=offre;
				}
			}

			if (totalOffres>=totalDemandes && totalDemandes>0.0) { // Les acheteurs vont obtenir la quantite souhaitee et vendeurs vendre au prorata de l'offre qu'ils ont faite
				journal.get(f).ajouter("l'offre ("+Journal.doubleSur(totalOffres, 2)+") est superieure a la demande ("+Journal.doubleSur(totalDemandes, 2)+")");
				for (IAcheteurBourse a : demandes.keySet()){
					boolean virementOk = banque.virer((IActeur)a, cryptos.get((IActeur)a), this,cours*demandes.get(a));
					if (virementOk) {
						a.notificationAchat(f,demandes.get(a), cours);
						journal.get(f).ajouter(Journal.texteColore((IActeur)a, a+" obtient "+Journal.doubleSur(demandes.get(a),2)+" et paye "+Journal.doubleSur(cours*demandes.get(a), 2)));
					} else { // Normalement la transaction peut avoir lieu vu qu'on a verifie au prealable la capacite du vendeur a payer
						a.notificationBlackList(6);
						blackList.put(a,6);
					}
				}
				for (IVendeurBourse v : offres.keySet()){
					// La quantite vendue est au prorata de la quantite mis en vente
					double quantite = (totalDemandes*offres.get(v))/totalOffres; 
					banque.virer(this, crypto, (IActeur)v,cours*quantite);
					v.notificationVente(f, quantite,cours);
					journal.get(f).ajouter(Journal.texteColore((IActeur)v, v+" vend "+Journal.doubleSur(quantite, 2)+" et est paye "+Journal.doubleSur(cours*quantite, 2)));
				}
			} else if (totalOffres<=totalDemandes && totalOffres>0.0){ // offre<demande : Les vendeurs vont vendre tout ce qu'ils ont mis en vente et les acheteurs auront des feves au prorata de leur proposition d'achat
				journal.get(f).ajouter("la demande ("+Journal.doubleSur(totalDemandes, 2)+") est superieure a l'offre ("+Journal.doubleSur(totalOffres, 2)+")");
				for (IAcheteurBourse a : demandes.keySet()){
					// La quantite achetee est au prorata de la quantite demandee
					double quantite = (totalOffres*demandes.get(a))/totalDemandes; 
					if (cours*quantite>0) {
						boolean virementOk = banque.virer((IActeur)a, cryptos.get((IActeur)a), this,cours*quantite);
						if (virementOk) { // normalement c'est le cas vu qu'on a verifie auparavant
							a.notificationAchat(f,quantite, cours);
							journal.get(f).ajouter(Journal.texteColore((IActeur)a, a+" obtient "+Journal.doubleSur(quantite,2)+" et paye "+Journal.doubleSur(cours*quantite, 2)));
						} else {
							a.notificationBlackList(6);
							blackList.put(a,6);
						}
					}
				}
				for (IVendeurBourse v : offres.keySet()){
					// Chaque vendeur vend tout ce qu'il a annonce vouloir vendre
					double quantite = offres.get(v); 
					if (cours*quantite>0) {
						banque.virer(this, crypto, (IActeur)v,cours*quantite);
						v.notificationVente(f, quantite,cours);
						journal.get(f).ajouter(Journal.texteColore((IActeur)v, v+" vend "+Journal.doubleSur(quantite, 2)+" et est paye "+Journal.doubleSur(cours*quantite, 2)));
					}
				}
			}
			// Mise a jour du cours.
			if ( totalDemandes==0.0 && totalOffres==0) {
				// il ne se passe rien
			} else if (totalDemandes==0.0 && totalOffres>0.0) {
				double diminution = Math.random()*2.0;  // ca va diminuer entre 0 et 2%
				cours = cours * (1.0- (diminution/100.0));
				this.cours.get(f).setValeur(this, cours, crypto);
			} else if (totalDemandes>0.0 && totalOffres==0.0) {
				double augmentation = Math.random()*2.0;  // ca va augmenter entre 0 et 2%
				cours = cours * (1.0+ (augmentation/100.0));
				this.cours.get(f).setValeur(this, cours, crypto);
			} else if (totalDemandes>totalOffres) {// Le cours va monter
				double augmentation = Math.max(Math.random()*1.5, Math.min(12.5,  (totalDemandes-totalOffres)/totalOffres)); // plus l'ecart entre demande et offre est eleve, plus l'agumentation sera forte, mais pas plus de 25% d'augmentation, pas moins qu'un nombre au hasard tire entre 0 et 3.
				double max = this.cours.get(f).getMax();
				if (cours * (1.0+ (augmentation/100.0))>max) {
					augmentation=Math.min(augmentation, 1.0); // Si ca fait aller au dela du Max connu alors on augmente au plus de 1%
				}
				cours = cours * (1.0+ (augmentation/100.0));
				this.cours.get(f).setValeur(this, cours, crypto);
			} else { // le cours va baisser
				double diminution = Math.max(Math.random()*1.5, Math.min(12.5,  (totalOffres-totalDemandes)/totalDemandes)); // plus l'ecart entre demande et offre est eleve, plus la diminution sera forte, mais pas plus de 25% d'augmentation, pas moins qu'un nombre au hasard tire entre 0 et 3.
				double min = this.cours.get(f).getMin();
				if (cours * (1.0-(diminution/100.0))<min) {
					diminution=Math.min(diminution, 1.0); // Si ca fait aller en dessous du Min connu alors on diminue au plus de 1%
				}
				cours = cours * (1.0- (diminution/100.0));
				this.cours.get(f).setValeur(this, cours, crypto);
			}
			journal.get(f).ajouter("--> Le cours de la feve "+f+" passe a :"+Journal.doubleSur(cours, 4));
		}
	}


}
