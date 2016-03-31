import java.util.ArrayList;

// Abstract Class of Goal Factory

public abstract class AbstractFactoryGoal {

	abstract public void addGoal(String goal_title, String goal_description, String nick);
	abstract public ArrayList<Goal> createGoalList(String nickname);
	abstract public void deleteGoal(String goal_name, String nicknameUser);
}
