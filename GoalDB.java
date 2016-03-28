import java.util.ArrayList;

public class GoalDB extends Goal {

	static JDBConnection jdbc = JDBConnection.getJDBC();
	
	
	GoalDB(String goal_title)
	{
		this.goal_title = null;
		this.goal_description = null;
		this.user_nickname =null;
		this.id_category = null;
		
	}
	
	GoalDB(String goal_t, String goal_desc, String nick, Integer id_cat)
	{
		this.goal_title = goal_t;
		this.goal_description = goal_desc;
		this.user_nickname =nick;
		this.id_category = id_cat;
		
	}
	
	public static void addGoal(String goal_title, String goal_description, String nick) {
		// TODO Auto-generated method stub
		
		jdbc.createGoal(goal_title,goal_description, nick );
		
	}
	
	static public ArrayList<Goal> createGoalList(String nickname)
	{
		
		return jdbc.getGoalList(nickname);
	}

	public static void deleteGoal(String goal_name, String nicknameUser) {
		// TODO Auto-generated method stub
		jdbc.deleteGoal(goal_name, nicknameUser);
		
	}

	
	

}
