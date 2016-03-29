/**
	 * This exception is used when an admin tries to create a user that already exists in the database.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user),
	 */
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
