import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
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
	public void getUser(User user, String nickname) throws UserNotInTheDatabaseException
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
			state.executeQuery("DELETE FROM public.\"global_user\" WHERE nickname=\'"  + nickname + "\';");   
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
	public String[][] getAllProducts(String nickname)
	{
		String stringProductList[][] = null;
		
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
	      
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"Product\" WHERE user_nickname = \'" + nickname + "\'"); 
	      
			//On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			Integer x=0;
			while(result.next())
			{
				for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				{
					stringProductList[x][i-1] = result.getObject(i).toString();
				}
				
				
				x++;
	    	
				for(int i = 1; i <= resultMeta.getColumnCount(); i++)
	        	{
	          		System.out.print("\t" + result.getObject(i).toString() + "\t |");
	        	}
	        	System.out.println("\n---------------------------------");

			}

			result.close();
			
	    } 
		catch (SQLException e) 
		{
			System.out.println("ERREUR - JDBConnection.getAllProducts() / : Requête erronée ou absence de valeur de retour (SQLException)");
	    }
		
		return stringProductList;
	}
	
	public Product getProduct(String nickname, String pdtName)
	{
		Product product = null;
		
		try 
		{
			//Création d'un objet Statement
			Statement state = conn.createStatement();
	      
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM public.\"Product\" WHERE user_nickname = \'" + nickname + "\' AND pdt_name =\'" + pdtName + "\'" ); 
	      
			//On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
	         
	         
			while(result.next())
			{
				Integer x=0;
				product.name=result.getObject(1).toString();
				product.briefDesc=result.getObject(2).toString();
				product.longDesc=result.getObject(3).toString();
				//product.quantity=result.getObject(4).toString();
				//product.price= result.getObject(5).toString();
				
				x++;
	    	
				for(int i = 1; i <= resultMeta.getColumnCount(); i++)
	        	{
	          		System.out.print("\t" + result.getObject(i).toString() + "\t |");
	        	}
	        	System.out.println("\n---------------------------------");

			}

			result.close();
	         
	    } 
		catch (SQLException e) 
		{
			System.out.println("ERREUR - JDBConnection.getProduct() / : Requête erronée ou absence de valeur de retour (SQLException)");
	    }
		
		return product;
	}
}
