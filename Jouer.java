import java.util.LinkedList;
import java.util.Scanner;

public class Jouer {
	Plateau p;
	Joueur[] j; //Linked List ou tableau ?

	
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
	
	
	//Game initialization methods
	public static int nbPlayers() { //Gets from launcher of game, how many players there will be
		Scanner sc=new Scanner(System.in);
		int nbJoueur;
		do { //Do-while makes sure the game will not be broken by it having too many or too few players
			System.out.print("Voulez-vous jouer à 3 ou 4 joueurs? ");
			nbJoueur=Integer.parseInt(sc.next());
			if (nbJoueur!=3 && nbJoueur!=4) System.out.println("\nCe nombre n'est pas valable ! Merci d'entrer 3 ou 4.");
		} while (nbJoueur!=3 && nbJoueur!=4);
		return nbJoueur;
	}
	
	public static int nbHumans(int nbJoueur) { //Gets, from launcher of game, how many humans will play
		Scanner sc2=new Scanner(System.in);
		int nbHumain;
		do { //Do-while makes sure the game will not be broken by it having too many or too few players (relative to number of players specified)
			System.out.print("Combien d'humains jouent (les joueurs manquants seront des ordinateurs)? ");
			nbHumain=Integer.parseInt(sc2.next());
			if (nbHumain>nbJoueur || nbHumain<0) System.out.println("\nCe nombre n'est pas valable ! Merci d'entrer une valeur positive, mais inférieure ou égale au nombre total de joueurs.");
		} while (nbHumain>nbJoueur || nbHumain<0);
		return nbHumain;
	}
	
	public static String[] noms (int nbHumain) { //Gets names of humans playing. Asks as many times as there is humans
		Scanner sc3=new Scanner(System.in);
		String[] noms=new String[nbHumain];
		for (int i=0; i<nbHumain; i++) {
			System.out.print("Joueur "+(i+1)+", entrez votre nom : ");
			//Make player verify their name ?
			noms[i]=sc3.nextLine();
		}
		return noms;
	}
		
	//This is the class that will launch the game and has the main.
	//Its purpose is to display the parameters of the game, then create an instance of Jouer that will run the game.
	public static void main (String ... args) {
		int nbJoueur=nbPlayers();
		System.out.println();
		int nbHumain=nbHumans(nbJoueur);
		System.out.println();
		String[] noms=noms(nbHumain);
		
		//Here, launch the game via method "jeu" or idk
		//This is a placeholder/test display (it works great!)
		Jouer a= new Jouer(nbJoueur, noms);
		System.out.println("Plateau de jeu :\n"+a.p+"\nJoueurs :");
		for (Joueur i:a.j) {
			System.out.println(i+"\n");
		}
	}
}
