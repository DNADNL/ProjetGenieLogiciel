
public class UserDB extends User
{
	static JDBConnectionGlobalUser jdbc = JDBConnectionGlobalUser.getJDBCGU();
	
	UserDB()
	{
		super();
	}
	
	UserDB(String nickname) throws ObjectNotInTheDatabaseException
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
		return jdbc.getUserData(nick);
	}
	
	public static boolean isAdmin(String nick) {
		return jdbc.isAdmin(nick);
	}

	public static boolean isSimpleUser(String nick) {
		return jdbc.isSimpleUser(nick);
	}
	
	public static boolean isSeller(String nick) {
		return jdbc.isSeller(nick);
	}
	
	public static void addUserRoleSeller(String nickname) {
		jdbc.addUserRoleSeller(nickname);
	}
	public static void addUserRoleSimpleUser(String nickname) {
		jdbc.addUserRoleSimpleUser(nickname);
	}
	public static void addUserRoleAdmin(String nickname) {
		jdbc.addUserRoleAdmin(nickname);
	}
	public static void deleteUserRoleSeller(String nickname) {
		jdbc.deleteUserRoleSeller(nickname);
	}
	public static void deleteUserRoleSimpleUser(String nickname) {
		jdbc.deleteUserRoleSimpleUser(nickname);
	}
	public static void deleteUserRoleAdmin(String nickname) {
		jdbc.deleteUserRoleAdmin(nickname);
	}
//	
	
}
