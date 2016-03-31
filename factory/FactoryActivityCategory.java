package factory;
import java.util.ArrayList;

import business.*;
import businessDB.*;

public class FactoryActivityCategory extends AbstractFactoryActivityCategory {

	ActivityCategory createdActivityCategory;
	ArrayList<ActivityCategory> createdActivityCategoryList;
	
	
	/**
	 * This method creates an Activity Category in the database. 
	 * The nickname argument specifies the user and must be a {@link String}.
	 *
	 * @param  title  (a {@link String} giving the title of the Activity Category)
	 * @return {@link Activity Category}
	 */
	public ActivityCategory createActivity(String title)
	{
		this.createdActivityCategory = new ActivityCategoryDB();
		return createdActivityCategory;
	}
	
	/**
	 * This method creates an Activity Categories List. 
	 *
	 * @return {@link ArrayList} of Activity Categories
	 */
	public ArrayList<ActivityCategory> createActivityCategoryList() {
		createdActivityCategoryList = ActivityCategoryDB.createActivityCategoriesList();
		return createdActivityCategoryList;
	}

	/**
	 * This method creates an Activity Category Suggestion in the database. 
	 * The title/description argument specifies the user and must be a {@link String}.
	 *
	 * @param  	title		(a {@link String giving the title of the Activity Category),
	 * 			description	 (a {@link String giving the description of the Activity Category)
	 */
	public void suggestActivityCategory(String title, String description) {
		// TODO Auto-generated method stub
		ActivityCategoryDB.suggestActivityCategory(title, description);
		
	}
	


	/**
	 * This method creates an Activity Category Suggestions List. 
	 *
	 * @return {@link ArrayList} of Activity Categories
	 */
	public ArrayList<ActivityCategory> createActivityCategorySuggestionList() {
		createdActivityCategoryList = ActivityCategoryDB.createCategoryActivitySuggestionList();
		return createdActivityCategoryList;
	}
	
	// Method Under Construction
	public ArrayList<ActivityCategory> createActivityCategoryList(String title) {

		return null;
	}
	
	// Method Under Construction
	public void getActivityCategory(String title) {
		
		
	}
	public ActivityCategory getLastActivityCategorySuggestion(){
		ActivityCategory LastActivityCategorySuggestion = ActivityCategoryDB.getLastActivityCategorySuggestion() ;
		return LastActivityCategorySuggestion;
	}
}
