
public class Route{
	
	final Joueur j;
	final boolean vertical; //sert à l'affichage
	
	private class PasAssezDeRessourcesPourRouteException extends PasAssezDeRessourcesException{
		//à mettre potentiel message d'erreur avec les diff ressources manquantes
		public PasAssezDeRessourcesPourRouteException(Ressource r) {
			super(r);
		}
		@Override
		protected void manquer(Ressource r) {
			System.out.println("Il vous manque "+Math.max(0,(double)1-r.getArgile())+" argile"+
		"et "+Math.max(0,(double)1-r.getBois())+" bois.");
		}

	}
	
	private class PlusDeRoutesException extends PlusDeJetonsException{
		public PlusDeRoutesException() {
			System.out.println("Vous n'avez plus de routes pour en poser !");
		}
	}
	

	public Route(Joueur j, int nb) throws PasAssezDeRessourcesPourRouteException, PlusDeRoutesException{
		if(j.nbRoute==0) {
			throw new PlusDeRoutesException();
		}
		if(j.ressources.payerRoute()) {
			this.j=j;
			//en fonction de son placement sur la tuile, la route sait si elle est positionnée 
			//de manière verticale ou horizontale. 
			//exemple : 
			/*	 
			 *  1_
			 * 0|_|2
			     3
			 */
			// si placement sur 0 ou 2, alors vertical, dans ce cas, la variable vertical
			// correspond à nb%2==0
			this.vertical=nb%2==0;
		}else {
			throw new PasAssezDeRessourcesPourRouteException(j.ressources);
			//exception utile car du coup arrête le constructeur
			//(ou alors j'ai pas compris à quoi servait les exceptions)
		}
		
		
	}
	@Override
	public String toString() {
		if(vertical) return "|"; else return "_";
	}

}
