
public class FactoryUser extends AbstractFactory {
	
	User createdUser;
	
	
	public User createUser(String nickname)
	{
		
		this.createdUser = new UserBD(nickname);
		return createdUser;
	}
}
