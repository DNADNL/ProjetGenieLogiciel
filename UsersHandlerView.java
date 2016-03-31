import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UsersHandlerView extends JFrame implements ActionListener{

	//Get the Facade
	FacadeUser FU = FacadeUser.getFU();

	//Create the panel
	JPanel panel = new JPanel();

	//Create the Buttons for "UsersHandler"
	Button returnAdminButton = new Button("Retour",540, 10, 150, 30);
	Button addUserButton = new Button("Ajouter", 250, 270, 200, 30);
	Button deleteUserButton = new Button("Supprimer",250, 310, 200, 30);
	Button modifyUserButton = new Button("Modifier",250, 350, 200, 30);

	public UsersHandlerView()
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
		Font fontTitre = new Font("Courier", Font.BOLD, 20);

//		//Ajout de l'étiquette "Page de xxx"
//		JLabel idLabel = new JLabel("<html>Page de <br>" + FU.getCurrentUser().nicknameUser + "</html>");
//		idLabel.setBounds(10, 10, 150, 50);
//		idLabel.setFont(font);
//		idLabel.setForeground(Color.BLACK);
//		panel.add(idLabel);

		//Ajout de l'étiquette "Outils Action Utilisateur"
		JLabel UsersHandlerTitle = new JLabel();
		UsersHandlerTitle.setBounds(250, 30, 300, 100);
		UsersHandlerTitle.setFont(fontTitre);					
		UsersHandlerTitle.setText("<html>Gestion Utilisateurs</html>");
		panel.add(UsersHandlerTitle);		

		// Buttons
		addUserButton.addActionListener(this);
		panel.add(addUserButton);	

		deleteUserButton.addActionListener(this);
		panel.add(deleteUserButton);

		modifyUserButton.addActionListener(this);
		panel.add(modifyUserButton);

		returnAdminButton.addActionListener(this);
		panel.add(returnAdminButton);

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
		if (source == returnAdminButton)
		{
			//Return to LoginView
			new AdminView();
			dispose();
			System.out.println("Panel Admin affiché");
		}
		else if (source == deleteUserButton)
		{
			//Go to DeleteUserView
			new DeleteUserView();
			dispose();
			System.out.println("Panel DeleteUser affiché");
		}
		else if (source == modifyUserButton)
		{
			//Go to ModifyUserView
			new ModifyUserView(FU.getCurrentUser());
			dispose();				
			System.out.println("Panel ModifyUser affiché");
		}
		else if (source == addUserButton)
		{
			//Go to AddUserView
			new AddUserView();
			dispose();			
			System.out.println("Panel AddUser affiché");
		}

	}


}
