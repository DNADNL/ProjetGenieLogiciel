package businessDB;
import java.util.ArrayList;

import business.Goal;
import jdbc.JDBConnectionGoal;

public class GoalDB extends Goal {

	static JDBConnectionGoal jdbc = JDBConnectionGoal.getJDBCG();
	
	
	public GoalDB(String goal_title)
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
	
	/**
	 * This method is used when a user wants to add a Goal to his list of Goals.
	 * 
	 * 
	 * @param goal_title, goal_description, nick
	 */
	public static void addGoal(String goal_title, String goal_description, String nick) {
		// TODO Auto-generated method stub
		
		jdbc.createGoal(goal_title,goal_description, nick );
		
	}
	
	/**
	 * This method creates an Goal List.
	 * It retrieves all the Goals of the user from the Database.
	 * 
	 * @param nickname
	 * @return      {@link ArrayList} of Goal
	 */
	static public ArrayList<Goal> createGoalList(String nickname)
	{
		
		return jdbc.getGoalList(nickname);
	}

	/**
	 * This method is used when a user wants to delete a Goal from his list of Goals.
	 * 
	 * 
	 * @param goal_name, nicknameUser
	 */
	public static void deleteGoal(String goal_name, String nicknameUser) {
		// TODO Auto-generated method stub
		jdbc.deleteGoal(goal_name, nicknameUser);
		
	}

	
	

}
