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

public class ShopView extends JFrame implements ActionListener
{	
	FacadeUser FU = FacadeUser.getFU();
	static User user;
	
	//Création du panel de navigation
	JPanel panel = new JPanel();
	
	//Création des boutons de "Principal"
	Button returnButton = new Button("Retour",540, 10, 150, 30);
	Button productListButton = new Button("Mes produits", 250, 335, 200, 30);

	Button adminButton = new Button("Administration", 250, 375, 200, 30);
	Button simpleUserButton = new Button("Simple User", 250, 415, 200, 30);
	
	//Constructeur
	public ShopView(User loggedUser)
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
		returnButton.addActionListener(this);
		panel.add(returnButton);
		
		productListButton.addActionListener(this);
		panel.add(productListButton);
		
		adminButton.addActionListener(this);
		panel.add(adminButton);
		
		simpleUserButton.addActionListener(this);
		panel.add(simpleUserButton);
		
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
		if (source == returnButton)
		{
			FU.disconnectUser();
			dispose();
			new LoginView();
		}
		else if (source == productListButton)
		{
			new ProductsListView();
			dispose();
			System.out.println("Panel ProductsList affiché");
		}
		else if (source == adminButton)
		{
			
			new AdminView();
			dispose();
			System.out.println("Panel Admin affiché");
		}
		else if (source == simpleUserButton)
		{
			
			new SimpleUserView(FU.getCurrentUser());
			dispose();
			System.out.println("Panel Simple User affiché");
		}
	
		
	}
}
