import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ProductsListView extends JFrame implements ActionListener{
	
	//Get the Facade
		FacadeUser FU = FacadeUser.getFU();

	//Create the panel
		JPanel panel = new JPanel();
	
	//Création des boutons de "ProductsList"
		Button returnButton = new Button("Retour",540, 10, 150, 30);
		Button productDetailsButton = new Button("Détails", 540, 335, 150,30);
		Button deleteProductButton = new Button("Supprimer",170,140,150,30);
		Button addProductButton = new Button("Ajouter",10,140,150,30);
		
		JTable tableau;
		
		public ProductsListView(User loggedUser)
		{
			super("Lazy'N Yourself");
			
			// Frame Config
			this.setSize(700,700);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);	
			
			// Construct the panel
			placeComponentsProductsList(panel);
			
			setContentPane(panel);
			setVisible(true);
		}
		
		//Method to construct the panel
		private void placeComponentsProductsList(JPanel panel)
		{
			panel.removeAll();
			JPanel panTab = new JPanel();
			
			panel.setLayout(null);
			
			// Font
			Font fontTitre = new Font("Courier", Font.BOLD, 20);
			Font font = new Font("Courier", Font.BOLD, 15);
			
			// Buttons
			returnButton.addActionListener(this);
			panel.add(returnButton);
			
			productDetailsButton.addActionListener(this);
			panel.add(productDetailsButton);
			
			addProductButton.addActionListener(this);
			panel.add(addProductButton);
			
			deleteProductButton.addActionListener(this);
			panel.add(deleteProductButton);
			
			// Textfields
		
				
			// Labels
			
			
			// List
			
			Object[][] donnees;
			
			donnees = FU.getStringProductList();
			
			String[] entetes = {"Nom Produit", "Brief Description"};
			tableau = new JTable(donnees, entetes);
			
			JScrollPane test = new JScrollPane(tableau);
			panTab.setLayout(new BorderLayout());
			
			panTab.add(test, BorderLayout.CENTER);
			panTab.setBounds(10, 190, 400, 410);
			panel.add(panTab);

			// Titre
			
			JLabel productsListTitle = new JLabel();
			productsListTitle.setBounds(250, 30, 300, 100);
			productsListTitle.setFont(fontTitre);					
			productsListTitle.setText("Liste des produits");
			panel.add(productsListTitle);

			// Background


		}

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();
			//The seller has chosen to return
			if (source == returnButton)
			{
				//Return to the SellerView
				new SellerView(FU.getCurrentUser());	
				dispose();
				System.out.println("Panel Seller affiché");
			}
			//The seller has chosen to add a product
			else if (source == addProductButton)
			{
				//Go to the AddProductView
				new AddProductView(FU.getCurrentUser());
				dispose();
				System.out.println("Panel AddProduct affiché");
			}
			//The seller has chosen to delete the selected product
			else if (source == deleteProductButton)
			{
				if (tableau.getSelectedRow() != -1)
				{
					//Get the information for the product in the selected cell
					String product_selected = (tableau.getValueAt(tableau.getSelectedRow(), 0).toString());
					//Go to the AddProductView
					new DeleteProductView(FU.getCurrentUser(), product_selected);
					dispose();
					System.out.println("Panel DeleteProduct affiché");
				}
				
			}
			//The seller has chosen to see the product
			else if (source == productDetailsButton)
			{
				if (tableau.getSelectedRow() != -1)
				{
					//Get the information for the product in the selected cell
					String product_selected = (tableau.getValueAt(tableau.getSelectedRow(), 0).toString());
					
					//Modify the product selected in ProductsHandler
					FU.modifyCurrentProduct(product_selected, FU.getCurrentUser().nicknameUser);
					
					//Go to the ProductDetailView
					new ProductDetailView();
					dispose();
					System.out.println("Panel Details Product affiché");
				}
			}
			
		}
		
		
}
