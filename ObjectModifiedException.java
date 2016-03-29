/**
	 * This exception is used when an admin modifies the data of a user successfully or when a user modifies its data successfully.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user),
	 */
@SuppressWarnings("serial")
public class ObjectModifiedException extends Exception {

	private String name;
	
	public ObjectModifiedException(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}