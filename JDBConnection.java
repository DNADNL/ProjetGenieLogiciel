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
			System.out.println("ERREUR - JDBConnection.createConnection() / Class.forName : Liaison/Initialisation �chou�e");
		}
		catch (SQLException e)
		{
			System.out.println("ERREUR - JDBConnection.createConnection() / DriverManager.getconnection : D�lai d�pass� OU Trop de connexions");
		}
	}

	// M�thodes User
	public void verifyLoginUser(User user, String nickname) throws UserNotInTheDatabaseException
	{
		
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le r�sultat de la requ�te SQL
			ResultSet result = state.executeQuery("SELECT nickname, password FROM public.\"global_user\" WHERE nickname = \'" + nickname + "\'" ); 
			
			//On r�cup�re les MetaData
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
			System.out.println("ERREUR - JDBConnection.getUser() / : Requ�te erron�e ou absence de valeur de retour (SQLException)");
	    }
	}
	
	public User getUserData(String nickname)
	{
		User user = new UserBD();
		
		try
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le r�sultat de la requ�te SQL
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
			System.out.println("ERREUR - JDBConnection.getUser() / : Requ�te erron�e ou absence de valeur de retour (SQLException)");
	    }
		
		return user;
	}
	
	// Methods USER
	public void createUser(String nickname, String password, String email)
	{
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//Ex�cution de la requ�te d'insertion de l'utilisateur
			state.executeQuery("INSERT INTO public.\"global_user\" VALUES (\'"  + nickname + "\', \'" + password + "\',\'\',\'\',\'\',\'\',\'\',\'\', \'" + email + "\');");   
			state.close();
	         
	    } 
		catch (SQLException e) {}
	}
	
	public void deleteUser(String nickname)
	{
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//Ex�cution de la requ�te d'insertion de l'utilisateur
			state.executeQuery("DELETE FROM public.\"global_user\" WHERE nickname = \'"  + nickname + "\';");   
			state.close();
	         
	    } 
		catch (SQLException e) {}
	}
	
	public void modifyUser(String nick, String pass, String email, String firstname,
				String lastname, String city,String street,String postalcode,String streetnumber){
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//Ex�cution de la requ�te d'insertion de l'utilisateur
			state.executeQuery("UPDATE public.\"global_user\" SET password=\'"  + pass + "\', mail=\'"  + email + "\', firstname=\'"  + firstname + "\', lastname=\'"  + lastname + "\', city=\'"  + city + "\', street=\'"  + street + "\', postalcode=\'"  + postalcode + "\', streetnumber=\'"  + streetnumber + "\' WHERE nickname=\'"  + nick + "\';");   
			state.close();   
	    } 
		catch (SQLException e) {}
	}
	
	
	// M�thodes Product
	public ArrayList<Product> getAllProducts(String nickname)
	{
		ArrayList<Product> productList = new ArrayList<Product>();
		//Product productList[] = null;
		
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
	      
			//L'objet ResultSet contient le r�sultat de la requ�te SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"product\" WHERE nickname = \'" + nickname + "\'"); 
	      

			//On r�cup�re les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			Integer x=0;
			while(result.next())
			{
				Product product = new ProductBD(nickname);

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
			System.out.println("ERREUR - JDBConnection.getAllProducts() / : Requ�te erron�e ou absence de valeur de retour (SQLException)");
	    }
		
		return productList;
	}
	
	//Obtenir le produit dont le nom est sp�cifi�
	public Product getProduct(String nickname, String pdtName) throws UserNotInTheDatabaseException
	{
		Product product = new ProductBD(pdtName);
		
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
	      
			//L'objet ResultSet contient le r�sultat de la requ�te SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"product\" WHERE nickname = \'" + nickname + "\' AND product_name =\'" + pdtName + "\'" ); 
	      
			//On r�cup�re les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
	         
	        
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
				throw new UserNotInTheDatabaseException(pdtName);
			}
	         
	    } 
		catch (SQLException e) 
		{
			System.out.println("ERREUR - JDBConnection.getProduct() / : Requ�te erron�e ou absence de valeur de retour (SQLException)");
	    }
		
		return product;
	}

	public void createProduct(String nickname, String pdt_name, Integer pdt_quantity, Integer pdt_price, String pdt_briefDesc, String pdt_longDesc)
	{
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//Ex�cution de la requ�te d'insertion de l'utilisateur


			state.executeQuery("INSERT INTO public.\"product\" (product_name, quantity, price, nickname, brief_desc, long_desc) VALUES (\'"  + pdt_name + "\', \'" + pdt_quantity + "\', \'"  + pdt_price + "\', \'"  + nickname + "\', \'"  + pdt_briefDesc + "\', \'" + pdt_longDesc + "\')");   

			state.close();
	         
	    } 
		catch (SQLException e) {}
	}
	
	public void deleteProduct(String pdt_name, String nickname)
	{
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//Ex�cution de la requ�te d'insertion de l'utilisateur
			state.executeQuery("DELETE FROM public.\"product\" WHERE product_name=\'"  + pdt_name + "\' AND nickname =\'"  + nickname + "\'");   
			state.close();
	         
	    } 
		catch (SQLException e) {}
	}

	//M�thodes Goal
	public void createGoal(String goal_title, String goal_description, String nick) {	
		
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//Ex�cution de la requ�te d'insertion de l'utilisateur
			
		
			state.executeQuery("INSERT INTO public.\"goal\"(goal_title, goal_description) VALUES (\'"  + goal_title + "\', \'" + goal_description + "\')");   
			state.close();
	         
	    } 
		catch (SQLException e) {}
	
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//Ex�cution de la requ�te d'insertion de l'utilisateur
			//Requ�te pour mettre � jour la table goal_list avec un nickname et l'id_goal de son goal

			state.executeQuery("INSERT INTO public.\"goal_list\" VALUES((SELECT id_goal FROM public.\"goal\" where goal_title =\'" + goal_title + "\'), \'"  + nick + "\')");
			state.close();
	         
	    } 
		catch (SQLException e) {}
		
	}
	
	public boolean isAdmin(String nickname)
	{
		
		boolean isAnAdmin = false;
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le r�sultat de la requ�te SQL
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
			System.out.println("ERREUR - JDBConnection.getUser() / : Requ�te erron�e ou absence de valeur de retour (SQLException)");
	    }
		
		return isAnAdmin;
	}
	
	public boolean isSimpleUser(String nickname)
	{
		
		boolean isASimpleUser = false;
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le r�sultat de la requ�te SQL
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
			System.out.println("ERREUR - JDBConnection.getUser() / : Requ�te erron�e ou absence de valeur de retour (SQLException)");
	    }
		
		return isASimpleUser;
	}
	
	public boolean isSeller(String nickname)
	{
		
		boolean isASeller = false;
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le r�sultat de la requ�te SQL
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
			System.out.println("ERREUR - JDBConnection.getUser() / : Requ�te erron�e ou absence de valeur de retour (SQLException)");
	    }
		
		return isASeller;
	}
		
	public void addUserRoleSeller(String nickname)
	{
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//Ex�cution de la requ�te d'insertion de l'utilisateur
			state.executeQuery("INSERT INTO public.\"seller\" VALUES (null, null, \'"  + nickname + "\');"); 
			state.close();         
	    } 
		catch (SQLException e) {}
	}
	public void addUserRoleSimpleUser(String nickname)
	{
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//Ex�cution de la requ�te d'insertion de l'utilisateur
			state.executeQuery("INSERT INTO public.\"simple_user\" VALUES (\'"  + nickname + "\');");   
			state.close();         
	    } 
		catch (SQLException e) {}
	}
	public void addUserRoleAdmin(String nickname)
	{
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//Ex�cution de la requ�te d'insertion de l'utilisateur
			state.executeQuery("INSERT INTO public.\"admin\" VALUES (\'"  + nickname + "\');");   
			state.close();         
	    } 
		catch (SQLException e) {}
	}
	public void deleteUserRoleSeller(String nickname)
	{
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//Ex�cution de la requ�te d'insertion de l'utilisateur
			state.executeQuery("DELETE FROM public.\"seller\" WHERE nickname = \'"  + nickname + "\';");
			state.close();         
	    } 
		catch (SQLException e) {}
	}
	public void deleteUserRoleSimpleUser(String nickname)
	{
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//Ex�cution de la requ�te d'insertion de l'utilisateur
			state.executeQuery("DELETE FROM public.\"simple_user\" WHERE nickname = \'"  + nickname + "\';");
			state.close();         
	    } 
		catch (SQLException e) {}
	}
	public void deleteUserRoleAdmin(String nickname)
	{
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//Ex�cution de la requ�te d'insertion de l'utilisateur
			state.executeQuery("DELETE FROM public.\"admin\" WHERE nickname = \'"  + nickname + "\';");
			state.close();         
	    } 
		catch (SQLException e) {}
	}
	
	
	//cr�ation d'une cat�gorie d'activit� sugg�r�e
	public void addSuggestionActivityCategory(String title, String description){
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//Ex�cution de la requ�te d'insertion de category d'activit�
			state.executeQuery("INSERT INTO public.\"activity_category_suggestion\" (title,description) VAlUES (\'" + title + "\', \'" + description + "\')");
			state.close();
		}
		catch (SQLException e){}
	}
	
	//obtenir toutes les cat�gories d'activit�
	public ArrayList<CategoryActivity> getAllCategoryActivity(){
		ArrayList<CategoryActivity> categoryActivityList = new ArrayList<CategoryActivity>() ;
		try{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le r�sultat de la requ�te SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"activity_category\"");
			//On r�cup�re les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			Integer x=0;
			while(result.next())
			{
				CategoryActivity categoryActivity = new CategoryActivityDB();
				categoryActivity.title = result.getObject(1).toString();
				categoryActivity.descritpion = result.getObject(2).toString();
				System.out.println(categoryActivity.title);
				System.out.println(categoryActivity.descritpion);
				categoryActivityList.add(categoryActivity );
				System.out.println(categoryActivity.title);
				x++;
				
				for(int i = 1; i <= resultMeta.getColumnCount()-1; i++){
					System.out.print("\t" + result.getObject(i).toString() + "\t |");
				
				}
				result.close();
			}
			
		}
		catch (SQLException e){		 
		}
		return categoryActivityList;
	}
	
	//obtenir toutes les cat�gories d'activit�s sugg�r�es
	public ArrayList<CategoryActivity> getAllSuggestionCategoryActivity(){
		ArrayList<CategoryActivity> suggestionCategoryActivityList = new ArrayList<CategoryActivity>() ;
		try{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
			//L'objet ResultSet contient le r�sultat de la requ�te SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"activity_category_suggestion\"");
			//On r�cup�re les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			Integer x=0;
			while(result.next())
			{
				CategoryActivity categoryActivitySuggestion = new CategoryActivityDB();
				categoryActivitySuggestion.title = result.getObject(1).toString();
				categoryActivitySuggestion.descritpion = result.getObject(2).toString();
				System.out.println(categoryActivitySuggestion.title);
				System.out.println(categoryActivitySuggestion.descritpion);
				
				suggestionCategoryActivityList.add(categoryActivitySuggestion );
				System.out.println(categoryActivitySuggestion.title);
				
				x++;
				
				for(int i = 1; i <= resultMeta.getColumnCount()-1; i++){
					System.out.print("\t" + result.getObject(i).toString() + "\t |");
				
				}
				result.close();
			}
			
		}
		catch (SQLException e){}
		return suggestionCategoryActivityList;
	}

	public ArrayList<Goal> getGoalList(String nickname) {
		// TODO Auto-generated method stub

		ArrayList<Goal> goalList = new ArrayList<Goal>();
		//Product productList[] = null;
		
		try 
		{
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();
	      
			//L'objet ResultSet contient le r�sultat de la requ�te SQL
			
			//Select g2.id_goal, goal_title, goal_description, g2.nickname from public."goal" g1, public."goal_list" g2 WHERE g2.id_goal=g1.id_goal AND g2.nickname='SU'
			
			System.out.println("Requ�te de liste goal : ");
			ResultSet result = state.executeQuery("SELECT g2.id_goal, goal_title, goal_description, g2.nickname FROM public.\"goal\" g1, public.\"goal_list\" g2 WHERE g2.id_goal=g1.id_goal AND g2.nickname = \'" + nickname + "\'"); 

			//On r�cup�re les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			Integer x=0;
			while(result.next())
			{
				Goal goal = new GoalBD(nickname);

				goal.goal_title=result.getObject(1).toString();
				goal.goal_description=result.getObject(2).toString();
				goalList.add(goal);			
				x++;

			}

			result.close();
			
	    } 
		catch (SQLException e) 
		{
			System.out.println("ERREUR - JDBConnection.getGoal() / : Requ�te erron�e ou absence de valeur de retour (SQLException)");
	    }
		
		return goalList;
		
		
	}
}

