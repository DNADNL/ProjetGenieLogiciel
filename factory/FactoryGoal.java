package factory;
import java.util.ArrayList;

import business.Goal;
import businessDB.GoalDB;

public class FactoryGoal extends AbstractFactoryGoal {

	Goal createdGoal;
	ArrayList<Goal> createdGoalList;
	
	/**
	 * This method creates a Goal into the database and adds it to the Simple User Goal List. 
	 * The title/nickname argument specifies the goal/user and must be a {@link String}.
	 *
	 * @param  	goal title  		(a {@link String} giving the title of a Goal)
	 * 			goal_description	(a {@link String} giving the description of a Goal)
	 * 			nickname			(a {@link String} giving the nickname of a User)
	 */
	public void addGoal(String goal_title, String goal_description, String nick) {
		GoalDB.addGoal(goal_title, goal_description, nick);
	}

	/**
	 * This method creates a simple user Goals List. 
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  	nickname			(a {@link String} giving the nickname of a User)
	 * @return {@link ArrayList} of Goals
	 */
	public ArrayList<Goal> createGoalList(String nickname) {
		
		createdGoalList = GoalDB.createGoalList(nickname);
		return createdGoalList;
	}

	/**
	 * This method deletes a Goal from the Simple User Goals List and then deletes the Goal from the database. 
	 * The nickname/goal name argument specifies the user/goal and must be a {@link String}.
	 *
	 * @param  	goal name	(a {@link String} giving the name of a goal)
	 * 			nickname	(a {@link String} giving the nickname of a User)
	 */
	public void deleteGoal(String goal_name, String nicknameUser) {
		GoalDB.deleteGoal(goal_name, nicknameUser);
		
	}
	
	

}
