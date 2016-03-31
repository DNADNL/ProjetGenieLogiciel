package handlers;
import java.util.ArrayList;

import business.Product;
import business.User;
import exceptions.EmptyFieldsException;
import exceptions.NotExpectedValueException;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectCreatedException;
import exceptions.ObjectDeletedException;
import exceptions.ObjectNotInTheDatabaseException;
import factory.AbstractFactoryProduct;
import factory.FactoryProduct;

public class ProductsHandler {

	public Product product_selected;
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


	//Méthodes

	//Get the product list for the seller
	/**
	 * This method is used when a user log in the system. It loads the productList from the database.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 */
	private void getProductsList(String nickname)
	{	
		if (productList==null)
		{
			productList = Fact.createProductList(nickname);
		}
	}

	//Refreshing the product list
	/**
	 * This method is used when a modification occurs on the productList. It loads the productList from the database.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 */
	private void refreshProductsList(String nickname)
	{	
		productList = Fact.createProductList(nickname);
	}

	//Create a StringProductList from the product list
	/**
	 * This method is used when a seller log in his main page. 
	 * It gets the needed informations for the SellerView from the productList and modifies their types to {@link String}
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 * @return      {@link String} 2D Table
	 * It checks if the productList is empty before filling the StringGoalList.
	 */
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
	/**
	 * This method is used when a seller wants to delete or show one of his products.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), {@link User}
	 * If the product does not exist in the database, then this method returns an {@link ObjectNotInTheDatabaseException}, 
	 * else product_selected is updated.
	 * @throws 		ObjectNotInTheDatabaseException
	 */
	public void getProduct(String pdt_name, String nickname) throws ObjectNotInTheDatabaseException
	{
		product_selected = Fact.getProduct(pdt_name, nickname);
	}

	//Delete a product from the database
	/**
	 * This method is used when a user wants to delete one of his products (via DeleteProductView).
	 * It checks the product is registered in the database (thanks to its pdt_product).
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), {@link User}
	 * If the product does not exist in the database, then this method returns an {@link ObjectNotInTheDatabaseException}, 
	 * else an {@link ObjectDeletedException} is returned, the product is deleted from the database and the productList, and the productList is refreshed.
	 * @throws 		ObjectNotInTheDatabaseException
	 */
	public void deleteProduct(String pdt_product, String nickname) throws ObjectNotInTheDatabaseException , ObjectDeletedException 
	{
		Fact.deleteProduct(pdt_product, nickname);
		refreshProductsList(nickname);
		throw new ObjectDeletedException(pdt_product);		
	}

	//Add a product to the Database
	/**
	 * This method is used when a user wants to add a product to his productList (via the AddProductView).
	 * 
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), pdt_name, pdt_quantity, pdt_price, pdt_briefDesc, pdt_longDesc
	 * If the product is already in the database, then this method returns an {@link ObjectAlreadyExistsException}, 
	 * else an {@link ObjectCreatedException} is returned, the product is added to the database and to the productList, and the productList is refreshed.
	 * @throws 		ObjectCreatedException, ObjectAlreadyExistsException
	 */
	public void addProduct(String nickname, String pdt_name, Integer pdt_quantity, Integer pdt_price,
			String pdt_briefDesc, String pdt_longDesc) {

		Fact.addProduct(nickname,pdt_name,pdt_quantity,pdt_price,pdt_briefDesc, pdt_longDesc);
		refreshProductsList(nickname);
	}

	/**
	 * This method is used by the addProduct method.
	 * It checks the fields required to add a product.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), goal_title, goal_description
	 * If one of the fields is empty, then this method returns an {@link EmptyFieldsException}
	 * If one of the "Integer" fields does not contain an Integer, then this method returns an {@link NotExpectedValueException}
	 * @throws 		EmptyFieldsException, NotExpectedValueException
	 * @exception {@link NumberFormatException}
	 */
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

	/**
	 * This method is used by the addProduct method.
	 * It checks if the product is already registered in the database.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), goal_title, goal_description
	 * If the product is already in the database, then this method returns an {@link ObjectAlreadyExistsException}, 
	 * else an {@link ObjectCreatedException} is returned, the product is added to the database and to the productList, and the productList is refreshed.
	 * @throws 		ObjectCreatedException, ObjectAlreadyExistsException
	 */
	public void verifyAlreadyExists(String nickname, String pdt_name) throws ObjectNotInTheDatabaseException, ObjectAlreadyExistsException
	{		
		Fact.getProduct(pdt_name, nickname);
		throw new ObjectAlreadyExistsException(pdt_name);
	}

	/**
	 * This method is used when a user disconnects.
	 * It deletes all the current informations from product_selected, productList, stringProductList
	 * <p>
	 *
	 */
	public void deleteAllCurrentInfos() {
		// TODO Auto-generated method stub
		product_selected = null;
		productList = null;
		stringProductList = null;
	}


}
