import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DeleteProductView extends JFrame implements ActionListener{

	FacadeUser FU = FacadeUser.getFU();
	static User user;
	static String pdt_name;
	
	//Création du panel de navigation
	JPanel panel = new JPanel();
	
	//Création des boutons de "DeleteProduct"
	Button returnProductsListButton = new Button("Retour",540, 10, 150, 30);
		Button validateDeleteProductButton = new Button("<html>Supprimer<br> Produit</html>",275,350,150,50);
		
		public DeleteProductView(User loggedUser, String product_selected)
		{
			super("Bienvenue !");
			user = loggedUser;
			pdt_name = product_selected;
			
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
		JLabel deleteProductLabel = new JLabel("Êtes vous sûr de vouloir supprimer le produit suivant :");
		deleteProductLabel.setBounds(100, 130, 500, 30);
		deleteProductLabel.setFont(font);
		deleteProductLabel.setForeground(Color.BLACK);
		panel.add(deleteProductLabel);
		
		JLabel productLabel = new JLabel(pdt_name);
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
			new ProductsListView(FU.getCurrentUser());	
			dispose();
			System.out.println("Panel ProductsList affiché");
		}
		else if (source==validateDeleteProductButton)
		{
			deleteProductButtonClicked(pdt_name);
		}
	}
	
	public void deleteProductButtonClicked(String pdt_name)
	{
		try {
			FU.deleteProduct(pdt_name);
		} catch (UserNotInTheDatabaseException e) {
			JOptionPane.showMessageDialog(null, pdt_name+" n'existe pas dans la BD.", "Suppression de produit", JOptionPane.ERROR_MESSAGE);

		} catch (UserDeletedException e) {
			JOptionPane.showMessageDialog(null, pdt_name+" a bien été supprimé de la BD !", "Suppression de produit", JOptionPane.INFORMATION_MESSAGE);
			new ProductsListView(FU.getCurrentUser());	
			dispose();

		}
	}
}
