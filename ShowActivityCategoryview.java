import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ShowActivityCategoryView extends JFrame implements  ActionListener {
	FacadeUser FU = FacadeUser.getFU();
	static User user;
	
	//Création du panel de navigation
	JPanel panel = new JPanel();
	
	//Création des boutons de "ProductsList"
	Button returnPrincipalButton = new Button("Retour",540, 10, 150, 30);
	Button suggestButton = new Button("Suggest",500,320,150,30);
	
	
	
	
	public ShowActivityCategoryView(User loggedUser)
	{
		super("Bienvenue !");
		user = loggedUser;
		
		// Options de la fenetre
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	
		
		// Construction du panel principal
		placeComponentsShowCategoryView(panel);
		
		// Choix du panel
		setContentPane(panel);
		
		setVisible(true);
	}
	
	
	private void placeComponentsShowCategoryView(JPanel panel)
	{
		panel.removeAll();
		JPanel panTab = new JPanel();
		
		panel.setLayout(null);
		
		// Font
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 12);
		
		// Buttons
		returnPrincipalButton.addActionListener(this);
		panel.add(returnPrincipalButton);
		
		suggestButton.addActionListener(this);
		panel.add(suggestButton);
		// List
		
		Object[][] donnees =  {{"",""}};
		donnees = FU.getStringActivityCategoryList();
			
		String[] entetes = {"Catégorie", "Brief Description"};
		JTable tableau = new JTable(donnees, entetes);
		
		JScrollPane test = new JScrollPane(tableau);
		panTab.setLayout(new BorderLayout());
			
		panTab.add(test, BorderLayout.CENTER);
		panTab.setBounds(10, 190, 400, 410);
		panel.add(panTab);
		// Titre
			
		JLabel categoryActivityListTitle = new JLabel();
		categoryActivityListTitle.setBounds(150, 30, 500, 100);
		categoryActivityListTitle.setFont(fontTitre);					
		categoryActivityListTitle.setText("category activity");
		panel.add(categoryActivityListTitle);
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
		else if(source == suggestButton){
				new SuggestCategoryView(FU.getCurrentUser());
				dispose();
				System.out.println("Panel de suggestion de catégorie d'activité affiché");
				
				
			}
		}
	}


