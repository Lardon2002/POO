import java.util.Objects;

public class Tuile {
	final int x; //x position on the board (starts from 0)
	final int y; //y position on the board (starts from 0)
	
	final int valeurDe; // Die value on this case
	final char type;
	public Tuile(int x, int y, int valeur, char type) {
		this.x=x; this.y=y;
		valeurDe=valeur;
		this.type=type;
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
		return " "+type+String.format("%0" + 2 + "d", valeurDe)+type+" ";
	}
}
