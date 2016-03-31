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

@SuppressWarnings("serial")
public class UsersHandlerView extends JFrame implements ActionListener{

	//Get the Facade
	FacadeUser FU = FacadeUser.getFU();

	//Create the panel
	JPanel panel = new JPanel();

	//Create the Buttons for "UsersHandler"
	Button returnAdminButton = new Button("Return",540, 10, 150, 30);
	Button addUserButton = new Button("Add User", 250, 270, 200, 30);
	Button deleteUserButton = new Button("Delete User",250, 310, 200, 30);
	Button modifyUserButton = new Button("Modify User",250, 350, 200, 30);

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

		//Adding Labels
		JLabel UsersHandlerTitle = new JLabel();
		UsersHandlerTitle.setBounds(250, 30, 300, 100);
		UsersHandlerTitle.setFont(fontTitre);					
		UsersHandlerTitle.setText("<html>Manage Users</html>");
		panel.add(UsersHandlerTitle);		

		// Adding Buttons
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

	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		if (source == returnAdminButton)
		{
			//Return to LoginView
			new AdminView();
			dispose();
			System.out.println("AdminView Displayed");
		}
		else if (source == deleteUserButton)
		{
			//Go to DeleteUserView
			new DeleteUserView();
			dispose();
			System.out.println("DeleteUserView Displayed");
		}
		else if (source == modifyUserButton)
		{
			//Go to ModifyUserView
			new ModifyUserView();
			dispose();				
			System.out.println("ModifyUserView Displayed");
		}
		else if (source == addUserButton)
		{
			//Go to AddUserView
			new AddUserView();
			dispose();			
			System.out.println("AddUserView Displayed");
		}
	}
}
