/* handler for category activity*/
public class CategoryHandler {
CategoryActivity categoryList[][];
	AbstractFactory Fact = new FactoryActivityCategory();
	
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
		public CategoryActivity[][] getCategoryList()
		{
			if (categoryList==null)
			{
// 				categoryList = Fact.createActivityList();
			}
			return categoryList;
		}
		

		public void suggestActivityCategory(String title, String description) {
			// TODO Auto-generated method stub
			try
			{
//				Fact.getCategoryActivity(String title);
				
			}
			catch(Exception e){
				
			}
			
		}
	}


