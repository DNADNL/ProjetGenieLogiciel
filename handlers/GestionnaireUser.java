package handlers;
import business.User;
import exceptions.EmptyFieldsException;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectCreatedException;
import exceptions.ObjectDeletedException;
import exceptions.ObjectModifiedException;
import exceptions.ObjectNotInTheDatabaseException;
import exceptions.WrongPasswordException;
import factory.AbstractFactoryUser;
import factory.FactoryUser;

public class GestionnaireUser {
	AbstractFactoryUser Fact = new FactoryUser();
	String logNick;
	String logMdp;
	public User currentUser;
	User factUser;

	//Constructeur Singleton
	private GestionnaireUser()
	{}

	//Initialisation Singleton
	private static GestionnaireUser singleton;

	//Accesseur Singleton
	public static GestionnaireUser getGU()
	{
		if (singleton==null)
		{
			singleton = new GestionnaireUser();
		}
		return singleton;
	}

	//Méthodes
	
	public User getCurrentUser()
	{
		return this.currentUser;
	}

	/**
	 * This method is used when the user disconnects.
	 * It deletes the value of currentUser
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 */
	public void deleteCurrentUser()
	{
		this.currentUser=null;
	}

	/**
	 * This method is used when the user log in the system.
	 * It starts the login process.
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), password
	 * @return Integer
	 * @throw WrongPasswordException, ObjectNotInTheDatabaseException
	 *
	 */
	public Integer login(String nickname, String password) throws ObjectNotInTheDatabaseException, WrongPasswordException
	{
		int bool = 0;
		this.logMdp = password;
		this.logNick = nickname;

		factUser = Fact.getUser(nickname);

		bool = this.compareMdp(logNick, logMdp, factUser);

		if (bool == 1)
		{
			refreshCurrentUser(nickname);
		}

		return bool;
	}

	/**
	 * This method is used when a seller wants to delete or show one of his products.
	 * It loads the user from the database and updates currentUser.
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 */
	public void refreshCurrentUser(String nickname)
	{
		factUser = Fact.getUserData(nickname);
		currentUser = factUser;
	}

	/**
	 * This method is used when the user log in the system.
	 * It checks if the informations in the database and the informations provided by the user are similar.
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), password, FactUser
	 * @return Integer
	 * @throw WrongPasswordException
	 *
	 */
	public Integer compareMdp(String nickname, String password, User FactUser) throws WrongPasswordException
	{
		int bool = 0;
		System.out.println(FactUser.nicknameUser + " " + FactUser.mdpUser);
		System.out.println(nickname + " " + password);
		if ( nickname.equals(FactUser.nicknameUser) &&  password.equals(FactUser.mdpUser))
		{
			System.out.println("test validé");
			bool = 1;
		}
		else
		{
			throw new WrongPasswordException(nickname);
		}

		return bool;
	}

	
	/**
	 * This method is used when an admin wants to add a user(via the AddUserView).
	 * 
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), pass, email
	 * If the product is already in the database, then this method returns an {@link ObjectAlreadyExistsException}, 
	 * else an {@link ObjectCreatedException} is returned and the user is added to the database.
	 * @throws 		ObjectCreatedException, ObjectAlreadyExistsException, EmptyFieldsException
	 * @exception ObjectNotInTheDatabaseException
	 */
	public void addUser(String nick, String pass, String email) throws ObjectAlreadyExistsException, ObjectCreatedException, EmptyFieldsException {

		if (nick.equals("") || pass.equals(""))
		{
			throw new EmptyFieldsException();
		}
		else
		{	
			try {
				Fact.getUser(nick);	
				throw new ObjectAlreadyExistsException(nick);
			}
			catch (ObjectNotInTheDatabaseException e) // L'exception sera levée si l'utilisateur ne se trouve pas dans la BD
			{
				Fact.addUser(nick, pass, email);	// On peut donc créer l'utilisateur ici
				throw new ObjectCreatedException(e.getName());
			}

		}
	}

	/**
	 * This method is used when an admin modifies the informations of a user (via ModifyUserView).
	 * 
	 *
	 * @param  		nick (a {@link String} giving the nickname of the user), pass, email, firstname, lastname, city, street, postalcode, streetnumber
	 * @return Integer
	 * @throw ObjectModifiedException, ObjectNotInTheDatabaseException
	 * @exception ObjectNotInTheDatabaseException
	 *
	 */
	public void modifyUser(String nick, String pass, String email, String firstname,
			String lastname, String city,String street,String postalcode,String streetnumber) throws ObjectNotInTheDatabaseException, ObjectModifiedException{

		try {
			Fact.getUser(nick);	
			Fact.modifyUser(nick, pass, email, firstname, lastname, city, street, postalcode, streetnumber);
			refreshCurrentUser(nick);
			throw new ObjectModifiedException(nick);
		}
		catch (ObjectNotInTheDatabaseException e) // L'exception sera levée si l'utilisateur ne se trouve pas dans la BD
		{
			System.out.println("ERREUR - GestionnaireUser.deleteUser() / Fact.getUser : L'utilisateur n'existe pas dans la base de données ");
			throw e;
		}

	}

