import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class SuggestionCategoryActivityView extends JFrame implements ActionListener {
	//création de la façade
	FacadeUser FU = FacadeUser.getFU();

	//Création du panel de navigation
	JPanel panel = new JPanel();

	//création du boutton retour
	Button returnAdminButton = new Button("Retour",540, 10, 150, 30);

	public SuggestionCategoryActivityView(){

		super("Lazy'N Yourself");

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
		donnees = FU.getStringActivityCategorySuggestionList();

		String[] entetes = {"Catégorie", "Brief Description"};
		JTable tableau = new JTable(donnees, entetes);

		JScrollPane test = new JScrollPane(tableau);
		panTab.setLayout(new BorderLayout());
		panTab.add(test, BorderLayout.CENTER);
		panTab.setBounds(10, 190, 400, 410);
		panel.add(panTab);


		// Logo
		JLabel image = new JLabel(new ImageIcon("logo.png"));
		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(5, 5, 150, 150);
		panelLogo.setLayout(new BorderLayout());
		panelLogo.add(image, BorderLayout.CENTER);
		panel.add(panelLogo);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		dispose();
		new AdminView();
		System.out.println("Panel Admin affiché");
	}
}
