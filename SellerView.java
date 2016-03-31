import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
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
	private void placeComponents(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);

		// Font



		// Buttons
		logoutButton.addActionListener(this);
		panel.add(logoutButton);

		productListButton.addActionListener(this);
		panel.add(productListButton);

		// Textfields

		// Labels

		// Titre

		// Logo
		
		JLabel image = new JLabel(new ImageIcon("logo.png"));
		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(10, 10, 150, 150);
		panelLogo.setLayout(new BorderLayout());
		panelLogo.add(image, BorderLayout.CENTER);
		panel.add(panelLogo);

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
