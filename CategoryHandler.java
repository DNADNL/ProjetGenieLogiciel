
public class CategoryHandler {
CategoryActivity categoryList[][];
	
	//Constructeur Singleton
		private CategoryHandler()
			{}
			
	//Initialisation Singleton
		private static CategoryHandler singleton;
			
	//Accesseur Singleton
		public static CategoryHandler getPH()
		{
			if (singleton==null)
			{
				singleton = new CategoryHandler();
			}
			return singleton;
		}

		
	//Méthodes
		public CategoryActivity[][] getcategorysList(String nickname)
		{
			if (categoryList==null)
			{
				
			}
			return categoryList;
		}
		

}


