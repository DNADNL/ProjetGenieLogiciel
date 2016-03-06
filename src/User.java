
public abstract class User {

	String nicknameUser;
	String mdpUser;
	String emailUser;
	
	User()
	{
		
	}
	
	User(String nickname)
	{
		this.nicknameUser = nickname;	
	}
	
	User(String nickname, String password)
	{
		this.nicknameUser = nickname;
		this.mdpUser = password;
	}
	
}
