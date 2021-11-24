
abstract class PasAssezDeRessourcesException extends Exception{
	public PasAssezDeRessourcesException(Ressource r) {
		super();
		manquer(r);
	}
	
	protected abstract void manquer(Ressource r);

}
