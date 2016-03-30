import java.util.ArrayList;

public class FactoryGoal extends AbstractFactoryGoal {

	Goal createdGoal;
	ArrayList<Goal> createdGoalList;
	
	@Override
	public void addGoal(String goal_title, String goal_description, String nick) {
		// TODO Auto-generated method stub
		GoalDB.addGoal(goal_title, goal_description, nick);
	}

	@Override
	public ArrayList<Goal> createGoalList(String nickname) {
		
		createdGoalList = GoalDB.createGoalList(nickname);
		return createdGoalList;
	}

	@Override
	public void deleteGoal(String goal_name, String nicknameUser) {
		GoalDB.deleteGoal(goal_name, nicknameUser);
		
	}
	
	

}
