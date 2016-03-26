import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SeeGoalView extends JFrame implements ActionListener
{	
	FacadeUser FU = FacadeUser.getFU();
	static User user;
	// static Goal goal;
	
	//Création du panel de navigation
	JPanel panel = new JPanel();
	
	//Création des boutons de "Principal"
	Button returnMainUserButton = new Button("Retour",540, 10, 150, 30);
	Button activityDetailsButton = new Button("Détails", 540, 335, 150,30);
	Button deleteActivityButton = new Button("Supprimer",170,140,150,30);
	Button addActivityButton = new Button("Ajouter",10,140,150,30);
	
	JTable tableau;
	
	//Constructeur
	public SeeGoalView(User loggedUser, String goal_selected)
	{
		super("Lazy'N Yourself");
		user = loggedUser;
		// goal = FU.getGoal(loggedUser.nicknameUser);
		
		// Options de la fenetre
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	
		
		// Construction du panel principal
		placeComponentsGoal(panel);
		
		// Choix du panel
		setContentPane(panel);
		
		setVisible(true);
	}
	
	private void placeComponentsGoal(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);
		
		// Font
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);
		
		// Buttons
		returnMainUserButton.addActionListener(this);
		panel.add(returnMainUserButton);
		
		activityDetailsButton.addActionListener(this);
		panel.add(activityDetailsButton);
		
		addActivityButton.addActionListener(this);
		panel.add(addActivityButton);
		
		deleteActivityButton.addActionListener(this);
		panel.add(deleteActivityButton);
		
		// List
		JPanel panTab = new JPanel();
		
		Object[][] donnees = {{"",""}};
		
		// donnees = FU.getStringActivitiesList();
		
		
		String[] entetes = {"Activity", "Brief Description"};
		tableau = new JTable(donnees, entetes);
		
		JScrollPane test = new JScrollPane(tableau);
		panTab.setLayout(new BorderLayout());
		
		panTab.add(test, BorderLayout.CENTER);
		panTab.setBounds(10, 190, 400, 410);
		panel.add(panTab);
		
		// Textfields
		
		// Labels
		JLabel userLabel = new JLabel("<html>Page de <br>" + user.nicknameUser + "</html>");
		userLabel.setBounds(10, 10, 150, 50);
		userLabel.setFont(font);
		userLabel.setForeground(Color.BLACK);
		panel.add(userLabel);
		
		// Titre

		// Background

	}


	//ActionPerformMethod
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == returnMainUserButton)
		{
			new SimpleUserView(FU.getUser());
			dispose();
			System.out.println("Panel Simple User affiché");
		}
		else if (source == activityDetailsButton)
		{
			if (tableau.getSelectedRow() != -1)
			{
				String activity_selected = (tableau.getValueAt(tableau.getSelectedRow(), 0).toString());
				// new ActivityView(FU.getUser(), activity_selected);
				dispose();
				System.out.println("Panel Détail Activité affiché");
			}
			
		}
		else if (source == addActivityButton)
		{
			
			System.out.println("Panel Ajout Activité affiché");
		}
		else if (source == deleteActivityButton)
		{
			if (tableau.getSelectedRow() != -1)
			{
				String activity_selected = (tableau.getValueAt(tableau.getSelectedRow(), 0).toString());
				new DeleteActivityView(FU.getUser(), activity_selected);
				dispose();
				System.out.println("Panel Delete Activity affiché");
			}
		}
	
		
	}
		

}