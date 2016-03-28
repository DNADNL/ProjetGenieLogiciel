import java.util.ArrayList;
import java.sql.SQLException;

public class ProductsHandler {
	
	Product product_selected;
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
		
		//Get the product list
		private void getProductsList(String nickname)
		{	
			if (productList==null)
			{
				productList = Fact.createProductList(nickname);
			}
		}
		
		//Refreshing the product list
		private void refreshProductsList(String nickname)
		{	
				productList = Fact.createProductList(nickname);
		}
		
		//Create a StringProductList from the product list
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
		
		//Get the product in the selected cell
		public void getProduct(String pdt_name, String nickname)
		{
			product_selected = Fact.getProduct(pdt_name, nickname);
		}


		public void deleteProduct(String pdt_product, String nickname) throws UserNotInTheDatabaseException , UserDeletedException 
		{
				Fact.deleteProduct(pdt_product, nickname);
				refreshProductsList(nickname);
				throw new UserDeletedException(pdt_product);		
		}


		public void addProduct(String nickname, String pdt_name, Integer pdt_quantity, Integer pdt_price,
				String pdt_briefDesc, String pdt_longDesc) {
				
			Fact.addProduct(nickname,pdt_name,pdt_quantity,pdt_price,pdt_briefDesc, pdt_longDesc);
			refreshProductsList(nickname);
		}
		

}
