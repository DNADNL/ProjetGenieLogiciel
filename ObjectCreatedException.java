
// This exception is used when an admin creates a user into the database successfully.
// The "name" parameter corresponds to a String giving the name of the Object.

@SuppressWarnings("serial")
public class ObjectCreatedException extends Exception {

	private String name;
	
	public ObjectCreatedException(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}