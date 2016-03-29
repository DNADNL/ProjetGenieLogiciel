/**
	 * This exception is used when an admin deletes a user from the database successfully.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user),
	 */
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