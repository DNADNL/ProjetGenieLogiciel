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
	
	FacadeUser FU = FacadeUser.getFU();
	static User user;
	
	//Cr�ation du panel de navigation
		JPanel panel = new JPanel();
	
	//Cr�ation des boutons de "ProductsList"
		Button returnPrincipalButton = new Button("Retour",540, 10, 150, 30);
		Button productDetailsButton = new Button("D�tails", 540, 335, 150,30);
		Button deleteProductButton = new Button("Supprimer",170,140,150,30);
		Button addProductButton = new Button("Ajouter",10,140,150,30);
		
		public ProductsListView(User loggedUser)
		{
			super("Bienvenue !");
			user = loggedUser;
			
			// Options de la fenetre
			this.setSize(700,700);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);	
			
			// Construction du panel principal
			placeComponentsProductsList(panel);
			
			// Choix du panel
			setContentPane(panel);
			
			setVisible(true);
		}
		
		private void placeComponentsProductsList(JPanel panel)
		{
			panel.removeAll();
			JPanel panTab = new JPanel();
			
			panel.setLayout(null);
			
			// Font
			Font fontTitre = new Font("Courier", Font.BOLD, 20);
			Font font = new Font("Courier", Font.BOLD, 15);
			
			// Buttons
			returnPrincipalButton.addActionListener(this);
			panel.add(returnPrincipalButton);
			
			productDetailsButton.addActionListener(this);
			panel.add(productDetailsButton);
			
			addProductButton.addActionListener(this);
			panel.add(addProductButton);
			
			deleteProductButton.addActionListener(this);
			panel.add(deleteProductButton);
			
			// Textfields
		
				
			// Labels
			
			
			// List
			
			Object[][] donnees =  {{"","","",""}};
			
			//donnees = FU.getProductList();
			
			String[] entetes = {"Nom Produit", "Brief Description", "Price", "Quantity"};
			JTable tableau = new JTable(donnees, entetes);
			
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
			// TODO Auto-generated method stub
			Object source = e.getSource();
			if (source == returnPrincipalButton)
			{
				new MainView(FU.getUser());	
				dispose();
				System.out.println("Panel Principal affich�");
			}
			else if (source == addProductButton)
			{
				new AddProductView(FU.getUser());
				dispose();
				System.out.println("Panel AddProduct affich�");
			}
			else if (source == deleteProductButton)
			{
				new DeleteProductView(FU.getUser());
				dispose();
				System.out.println("Panel DeleteProduct affich�");
			}
			else if (source == productDetailsButton)
			{
				new ProductDetailView(FU.getUser());
				dispose();
				System.out.println("Panel ProductDetails affich�");
			}
			
		}
		
		
}
