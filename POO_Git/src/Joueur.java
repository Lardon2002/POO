import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Joueur {
	final Plateau p; //Plateau with which player will interact 
	static final int nbRessourcesVoleur=7;
	static LinkedList<Character> colors=new LinkedList<Character>(Arrays.asList('w','r','o','b')); //Used for randomization of colors
	final boolean ia;
	String nom;
	final char couleur;
	
	Ressource ressources=new Ressource(5,5,5,5,5);
	int nbColonie=5; //Number of colonies player can build; to decrement when building one and value to check before building
	int nbVille=4;
	int nbRoute=15;
	LinkedList<Developpement> cartes=new LinkedList<Developpement>();
	LinkedList<Developpement> posees=new LinkedList<Developpement>();

	
	LinkedList<Maison> colonies= new LinkedList<Maison>();
	LinkedList<Port> ports=new LinkedList<Port>();
	
	//Constructors
	public Joueur(boolean ia, String nom, char couleur, Plateau p) { //Not actually used? Left for convinience, testing...
		this.ia=ia;
		this.nom=nom;
		this.couleur=couleur;
		this.p=p;
	}
	
	public Joueur(String nom, Plateau p) {
		this.p=p;
		ia=false; this.nom=nom;
		double val2=Math.random()*colors.size();
		int val=(int) val2;
		if (!colors.isEmpty()) couleur =colors.remove(val);
		else couleur='d';
	}
	
	public Joueur(Plateau p) {
		this.p=p;
		ia=true;
		double val2=Math.random()*colors.size();
		int val=(int) val2;
		if (!colors.isEmpty()) couleur =colors.remove(val);
		else couleur='d';
		switch(couleur) { //Used when displaying person playing (should we display person's name or their color? Would opt for name)
		case 'b': nom="Bleu"; break;
		case 'w': nom="Blanc"; break;
		case 'o': nom="Orange"; break;
		case 'r': nom="Rouge"; break;
		default: nom="Indéfini"; break;
		}
	}
	//Constructors - end
	
	//Getters
	public void checkCards() {
		/** Display cards currently held by player. */
		if (cartes.size()==0) System.out.println("Vous n'avez pas de carte développement.");
		else {
			System.out.println("Voici les cartes développement que vous avez en main :");
			int i=1;
			for (Developpement c:cartes) {
				System.out.println(i++ +" : "+c);
			}
		}
	}
	
	public void afficheRessources() {
		/** Displays current Ressources of the player. */
		System.out.println("Vous avez "+ressources);
	}
	//Getters - end
	
	//Object methods override
	public String toString() {
		String ia=this.ia?"IA":nom;
		String color;
		switch(couleur) {
			case 'b': color="Bleu"; break;
			case 'w': color="Blanc"; break;
			case 'o': color="Orange"; break;
			case 'r': color="Rouge"; break;
			default: color="Indéfini"; break;
		}
		return (color+" ("+ia+")\n"
				+"Ressources : "+ ressources+
				"\nIl peut encore construire "+nbColonie+" colonies, "+nbVille+" villes et "+nbRoute+" routes.");
	}
	//Object methods override - end
	
	//Player choices - initialization round methods

	//Player choices methods
	public void tour() {
		/** Method called to make a player play its turn.
		 * Entry: none, return: none
		 * Calls other methods, which will allow player to do the wanted actions
		 * (launch dice, build, get or play card, and end turn) 
		 */
		System.out.println("AU TOUR DE "+nom.toUpperCase());
		//lancer les dés (1e action obligatoirement)
		//donner les ressources à tous les joueurs selon la position de leur maison
		//Proposer une liste d'action au joueur : construire route, colonie, ville, carte, poser cartes ou finir le tour.
		//TODO - force player to make lancer_de() their first action
		//TODO - methods for construire trucs
		
	}
	public void lancer_de() {
		/**This is the 1st method that will be called upon beginning a player turn
		 * It will allow player to launch dice and act accordingly (give resources or act Voleur)
		 */
		int diceValue;
		if (!ia) {
			System.out.println("Vous lancez les dés !"); //A gérer par clic plus tard
			diceValue=valeurDe();
			System.out.println("Vous avez fait "+diceValue+" !");
		} else {
			diceValue=valeurDe();
			System.out.println(nom+" a fait "+diceValue+".");
		}
		//TODO - répartir ressources ou appeler voleur
		//Si c'est pas 7 distribuer les ressources
		//Si c'est 7 appeler le voleur pour chaque joueur
	}
	
	public int valeurDe() {
		/**Generates random value of dice
		 * entry: none, return: total dice value		 * 
		 */
		Random r=new Random();
		int d1=r.nextInt(6)+1; //Number between 1 and 6 (0 and 5, +1)
		int d2=r.nextInt(6)+1;
		return d1+d2; //Necessary to generate 2 number for 2 dice, because of number probabilities
	}
	
	public void construireColonie() {
		//normalement demande au joueur où, mias la truc de base juste pour le fonctionnement
		try {
			Maison m=new Maison(this);
			//sur case 0 0 à position 0
			this.p.addColonie(0, 0, 0,m); //cette fonction rajoute les types à la colonie en 
			//fonction du plateau ET met la colonie sur le plateau 
		}catch(Exception e) {
			System.out.println("Vous n'avez pas pu construire de colonie.");
		}
	}
	//public void construireVille() {
	//public void construireRoute() {
	
	public void piocher() {
		/**piocher: Method called when player wants to draw a card
		 * Entry: none
		 * Return: void
		 * Action: Gives card in exchange for resources if player can afford card and there is cards left in heap; display error message otherwise
		 */
		if (ressources.getMouton()<1 && ressources.getBle()<1 && ressources.getRoche()<1) System.out.println("Vous n'avez pas les ressources nécessaires pour acheter une carte !"); //Doesn't have resources, doesn't pay, doesn't get card
		else { 
			Developpement c=p.piocher(this);
			if (c==null) System.out.println("Il n'y a plus de cartes dans la pioche ! Vous ne pouvez pas piocher. Vous n'avez donc payé aucune ressource.");
			else {
				ressources.payerDev(); //Always true, tested above
				System.out.println("Vous avez pioché la carte suivante : \n"+c.toString()
				+"\nLa carte a été ajoutée à votre main. Vous ne pouvez pas la poser à ce tour.");
				cartes.add(c);				
			}
		}
	}
	public void poserCarte() {
		if (ia) poserCarteIA(); else poserCarteHumain();
	}
	
	private void poserCarteIA() {
		// TODO poserCarteIA
	}

	public void poserCarteHumain() {
		Scanner sc=new Scanner(System.in);
		/* TODO poserCarteHumain
		 * Quand la méthode est appelée, afficher les cartes
		 * Demander quelle carte le joueur veut jouer
		 * Récupérer la carte (à l'aide d'une méthode getCarte qui parcourt une liste de Developpement ?)
		 * La retirer de cartes, l'ajouter à jouees
		 * vérifier qu'elle n'est pas tirée à ce tour (comment ? avec classe locale?)
		 * Appliquer son action (sera défini dans Developpement)
		 */
		checkCards();
		System.out.print("Quelle carte voulez-vous poser ? Indiquez le nom de la carte (la partie en majuscules) : ");
		String carte=sc.nextLine();
		switch (carte) {
		default: System.out.println("Cette carte n'existe pas :("); //+ demander à rappeler la méthode ?
		}
	}
	
	public void finirTour() {
		//TODO - Cette fonction dépend de comment est mis en place le tour. Elle sera donc définie un peu plus tard (voir bougée !) quand j'aurai défini dans Jouer le launcher, je pense.
	}
	//Player choices methods - end
	
	//Voleur methods
	public void volRessources() {
		/** Called on each player if total of dice is a 7
		 * Entry: none, return: none
		 * Will ask player to discard half their cards if they have more than 7
		 * This choice will be randomized for IA 
		 */
		if (ressources.nbRessources()>nbRessourcesVoleur) {
			int nbCards=ressources.nbRessources()/2; //Number of cards to discard ; to the advantage of the player as specified in rules
			if (ia) volIA(nbCards, defaussees); else volHumain(nbCards, defaussees);
		}
	}
	
	public void volHumain(int nbCards) {
		/**Called on each human player if total of dice is 7 and total of resources is above 7
		 * Entry: none, return: none
		 * Will ask player to discard half their cards if they have more than 7
		 * Humans choose such cards
		 */
		Scanner sc=new Scanner(System.in);
		while (nbCards>0) {
			boolean possible=true;
			System.out.print("Vous devez défausser encore "+nbCards+" ressources.\n"+
					"Vous avez "+ressources+".\n"+
					"Quelles ressources voulez-vous défausser ?\n"+
					"(entrez 'Bois', 'Argile', 'mouton','blé' ou 'roche')");
			String ress=sc.next().toLowerCase();
			System.out.println();
			switch (ress) {
			case "bois": possible=ressources.discard('b', 1); break;
			case "argile": possible=ressources.discard('a', 1); break;
			case "mouton": possible=ressources.discard('a', 1); break;
			case "blé": possible=ressources.discard('f', 1); break;
			case "foin": possible=ressources.discard('f', 1); break; 
			case "roche": possible=ressources.discard('r', 1); break;
			case "caillou": possible=ressources.discard('r', 1); break;
			default: possible=ressources.discard('z', 1); //Will return false anyway
			}
			if (possible) nbCards--; else System.out.println("Vous ne pouvez pas défausser ce type de ressource, vous n'en avez pas !");
		}		
	}
	public void volIA(int nbCards, Ressource defaussees) {
		/**Called on each AI player if total of dice is 7 and total of resources is above 7
		 * Entry: none, return: none
		 * For AI, discarded cards are randomized
		 */
		while (nbCards>0) {
			boolean possible=true;
			double rand=Math.random()*5;
			int type=(int)rand;
			switch (type) {
			case 0: possible=ressources.discard('b', 1); break;
			case 1: possible=ressources.discard('a', 1); break; 
			case 2: possible=ressources.discard('m', 1); break;
			case 3: possible=ressources.discard('f', 1); break;
			case 4: possible=ressources.discard('r', 1); break;
			default: possible=ressources.discard('z', 1); //Will return false anyway
			}
			if (possible) nbCards--;
		}
	}
	//Voleur methods - end
}
