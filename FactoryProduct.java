import java.util.ArrayList;

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

	public User getUser(String nickname) throws ObjectNotInTheDatabaseException {
		// TODO Auto-generated method stub
		return null;
	}
	public void deleteUser(String nick) {
		// TODO Auto-generated method stub
		
	}
	public void addUser(String nickname, String password, String email) {
		// TODO Auto-generated method stub
		
	}
	public void modifyUser(String nick, String pass, String email, String firstname, String lastname, String city,
			String street, String postalcode, String streetnumber) {
		// TODO Auto-generated method stub
		
	}


	public Product getProduct(String pdtName, String nickname) 
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
