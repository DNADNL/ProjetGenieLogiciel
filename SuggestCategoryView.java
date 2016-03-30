import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SuggestCategoryView extends JFrame implements ActionListener {

	FacadeUser FU = FacadeUser.getFU();
	static User user;
	
	//Création du panel de navigation
	JPanel panel = new JPanel();
	
	//Création des boutons de "ProductsList"
	Button returnPrincipalButton = new Button("Retour",540, 10, 150, 30);
	Button suggestButton = new Button("Suggest",300,320,150,30);
	JTextField nameCategory = new JTextField("Category name");
	JTextArea briefDescCategory = new JTextArea("Brief Category description");
	
	public SuggestCategoryView(User loggedUser)
	{
		super("Lazy'N Yourself");
		user = loggedUser;
		
		// Options de la fenetre
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	
		
		// Construction du panel principal
		placeComponentsCategoryView(panel);
		
		// Choix du panel
		setContentPane(panel);
		
		setVisible(true);
	}

	
	private void placeComponentsCategoryView(JPanel panel)
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

		// Background


	}


	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == returnPrincipalButton)
		{
			new SimpleUserView(FU.getCurrentUser());	
			dispose();
			System.out.println("Panel Principal affiché");
		}
		else if (source == suggestButton)
		{
			String title = nameCategory.getText();
			String description = briefDescCategory.getText();
			suggestButtonClicked(title,description);
			
		}
		
	}
	public void  suggestButtonClicked(String title, String description){
		
		
			FU.suggestActivityCategory(title, description );
			new ShowActivityCategoryView(FU.getCurrentUser());	
			dispose();
			System.out.println("Panel Principal affiché");
			JOptionPane.showMessageDialog(null, title +" has been suggested. thank you!", "Category Activity Suggestion", JOptionPane.INFORMATION_MESSAGE);
		
		
			
		}
	}



