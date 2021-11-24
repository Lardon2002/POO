import java.util.LinkedList;

public class Maison{
	
	LinkedList<Character> typesGiven=new LinkedList<Character>();
	int nbRessources=1; 
	final Joueur j;
	
	protected class PasAssezDeRessourcesPourColonieException extends PasAssezDeRessourcesException{
		//à mettre potentiel message d'erreur avec les diff ressources manquantes
		public PasAssezDeRessourcesPourColonieException(Ressource r) {
			super(r);
		}
		protected void manquer(Ressource r) {
			System.out.println("Il vous manque "+Math.max(0,(double)2-r.getArgile())+" argile"+
		"et "+Math.max(0,(double)3-r.getBois())+" bois.");
		}

	}
	
	protected class PlusDeColoniesException extends PlusDeJetonsException{
		public PlusDeColoniesException() {
			System.out.println("Vous n'avez plus de colonies pour en poser !");
		}
	}
	
	
	
	public Maison(Joueur j) throws PasAssezDeRessourcesPourColonieException, PlusDeColoniesException{
		// TODO Auto-generated constructor stub
		if(j.nbColonie==0) {
			throw new PlusDeColoniesException();
		}
		if(j.ressources.payerColonie()) {
			this.j=j;
			j.colonies.add(this);
			j.nbColonie--;
		}else{
			throw new PasAssezDeRessourcesPourColonieException(j.ressources);
		}
	}
	
	public void evoluerEnVille(){
		if(j.nbVille==0) {
			System.out.println("Vous n'avez plus de villes pour en poser.");
			return;
		}
		if(j.ressources.payerVille()) {
			this.nbRessources=2;
			j.nbColonie++;
			j.nbVille--;
		}else {
			System.out.println("Il vous manque "+Math.max(0,(double)2-j.ressources.getRoche())+" roche"+
					"et "+Math.max(0,(double)3-j.ressources.getBle())+" blé.");
		}
	}
	@Override
	public String toString() {
		if(nbRessources==1) { return "c"; } else return "v";
	}

}
