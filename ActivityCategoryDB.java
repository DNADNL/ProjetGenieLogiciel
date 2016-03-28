import java.util.ArrayList;

public class ActivityCategoryDB extends ActivityCategory {
	static JDBConnection jdbc = JDBConnection.getJDBC();
	
	public ActivityCategoryDB(){
		this.title = null;
		this.description = null;
	}
	
	
	static public ArrayList<ActivityCategory> createCategoryActivityList()
	{
		return jdbc.getAllCategoryActivity();
	}
	
	static public ArrayList<ActivityCategory> createCategoryActivitySuggestionList()
	{
		return jdbc.getAllSuggestionActivityCategory();
	}
	
	public static void SuggestCategoryActivity(String title, String description){
		jdbc.addSuggestionActivityCategory(title, description);
	}
	
}

