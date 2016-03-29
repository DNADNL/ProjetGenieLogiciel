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

public class ModifyUserView  extends JFrame implements ActionListener{
	
	FacadeUser FU = FacadeUser.getFU();
	static User user;
	
	//Création du panel de navigation
			JPanel panel = new JPanel();
	
	//Création des boutons et Textfield de "ModifiyUser"
		Button returnUsersButton = new Button("Retour", 540, 10, 150, 30);
		Button validateModifyUserButton = new Button("Modifier",250, 600, 200, 30);
		

		JTextField modifyUserNickname = new JTextField("pseudo");
		JPasswordField modifyUserPassword = new JPasswordField("mot_de_passe");
		JTextField modifyUserEMail = new JTextField("e-mail");
		
		JTextField modifyFirstName = new JTextField("FirstName");
		JTextField modifyLastName = new JTextField("LastName");
		JTextField modifyCity = new JTextField("City");
		JTextField modifyStreet = new JTextField("Street");
		
		JTextField modifyPostalCode = new JTextField("Postal Code");
		JTextField modifyStreetNumber = new JTextField("Street Number");
		
		JComboBox<String> modifyUserRole = new JComboBox<String>();
		
		
		public ModifyUserView(User loggedUser)
		{
			super("Lazy'N Yourself");
			user = loggedUser;
			
			// Options de la fenetre
			this.setSize(700,700);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);	
			
			// Construction du panel principal
			placeComponentsModifyUser(panel);
			
			// Choix du panel
			setContentPane(panel);
			
			setVisible(true);
		}
		
		private void placeComponentsModifyUser(JPanel panel) {

		  	panel.removeAll();
			panel.setLayout(null);
			Font fontTitre = new Font("Courier", Font.BOLD, 20);
			Font font = new Font("Courier", Font.BOLD, 15);
			Font fontAdvice = new Font("Courier", Font.ITALIC, 14);
			
			//Ajout de l'étiquette "Page de xxx"
			JLabel idLabel = new JLabel("<html>Page de <br>" + user.nicknameUser + "</html>");
			idLabel.setBounds(10, 10, 150, 50);
			idLabel.setFont(font);
			idLabel.setForeground(Color.BLACK);
			panel.add(idLabel);
			
			//Ajout de l'étiquette "Modification d'utilisateur"
			JLabel modifyUserTitle = new JLabel();
			modifyUserTitle.setBounds(250, 20, 300, 100);
			modifyUserTitle.setFont(fontTitre);					
			modifyUserTitle.setText("<html>Modification d'utilisateur</html>");
			panel.add(modifyUserTitle);		
			
			//Ajout de l'étiquette "Choisissez un pseudo à modifier"
			JLabel modifiyPseudoTitle = new JLabel();
			modifiyPseudoTitle.setBounds(250, 70, 300, 100);
			modifiyPseudoTitle.setFont(font);					
			modifiyPseudoTitle.setText("Choisissez un pseudo à modifier");
			panel.add(modifiyPseudoTitle);
			
			//Ajout de l'étiquette "Choisissez un pseudo à modifier"
			JLabel modifiyDetailProfil = new JLabel();
			modifiyDetailProfil.setBounds(250, 170, 360, 100);
			modifiyDetailProfil.setFont(font);					
			modifiyDetailProfil.setText("Indiquez les informations à modifier");
			panel.add(modifiyDetailProfil);	
			
			// Ajout du Bouton Retour
			returnUsersButton.addActionListener(this);
			panel.add(returnUsersButton);

			// Ajout du Bouton Modifier
			validateModifyUserButton.addActionListener(this);
			panel.add(validateModifyUserButton);	
			
			// Ajout des champs à rentrer
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
			
			// Ajout de la liste de rôles
			modifyUserRole.addItem("Simple Utilisateur");
			modifyUserRole.addItem("Vendeur");
			modifyUserRole.addItem("Administrateur");
			modifyUserRole.setSelectedIndex(0);
			modifyUserRole.addActionListener(this);
			modifyUserRole.setBounds(250, 560, 200, 25);
			panel.add(modifyUserRole);	
			
			
			panel.setLayout(new BorderLayout());
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();
			if (source == returnUsersButton)
			{
				new UsersHandlerView();	
				dispose();
				System.out.println("Panel Admin affiché");
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
				JOptionPane.showMessageDialog(null, nick+" n'existe pas dans la BD.", "Modification d'utilisateur", JOptionPane.ERROR_MESSAGE);
//				System.out.println("DeleteUser : Failed !");
//				deleteUserResultLabel.setText("Aucun utilisateur avec ce pseudo n'existe dans la BD, veuillez réessayer.");
//				deleteUserResultLabel.setForeground(Color.RED);
			} catch (ObjectModifiedException e) {
				if ((modifyUserRole.getSelectedItem()).equals("Administrateur"))
				{
					FU.chooseUserRoleAdmin(nick);
				}
				else if ((modifyUserRole.getSelectedItem()).equals("Simple Utilisateur"))
				{
					FU.chooseUserRoleSimpleUser(nick);
				}
				else if ((modifyUserRole.getSelectedItem()).equals("Vendeur"))
				{
					FU.chooseUserRoleSeller(nick);
				}
				JOptionPane.showMessageDialog(null, nick+" a bien été modifé dans la BD !", "Modification d'utilisateur", JOptionPane.INFORMATION_MESSAGE);
//				System.out.println("DeleteUser : Successful !");
//				deleteUserResultLabel.setText("L'utilisateur a bien été supprimé !");
//				deleteUserResultLabel.setForeground(Color.BLUE);
			}
			
		}
		

}
