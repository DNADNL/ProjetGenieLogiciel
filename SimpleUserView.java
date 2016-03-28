import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SimpleUserView extends JFrame implements ActionListener
{	
	FacadeUser FU = FacadeUser.getFU();
	static User user;
	
	//Création du panel de navigation
	JPanel panel = new JPanel();
	JPanel panelListeObjectifs = new JPanel();
	
	//Création des boutons principaux
	Button logoutButton = new Button("Déconnexion", 540, 10, 150, 30);
	Button profileButton = new Button("Mon Profil", 380, 10, 150, 30);
	Button cartButton = new Button("Mon Panier", 380, 630, 150, 30);
	Button shopButton = new Button("Boutique", 540, 630, 150, 30);
	Button addGoalButton = new Button("Ajouter", 10, 540, 150, 30);
	Button seeGoalButton = new Button("Voir", 170, 540, 150, 30);
	Button deleteGoalButton = new Button("Supprimer", 330, 540, 150, 30);
	Button categorySuggestionButton = new Button("<html>Suggérer <br>des catégories</html>",450,200,200,60);
	
	//Creation de la JTable
	JTable listeObjectifs;
	
	
	//Constructeur
	public SimpleUserView(User loggedUser)
	{
		super("Bienvenue !");
		user = loggedUser;
		
		// Options de la fenetre
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	
		
		// Construction du panel principal
		placeComponentsPrincipal(panel);
		
		// Choix du panel
		setContentPane(panel);
		
		setVisible(true);
	}
	
	private void placeComponentsPrincipal(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);
		
		// Création des polices
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);
		
		//suggérer descatégories d'activités
		categorySuggestionButton.addActionListener(this);
		panel.add(categorySuggestionButton);
		
		// Affectation des boutons à la fenêtre
		// "Déconnexion"
		logoutButton.addActionListener(this);
		panel.add(logoutButton);
		
		// "Mon Profil"
		profileButton.addActionListener(this);
		panel.add(profileButton);
		
		// "Mon Panier"
		cartButton.addActionListener(this);
		panel.add(cartButton);
		
		// "Boutique"
		shopButton.addActionListener(this);
		panel.add(shopButton);
		
		// Objectif : "Ajouter"
		addGoalButton.addActionListener(this);
		panel.add(addGoalButton);
		
		// Objectif : "Voir"
		seeGoalButton.addActionListener(this);
		panel.add(seeGoalButton);
		
		// Objectif : "Supprimer"
		deleteGoalButton.addActionListener(this);
		panel.add(deleteGoalButton);
		
		// Création de l'étiquette "Bonjour xxx !"
		JLabel userLabel = new JLabel("<html>Bonjour, " + user.nicknameUser + " !</html>");
		userLabel.setBounds(10, 10, 150, 50);
		userLabel.setFont(font);
		userLabel.setForeground(Color.BLACK);
		panel.add(userLabel);
		
		// Création de la liste des objectifs
		
		Object[][] donneesListeObjectifs =  {{"",""}};
		
		//donnees = FU.getProductList();
		
		String[] enteteListeObjectifs = {"Objectif", "Description"};
		listeObjectifs = new JTable(donneesListeObjectifs, enteteListeObjectifs);
		
		JScrollPane defilementListeObjectifs = new JScrollPane(listeObjectifs);
		panelListeObjectifs.setLayout(new BorderLayout());
		panelListeObjectifs.add(defilementListeObjectifs, BorderLayout.CENTER);
		panelListeObjectifs.setBounds(10, 90, 400, 410);
		panel.add(panelListeObjectifs);
	}


	//ActionPerformMethod
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == logoutButton)
		{
			FU.disconnectUser();
			dispose();
			new LoginView();
		}
		else if (source == profileButton)
		{
			new SimpleUserProfileView(FU.getCurrentUser());
			dispose();
			System.out.println("Panel SimpleUserProfile affiché");
		}
		else if (source == cartButton)
		{
			new CartView(FU.getCurrentUser());
			dispose();
			System.out.println("Panel CartView affiché");			
		}
		else if (source == shopButton)
		{
			new ShopView(FU.getCurrentUser());
			dispose();
			System.out.println("Panel ShopView affiché");
		}
		else if (source == addGoalButton)
		{
			new AddGoalView(FU.getCurrentUser());
			dispose();
			System.out.println("Panel addGoal affiché");
			
		}
		else if (source == seeGoalButton)
		{
			if (listeObjectifs.getSelectedRow() != -1)
			{
				String goal_selected = (listeObjectifs.getValueAt(listeObjectifs.getSelectedRow(), 0).toString());
				new SeeGoalView(FU.getCurrentUser(), goal_selected);
				dispose();
				System.out.println("Panel seeGoal affiché");
			}
			
		}
		else if ( source == categorySuggestionButton){
			new SuggestCategoryView(FU.getCurrentUser());
			dispose();
			System.out.println("panel SuggestCategoryView affiché");	
		}
		else if (source == deleteGoalButton)
		{
			if (listeObjectifs.getSelectedRow() != -1)
			{
				String goal_selected = (listeObjectifs.getValueAt(listeObjectifs.getSelectedRow(), 0).toString());
				new DeleteGoalView(FU.getCurrentUser(), goal_selected);
				dispose();
				System.out.println("Panel DeleteGoal affiché");
			}
		}
		
	}
}