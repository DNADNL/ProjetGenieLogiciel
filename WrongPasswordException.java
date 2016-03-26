public class WrongPasswordException extends Exception {

	private String nickname;
	
	public WrongPasswordException(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
}