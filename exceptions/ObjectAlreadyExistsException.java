package exceptions;

// This exception is used when an admin tries to create a user that already exists in the database.
// The "name" parameter corresponds to a String giving the name of the Object.

@SuppressWarnings("serial")
public class ObjectAlreadyExistsException extends Exception {

	private String name;
	
	public ObjectAlreadyExistsException(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
