
public class TestTuile {

	public static void main(String[] args) {
		
		 Tuile t0=new Tuile (0,0,5, 'm');
		
		Tuile t1=new Tuile(0,1,9,'r');
		Tuile t2=new Tuile (1,0,4, 'm');
		Tuile t3=new Tuile(1,1,7,'d');
		
		
		Plateau init= new Plateau();
		Joueur test=new Joueur("aaa",init);
		test.ressources=new Ressource(5,5,5,5,5);
		
		test.construireColonie(); //sur tuile 00 à pos 0
		try { init.tuiles[1][1].routes[2].obj=new Route(test,2); }catch(Exception e) {}
	
		
		System.out.println(init+"\n");
		System.out.print("types de la maison en haut à gauche : ");
		for(Character c : init.tuiles[1][0].maisons[1].typesGiven()) {
			System.out.print(c+" ");
		}
		System.out.println();
		
		Plateau custom=new Plateau(2,2, t0, t1, t2, t3);
		System.out.println(custom);
		
		/*Joueur a=new Joueur(false, "Mme Hamm",'b');
		Joueur b=new Joueur(true, "Mme Cols", 'r');
		Joueur c= new Joueur(true, "M. Mancaux", 'w');
		Joueur d= new Joueur(false,"Lilou" ,'o');
		System.out.println(a+"\n");
		System.out.println(b+"\n");
		System.out.println(c+"\n");

		System.out.println(d);*/
		
		Joueur a=new Joueur("Lol",init);
		System.out.println(Joueur.colors);System.out.println(a);

		a.checkCards();
		
		System.out.println();
		Tuile t=new Tuile(0,0,2,'m');
		
		
		Tuile t42=new Tuile(0,1,8,'b');
		Tuile aaa=new Tuile(1,0,3,'f');
		Tuile bbb=new Tuile(1,1,4,'r');
		Plateau nov=new Plateau(2,2,t,t42,aaa,bbb);
		
		try { t.maisons[1].obj=new Maison(a); }catch(Exception e) {System.out.println("why");}
		try { t.routes[1].obj=new Route(a,1); }catch(Exception e) {System.out.println("why");}
		try { t.routes[2].obj=new Route(a,2); }catch(Exception e) {System.out.println("why");}
		try { t.maisons[3].obj=new Maison(a); }catch(Exception e) {System.out.println("why");}
		//try { t.maisons[3].obj=new Maison(a); }catch(Exception e) {System.out.println("why");}
		//try { t42.maisons[3].obj=new Maison(a); }catch(Exception e) {System.out.println("why");}
		
		System.out.println(t);
		System.out.println(t42);
		System.out.println(nov);
	}
}


