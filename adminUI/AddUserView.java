package adminUI;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import exceptions.*;
import facade.*;
import miscellaneous.*;

//The Add User View

@SuppressWarnings("serial")
public class AddUserView  extends JFrame implements ActionListener{

	//Creating Facade Link
	FacadeUser FU = FacadeUser.getFU();

	//Creating Panel
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

		//Ajout de l'étiquette "Ajout d'utilisateur"
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

		// Ajout des champs à rentrer
		addUserNickname.addActionListener(this);
		addUserNickname.setBounds(250, 100, 200, 25);
		panel.add(addUserNickname);

		addUserPassword.addActionListener(this);
		addUserPassword.setBounds(250, 140, 200, 25);
		panel.add(addUserPassword);

		addUserEMail.addActionListener(this);
		addUserEMail.setBounds(250, 180, 200, 25);
		panel.add(addUserEMail);			

		// Ajout de la liste de rôles
		addUserRole.addItem("Simple Utilisateur");
		addUserRole.addItem("Vendeur");
		addUserRole.addItem("Administrateur");
		addUserRole.setSelectedIndex(0);
		addUserRole.addActionListener(this);
		addUserRole.setBounds(250, 220, 200, 25);
		panel.add(addUserRole);	

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
			System.out.println("Panel Admin affiché");
		}

	}

	//Handle the adding of a User in the Database
	public void addUserButtonClicked(String nick, String pass, String email)
	{			
		try {
			FU.addUser(nick, pass, email);
		} catch (EmptyFieldsException e) {
			JOptionPane.showMessageDialog(null, "Merci de rentrer un pseudo et un mot de passe.", "Ajout d'utilisateur", JOptionPane.ERROR_MESSAGE);
		} catch (ObjectCreatedException e) {
//			System.out.println("Admin : " + (addUserRole.getSelectedItem()).equals("Administrateur"));
//			System.out.println("SU : " + (addUserRole.getSelectedItem()).equals("Simple Utilisateur"));
//			System.out.println("Seller : " + (addUserRole.getSelectedItem()).equals("Vendeur"));

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
			JOptionPane.showMessageDialog(null, nick+" a bien été ajouté à la BD !", "Ajout d'utilisateur", JOptionPane.INFORMATION_MESSAGE);
			//				System.out.println("AddUser : Successful !");
			//				addUserResultLabel.setText("L'utilisateur a été ajouté à la BD !");
			//				addUserResultLabel.setForeground(Color.BLUE);
		} catch (ObjectAlreadyExistsException e) {
			JOptionPane.showMessageDialog(null, nick+" existe déjà dans la BD.", "Ajout d'utilisateur", JOptionPane.ERROR_MESSAGE);
			//				System.out.println("AddUser : Failed !");
			//				addUserResultLabel.setText("Cet utilisateur existe déjà !");
			//				addUserResultLabel.setForeground(Color.RED);
		}
	}

}
