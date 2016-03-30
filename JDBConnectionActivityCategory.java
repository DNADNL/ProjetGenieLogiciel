import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class JDBConnectionActivityCategory {

	static Connection conn;

	// Singleton Constructor
	private JDBConnectionActivityCategory()
	{
		createConnection();
	}

	// Singleton Initialisator
	private static JDBConnectionActivityCategory singleton;

	// Singleton Accessor
	public static JDBConnectionActivityCategory getJDBCAC()
	{
		if (singleton == null)
		{ 	
			singleton = new JDBConnectionActivityCategory();	
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
			// Connection Information
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");
			String url = "jdbc:postgresql://qdjjtnkv.db.elephantsql.com:5432/xchuldjm";
			String user = "xchuldjm";
			String passwd = "k_s5Zb_Br9lFxGz4SmfrlPKEmJbTOvY-";

			// Creation of the link between the program and the database 
			JDBConnectionActivityCategory.conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connected Successfully !");
		} 
		catch (ClassNotFoundException e)
		{
			System.out.println("ERROR - JDBConnectionActivityCategory.createConnection() / Class.forName : Classe/Driver Not Found (ClassNotFoundException)");
		}
		catch (LinkageError e)
		{
			System.out.println("ERROR - JDBConnectionActivityCategory.createConnection() / Class.forName : Linkage/Initialization failed (LinkageError)");
		}
		catch (SQLException e)
		{
			System.out.println("ERROR - JDBConnectionActivityCategory.createConnection() / DriverManager.getconnection : Connection Timeout OR Too Many Connections (SQLException)");
		}
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
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we don't have a result to get, here)
			state.executeQuery("INSERT INTO public.\"activity_category_suggestion\" (title,description) VAlUES (\'" + title + "\', \'" + description + "\')");
			
			// We close everything
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
	public ArrayList<ActivityCategory> getAllActivityCategories(){
		ArrayList<ActivityCategory> activityCategoryList = new ArrayList<ActivityCategory>() ;
		try{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we get the result in the ResultSet object)
			ResultSet result = state.executeQuery("SELECT * FROM public.\"activity_category\"");
			
			// We get all Activity Categories Data
			Integer x=0;
			while(result.next())
			{
				ActivityCategory activityCategory = new ActivityCategoryDB();
				
				activityCategory.title = result.getObject(2).toString();
				System.out.println(activityCategory.title);
				
				activityCategory.description = result.getObject(3).toString();
				System.out.println(activityCategory.description);
				
				activityCategoryList.add(activityCategory);
				
				x++;
			}
			
			// We close everything
			result.close();
			state.close();


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
	public ArrayList<ActivityCategory> getAllSuggestionActivityCategory(){
		ArrayList<ActivityCategory> suggestionActivityCategoryList = new ArrayList<ActivityCategory>() ;
		try{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we get the result in the ResultSet object)
			ResultSet result = state.executeQuery("SELECT * FROM public.\"activity_category_suggestion\"");
			
			// We get all Suggestion Activity Categories Data
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
			
			// We close everything
			result.close();
			state.close();


		}
		catch (SQLException e){}
		return suggestionActivityCategoryList;
	}
	
	/**
	 * This method is only used for JUnit tests.
	 * It gets the last Activity Category Suggestion from the database.
	 * <p>
	 *
	 * @param  		none
	 * @return      {@link ActivityCategory}
	 * @exception	SQLException
	 */
	 public ActivityCategory getLastActivityCategorySuggestion(){
		 ActivityCategory AC = new ActivityCategory();
			try 
			{
				// Creation of a Statement object (for the Query)
				Statement state = conn.createStatement();

				// Query (we get the result in the ResultSet object)
				ResultSet result = state.executeQuery("SELECT * FROM public.\"activity_category_suggestion\"");
				
				// We get Activity Category Suggestion data
				while(result.next()){
				AC.title = result.getObject(2).toString();
				AC.description = result.getObject(3).toString();
				}
				
				// We close everything
				result.close();
				state.close();
			}
			catch (SQLException e) {}
			return AC; 
	}
}
