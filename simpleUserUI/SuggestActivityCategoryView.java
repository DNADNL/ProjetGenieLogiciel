package simpleUserUI;
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

@SuppressWarnings("serial")
public class SuggestActivityCategoryView extends JFrame implements ActionListener {

	FacadeUser FU = FacadeUser.getFU();

	//Création du panel de navigation
	JPanel panel = new JPanel();

	//Création des boutons de "ProductsList"
	Button returnPrincipalButton = new Button("Retour",540, 10, 150, 30);
	Button suggestButton = new Button("Suggest",300,320,150,30);
	JTextField nameCategory = new JTextField("");
	JTextArea briefDescCategory = new JTextArea("");

	public SuggestActivityCategoryView()
	{
		super("Lazy'N Yourself");

		// Options de la fenetre
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	

		// Construction du panel principal
		placeComponents(panel);

		// Choix du panel
		setContentPane(panel);

		setVisible(true);
	}

	/**
	 * This method places all the components onto the panel.s
	 *
	 * @param  		panel	(a {@link JPanel} giving the the panel where to place components)
	 * @return      void
	 */
	private void placeComponents(JPanel panel)
	{
		panel.removeAll();
		//JPanel panTab = new JPanel();

		panel.setLayout(null);

		// Font
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 12);

		// Buttons
		returnPrincipalButton.addActionListener(this);
		panel.add(returnPrincipalButton);

		suggestButton.addActionListener(this);
		panel.add(suggestButton);


		// Textfields

		nameCategory.setBounds(300, 200, 160, 30);
		panel.add(nameCategory);

		briefDescCategory.setBounds(300, 250, 160, 60);
		panel.add(briefDescCategory);

		// Labels

		JLabel nameLabel = new JLabel("Nom");
		nameLabel.setBounds(210, 200, 90, 30);
		nameLabel.setFont(font);
		nameLabel.setForeground(Color.BLACK);
		panel.add(nameLabel);

		JLabel briefDescLabel = new JLabel("<html>Brief <br> description</html>");
		briefDescLabel.setBounds(210, 250, 100, 60);
		briefDescLabel.setFont(font);
		briefDescLabel.setForeground(Color.BLACK);
		panel.add(briefDescLabel);

		// Titre

		JLabel productsListTitle = new JLabel();
		productsListTitle.setBounds(150, 30, 500, 100);
		productsListTitle.setFont(fontTitre);					
		productsListTitle.setText("Suggérer des Catégories d'Activité");
		panel.add(productsListTitle);

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
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == returnPrincipalButton)
		{
			new ShowActivityCategoryView();	
			dispose();
			System.out.println("panel ShowcategoryActivityView affiché");
		}
		else if (source == suggestButton)
		{
			String title = nameCategory.getText();
			String description = briefDescCategory.getText();
			suggestButtonClicked(title,description);

		}

	}
	public void  suggestButtonClicked(String title, String description){
		try{
		FU.suggestActivityCategory(title, description );
		new ShowActivityCategoryView();	
		dispose();
		System.out.println("panel ShowcategoryActivityView affiché");
		JOptionPane.showMessageDialog(null, title +" has been suggested. thank you!", "Category Activity Suggestion", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (EmptyFieldsException e){
			JOptionPane.showMessageDialog(null, "Title field can't be empty", "Category Activity Suggestion", JOptionPane.ERROR_MESSAGE);
		}


	}
}



