
// Abstract Class of User Factory

public abstract class AbstractFactoryUser 
{

	abstract public User getUser(String nickname) throws ObjectNotInTheDatabaseException;
	abstract public User getUserData(String nickname);
	abstract public void addUser(String nickname, String password, String email);
	abstract public void deleteUser(String nick);
	abstract public void modifyUser(String nick, String pass, String email, String firstname, String lastname, 
			String city, String street, String postalcode, String streetnumber);
	abstract public boolean isAdmin(String nick);
	abstract public boolean isSimpleUser(String nick);
	abstract public boolean isSeller(String nick);
	abstract public void addUserRoleSeller(String nickname);
	abstract public void addUserRoleSimpleUser(String nickname);
	abstract public void addUserRoleAdmin(String nickname);
	abstract public void deleteUserRoleSeller(String nickname);
	abstract public void deleteUserRoleSimpleUser(String nickname);
	abstract public void deleteUserRoleAdmin(String nickname);
}
