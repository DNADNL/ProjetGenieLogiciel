/**
	 * This exception is used when an admin deletes a user from the database successfully.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user),
	 */
@SuppressWarnings("serial")
public class UserDeletedException extends Exception {

	private String nickname;
	
	public UserDeletedException(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
}