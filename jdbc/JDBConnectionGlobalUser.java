package jdbc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import business.User;
import businessDB.UserDB;
import exceptions.ObjectNotInTheDatabaseException;


public class JDBConnectionGlobalUser {

	static Connection conn;

	// Singleton Constructor
	private JDBConnectionGlobalUser()
	{
		conn = JDBConnectionOpen.conn;
	}

	// Singleton Initialisator
	private static JDBConnectionGlobalUser singleton;

	// Singleton Accessor
	public static JDBConnectionGlobalUser getJDBCGU()
	{
		if (singleton == null)
		{ 	
			singleton = new JDBConnectionGlobalUser();	
		}
		return singleton;
	}

	// User Methods
	/**
	 * This method is used when a user wants to log in the app (via the Login View).
	 * It checks the user is registered in the database (thanks to its nickname).
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), {@link User}
	 * @return      void
	 * If the user is located in the database, then this method returns the user, 
	 * else an {@link ObjectNotInTheDatabaseException} is returned.
	 * @exception	SQLException
	 * @throws 		ObjectNotInTheDatabaseException
	 */
	public void verifyLoginUser(User user, String nickname) throws ObjectNotInTheDatabaseException
	{

		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we get the result in the ResultSet object)
			ResultSet result = state.executeQuery("SELECT nickname, password FROM public.\"global_user\" WHERE nickname = \'" + nickname + "\'" ); 

			while(result.next())
			{
				// We get the nickname
				user.nicknameUser = result.getObject(1).toString();
				// We get the password
				user.mdpUser = result.getObject(2).toString();
			}

			// We close everything
			result.close();
			state.close();

