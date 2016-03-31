package businessDB;
import java.util.ArrayList;

import business.ActivityCategory;
import jdbc.JDBConnectionActivityCategory;


public class ActivityCategoryDB extends ActivityCategory {
	static JDBConnectionActivityCategory jdbc = JDBConnectionActivityCategory.getJDBCAC();
	
	public ActivityCategoryDB(){
		this.title = null;
		this.description = null;
	}
	
	/**
	 * This method creates an Activity Category List.
	 * It retrieves all the Activity Categories from the Database (thanks to JDBC).
	 *
	 * @return      {@link ArrayList} of Activity Categories
	 */
	static public ArrayList<ActivityCategory> createActivityCategoriesList()
	{
		return jdbc.getAllActivityCategories();
	}
	
	/**
	 * This method is used to create an Activity Category Suggestion List.
	 * It retrieves all the Activity Category Suggestions from the Database.
	 *
	 * @return      {@link ArrayList} of Activity Category Suggestions
	 */
	static public ArrayList<ActivityCategory> createCategoryActivitySuggestionList()
	{
		return jdbc.getAllSuggestionActivityCategory();
	}
	
	/**
	 * This method is used when a Simple User wants to suggest an Activity Category to add into the Activity Category List.
	 * It creates the Activity Category suggested into the Activity Category Suggestions table in the Database.
	 *
	 * @param  		title			(a {@link String} giving the title of the Activity Category),
	 * 				description 	(a {@link String} giving the description of the Activity Category).
	 */
	public static void suggestActivityCategory(String title, String description){
		jdbc.addSuggestionActivityCategory(title, description);
	}

	public static ActivityCategory getLastActivityCategorySuggestion() {
		return jdbc.getLastActivityCategorySuggestion();
	}
	
}

