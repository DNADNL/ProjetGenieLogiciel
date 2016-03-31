package sellerUI;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import exceptions.*;
import facade.*;
import miscellaneous.*;

// The Add Product View

@SuppressWarnings("serial")
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

	public AddProductView()
	{
		super("Lazy'N Yourself");

		// Frame config
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
	 *
	 * @param  		panel	(a {@link JPanel} giving the the panel where to place components)
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
		priceLabel.setBounds(160, 250, 90, 30);
		priceLabel.setFont(font);
		priceLabel.setForeground(Color.BLACK);
		panel.add(priceLabel);

		JLabel quantityLabel = new JLabel("Quantité");
		quantityLabel.setBounds(160, 210, 90, 30);
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
		//The seller has chosen to add a product
		else if (source == validateAddProductButton)
		{
			//We get all the needed informations from the TextFields
			String nickname = FU.getCurrentUser().nicknameUser;

			String pdt_name = nameAddProduct.getText();
			String pdt_briefDesc = briefDescAddProduct.getText();
			String pdt_longDesc = longDescAddProduct.getText();
			String stringPdt_quantity = quantityAddProduct.getText();
			String stringPdt_price = priceAddProduct.getText();
			
			//This method handles the tests and the adding of the product
			addProductButtonClicked(nickname, pdt_name, pdt_briefDesc, pdt_longDesc, stringPdt_quantity, stringPdt_price);

			
		}


	}

	private void addProductButtonClicked(String nickname, String pdt_name, String pdt_briefDesc, String pdt_longDesc, String stringPdt_quantity, String stringPdt_price)
	{
		

		try 
		{
			//Test if all the fields are correctly filled
			FU.verifyAddProductFields(pdt_name, pdt_briefDesc, pdt_longDesc, stringPdt_quantity, stringPdt_price);
			//Test if the product already exists
			FU.verifyAlreadyExists(nickname,pdt_name);
		} 
		catch (EmptyFieldsException e) 
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Please fill all the fields before you confirm", "Empty fields", JOptionPane.ERROR_MESSAGE);
		} 
		catch (NotExpectedValueException e) 
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getName() + " must be an Integer", "Incorrect value", JOptionPane.ERROR_MESSAGE);
		}
		catch (ObjectAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Your product " + e1.getName() + " already exists", "Already exists", JOptionPane.ERROR_MESSAGE);
		}
		catch (ObjectNotInTheDatabaseException e1) {
			// TODO Auto-generated catch block

			//Set the right type
			int pdt_quantity = Integer.parseInt(stringPdt_quantity);
			int pdt_price = Integer.parseInt(stringPdt_price);
			
			//Add the product to the database
			FU.addProduct(nickname, pdt_name, pdt_quantity, pdt_price, pdt_briefDesc, pdt_longDesc);
			
			JOptionPane.showMessageDialog(null, "Your product " + e1.getName() + " has been added", "Product added", JOptionPane.INFORMATION_MESSAGE);
			
			//Return to the ProductListView
			new ProductsListView();	
			dispose();

		}
	}
}
