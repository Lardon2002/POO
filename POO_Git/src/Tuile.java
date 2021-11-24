import java.util.Objects;

public class Tuile {
	final int x; //x position on the board (starts from 0)
	final int y; //y position on the board (starts from 0)
	
	final int valeurDe; // Die value on this case
	final char type;
	
	//essai pour les routes 
	//on met des cellules pour pouvoir pointer sur la même route entre tuiles
	Cellule[] routes=new Cellule[4];//car 4 côtés dans un carré
	/*	 
	 *  1_
	 * 0|_|2
	     3
	 */
	Cellule[] maisons=new Cellule[4]; //pareil que pour route
	/*
	 * 
	 */
	public Tuile(int x, int y, int valeur, char type) {
		this.x=x; this.y=y;
		valeurDe=valeur;
		this.type=type;
		for(int i=0; i<4; i++) {
			this.routes[i]=new Cellule();
			this.maisons[i]=new Cellule();
		}
	}
	
	//Getters
	public int getX() {return x;}
	public int getY() {return y;}
	public int getId() {return hashCode();}
	public int getValeur() {return valeurDe;}
	public char getRessource() {return type;}
	
	// Object methods overrides
	@Override
	public boolean equals(Object o) {
		if (o==null) return false;
		if (!(o instanceof Tuile)) return false;
		Tuile t=(Tuile) o;
		return (this.x==t.x && y==t.y && type==t.type && valeurDe==t.valeurDe);
	}
	@Override
	public int hashCode() {
		return Objects.hash(x,y);
	}
	
	public String toString() {
		String str="";
		
		str+=Printed.toString(maisons[1],routes[1],routes[1],routes[1],routes[1],maisons[2]);
		str+="\n";
		str+=Printed.toString(routes[0]);
		str+=type+String.format("%0" + 2 + "d", valeurDe)+type;
		str+=Printed.toString(routes[2]);
		str+="\n";
		str+=Printed.toString(maisons[0],routes[3],routes[3],routes[3],routes[3],maisons[3]);
		
		return str;
	}
}
