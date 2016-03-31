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

@SuppressWarnings("serial")
public class ModifyUserView  extends JFrame implements ActionListener{
	
	FacadeUser FU = FacadeUser.getFU();
	
	//Create Navigation Panel
			JPanel panel = new JPanel();
	
	//Create Components
		Button returnUsersButton = new Button("Return", 540, 10, 150, 30);
		Button validateModifyUserButton = new Button("Modify",250, 600, 200, 30);
		
		JTextField modifyUserNickname = new JTextField("nickname");
		JPasswordField modifyUserPassword = new JPasswordField("password");
		JTextField modifyUserEMail = new JTextField("e-mail");
		
		JTextField modifyFirstName = new JTextField("First Name");
		JTextField modifyLastName = new JTextField("Last Name");
		JTextField modifyCity = new JTextField("City");
		JTextField modifyStreet = new JTextField("Street");
		
		JTextField modifyPostalCode = new JTextField("Postal Code");
		JTextField modifyStreetNumber = new JTextField("Street Number");
		
		JComboBox<String> modifyUserRole = new JComboBox<String>();
		
		
		public ModifyUserView()
		{
			super("Lazy'N Yourself");
			
			// Window Options
			this.setSize(700,700);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);	
			
			// Building Panel
			placeComponents(panel);
			
			// Choose Panel
			setContentPane(panel);
			
			// Set the Panel Visible
			setVisible(true);
		}
		
		/**
		 * This method places all the components onto the panel.
		 * <p>
		 *
		 * @param  		panel	(a {@link JPanel} giving the the panel where to place components)
		 */
		private void placeComponents(JPanel panel) {

		  	panel.removeAll();
			panel.setLayout(null);
			Font fontTitle = new Font("Courier", Font.BOLD, 20);
			Font font = new Font("Courier", Font.BOLD, 15);
			
			// Adding Labels
			JLabel modifyUserTitle = new JLabel();
			modifyUserTitle.setBounds(250, 20, 300, 100);
			modifyUserTitle.setFont(fontTitle);					
			modifyUserTitle.setText("<html>Modify a User</html>");
			panel.add(modifyUserTitle);		
			

			JLabel modifiyNicknameTitle = new JLabel();
			modifiyNicknameTitle.setBounds(150, 70, 450, 100);
			modifiyNicknameTitle.setFont(font);					
			modifiyNicknameTitle.setText("Please write the nickname of the user to modify");
			panel.add(modifiyNicknameTitle);
			

			JLabel modifiyDetailProfile = new JLabel();
			modifiyDetailProfile.setBounds(150, 170, 360, 100);
			modifiyDetailProfile.setFont(font);					
			modifiyDetailProfile.setText("Fill the Information you want to modify");
			panel.add(modifiyDetailProfile);	
			
			// Adding Buttons
			returnUsersButton.addActionListener(this);
			panel.add(returnUsersButton);

			validateModifyUserButton.addActionListener(this);
			panel.add(validateModifyUserButton);	
			
			// Adding Text Fields
			modifyUserNickname.addActionListener(this);
			modifyUserNickname.setBounds(250, 140, 200, 25);
			panel.add(modifyUserNickname);
			
			modifyUserPassword.addActionListener(this);
			modifyUserPassword.setBounds(250, 240, 200, 25);
			panel.add(modifyUserPassword);
			
			modifyUserEMail.addActionListener(this);
			modifyUserEMail.setBounds(250, 280, 200, 25);
			panel.add(modifyUserEMail);			
			
			modifyFirstName.addActionListener(this);
			modifyFirstName.setBounds(250, 320, 200, 25);
			panel.add(modifyFirstName);	
			
			modifyLastName.addActionListener(this);
			modifyLastName.setBounds(250, 360, 200, 25);
			panel.add(modifyLastName);	
			
			modifyCity.addActionListener(this);
			modifyCity.setBounds(250, 400, 200, 25);
			panel.add(modifyCity);	
			
			modifyStreet.addActionListener(this);
			modifyStreet.setBounds(250, 440, 200, 25);
			panel.add(modifyStreet);	
			
			modifyPostalCode.addActionListener(this);
			modifyPostalCode.setBounds(250, 480, 200, 25);
			panel.add(modifyPostalCode);
			
			modifyStreetNumber.addActionListener(this);
			modifyStreetNumber.setBounds(250, 520, 200, 25);
			panel.add(modifyStreetNumber);
			
			// Adding ComboBox
			modifyUserRole.addItem("Simple User");
			modifyUserRole.addItem("Seller");
			modifyUserRole.addItem("Admin");
			modifyUserRole.setSelectedIndex(0);
			modifyUserRole.addActionListener(this);
			modifyUserRole.setBounds(250, 560, 200, 25);
			panel.add(modifyUserRole);	
			
			
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
			// TODO Auto-generated method stub
			Object source = e.getSource();
			if (source == returnUsersButton)
			{
				new UsersHandlerView();	
				dispose();
				System.out.println("UsersHandlerView Displayed");
			}
			else if (source == validateModifyUserButton)
			{
				String nickname = modifyUserNickname.getText();
				String password = new String(modifyUserPassword.getPassword());
				String email = modifyUserEMail.getText();
				String firstname = modifyFirstName.getText();
				String lastname = modifyLastName.getText();
				String city = modifyCity.getText();
				String street = modifyStreet.getText();
				String postalcode = modifyPostalCode.getText();
				String streetnumber = modifyStreetNumber.getText();
				
				modifyUserButtonClicked(nickname, password, email, firstname, lastname, city, street, postalcode, streetnumber);
			}
			
		}
		
		public void modifyUserButtonClicked(String nick, String pass, String email, String firstname, String lastname, String city,String street,String postalcode,String streetnumber )
		{
				
			try {
				FU.modifyUser(nick, pass, email, firstname, lastname, city, street, postalcode, streetnumber);
			} catch (ObjectNotInTheDatabaseException e) {				
				JOptionPane.showMessageDialog(null, nick+" doesn't exist in the DB.", "Modify a User", JOptionPane.ERROR_MESSAGE);

			} catch (ObjectModifiedException e) {
				if ((modifyUserRole.getSelectedItem()).equals("Admin"))
				{
					FU.chooseUserRoleAdmin(nick);
				}
				else if ((modifyUserRole.getSelectedItem()).equals("Simple User"))
				{
					FU.chooseUserRoleSimpleUser(nick);
				}
				else if ((modifyUserRole.getSelectedItem()).equals("Seller"))
				{
					FU.chooseUserRoleSeller(nick);
				}
				JOptionPane.showMessageDialog(null, nick+" has been modified successfully in the DB !", "Modify a User", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		

}
