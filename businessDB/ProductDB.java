package businessDB;
import java.util.ArrayList;

import business.Product;
import exceptions.ObjectNotInTheDatabaseException;
import jdbc.JDBConnectionProduct;

public class ProductDB extends Product{
	
	
	//TU L'AS TON FICHIER LA CONNARD ???? 
	static JDBConnectionProduct jdbc = JDBConnectionProduct.getJDBCP();
	
	public ProductDB(String pdtName)
	{
		this.pdt_name = null;
		this.price = null;
		this.id_category =null;
		this.quantity = null;
		this.user_nickname = null;
	}
	
	ProductDB(String nickname, String pdtName, Integer pdtQuantity, Integer pdtPrice, String briefDesc, String longDesc)
	{
		this.pdt_name = pdtName;
		this.price = pdtPrice;
		this.id_category =null;
		this.quantity = pdtQuantity;
		this.user_nickname = nickname;
		this.briefDesc = briefDesc;
		this.longDesc = longDesc;
		
		
	}
	
	
	/**
	 * This method is used when a user wants to add a Goal to his list of Goals.
	 * 
	 * @param nickname, pdt_name, pdt_quantity, pdt_price, pdt_briefDesc, pdt_longDesc
	 */
	public static void addProduct(String nickname, String pdt_name, Integer pdt_quantity, Integer pdt_price, String pdt_briefDesc, String pdt_longDesc)
	{
		jdbc.createProduct(nickname,pdt_name,pdt_quantity,pdt_price,pdt_briefDesc,pdt_longDesc);
	}

	/**
	 * This method returns a product.
	 * It retrieves all the information of the Product of the seller from the Database.
	 * 
	 * @param nickname, pdtName
	 * @return      Product
	 * @throws ObjectNotInTheDatabaseException
	 */
	public static Product getProduct(String nickname ,String pdtName) throws ObjectNotInTheDatabaseException
	{
		Product product = null;

			product = jdbc.getProduct(nickname, pdtName);

		
		return product;
	}
	
	/**
	 * This method creates a Product List.
	 * It retrieves all the Products of the seller from the Database.
	 * 
	 * @param nickname
	 * @return      {@link ArrayList} of Product
	 */
	static public ArrayList<Product> createProductList(String nickname)
	{
		return jdbc.getAllProducts(nickname);
	}

	/**
	 * This method is used when a user wants to delete a Product from his list of Products.
	 * 
	 * 
	 * @param pdt_name, nicknameUser
	 */
	public static void deleteProduct(String pdt_product, String nickname) 
	{
		
		jdbc.deleteProduct(pdt_product, nickname);
		
	}
}
