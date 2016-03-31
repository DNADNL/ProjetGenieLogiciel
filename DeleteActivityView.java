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
public class DeleteActivityView extends JFrame implements ActionListener{

	FacadeUser FU = FacadeUser.getFU();
	static String activity_name;

	//Création du panel de navigation
	JPanel panel = new JPanel();

	//Création des boutons de "DeleteProduct"
	Button returnMainUserButton = new Button("Retour",540, 10, 150, 30);
	Button validateDeleteActivityButton = new Button("<html>Delete<br> Activity</html>",275,400,150,50);

	public DeleteActivityView(User loggedUser, String activity_selected)
	{
		super("Lazy'N Yourself");

		activity_name = activity_selected;

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
	 * @return      void
	 */
	private void placeComponents(JPanel panel)
	{
		panel.removeAll();
		panel.setLayout(null);

		// Font
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);

		// Buttons
		returnMainUserButton.addActionListener(this);
		panel.add(returnMainUserButton);

		validateDeleteActivityButton.addActionListener(this);
		panel.add(validateDeleteActivityButton);


		// Textfields


		// Labels
		JLabel deleteProductLabel = new JLabel("<html>Are you sure you want to delete<br> the following activity :</html>");
		deleteProductLabel.setBounds(100, 130, 500, 30);
		deleteProductLabel.setFont(font);
		deleteProductLabel.setForeground(Color.BLACK);
		panel.add(deleteProductLabel);

		JLabel productLabel = new JLabel(activity_name);
		productLabel.setBounds(120, 170, 460, 60);
		productLabel.setFont(font);
		productLabel.setForeground(Color.BLACK);
		panel.add(productLabel);



		// Titre

		JLabel deleteProductTitle = new JLabel();
		deleteProductTitle.setBounds(250, 30, 300, 100);
		deleteProductTitle.setFont(fontTitre);					
		deleteProductTitle.setText("Delete Your Activity");
		panel.add(deleteProductTitle);

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
		if (source == returnMainUserButton)
		{
			new GoalDetailsView();
			dispose();
			System.out.println("Panel Simple User affiché");
		}
		else if (source==validateDeleteActivityButton)
		{
			deleteActivityButtonClicked(activity_name);
		}
	}

	public void deleteActivityButtonClicked(String activity_name)
	{

	}
}
