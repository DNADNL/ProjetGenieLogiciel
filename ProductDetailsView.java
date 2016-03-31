import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ProductDetailsView extends JFrame implements ActionListener{

	//Get the Facade
	FacadeUser FU = FacadeUser.getFU();

	//Create the panel
	JPanel panel = new JPanel();

	//Create the Buttons for "ProductDetails"
	Button returnProductsListButton = new Button("Retour",540, 10, 150, 30);

	public ProductDetailsView()
	{
		super("Lazy'N Yourself");

		// Window Options
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
	 */
	private void placeComponents(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);

		// Font
		Font fontTitle = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);

		// Buttons
		returnProductsListButton.addActionListener(this);
		panel.add(returnProductsListButton);


		// Textfields

		// Labels

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

		JLabel priceLabel = new JLabel(FU.getCurrentProduct().price.toString() + "€");
		priceLabel.setBounds(160, 210, 90, 30);
		priceLabel.setFont(font);
		priceLabel.setForeground(Color.BLACK);
		panel.add(priceLabel);

		JLabel quantityLabel = new JLabel("quantity : " + FU.getCurrentProduct().quantity.toString());
		quantityLabel.setBounds(160, 250, 200, 30);
		quantityLabel.setFont(font);
		quantityLabel.setForeground(Color.BLACK);
		panel.add(quantityLabel);

		JLabel longDescLabel = new JLabel(FU.getCurrentProduct().longDesc);
		longDescLabel.setBounds(160, 290, 200, 300);
		longDescLabel.setFont(font);
		longDescLabel.setForeground(Color.BLACK);
		panel.add(longDescLabel);

		// Title
		JLabel productDetailTitle = new JLabel();
		productDetailTitle.setBounds(250, 30, 300, 100);
		productDetailTitle.setFont(fontTitle);					
		productDetailTitle.setText("<html>Product Detail</html>");
		panel.add(productDetailTitle);	

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

	}

}
