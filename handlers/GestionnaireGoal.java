package handlers;
import java.util.ArrayList;

import business.Goal;
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

	private void getGoalList(String nickname)
	{	
		if (goalList==null)
		{
			goalList = Fact.createGoalList(nickname);
		}
	}

	private void refreshGoalList(String nickname)
	{	
		goalList = Fact.createGoalList(nickname);
	}

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


	public void deleteGoal(String goal_name, String nicknameUser) throws ObjectNotInTheDatabaseException , ObjectDeletedException 
	{
		Fact.deleteGoal(goal_name, nicknameUser);
		refreshGoalList(nicknameUser);
		throw new ObjectDeletedException(goal_name);		
	}

	public void deleteAllCurrentInfos() {
		// TODO Auto-generated method stub
		goal_selected = null;
		goalList = null;
		stringGoalList = null;
	}

	

}
