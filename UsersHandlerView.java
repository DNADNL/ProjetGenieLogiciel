import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UsersHandlerView extends JFrame implements ActionListener{
	
	FacadeUser FU = FacadeUser.getFU();
	static User user;
	
	//Création du panel de navigation
		JPanel panel = new JPanel();
	
	//Création des boutons de "UsersHandler"
		Button returnAdminButton = new Button("Retour",540, 10, 150, 30);
		Button addUserButton = new Button("Ajouter", 250, 270, 200, 30);
		Button deleteUserButton = new Button("Supprimer",250, 310, 200, 30);
		Button modifyUserButton = new Button("Modifier",250, 350, 200, 30);
		
		public UsersHandlerView(User loggedUser)
		{
			super("Bienvenue !");
			user = loggedUser;
			
			// Options de la fenetre
			this.setSize(700,700);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);	
			
			// Construction du panel principal
			placeComponentsUsersHandler(panel);
			
			// Choix du panel
			setContentPane(panel);
			
			setVisible(true);
		}
		
		
		
		private void placeComponentsUsersHandler(JPanel panel) {

		  	panel.removeAll();
			panel.setLayout(null);
			Font fontTitre = new Font("Courier", Font.BOLD, 20);
			Font font = new Font("Courier", Font.BOLD, 15);
			
			//Ajout de l'étiquette "Page de xxx"
			JLabel idLabel = new JLabel("<html>Page de <br>" + user.nicknameUser + "</html>");
			idLabel.setBounds(10, 10, 150, 50);
			idLabel.setFont(font);
			idLabel.setForeground(Color.BLACK);
			panel.add(idLabel);
			
			//Ajout de l'étiquette "Outils Action Utilisateur"
			JLabel UsersHandlerTitle = new JLabel();
			UsersHandlerTitle.setBounds(250, 30, 300, 100);
			UsersHandlerTitle.setFont(fontTitre);					
			UsersHandlerTitle.setText("<html>Gestion des Utilisateur</html>");
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
			// TODO Auto-generated method stub
			Object source = e.getSource();
			if (source == returnAdminButton)
			{
				dispose();
				new AdminView(FU.getUser());
				System.out.println("Panel Admin affiché");
			}
			else if (source == deleteUserButton)
			{
				dispose();
				new DeleteUserView(FU.getUser());
				System.out.println("Panel DeleteUser affiché");
			}
			else if (source == modifyUserButton)
			{
				dispose();
				new ModifyUserView(FU.getUser());
				System.out.println("Panel ModifyUser affiché");
			}
			else if (source == addUserButton)
			{
				dispose();
				new AddUserView(FU.getUser());
				System.out.println("Panel AddUser affiché");
			}
			
		}
	 

}
