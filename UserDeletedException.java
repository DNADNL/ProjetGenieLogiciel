public class UserDeletedException extends Exception {

	private String nickname;
	
	public UserDeletedException(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
}