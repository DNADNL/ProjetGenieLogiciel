import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class UserBD extends User
{
	UserBD()
	{
		super();
	}
	
	UserBD(String nickname)
	{
		createUser(nickname);
	}
	
	public void createUser(String nickname)
	{
		try {
		      Class.forName("org.postgresql.Driver");
		      System.out.println("Driver O.K.");

		      String url = "jdbc:postgresql://localhost:5432/GenieLog";
		      String user = "postgres";
		      String passwd = "azerty";

		      Connection conn = DriverManager.getConnection(url, user, passwd);
		      System.out.println("Connexion effective !"); 
		      
		      //Création d'un objet Statement
		      Statement state = conn.createStatement();
		      
		      //L'objet ResultSet contient le résultat de la requête SQL
		      ResultSet result = state.executeQuery("SELECT user_nickname, user_mdp FROM public.\"GlobalUser\" WHERE user_nickname = \'" + nickname + "\'" ); 
		      
		      //On récupère les MetaData
		      ResultSetMetaData resultMeta = result.getMetaData();
		         
		     /* System.out.println("\n**********************************");
		      //On affiche le nom des colonnes
		      for(int i = 1; i <= resultMeta.getColumnCount(); i++)
		        System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
		         
		      System.out.println("\n**********************************"); */
		         
		      while(result.next())
		      {
		    	  this.nicknameUser = result.getObject(1).toString();
		    	  this.mdpUser = result.getObject(2).toString();
		    	
		    	 
		    	  
		      /*  for(int i = 1; i <= resultMeta.getColumnCount(); i++)
		        {
		          System.out.print("\t" + result.getObject(i).toString() + "\t |");
		        }
		        System.out.println("\n---------------------------------");*/

		      }

		      result.close();
		      state.close();
		         
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}
	
}
