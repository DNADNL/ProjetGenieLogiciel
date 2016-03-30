import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SimpleUserProfileView extends JFrame implements ActionListener, KeyListener
{	
	FacadeUser FU = FacadeUser.getFU();
	User user;
	
	//Création du panel de navigation
	JPanel panel = new JPanel();

	//Création des boutons de la fenêtre
	Button returnButton = new Button("Retour",540, 10, 150, 30);
	Button modifyUserDataButton = new Button("Modifier",280, 350, 150, 30);
	JPasswordField simpleUserProfilePassword;
	JTextField simpleUserProfileFirstName;
	JTextField simpleUserProfileLastName;
	JTextField simpleUserProfileCity;
	JTextField simpleUserProfileStreet;
	JTextField simpleUserProfilePostalCode;
	JTextField simpleUserProfileStreetNumber;
	JTextField simpleUserProfileEMail;


	//Constructeur
	public SimpleUserProfileView()
	{
		super("Lazy'N Yourself");
		user=FU.getCurrentUser();
		System.out.println(user.nicknameUser);
		System.out.println(user.firstNameUser);
		System.out.println(user.getUserFirstName());

		simpleUserProfilePassword = new JPasswordField(user.getUserPassword());
		simpleUserProfileFirstName = new JTextField(user.getUserFirstName());
		simpleUserProfileLastName = new JTextField(user.getUserLastName());
		simpleUserProfileCity = new JTextField(user.getUserCity());
		simpleUserProfileStreet = new JTextField(user.getUserStreet());
		simpleUserProfilePostalCode = new JTextField(user.getUserPostalCode());
		simpleUserProfileStreetNumber = new JTextField(user.getUserStreetNumber());
		simpleUserProfileEMail = new JTextField(user.getUserEMail());

		// Options de la fenetre
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		// Construction du panel principal
		placeComponents(panel);

		// Choix du panel
		setContentPane(panel);
		getRootPane().setDefaultButton(modifyUserDataButton);

		setVisible(true);
	}

	private void placeComponents(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);


		// Font
		Font titleFont = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);

		// Buttons		
		returnButton.addActionListener(this);
		panel.add(returnButton);

		modifyUserDataButton.addActionListener(this);
		modifyUserDataButton.addKeyListener(this);
		panel.add(modifyUserDataButton);

		// Textfields
		simpleUserProfilePassword.addActionListener(this);
		simpleUserProfilePassword.setBounds(280, 100, 200, 30);
		panel.add(simpleUserProfilePassword);

		simpleUserProfileEMail.addActionListener(this);
		simpleUserProfileEMail.setBounds(280, 130, 200, 30);
		panel.add(simpleUserProfileEMail);

		simpleUserProfileFirstName.addActionListener(this);
		simpleUserProfileFirstName.setBounds(280, 160, 200, 30);
		panel.add(simpleUserProfileFirstName);

		simpleUserProfileLastName.addActionListener(this);
		simpleUserProfileLastName.setBounds(280, 190, 200, 30);
		panel.add(simpleUserProfileLastName);

		simpleUserProfileStreetNumber.addActionListener(this);
		simpleUserProfileStreetNumber.setBounds(280, 220, 200, 30);
		panel.add(simpleUserProfileStreetNumber);

		simpleUserProfileStreet.addActionListener(this);
		simpleUserProfileStreet.setBounds(280, 250, 200, 30);
		panel.add(simpleUserProfileStreet);

		simpleUserProfilePostalCode.addActionListener(this);
		simpleUserProfilePostalCode.setBounds(280, 280, 200, 30);
		panel.add(simpleUserProfilePostalCode);

		simpleUserProfileCity.addActionListener(this);
		simpleUserProfileCity.setBounds(280, 310, 200, 30);
		panel.add(simpleUserProfileCity);




		// Titre
		JLabel titleLabel = new JLabel("My Profile");
		titleLabel.setBounds(200, 50, 500, 30);
		titleLabel.setFont(titleFont);
		titleLabel.setForeground(Color.BLACK);
		panel.add(titleLabel);

		JLabel passwordLabel = new JLabel("<html>Mot de passe</html>");
		passwordLabel.setBounds(150, 100, 150, 30);
		passwordLabel.setFont(font);
		passwordLabel.setForeground(Color.BLACK);
		panel.add(passwordLabel);

		JLabel eMailLabel = new JLabel("<html>E-mail</html>");
		eMailLabel.setBounds(150, 130, 150, 30);
		eMailLabel.setFont(font);
		eMailLabel.setForeground(Color.BLACK);
		panel.add(eMailLabel);

		JLabel firstNameLabel = new JLabel("<html>Prénom</html>");
		firstNameLabel.setBounds(150, 160, 150, 30);
		firstNameLabel.setFont(font);
		firstNameLabel.setForeground(Color.BLACK);
		panel.add(firstNameLabel);

		JLabel lastNameLabel = new JLabel("<html>Nom</html>");
		lastNameLabel.setBounds(150, 190, 150, 30);
		lastNameLabel.setFont(font);
		lastNameLabel.setForeground(Color.BLACK);
		panel.add(lastNameLabel);

		JLabel streetNumberLabel = new JLabel("<html>N° Rue</html>");
		streetNumberLabel.setBounds(150, 220, 150, 30);
		streetNumberLabel.setFont(font);
		streetNumberLabel.setForeground(Color.BLACK);
		panel.add(streetNumberLabel);

		JLabel streetLabel = new JLabel("<html>Rue</html>");
		streetLabel.setBounds(150, 250, 150, 30);
		streetLabel.setFont(font);
		streetLabel.setForeground(Color.BLACK);
		panel.add(streetLabel);

		JLabel postalCodeLabel = new JLabel("<html>Code Postal</html>");
		postalCodeLabel.setBounds(150, 280, 150, 30);
		postalCodeLabel.setFont(font);
		postalCodeLabel.setForeground(Color.BLACK);
		panel.add(postalCodeLabel);

		JLabel cityLabel = new JLabel("<html>Ville</html>");
		cityLabel.setBounds(150, 310, 150, 30);
		cityLabel.setFont(font);
		cityLabel.setForeground(Color.BLACK);
		panel.add(cityLabel);		

		// Logo
		JLabel image = new JLabel(new ImageIcon("logo.png"));
		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(5, 5, 150, 150);
		panelLogo.setLayout(new BorderLayout());
		panelLogo.add(image, BorderLayout.CENTER);
		panel.add(panelLogo);
	}


	//ActionPerformMethod
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == returnButton)
		{

			new SimpleUserView();
			dispose();
			System.out.println("Panel Simple User affiché");
		}
		else if (source == modifyUserDataButton)
		{
			try {
				FU.modifyUserData(user.nicknameUser, new String(simpleUserProfilePassword.getPassword()), simpleUserProfileEMail.getText(), 
						simpleUserProfileFirstName.getText(), simpleUserProfileLastName.getText(), 
						simpleUserProfileCity.getText(), simpleUserProfileStreet.getText(), 
						simpleUserProfilePostalCode.getText(), simpleUserProfileStreetNumber.getText() );
			} catch (ObjectNotInTheDatabaseException e1) {
				JOptionPane.showMessageDialog(null, "ERREUR ! Vous n'êtes pas présent dans la BD (ce message ne devrait jamais s'afficher).", "Modifier mes informations", JOptionPane.ERROR_MESSAGE);
			} catch (ObjectModifiedException e1) {
				JOptionPane.showMessageDialog(null, "Vos informations ont bien été modifiées dans la BD !", "Modifier mes informations", JOptionPane.INFORMATION_MESSAGE);
				new SimpleUserView();
				dispose();
				System.out.println("Panel Simple User affiché");
			}
		}


	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if((arg0.getKeyCode()==KeyEvent.VK_ENTER))
		{
			try {
				FU.modifyUserData(user.nicknameUser, new String(simpleUserProfilePassword.getPassword()), simpleUserProfileEMail.getText(), 
						simpleUserProfileFirstName.getText(), simpleUserProfileLastName.getText(), 
						simpleUserProfileCity.getText(), simpleUserProfileStreet.getText(), 
						simpleUserProfilePostalCode.getText(), simpleUserProfileStreetNumber.getText() );
			} catch (ObjectNotInTheDatabaseException e1) {
				JOptionPane.showMessageDialog(null, "ERREUR ! Vous n'êtes pas présent dans la BD (ce message ne devrait jamais s'afficher).", "Modifier mes informations", JOptionPane.ERROR_MESSAGE);
			} catch (ObjectModifiedException e1) {
				JOptionPane.showMessageDialog(null, "Vos informations ont bien été modifiées dans la BD !", "Modifier mes informations", JOptionPane.INFORMATION_MESSAGE);
			}
		} 
	}


	//	private void displayAddResult(Object result)
	//	{
	//		if (result.equals("UserCreated"))
	//		{
	//			System.out.println("AddUser : Successful !");
	//			addUserResultLabel.setText("L'utilisateur a été ajouté à la BD !");
	//			addUserResultLabel.setForeground(Color.BLUE);
	//			
	//		}
	//		else
	//		{
	//			System.out.println("AddUser : Failed !");
	//			addUserResultLabel.setText("Cet utilisateur existe déjà !");
	//			addUserResultLabel.setForeground(Color.RED);
	//			//addUserResultLabel.setVisible(true);				
	//		}
	//	}

}