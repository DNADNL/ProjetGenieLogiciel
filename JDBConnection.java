import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;


public class JDBConnection {
	
	Random rand = new Random();
	
	static Connection conn;
	
	//Constructeur Singleton
	private JDBConnection()
	{
		createConnection();
	}
	
	//Initialisation Singleton
	private static JDBConnection singleton;
	
	//Accesseur Singleton
	public static JDBConnection getJDBC()
	{
		if (singleton == null)
		{ 	
			singleton = new JDBConnection();	
		}
		return singleton;
	}
	
	private void createConnection()
	{
		try 
		{
			Class.forName("org.postgresql.Driver");
		    System.out.println("Driver O.K.");
		    String url = "jdbc:postgresql://qdjjtnkv.db.elephantsql.com:5432/xchuldjm";
		    String user = "xchuldjm";
		    String passwd = "k_s5Zb_Br9lFxGz4SmfrlPKEmJbTOvY-";

		    JDBConnection.conn = DriverManager.getConnection(url, user, passwd);
		    System.out.println("Connexion effective !");
		} 
		catch (ClassNotFoundException e)
		{
			System.out.println("ERREUR - JDBConnection.createConnection() / Class.forName : Classe/Driver Introuvable");
		}
		catch (LinkageError e)
		{
			System.out.println("ERREUR - JDBConnection.createConnection() / Class.forName : Liaison/Initialisation échouée");
		}
		catch (SQLException e)
		{
			System.out.println("ERREUR - JDBConnection.createConnection() / DriverManager.getconnection : Délai dépassé OU Trop de connexions");
		}
	}

	// Méthodes User
	public void verifyLoginUser(User user, String nickname) throws UserNotInTheDatabaseException
	{
		
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT nickname, password FROM public.\"global_user\" WHERE nickname = \'" + nickname + "\'" ); 
			//On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			/* System.out.println("\n**********************************");
	      	//On affiche le nom des colonnes
	      	for(int i = 1; i <= resultMeta.getColumnCount(); i++)
	        	System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
	         
	      	System.out.println("\n**********************************"); */
	        
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
				throw new UserNotInTheDatabaseException(nickname);
			}
	         
	    } 
		catch (SQLException e) 
		{
			System.out.println("ERREUR - JDBConnection.getUser() / : Requête erronée ou absence de valeur de retour (SQLException)");
	    }
	}
	
	public User getUserData(String nickname)
	{
		User user = new UserBD();
		
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
			System.out.println("ERREUR - JDBConnection.getUser() / : Requête erronée ou absence de valeur de retour (SQLException)");
	    }
		
		return user;
	}
	
	// Methods USER
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
	
	
	// Méthodes Product
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
	      

			//On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			Integer x=0;
			while(result.next())
			{
				Product product = new ProductBD(nickname);

				product.pdt_name=result.getObject(2).toString();

				
				
				product.quantity=Integer.parseInt(result.getObject(3).toString());

				product.price= Float.parseFloat(result.getObject(4).toString());

				product.user_nickname= result.getObject(5).toString();

				//product.id_category= Integer.parseInt(result.getObject(6).toString());
				
				product.briefDesc=result.getObject(7).toString();
				product.longDesc=result.getObject(8).toString();
				
				
				System.out.println(product.pdt_name);
				System.out.println(product.user_nickname);
				System.out.println(product.price);
				System.out.println(product.quantity);
				System.out.println(product.id_category);
				
				productList.add(product);
				
				System.out.println(product.pdt_name);

			
				x++;
				
	    	
//				for(int i = 1; i <= resultMeta.getColumnCount()-1; i++)
//	        	{
//	          		System.out.print("\t" + result.getObject(i).toString() + "\t |");
//	        	}
//	        	System.out.println("\n---------------------------------");

			}

			result.close();
			
	    } 
		catch (SQLException e) 
		{
			System.out.println("ERREUR - JDBConnection.getAllProducts() / : Requête erronée ou absence de valeur de retour (SQLException)");
	    }
		
		return productList;
	}
	
	public Product getProduct(String nickname, String pdtName) throws UserNotInTheDatabaseException
	{
		Product product = null;
		
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
	      
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"product\" WHERE nickname = \'" + nickname + "\' AND product_name =\'" + pdtName + "\'" ); 
	      
			//On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
	         
	         
			while(result.next())
			{
				Integer x=0;
				product.pdt_name=result.getObject(2).toString();
				//product.briefDesc=result.getObject(2).toString();
				//product.longDesc=result.getObject(3).toString();
				product.quantity=Integer.parseInt(result.getObject(3).toString());
				product.price= Float.parseFloat(result.getObject(4).toString());
				product.user_nickname= result.getObject(5).toString();
				product.id_category= Integer.parseInt(result.getObject(6).toString());
				
				x++;
	    	
				for(int i = 1; i <= resultMeta.getColumnCount(); i++)
	        	{
	          		System.out.print("\t" + result.getObject(i).toString() + "\t |");
	        	}
	        	System.out.println("\n---------------------------------");

			}

			result.close();
			state.close();
			
			if (product.pdt_name == null)
			{
				throw new UserNotInTheDatabaseException(pdtName);
			}
	         
	    } 
		catch (SQLException e) 
		{
			System.out.println("ERREUR - JDBConnection.getProduct() / : Requête erronée ou absence de valeur de retour (SQLException)");
	    }
		
		return product;
	}

	public void createProduct(String pdt_name, Integer pdt_quantity, Float pdt_price, String nickname, String pdt_briefDesc, String pdt_longDesc)
	{
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			state.executeQuery("INSERT INTO public.\"product\" (product_name, quantity, price, nickname, brief_desc, long_desc) VALUES (\'"  + pdt_name + "\', \'" + pdt_price + "\', \'"  + pdt_quantity + "\', \'"  + nickname + "\', \'"  + pdt_briefDesc + "\', \'" + pdt_longDesc + "\')");   
			state.close();
	         
	    } 
		catch (SQLException e) {}
	}
	
	public void deleteProduct(String pdt_name, String nickname)
	{
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			state.executeQuery("DELETE FROM public.\"product\" WHERE product_name=\'"  + pdt_name + "\',nickname =\'"  + nickname + "\' ;");   
			state.close();
	         
	    } 
		catch (SQLException e) {}
	}

	public void createGoal(String goal_title, String goal_description, String nick) {
		// TODO Auto-generated method stub
		
		
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
			//Exécution de la requête d'insertion de l'utilisateur
			
			System.out.println(goal_title);
			state.executeQuery("INSERT INTO public.\"goal\"(goal_title, goal_description) VALUES (\'"  + goal_title + "\', \'" + goal_description + "\')");   
			
			state.close();
	         
	    } 
		catch (SQLException e) {}
		
	}
	
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
			System.out.println("ERREUR - JDBConnection.getUser() / : Requête erronée ou absence de valeur de retour (SQLException)");
	    }
		
		return isAnAdmin;
	}
	
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
	
}

