
public interface Printed{
	abstract String toString();
	
	static String toString(Printed ...args) {
		String str="";
		for(Printed obj : args) {
			try { str+=obj.toString(); }catch(NullPointerException e) { str+=" ";}
		}
		
		return str;
	}

}
