
public class ProductsHandler {
	
	Product productList[][];
	
	//Constructeur Singleton
		private ProductsHandler()
			{}
			
	//Initialisation Singleton
		private static ProductsHandler singleton;
			
	//Accesseur Singleton
		public static ProductsHandler getPH()
		{
			if (singleton==null)
			{
				singleton = new ProductsHandler();
			}
			return singleton;
		}

		
	//Méthodes
		public Product[][] getProductsList(String nickname)
		{
			if (productList==null)
			{
				
			}
			return productList;
		}
		

}
