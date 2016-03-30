
public class GestionnaireUser {
		AbstractFactoryUser Fact = new FactoryUser();
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
		
		public Integer login(String nickname, String password) throws ObjectNotInTheDatabaseException, WrongPasswordException
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
		
		public User getUserData(String nick)
		{
			return Fact.getUserData(nick);
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

		public void addUser(String nick, String pass, String email) throws ObjectAlreadyExistsException, ObjectCreatedException {
			
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
		
		public void modifyUser(String nick, String pass, String email, String firstname,
				String lastname, String city,String street,String postalcode,String streetnumber) throws ObjectNotInTheDatabaseException, ObjectModifiedException{
			
			try {
				Fact.getUser(nick);	
				Fact.modifyUser(nick, pass, email, firstname, lastname, city, street, postalcode, streetnumber);
				throw new ObjectModifiedException(nick);
			}
			catch (ObjectNotInTheDatabaseException e) // L'exception sera levée si l'utilisateur ne se trouve pas dans la BD
			{
				System.out.println("ERREUR - GestionnaireUser.deleteUser() / Fact.getUser : L'utilisateur n'existe pas dans la base de données ");
				throw e;
			}
			
		}
		
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
		
		public boolean isAdmin(String nick) {
			return Fact.isAdmin(nick);
		}

		public boolean isSimpleUser(String nick) {
			return Fact.isSimpleUser(nick);
		}
		
		public boolean isSeller(String nick) {
			return Fact.isSeller(nick);
		}
		
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
