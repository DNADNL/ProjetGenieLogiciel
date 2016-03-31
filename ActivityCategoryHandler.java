import java.util.ArrayList;

// The Activity Category Handler
public class ActivityCategoryHandler {
	
	AbstractFactoryActivityCategory Fact = new FactoryActivityCategory();
	
	ArrayList<ActivityCategory> activityCategoryList;
	String [][] stringActivityCategoryList;
	ArrayList<ActivityCategory> activityCategorySuggestionList;
	
	//Singleton Constructor
		private ActivityCategoryHandler()
			{}
			
	//Singleton Initialisator
		private static ActivityCategoryHandler singleton;
			
	//Singleton Accessor
		public static ActivityCategoryHandler getCH()
		{
			if (singleton==null)
			{
				singleton = new ActivityCategoryHandler();
			}
			return singleton;
		}

		
	//Methods
		
		
		
		/**
		 * This method gets an Activity Category List (or creates it if it doesn't exist).
		 * It retrieves all the Activity Categories from the Database.
		 * <p>
		 *
		 * @param  		none
		 * @return      void
		 */
		public void getActivityCategoryList()
		{
			if (activityCategoryList==null)
			{
			activityCategoryList = Fact.createActivityCategoryList();
			}
		}
	
		/**
		 * This method is used when a user wants to suggest an Activity Category.
		 * It adds the Activity Category Suggestion to the database.
		 * The title/description argument specifies the Activity Category and must be a {@link String}.
		 * <p>
		 *
		 * @param  		title			(a {@link String} giving the Activity Category title)
		 * 				description		(a {@link String} giving the Activity Category description)
		 * @return      void
		 * @exception	EmptyFieldsException
		 */
		public void suggestActivityCategory(String title, String description) throws EmptyFieldsException {
			// TODO Auto-generated method stub
			if(title.isEmpty()){
				throw new EmptyFieldsException();
			}
			else{
			Fact.suggestActivityCategory(title, description);
			refreshActivityCategorySuggestionList();
			}
		}
		
		/**
		 * This method gets an Activity Category List as a String 2D Table (useful to display Activity Categories).
		 * It retrieves the Activity Category List from the database.
		 * <p>
		 *
		 * @param  		none
		 * @return      {@link String} 2D Table
		 */
		public String[][] getStringActivityCategoryList()
		{
			getActivityCategoryList();
			stringActivityCategoryList= new String [activityCategoryList.size()][2];
			
			if (activityCategoryList != null)
			{
				for (Integer i=0; i<activityCategoryList.size(); i++)
				{
					stringActivityCategoryList[i][0]= activityCategoryList.get(i).title;
					stringActivityCategoryList[i][1]= activityCategoryList.get(i).description;
				}
			}
			return stringActivityCategoryList;
			
		}

		/**
		 * This method gets an Activity Category Suggestion List.
		 * It retrieves all the Activity Category Suggestions from the database to store them.
		 * <p>
		 *
		 * @param  		none
		 * @return      void
		 */
		public void getActivityCategorySuggestionList()
		{
			if (activityCategorySuggestionList==null)
			{
				activityCategorySuggestionList = Fact.createActivityCategorySuggestionList();
			}
		}
		
		/**
		 * This method gets an Activity Category Suggestion List as a String 2D Table (useful to display Activity Category Suggestions).
		 * It retrieves the Activity Category Suggestion List from the database.
		 * <p>
		 *
		 * @param  		none
		 * @return      {@link String} 2D Table
		 */
		public String[][] getStringActivityCategorySuggestionList() {
			getActivityCategorySuggestionList();
			stringActivityCategoryList= new String [activityCategorySuggestionList.size()][2];
			if (activityCategorySuggestionList != null){
				for (Integer i=0; i<activityCategorySuggestionList.size(); i++)
				{
					stringActivityCategoryList[i][0]= activityCategorySuggestionList.get(i).title;
					stringActivityCategoryList[i][1]= activityCategorySuggestionList.get(i).description;
				}
			}
			return stringActivityCategoryList;
			
			}
		
		/**
		 * This method refreshes the Activity Category Suggestion List stored.
		 * It recreates the Activity Category Suggestion List.
		 * <p>
		 *
		 * @param  		none
		 * @return      void
		 */
		public void refreshActivityCategorySuggestionList(){
			activityCategorySuggestionList = Fact.createActivityCategorySuggestionList();
		}

		/**
		 * This method deletes all current information stored in vars.
		 * <p>
		 *
		 * @param  		none
		 * @return      void
		 */
		public void deleteAllCurrentInfos() {
			// TODO Auto-generated method stub
			activityCategoryList = null;
			stringActivityCategoryList = null;
			activityCategorySuggestionList = null;
		}
		
}

			

	