	/**
	 * This method is used when an admin wants to delete one user (via DeleteUserView).
	 * It checks if the user is registered in the database (thanks to its nickname).
	 *
	 *
	 * @param  		nick (a {@link String} giving the nickname of the user)
	 * If the user does not exist in the database, then this method returns an {@link ObjectNotInTheDatabaseException}, 
	 * else an {@link ObjectDeletedException} is returned and the used is deleted from the database.
	 * @throws 		ObjectDeletedException
	 * @exception ObjectNotInTheDatabaseException
	 */
	public void deleteUser(String nick) throws ObjectNotInTheDatabaseException, ObjectDeletedException {

		try {
			Fact.getUser(nick);	
			if (this.isSimpleUser(nick))
			{
				Fact.deleteUserRoleSimpleUser(nick);
				System.out.println("GU.deleteUser - deleteUserRoleSimpleUser");
			}
			if (this.isAdmin(nick))
			{
				Fact.deleteUserRoleAdmin(nick);
				System.out.println("GU.deleteUser - deleteUserRoleAdmin");
			}
			if (this.isSeller(nick))
			{
				Fact.deleteUserRoleSeller(nick);
				System.out.println("GU.deleteUser - deleteUserRoleSeller");
			}			
			Fact.deleteUser(nick);
			System.out.println("GU.deleteUser - deleteUser");
			throw new ObjectDeletedException(nick);
		}
		catch (ObjectNotInTheDatabaseException e) // L'exception sera levée si l'utilisateur ne se trouve pas dans la BD
		{
			System.out.println("ERREUR - GestionnaireUser.deleteUser() / Fact.getUser : L'utilisateur n'existe pas dans la base de données ");
			throw e;
		}

	}

	/**
	 * This method is used when a user log in the system.
	 * It check if the user is an admin.
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 * @return boolean
	 */
	public boolean isAdmin(String nick) {
		return Fact.isAdmin(nick);
	}

	/**
	 * This method is used when a user log in the system.
	 * It check if the user is a simple user.
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 * @return boolean
	 */
	public boolean isSimpleUser(String nick) {
		return Fact.isSimpleUser(nick);
	}

	/**
	 * This method is used when a user log in the system.
	 * It check if the user is a seller.
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 * @return boolean
	 */
	public boolean isSeller(String nick) {
		return Fact.isSeller(nick);
	}

	/**
	 * This method is used when an admin wants to add a user(via the AddUserView).
	 * It deletes the current role of the user and add the role seller
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 */
	public void chooseUserRoleSeller(String nick) {
		if (this.isSimpleUser(nick))
		{
			Fact.deleteUserRoleSimpleUser(nick);
			System.out.println("GU.chooseUserRoleSeller - deleteUserRoleSimpleUser");
		}
		if (this.isAdmin(nick))
		{
			Fact.deleteUserRoleAdmin(nick);
			System.out.println("GU.chooseUserRoleSeller - deleteUserRoleAdmin");
		}
		if (!(this.isSeller(nick)))
		{
			Fact.addUserRoleSeller(nick);
			System.out.println("GU.chooseUserRoleSeller - addUserRoleSeller");

		}			
	}

	/**
	 * This method is used when an admin wants to add a user(via the AddUserView).
	 * It deletes the current role of the user and add the role user
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 */
	public void chooseUserRoleSimpleUser(String nick) {
		if (this.isAdmin(nick))
		{
			Fact.deleteUserRoleAdmin(nick);
			System.out.println("GU.chooseUserRoleSimpleUser - deleteUserRoleAdmin");
		}
		if (this.isSeller(nick))
		{
			Fact.deleteUserRoleSeller(nick);
			System.out.println("GU.chooseUserRoleSimpleUser - deleteUserRoleSeller");
		}	
		if (!(this.isSimpleUser(nick)))
		{
			Fact.addUserRoleSimpleUser(nick);
			System.out.println("GU.chooseUserRoleSimpleUser - addUserRoleSimpleUser");
		}
	}

	/**
	 * This method is used when an admin wants to add a user(via the AddUserView).
	 * It deletes the current role of the user and add the role admin
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 */
	public void chooseUserRoleAdmin(String nick) {
		if (this.isSeller(nick))
		{
			Fact.deleteUserRoleSeller(nick);
			System.out.println("GU.chooseUserRoleAdmin - deleteUserRoleSeller");
		}	
		if (this.isSimpleUser(nick))
		{
			Fact.deleteUserRoleSimpleUser(nick);
			System.out.println("GU.chooseUserRoleAdmin - deleteUserRoleSimpleUser");
		}
		if (!(this.isAdmin(nick)))
		{
			Fact.addUserRoleAdmin(nick);
			System.out.println("GU.chooseUserRoleAdmin - addUserRoleAdmin");
		}
	}
}
