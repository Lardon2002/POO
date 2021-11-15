import java.util.LinkedList;
import java.util.Random;

public class Plateau {
	final Tuile[][] tuiles;
	LinkedList<Developpement> cartes= new LinkedList<Developpement>();
	//Maisons à rajouter dans le plateau ? Si les mainsons sont assignées à des emplacements, puis rattachées à des cases.
	//Rajouter également les routes ?
	public Plateau(int lon, int larg, Tuile ...args) {
		tuiles=new Tuile[lon][larg];
		for (Tuile t:args) {
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
	//Should do a getter Tuile(x,y) ?
	//Getter of nb of dev cards is useless, but number/isEmpty could be useful (dunno where to use them but better to have them in case of need
	public boolean isEmptyDev() {return cartes.size()==0;}
	public int nbDevCards() {return cartes.size();}
	
	//Object methods override
	//equals and hashcode not relevant to change because 2 Plateau with the same Tuiles are still 2 distinct instances of game
	
	/* Comment afficher le plateau ?

	  mmm  w  bbb
	 m 8 m w b 2 b
	  mmm  w  bbb
	  	   -
	 wwww |w| wwww                //petite lettre pour une colonie, grande lettre pour une ville
	       -					//Sinon on eut aussi s'accorder pour un symbole pour chaque couleur
	  mmm  w  ddd				//Pour ne pas surcharger visuellement avec des lettres
	 m 4 m w d 7 d
	  mmm  w  ddd
	  
	  ___________________
	  
	|w| m8m w b8b   => On va faire ca car impossible d'imprimer sur plusieurs lignes
	 w  ww |w| ww  
	|w| f4f w d7d
	*/
	public String toString() {
		String res="";
		for (int i=0; i<tuiles.length; i++) {
			for (int j=0; j<tuiles[i].length; j++) {
				if (tuiles[i][j]==null) res+="     ";
				else res+=tuiles[i][j].toString();
			}
			res+="\n";
		}
		return res;
	}
	
	public Developpement piocher(Joueur j) {
		//Comment la lier au joueur ? Laquelle on appelle en premier?
		double nb=Math.random()*cartes.size();
		int index=(int) nb;
		if (cartes.size()==0) return null;
		return cartes.remove(index);
	}
	
	public int lancerDe() {
		Random r=new Random();
		int d1=r.nextInt(6)+1; //Number between 1 and 6 (0 and 5, +1)
		int d2=r.nextInt(6)+1;
		return d1+d2; //Necessary to generate 2 number for 2 dice, because of number probabilities
	}
	
	public void donnerRessources(int de) {
		//Donner les ressources selon le résultat du dé
		//Parcourir les cases du plateau, si la case vaut 1 on donne les ressources correspondantes à toutes les maisons qui sont dessus?
	}
}	

