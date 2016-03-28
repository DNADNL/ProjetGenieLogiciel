public class GoalCreatedException extends Exception {

	private String goal_title;
	
	public GoalCreatedException(String goal_title) {
		this.goal_title = goal_title;
	}
	
	public String getGoal_title() {
		return goal_title;
	}
}