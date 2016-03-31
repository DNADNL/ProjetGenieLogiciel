package tests;

import org.junit.Before;

import business.User;
import businessDB.UserDB;
import exceptions.ObjectDeletedException;
import exceptions.ObjectModifiedException;
import exceptions.ObjectNotInTheDatabaseException;

import static org.junit.Assert.fail;

import org.junit.*;
import facade.FacadeUser;
import factory.FactoryUser;
import jdbc.JDBConnectionOpen;

public class modifyUserTest {
	private FacadeUser FU = FacadeUser.getFU();

	@Before
	public void setUpModifyUser(){
		try {
			JDBConnectionOpen.getJDBCO();
			FU.addUser("modifyTest", "pwdTest", "e-mail@test.fr");
			FU.chooseUserRoleSimpleUser("modifyTest");
			
			
		}
		catch(Exception e){}
	}	
	
	@Test
	public void testModifyUser() throws ObjectNotInTheDatabaseException, ObjectModifiedException{
		User userModif;
		try{
			FU.modifyUser("modifyTest", "nouveauTest", "test@test.fr", "a", "a", "a", "a", "a", "a");
			fail("it has been modified");
		}
		catch(ObjectModifiedException e)
		{
			assert(e.getName().equals("modifyTest"));
		}
		
	}
	
	//@After
	public void tearDownModifyUserTest() throws ObjectNotInTheDatabaseException, ObjectDeletedException{
	try{
	FU.deleteUser("modifyTest");
	}
	catch (Exception e) {}
}
}
