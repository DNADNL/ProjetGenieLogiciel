import java.util.ArrayList;

public class ProductsHandler {

	Product product_selected;
	ArrayList<Product> productList;
	String [][] stringProductList;

	//Get the Factory
	AbstractFactoryProduct Fact = new FactoryProduct();

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


	//M�thodes

	//Get the product list for the seller
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

	//Get the product for the seller and set the "product_selected"
	public void getProduct(String pdt_name, String nickname) throws ObjectNotInTheDatabaseException
	{
		product_selected = Fact.getProduct(pdt_name, nickname);
	}

	//Delete a product from the database
	public void deleteProduct(String pdt_product, String nickname) throws ObjectNotInTheDatabaseException , ObjectDeletedException 
	{
		Fact.deleteProduct(pdt_product, nickname);
		refreshProductsList(nickname);
		throw new ObjectDeletedException(pdt_product);		
	}

	//Add a product to the Database
	public void addProduct(String nickname, String pdt_name, Integer pdt_quantity, Integer pdt_price,
			String pdt_briefDesc, String pdt_longDesc) {

		Fact.addProduct(nickname,pdt_name,pdt_quantity,pdt_price,pdt_briefDesc, pdt_longDesc);
		refreshProductsList(nickname);
	}


	public void verifyAddProductFields(String pdt_name, String pdt_briefDesc, String pdt_longDesc,
			String stringPdt_quantity, String stringPdt_price) throws EmptyFieldsException, NotExpectedValueException{
		String s ="";
		if ( s.equals(pdt_name) || s.equals(pdt_briefDesc) || s.equals(pdt_longDesc) || s.equals(stringPdt_quantity) || s.equals(stringPdt_price) )
		{
			throw new EmptyFieldsException();
		}
		// I do this to have only one catch that handles the two cases in AddProductView
		try
		{
			Integer.parseInt(stringPdt_quantity);
		}
		catch(NumberFormatException e)
		{
			throw new NotExpectedValueException("Quantity");
		}
		try
		{
			Integer.parseInt(stringPdt_price);
		}
		catch(NumberFormatException e)
		{
			throw new NotExpectedValueException("Price");
		}
		
	}


	public void verifyAlreadyExists(String nickname, String pdt_name) throws ObjectNotInTheDatabaseException, ObjectAlreadyExistsException
	{		
		Fact.getProduct(pdt_name, nickname);
		throw new ObjectAlreadyExistsException(pdt_name);
	}


	public void deleteAllCurrentInfos() {
		// TODO Auto-generated method stub
		product_selected = null;
		productList = null;
		stringProductList = null;
	}


}
