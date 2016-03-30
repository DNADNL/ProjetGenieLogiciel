import java.util.ArrayList;

public abstract class AbstractFactoryActivityCategory {

	abstract public ArrayList<ActivityCategory> createActivityCategoryList(String title);
	abstract public void suggestActivityCategory(String title,String description);
	abstract public void getActivityCategory(String title);
	abstract public ArrayList<ActivityCategory> createCategoryActivityList();
	abstract public ArrayList<ActivityCategory> createCategoryActivitySuggestionList();
}
