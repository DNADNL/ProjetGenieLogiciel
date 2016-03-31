package factory;
import java.util.ArrayList;

import business.Product;
import businessDB.ProductDB;
import exceptions.*;

public class FactoryProduct extends AbstractFactoryProduct{
	
	Product createdProduct;
	ArrayList<Product> createdProductList;
	
	/**
	 * This method creates a Product. 
	 * The product name argument specifies the product and must be a {@link String}.
	 *
	 * @param  	product name	(a {@link String} giving the name of the Product)
	 * @return {@link Product}
	 */
	public Product createProduct(String pdtName)
	{
		this.createdProduct = new ProductDB(pdtName);
		return createdProduct;
	}
	
	/**
	 * This method creates a Products List owned by a Seller. 
	 * The nickname argument specifies the seller and must be a {@link String}.
	 *
	 * @param  	nickname	(a {@link String} giving the nickname of the Seller)
	 * @return {@link ArrayList} of Products
	 */
	public ArrayList<Product> createProductList(String nickname)
	{
		createdProductList = ProductDB.createProductList(nickname);
		return createdProductList;
	}

	/**
	 * This method gets a product from a Seller. 
	 * The nickname/product name argument specifies the seller/product and must be a {@link String}.
	 *
	 * @param  	product name (a {@link String} giving the name of the Product)
	 * 			nickname	(a {@link String} giving the nickname of the Seller)
	 * @return 	{@link Product}
	 * @throws	ObjectNotInTheDatabaseException
	 */
	public Product getProduct(String pdtName, String nickname) throws ObjectNotInTheDatabaseException
	{
		return ProductDB.getProduct(nickname, pdtName);
	}


	/**
	 * This method deletes a product of a Seller. 
	 * It deletes The product from the seller Products List and then deletes the product from the database.
	 * The nickname/product name argument specifies the seller/product and must be a {@link String}.
	 *
	 * @param  	product name (a {@link String} giving the name of the Product)
	 * 			nickname	(a {@link String} giving the nickname of the Seller)
	 */
	public void deleteProduct(String pdt_product, String nickname) 
	{
		ProductDB.deleteProduct(pdt_product, nickname);
	}
	
	/**
	 * This method adds a product of a Seller. 
	 * It adds The product into the seller Products List and adds the product in the database.
	 * The nickname/product name argument specifies the seller/product and must be a {@link String}.
	 *
	 * @param		nickname	(a {@link String} giving the nickname of the Seller),
	 * 				product name (a {@link String} giving the name of the Product),
	 * 				product quantity (a {@link Integer} giving the quantity of the Product),
	 * 				product price (a {@link Integer} giving the price of the Product),
	 * 				product brief description (a {@link String} giving the brief description of the Product),
	 * 				product long description (a {@link String} giving the long description of the Product)
	 */
	public void addProduct(String nickname, String pdt_name, Integer pdt_quantity, Integer pdt_price,
			String pdt_briefDesc, String pdt_longDesc) 
	{
		ProductDB.addProduct(nickname, pdt_name, pdt_quantity, pdt_price, pdt_briefDesc, pdt_longDesc);
	}

}
