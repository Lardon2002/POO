import java.util.LinkedList;
import java.util.Random;

public class Plateau {
	final Tuile[][] tuiles;
	LinkedList<Developpement> cartes= new LinkedList<Developpement>();
	//Rajouter les maisons ?	
	//Rajouter les routes ?
	public Plateau(int lon, int larg, Tuile ...args) {
		tuiles=new Tuile[lon][larg];
		for (Tuile t:args) {
			//TODO?
			//1er parcours pour déterminer les tailles des tableaux
			//2e parcours pour les remplir ?
			//Pour l'instant on part sur un principe de tableau à taille fixe, mentionnée
			if (t!=null) {
				tuiles[t.getX()][t.getY()]=t;
			}
		}
		// Order of cards in list does not matter because of the way the cards are selected and returned
		for (int i=0; i<14; i++) { cartes.add(new Developpement('c')); } //Add the 14 chevalier cards
		for (int i=0; i<5; i++) { cartes.add(new Developpement('p')); } //Add the 5 point cards
		for (int i=0; i<2; i++) { cartes.add(new Developpement('m')); } //Add the 2 mononpole cards
		for (int i=0; i<2; i++) { cartes.add(new Developpement('r')); } //Add the 2 route cards
		for (int i=0; i<2; i++) { cartes.add(new Developpement('i')); } //Add the 2 invention cards
	}
	public Plateau(Tuile ... args) {
		this(4,4,args);
	}
	public Plateau() {
		//Constructeur du plateau de base
		//Définir ici toutes les tuiles, puis appeler le constructeur Plateau 2
		this(new Tuile(0,0,6,'b'),new Tuile(0,1,10,'m'),new Tuile(0,2,11,'f'),new Tuile(0,3,8,'m'),
				new Tuile(1,0,4,'f'),new Tuile(1,1,9,'a'),new Tuile(1,2,5,'b'),new Tuile(1,3,12,'r'),
				new Tuile(2,0,3,'r'),new Tuile(2,1,7,'d'),new Tuile(2,2,10,'f'),new Tuile(2,3,6,'a'),
				new Tuile(3,0,9,'a'),new Tuile(3,1,8,'r'),new Tuile(3,2,5,'m'),new Tuile(3,3,2,'b')); 
	}
	//Getters
	public Tuile getTuile(int x, int y) {
		/** return Tuile in plateau's specified position */
		try {
			return tuiles[x][y];
		} catch (NullPointerException e) {
			System.out.println("Cette tuile n'existe pas..."); return null;
		}
	}
	public boolean isEmptyDev() /** return true if there is no Developpement cards left */ {return cartes.size()==0;}
	public int nbDevCards() /** return number of Developpement cards */ {return cartes.size();}
	
	//Object methods override
	//equals and hashcode not relevant to change because 2 Plateau with the same Tuiles are still 2 distinct instances of game
	
	public String toString() {
		//TODO rajouter l'affichage des maisons
		String res="";
		for (int i=0; i<tuiles.length; i++) {
			for (int j=0; j<tuiles[i].length; j++) {
				if (tuiles[i][j]==null) res+="      ";
				else res+=tuiles[i][j].toString();
			}
			res+="\n";
		}
		return res;
	}
	
	//Relative to Plateau player turn methods
	public Developpement piocher(Joueur j) {
		/**Gives a random Developpement card to player j
		 * Entry: Joueur j who wants the card
		 * return: Developpement card		 * 
		 */
		double nb=Math.random()*cartes.size();
		int index=(int) nb;
		if (cartes.size()==0) return null;
		return cartes.remove(index);
	}

	public void donnerRessourcesJoueur() {
		//TODO with Noemie's method
	}
	
	public void donnerRessources(int de) {
		if (de==7) return; //Case of Voleur, this does not applyff
		for (int i=0; i<tuiles.length; i++) {
			for (int j=0; j<tuiles[i].length; j++) {
				Tuile t=tuiles [i][j];
				if (t.valeurDe==de) {
					Ressource ress=new Ressource(t.getRessource());
					//We check the 4 possible spots for maison on this tuile
					//PB sur optimisation long terme : comment gérer un nombre potentiellement infini de positions pour maisons ?
					//Créer un type enum qui sera composé de toutes les maisons sur cette case ?
					//Vérifier comment fonctionne enum
					try {
						donnerRessourcesJoueur(); //TODO!
					} catch (NullPointerException e) {
						System.out.println("Une erreur est survenue, les ressources n'ont pas été distribuées à tous :(");
					}
				}
			}
		}
	}
}	

