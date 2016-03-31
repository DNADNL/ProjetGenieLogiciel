import org.junit.*;

public class SuggestionActivityCategoryTests {

	static JDBConnectionActivityCategory jdbc;
	 
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
