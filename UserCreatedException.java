/**
	 * This exception is used when an admin creates a user into the database successfully.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user),
	 */
@SuppressWarnings("serial")
public class UserCreatedException extends Exception {

	private String nickname;
	
	public UserCreatedException(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
}