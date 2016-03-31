package adminUI;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exceptions.*;
import facade.*;
import miscellaneous.*;

@SuppressWarnings("serial")
public class DeleteUserView  extends JFrame implements ActionListener{

	FacadeUser FU = FacadeUser.getFU();


	//Creating Navigation Panel
	JPanel panel = new JPanel();

	//Creating Buttons
	Button returnUsersButton = new Button("Return", 540, 10, 150, 30);
	Button validateDeleteUserButton = new Button("Delete",250, 270, 200, 30);

	//Create the textfields for DeleteUserView
	JTextField deleteUserNickname = new JTextField("nickname");
	JTextField deleteUserEMail = new JTextField("e-mail");

	public DeleteUserView()
	{
		super("Lazy'N Yourself");


		// Frame Config
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	

		// Construct the panel
		placeComponents(panel);

		// Choose the Panel
		setContentPane(panel);

		// Set the Panel as visible
		setVisible(true);
	}

	/**
	 * This method places all the components onto the panel.
	 *
	 * @param  		panel	(a {@link JPanel} giving the the panel where to place components)
	 * @return      void
	 */
	private void placeComponents(JPanel panel) {

		panel.removeAll();
		panel.setLayout(null);

		// Adding Font
		Font fontTitre = new Font("Courier", Font.BOLD, 20);

		// Adding Labels
		JLabel deleteUserTitle = new JLabel();
		deleteUserTitle.setBounds(250, 30, 300, 100);
		deleteUserTitle.setFont(fontTitre);					
		deleteUserTitle.setText("<html>Delete a User</html>");
		panel.add(deleteUserTitle);	

		// Adding Buttons
		returnUsersButton.addActionListener(this);
		panel.add(returnUsersButton);

		validateDeleteUserButton.addActionListener(this);
		panel.add(validateDeleteUserButton);	

		// Adding Text Fields
		deleteUserNickname.addActionListener(this);
		deleteUserNickname.setBounds(250, 100, 200, 25);
		panel.add(deleteUserNickname);

		deleteUserEMail.addActionListener(this);
		deleteUserEMail.setBounds(250, 140, 200, 25);
		panel.add(deleteUserEMail);			


		// Adding Logo
		JLabel image = new JLabel(new ImageIcon("logo.png"));
		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(5, 5, 150, 150);
		panelLogo.setLayout(new BorderLayout());
		panelLogo.add(image, BorderLayout.CENTER);
		panel.add(panelLogo);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == validateDeleteUserButton)
		{
			String nickname = deleteUserNickname.getText();
			deleteUserButtonClicked(nickname);
		}
		else if (source == returnUsersButton)
		{
			new UsersHandlerView();	
			dispose();
			System.out.println("UsersHandlerView Displayed");
		}
	}

	public void deleteUserButtonClicked(String nick)
	{
		try {
			FU.deleteUser(nick);
		} catch (ObjectNotInTheDatabaseException e) {
			JOptionPane.showMessageDialog(null, nick+" doesn't exist in the DB.", "Delete a User", JOptionPane.ERROR_MESSAGE);
		} catch (ObjectDeletedException e) {
			JOptionPane.showMessageDialog(null, nick+" has been successfully deleted from the DB !", "Delete a User", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
