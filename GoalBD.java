
public class GoalBD extends Goal {

	static JDBConnection jdbc = JDBConnection.getJDBC();
	
	public static void addGoal(String goal_title, String goal_description, String nick) {
		// TODO Auto-generated method stub
		
		jdbc.createGoal(goal_title,goal_description, nick );
		System.out.println("Ajout d'un Goal");
	}
	
	

}
