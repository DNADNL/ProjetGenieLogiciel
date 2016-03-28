import java.util.ArrayList;

/* handler for category activity*/
public class CategoryHandler {
	
	ArrayList<ActivityCategory> categoryList;
	String [][] stringCategoryList;
	AbstractFactory Fact = new FactoryActivityCategory();
	ArrayList<ActivityCategory> categorySuggestionList;
	//Constructeur Singleton
		private CategoryHandler()
			{}
			
	//Initialisation Singleton
		private static CategoryHandler singleton;
			
	//Accesseur Singleton
		public static CategoryHandler getCH()
		{
			if (singleton==null)
			{
				singleton = new CategoryHandler();
			}
			return singleton;
		}

		
	//Méthodes
		public void getCategoryList()
		{
			if (categoryList==null)
			{
			categoryList = Fact.createCategoryActivityList();
			}
		}
	

		public void suggestActivityCategory(String title, String description) {
			// TODO Auto-generated method stub
			Fact.suggestActivityCategory(title, description);
			
			}
		
		public String[][] getStringCategoryList()
		{
			getCategoryList();
			stringCategoryList= new String [categoryList.size()][2];
			
			if (categoryList != null)
			{
				for (Integer i=0; i<categoryList.size(); i++)
				{
					stringCategoryList[i][0]= categoryList.get(i).title;
					stringCategoryList[i][1]= categoryList.get(i).description;
				}
			}
			return stringCategoryList;
			
		}


		public void getCategoryActivitySuggestionList()
		{
			if (categorySuggestionList==null)
			{
				categorySuggestionList = Fact.createCategoryActivitySuggestionList();
			}
		}
		
		public String[][] getStringCategorySuggestionList() {
			getCategoryActivitySuggestionList();
			stringCategoryList= new String [categorySuggestionList.size()][2];
			if (categorySuggestionList != null){
				for (Integer i=0; i<categorySuggestionList.size(); i++)
				{
					stringCategoryList[i][0]= categorySuggestionList.get(i).title;
					stringCategoryList[i][1]= categorySuggestionList.get(i).description;
				}
			}
			return stringCategoryList;
			
			}
}

			

	


