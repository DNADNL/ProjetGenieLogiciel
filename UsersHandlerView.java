import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		placeComponentsUsersHandler(panel);

		setContentPane(panel);
		setVisible(true);
	}


	//Method to construct the panel
	private void placeComponentsUsersHandler(JPanel panel) {

		panel.removeAll();
		panel.setLayout(null);
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);

		//Ajout de l'�tiquette "Page de xxx"
		JLabel idLabel = new JLabel("<html>Page de <br>" + FU.getCurrentUser().nicknameUser + "</html>");
		idLabel.setBounds(10, 10, 150, 50);
		idLabel.setFont(font);
		idLabel.setForeground(Color.BLACK);
		panel.add(idLabel);

		//Ajout de l'�tiquette "Outils Action Utilisateur"
		JLabel UsersHandlerTitle = new JLabel();
		UsersHandlerTitle.setBounds(250, 30, 300, 100);
		UsersHandlerTitle.setFont(fontTitre);					
		UsersHandlerTitle.setText("<html>Gestion Utilisateurs</html>");
		panel.add(UsersHandlerTitle);		


		// Ajout du Bouton Ajouter
		addUserButton.addActionListener(this);
		panel.add(addUserButton);	

		// Ajout du Bouton Supprimer
		deleteUserButton.addActionListener(this);
		panel.add(deleteUserButton);

		// Ajout du Bouton Modifier
		modifyUserButton.addActionListener(this);
		panel.add(modifyUserButton);

		returnAdminButton.addActionListener(this);
		panel.add(returnAdminButton);

		panel.setLayout(new BorderLayout());
	}



	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		if (source == returnAdminButton)
		{
			//Return to LoginView
			new AdminView();
			dispose();
			System.out.println("Panel Admin affich�");
		}
		else if (source == deleteUserButton)
		{
			//Go to DeleteUserView
			new DeleteUserView();
			dispose();
			System.out.println("Panel DeleteUser affich�");
		}
		else if (source == modifyUserButton)
		{
			//Go to ModifyUserView
			new ModifyUserView(FU.getCurrentUser());
			dispose();				
			System.out.println("Panel ModifyUser affich�");
		}
		else if (source == addUserButton)
		{
			//Go to AddUserView
			new AddUserView();
			dispose();			
			System.out.println("Panel AddUser affich�");
		}

	}


}
