public class UserModifyException extends Exception {

	private String nickname;
	
	public UserModifyException(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
}