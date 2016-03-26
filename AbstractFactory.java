import java.sql.SQLException;

public abstract class AbstractFactory 
{

	abstract public User getUser(String nickname) throws UserNotInTheDatabaseException;
	abstract public void addUser(String nickname, String password, String email);
	abstract public void deleteUser(String nick);
	abstract public void modifyUser(String nick, String pass, String email, String firstname, String lastname, String city,
			String street, String postalcode, String streetnumber);

}
