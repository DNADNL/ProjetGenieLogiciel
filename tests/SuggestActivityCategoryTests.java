package tests;
import org.junit.*;

import business.ActivityCategory;
import jdbc.JDBConnectionActivityCategory;
import jdbc.JDBConnectionOpen;

public class SuggestActivityCategoryTests {


	static JDBConnectionActivityCategory jdbc;
	
	@Before
	public void setUpSuggestionActivityCategory(){
		JDBConnectionOpen.getJDBCO();
	}
	@Test 
	public void testSuggestionActivityCategory(){
		ActivityCategory actCat = new ActivityCategory();
		actCat.title="test";
		actCat.description="test2";
		
		jdbc.addSuggestionActivityCategory(actCat.title,actCat.description);
		ActivityCategory ac = jdbc.getLastActivityCategorySuggestion();
		boolean result = (ac.title.equals(actCat.title) && ac.description.equals(actCat.description));
		Assert.assertTrue("ça devrait être vrai", result);
	 }

}
