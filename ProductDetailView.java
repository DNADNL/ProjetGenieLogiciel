import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ProductDetailView extends JFrame implements ActionListener{
	
	FacadeUser FU = FacadeUser.getFU();
	static User user;
	static Product product;
	
	//Création du panel de navigation
		JPanel panel = new JPanel();
		
	//Création des boutons de "ProductDetails"
	Button returnProductsListButton = new Button("Retour",540, 10, 150, 30);
	
	public ProductDetailView(User loggedUser, String product_name)
	{
		super("Bienvenue !");
		user = loggedUser;
		//product = FU.getProduct(product_name);
		
		// Options de la fenetre
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	
		
		// Construction du panel principal
		placeComponentsProduct(panel);
		
		// Choix du panel
		setContentPane(panel);
		
		setVisible(true);
	}
	
	private void placeComponentsProduct(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);
		
		// Font
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);
		
		// Buttons
		returnProductsListButton.addActionListener(this);
		panel.add(returnProductsListButton);
		
		
		// Textfields
		
		// Labels

		
		
		
		// Titre

		// Background

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == returnProductsListButton)
		{
			new ProductsListView(FU.getCurrentUser());	
			dispose();
			System.out.println("Panel ProductsList affiché");
		}
		
	}

}
