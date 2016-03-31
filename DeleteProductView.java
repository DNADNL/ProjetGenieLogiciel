import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DeleteProductView extends JFrame implements ActionListener{



	//Get the Facade
	FacadeUser FU = FacadeUser.getFU();



	//Create the panel
	JPanel panel = new JPanel();



	//Create the Buttons for "DeleteProduct"
	Button returnProductsListButton = new Button("Retour",540, 10, 150, 30);
	Button validateDeleteProductButton = new Button("<html>Supprimer<br> Produit</html>",275,350,150,50);

	public DeleteProductView()
	{
		super("Lazy'N Yourself");


		// Frame Config
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	

		// Panel construction
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

		JLabel productLabel = new JLabel(FU.getCurrentProduct().pdt_name);
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

		// Logo

		JLabel image = new JLabel(new ImageIcon("logo.png"));
		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(5, 5, 150, 150);
		panelLogo.setLayout(new BorderLayout());
		panelLogo.add(image, BorderLayout.CENTER);
		panel.add(panelLogo);
	}




	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		//The seller has chosen to return
		if (source == returnProductsListButton)
		{
			//Return to the ProductListView
			new ProductsListView();	
			dispose();
			System.out.println("Panel ProductsList affiché");
		}
		//The seller has chosen to delete the product
		else if (source==validateDeleteProductButton)
		{
			deleteProductButtonClicked(FU.getCurrentProduct().pdt_name);

			//Return to the ProductListView
			new ProductsListView();	
			dispose();
		}
	}

	//Handle the removal of the product from the Database
	public void deleteProductButtonClicked(String pdt_name)
	{
		try {
			FU.deleteProduct(pdt_name);
		} catch (ObjectNotInTheDatabaseException e) {
			JOptionPane.showMessageDialog(null, pdt_name+" n'existe pas dans la BD.", "Suppression de produit", JOptionPane.ERROR_MESSAGE);

		} catch (ObjectDeletedException e) {
			JOptionPane.showMessageDialog(null, pdt_name+" a bien été supprimé de la BD !", "Suppression de produit", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
