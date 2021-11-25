public class Ressource {
	private int bois=0;
	private int argile=0;
	private int mouton=0;
	private int ble=0; //Later referenced to as 'f', for "foin" (avoids conflict with "bois")
	private int roche=0;
	public Ressource(int b, int a, int m, int f, int r) {
		bois=b; argile=a; mouton=m; ble=f; roche=r;
	}
	//ressource vide
	public Ressource() {
		this(0,0,0,0,0);
	}
	//création d'une seule ressource
	public Ressource (char c) {
		switch(c) {
			case 'b': bois=1; break;
			case 'a': argile=1; break;
			case 'm': mouton=1; break;
			case 'f': ble=1; break;
			case 'r': roche=1; break;
			case 'd': break; //desert
		}		
	}
	//Getters
	public int getBois() {return bois;}
	public int getArgile() {return argile;}
	public int getMouton() {return mouton;}
	public int getBle() {return ble;}
	public int getRoche() {return roche;}
	public int nbRessources() {return bois+argile+mouton+ble+roche;}

	//Sum of 2 resource objects. Modifies this object
	public void somme(Ressource r) {
		bois+=r.bois;
		argile+=r.argile;
		mouton+=r.mouton;
		ble+=r.ble;
		roche+=r.roche;
	}
	
	//Object method overrides
	@Override
	public boolean equals(Object o) {
		if (o==null) return false;
		if (!(o instanceof Ressource)) return false;
		Ressource r= (Ressource) o;
		return (bois==r.bois && argile==r.argile && mouton ==r.mouton && ble==r.ble && roche==r.roche);
	}
	
	public String toString() {
		return bois+ " bois, "+ argile + " argile, "+ ble + " blé, " +
				mouton+" mouton, "+ roche + " roche.";
	}
	
	//Pay to build methods
	public boolean payerRoute() {
		//Du coté du joueur, il faudra évidemment lui donner sa route
		//La méthode pour construire ses premières routes et colonies ne fait pas appel à cette méthode.
		if (bois<1 && argile<1) return false;
		bois--; argile--; return true;
	}
	public boolean payerColonie() {
		//Du coté du joueur, il faudra évidemment lui donner sa colonie
		//La méthode pour construire ses premières routes et colonies ne fait pas appel à cette méthode.
		if (bois<1 && argile<1 && mouton<1 && ble<1) return false;
		bois--; argile--; mouton--; ble--; return true;
	}
	public boolean payerVille() {
		//Du coté du joueur, il faudra évidemment lui donner sa ville
		if (ble<2 && roche<3) return false;
		ble-=2; roche-=3; return true;
	}
	public boolean payerDev() {
		//Du coté du joueur, il faudra évidemment lui donner sa carte
		if (mouton<1 && ble<1 && roche<1) return false;
		mouton--; ble--; roche--; return true;
	}
	
	//Discard when Voleur methods
	public boolean discard(char type, int nb) {
		/**Method to discard one or several instances of one type of resource
		 * entry: type and number of resources to discard
		 * return : true if player can discard that many resources; false otherwise
		 * This method is called when Voleur steals resourses, among others.		 * 
		 */
		switch (type) {
		case 'b': if (bois-nb<0) return false; else {bois-=nb; return true;}
		case 'a': if (argile-nb<0) return false; else {argile-=nb; return true;}
		case 'm': if (mouton-nb<0) return false; else {mouton-=nb; return true;}
		case 'f': if (ble-nb<0) return false; else {ble-=nb; return true;}
		case 'r': if (roche-nb<0) return false; else {roche-=nb; return true;}
		default: return false;
		}
	}
}
