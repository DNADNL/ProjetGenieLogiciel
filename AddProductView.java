import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddProductView extends JFrame implements ActionListener{

		//Get the Facade
		FacadeUser FU = FacadeUser.getFU();

		//Create the panel
		JPanel panel = new JPanel();
	
		//Create the Buttons "AddProduct"
		Button returnProductsListButton = new Button("Retour",540, 10, 150, 30);
		Button validateAddProductButton = new Button("<html>Ajouter<br> Produit</html>",275,350,150,50);
		
		//Create the Textfields
		JTextField nameAddProduct = new JTextField("nom produit");
		JTextArea briefDescAddProduct = new JTextArea("Brief Description");
		JTextArea longDescAddProduct = new JTextArea("Long Description");
		JTextField quantityAddProduct = new JTextField("0");
		JTextField priceAddProduct = new JTextField("0");
		
		public AddProductView(User loggedUser)
		{
			super("Lazy'N Yourself");
			
			// Frame config
			this.setSize(700,700);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);	
			
			// Panel construction
			placeComponentsAddProduct(panel);
			
			setContentPane(panel);
			setVisible(true);
		}
		
		//Method to construct the panel
		private void placeComponentsAddProduct(JPanel panel)
		{
			panel.removeAll();
			panel.setLayout(null);
			
			// Font
			Font fontTitre = new Font("Courier", Font.BOLD, 20);
			Font font = new Font("Courier", Font.BOLD, 15);
			
			// Buttons
			returnProductsListButton.addActionListener(this);
			panel.add(returnProductsListButton);
			
			validateAddProductButton.addActionListener(this);
			panel.add(validateAddProductButton);
			
			
			
			// Textfields TextAreas
			nameAddProduct.setBounds(270, 100, 160, 30);
			panel.add(nameAddProduct);
			
			briefDescAddProduct.setBounds(270, 140, 160, 60);
			panel.add(briefDescAddProduct);
			
			quantityAddProduct.setBounds(270, 210, 90, 30);
			panel.add(quantityAddProduct);
			
			priceAddProduct.setBounds(270, 250, 90, 30);
			panel.add(priceAddProduct);
			
			longDescAddProduct.setBounds(270, 310, 160, 30);
			panel.add(longDescAddProduct);
			
			// Labels
			JLabel nameLabel = new JLabel("Nom");
			nameLabel.setBounds(160, 100, 90, 30);
			nameLabel.setFont(font);
			nameLabel.setForeground(Color.BLACK);
			panel.add(nameLabel);
			
			JLabel briefDescLabel = new JLabel("<html>Brief <br> description</html>");
			briefDescLabel.setBounds(160, 140, 100, 60);
			briefDescLabel.setFont(font);
			briefDescLabel.setForeground(Color.BLACK);
			panel.add(briefDescLabel);
			
			JLabel priceLabel = new JLabel("Prix");
			priceLabel.setBounds(160, 210, 90, 30);
			priceLabel.setFont(font);
			priceLabel.setForeground(Color.BLACK);
			panel.add(priceLabel);
			
			JLabel quantityLabel = new JLabel("Quantité");
			quantityLabel.setBounds(160, 250, 90, 30);
			quantityLabel.setFont(font);
			quantityLabel.setForeground(Color.BLACK);
			panel.add(quantityLabel);
			
			JLabel longDescLabel = new JLabel("<html>Long <br> description</html>");
			longDescLabel.setBounds(160, 290, 100, 60);
			longDescLabel.setFont(font);
			longDescLabel.setForeground(Color.BLACK);
			panel.add(longDescLabel);
			
			
			
			// Titre
			
			JLabel addProductTitle = new JLabel();
			addProductTitle.setBounds(250, 30, 300, 100);
			addProductTitle.setFont(fontTitre);					
			addProductTitle.setText("Ajout Produit");
			panel.add(addProductTitle);

			// Background

		}
			
		
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		//The seller has chosen to return
		if (source == returnProductsListButton)
		{
			//Return to the ProductListView
			new ProductsListView(FU.getCurrentUser());	
			dispose();
			System.out.println("Panel ProductsList affiché");
		}
		//The seller has chosen to add a product
		else if (source == validateAddProductButton)
		{
			//We get all the needed informations from the TextFields
			String nickname = FU.getCurrentUser().nicknameUser;
			String pdt_name = nameAddProduct.getText();
			String pdt_briefDesc = briefDescAddProduct.getText();
			String pdt_longDesc = longDescAddProduct.getText();
			int pdt_quantity = Integer.parseInt(quantityAddProduct.getText());
			int pdt_price = Integer.parseInt(priceAddProduct.getText());
			
			//Call the function to add a product
			FU.addProduct(nickname, pdt_name, pdt_quantity, pdt_price, pdt_briefDesc, pdt_longDesc);
			System.out.println("Produit ajouté");
			
			//Return to the ProductListView
			new ProductsListView(FU.getCurrentUser());	
			dispose();
		}
		
		
	}

}
