import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class UserBD extends User
{
	static JDBConnection jdbc = JDBConnection.getJDBC();
	
	UserBD()
	{
		super();
	}
	
	UserBD(String nickname) throws UserNotInTheDatabaseException
	{
		jdbc.verifyLoginUser(this,nickname);
	}
	
	public static void addUser(String nick, String pass, String email)
	{
		jdbc.createUser(nick, pass, email);
	}

	public static void deleteUser(String nick) {
		jdbc.deleteUser(nick);
	}
	
	public static void modifyUser(String nick, String pass, String email, String firstname,
				String lastname, String city,String street,String postalcode,String streetnumber){
		
		jdbc.modifyUser(nick, pass, email, firstname, lastname, city, street, postalcode, streetnumber);
	}

	public static User getUserData(String nick) {
		// TODO Auto-generated method stub
		return jdbc.getUserData(nick);
	}

//	
	
}
