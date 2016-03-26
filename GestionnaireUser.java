import java.sql.SQLException;

public class GestionnaireUser {
		AbstractFactory Fact = new FactoryUser();
		String logNick;
		String logMdp;
		User currentUser;
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
		
		public void deleteCurrentUser()
		{
			this.currentUser=null;
		}
		
		public Integer login(String nickname, String password) throws UserNotInTheDatabaseException, WrongPasswordException
		{
			int bool = 0;
			this.logMdp = password;
			this.logNick = nickname;
			
			factUser = Fact.getUser(nickname);

			bool = this.compareMdp(logNick, logMdp, factUser);
		
			if (bool == 1)
			{
				this.loadUser(nickname);
			}
			
			return bool;
		}
		
		public void loadUser(String nick)
		{
			currentUser = factUser;
		}
		
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

		public void addUser(String nick, String pass, String email) throws UserAlreadyExistsException, UserCreatedException {
			
			try {
				Fact.getUser(nick);	
				throw new UserAlreadyExistsException(nick);
			}
			catch (UserNotInTheDatabaseException e) // L'exception sera levée si l'utilisateur ne se trouve pas dans la BD
			{
				Fact.addUser(nick, pass, email);	// On peut donc créer l'utilisateur ici
				throw new UserCreatedException(e.getNickname());
			}

		}
		
		public void modifyUser(String nick, String pass, String email, String firstname,
				String lastname, String city,String street,String postalcode,String streetnumber) throws UserNotInTheDatabaseException, UserModifyException{
			
			try {
				Fact.getUser(nick);	
				Fact.modifyUser(nick, pass, email, firstname, lastname, city, street, postalcode, streetnumber);
				throw new UserModifyException(nick);
			}
			catch (UserNotInTheDatabaseException e) // L'exception sera levée si l'utilisateur ne se trouve pas dans la BD
			{
				System.out.println("ERREUR - GestionnaireUser.deleteUser() / Fact.getUser : L'utilisateur n'existe pas dans la base de données ");
				throw e;
			}
			
		}
		
		public void deleteUser(String nick) throws UserNotInTheDatabaseException, UserDeletedException {
			
			try {
				Fact.getUser(nick);	
				Fact.deleteUser(nick);
				throw new UserDeletedException(nick);
			}
			catch (UserNotInTheDatabaseException e) // L'exception sera levée si l'utilisateur ne se trouve pas dans la BD
			{
				System.out.println("ERREUR - GestionnaireUser.deleteUser() / Fact.getUser : L'utilisateur n'existe pas dans la base de données ");
				throw e;
			}

		}


}
