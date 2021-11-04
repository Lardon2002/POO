public class Tuile {
	int id;
	int valeurDe; //La valeur du jeton qui se trouve dessus
	Ressource type;
	public Tuile(int id, int valeur, char type) {
		this.id=id;
		valeurDe=valeur;
		this.type=new Ressource(type);
	}
	public int getId() {return id;}
	public int getValeur() {return valeurDe;}
	public Ressource getRessource() {return type;}
}
