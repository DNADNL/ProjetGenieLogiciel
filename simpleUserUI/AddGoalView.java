package simpleUserUI;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exceptions.*;
import facade.*;
import miscellaneous.*;

// The Add Goal View

@SuppressWarnings("serial")
public class AddGoalView extends JFrame implements ActionListener
{	
	// Creating Facade Link
	FacadeUser FU = FacadeUser.getFU();

	// Creating Navigation Panel
	JPanel panel = new JPanel();


	// Creating Buttons
	Button returnButton = new Button("Return", 540, 10, 150, 30);
	Button validateAddUserButton = new Button("Add", 250, 270, 200, 30);

	// Creating Text Fields
	JTextField addGoalTitle = new JTextField("Goal Title");
	JTextField addGoalDescription = new JTextField("Goal Description");

	// Constructor
	public AddGoalView()
	{
		super("Lazy'N Yourself");

		// Window Options
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	

		// Building the Panel
		placeComponents(panel);

		// Choosing the panel
		setContentPane(panel);

		// Setting it visible
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
		Font fontTitle = new Font("Courier", Font.BOLD, 20);

		// Adding Buttons
		returnButton.addActionListener(this);
		panel.add(returnButton);

		validateAddUserButton.addActionListener(this);
		panel.add(validateAddUserButton);	

		// Adding Text Fields
		addGoalTitle.addActionListener(this);
		addGoalTitle.setBounds(250, 140, 200, 25);
		panel.add(addGoalTitle);

		addGoalDescription.addActionListener(this);
		addGoalDescription.setBounds(250, 180, 200, 25);
		panel.add(addGoalDescription);
		
		// Adding Label : "Add a Goal"
		JLabel addGoalLabel = new JLabel();
		addGoalLabel.setBounds(250, 30, 300, 100);
		addGoalLabel.setFont(fontTitle);					
		addGoalLabel.setText("Add a Goal");
		panel.add(addGoalLabel);		

		// Adding Logo
		JLabel image = new JLabel(new ImageIcon("logo.png"));
		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(5, 5, 150, 150);
		panelLogo.setLayout(new BorderLayout());
		panelLogo.add(image, BorderLayout.CENTER);
		panel.add(panelLogo);
	}


	//ActionPerformMethod
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		if (source == validateAddUserButton)
		{
			String goal_title = addGoalTitle.getText();
			String goal_description = addGoalDescription.getText();
			String nick = FU.getCurrentUser().nicknameUser;
			addUserButtonClicked(goal_title, goal_description, nick);
		}
		else if (source == returnButton)
		{
			new SimpleUserView();	
			dispose();
			System.out.println("Panel Admin affiché");
		}


	}

	public void addUserButtonClicked(String goal_title, String goal_description, String nick)
	{
		try{
			FU.addGoal(goal_title, goal_description, nick);
		} catch (ObjectCreatedException e) {
			JOptionPane.showMessageDialog(null, goal_title+" added to the DB !", "Add a Goal", JOptionPane.INFORMATION_MESSAGE);
			new SimpleUserView();
			dispose();
			System.out.println("SimpleUserView Displayed");
		}
		catch (ObjectAlreadyExistsException e) {
			JOptionPane.showMessageDialog(null, "This Goal already exists in the DB.", "Add a Goal", JOptionPane.ERROR_MESSAGE);
		}
	}


}