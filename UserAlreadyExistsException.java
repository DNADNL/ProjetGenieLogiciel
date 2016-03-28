/**
	 * This exception is used when an admin tries to create a user that already exists in the database.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user),
	 */
@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception {

	private String nickname;
	
	public UserAlreadyExistsException(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
}
