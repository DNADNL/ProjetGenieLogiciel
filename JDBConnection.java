import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;


public class JDBConnection {
	
	Random rand = new Random();
	
	static Connection conn;
	
//Singleton Constructor
	private JDBConnection()
	{
		createConnection();
	}
	
//Singleton Initialisator
	private static JDBConnection singleton;
	
//Singleton Accessor
	public static JDBConnection getJDBC()
	{
		if (singleton == null)
		{ 	
			singleton = new JDBConnection();	
		}
		return singleton;
	}

// Connection Methods
	/**
	 * This method creates the connection to the database.
	 * <p>
	 *
	 * @param		none
	 * @return		void
	 * @exception	ClassNotFoundException, LinkageError, SQLException
	 */
	private void createConnection()
	{
		try 
		{
			// Connection information
			Class.forName("org.postgresql.Driver");
		    System.out.println("Driver O.K.");
		    String url = "jdbc:postgresql://qdjjtnkv.db.elephantsql.com:5432/xchuldjm";
		    String user = "xchuldjm";
		    String passwd = "k_s5Zb_Br9lFxGz4SmfrlPKEmJbTOvY-";

		    // Creation of the link between the program and the database 
		    JDBConnection.conn = DriverManager.getConnection(url, user, passwd);
		    System.out.println("Connected Successfully !");
		} 
		catch (ClassNotFoundException e)
		{
			System.out.println("ERROR - JDBConnection.createConnection() / Class.forName : Classe/Driver Not Found (ClassNotFoundException)");
		}
		catch (LinkageError e)
		{
			System.out.println("ERROR - JDBConnection.createConnection() / Class.forName : Linkage/Initialization failed (LinkageError)");
		}
		catch (SQLException e)
		{
			System.out.println("ERROR - JDBConnection.createConnection() / DriverManager.getconnection : Connection Timeout OR Too Many Connections (SQLException)");
		}
	}

// User Methods
	/**
	 * This method is used when a user wants to log in the app (via the Login View).
	 * It checks the user is registered in the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
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
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT nickname, password FROM public.\"global_user\" WHERE nickname = \'" + nickname + "\'" ); 
			
			while(result.next())
			{
				user.nicknameUser = result.getObject(1).toString();
				user.mdpUser = result.getObject(2).toString();
	    	
				/*  for(int i = 1; i <= resultMeta.getColumnCount(); i++)
	        	{
	          		System.out.print("\t" + result.getObject(i).toString() + "\t |");
	        	}
	        	System.out.println("\n---------------------------------");*/

			}

			result.close();
			state.close();
			
			if (user.nicknameUser == null)
			{
				throw new ObjectNotInTheDatabaseException(nickname);
			}
	         
	    } 
		catch (SQLException e) 
		{
			System.out.println("ERROR - JDBConnection.getUser() / : SQL Query Error (SQLException)");
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
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"global_user\" WHERE nickname = \'" + nickname + "\'" ); 
	        
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

			result.close();
			state.close();

	    } 
		catch (SQLException e) 
		{
			System.out.println("ERROR - JDBConnection.getUser() / : SQL Query Error (SQLException)");
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
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			state.executeQuery("INSERT INTO public.\"global_user\" VALUES (\'"  + nickname + "\', \'" + password + "\',\'\',\'\',\'\',\'\',\'\',\'\', \'" + email + "\');");   
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
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			state.executeQuery("DELETE FROM public.\"global_user\" WHERE nickname = \'"  + nickname + "\';");   
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
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			state.executeQuery("UPDATE public.\"global_user\" SET password=\'"  + pass + "\', mail=\'"  + email + "\', firstname=\'"  + firstname + "\', lastname=\'"  + lastname + "\', city=\'"  + city + "\', street=\'"  + street + "\', postalcode=\'"  + postalcode + "\', streetnumber=\'"  + streetnumber + "\' WHERE nickname=\'"  + nick + "\';");   
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
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"admin\" WHERE nickname = \'" + nickname + "\';"); 
			
			while(result.next())
			{
				if (nickname.equals(result.getObject(1).toString()))
				{
					isAnAdmin = true;
				}
			}

			result.close();
			state.close();
	         
	    } 
		catch (SQLException e) 
		{
			System.out.println("ERROR - JDBConnection.getUser() / : SQL Query Error (SQLException)");
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
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"simple_user\" WHERE nickname = \'" + nickname + "\';"); 
			
			while(result.next())
			{
				if (nickname.equals(result.getObject(1).toString()))
				{
					isASimpleUser = true;
				}
			}

			result.close();
			state.close();
	         
	    } 
		catch (SQLException e) 
		{
			System.out.println("ERREUR - JDBConnection.getUser() / : Requête erronée ou absence de valeur de retour (SQLException)");
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
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"seller\" WHERE nickname = \'" + nickname + "\';"); 
			
			while(result.next())
			{
				if (nickname.equals(result.getObject(3).toString()))
				{
					isASeller = true;
				}
			}

			result.close();
			state.close();
	         
	    } 
		catch (SQLException e) 
		{
			System.out.println("ERREUR - JDBConnection.getUser() / : Requête erronée ou absence de valeur de retour (SQLException)");
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
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			state.executeQuery("INSERT INTO public.\"seller\" VALUES (null, null, \'"  + nickname + "\');"); 
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
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			state.executeQuery("INSERT INTO public.\"simple_user\" VALUES (\'"  + nickname + "\');");   
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
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			state.executeQuery("INSERT INTO public.\"admin\" VALUES (\'"  + nickname + "\');");   
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
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			state.executeQuery("DELETE FROM public.\"seller\" WHERE nickname = \'"  + nickname + "\';");
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
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			state.executeQuery("DELETE FROM public.\"simple_user\" WHERE nickname = \'"  + nickname + "\';");
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
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			state.executeQuery("DELETE FROM public.\"admin\" WHERE nickname = \'"  + nickname + "\';");
			state.close();         
	    } 
		catch (SQLException e) {}
	}
	
