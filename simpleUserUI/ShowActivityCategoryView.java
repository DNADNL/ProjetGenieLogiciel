package simpleUserUI;
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

import facade.*;
import miscellaneous.*;

@SuppressWarnings("serial")
public class ShowActivityCategoryView extends JFrame implements  ActionListener {
	FacadeUser FU = FacadeUser.getFU();

	//Création du panel de navigation
	JPanel panel = new JPanel();

	//Création des boutons de "ProductsList"
	Button returnPrincipalButton = new Button("Return",540, 10, 150, 30);
	Button suggestButton = new Button("Suggest",500,320,150,30);

	public ShowActivityCategoryView()
	{
		super("Lazy'N Yourself");

		// Window Options
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
	 * <p>
	 *
	 * @param  		panel	(a {@link JPanel} giving the the panel where to place components)
	 * @return      void
	 */
	private void placeComponents(JPanel panel)
	{
		panel.removeAll();
		JPanel panTab = new JPanel();

		panel.setLayout(null);

		// Font
		Font fontTitre = new Font("Courier", Font.BOLD, 20);

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
			new SimpleUserView();	
			dispose();
			System.out.println("Panel Principal affiché");
		}
		else if(source == suggestButton){
			new SuggestActivityCategoryView();
			dispose();
			System.out.println("Panel de suggestion de catégorie d'activité affiché");


		}
	}
}


