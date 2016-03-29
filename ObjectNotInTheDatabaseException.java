/**
	 * This exception is used when a user tries to get an Object that doesn't exist in the database.
	 * <p>
	 *
	 * @param  		name (a {@link String} giving the nickname of the Object),
	 */
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
