/**
	 * This exception is used when an admin modifies the data of a user successfully or when a user modifies its data successfully.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user),
	 */
@SuppressWarnings("serial")
public class UserModifiedException extends Exception {

	private String nickname;
	
	public UserModifiedException(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
}