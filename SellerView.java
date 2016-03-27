import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SellerView extends JFrame implements ActionListener
{
	
	
	FacadeUser FU = FacadeUser.getFU();
	static User user;
	
	//Création du panel de navigation
	JPanel panel = new JPanel();
	
	//Création des boutons de "Principal"
	Button logoutButton = new Button("Déconnexion",540, 10, 150, 30);
	Button productListButton = new Button("Mes produits", 250, 335, 200, 30);
	
	//Constructeur
	public SellerView(User loggedUser)
	{
		super("Bienvenue !");
		user = loggedUser;
		
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
		
		// Font
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);
		
		// Buttons
		logoutButton.addActionListener(this);
		panel.add(logoutButton);
		
		productListButton.addActionListener(this);
		panel.add(productListButton);
		
		// Textfields
		
		// Labels
		JLabel userLabel = new JLabel("<html>Page de <br>" + user.nicknameUser + "</html>");
		userLabel.setBounds(10, 10, 150, 50);
		userLabel.setFont(font);
		userLabel.setForeground(Color.BLACK);
		panel.add(userLabel);
		
		// Titre

		// Background

	}


	//ActionPerformMethod
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == logoutButton)
		{
			FU.disconnectUser();
			dispose();
			new LoginView();
		}
		else if (source == productListButton)
		{
			new ProductsListView(FU.getCurrentUser());
			dispose();
			System.out.println("Panel ProductsList affiché");
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
