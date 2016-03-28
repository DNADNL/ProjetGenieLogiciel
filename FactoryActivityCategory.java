import java.util.ArrayList;

public class FactoryActivityCategory extends AbstractFactory {

	ActivityCategory createdActivityCategory;
	ArrayList<ActivityCategory> createdActivityCategoryList;
	
	public ActivityCategory createActivity(String title)
	{
		this.createdActivityCategory = new ActivityCategoryDB();
		return createdActivityCategory;
	}
	
	
	
	@Override
	public User getUser(String nickname) throws ObjectNotInTheDatabaseException {
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
	public Product getProduct(String pdt_product, String nickname) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(String pdt_product, String nickname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ActivityCategory> createCategoryActivityList() {
		createdActivityCategoryList = ActivityCategoryDB.createCategoryActivityList();
		return createdActivityCategoryList;
	}
//	


	@Override
	public User getUserData(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void suggestActivityCategory(String title, String description) {
		// TODO Auto-generated method stub
		ActivityCategoryDB.SuggestCategoryActivity(title, description);
		
	}



	@Override
	public void getCategoryActivity(String title) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void addGoal(String goal_title, String goal_description, String nick) {
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
	public void addProduct(String nickname, String pdt_name, Integer pdt_quantity, Integer pdt_price,
			String pdt_briefDesc, String pdt_longDesc) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public ArrayList<Goal> createGoalList(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ArrayList<ActivityCategory> createCategoryActivityList(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<CategoryActivity> createCategoryActivitySuggestionList() {
		createdActivityCategoryList = CategoryActivityDB.createCategoryActivitySuggestionList();
		return createdActivityCategoryList;
	}

}
