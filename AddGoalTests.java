import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddGoalTests {

	private FacadeUser FU = FacadeUser.getFU();
	

	@Before
	public void setUpTestGoalCreatedException() throws Exception {
		try {
			FU.deleteGoal("goal_title_test");
		} catch (Exception e) {}

	}

	@Test
	public void testGoalCreatedException() throws ObjectCreatedException {
		try {
			FU.addGoal("goal_title_test", "goal_description", "nickname_test");
			fail("Expected an GoalCreatedException to be thrown");
		} catch (ObjectAlreadyExistsException e) {
			fail("Not Expected an GoalAlreadyExistsException to be thrown");
		}
		catch (ObjectCreatedException e) {
			assert(e.getName().equals("goal_title_test"));
		}
	}
	
	@After
	public void tearDownTestGoalCreatedException() {
		try {
			FU.deleteGoal("goal_title_test");
		} catch (Exception e) {}
	}
	
}
