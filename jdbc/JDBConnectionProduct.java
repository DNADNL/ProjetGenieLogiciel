package jdbc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import business.Product;
import businessDB.ProductDB;
import exceptions.*;


public class JDBConnectionProduct {

	static Connection conn;

	// Singleton Constructor
	private JDBConnectionProduct()
	{
		conn = JDBConnectionOpen.conn;
	}

	// Singleton Initialisator
	private static JDBConnectionProduct singleton;

	// Singleton Accessor
	public static JDBConnectionProduct getJDBCP()
	{
		if (singleton == null)
		{ 	
			singleton = new JDBConnectionProduct();	
		}
		return singleton;
	}

	// Product Methods
	/**
	 * This method is used when a seller wants to see its products list.
	 * It gets all the products from the seller into the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), 
	 * @return      {@link ArrayList} of Products
	 * @exception	SQLException
	 */
	public ArrayList<Product> getAllProducts(String nickname)
	{
		ArrayList<Product> productList = new ArrayList<Product>();
		//Product productList[] = null;

		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();

			// Query (we get the result in the ResultSet object)
			ResultSet result = state.executeQuery("SELECT * FROM public.\"product\" WHERE nickname = \'" + nickname + "\'"); 

			// We get All Products data
			Integer x=0;
			while(result.next())
			{
				Product product = new ProductDB(nickname);

				product.pdt_name=result.getObject(2).toString();



				product.quantity=Integer.parseInt(result.getObject(3).toString());

				product.price= Integer.parseInt(result.getObject(4).toString());

				product.user_nickname= result.getObject(5).toString();

				//product.id_category= Integer.parseInt(result.getObject(6).toString());

				product.briefDesc=result.getObject(7).toString();
				
				product.longDesc=result.getObject(8).toString();

				productList.add(product);

				x++;

			}

			// We close everything
			result.close();
			state.close();

		} 
		catch (SQLException e) 
		{
			System.out.println("ERROR - JDBConnectionProduct.getAllProducts() / : SQL Query Error (SQLException)");
		}

		return productList;
	}

	/**
	 * This method is used when a seller wants to get a product of its products list.
	 * It gets the product wanted by the seller from the database (thanks to the seller's nickname and the product's name).
	 * The nickname/product name argument specifies the user/product and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), 
	 * 				product name (a {@link String} giving the name of the product)
	 * @return      {@link ArrayList} of Products
	 * @throws		ObjectNotInTheDatabaseException
	 * @exception	SQLException
	 */
	public Product getProduct(String nickname, String pdtName) throws ObjectNotInTheDatabaseException
	{
		Product product = new ProductDB(pdtName);

		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();

			// Query (we get the result in the ResultSet object)
			ResultSet result = state.executeQuery("SELECT * FROM public.\"product\" WHERE nickname = \'" + nickname + "\' AND product_name =\'" + pdtName + "\'" ); 

		
			// We get All Product data
			if(!result.next())
			{
				throw new ObjectNotInTheDatabaseException(pdtName);
			}
			else
			{

					System.out.println(result.getObject(2).toString());
					product.pdt_name=result.getObject(2).toString();

					product.quantity=Integer.parseInt(result.getObject(3).toString());

					product.price= Integer.parseInt(result.getObject(4).toString());

					product.user_nickname= result.getObject(5).toString();
					
					//product.id_category= Integer.parseInt(result.getObject(6).toString());
					
					product.briefDesc=result.getObject(7).toString();

					product.longDesc=result.getObject(8).toString();		
			}
			result.close();
			state.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("ERROR - JDBConnectionProduct.getProduct() / : SQL Query Error (SQLException)");
		}

		System.out.println(product.pdt_name);
		return product;
		
	}

	/**
	 * This method is used when a seller wants to create a product and add it to its products list.
	 * It creates the seller product into the database (thanks to the seller's nickname and the product's name).
	 * The nickname/product name argument specifies the user/product and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the seller), 
	 * 				product name (a {@link String} giving the name of the product),
	 * 				product quantity (an {@link Integer} giving the product quantity in stocks),
	 * 				product price (an {@link Integer} giving the product price),
	 * 				product brief description (a {@link String} giving the brief description of the product),
	 * 				product long description (a {@link String} giving the long description of the product),
	 * @return      void
	 * @exception	SQLException
	 */
	public void createProduct(String nickname, String pdt_name, Integer pdt_quantity, Integer pdt_price, String pdt_briefDesc, String pdt_longDesc)
	{
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we don't have a result to get, here)
			state.executeQuery("INSERT INTO public.\"product\" (product_name, quantity, price, nickname, brief_desc, long_desc) VALUES (\'"  + pdt_name + "\', \'" + pdt_quantity + "\', \'"  + pdt_price + "\', \'"  + nickname + "\', \'"  + pdt_briefDesc + "\', \'" + pdt_longDesc + "\')");   

			// We close everything
			state.close();

		} 
		catch (SQLException e) {}
	}

	/**
	 * This method is used when a seller wants to delete a product from its products list.
	 * It deletes the seller product from the database (thanks to the seller's nickname and the product's name).
	 * The nickname/product name argument specifies the user/product and must be a {@link String}.
	 * <p>
	 *
	 * @param  		product name (a {@link String} giving the name of the product),
	 * 				nickname (a {@link String} giving the nickname of the seller), 
	 * @return      void
	 * @exception	SQLException
	 */
	public void deleteProduct(String pdt_name, String nickname)
	{
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we don't have a result to get, here)
			state.executeQuery("DELETE FROM public.\"product\" WHERE product_name=\'"  + pdt_name + "\' AND nickname =\'"  + nickname + "\'");   
			
			// We close everything
			state.close();

		} 
		catch (SQLException e) {}
	}
}
