
public class TestTuile {

	public static void main(String[] args) {
		Tuile t0=new Tuile (0,0,5, 'm');
		Tuile t1=new Tuile(0,1,9,'r');
		Tuile t2=new Tuile (1,0,4, 'm');
		Tuile t3=new Tuile(1,1,7,'d');
		
		Plateau init= new Plateau();
		System.out.println(init+"\n");
		Plateau custom=new Plateau(2,2, t0, t1, t2, t3);
		System.out.println(custom);
		
		/*
		 * 		Joueur a=new Joueur(false, "Mme Hamm",'b');
		Joueur b=new Joueur(true, "Mme Cols", 'r');
		Joueur c= new Joueur(true, "M. Mancaux", 'w');
		Joueur d= new Joueur(false,"Lilou" ,'o');
		System.out.println(a+"\n");
		System.out.println(b+"\n");
		System.out.println(c+"\n");

		System.out.println(d);
		 */
		Joueur a=new Joueur("Lol",init);
		System.out.println(Joueur.colors);System.out.println(a);

		a.checkCards();
	}
}


