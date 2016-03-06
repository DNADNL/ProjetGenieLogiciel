
public class FacadeUser {
	
	GestionnaireUser GU = new GestionnaireUser();
	
	
	public Integer login(String nickname, String mdp )
	{
		System.out.println("bloup");
		int bool = GU.login(nickname, mdp);
		System.out.println(bool);
		return bool;
	}
	
	public User getUser()
	{
		
		return GU.getCurrentUser();
	}
	
}
