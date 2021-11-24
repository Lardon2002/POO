import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;
import java.util.Collection;

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
		
		//routes maisons liées entre elles après l'initialisation du plateau 
		this.routesMaisonsInit();

	
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
	
	//fonction pour initialiser les routes reliées entre elles, utilisée dans le constructeur 
	private void routesMaisonsInit(){
		Cellule tmp; //utilisé pour faire pointer deux cases de deux tableaus différents
		//sur le même objet
		
		for(int i=0; i<tuiles.length; i++) {
			for(int l=0; l<tuiles[i].length; l++) {
				
				//route 0
				pointeVersMeme(tuiles[i][l],i,l-1,true,0,2);
				//ne rien faire si il attrappe une exception ArrayOIndexOutOfBorders
					//puisqu'effectivement, c'est normal, faire en sorte que le code continue normalement
				/*
				 *       _ _
				 * i,l-1|_|_|i,l On fait pointer la route sur le côté gauche de i,l sur 
				 * la route du côté droit de i,l-1
				 */
				
				//route 1
				pointeVersMeme(tuiles[i][l],i-1,l-1,true,1,3);
				//route 2
				pointeVersMeme(tuiles[i][l],i,l+1,true,2,0);
				//route 3
				pointeVersMeme(tuiles[i][l],i+1,l,true,3,1);
				
				if(i%2==0) { //on applique le même pointeur pour les maisons uniquement 
					//pour une ligne sur deux 
					//cela suffit à ce que toutes les maisons soient les mêmes
					//maison 0
					pointeVersMeme(tuiles[i][l],i,l-1,false,0,3);
					pointeVersMeme(tuiles[i][l],i+1,l,false,0,1);
					pointeVersMeme(tuiles[i][l],i+1,l-1,false,0,2);
					/*
					 *          _ _
					 * i,l-1   |_|_| i,l
					 * i+1,l-1 |_|_| i+1,l
					 * On fait pointer la maison situé au milieu des cases sur chaque coin des cases
					 */
		
					//maison 1
					pointeVersMeme(tuiles[i][l],i-1,l-1,false,1,0);
					pointeVersMeme(tuiles[i][l],i-1,l-1,false,1,3);
					pointeVersMeme(tuiles[i][l],i,l-1,false,1,2);
				
					//maison 2
					pointeVersMeme(tuiles[i][l],i-1,l-1,false,2,3);
					pointeVersMeme(tuiles[i][l],i-1,l+1,false,2,0);
					pointeVersMeme(tuiles[i][l],i,l+1,false,2,1);
					
					//maison 3
					pointeVersMeme(tuiles[i][l],i,l+1,false,3,0);
					pointeVersMeme(tuiles[i][l],i+1,l+1,false,3,1);
					
					pointeVersMeme(tuiles[i][l],i+1,l,false,3,2);
					
				}
				
				//VIVE LES ECLAIRS DE GENIE AUXX TOILETTES? CA MARCHE §!!!!
			
			}
		}
	}
	private void pointeVersMeme(Tuile t, int i, int l, boolean r, int nb1, int nb2) { //fonction servant à récupérer 
		//les erreurs sans arrêter le programme
		
		if(r) { //si on échange des routes
			try{
				this.tuiles[i][l].routes[nb2]=t.routes[nb1];
			}catch(Exception e) {
				//ne rien faire
			}
			
			
		}else {
			try {
				this.tuiles[i][l].maisons[nb2]=t.maisons[nb1];
				
				
			}catch(ArrayIndexOutOfBoundsException e) {
				//System.out.println("wesh");
			}
		}
		/*if(this.tuiles[0][1].maisons[3]!=this.tuiles[1][1].maisons[1]){
			System.out.println("ICI "+i+l+" "+nb1+" "+nb2+" "+t);
		}else {
			System.out.println("eq");
		}*/
		
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
		String lastLign="";
		String t="";
	
		for (int i=0; i<tuiles.length; i++) {
			for (int j=0; j<tuiles[i].length; j++) {
				
				
				if (tuiles[i][j]==null) t="     \n     \n     \n"; //tuile vide
				else t=tuiles[i][j].toString();
				//fusion des tuiles
				lastLign=fusionNouvelleTuile(lastLign,t);
				
				
				
			}
			//sert à supprimer la ligne du haut des tuiles, puisqu'elle sera identique à la dernière 
			//ligne du bas de res
			if(!res.isEmpty()) {
				Scanner sc=new Scanner(lastLign);
				//on sait que lastLign possède 3 lignes 
				sc.next(); //on jette la première ligne. 
				res+=sc.next()+"\n"; 
				res+=sc.next();
				sc.close();
			}else {
				res+=lastLign;
			}
			res+="\n";
			lastLign="";
		}
		
		return res;
	}
	
	private static String fusionNouvelleTuile(String s1, String s2) {
		if(s1.isEmpty()) { return s2; } //cas de la première tuile
		
		Scanner t1=new Scanner(s1).useDelimiter("\n"); //scanner des anciennes tuiles
		Scanner t2=new Scanner(s2).useDelimiter("\n"); //scanner de la nouvelle tuile
		String h="",mid="",b="",str="";
		String h2, mid2,b2;
		
		//découpe la ligne de tuiles en trois
			h=t1.next();
			mid=t1.next();
			b=t1.next();
	
		
		//divise en trois la dernière tuile, possédant forcément trois lignes
		h2=t2.next();
		mid2=t2.next();
		b2=t2.next();
		
		//on fusionne les côtés en même temps qu'on colle les tuiles
		h=h.substring(0,h.length()-1)+h2;
		mid=mid.substring(0,mid.length()-1)+mid2;
		b=b.substring(0,b.length()-1)+b2;
		
		str+=h+"\n"+mid+"\n"+b; //on fusionne la nouvelle ligne possédant la nouvelle tuile 
		//avec les précédentes lignes
		
		
		return str;
		
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
	
	
	//fonction pour attribuer ressources à la maison
	//exception à faire à faire  
	public void addColonie(int i, int j, int pos, Maison m) {
		try {
			m.typesGiven=allTypesOfPos(i,j,pos);//attribue types à la maison
			this.tuiles[i][j].maisons[pos].obj=m; //atribue la maison à sa cellule
		}catch(Exception e) {
			System.out.println("Je sais pas ce qui s'est passé, mais c'est normalemenet écrit au dessus.");
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
		}
	}
	private class PasDansPlateauException extends Exception{} //à utiliser plus tard mais là flemme
	private LinkedList<Character> allTypesOfPos(int i, int j, int pos){
		//cuz flemme, on assume que i j et pos toujours valides 
		LinkedList<Character> types=new LinkedList<Character>();
		types.add(this.tuiles[i][j].getRessource());
		
		switch(pos) {
		case 0 :
			types.add(this.giveType(i, j-1));
			types.add(this.giveType(i+1,j-1));
			types.add(this.giveType(i+1, j));
			break;
		case 1 : 
			types.add(this.giveType(i,j-1));
			types.add(this.giveType(i-1, j-1));
			types.add(this.giveType(i-1, j));
			break; 
		case 2 : 
			types.add(this.giveType(i, j+1));
			types.add(this.giveType(i-1, j));
			types.add(this.giveType(i-1, j+1));
			break; 
		default : //pos=3 
			types.add(this.giveType(i,j+1)); 
			types.add(this.giveType(i+1, j+1));
			types.add(this.giveType(i+1, j)); 
		}
		
		return types;
		
	}
	//à faire erreur aussi mais flemme
	private Character giveType(int i, int j) {
		Tuile t; 
		try {
			t=this.tuiles[i][j];
		}catch(Exception e) {
			return '@';
		}
		return t.getRessource();
		
	}
}	

