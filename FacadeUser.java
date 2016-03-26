import java.sql.SQLException;

public class FacadeUser {
	
	GestionnaireUser GU = GestionnaireUser.getGU();
	ProductsHandler PH = ProductsHandler.getPH();
	GestionnaireGoal GG = GestionnaireGoal.getGG();
	
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
	
	public User getCurrentUser()
	{
		return GU.getCurrentUser();
	}
	
	public void disconnectUser()
	{
		GU.deleteCurrentUser();
	}
	
	//Méthodes Products
	public String[][] getStringProductList()
	{
		return PH.getStringProductList(GU.currentUser.nicknameUser);
	}
	
	public void deleteProduct(String pdt_product) throws UserNotInTheDatabaseException, UserDeletedException{
		PH.deleteProduct(pdt_product, GU.getCurrentUser().nicknameUser);
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
	
	public void addGoal(String goal_title, String goal_description, String nick) {
		// TODO Auto-generated method stub
		GG.addGoal(goal_title, goal_description, nick);
	}


	public User getUserData(String nicknameUser) {
		return GU.getUserData(nicknameUser);
	}


	

}
