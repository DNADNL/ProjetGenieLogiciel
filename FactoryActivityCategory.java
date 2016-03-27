import java.util.ArrayList;

public class FactoryActivityCategory extends AbstractFactory {

	CategoryActivity createdActivityCategory;
	ArrayList<CategoryActivity> createdActivityCategoryList;
	
	
	@Override
	public User getUser(String nickname) throws UserNotInTheDatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(String nickname, String password, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String nick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyUser(String nick, String pass, String email, String firstname, String lastname, String city,
			String street, String postalcode, String streetnumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Product> createProductList(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getProduct(String pdt_product, String nickname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(String pdt_product, String nickname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<CategoryActivity> createCategoryActivityList(String nickname) {
		createdActivityCategoryList = CategoryActivityDB.(nickname);
		return createdActivityCategoryList;
	}
	public getCategoryActivity(String title){
		
	}

}
