import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Joueur {
	final Plateau p; //Plateau with which player will interact 
	static final int nbRessourcesVoleur=7;
	static LinkedList<Character> colors=new LinkedList<Character>(Arrays.asList('w','r','o','b')); //Used for randomization of colors
	final boolean ia;
	String nom;
	final char couleur;
	Ressource ressources=new Ressource(0,0,0,0,0);
	int nbColonie=5; //Number of colonies player can build; to decrement when building one and value to check before building
	int nbVille=4;
	int nbRoute=15;
	LinkedList<Developpement> cartes=new LinkedList<Developpement>();
	/*Commenté car erreurs provoquées par inexistence des types
	LinkedList<Colonie> colonies= new LinkedList<Colonie>();
	LinkedList<Port> ports=new LinkedList<Port>();
	*/
	
	public Joueur(boolean ia, String nom, char couleur, Plateau p) { //Not actually used? Left for convinience, testing...
		this.ia=ia;
		this.nom=nom;
		this.couleur=couleur;
		this.p=p;
	}
	
	//Constructors
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
		if (cartes.size()==0) System.out.println("Vous n'avez pas de carte développement.");
		else {
			System.out.println("Voici les cartes développement que vous avez en main :");
			int i=1;
			for (Developpement c:cartes) {
				System.out.println(i++ +" : "+c);
			}
		}
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
	
	//Player choices methods
	
	//public void construireColonie() {
	//public void construireVille() {
	//public void construireRoute() {
	
	public void piocher() {
		/**piocher: Method called when player wants to draw a card
		 * Entry: none
		 * Return: void
		 * Action: Gives card in exchange for resources if player can afford card and there is cards left in heap; display error message otherwise
		 */
		if (!ressources.payerDev()) System.out.println("Vous n'avez pas les ressources nécessaires pour acheter une carte !"); //Doesn't have resources, doesn't pay, doesn't get card
		else { 
			Developpement c=p.piocher(this);
			if (c==null) System.out.println("Il n'y a plus de cartes dans la pioche ! Vous ne pouvez pas piocher. Vous n'avez donc payé aucune ressource.");
			else {
				ressources.payerDev(); //Always true, tested above
				System.out.println("Vous avez pioché la carte suivante ; \n"+c.toString()
				+"\nLa carte a été ajoutée à votre main. Vous ne pouvez pas la poser à ce tour.");
				cartes.add(c);				
			}
		}
	}
	//Player choices methods - end
	
	//Voleur methods
	public void volRessources() {
		//Called if die is a 7
		//Serait mieux dans Jouer, ou plateau ? aaaaaah je suis si perdue
		if (ressources.nbRessources()>nbRessourcesVoleur) {
			int nbCards=ressources.nbRessources()/2; //Number of cards to discard ; to the advantage of the player as specified in rules
			Ressource defaussees=new Ressource();
			if (ia) volIA(nbCards, defaussees); else volHumain(nbCards, defaussees);
			
		}
	}
	
	public void volHumain(int nbCards, Ressource defaussees) {
		//Make player choose which cards to remove
		Scanner sc=new Scanner(System.in);
		System.out.println("Vous devez défausser "+nbCards+" ressources.\n"+
				"Vous avez "+ressources+".\n"+
				"Quelles ressources voulez-vous défausser ?");
		//Scanner pour rentrer le nom des ressources qu'on défausse
		//boucle nbCards fois, on demande une entrée de type textuelle "bois", "argile","mouton", "ble", "roche"
		//Switch sur la valeur entrée, décrémente le compteur associée de la ressource (avec DiscardX()
		//on retourne la ressource
		
	}
	public void volIA(int nbCards, Ressource defaussees) {
		
	}
	//Voleur methods - end
}
