import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminView extends JFrame implements ActionListener{

	//Get the Facade
	FacadeUser FU = FacadeUser.getFU();

	//Create the panel
	JPanel panel = new JPanel();

	//Create the Buttons for "Admin"
	Button logoutButton = new Button("D�connexion",540, 10, 150, 30);
	Button usersHandlerButton = new Button("<html>Gestion<br>utilisateurs</html>",275,335,150,60);
	Button showSuggestionButton = new Button ("<html>Suggestions <br>Cat�gories <br>d'Activit�</html>",275,405,150,120);

	public AdminView()
	{
		super("Lazy'N Yourself");

		// Frame Config
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	

		// Construct the panel
		placeComponentsAdmin(panel);

		setContentPane(panel);
		setVisible(true);
	}

	//Method to construct the panel
	private void placeComponentsAdmin(JPanel panel) {

		panel.removeAll();
		panel.setLayout(null);

		// Fonts
		Font fontTitre = new Font("Courier", Font.BOLD, 30);
		Font font = new Font("Courier", Font.BOLD, 18);

		// Labels

		//Ajout de l'�tiquette "Page de xxx"
		JLabel idLabel = new JLabel("<html>Page de <br>" + FU.getCurrentUser().nicknameUser + "</html>");
		idLabel.setBounds(10, 10, 150, 50);
		idLabel.setFont(font);
		idLabel.setForeground(Color.BLACK);
		panel.add(idLabel);

		//Ajout de l'�tiquette "Outils Action Utilisateur"
		JLabel UserLabel = new JLabel();
		UserLabel.setBounds(250, 30, 300, 100);
		UserLabel.setFont(font);					
		UserLabel.setText("Outils Administrateur");
		panel.add(UserLabel);		

		// Buttons	
		usersHandlerButton.addActionListener(this);
		panel.add(usersHandlerButton);

		logoutButton.addActionListener(this);
		panel.add(logoutButton);

		showSuggestionButton.addActionListener(this);
		panel.add(showSuggestionButton);

		panel.setLayout(new BorderLayout());
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		if (source == logoutButton)
		{
			//Return to LoginView
			new LoginView();	
			dispose();
			System.out.println("Panel Login affich�");
		}
		else if (source == usersHandlerButton)
		{
			//Go to UsersHandlerView
			new UsersHandlerView();	
			dispose();
			System.out.println("Panel Gestion Utilisateurs affich�");
		}
		else if ( source == showSuggestionButton)
		{
			//Go to SuggestionCategoryActivityView
			new SuggestionCategoryActivityView(FU.getCurrentUser());
			dispose();
			System.out.println("Panel Suggestion Cat�gories Activit� affich�");
		}

	}
}
