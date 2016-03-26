import java.util.ArrayList;
import java.sql.SQLException;

public class ProductsHandler {
	
	ArrayList<Product> productList;
	String [][] stringProductList;
	AbstractFactory Fact = new FactoryProductDB();
	
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
		private void getProductsList(String nickname)
		{	
			if (productList==null)
			{
				productList = Fact.createProductList(nickname);
			}
		}
		
		private void refreshProductsList(String nickname)
		{	
				productList = Fact.createProductList(nickname);
		}
		
		public String[][] getStringProductList(String nickname)
		{
			getProductsList(nickname);
			stringProductList= new String [productList.size()][2];
			
			if (productList != null)
			{
				for (Integer i=0; i<productList.size(); i++)
				{
					stringProductList[i][0]= productList.get(i).pdt_name;
					stringProductList[i][1]= productList.get(i).briefDesc;
				}
			}
			return stringProductList;
			
		}


		public void deleteProduct(String pdt_product, String nickname) throws UserNotInTheDatabaseException , UserDeletedException 
		{

				Fact.getProduct(pdt_product, nickname);	
				Fact.deleteProduct(pdt_product, nickname);
				refreshProductsList(nickname);
				throw new UserDeletedException(pdt_product);		
		}
		

}
