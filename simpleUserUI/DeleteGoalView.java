package simpleUserUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import business.User;
import exceptions.ObjectDeletedException;
import exceptions.ObjectNotInTheDatabaseException;
import facade.FacadeUser;
import miscellaneous.Button;

@SuppressWarnings("serial")
public class DeleteGoalView extends JFrame implements ActionListener{

	FacadeUser FU = FacadeUser.getFU();
	static String goal_name;

	//Création du panel de navigation
	JPanel panel = new JPanel();

	//Création des boutons de "DeleteProduct"
	Button returnMainUserButton = new Button("Retour",540, 10, 150, 30);
	Button validateDeleteGoalButton = new Button("<html>Delete<br> Goal</html>",275,350,150,50);

	public DeleteGoalView(User loggedUser, String goal_selected)
	{
		super("Lazy'N Yourself");

		goal_name = goal_selected;

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

		validateDeleteGoalButton.addActionListener(this);
		panel.add(validateDeleteGoalButton);


		// Textfields


		// Labels
		JLabel deleteProductLabel = new JLabel("Are you sure you want to delete the following goal :");
		deleteProductLabel.setBounds(100, 130, 500, 30);
		deleteProductLabel.setFont(font);
		deleteProductLabel.setForeground(Color.BLACK);
		panel.add(deleteProductLabel);

		JLabel productLabel = new JLabel(goal_name);
		productLabel.setBounds(120, 170, 460, 60);
		productLabel.setFont(font);
		productLabel.setForeground(Color.BLACK);
		panel.add(productLabel);



		// Titre

		JLabel deleteProductTitle = new JLabel();
		deleteProductTitle.setBounds(250, 30, 300, 100);
		deleteProductTitle.setFont(fontTitre);					
		deleteProductTitle.setText("Delete Your Goal");
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
			new SimpleUserView();
			dispose();
			System.out.println("Panel Simple User affiché");
		}
		else if (source==validateDeleteGoalButton)
		{
			deleteGoalButtonClicked(goal_name);

		}
	}

	public void deleteGoalButtonClicked(String goal_name)
	{
		//		{
		try {
			FU.deleteGoal(goal_name);
		} catch (ObjectNotInTheDatabaseException e) {
			JOptionPane.showMessageDialog(null, goal_name+" n'existe pas dans la BD.", "Suppression de goal", JOptionPane.ERROR_MESSAGE);

		} catch (ObjectDeletedException e) {
			JOptionPane.showMessageDialog(null, goal_name+" a bien été supprimé de la BD !", "Suppression de goal", JOptionPane.INFORMATION_MESSAGE);
			new SimpleUserView();
			dispose();
			System.out.println("Panel Simple User affiché");
		}
	}

}
