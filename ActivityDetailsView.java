import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ActivityDetailsView extends JFrame implements ActionListener{

	
	FacadeUser FU = FacadeUser.getFU();
	static User user;
	// static Activity activity;
	
	//Création du panel de navigation
		JPanel panel = new JPanel();
		
	//Création des boutons de "ProductDetails"
	Button returnSeeGoalButton = new Button("Retour",540, 10, 150, 30);
	
	public ActivityDetailsView(User loggedUser, String activity_name)
	{
		super("Lazy'N Yourself");
		user = loggedUser;
		//product = FU.getProduct(product_name);
		
		// Options de la fenetre
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	
		
		// Construction du panel principal
		placeComponentsActivity(panel);
		
		// Choix du panel
		setContentPane(panel);
		
		setVisible(true);
	}
	
	private void placeComponentsActivity(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);
		
		// Font
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);
		
		// Buttons
		returnSeeGoalButton.addActionListener(this);
		panel.add(returnSeeGoalButton);
		
		
		// Textfields
		
		// Labels

		
		
		
		// Titre

		// Background

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == returnSeeGoalButton)
		{
			new SeeGoalView(FU.getCurrentUser(), "FU.getCurrentGoal().goal_name");	
			dispose();
			System.out.println("Panel SeeGoal affiché");
		}
		
	}

}
