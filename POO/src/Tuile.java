import java.util.Objects;

public class Tuile {
	int x;
	int y; //Sa position x et y sur le plateau (de 0 à n)
	
	int valeurDe; //La valeur du jeton qui se trouve dessus
	Ressource type;
	public Tuile(int x, int y, int valeur, char type) {
		this.x=x; this.y=y;
		valeurDe=valeur;
		this.type=new Ressource(type);
	}
	public int getId() {return id;}
	public int getValeur() {return valeurDe;}
	public Ressource getRessource() {return type;}
	@Override
	public boolean equals(Object o) {
		if (o==null) return false;
		if (!(o instanceof Tuile)) return false;
		Tuile t=(Tuile) t;
return (this==t ||(this.x==t.x && y==t.y && type==t.type && valeurDe==t.valeurDe));
	}
	@Override
	public int hashCode() {
		return Objects.hash(Integer.hashCode(x), Integer.hashCode(y));
	}
}
