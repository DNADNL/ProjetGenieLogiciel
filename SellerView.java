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

	//Get the Facade
	FacadeUser FU = FacadeUser.getFU();

	//Create the panel
	JPanel panel = new JPanel();


	//Create the Button for SellerView
	Button logoutButton = new Button("Déconnexion",540, 10, 150, 30);
	Button productListButton = new Button("Mes produits", 250, 335, 200, 30);

	//Constructeur
	public SellerView()
	{
		super("Lazy'N Yourself");


		// Frame Config
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	

		// Construct the panel
		placeComponentsPrincipal(panel);

		setContentPane(panel);
		setVisible(true);
	}

	//Method to construct the panel
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
		JLabel userLabel = new JLabel("<html>Page de <br>" + FU.getCurrentUser().nicknameUser + "</html>");
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

		Object source = e.getSource();

		if (source == logoutButton)
		{
			//Return to LoginView
			FU.disconnectUser();
			dispose();
			new LoginView();
		}
		else if (source == productListButton)
		{
			//Go to ProductsListView
			new ProductsListView();
			dispose();
			System.out.println("Panel ProductsList affiché");
		}		
	}

}
