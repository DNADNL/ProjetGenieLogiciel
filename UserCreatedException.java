public class UserCreatedException extends Exception {

	private String nickname;
	
	public UserCreatedException(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
}