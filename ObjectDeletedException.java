
// This exception is used when an admin deletes a user from the database successfully.
// The "name" parameter corresponds to a String giving the name of the Object.

@SuppressWarnings("serial")
public class ObjectDeletedException extends Exception {

	private String name;
	
	public ObjectDeletedException(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}