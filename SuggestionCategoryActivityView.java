import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SuggestionCategoryActivityView extends JFrame implements ActionListener {
	//création de la façade
	FacadeUser FU = FacadeUser.getFU();
	
	static User user;

	//Création du panel de navigation
	JPanel panel = new JPanel();
	
	//création du boutton retour
	Button returnAdminButton = new Button("Retour",540, 10, 150, 30);
	
	public SuggestionCategoryActivityView(User loggedUser){

		super("Bienvenue !");
		user = loggedUser;
		
		// Options de la fenetre
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	
		
		// Construction du panel principal
			placeComponentsSuggestionCategoryActivityView(panel);
		
		// Choix du panel
		setContentPane(panel);
		
		setVisible(true);
	}
	
	private void placeComponentsSuggestionCategoryActivityView(JPanel panel){
		panel.removeAll();
		JPanel panTab = new JPanel();
		panel.setLayout(null);
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);
				
		//Ajout de l'étiquette "Page de xxx"
		JLabel idLabel = new JLabel("<html>Bonjour, <br>" + user.nicknameUser + " !</html>");
		idLabel.setBounds(10, 10, 150, 50);
		idLabel.setFont(font);
		idLabel.setForeground(Color.BLACK);
		panel.add(idLabel);
		
		//Ajout de l'étiquette ">Suggestion de Catégorie d'Activité"
		JLabel addSuggestionActivityCategoryPanelTitle = new JLabel();
		addSuggestionActivityCategoryPanelTitle.setBounds(150, 30, 500, 100);
		addSuggestionActivityCategoryPanelTitle.setFont(fontTitre);					
		addSuggestionActivityCategoryPanelTitle.setText("<html>Suggestions de Catégories d'Activité</html>");
		panel.add(addSuggestionActivityCategoryPanelTitle);		
		
		//Buttons
		returnAdminButton.addActionListener(this);
		panel.add(returnAdminButton);
		
		// List
		Object[][] donnees =  {{"",""}};
		
		//donnees = FU.getProductList();
		
		String[] entetes = {"Catégorie", "Brief Description"};
		JTable tableau = new JTable(donnees, entetes);
				
		JScrollPane test = new JScrollPane(tableau);
		panTab.setLayout(new BorderLayout());
		
		panTab.add(test, BorderLayout.CENTER);
		panTab.setBounds(10, 190, 400, 410);
		panel.add(panTab);
		
		panel.setLayout(new BorderLayout());
		}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
			dispose();
			new AdminView();
			System.out.println("Panel Admin affiché");
		}
}