// Product Methods
	/**
	 * This method is used when a seller wants to see its products list.
	 * It gets all the products from the seller into the database (thanks to its nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), 
	 * @return      {@link ArrayList} of Products
	 * @exception	SQLException
	 */
	public ArrayList<Product> getAllProducts(String nickname)
	{
		ArrayList<Product> productList = new ArrayList<Product>();
		//Product productList[] = null;
		
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
	      
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"product\" WHERE nickname = \'" + nickname + "\'"); 
	      

			Integer x=0;
			while(result.next())
			{
				Product product = new ProductDB(nickname);

				product.pdt_name=result.getObject(2).toString();

				
				
				product.quantity=Integer.parseInt(result.getObject(3).toString());

				product.price= Integer.parseInt(result.getObject(4).toString());

				product.user_nickname= result.getObject(5).toString();

				//product.id_category= Integer.parseInt(result.getObject(6).toString());
				
				product.briefDesc=result.getObject(7).toString();
				product.longDesc=result.getObject(8).toString();
						
				productList.add(product);
				
				x++;

			}

			result.close();
			
	    } 
		catch (SQLException e) 
		{
			System.out.println("ERROR - JDBConnection.getAllProducts() / : SQL Query Error (SQLException)");
	    }
		
		return productList;
	}
	
	/**
	 * This method is used when a seller wants to get a product of its products list.
	 * It gets the product wanted by the seller from the database (thanks to the seller's nickname and the product's name).
	 * The nickname/product name argument specifies the user/product and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), 
	 * 				product name (a {@link String} giving the name of the product)
	 * @return      {@link ArrayList} of Products
	 * @throws		ObjectNotInTheDatabaseException
	 * @exception	SQLException
	 */
	public Product getProduct(String nickname, String pdtName) throws ObjectNotInTheDatabaseException
	{
		Product product = new ProductDB(pdtName);
		
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
	      
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"product\" WHERE nickname = \'" + nickname + "\' AND product_name =\'" + pdtName + "\'" ); 
	        
			while(result.next())
			{				
				
				product.pdt_name=result.getObject(2).toString();
				
				product.briefDesc=result.getObject(7).toString();
				
				product.longDesc=result.getObject(8).toString();
				product.quantity=Integer.parseInt(result.getObject(3).toString());
				
				product.price= Integer.parseInt(result.getObject(4).toString());
				
				product.user_nickname= result.getObject(5).toString();
				//product.id_category= Integer.parseInt(result.getObject(6).toString());
	

			}

			result.close();
			state.close();
			
			if (product.pdt_name == null)
			{
				throw new ObjectNotInTheDatabaseException(pdtName);
			}
	         
	    } 
		catch (SQLException e) 
		{
			System.out.println("ERROR - JDBConnection.getProduct() / : SQL Query Error (SQLException)");
	    }
		
		return product;
	}

	/**
	 * This method is used when a seller wants to create a product and add it to its products list.
	 * It creates the seller product into the database (thanks to the seller's nickname and the product's name).
	 * The nickname/product name argument specifies the user/product and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the seller), 
	 * 				product name (a {@link String} giving the name of the product),
	 * 				product quantity (an {@link Integer} giving the product quantity in stocks),
	 * 				product price (an {@link Integer} giving the product price),
	 * 				product brief description (a {@link String} giving the brief description of the product),
	 * 				product long description (a {@link String} giving the long description of the product),
	 * @return      void
	 * @exception	SQLException
	 */
	public void createProduct(String nickname, String pdt_name, Integer pdt_quantity, Integer pdt_price, String pdt_briefDesc, String pdt_longDesc)
	{
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur


			state.executeQuery("INSERT INTO public.\"product\" (product_name, quantity, price, nickname, brief_desc, long_desc) VALUES (\'"  + pdt_name + "\', \'" + pdt_quantity + "\', \'"  + pdt_price + "\', \'"  + nickname + "\', \'"  + pdt_briefDesc + "\', \'" + pdt_longDesc + "\')");   

			state.close();
	         
	    } 
		catch (SQLException e) {}
	}
	
	/**
	 * This method is used when a seller wants to delete a product from its products list.
	 * It deletes the seller product from the database (thanks to the seller's nickname and the product's name).
	 * The nickname/product name argument specifies the user/product and must be a {@link String}.
	 * <p>
	 *
	 * @param  		product name (a {@link String} giving the name of the product),
	 * 				nickname (a {@link String} giving the nickname of the seller), 
	 * @return      void
	 * @exception	SQLException
	 */
	public void deleteProduct(String pdt_name, String nickname)
	{
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			state.executeQuery("DELETE FROM public.\"product\" WHERE product_name=\'"  + pdt_name + "\' AND nickname =\'"  + nickname + "\'");   
			state.close();
	         
	    } 
		catch (SQLException e) {}
	}

