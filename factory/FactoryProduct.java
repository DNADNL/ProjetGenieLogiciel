package factory;
import java.util.ArrayList;

import business.Product;
import businessDB.ProductDB;
import exceptions.*;

public class FactoryProduct extends AbstractFactoryProduct{
	
	Product createdProduct;
	ArrayList<Product> createdProductList;
	
	public Product createProduct(String pdtName)
	{
		this.createdProduct = new ProductDB(pdtName);
		return createdProduct;
	}
	
	public ArrayList<Product> createProductList(String nickname)
	{
		createdProductList = ProductDB.createProductList(nickname);
		return createdProductList;
	}

	public Product getProduct(String pdtName, String nickname) throws ObjectNotInTheDatabaseException
	{
		return ProductDB.getProduct(nickname, pdtName);
	}


	public void deleteProduct(String pdt_product, String nickname) 
	{
		ProductDB.deleteProduct(pdt_product, nickname);
	}
	
	public void addProduct(String nickname, String pdt_name, Integer pdt_quantity, Integer pdt_price,
			String pdt_briefDesc, String pdt_longDesc) 
	{
		ProductDB.addProduct(nickname, pdt_name, pdt_quantity, pdt_price, pdt_briefDesc, pdt_longDesc);
		
	}

}
