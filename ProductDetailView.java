import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProductDetailView extends JFrame implements ActionListener{
	
	FacadeUser FU = FacadeUser.getFU();
	
	//Création du panel de navigation
		JPanel panel = new JPanel();
		
	//Création des boutons de "ProductDetails"
	Button returnProductsListButton = new Button("Retour",540, 10, 150, 30);
	
	public ProductDetailView()
	{
		super("Bienvenue !");
		
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
					System.out.println("test");
					JLabel nameLabel = new JLabel(FU.getCurrentProduct().pdt_name);
					nameLabel.setBounds(160, 100, 90, 30);
					nameLabel.setFont(font);
					nameLabel.setForeground(Color.BLACK);
					panel.add(nameLabel);
					
					JLabel briefDescLabel = new JLabel(FU.getCurrentProduct().briefDesc);
					briefDescLabel.setBounds(160, 140, 200, 200);
					briefDescLabel.setFont(font);
					briefDescLabel.setForeground(Color.BLACK);
					panel.add(briefDescLabel);
					
					JLabel priceLabel = new JLabel(FU.getCurrentProduct().price.toString());
					priceLabel.setBounds(160, 210, 90, 30);
					priceLabel.setFont(font);
					priceLabel.setForeground(Color.BLACK);
					panel.add(priceLabel);
					
					JLabel quantityLabel = new JLabel(FU.getCurrentProduct().quantity.toString());
					quantityLabel.setBounds(160, 250, 90, 30);
					quantityLabel.setFont(font);
					quantityLabel.setForeground(Color.BLACK);
					panel.add(quantityLabel);
					
					JLabel longDescLabel = new JLabel(FU.getCurrentProduct().longDesc);
					longDescLabel.setBounds(160, 290, 200, 300);
					longDescLabel.setFont(font);
					longDescLabel.setForeground(Color.BLACK);
					panel.add(longDescLabel);
		
		
		
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
