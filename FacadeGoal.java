
public class FacadeGoal {
	
	GestionnaireGoal GG = GestionnaireGoal.getGG();

	//Constructeur Singleton
		private FacadeGoal()
		{}
		
		//Initialisation Singleton
		private static FacadeGoal singleton;
		
		//Accesseur Singleton
		public static FacadeGoal getFG()
		{
			if (singleton==null)
			{
				singleton = new FacadeGoal();
			}
			return singleton;
		}

		public void addGoal(String goal_title, String goal_description, String nick) {
			// TODO Auto-generated method stub
			GG.addGoal(goal_title, goal_description, nick);
		}
}
