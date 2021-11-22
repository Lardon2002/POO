import java.util.Scanner;

/* NOTES RANDOM:
 * Créer une classe pour le voleur ? Avec ref vers la case sur laquelle il est, méthodes pour le bouger...
 * TODO Commentaires explicatifs pour toutes les méthodes
 */
public class Jouer {
	Plateau p;
	Joueur[] j;

	public Jouer(int nbJoueur, String ... names ) {
		//Plateau
		//Later on we could try to randomize plateau or at least let player configure it
		//Random idea: let player make their plateau Mario Maker style?
		p=new Plateau();
		
		//Joueur[]
		if (nbJoueur!=3 && nbJoueur!=4) System.out.println("Impossible de créer une partie dans ces conditions !");
		j=new Joueur[nbJoueur];
		int current=0; //Player that is being created
		do {
			double cas=Math.random()*nbJoueur;
			int index=(int) cas;
			if (j[index]==null) {
				if (current<names.length) { //We didn't create all humans yet
					j[index]=new Joueur(names[current],p);
				} else { //We did create all humans and are completing with AI
					j[index]= new Joueur(p);					
				}
				current++;
				
			}
		} while (current<nbJoueur); //If current>=nbJoueur, it means we are trying to create more joueur than needed : we're done
	}
	
	//Game initialization methods  -- THESE ARE TESTED AND WORK (they work great, btw. I love them. They are my babies, my pride)
	public static int nbPlayers() { 
		/**Gets, from person launching game (Scanner), how many players there will be
		 * entry: none / return: number of players in total (int)
		 */
		Scanner sc=new Scanner(System.in);
		int nbJoueur;
		do { //Do-while makes sure the game will not be broken by it having too many or too few players
			System.out.print("Voulez-vous jouer à 3 ou 4 joueurs? ");
			String entree=sc.next();
			try {
				nbJoueur=Integer.parseInt(entree);
				if (nbJoueur!=3 && nbJoueur!=4) System.out.println("\nCe nombre n'est pas valable ! Merci d'entrer 3 ou 4.");
			} catch (NumberFormatException e) { //Prevents breaking the game if not careful
				System.out.println ("Merci d'entrer un entier (\"3\" ou \"4\") !\n");
				nbJoueur=0;
			}
		} while (nbJoueur!=3 && nbJoueur!=4);
		return nbJoueur;
	}
	
	public static int nbHumans(int nbJoueur) { 
		/**Gets, from person launching game, how many humans will play
		 * entry: none / return: number of controlled players/humans (int)
		 */
		Scanner sc2=new Scanner(System.in);
		int nbHumain;
		do { //Do-while makes sure the game will not be broken by it having too many or too few players (relative to number of players specified)
			System.out.print("Combien d'humains jouent (les joueurs manquants seront des ordinateurs)? ");
			String entree=sc2.next();
			try {
				nbHumain=Integer.parseInt(entree);
				if (nbHumain>nbJoueur || nbHumain<0) System.out.println("\nCe nombre n'est pas valable ! Merci d'entrer une valeur positive, mais inférieure ou égale au nombre total de joueurs.");
			} catch (NumberFormatException e) {
				System.out.println ("Merci d'entrer un entier !\n");
				nbHumain=-1;
			}
		} while (nbHumain>nbJoueur || nbHumain<0);
		return nbHumain;
	}
	
	public static String[] noms (int nbHumain) { 
		/**Gets names of humans playing. Asks as many times as there is humans
		 * entry: number of names to ask for / return: string array containing names of players
		 * For each player, it will ask for confirmation of the name (useful in case of typo!)
		 */
		Scanner sc3=new Scanner(System.in);
		String[] noms=new String[nbHumain];
		for (int i=0; i<nbHumain; i++) {
			boolean ok=true; String nom;
			do {
				System.out.print("Joueur "+(i+1)+", entrez votre nom : ");
				nom=sc3.nextLine();
				ok=true;
				System.out.print("Vous avez choisi le nom "+nom+". Cela vous convient-il ? (y/n) ");
				String valid=sc3.nextLine();
				if (valid.toLowerCase().contentEquals("non") || valid.toLowerCase().contentEquals("n") || valid.toLowerCase().contentEquals("no")) {
					ok=false;
				}
				
			} while (!ok);
			noms[i]=nom;
		}
		return noms;
	}
		
	//Game play method
	public static void play() {
		//TODO and to think about	
	}
	
	public static Jouer config() {
		/** Initialization of game method
		 * Entry: none, return: Jouer jeu (an instance of a game)
		 * Asks for number of players, number of humans
		 * Asks for the name of all humans
		 * Creates the humans as specified, and completes with AI
		 * Colors and play order are randomized
		 */
		int nbJoueur=nbPlayers();
		System.out.println();
		int nbHumain=nbHumans(nbJoueur);
		System.out.println();
		String[] noms=noms(nbHumain);
		Jouer jeu= new Jouer(nbJoueur, noms);
		return jeu;
	}
	//This is the class that will launch the game and has the main.
	//Its purpose is to display the parameters of the game, then create an instance of Jouer that will run the game.
	public static void main (String ... args) {
		Jouer a=config();
		//Here, launch the game via method "jeu" or idk
		//This is a placeholder/test display (it works great!)
		System.out.println("Plateau de jeu :\n"+a.p+"\nJoueurs :");
		for (Joueur i:a.j) {
			System.out.println(i+"\n");
		}
	}
}
