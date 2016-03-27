import java.sql.SQLException;
import java.util.ArrayList;


//test cl�ment

public abstract class AbstractFactory 
{

	abstract public User getUser(String nickname) throws UserNotInTheDatabaseException;
	abstract public void addUser(String nickname, String password, String email);
	abstract public void deleteUser(String nick);
	abstract public void modifyUser(String nick, String pass, String email, String firstname, String lastname, String city,
			String street, String postalcode, String streetnumber);
	abstract public ArrayList<Product> createProductList(String nickname);
	abstract public void getProduct(String pdt_product, String nickname);
	abstract public void deleteProduct(String pdt_product, String nickname);
	abstract public ArrayList<CategoryActivity> createCategoryActivityList(String title);
	abstract public void suggestActivityCategory(String title,String description);
	abstract public void getCategoryActivity(String title);
	
}
