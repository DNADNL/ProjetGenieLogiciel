/**
	 * This exception is used when the user enters a wrong password during its login attempt.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user),
	 */
@SuppressWarnings("serial")
public class WrongPasswordException extends Exception {

	private String nickname;
	
	public WrongPasswordException(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
}