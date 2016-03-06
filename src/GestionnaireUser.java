
public class GestionnaireUser {
		AbstractFactory Fact = new FactoryUser();
		String logNick;
		String logMdp;
		User currentUser;
		
		public User getCurrentUser()
		{
			return this.currentUser;
		}
		
		public Integer login(String nickname, String password)
		{
			int bool = 0;
			this.logMdp = password;
			this.logNick = nickname;
			
			this.loadUser(nickname);
			bool = this.compareMdp(nickname, password, currentUser);
			return bool;
		}
		
		public void loadUser(String nick)
		{
			currentUser = Fact.createUser(nick);
		}
		
		public Integer compareMdp(String nickname, String password, User FactUser)
		{
			int bool = 0;
			System.out.println(FactUser.nicknameUser + " " + FactUser.mdpUser);
			System.out.println(nickname + " " + password);
			if ( nickname.equals(FactUser.nicknameUser) &&  password.equals(FactUser.mdpUser))
			{
				System.out.println("test validé");
				bool = 1;
			}
			
			return bool;
		}
		
		
}
