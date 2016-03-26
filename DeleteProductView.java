import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DeleteProductView extends JFrame implements ActionListener{

	FacadeUser FU = FacadeUser.getFU();
	static User user;
	
	//Cr�ation du panel de navigation
	JPanel panel = new JPanel();
	
	//Cr�ation des boutons de "DeleteProduct"
	Button returnProductsListButton = new Button("Retour",540, 10, 150, 30);
		Button validateDeleteProductButton = new Button("<html>Supprimer<br> Produit</html>",275,350,150,50);
		
		public DeleteProductView(User loggedUser)
		{
			super("Bienvenue !");
			user = loggedUser;
			
			// Options de la fenetre
			this.setSize(700,700);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);	
			
			// Construction du panel principal
			placeComponentsDeleteProduct(panel);
			
			// Choix du panel
			setContentPane(panel);
			
			setVisible(true);
		}
		
		private void placeComponentsDeleteProduct(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);
		
		// Font
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);
		
		// Buttons
		returnProductsListButton.addActionListener(this);
		panel.add(returnProductsListButton);
		
		validateDeleteProductButton.addActionListener(this);
		panel.add(validateDeleteProductButton);
		
		
		// Textfields

		
		// Labels
		JLabel deleteProductLabel = new JLabel("�tes vous s�r de vouloir supprimer le produit suivant :");
		deleteProductLabel.setBounds(100, 130, 500, 30);
		deleteProductLabel.setFont(font);
		deleteProductLabel.setForeground(Color.BLACK);
		panel.add(deleteProductLabel);
		
		JLabel productLabel = new JLabel("Un tel produit");
		productLabel.setBounds(120, 170, 460, 60);
		productLabel.setFont(font);
		productLabel.setForeground(Color.BLACK);
		panel.add(productLabel);
		
		
		
		// Titre
		
		JLabel deleteProductTitle = new JLabel();
		deleteProductTitle.setBounds(250, 30, 300, 100);
		deleteProductTitle.setFont(fontTitre);					
		deleteProductTitle.setText("Suppression Produit");
		panel.add(deleteProductTitle);

		// Background

	}
	
	
		
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == returnProductsListButton)
		{
			new ProductsListView(FU.getUser());	
			dispose();
			System.out.println("Panel ProductsList affich�");
		}
	}

}
