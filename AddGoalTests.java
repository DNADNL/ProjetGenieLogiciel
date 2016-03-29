import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddGoalTests {

	private FacadeUser FU = FacadeUser.getFU();
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Before
	public void setUpTestGoalCreatedException() throws Exception {
		try {
			FU.deleteGoal("NotInDBTest");
		} catch (Exception e) {}

	}

	@Test
	public void testGoalCreatedException() throws GoalCreatedException {
		try {
			FU.addGoal("goal_title", "goal_description", "nickname_test");
			fail("Expected an UserCreatedException to be thrown");
		} catch (UserAlreadyExistsException e) {
			fail("Not Expected an UserAlreadyExistsException to be thrown");
		}
		catch (GoalCreatedException e) {
			assert(e.getGoal_title().equals("NotInDBTest"));
		}
	}
	
	@After
	public void tearDownTestGoalCreatedException() {
		try {
			FU.deleteUser("NotInDBTest");
		} catch (Exception e) {}
	}
	
}