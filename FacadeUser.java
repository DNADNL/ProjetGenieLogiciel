import java.sql.SQLException;

public class FacadeUser {
	
	GestionnaireUser GU = GestionnaireUser.getGU();
	ProductsHandler PH = ProductsHandler.getPH();
	
	//Constructeur Singleton
	private FacadeUser()
	{}
	
	//Initialisation Singleton
	private static FacadeUser singleton;
	
	//Accesseur Singleton
	public static FacadeUser getFU()
	{
		if (singleton==null)
		{
			singleton = new FacadeUser();
		}
		return singleton;
	}
	
	
	//Méthodes User
	public Integer login(String nickname, String mdp) throws UserNotInTheDatabaseException, WrongPasswordException
	{
		int bool = GU.login(nickname, mdp);
		System.out.println(bool);
		return bool;
	}
	
	public User getUser()
	{
		return GU.getCurrentUser();
	}
	
	public void disconnectUser()
	{
		GU.deleteCurrentUser();
	}
	
	//Méthodes Products
	public Product[][] getProductList()
	{
		return PH.getProductsList(GU.currentUser.nicknameUser);
	}
	
	// Méthodes Admin
	public void addUser(String nick, String pass, String email) throws UserAlreadyExistsException, UserCreatedException {
		GU.addUser(nick, pass, email);
	}
	
	public void modifyUser(String nick, String pass, String email, String firstname, String lastname,
			String city,String street,String postalcode,String streetnumber) 
					
	throws UserNotInTheDatabaseException, UserModifyException{
		
		GU.modifyUser(nick, pass, email, firstname, lastname, city, street, postalcode, streetnumber);
	}
	
	public void deleteUser(String nick) throws UserNotInTheDatabaseException, UserDeletedException{
		GU.deleteUser(nick);
	}

}
