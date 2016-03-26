import java.sql.SQLException;

public class FactoryProductDB extends AbstractFactory{
	
	Product createdProduct;
	Product[][] createdProductList;
	
	public Product createProduct(String pdtName)
	{
		this.createdProduct = new ProductBD(pdtName);
		return createdProduct;
	}
	
	public Product[][] createProductList(String nickname)
	{
		String[][] stringProductList = ProductBD.jdbc.getAllProducts(nickname);
		return createdProductList;
	}

	public User getUser(String nickname) throws UserNotInTheDatabaseException {
		// TODO Auto-generated method stub
		return null;
	}
	public void deleteUser(String nick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUser(String nickname, String password, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyUser(String nick, String pass, String email, String firstname, String lastname, String city,
			String street, String postalcode, String streetnumber) {
		// TODO Auto-generated method stub
		
	}
	
	

}
