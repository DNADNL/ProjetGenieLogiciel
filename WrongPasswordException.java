
// This exception is used when the user enters a wrong password during its login attempt.
// The "nickname" parameter corresponds to a String giving the nicknameh of the User.

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