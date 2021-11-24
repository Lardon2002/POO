import java.util.LinkedList; 

public class Cellule implements Printed{
	
	Object obj; 
	
	public Cellule() {
		this.obj=null;
	}
	public Cellule(Object obj) {
		this.obj=obj;
	}
	
	@Override
	public String toString() throws NullPointerException{
		try{ return obj.toString(); }
		catch(NullPointerException e) { return "-";} //cas vide
	}
	
	//sert aux tests
	public LinkedList<Character> typesGiven(){
		if(obj!=null) {
			return ((Maison)obj).typesGiven; 
		}else {
			return null;
		}
	}
}
