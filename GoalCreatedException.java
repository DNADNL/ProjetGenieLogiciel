/**
	 * This exception is used when a simple user creates a goal into the database successfully.
	 * <p>
	 *
	 * @param  		goal title (a {@link String} giving the title of the goal),
	 */
@SuppressWarnings("serial")
public class GoalCreatedException extends Exception {

	private String goal_title;
	
	public GoalCreatedException(String goal_title) {
		this.goal_title = goal_title;
	}
	
	public String getGoal_title() {
		return goal_title;
	}
}