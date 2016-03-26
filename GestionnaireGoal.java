
public class GestionnaireGoal {
	
	AbstractFactory Fact = new FactoryGoal();
	
	//Constructeur Singleton
			private GestionnaireGoal()
			{}
			
			//Initialisation Singleton
			private static GestionnaireGoal singleton;
			
			//Accesseur Singleton
			public static GestionnaireGoal getGG()
			{
				if (singleton==null)
				{
					singleton = new GestionnaireGoal();
				}
				return singleton;
			}

			public void addGoal(String goal_title, String goal_description, String nick) {
				// TODO Auto-generated method stub
				Fact.addGoal(goal_title, goal_description, nick);
			}

			
}
