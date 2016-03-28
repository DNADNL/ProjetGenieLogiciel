import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddSuggestionActivityCategoryView extends JFrame implements ActionListener{

	//A FAIRE
	//FacadeActivityCategorySuggestion FACS = FacadeActivityCategorySuggestion.getFACS();
	static User user;
	
	//Cr�ation du panel de navigation
	JPanel panel = new JPanel();

	//cr�ation du bouton pour ajouter un utilisateur et des champs � rentrer
	Button returnActivityCategoriesButton = new Button("Retour", 540, 10, 150, 30);
	Button validateAddSuggestionActivityCategoryButton = new Button("Ajouter", 250, 270, 200, 30);
	
	//JLabel addUserResultLabel = new JLabel("Les r�sultats seront affich�s ici");
	
	JTextField addSuggestionActivityCategoryTitle = new JTextField("Titre");
	JTextField addSuggestionActivityCategoryDescription = new JTextField("Description");
	

	public AddSuggestionActivityCategoryView(User loggedUser)
	{
		super("Lazy'N Yourself");
		user = loggedUser;
		
		// Options de la fenetre
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	
		
		// Construction du panel principal
		placeComponentsAddSuggestionActivityCategory(panel);
		
		// Choix du panel
		setContentPane(panel);
		
		setVisible(true);
	}
	
	private void placeComponentsAddSuggestionActivityCategory(JPanel panel) {

	  	panel.removeAll();
		panel.setLayout(null);
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);
		Font fontAdvice = new Font("Courier", Font.ITALIC, 14);
		
		//Ajout de l'�tiquette "Page de xxx"
		JLabel idLabel = new JLabel("<html>Bonjour, <br>" + user.nicknameUser + " !</html>");
		idLabel.setBounds(10, 10, 150, 50);
		idLabel.setFont(font);
		idLabel.setForeground(Color.BLACK);
		panel.add(idLabel);
		
		//Ajout de l'�tiquette ">Suggestion de Cat�gorie d'Activit�"
		JLabel addSuggestionActivityCategoryPanelTitle = new JLabel();
		addSuggestionActivityCategoryPanelTitle.setBounds(250, 30, 300, 100);
		addSuggestionActivityCategoryPanelTitle.setFont(fontTitre);					
		addSuggestionActivityCategoryPanelTitle.setText("<html>Suggestion de Cat�gorie d'Activit�</html>");
		panel.add(addSuggestionActivityCategoryPanelTitle);		
		
		// Ajout du Bouton Retour
		returnActivityCategoriesButton.addActionListener(this);
		panel.add(returnActivityCategoriesButton);

		// Ajout du Bouton Ajouter
		validateAddSuggestionActivityCategoryButton.addActionListener(this);
		panel.add(validateAddSuggestionActivityCategoryButton);	
		
		// Ajout des champs � rentrer
		addSuggestionActivityCategoryTitle.addActionListener(this);
		addSuggestionActivityCategoryTitle.setBounds(250, 140, 200, 25);
		panel.add(addSuggestionActivityCategoryTitle);
		
		addSuggestionActivityCategoryDescription.addActionListener(this);
		addSuggestionActivityCategoryDescription.setBounds(250, 140, 200, 25);
		panel.add(addSuggestionActivityCategoryDescription);
				
		panel.setLayout(new BorderLayout());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == validateAddSuggestionActivityCategoryButton)
		{
			String title = addSuggestionActivityCategoryTitle.getText();
			String description = addSuggestionActivityCategoryDescription.getText();
			addSuggestionActivityCategoryButtonClicked(title, description);
		}
		else if (source == returnActivityCategoriesButton)
		{
//			A FAIRE
//			new ActivityCategoriesView(FU.getUser());	
//			dispose();
			System.out.println("Panel Activity Categories affich�");
		}
		
	}

private void addSuggestionActivityCategoryButtonClicked(String title, String description) {
		// TODO Auto-generated method stub

//	Object resultAddUser = null;
//	
//	try {
//		FU.addUser(nick, pass);
//	} catch (UserCreatedException e) {
//		JOptionPane.showMessageDialog(null, nick+" a bien �t� ajout� � la BD !", "Ajout d'utilisateur", JOptionPane.INFORMATION_MESSAGE);
////		System.out.println("AddUser : Successful !");
////		addUserResultLabel.setText("L'utilisateur a �t� ajout� � la BD !");
////		addUserResultLabel.setForeground(Color.BLUE);
//	} catch (UserAlreadyExistsException e) {
//		JOptionPane.showMessageDialog(null, nick+" existe d�j� dans la BD.", "Ajout d'utilisateur", JOptionPane.ERROR_MESSAGE);
////		System.out.println("AddUser : Failed !");
////		addUserResultLabel.setText("Cet utilisateur existe d�j� !");
////		addUserResultLabel.setForeground(Color.RED);
}

}

