package exceptions;

// This exception is used when a user tries to get an Object that doesn't exist in the database.
// The "name" parameter corresponds to a String giving the name of the Object.

@SuppressWarnings("serial")
public class ObjectNotInTheDatabaseException extends Exception{
	private String name;
	
	public ObjectNotInTheDatabaseException(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
