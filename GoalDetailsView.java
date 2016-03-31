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
public class GoalDetailsView extends JFrame implements ActionListener
{	
	//Get the Facade
	FacadeUser FU = FacadeUser.getFU();

	//Create the panel
	JPanel panel = new JPanel();

	//Create the Buttons for "Principal"
	Button returnMainUserButton = new Button("Retour",540, 10, 150, 30);
	Button activityDetailsButton = new Button("Détails", 540, 335, 150,30);
	Button deleteActivityButton = new Button("Supprimer",170,170,150,30);
	Button addActivityButton = new Button("Ajouter",10,170,150,30);

	//Create the table
	JTable tableau;

	//Constructeur
	public GoalDetailsView()
	{
		super("Lazy'N Yourself");

		// Frame Config
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	

		// Construct the panel
		placeComponents(panel);

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
		panel.setLayout(null);

		// Font
		Font fontTitle = new Font("Courier", Font.BOLD, 20);
//		Font font = new Font("Courier", Font.BOLD, 15);

		// Buttons
		returnMainUserButton.addActionListener(this);
		panel.add(returnMainUserButton);

		activityDetailsButton.addActionListener(this);
		panel.add(activityDetailsButton);

		addActivityButton.addActionListener(this);
		panel.add(addActivityButton);

		deleteActivityButton.addActionListener(this);
		panel.add(deleteActivityButton);

		// List
		JPanel panTab = new JPanel();

		Object[][] donnees = {{"",""}};

		String[] entetes = {"Activity", "Brief Description"};
		tableau = new JTable(donnees, entetes);

		JScrollPane test = new JScrollPane(tableau);
		panTab.setLayout(new BorderLayout());

		panTab.add(test, BorderLayout.CENTER);
		panTab.setBounds(10, 220, 400, 410);
		panel.add(panTab);

		// Textfields

		// Labels

		JLabel SeeGoalLabel = new JLabel();
		SeeGoalLabel.setBounds(250, 30, 300, 100);
		SeeGoalLabel.setFont(fontTitle);					
		SeeGoalLabel.setText("Selected Goal");
		panel.add(SeeGoalLabel);	

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
		if (source == returnMainUserButton)
		{
			new SimpleUserView();
			dispose();
			System.out.println("Panel Simple User affiché");
		}
		else if (source == activityDetailsButton)
		{
			if (tableau.getSelectedRow() != -1)
			{
				String activity_selected = (tableau.getValueAt(tableau.getSelectedRow(), 0).toString());
				new ActivityDetailsView(FU.getCurrentUser(), activity_selected);
				dispose();
				System.out.println("Panel Détail Activité affiché");
			}

		}
		else if (source == addActivityButton)
		{

			System.out.println("Panel Ajout Activité affiché");
		}
		else if (source == deleteActivityButton)
		{
			if (tableau.getSelectedRow() != -1)
			{
				String activity_selected = (tableau.getValueAt(tableau.getSelectedRow(), 0).toString());
				new DeleteActivityView(FU.getCurrentUser(), activity_selected);
				dispose();
				System.out.println("Panel Delete Activity affiché");
			}
		}


	}


}