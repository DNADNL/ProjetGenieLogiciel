package factory;
import java.util.ArrayList;

import business.ActivityCategory;

// Abstract Class of Activity Category Factory

public abstract class AbstractFactoryActivityCategory {

	abstract public ArrayList<ActivityCategory> createActivityCategoryList(String title);
	abstract public void suggestActivityCategory(String title,String description);
	abstract public void getActivityCategory(String title);
	abstract public ArrayList<ActivityCategory> createActivityCategoryList();
	abstract public ArrayList<ActivityCategory> createActivityCategorySuggestionList();
	abstract public ActivityCategory getLastActivityCategorySuggestion();
}
