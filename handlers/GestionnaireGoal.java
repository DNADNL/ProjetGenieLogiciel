package handlers;
import java.sql.SQLException;
import java.util.ArrayList;

import business.Goal;
import business.User;
import exceptions.*;
import factory.AbstractFactoryGoal;
import factory.FactoryGoal;

public class GestionnaireGoal {

	Goal goal_selected;
	ArrayList<Goal> goalList;
	String [][] stringGoalList;

	AbstractFactoryGoal Fact = new FactoryGoal();

	//Constructeur Singleton
	private GestionnaireGoal()
	{}

	//Initialisation Singleton
	private static GestionnaireGoal singleton;

	//Accesseur Singleton
	public static GestionnaireGoal getGG()
	{
		if (singleton==null)
		{
			singleton = new GestionnaireGoal();
		}
		return singleton;
	}

	/**
	 * This method is used when a user wants to add a goal to his Goal List (via the AddGoalView).
	 * It checks if the goal is already registered in the database.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user), goal_title, goal_description
	 * If the goal is already in the database, then this method returns an {@link ObjectAlreadyExistsException}, 
	 * else an {@link ObjectCreatedException} is returned, the goal is added to the database and to the Goal List, and the Goal List is refreshed.
	 * @throws 		ObjectCreatedException, ObjectAlreadyExistsException
	 */
	public void addGoal(String goal_title, String goal_description, String nick)throws ObjectCreatedException, ObjectAlreadyExistsException {
		// TODO Auto-generated method stub
		getGoalList(nick);
		String goal_title_test;
		
		
		
		for (Integer i=0; i<goalList.size(); i++)
		{	
			
			
			goal_title_test = goalList.get(i).goal_title;
			if(goal_title_test.equals(goal_title)){
				System.out.println("GOAL déja dans la DB !");
				throw new ObjectAlreadyExistsException(nick);	
				
			}
		}
		

		
		
		Fact.addGoal(goal_title, goal_description, nick);
		refreshGoalList(nick);
		throw new ObjectCreatedException(nick);
		
	}

	/**
	 * This method is used when a user log in the system. It loads the goal List from the database.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 */
	private void getGoalList(String nickname)
	{	
		if (goalList==null)
		{
			goalList = Fact.createGoalList(nickname);
		}
	}

	/**
	 * This method is used when a modification occurs on the goal List. It loads the goal List from the database.
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 */
	private void refreshGoalList(String nickname)
	{	
		goalList = Fact.createGoalList(nickname);
	}

	/**
	 * This method is used when a user log in his main page. 
	 * It gets the needed informations for the SimpleUserView and modifies their types to {@link String}
	 * <p>
	 *
	 * @param  		nickname (a {@link String} giving the nickname of the user)
	 * @return      {@link String} 2D Table
	 * It checks if the Goal List is empty before filling the StringGoalList.
	 */
	public String[][] getStringGoalList(String nickname)
	{
		
		getGoalList(nickname);
		
		
		stringGoalList= new String [goalList.size()][2];

		if (goalList != null)
		{
			for (Integer i=0; i<goalList.size(); i++)
			{
				stringGoalList[i][0]= goalList.get(i).goal_title;
				stringGoalList[i][1]= goalList.get(i).goal_description;
			}
		}

		return stringGoalList;

	}


	/**
	 * This method is used when a user deletes one of his goals.
	 * It deletes the selected goal from the database (thanks to its goal_name).
	 * The goal_name argument specifies the user and must be a {@link String}.
	 * <p>
	 *
	 * @param  		goal_name, nickname (a {@link String} giving the nickname of the user)
	 * If the goal does not exists, this method returns an {@link ObjectAlreadyExistsException}, 
	 * else an {@link ObjectDeletedException} is returned, the goal is deleted from the database and the Goal List, and the Goal List is refreshed.
	 * @throws 		ObjectNotInTheDatabaseException, ObjectDeletedException
	 */
	public void deleteGoal(String goal_name, String nicknameUser) throws ObjectNotInTheDatabaseException , ObjectDeletedException 
	{
		Fact.deleteGoal(goal_name, nicknameUser);
		refreshGoalList(nicknameUser);
		throw new ObjectDeletedException(goal_name);		
	}

	/**
	 * This method is used when a user disconnects.
	 * It deletes all the current informations from goal_selected, goalList, stringGoalList
	 * <p>
	 *
	 */
	public void deleteAllCurrentInfos() {
		// TODO Auto-generated method stub
		goal_selected = null;
		goalList = null;
		stringGoalList = null;
	}

	

}
