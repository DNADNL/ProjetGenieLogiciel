import java.util.ArrayList;

public class ActivityCategoryDB extends ActivityCategory {
	static JDBConnectionActivityCategory jdbc = JDBConnectionActivityCategory.getJDBCAC();
	
	public ActivityCategoryDB(){
		this.title = null;
		this.description = null;
	}
	
	/**
	 * This method creates an Activity Category List.
	 * It retrieves all the Activity Categories from the Database (thanks to JDBC).
	 * <p>
	 *
	 * @param  		none
	 * @return      {@link ArrayList} of Activity Categories
	 */
	static public ArrayList<ActivityCategory> createActivityCategoriesList()
	{
		return jdbc.getAllActivityCategories();
	}
	
	/**
	 * This method is used to create an Activity Category Suggestion List.
	 * It retrieves all the Activity Category Suggestions from the Database.
	 * <p>
	 *
	 * @param  		none
	 * @return      {@link ArrayList} of Activity Category Suggestions
	 */
	static public ArrayList<ActivityCategory> createCategoryActivitySuggestionList()
	{
		return jdbc.getAllSuggestionActivityCategory();
	}
	
	/**
	 * This method is used when a Simple User wants to suggest an Activity Category to add into the Activity Category List.
	 * It creates the Activity Category suggested into the Activity Category Suggestions table in the Database.
	 * <p>
	 *
	 * @param  		title			(a {@link String} giving the title of the Activity Category),
	 * 				description 	(a {@link String} giving the description of the Activity Category).
	 * @return      void
	 */
	public static void suggestActivityCategory(String title, String description){
		jdbc.addSuggestionActivityCategory(title, description);
	}
	
}

