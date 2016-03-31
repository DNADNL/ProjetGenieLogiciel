package simpleUserUI;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import facade.*;
import miscellaneous.*;

@SuppressWarnings("serial")
public class ShopView extends JFrame implements ActionListener
{	
	FacadeUser FU = FacadeUser.getFU();

	//Création du panel de navigation
	JPanel panel = new JPanel();

	//Création des boutons de "Principal"
	Button returnSUViewButton = new Button("Retour",540, 10, 150, 30);

	//Constructeur
	public ShopView()
	{
		super("Lazy'N Yourself");

		// Options de la fenetre
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
	 */
	private void placeComponents(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);

		// Font
		Font fontTitle = new Font("Courier", Font.BOLD, 20);

		// Buttons
		returnSUViewButton.addActionListener(this);
		panel.add(returnSUViewButton);

		// Title
		JLabel underConstructionTitle = new JLabel();
		underConstructionTitle.setBounds(250, 30, 300, 100);
		underConstructionTitle.setFont(fontTitle);					
		underConstructionTitle.setText("<html>This window is under construction...</html>");
		panel.add(underConstructionTitle);	

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
		if (source == returnSUViewButton)
		{
			new SimpleUserView();	
			dispose();
			System.out.println("Panel Admin affiché");
		}


	}
}
