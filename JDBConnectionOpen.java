import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnectionOpen {
	
	static Connection conn;

	// Singleton Constructor
	private JDBConnectionOpen()
	{
		createConnection();
	}

	// Singleton Initialisator
	private static JDBConnectionOpen singleton;

	// Singleton Accessor
	public static JDBConnectionOpen getJDBCO()
	{
		if (singleton == null)
		{ 	
			singleton = new JDBConnectionOpen();	
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
				JDBConnectionOpen.conn = DriverManager.getConnection(url, user, passwd);
				System.out.println("Connected Successfully !");
			} 
			catch (ClassNotFoundException e)
			{
				System.out.println("ERROR - JDBConnectionGlobalUser.createConnection() / Class.forName : Classe/Driver Not Found (ClassNotFoundException)");
			}
			catch (LinkageError e)
			{
				System.out.println("ERROR - JDBConnectionGlobalUser.createConnection() / Class.forName : Linkage/Initialization failed (LinkageError)");
			}
			catch (SQLException e)
			{
				System.out.println("ERROR - JDBConnectionGlobalUser.createConnection() / DriverManager.getconnection : Connection Timeout OR Too Many Connections (SQLException)");
			}
		}
}
