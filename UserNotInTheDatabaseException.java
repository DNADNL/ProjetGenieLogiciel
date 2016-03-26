public class UserNotInTheDatabaseException extends Exception{
	private String nickname;
	
	public UserNotInTheDatabaseException(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
}
