import java.sql.SQLException;
import java.util.ArrayList;

public class FactoryProductDB extends AbstractFactory{
	
	Product createdProduct;
	ArrayList<Product> createdProductList;
	
	public Product createProduct(String pdtName)
	{
		this.createdProduct = new ProductBD(pdtName);
		return createdProduct;
	}
	
	public ArrayList<Product> createProductList(String nickname)
	{
		createdProductList = ProductBD.createProductList(nickname);
		return createdProductList;
	}

	public User getUser(String nickname) throws UserNotInTheDatabaseException {
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


	public void getProduct(String pdt_product, String nickname) 
	{

	}


	public void deleteProduct(String pdt_product, String nickname) 
	{
		ProductBD.deleteProduct(pdt_product, nickname);
	}

	@Override
	public void addGoal(String goal_title, String goal_description, String nick) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
