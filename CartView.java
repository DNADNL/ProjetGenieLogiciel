import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CartView extends JFrame implements ActionListener
{	
	FacadeUser FU = FacadeUser.getFU();


	//Creates Navigation Panel
	JPanel panel = new JPanel();

	//Creates Buttons
	Button returnButton = new Button("Return",540, 10, 150, 30);

	//Constructor
	public CartView()
	{
		super("Lazy 'n Yourself");


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
	 * This method places all the components onto the panel.
	 *
	 * @param  		panel	(a {@link JPanel} giving the the panel where to place components)
	 * @return      void
	 */
	private void placeComponents(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);

		// Fonts
		Font fontTitle = new Font("Courier", Font.BOLD, 20);
		//Font font = new Font("Courier", Font.BOLD, 15);

		// Buttons
		returnButton.addActionListener(this);
		panel.add(returnButton);

		// Title
		JLabel windowUnderConstructionLabel = new JLabel("<html>This window is under construction...</html>");
		windowUnderConstructionLabel.setBounds(125, 300, 500, 50);
		windowUnderConstructionLabel.setFont(fontTitle);
		windowUnderConstructionLabel.setForeground(Color.BLACK);
		panel.add(windowUnderConstructionLabel);

		// Logo
		JLabel image = new JLabel(new ImageIcon("logo.png"));
		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(5, 5, 150, 150);
		panelLogo.setLayout(new BorderLayout());
		panelLogo.add(image, BorderLayout.CENTER);
		panel.add(panelLogo);
	}


	//ActionPerform Method
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == returnButton)
		{
			new SimpleUserView();	
			dispose();
			System.out.println("SimpleUserView Displayed");
		}
	}
}
