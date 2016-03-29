/**
	 * This exception is used when an admin creates a user into the database successfully.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user),
	 */
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