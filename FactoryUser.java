
public class FactoryUser extends AbstractFactoryUser {
	
	User user;
	
	/**
	 * This method is used when an user wants to log in the app (via the Login View).
	 * Creates a log instance of the user if the user is registered in the database. 
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  nickname  a String giving the name of the user
	 * @return      a User. 
	 * If the user is located in the database, then this method returns the user, 
	 * else a {@link null} user is returned.
	 * @throws ObjectNotInTheDatabaseException 
	 */
	
	public User getUser(String nickname) throws ObjectNotInTheDatabaseException
	{
		this.user = new UserDB(nickname);
		return user;
	}
	
	public User getUserData(String nick)
	{
		return UserDB.getUserData(nick);
	}
	
	public void addUser(String nick, String pass, String email)
	{
		UserDB.addUser(nick, pass, email);		
	}
	
	public void modifyUser(String nick, String pass, String email, String firstname,
				String lastname, String city,String street,String postalcode,String streetnumber){
		
		UserDB.modifyUser(nick, pass, email, firstname, lastname, city, street, postalcode, streetnumber);
	}
	
	public void deleteUser(String nick)
	{
		UserDB.deleteUser(nick);
	}
	
	public boolean isAdmin(String nick) {
		return UserDB.isAdmin(nick);
	}

	public boolean isSimpleUser(String nick) {
		return UserDB.isSimpleUser(nick);
	}
	
	public boolean isSeller(String nick) {
		return UserDB.isSeller(nick);
	}

	public void addUserRoleSeller(String nickname) {
		UserDB.addUserRoleSeller(nickname);
	}
	public void addUserRoleSimpleUser(String nickname) {
		UserDB.addUserRoleSimpleUser(nickname);
	}
	public void addUserRoleAdmin(String nickname) {
		UserDB.addUserRoleAdmin(nickname);
	}
	public void deleteUserRoleSeller(String nickname) {
		UserDB.deleteUserRoleSeller(nickname);
	}
	public void deleteUserRoleSimpleUser(String nickname) {
		UserDB.deleteUserRoleSimpleUser(nickname);
	}
	public void deleteUserRoleAdmin(String nickname) {
		UserDB.deleteUserRoleAdmin(nickname);
	}
	
}
