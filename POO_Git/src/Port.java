

public class Port extends Maison{
	Character exchangeType;
	int taux;
	public Port(Joueur j, Character t, int tx) throws PasAssezDeRessourcesPourColonieException, PlusDeColoniesException{
		super(j);
		this.exchangeType=t; 
		this.taux=tx;
		j.ports.add(this);
	}

}
