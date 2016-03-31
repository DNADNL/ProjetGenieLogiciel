import java.util.ArrayList;

public class FactoryActivityCategory extends AbstractFactoryActivityCategory {

	ActivityCategory createdActivityCategory;
	ArrayList<ActivityCategory> createdActivityCategoryList;
	
	public ActivityCategory createActivity(String title)
	{
		this.createdActivityCategory = new ActivityCategoryDB();
		return createdActivityCategory;
	}
	
	@Override
	public ArrayList<ActivityCategory> createCategoryActivityList() {
		createdActivityCategoryList = ActivityCategoryDB.createActivityCategoriesList();
		return createdActivityCategoryList;
	}
//	

	@Override
	public void suggestActivityCategory(String title, String description) {
		// TODO Auto-generated method stub
		ActivityCategoryDB.suggestActivityCategory(title, description);
		
	}



	@Override
	public void getActivityCategory(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ActivityCategory> createActivityCategoryList(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ActivityCategory> createCategoryActivitySuggestionList() {
		createdActivityCategoryList = ActivityCategoryDB.createCategoryActivitySuggestionList();
		return createdActivityCategoryList;
	}

}
