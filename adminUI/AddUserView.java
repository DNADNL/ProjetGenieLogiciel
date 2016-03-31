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
	Button returnUsersButton = new Button("Return", 540, 10, 150, 30);
	Button validateAddUserButton = new Button("Add", 250, 270, 200, 30);

	//Create the Textfields for AddUserView
	JTextField addUserNickname = new JTextField("nickname");
	JPasswordField addUserPassword = new JPasswordField("password");
	JTextField addUserEMail = new JTextField("e-mail");

	//Create the combobox for AddUserView
	JComboBox<String> addUserRole = new JComboBox<String>();

	public AddUserView()
	{
		super("Lazy'N Yourself");

		// Window Options
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	

		// Building the Panel
		placeComponents(panel);

		// Choosing the Panel
		setContentPane(panel);
		
		// Setting the Panel Visible
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

		//	Adding Labels
		JLabel addUserTitle = new JLabel();
		addUserTitle.setBounds(250, 30, 300, 100);
		addUserTitle.setFont(fontTitre);					
		addUserTitle.setText("<html>Add a User</html>");
		panel.add(addUserTitle);		

		// Adding Buttons
		returnUsersButton.addActionListener(this);
		panel.add(returnUsersButton);

		validateAddUserButton.addActionListener(this);
		panel.add(validateAddUserButton);	

		// Adding Text Fields
		addUserNickname.addActionListener(this);
		addUserNickname.setBounds(250, 100, 200, 25);
		panel.add(addUserNickname);

		addUserPassword.addActionListener(this);
		addUserPassword.setBounds(250, 140, 200, 25);
		panel.add(addUserPassword);

		addUserEMail.addActionListener(this);
		addUserEMail.setBounds(250, 180, 200, 25);
		panel.add(addUserEMail);			

		// Adding Roles List
		addUserRole.addItem("Simple User");
		addUserRole.addItem("Seller");
		addUserRole.addItem("Admin");
		addUserRole.setSelectedIndex(0);
		addUserRole.addActionListener(this);
		addUserRole.setBounds(250, 220, 200, 25);
		panel.add(addUserRole);	

		// Adding Logo
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
		if (source == validateAddUserButton)
		{
			//Get the informations from the Text Fields
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
			System.out.println("AdminView Displayed");
		}

	}

	//Handle the adding of a User in the Database
	public void addUserButtonClicked(String nick, String pass, String email)
	{			
		try {
			FU.addUser(nick, pass, email);
		} catch (EmptyFieldsException e) {
			JOptionPane.showMessageDialog(null, "Enter a nickname and a password.", "Add a User", JOptionPane.ERROR_MESSAGE);
		} catch (ObjectCreatedException e) {
			if ((addUserRole.getSelectedItem()).equals("Admin"))
			{
				FU.chooseUserRoleAdmin(nick);
			}
			else if ((addUserRole.getSelectedItem()).equals("Simple User"))
			{
				FU.chooseUserRoleSimpleUser(nick);
			}
			else if ((addUserRole.getSelectedItem()).equals("Seller"))
			{
				FU.chooseUserRoleSeller(nick);
			}
			JOptionPane.showMessageDialog(null, nick+" is now registered into the DB !", "Add a User", JOptionPane.INFORMATION_MESSAGE);
		} catch (ObjectAlreadyExistsException e) {
			JOptionPane.showMessageDialog(null, nick+" already exists into the DB.", "Add a User", JOptionPane.ERROR_MESSAGE);
		}
	}

}
