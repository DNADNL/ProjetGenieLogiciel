import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class SimpleUserView extends JFrame implements ActionListener
{	
	FacadeUser FU = FacadeUser.getFU();

	//Création du panel de navigation
	JPanel panel = new JPanel();
	JPanel panelListeObjectifs = new JPanel();

	//Création des boutons principaux
	Button logoutButton = new Button("Déconnexion", 540, 10, 150, 30);
	Button profileButton = new Button("Mon Profil", 380, 10, 150, 30);
	Button cartButton = new Button("Mon Panier", 380, 620, 150, 30);
	Button shopButton = new Button("Boutique", 540, 620, 150, 30);
	Button addGoalButton = new Button("Ajouter", 10, 620, 150, 30);
	Button seeGoalButton = new Button("Voir", 540,540, 150, 30);
	Button deleteGoalButton = new Button("Supprimer", 170, 620, 150, 30);
	Button showCategoryActivityButton = new Button("<html>Show <br>Activity Categories</html>",540,170,150,45);

	//Creation de la JTable
	JTable listeObjectifs;


	//Constructeur
	public SimpleUserView()
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
	 * This method places all the components onto the panel.
	 *
	 * @param  		panel	(a {@link JPanel} giving the the panel where to place components)
	 */
	private void placeComponents(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);

		// Création des polices

		//suggérer descatégories d'activités
		showCategoryActivityButton.addActionListener(this);
		panel.add(showCategoryActivityButton);

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

		//List of Goals
		Object[][] donneesListeObjectifs =  {{"",""}};

		donneesListeObjectifs = FU.getStringGoalList();

		String[] enteteListeObjectifs = {"Objectif", "Description"};
		listeObjectifs = new JTable(donneesListeObjectifs, enteteListeObjectifs);

		JScrollPane defilementListeObjectifs = new JScrollPane(listeObjectifs);
		panelListeObjectifs.setLayout(new BorderLayout());
		panelListeObjectifs.add(defilementListeObjectifs, BorderLayout.CENTER);
		panelListeObjectifs.setBounds(10, 170, 400, 410);
		panel.add(panelListeObjectifs);

		// Logo
		JLabel image = new JLabel(new ImageIcon("logo.png"));
		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(5, 5, 150, 150);
		panelLogo.setLayout(new BorderLayout());
		panelLogo.add(image, BorderLayout.CENTER);
		panel.add(panelLogo);
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
			new SimpleUserProfileView();
			dispose();
			System.out.println("Panel SimpleUserProfile affiché");
		}
		else if (source == cartButton)
		{
			new CartView();
			dispose();
			System.out.println("Panel CartView affiché");			
		}
		else if (source == shopButton)
		{
			new ShopView();
			dispose();
			System.out.println("Panel ShopView affiché");
		}
		else if (source == addGoalButton)
		{
			new AddGoalView();
			dispose();
			System.out.println("Panel addGoal affiché");

		}
		else if (source == seeGoalButton)
		{
			if (listeObjectifs.getSelectedRow() != -1)
			{
				//String goal_selected = (listeObjectifs.getValueAt(listeObjectifs.getSelectedRow(), 0).toString());
				new GoalDetailsView();
				dispose();
				System.out.println("Panel seeGoal affiché");
			}

		}
		else if ( source == showCategoryActivityButton){
			new ShowActivityCategoryView();
			dispose();
			System.out.println("panel ShowcategoryActivityView affiché");	
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