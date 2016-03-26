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

public class AddGoalView extends JFrame implements ActionListener
{	
	FacadeUser FU = FacadeUser.getFU();
	
	static User user;
	
	//Création du panel de navigation
	JPanel panel = new JPanel();
	
	
	//création du bouton pour ajouter un utilisateur et des champs à rentrer
		Button returnUsersButton = new Button("Retour", 540, 10, 150, 30);
			Button validateAddUserButton = new Button("Ajouter", 250, 270, 200, 30);
	
	// Création des champs de textes pour completer les attributs d'un objectif
			JTextField addGoalTitle = new JTextField("Goal Title");
			JTextField addGoalDescription = new JTextField("Goal Description");
			
	//Constructeur
	public AddGoalView(User loggedUser)
	{
		super("Bienvenue !");
		user = loggedUser;
		
		// Options de la fenetre
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	
		
		// Construction du panel principal
		placeComponentsPrincipal(panel);
		
		// Choix du panel
		setContentPane(panel);
		
		setVisible(true);
	}
	
	private void placeComponentsPrincipal(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);
		Font fontAdvice = new Font("Courier", Font.ITALIC, 14);
		
		//Ajout de l'étiquette "Page de xxx"
		JLabel idLabel = new JLabel("<html>Page de <br>" + user.nicknameUser + "</html>");
		idLabel.setBounds(10, 10, 150, 50);
		idLabel.setFont(font);
		idLabel.setForeground(Color.BLACK);
		panel.add(idLabel);
		
		//Ajout de l'étiquette "Ajout d'utilisateur"
		JLabel addUserTitle = new JLabel();
		addUserTitle.setBounds(250, 30, 300, 100);
		addUserTitle.setFont(fontTitre);					
		addUserTitle.setText("<html>Add a Goal</html>");
		panel.add(addUserTitle);		
		
		
		
		// Ajout du Bouton Retour
		returnUsersButton.addActionListener(this);
		panel.add(returnUsersButton);

		// Ajout du Bouton Ajouter
		validateAddUserButton.addActionListener(this);
		panel.add(validateAddUserButton);	
		
		// Ajout des champs à rentrer
		addGoalTitle.addActionListener(this);
		addGoalTitle.setBounds(250, 140, 200, 25);
		panel.add(addGoalTitle);
		
		addGoalDescription.addActionListener(this);
		addGoalDescription.setBounds(250, 180, 200, 25);
		panel.add(addGoalDescription);
				
		
		
		panel.setLayout(new BorderLayout());

	}


	//ActionPerformMethod
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Object source = e.getSource();
		if (source == validateAddUserButton)
		{
			String goal_title = addGoalTitle.getText();
			String goal_description = addGoalDescription.getText();
			String nick = user.getUserNickname();
			addUserButtonClicked(goal_title, goal_description, nick);
		}
		else if (source == returnUsersButton)
		{
			new SimpleUserView(FU.getUser());	
			dispose();
			System.out.println("Panel Admin affiché");
		}
	
		
	}
	
	public void addUserButtonClicked(String goal_title, String goal_description, String nick)
	{
		//Object resultAddUser = null;
		
		FU.addGoal(goal_title, goal_description, nick);
		
	}


}