			if (user.nicknameUser == null) // If the nickname is null, so the user isn't in the DB
			{
				throw new ObjectNotInTheDatabaseException(nickname);
			}

		} 
		catch (SQLException e) 
		{
			System.out.println("ERROR - JDBConnectionGlobalUser.getUser() / : SQL Query Error (SQLException)");
		}
	}

	/**
	 * This method is used when a user wants to get its data, for example if it wants to modify them.
	 * It creates a user and fill all its data which are in the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 * @return      {@link User}
	 * @exception	SQLException
	 */
	public User getUserData(String nickname)
	{
		User user = new UserDB();

		try
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we get the result in the ResultSet object)
			ResultSet result = state.executeQuery("SELECT * FROM public.\"global_user\" WHERE nickname = \'" + nickname + "\'" ); 

			// We get the user data
			while(result.next())
			{
				
				user.nicknameUser = result.getObject(1).toString();
				user.mdpUser = result.getObject(2).toString();
				user.firstNameUser = result.getObject(3).toString();
				user.lastNameUser = result.getObject(4).toString();
				user.cityUser = result.getObject(5).toString();
				user.streetUser = result.getObject(6).toString();
				user.postalCodeUser = result.getObject(7).toString();
				user.streetNumberUser = result.getObject(8).toString();
				user.emailUser = result.getObject(9).toString();
			}

			// We close everything
			result.close();
			state.close();

		} 
		catch (SQLException e) 
		{
			System.out.println("ERROR - JDBConnectionGlobalUser.getUser() / : SQL Query Error (SQLException)");
		}

		return user;
	}

	/**
	 * This method is used when an admin registers a user.
	 * It creates the user into the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), password (a {@link String} giving the password of the user), e-mail (a {@link String} giving the e-mail of the user)
	 * @return      void
	 * If the user is located in the database, then this method returns the user, 
	 * else an {@link ObjectNotInTheDatabaseException} is returned.
	 * @exception	SQLException
	 */
	public void createUser(String nickname, String password, String email)
	{
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we don't have a result to get, here)
			state.executeQuery("INSERT INTO public.\"global_user\" VALUES (\'"  + nickname + "\', \'" + password + "\',\'\',\'\',\'\',\'\',\'\',\'\', \'" + email + "\');");   
			
			// We close everything
			state.close();

		} 
		catch (SQLException e) {}
	}

	/**
	 * This method is used when an admin deletes a user.
	 * It deletes the user from the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 * @return      void
	 * @exception	SQLException
	 */
	public void deleteUser(String nickname)
	{
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we don't have a result to get, here)
			state.executeQuery("DELETE FROM public.\"global_user\" WHERE nickname = \'"  + nickname + "\';");   
			
			// We close everything
			state.close();

		} 
		catch (SQLException e) {}
	}

	/**
	 * This method is used when an admin modifies a user or when a user wants to modify its data.
	 * It modifies the user data into the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), 
	 * 				password (a {@link String} giving the password of the user), 
	 * 				e-mail (a {@link String} giving the e-mail of the user), 
	 * 				first name (a {@link String} giving the first name of the user), 
	 * 				last name (a {@link String} giving the last name of the user), 
	 * 				city (a {@link String} giving the city of the user), 
	 * 				street (a {@link String} giving the street of the user), 
	 * 				postal code (a {@link String} giving the postal code of the user), 
	 * 				street number (a {@link String} giving the street number of the user)
	 * @return      void
	 * @exception	SQLException
	 */
	public void modifyUser(String nick, String pass, String email, String firstname,
			String lastname, String city,String street,String postalcode,String streetnumber){
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we don't have a result to get, here)
			state.executeQuery("UPDATE public.\"global_user\" SET password=\'"  + pass + "\', mail=\'"  + email + "\', firstname=\'"  + firstname + "\', lastname=\'"  + lastname + "\', city=\'"  + city + "\', street=\'"  + street + "\', postalcode=\'"  + postalcode + "\', streetnumber=\'"  + streetnumber + "\' WHERE nickname=\'"  + nick + "\';");   
			
			// We close everything
			state.close();   
		} 
		catch (SQLException e) {}
	}

	/**
	 * This method is used to know if a user is an admin.
	 * It seeks the user into the "admin" table in the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), 
	 * @return      {@link boolean} (true if the user is an admin, else false)
	 * @exception	SQLException
	 */
	public boolean isAdmin(String nickname)
	{

		boolean isAnAdmin = false;
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we get the result in the ResultSet object)
			ResultSet result = state.executeQuery("SELECT * FROM public.\"admin\" WHERE nickname = \'" + nickname + "\';"); 

			while(result.next())
			{
				if (nickname.equals(result.getObject(1).toString())) // If the nickname is in the "admin" table, then the user is an Admin
				{
					isAnAdmin = true;
				}
			}

			// We close everything
			result.close();
			state.close();

		} 
		catch (SQLException e) 
		{
			System.out.println("ERROR - JDBConnectionGlobalUser.getUser() / : SQL Query Error (SQLException)");
		}

		return isAnAdmin;
	}

	/**
	 * This method is used to know if a user is a simple user.
	 * It seeks the user into the "simple_user" table in the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), 
	 * @return      {@link boolean} (true if the user is a simple user, else false)
	 * @exception	SQLException
	 */
	public boolean isSimpleUser(String nickname)
	{

		boolean isASimpleUser = false;
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			// Query (we get the result in the ResultSet object)
			ResultSet result = state.executeQuery("SELECT * FROM public.\"simple_user\" WHERE nickname = \'" + nickname + "\';"); 

			while(result.next())
			{
				if (nickname.equals(result.getObject(1).toString())) // If the nickname is in the "simple_user" table, then the user is a Simple User
				{
					isASimpleUser = true;
				}
			}

			// We close everything
			result.close();
			state.close();

		} 
		catch (SQLException e) 
		{
			System.out.println("ERROR - JDBConnectionGlobalUser.getUser() / : SQL Query Error (SQLException)");
		}

		return isASimpleUser;
	}

	/**
	 * This method is used to know if a user is a seller.
	 * It seeks the user into the "seller" table in the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), 
	 * @return      {@link boolean} (true if the user is a seller, else false)
	 * @exception	SQLException
	 */
	public boolean isSeller(String nickname)
	{

		boolean isASeller = false;
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			// Query (we get the result in the ResultSet object)
			ResultSet result = state.executeQuery("SELECT * FROM public.\"seller\" WHERE nickname = \'" + nickname + "\';"); 

			while(result.next())
			{
				if (nickname.equals(result.getObject(3).toString())) // If the nickname is in the "seller" table, then the user is a Seller
				{
					isASeller = true;
				}
			}
			
			// We close everything
			result.close();
			state.close();

		} 
		catch (SQLException e) 
		{
			System.out.println("ERROR - JDBConnectionGlobalUser.getUser() / : SQL Query Error (SQLException)");
		}

		return isASeller;
	}

	/**
	 * This method is used to add the seller role to a user.
	 * It adds the user into the "seller" table in the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), 
	 * @return      void
	 * @exception	SQLException
	 */
	public void addUserRoleSeller(String nickname)
	{
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we don't have a result to get, here)
			state.executeQuery("INSERT INTO public.\"seller\" VALUES (null, null, \'"  + nickname + "\');"); 
			
			// We close everything
			state.close();         
		} 
		catch (SQLException e) {}
	}

	/**
	 * This method is used to add the simple user role to a user.
	 * It adds the user into the "simple_user" table in the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), 
	 * @return      void
	 * @exception	SQLException
	 */
	public void addUserRoleSimpleUser(String nickname)
	{
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we don't have a result to get, here)
			state.executeQuery("INSERT INTO public.\"simple_user\" VALUES (\'"  + nickname + "\');");   
			
			// We close everything
			state.close();         
		} 
		catch (SQLException e) {}
	}

	/**
	 * This method is used to add the admin role to a user.
	 * It adds the user into the "admin" table in the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), 
	 * @return      void
	 * @exception	SQLException
	 */
	public void addUserRoleAdmin(String nickname)
	{
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we don't have a result to get, here)
			state.executeQuery("INSERT INTO public.\"admin\" VALUES (\'"  + nickname + "\');");   
			
			// We close everything
			state.close();         
		} 
		catch (SQLException e) {}
	}

	/**
	 * This method is used to delete the seller role from a user.
	 * It deletes the user from the "seller" table in the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), 
	 * @return      void
	 * @exception	SQLException
	 */
	public void deleteUserRoleSeller(String nickname)
	{
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we don't have a result to get, here)
			state.executeQuery("DELETE FROM public.\"seller\" WHERE nickname = \'"  + nickname + "\';");
			
			// We close everything
			state.close();         
		} 
		catch (SQLException e) {}
	}

	/**
	 * This method is used to delete the simple user role from a user.
	 * It deletes the user from the "simple_user" table in the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), 
	 * @return      void
	 * @exception	SQLException
	 */
	public void deleteUserRoleSimpleUser(String nickname)
	{
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we don't have a result to get, here)
			state.executeQuery("DELETE FROM public.\"simple_user\" WHERE nickname = \'"  + nickname + "\';");
			
			// We close everything
			state.close();         
		} 
		catch (SQLException e) {}
	}

	/**
	 * This method is used to delete the admin role from a user.
	 * It deletes the user from the "admin" table in the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), 
	 * @return      void
	 * @exception	SQLException
	 */
	public void deleteUserRoleAdmin(String nickname)
	{
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we don't have a result to get, here)
			state.executeQuery("DELETE FROM public.\"admin\" WHERE nickname = \'"  + nickname + "\';");
			
			// We close everything
			state.close();         
		} 
		catch (SQLException e) {}
	}
}