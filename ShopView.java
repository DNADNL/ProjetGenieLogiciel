import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ShopView extends JFrame implements ActionListener
{	
	FacadeUser FU = FacadeUser.getFU();
	static User user;
	
	//Création du panel de navigation
	JPanel panel = new JPanel();
	
	//Création des boutons de "Principal"
	Button returnButton = new Button("Retour",540, 10, 150, 30);
	
	//Constructeur
	public ShopView(User loggedUser)
	{
		super("Lazy'N Yourself");
		user = loggedUser;
		
		// Options de la fenetre
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	
		
		// Construction du panel principal
		placeComponentsPrincipal(panel);
		
		// Choix du panel
		setContentPane(panel);
		
		setVisible(true);
	}
	
	private void placeComponentsPrincipal(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);
		
		// Font
		Font fontTitle = new Font("Courier", Font.BOLD, 20);
		
		// Buttons
		returnButton.addActionListener(this);
		panel.add(returnButton);
		
		// Title
		JLabel underConstructionTitle = new JLabel();
		underConstructionTitle.setBounds(250, 30, 300, 100);
		underConstructionTitle.setFont(fontTitle);					
		underConstructionTitle.setText("<html>This window is under construction...</html>");
		panel.add(underConstructionTitle);	

		// Background

	}


	//ActionPerformMethod
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == returnButton)
		{
			FU.disconnectUser();
			dispose();
			new LoginView();
		}
	
		
	}
}
