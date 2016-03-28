public class UserModifiedException extends Exception {

	private String nickname;
	
	public UserModifiedException(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
}