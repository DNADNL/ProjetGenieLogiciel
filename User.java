
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

	public String getUserNickname()
	{
		if (this.nicknameUser == null)
			{
				return null;
			}
		
		return this.nicknameUser;
	}	
}
