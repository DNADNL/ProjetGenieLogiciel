public class ObjectNotInTheDatabaseException extends Exception{
	private String nickname;
	
	public ObjectNotInTheDatabaseException(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
}
