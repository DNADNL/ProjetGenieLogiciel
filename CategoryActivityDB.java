import java.util.ArrayList;

public class CategoryActivityDB extends CategoryActivity {
	static JDBConnection jdbc = JDBConnection.getJDBC();
	
	public CategoryActivityDB(){
		this.title = null;
		this.descritpion = null;
	}
	
	
	static public ArrayList<CategoryActivity> createCategoryActivityList()
	{
		return jdbc.getAllCategoryActivity();
	}
	
	static public ArrayList<CategoryActivity> createCategoryActivitySuggestionList()
	{
		return jdbc.getAllSuggestionCategoryActivity();
	}
	
	public static void SuggestCategoryActivity(String title, String description){
		jdbc.addSuggestionActivityCategory(title, description);
	}
	
}


