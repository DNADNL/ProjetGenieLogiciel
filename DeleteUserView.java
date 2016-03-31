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

@SuppressWarnings("serial")
public class DeleteUserView  extends JFrame implements ActionListener{

	FacadeUser FU = FacadeUser.getFU();


	//Création du panel de navigation
	JPanel panel = new JPanel();

	//Create the Buttons for "DeleteUserView
	Button returnUsersButton = new Button("Retour", 540, 10, 150, 30);
	Button validateDeleteUserButton = new Button("Supprimer",250, 270, 200, 30);

	//Create the textfields for DeleteUserView
	JTextField deleteUserNickname = new JTextField("pseudo");
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

		setContentPane(panel);
		setVisible(true);
	}
	
	/**
	 * This method places all the components onto the panel.
	 * <p>
	 *
	 * @param  		panel	(a {@link JPanel} giving the the panel where to place components)
	 * @return      void
	 */
	private void placeComponents(JPanel panel) {

		panel.removeAll();
		panel.setLayout(null);

		// Font
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		//Font font = new Font("Courier", Font.BOLD, 15);
		//Font fontAdvice = new Font("Courier", Font.ITALIC, 14);

		//Ajout de l'étiquette "Suppression d'utilisateur"
		JLabel deleteUserTitle = new JLabel();
		deleteUserTitle.setBounds(250, 30, 300, 100);
		deleteUserTitle.setFont(fontTitre);					
		deleteUserTitle.setText("<html>Suppression d'utilisateur</html>");
		panel.add(deleteUserTitle);	

		// Ajout du Bouton Retour
		returnUsersButton.addActionListener(this);
		panel.add(returnUsersButton);

		// Ajout du Bouton Supprimer
		validateDeleteUserButton.addActionListener(this);
		panel.add(validateDeleteUserButton);	

		// Ajout des champs à rentrer
		deleteUserNickname.addActionListener(this);
		deleteUserNickname.setBounds(250, 100, 200, 25);
		panel.add(deleteUserNickname);

		deleteUserEMail.addActionListener(this);
		deleteUserEMail.setBounds(250, 140, 200, 25);
		panel.add(deleteUserEMail);			


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
		if (source == validateDeleteUserButton)
		{
			String nickname = deleteUserNickname.getText();
			deleteUserButtonClicked(nickname);
		}
		else if (source == returnUsersButton)
		{
			new UsersHandlerView();	
			dispose();
			System.out.println("Panel Admin affiché");
		}
	}

	public void deleteUserButtonClicked(String nick)
	{
		try {
			FU.deleteUser(nick);
		} catch (ObjectNotInTheDatabaseException e) {
			JOptionPane.showMessageDialog(null, nick+" n'existe pas dans la BD.", "Suppression d'utilisateur", JOptionPane.ERROR_MESSAGE);
			//				System.out.println("DeleteUser : Failed !");
			//				deleteUserResultLabel.setText("Aucun utilisateur avec ce pseudo n'existe dans la BD, veuillez réessayer.");
			//				deleteUserResultLabel.setForeground(Color.RED);
		} catch (ObjectDeletedException e) {
			JOptionPane.showMessageDialog(null, nick+" a bien été supprimé de la BD !", "Suppression d'utilisateur", JOptionPane.INFORMATION_MESSAGE);
			//				System.out.println("DeleteUser : Successful !");
			//				deleteUserResultLabel.setText("L'utilisateur a bien été supprimé !");
			//				deleteUserResultLabel.setForeground(Color.BLUE);
		}
	}

}
