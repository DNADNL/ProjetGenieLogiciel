import java.awt.BorderLayout;
import java.awt.Color;
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
public class AddGoalView extends JFrame implements ActionListener
{	
	FacadeUser FU = FacadeUser.getFU();

	//Création du panel de navigation
	JPanel panel = new JPanel();


	//création du bouton pour ajouter un utilisateur et des champs à rentrer
	Button returnUsersButton = new Button("Retour", 540, 10, 150, 30);
	Button validateAddUserButton = new Button("Ajouter", 250, 270, 200, 30);

	// Création des champs de textes pour completer les attributs d'un objectif
	JTextField addGoalTitle = new JTextField("Goal Title");
	JTextField addGoalDescription = new JTextField("Goal Description");

	//Constructeur
	public AddGoalView()
	{
		super("Lazy'N Yourself");

		// Options de la fenetre
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	

		// Construction du panel principal
		placeComponentsPrincipal(panel);

		// Choix du panel
		setContentPane(panel);

		setVisible(true);
	}

	private void placeComponentsPrincipal(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);

		// Ajout du Bouton Retour
		returnUsersButton.addActionListener(this);
		panel.add(returnUsersButton);

		// Ajout du Bouton Ajouter
		validateAddUserButton.addActionListener(this);
		panel.add(validateAddUserButton);	

		// Ajout des champs à rentrer
		addGoalTitle.addActionListener(this);
		addGoalTitle.setBounds(250, 140, 200, 25);
		panel.add(addGoalTitle);

		addGoalDescription.addActionListener(this);
		addGoalDescription.setBounds(250, 180, 200, 25);
		panel.add(addGoalDescription);



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
		if (source == validateAddUserButton)
		{
			String goal_title = addGoalTitle.getText();
			String goal_description = addGoalDescription.getText();
			String nick = FU.getCurrentUser().nicknameUser;
			addUserButtonClicked(goal_title, goal_description, nick);
		}
		else if (source == returnUsersButton)
		{
			new SimpleUserView();	
			dispose();
			System.out.println("Panel Admin affiché");
		}


	}

	public void addUserButtonClicked(String goal_title, String goal_description, String nick)
	{
		try{
			FU.addGoal(goal_title, goal_description, nick);
		} catch (ObjectCreatedException e) {
			JOptionPane.showMessageDialog(null, goal_title+" a bien été ajouté à la BD !", "Ajout d'un goal", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (ObjectAlreadyExistsException e) {
			JOptionPane.showMessageDialog(null, "Ce Goal existe déjà dans la BD.", "Ajout d'utilisateur", JOptionPane.ERROR_MESSAGE);
			//				System.out.println("AddUser : Failed !");
			//				addUserResultLabel.setText("Cet utilisateur existe déjà !");
			//				addUserResultLabel.setForeground(Color.RED);
		}
	}


}