import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// The Activity Details View

@SuppressWarnings("serial")
public class ActivityDetailsView extends JFrame implements ActionListener{


	FacadeUser FU = FacadeUser.getFU();
	static User user;
	// static Activity activity;

	// Navigation Panel Creation
	JPanel panel = new JPanel();

	// "ProductDetails" Buttons Creation
	Button returnSeeGoalButton = new Button("Retour",540, 10, 150, 30);

	public ActivityDetailsView(User loggedUser, String activity_name)
	{
		super("Lazy'N Yourself");
		user = loggedUser;
		//product = FU.getProduct(product_name);

		// Window Options
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	

		// Builds the Panel
		placeComponents(panel);

		// Chooses the panel
		setContentPane(panel);

		// Sets it visible
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

		// Font
		Font fontTitle = new Font("Courier", Font.BOLD, 20);

		// Buttons
		returnSeeGoalButton.addActionListener(this);
		panel.add(returnSeeGoalButton);


		// Textfields

		// Labels

		// Title
		JLabel activityDetailTitle = new JLabel();
		activityDetailTitle.setBounds(250, 30, 300, 100);
		activityDetailTitle.setFont(fontTitle);					
		activityDetailTitle.setText("<html>Activity Detail</html>");
		panel.add(activityDetailTitle);	

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
		if (source == returnSeeGoalButton)
		{
			new GoalDetailsView();	
			dispose();
			System.out.println("Panel SeeGoal affiché");
		}

	}

}
