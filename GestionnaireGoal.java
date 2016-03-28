import java.util.ArrayList;

public class GestionnaireGoal {
	
	Goal goal_selected;
	ArrayList<Goal> goalList;
	String [][] stringGoalList;
	
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

			public void addGoal(String goal_title, String goal_description, String nick)throws GoalCreatedException {
				// TODO Auto-generated method stub
				Fact.addGoal(goal_title, goal_description, nick);
				throw new GoalCreatedException(nick);
			}

			private void getGoalList(String nickname)
			{	
				if (goalList==null)
				{
					goalList = Fact.createGoalList(nickname);
				}
			}
			
			private void refreshGoalList(String nickname)
			{	
				goalList = Fact.createGoalList(nickname);
			}
			
			public String[][] getStringGoalList(String nickname)
			{
				getGoalList(nickname);
				stringGoalList= new String [goalList.size()][2];
				
				if (goalList != null)
				{
					for (Integer i=0; i<goalList.size(); i++)
					{
						stringGoalList[i][0]= goalList.get(i).goal_title;
						stringGoalList[i][1]= goalList.get(i).goal_description;
					}
				}
				
				return stringGoalList;
				
			}
			
}
