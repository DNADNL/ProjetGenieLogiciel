import org.junit.*;

public class SuggestionActivityCategoryTests {

	static JDBConnectionActivityCategory jdbq;
	 
	@Test 
	public void testSuggestionActivityCategory(){
		ActivityCategory actCat = new ActivityCategory();
		actCat.title="test";
		actCat.description="test2";
		
		jdbq.addSuggestionActivityCategory(actCat.title,actCat.description);
		ActivityCategory ac = jdbq.getLastActivityCategorySuggestion();
		boolean result = (ac.title.equals(actCat.title) && ac.description.equals(actCat.description));
		Assert.assertTrue("�a devrait �tre vrai", result);
	 }

}
