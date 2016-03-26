public class UserAlreadyExistsException extends Exception {

	private String nickname;
	
	public UserAlreadyExistsException(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
}
