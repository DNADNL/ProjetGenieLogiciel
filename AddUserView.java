import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddUserView  extends JFrame implements ActionListener{
	
	FacadeUser FU = FacadeUser.getFU();
	static User user;
	
	//Création du panel de navigation
			JPanel panel = new JPanel();
	
	//création du bouton pour ajouter un utilisateur et des champs à rentrer
	Button returnUsersButton = new Button("Retour", 540, 10, 150, 30);
		Button validateAddUserButton = new Button("Ajouter", 250, 270, 200, 30);
		
	//JLabel addUserResultLabel = new JLabel("Les résultats seront affichés ici");
		
		JTextField addUserNickname = new JTextField("pseudo");
		JPasswordField addUserPassword = new JPasswordField("mot_de_passe");
		JTextField addUserEMail = new JTextField("e-mail");
		
		public AddUserView(User loggedUser)
		{
			super("Bienvenue !");
			user = loggedUser;
			
			// Options de la fenetre
			this.setSize(700,700);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);	
			
			// Construction du panel principal
			placeComponentsAddUser(panel);
			
			// Choix du panel
			setContentPane(panel);
			
			setVisible(true);
		}

		private void placeComponentsAddUser(JPanel panel) {

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
			
			//Ajout de l'étiquette "Ajout d'utilisateur"
			JLabel addUserTitle = new JLabel();
			addUserTitle.setBounds(250, 30, 300, 100);
			addUserTitle.setFont(fontTitre);					
			addUserTitle.setText("<html>Ajout d'utilisateur</html>");
			panel.add(addUserTitle);		
			
			//Ajout de l'étiquette "Résultat Ajout d'utilisateur"
			
//			addUserResultLabel.setText("Les résultats seront affichés ici");
//			addUserResultLabel.setForeground(Color.BLACK);
//			addUserResultLabel.setBounds(250, 10, 300, 100);
//			addUserResultLabel.setFont(fontAdvice);					
//			addUserResultLabel.setVisible(true);
//			panel.add(addUserResultLabel);
			
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
			
			
			panel.setLayout(new BorderLayout());
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();
			if (source == validateAddUserButton)
			{
				String nickname = addUserNickname.getText();
				String password = new String(addUserPassword.getPassword());
				String email = addUserEMail.getText();
				addUserButtonClicked(nickname, password, email);
			}
			else if (source == returnUsersButton)
			{
				new UsersHandlerView(FU.getUser());	
				dispose();
				System.out.println("Panel Admin affiché");
			}
			
		}
		
		public void addUserButtonClicked(String nick, String pass, String email)
		{
			Object resultAddUser = null;
			
			try {
				FU.addUser(nick, pass, email);
			} catch (UserCreatedException e) {
				JOptionPane.showMessageDialog(null, nick+" a bien été ajouté à la BD !", "Ajout d'utilisateur", JOptionPane.INFORMATION_MESSAGE);
//				System.out.println("AddUser : Successful !");
//				addUserResultLabel.setText("L'utilisateur a été ajouté à la BD !");
//				addUserResultLabel.setForeground(Color.BLUE);
			} catch (UserAlreadyExistsException e) {
				JOptionPane.showMessageDialog(null, nick+" existe déjà dans la BD.", "Ajout d'utilisateur", JOptionPane.ERROR_MESSAGE);
//				System.out.println("AddUser : Failed !");
//				addUserResultLabel.setText("Cet utilisateur existe déjà !");
//				addUserResultLabel.setForeground(Color.RED);
			}
		}
		
}
