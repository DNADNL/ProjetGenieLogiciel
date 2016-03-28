import java.util.ArrayList;

public class FactoryUser extends AbstractFactory {
	
	User user;
	
	/**
	 * This method is used when an user wants to log in the app (via the Login View).
	 * Creates a log instance of the user if the user is registered in the database. 
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  nickname  a String giving the name of the user
	 * @return      a User. 
	 * If the user is located in the database, then this method returns the user, 
	 * else a {@link null} user is returned.
	 * @throws UserNotInTheDatabaseException 
	 */
	
	public User getUser(String nickname) throws UserNotInTheDatabaseException
	{
		this.user = new UserBD(nickname);
		return user;
	}
	
	public User getUserData(String nick)
	{
		return UserBD.getUserData(nick);
	}
	
	public void addUser(String nick, String pass, String email)
	{
		UserBD.addUser(nick, pass, email);		
	}
	
	public void modifyUser(String nick, String pass, String email, String firstname,
				String lastname, String city,String street,String postalcode,String streetnumber){
		
		UserBD.modifyUser(nick, pass, email, firstname, lastname, city, street, postalcode, streetnumber);
	}
	
	public void deleteUser(String nick)
	{
		UserBD.deleteUser(nick);
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
	
	}

	@Override

	public ArrayList<CategoryActivity> createCategoryActivityList(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addGoal(String goal_title, String goal_description, String nick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suggestActivityCategory(String title, String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getCategoryActivity(String title) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isAdmin(String nick) {
		return UserBD.isAdmin(nick);
	}

	public boolean isSimpleUser(String nick) {
		return UserBD.isSimpleUser(nick);
	}
	
	public boolean isSeller(String nick) {
		return UserBD.isSeller(nick);
	}

	public void addUserRoleSeller(String nickname) {
		UserBD.addUserRoleSeller(nickname);
	}
	public void addUserRoleSimpleUser(String nickname) {
		UserBD.addUserRoleSimpleUser(nickname);
	}
	public void addUserRoleAdmin(String nickname) {
		UserBD.addUserRoleAdmin(nickname);
	}
	public void deleteUserRoleSeller(String nickname) {
		UserBD.deleteUserRoleSeller(nickname);
	}
	public void deleteUserRoleSimpleUser(String nickname) {
		UserBD.deleteUserRoleSimpleUser(nickname);
	}
	public void deleteUserRoleAdmin(String nickname) {
		UserBD.deleteUserRoleAdmin(nickname);
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
}
