package adminUI;
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

import miscellaneous.*;
import facade.*;

// The Activity Category Suggestions View, where the Admin can see all the suggestions

@SuppressWarnings("serial")
public class ActivityCategorySuggestionsView extends JFrame implements ActionListener {
	
	// Creating Facade Link
	FacadeUser FU = FacadeUser.getFU();

	// Creating Navigation Panel
	JPanel panel = new JPanel();

	// Creating Buttons
	Button returnButton = new Button("Return",540, 10, 150, 30);

	public ActivityCategorySuggestionsView(){

		super("Lazy'N Yourself");

		// Window Options
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	

		// Building the Panel
		placeComponents(panel);

		// Choosing the Panel
		setContentPane(panel);

		// Setting it visible
		setVisible(true);
	}

	/**
	 * This method places all the components onto the panel.
	 *
	 * @param  		panel	(a {@link JPanel} giving the the panel where to place components)
	 */
	private void placeComponents(JPanel panel){
		panel.removeAll();
		JPanel panTab = new JPanel();
		panel.setLayout(null);
		Font fontTitre = new Font("Courier", Font.BOLD, 20);

		//Adding Label : "Activity Category Suggestions"
		JLabel addSuggestionActivityCategoryPanelTitle = new JLabel();
		addSuggestionActivityCategoryPanelTitle.setBounds(150, 30, 500, 100);
		addSuggestionActivityCategoryPanelTitle.setFont(fontTitre);					
		addSuggestionActivityCategoryPanelTitle.setText("<html>Activity Category Suggestions</html>");
		panel.add(addSuggestionActivityCategoryPanelTitle);		

		//Adding Buttons
		returnButton.addActionListener(this);
		panel.add(returnButton);

		// Adding Activity Category Suggestions List
		Object[][] donnees =  {{"",""}};
		donnees = FU.getStringActivityCategorySuggestionList();

		String[] entetes = {"Catégorie", "Brief Description"};
		JTable tableau = new JTable(donnees, entetes);

		JScrollPane test = new JScrollPane(tableau);
		panTab.setLayout(new BorderLayout());
		panTab.add(test, BorderLayout.CENTER);
		panTab.setBounds(10, 190, 400, 410);
		panel.add(panTab);


		// Adding Logo
		JLabel image = new JLabel(new ImageIcon("logo.png"));
		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(5, 5, 150, 150);
		panelLogo.setLayout(new BorderLayout());
		panelLogo.add(image, BorderLayout.CENTER);
		panel.add(panelLogo);
	}

	public void actionPerformed(ActionEvent e) {
		// The only Button Available is the Return Button 
		dispose();
		new AdminView();
		System.out.println("AdminView Displayed !");
	}
}
