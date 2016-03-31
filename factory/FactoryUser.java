package factory;
import business.User;
import businessDB.UserDB;
import exceptions.ObjectNotInTheDatabaseException;

public class FactoryUser extends AbstractFactoryUser {
	
	User user;
	
	/**
	 * This method is used when a user wants to log in the app (via the Login View).
	 * It creates a log instance of the user if the user is registered in the database. 
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  nickname  (a {@link String} giving the name of the user)
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
	
	/**
	 * This method is used when a user wants to modify his/her data.
	 * It retrieves all the user data from the database. 
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  		nickname  (a {@link String} giving the nickname of the user)
	 * @return      {@link User}
	 */
	public User getUserData(String nick)
	{
		return UserDB.getUserData(nick);
	}
	
	/**
	 * This method is used when an admin wants to add a Global User to the database.
	 * It adds the user into the database. 
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  		nickname  	(a {@link String} giving the nickname of the user)
	 * 				password 	(a {@link String} giving the password of the user)
	 * 				e-mail 		(a {@link String} giving the e-mail address of the user)
	 */
	public void addUser(String nick, String pass, String email)
	{
		UserDB.addUser(nick, pass, email);		
	}
	
	
	/**
	 * This method is used when an admin wants to modify Global User data or when a Simple User wants to modify his/her data in the database.
	 * It modifies the user data into the database. 
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  		nickname  		(a {@link String} giving the nickname of the user),
	 * 				password 		(a {@link String} giving the password of the user),
	 * 				e-mail 			(a {@link String} giving the e-mail address of the user),
	 * 				first name 		(a {@link String} giving the first name of the user),
	 * 				last name		(a {@link String} giving the last name of the user),
	 * 				city 			(a {@link String} giving the city of the user),
	 * 				street 			(a {@link String} giving the street of the user),
	 * 				postal code		(a {@link String} giving the postal code of the user),
	 * 				street number	(a {@link String} giving the street number of the user)
	 */
	public void modifyUser(String nick, String pass, String email, String firstname,
				String lastname, String city,String street,String postalcode,String streetnumber){
		
		UserDB.modifyUser(nick, pass, email, firstname, lastname, city, street, postalcode, streetnumber);
	}
	
	/**
	 * This method is used when an admin wants to delete a Global User.
	 * It deletes the user from the database. 
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  	nickname	(a {@link String} giving the nickname of the user)
	 */
	public void deleteUser(String nick)
	{
		UserDB.deleteUser(nick);
	}
	
	/**
	 * This method verifies if a Global User is an Admin.
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  	nickname	(a {@link String} giving the nickname of the user)
	 * @return	{@link boolean}
	 */
	public boolean isAdmin(String nick) {
		return UserDB.isAdmin(nick);
	}

	/**
	 * This method verifies if a Global User is a Simple User.
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  	nickname	(a {@link String} giving the nickname of the user)
	 * @return	{@link boolean}
	 */
	public boolean isSimpleUser(String nick) {
		return UserDB.isSimpleUser(nick);
	}
	
	/**
	 * This method verifies if a Global User is a Seller.
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  	nickname	(a {@link String} giving the nickname of the user)
	 * @return	{@link boolean}
	 */
	public boolean isSeller(String nick) {
		return UserDB.isSeller(nick);
	}
	
	/**
	 * This method adds the Seller Role to the Global User.
	 * It adds the nickname of the user into the "seller" table in the database
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  	nickname	(a {@link String} giving the nickname of the user)
	 */
	public void addUserRoleSeller(String nickname) {
		UserDB.addUserRoleSeller(nickname);
	}
	
	/**
	 * This method adds the Simple User Role to the Global User.
	 * It adds the nickname of the user into the "simple_user" table in the database
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  	nickname	(a {@link String} giving the nickname of the user)
	 */
	public void addUserRoleSimpleUser(String nickname) {
		UserDB.addUserRoleSimpleUser(nickname);
	}
	
	/**
	 * This method adds the Admin Role to the Global User.
	 * It adds the nickname of the user into the "admin" table in the database
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  	nickname	(a {@link String} giving the nickname of the user)
	 */
	public void addUserRoleAdmin(String nickname) {
		UserDB.addUserRoleAdmin(nickname);
	}
	
	/**
	 * This method deletes the Seller Role to the Global User.
	 * It deletes the nickname of the user into the "seller" table in the database
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  	nickname	(a {@link String} giving the nickname of the user)
	 */
	public void deleteUserRoleSeller(String nickname) {
		UserDB.deleteUserRoleSeller(nickname);
	}
	
	/**
	 * This method deletes the Simple User Role to the Global User.
	 * It deletes the nickname of the user into the "simple_user" table in the database
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  	nickname	(a {@link String} giving the nickname of the user)
	 */
	public void deleteUserRoleSimpleUser(String nickname) {
		UserDB.deleteUserRoleSimpleUser(nickname);
	}
	
	/**
	 * This method deletes the Admin Role to the Global User.
	 * It deletes the nickname of the user into the "admin" table in the database
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  	nickname	(a {@link String} giving the nickname of the user)
	 */
	public void deleteUserRoleAdmin(String nickname) {
		UserDB.deleteUserRoleAdmin(nickname);
	}
	
}
