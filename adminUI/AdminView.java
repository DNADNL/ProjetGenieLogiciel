package adminUI;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import facade.*;
import miscellaneous.*;


//The Admin View

@SuppressWarnings("serial")
public class AdminView extends JFrame implements ActionListener{

	//Get the Facade
	FacadeUser FU = FacadeUser.getFU();

	//Create the panel
	JPanel panel = new JPanel();

	//Create the Buttons for "Admin"
	Button logoutButton = new Button("Disconnection",540, 10, 150, 30);
	Button usersHandlerButton = new Button("<html>Handle<br>Users</html>",275,335,150,60);
	Button showSuggestionButton = new Button ("<html>Activity <br>Category <br>Suggestions</html>",275,405,150,120);

	public AdminView()
	{
		super("Lazy'N Yourself");

		// Frame Config
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	

		// Construct the panel
		placeComponents(panel);

		setContentPane(panel);
		setVisible(true);
	}
	
	/**
	 * This method places all the components onto the panel.
	 *
	 * @param  		panel	(a {@link JPanel} giving the the panel where to place components)
	 */
	private void placeComponents(JPanel panel) {

		panel.removeAll();
		panel.setLayout(null);

		// Fonts
		Font fontTitle = new Font("Courier", Font.BOLD, 20);

		// Labels

		//Ajout de l'étiquette "Page de xxx"
//		JLabel idLabel = new JLabel("<html>Hello, <br>" + FU.getCurrentUser().nicknameUser + "</html>");
//		idLabel.setBounds(10, 10, 150, 50);
//		idLabel.setFont(font);
//		idLabel.setForeground(Color.BLACK);
//		panel.add(idLabel);

		//Ajout de l'étiquette "Outils Administrateur"
		JLabel UserLabel = new JLabel();
		UserLabel.setBounds(250, 30, 300, 100);
		UserLabel.setFont(fontTitle);					
		UserLabel.setText("Admin Tools");
		panel.add(UserLabel);		

		// Buttons	
		usersHandlerButton.addActionListener(this);
		panel.add(usersHandlerButton);

		logoutButton.addActionListener(this);
		panel.add(logoutButton);

		showSuggestionButton.addActionListener(this);
		panel.add(showSuggestionButton);

		// Logo

		JLabel image = new JLabel(new ImageIcon("logo.png"));
		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(5, 5, 150, 150);
		panelLogo.setLayout(new BorderLayout());
		panelLogo.add(image, BorderLayout.CENTER);
		panel.add(panelLogo);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		if (source == logoutButton)
		{
			//Return to LoginView
			new LoginView();	
			dispose();
			System.out.println("Panel Login affiché");
		}
		else if (source == usersHandlerButton)
		{
			//Go to UsersHandlerView
			new UsersHandlerView();	
			dispose();
			System.out.println("Panel Gestion Utilisateurs affiché");
		}
		else if ( source == showSuggestionButton)
		{
			//Go to SuggestionCategoryActivityView
			new ActivityCategorySuggestionsView();
			dispose();
			System.out.println("Panel Suggestion Catégories Activité affiché");
		}

	}
}
