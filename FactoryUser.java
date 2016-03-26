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
	public void getProduct(String pdt_product, String nickname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(String pdt_product, String nickname) {
	
	}

	@Override
	public void addGoal(String goal_title, String goal_description, String nick) {
		// TODO Auto-generated method stub
		
	}

	

}
