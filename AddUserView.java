import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddUserView  extends JFrame implements ActionListener{

	//Get the Facade
	FacadeUser FU = FacadeUser.getFU();

	//Create the panel
	JPanel panel = new JPanel();

	//Create the button for AddUserView
	Button returnUsersButton = new Button("Retour", 540, 10, 150, 30);
	Button validateAddUserButton = new Button("Ajouter", 250, 270, 200, 30);

	//Create the Textfields for AddUserView
	JTextField addUserNickname = new JTextField("pseudo");
	JPasswordField addUserPassword = new JPasswordField("mot_de_passe");
	JTextField addUserEMail = new JTextField("e-mail");

	//Create the combobox for AddUserView
	JComboBox<String> addUserRole = new JComboBox<String>();

	public AddUserView()
	{
		super("Lazy'N Yourself");

		// Frame Config
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	

		// Construct the panel
		placeComponentsAddUser(panel);


		setContentPane(panel);
		setVisible(true);
	}

	//Method to construct the panel
	private void placeComponentsAddUser(JPanel panel) {

		panel.removeAll();
		panel.setLayout(null);
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);
		Font fontAdvice = new Font("Courier", Font.ITALIC, 14);

		//Ajout de l'�tiquette "Page de xxx"
		JLabel idLabel = new JLabel("<html>Page de <br>" + FU.getCurrentUser().nicknameUser + "</html>");
		idLabel.setBounds(10, 10, 150, 50);
		idLabel.setFont(font);
		idLabel.setForeground(Color.BLACK);
		panel.add(idLabel);

		//Ajout de l'�tiquette "Ajout d'utilisateur"
		JLabel addUserTitle = new JLabel();
		addUserTitle.setBounds(250, 30, 300, 100);
		addUserTitle.setFont(fontTitre);					
		addUserTitle.setText("<html>Ajout d'utilisateur</html>");
		panel.add(addUserTitle);		

		// Ajout du Bouton Retour
		returnUsersButton.addActionListener(this);
		panel.add(returnUsersButton);

		// Ajout du Bouton Ajouter
		validateAddUserButton.addActionListener(this);
		panel.add(validateAddUserButton);	

		// Ajout des champs � rentrer
		addUserNickname.addActionListener(this);
		addUserNickname.setBounds(250, 100, 200, 25);
		panel.add(addUserNickname);

		addUserPassword.addActionListener(this);
		addUserPassword.setBounds(250, 140, 200, 25);
		panel.add(addUserPassword);

		addUserEMail.addActionListener(this);
		addUserEMail.setBounds(250, 180, 200, 25);
		panel.add(addUserEMail);			

		// Ajout de la liste de r�les
		addUserRole.addItem("Simple Utilisateur");
		addUserRole.addItem("Vendeur");
		addUserRole.addItem("Administrateur");
		addUserRole.setSelectedIndex(0);
		addUserRole.addActionListener(this);
		addUserRole.setBounds(250, 220, 200, 25);
		panel.add(addUserRole);	

		panel.setLayout(new BorderLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == validateAddUserButton)
		{
			//Get the informations from the Textfields
			String nickname = addUserNickname.getText();
			String password = new String(addUserPassword.getPassword());
			String email = addUserEMail.getText();

			//Add the user
			addUserButtonClicked(nickname, password, email);
		}
		else if (source == returnUsersButton)
		{
			//Return to UsersHandlerView
			new UsersHandlerView();	
			dispose();
			System.out.println("Panel Admin affich�");
		}

	}

	//Handle the adding of a User in the Database
	public void addUserButtonClicked(String nick, String pass, String email)
	{			
		try {
			FU.addUser(nick, pass, email);
		} catch (ObjectCreatedException e) {
			System.out.println("Admin : " + (addUserRole.getSelectedItem()).equals("Administrateur"));
			System.out.println("SU : " + (addUserRole.getSelectedItem()).equals("Simple Utilisateur"));
			System.out.println("Seller : " + (addUserRole.getSelectedItem()).equals("Vendeur"));

			if ((addUserRole.getSelectedItem()).equals("Administrateur"))
			{
				FU.chooseUserRoleAdmin(nick);
			}
			else if ((addUserRole.getSelectedItem()).equals("Simple Utilisateur"))
			{
				FU.chooseUserRoleSimpleUser(nick);
			}
			else if ((addUserRole.getSelectedItem()).equals("Vendeur"))
			{
				FU.chooseUserRoleSeller(nick);
			}
			JOptionPane.showMessageDialog(null, nick+" a bien �t� ajout� � la BD !", "Ajout d'utilisateur", JOptionPane.INFORMATION_MESSAGE);
			//				System.out.println("AddUser : Successful !");
			//				addUserResultLabel.setText("L'utilisateur a �t� ajout� � la BD !");
			//				addUserResultLabel.setForeground(Color.BLUE);
		} catch (ObjectAlreadyExistsException e) {
			JOptionPane.showMessageDialog(null, nick+" existe d�j� dans la BD.", "Ajout d'utilisateur", JOptionPane.ERROR_MESSAGE);
			//				System.out.println("AddUser : Failed !");
			//				addUserResultLabel.setText("Cet utilisateur existe d�j� !");
			//				addUserResultLabel.setForeground(Color.RED);
		}
	}

}
