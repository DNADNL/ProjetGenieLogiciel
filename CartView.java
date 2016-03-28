import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CartView extends JFrame implements ActionListener
{	
	FacadeUser FU = FacadeUser.getFU();
	static User user;
	
	//Creates Navigation Panel
	JPanel panel = new JPanel();
	
	//Creates Buttons
	Button returnButton = new Button("Return",540, 10, 150, 30);
	
	//Constructor
	public CartView(User loggedUser)
	{
		super("Lazy 'n Yourself");
		user = loggedUser;
		
		// Window Options
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	
		
		// Panel Construction
		placeComponents(panel);
		
		// Panel Chosen
		setContentPane(panel);
		
		setVisible(true);
	}
	
	/**
	 * This method places the components on the panel.
	 * <p>
	 *
	 * @param  		panel (a {@link JPanel} giving the panel where to place the components),
	 * @return      void
	 */
	private void placeComponents(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);
		
		// Fonts
		Font fontTitle = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);
		
		// Buttons
		returnButton.addActionListener(this);
		panel.add(returnButton);
		
		// Labels
		JLabel userLabel = new JLabel("<html>Hello, " + user.nicknameUser + " !</html>");
		userLabel.setBounds(10, 10, 150, 50);
		userLabel.setFont(font);
		userLabel.setForeground(Color.BLACK);
		panel.add(userLabel);
		
		// Title
		JLabel windowUnderConstructionLabel = new JLabel("<html>This window is under construction...</html>");
		windowUnderConstructionLabel.setBounds(125, 300, 500, 50);
		windowUnderConstructionLabel.setFont(fontTitle);
		windowUnderConstructionLabel.setForeground(Color.BLACK);
		panel.add(windowUnderConstructionLabel);
	}


	//ActionPerform Method
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == returnButton)
		{
			new SimpleUserView(FU.getCurrentUser());	
			dispose();
			System.out.println("SimpleUserView Displayed");
		}
	}
}
