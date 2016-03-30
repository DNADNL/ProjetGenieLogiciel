import java.util.ArrayList;

public class FactoryProductDB extends AbstractFactory{
	
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

	@Override

	public ArrayList<ActivityCategory> createCategoryActivityList() {
		return null;
	}

	public void addGoal(String goal_title, String goal_description, String nick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserData(String nickname) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void suggestActivityCategory(String title, String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getCategoryActivity(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAdmin(String nick) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSimpleUser(String nick) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSeller(String nick) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addUserRoleSeller(String nickname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUserRoleSimpleUser(String nickname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUserRoleAdmin(String nickname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserRoleSeller(String nickname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserRoleSimpleUser(String nickname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserRoleAdmin(String nickname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Goal> createGoalList(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ActivityCategory> createCategoryActivitySuggestionList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ActivityCategory> createActivityCategoryList(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGoal(String goal_name, String nicknameUser) {
		// TODO Auto-generated method stub
		
	}

	


	
	

}