// Goal Methods
	/**
	 * This method is used when a simple user wants to create a goal and add it to its goals list.
	 * It creates the goal into the database and then adds it into the user's goal list (thanks to the simple user's nickname and the goal's name).
	 * The nickname/goal title argument specifies the user/goal and must be a {@link String}.
	 * <p>
	 *
	 * @param  		goal title (a {@link String} giving the title of the goal),
	 * 				goal description (a {@link String} giving the description of the goal),
	 * 				nickname (a {@link String} giving the nickname of the simple user), 
	 * @return      void
	 * @exception	SQLException
	 */
	public void createGoal(String goal_title, String goal_description, String nick) {	
		
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			
		
			state.executeQuery("INSERT INTO public.\"goal\"(goal_title, goal_description) VALUES (\'"  + goal_title + "\', \'" + goal_description + "\')");   
			state.close();
	         
	    } 
		catch (SQLException e) {}
	
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			//Requête pour mettre à jour la table goal_list avec un nickname et l'id_goal de son goal

			state.executeQuery("INSERT INTO public.\"goal_list\" VALUES((SELECT id_goal FROM public.\"goal\" where goal_title =\'" + goal_title + "\'), \'"  + nick + "\')");
			state.close();
	         
	    } 
		catch (SQLException e) {}
		
	}	
	
	/**
	 * This method is used when a simple user wants to suggest an activity category.
	 * It creates the suggestion into the database.
	 * The goal title argument specifies the goal and must be a {@link String}.
	 * <p>
	 *
	 * @param  		goal title (a {@link String} giving the title of the goal),
	 * 				goal description (a {@link String} giving the description of the goal),
	 * @return      void
	 * @exception	SQLException
	 */
	public void addSuggestionActivityCategory(String title, String description){
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de category d'activité
			state.executeQuery("INSERT INTO public.\"activity_category_suggestion\" (title,description) VAlUES (\'" + title + "\', \'" + description + "\')");
			state.close();
		}
		catch (SQLException e){}
	}
	
	/**
	 * This method is used when a simple user wants to get all activity categories.
	 * It gets the activity categories from the database.
	 * <p>
	 *
	 * @param  		none
	 * @return      {@link ArrayList} of Activity Categories
	 * @exception	SQLException
	 */
	public ArrayList<ActivityCategory> getAllCategoryActivity(){
		ArrayList<ActivityCategory> activityCategoryList = new ArrayList<ActivityCategory>() ;
		try{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"activity_category\"");
			//On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			Integer x=0;
			while(result.next())
			{
				ActivityCategory activityCategory = new ActivityCategoryDB();
				activityCategory.title = result.getObject(2).toString();
				activityCategory.description = result.getObject(3).toString();
				System.out.println(activityCategory.title);
				System.out.println(activityCategory.description);
				activityCategoryList.add(activityCategory );
				System.out.println(activityCategory.title);
				x++;
				
				
				
				}
				result.close();
			
			
		}
		catch (SQLException e){		 
		}
		return activityCategoryList;
	}
	
	
	/**
	 * This method is used when an admin wants to get all the suggestions about activity categories.
	 * It gets the suggestions from the database.
	 * <p>
	 *
	 * @param  		none
	 * @return      {@link ArrayList} of Activity Categories
	 * @exception	SQLException
	 */
	//obtenir toutes les catégories d'activités suggérées
	public ArrayList<ActivityCategory> getAllSuggestionActivityCategory(){
		ArrayList<ActivityCategory> suggestionActivityCategoryList = new ArrayList<ActivityCategory>() ;
		try{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"activity_category_suggestion\"");
			//On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			Integer x=0;
			while(result.next())
			{
				ActivityCategory activityCategorySuggestion = new ActivityCategoryDB();
				activityCategorySuggestion.title = result.getObject(2).toString();
				activityCategorySuggestion.description = result.getObject(3).toString();
				System.out.println(activityCategorySuggestion.title);
				System.out.println(activityCategorySuggestion.description);
				
				suggestionActivityCategoryList.add(activityCategorySuggestion );
				System.out.println(activityCategorySuggestion.title);
				
				x++;
				
			
				}
				result.close();
			
			
		}
		catch (SQLException e){}
		return suggestionActivityCategoryList;
	}

	public ArrayList<Goal> getGoalList(String nickname) {
		// TODO Auto-generated method stub

		ArrayList<Goal> goalList = new ArrayList<Goal>();
		//Product productList[] = null;
		
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
	      
			//L'objet ResultSet contient le résultat de la requête SQL
			
			//Select g2.id_goal, goal_title, goal_description, g2.nickname from public."goal" g1, public."goal_list" g2 WHERE g2.id_goal=g1.id_goal AND g2.nickname='SU'
			
			System.out.println("Requête de liste goal : ");
			ResultSet result = state.executeQuery("SELECT g2.id_goal, goal_title, goal_description, g2.nickname FROM public.\"goal\" g1, public.\"goal_list\" g2 WHERE g2.id_goal=g1.id_goal AND g2.nickname = \'" + nickname + "\'"); 

			Integer x=0;
			while(result.next())
			{
				Goal goal = new GoalDB(nickname);

				goal.goal_title=result.getObject(1).toString();
				goal.goal_description=result.getObject(2).toString();
				goalList.add(goal);			
				x++;

			}

			result.close();
			
	    } 
		catch (SQLException e) 
		{
			System.out.println("ERREUR - JDBConnection.getGoal() / : Requête erronée ou absence de valeur de retour (SQLException)");
	    }
		
		return goalList;
		
		
	}
}

