package jdbc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import business.Goal;
import businessDB.GoalDB;


public class JDBConnectionGoal {

	static Connection conn;

	// Singleton Constructor
	private JDBConnectionGoal()
	{
		conn = JDBConnectionOpen.conn;
	}

	// Singleton Initialisator
	private static JDBConnectionGoal singleton;

	// Singleton Accessor
	public static JDBConnectionGoal getJDBCG()
	{
		if (singleton == null)
		{ 	
			singleton = new JDBConnectionGoal();	
		}
		return singleton;
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

			// STEP 1 - Creation + Insertion of the goal into the database
			try 
			{
				
				// Creation of a Statement object (for the Query)
				Statement state = conn.createStatement();
				
				// Query (we don't have a result to get, here)
				state.executeQuery("INSERT INTO public.\"goal\"(goal_title, goal_description) VALUES (\'"  + goal_title + "\', \'" + goal_description + "\')");   
				
				// We close everything
				state.close();

			} 
			catch (SQLException e) {}

			// STEP 2 - Insertion of the goal into the user goal list in the database
			try 
			{
				// Creation of a Statement object (for the Query)
				Statement state = conn.createStatement();
				
				// Query (we don't have a result to get, here)
				state.executeQuery("INSERT INTO public.\"goal_list\" VALUES((SELECT id_goal FROM public.\"goal\" where goal_title =\'" + goal_title + "\'), \'"  + nick + "\')");
				
				// We close everything
				state.close();

			} 
			catch (SQLException e) {}

		}	

	/**
	 * This method is used when a simple user wants to get its goals list.
	 * It gets the goals list from the database (thanks to the user's nickname).
	 * The nickname argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user),
	 * @return      {@link ArrayList} of Goals
	 * @exception	SQLException
	 */
	public ArrayList<Goal> getGoalList(String nickname) {
		ArrayList<Goal> goalList = new ArrayList<Goal>();

		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();

			// Query (we get the result in the ResultSet object)
			ResultSet result = state.executeQuery("SELECT g2.id_goal, goal_title, goal_description, g2.nickname FROM public.\"goal\" g1, public.\"goal_list\" g2 WHERE g2.id_goal=g1.id_goal AND g2.nickname = \'" + nickname + "\'"); 

			// We get all Goals Data from the list
			Integer x=0;
			while(result.next())
			{
				Goal goal = new GoalDB(nickname);

				goal.goal_title=result.getObject(2).toString();
				goal.goal_description=result.getObject(3).toString();
				goalList.add(goal);			
				x++;

			}

			// We close everything
			result.close();
			state.close();

		} 
		catch (SQLException e) 
		{
			System.out.println("ERROR - JDBConnection.getGoal() / : SQL Query Error (SQLException)");
		}

		return goalList;


	}

	/**
	 * This method is used when a simple user wants to delete a goal from its goals list.
	 * It deletes the goals list from the database (thanks to the user's nickname and the goal's name).
	 * The nickname/goal name argument specifies the user/goal and must be a {@link String}.
	 * 
	 * <p>
	 *
	 * @param  		goal name 	(a {@link String} giving the name of the goal),
	 * 				nickname	(a {@link String} giving the nickname of the user)
	 * @return      void
	 * @exception	SQLException
	 */
	public void deleteGoal(String goal_name, String nicknameUser) {
		
		// STEP 1 - Delete Goal from the user Goal List
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we don't have a result to get, here)
			state.executeQuery("DELETE FROM public.\"goal_list\" WHERE id_goal=(SELECT id_goal FROM public.\"goal\" where goal_title =\'" + goal_name + "\')");   
			
			// We close everything
			state.close();

		} 
		catch (SQLException e) {}
		
		// STEP 2 - Delete Goal from the Database
		try 
		{
			// Creation of a Statement object (for the Query)
			Statement state = conn.createStatement();
			
			// Query (we don't have a result to get, here)
			state.executeQuery("DELETE FROM public.\"goal\" WHERE goal_title=\'"  + goal_name + "\'");
			
			// We close everything
			state.close();

		} 
		catch (SQLException e) {}
	
	}

}

