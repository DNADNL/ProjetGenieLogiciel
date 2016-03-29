import static org.junit.Assert.*;

import org.junit.*;

public class AddUserTests {

	private FacadeUser FU = FacadeUser.getFU();

	@Before
	public void setUpTestIsAdmin() {
		try {
			FU.addUser("AdminTest", "pwdTest", "e-mail@test.fr");
			FU.chooseUserRoleAdmin("AdminTest");
		}
		catch (Exception e){};
	}	

	@Test
	public void testIsAdmin() {
		assertTrue("AdminTest should be an Admin", !(FU.isAdmin("AdminTest")));
	}
	
	@After
	public void tearDownTestIsAdmin() {
		try {
			FU.deleteUser("AdminTest");
		} catch (Exception e) {}
	}
	
	
	
	@Before
	public void setUpTestIsSeller() {
		try {
			FU.addUser("SellerTest", "pwdTest", "e-mail@test.fr");
			FU.chooseUserRoleSeller("SellerTest");
		}
		catch (Exception e){};
	}

	@Test
	public void testIsSeller() {
		assertTrue("SellerTest should be a Seller", !(FU.isSeller("SellerTest")));
	}
	
	@After
	public void tearDownTestIsSeller() {
		try {
			FU.deleteUser("SellerTest");
		} catch (Exception e) {}
	}
	
	
		
	@Before
	public void setUpTestIsSimpleUser() {
		try {
			FU.addUser("SUTest", "pwdTest", "e-mail@test.fr");
		}
		catch (Exception e){};
	}
	
	@Test
	public void testIsSimpleUser() {
		assertTrue("SUTest should be a Simple User", !(FU.isSimpleUser("SUTest")));
	}
	
	@After
	public void tearDownTestIsSimpleUser() {
		try {
			FU.deleteUser("SUTest");
		} catch (Exception e) {}
	}
	
	
	
	@Before
	public void setUpTestUserAlreadyExistsException() {
		try {
			FU.addUser("AlreadyInDBTest", "pwdTest", "e-mail@test.fr");
			FU.chooseUserRoleSimpleUser("AlreadyInDBTest");
		}
		catch (Exception e){};
	}
	
	@Test
	public void testUserAlreadyExistsException() {
		try {
			FU.addUser("AlreadyInDBTest", "pwdTest", "e-mail@test.fr");
			fail("Expected an UserAlreadyExistsException to be thrown");
		} catch (ObjectAlreadyExistsException e) {
			assert(e.getName().equals("AlreadyInDBTest"));
		}
		catch (ObjectCreatedException e) {
			fail("Not Expected an UserCreatedException to be thrown");
		}
	}
	
	@After
	public void tearDownTestUserAlreadyExistsException() {
		try {
			FU.deleteUser("AlreadyInDBTest");
		} catch (Exception e) {}
	}
	

	
	@Before
	public void setUpTestUserCreatedException() throws Exception {
		try {
			FU.deleteUser("NotInDBTest");
		} catch (Exception e) {}

	}

	@Test
	public void testUserCreatedException() {
		try {
			FU.addUser("NotInDBTest", "pwdTest", "e-mail@test.fr");
			fail("Expected an UserCreatedException to be thrown");
		} catch (ObjectAlreadyExistsException e) {
			fail("Not Expected an UserAlreadyExistsException to be thrown");
		}
		catch (ObjectCreatedException e) {
			assert(e.getName().equals("NotInDBTest"));
		}
	}
	
	@After
	public void tearDownTestUserCreatedException() {
		try {
			FU.deleteUser("NotInDBTest");
		} catch (Exception e) {}
	}


}