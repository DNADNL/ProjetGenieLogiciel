import java.util.ArrayList;

public class ActivityCategoryDB extends ActivityCategory {
	static JDBConnectionActivityCategory jdbc = JDBConnectionActivityCategory.getJDBCAC();
	
	public ActivityCategoryDB(){
		this.title = null;
		this.description = null;
	}
	
	
	static public ArrayList<ActivityCategory> createCategoryActivityList()
	{
		return jdbc.getAllActivityCategories();
	}
	
	static public ArrayList<ActivityCategory> createCategoryActivitySuggestionList()
	{
		return jdbc.getAllSuggestionActivityCategory();
	}
	
	public static void SuggestCategoryActivity(String title, String description){
		jdbc.addSuggestionActivityCategory(title, description);
	}
	
}

