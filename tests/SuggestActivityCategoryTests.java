package tests;
import org.junit.*;

import business.ActivityCategory;
import exceptions.EmptyFieldsException;
import facade.FacadeUser;
import jdbc.JDBConnectionActivityCategory;
import jdbc.JDBConnectionOpen;

public class SuggestActivityCategoryTests {


	static FacadeUser FU =FacadeUser.getFU() ;
	
	
	@Before
	public void setUpSuggestionActivityCategory() throws EmptyFieldsException{
		JDBConnectionOpen.getJDBCO();
		ActivityCategory actCat = new ActivityCategory();
	    actCat.title="test";
		
		actCat.description="test2";
		try{
		FU.suggestActivityCategory(actCat.title,actCat.description);
	}
		catch(EmptyFieldsException e){}
	}
	@Test 
	public void testSuggestionActivityCategory() throws EmptyFieldsException{
		ActivityCategory actCat = new ActivityCategory();
	    actCat.title="test";
		
		actCat.description="test2";
		ActivityCategory ac = new ActivityCategory();
		ac =  FU.getLastActivityCategorySuggestion();
		System.out.print(ac.title);
		boolean result = (ac.title.equals(actCat.title) && ac.description.equals(actCat.description));
		Assert.assertTrue("ça devrait être vrai", result);
	 }

}
