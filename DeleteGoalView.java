import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DeleteGoalView extends JFrame implements ActionListener{

	FacadeUser FU = FacadeUser.getFU();
	static String goal_name;
	
	//Cr�ation du panel de navigation
	JPanel panel = new JPanel();
	
	//Cr�ation des boutons de "DeleteProduct"
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
			placeComponentsDeleteGoal(panel);
			
			// Choix du panel
			setContentPane(panel);
			
			setVisible(true);
		}
		
		private void placeComponentsDeleteGoal(JPanel panel)
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

		// Background

	}
	
	
		
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == returnMainUserButton)
		{
			new SimpleUserView(FU.getCurrentUser());
			dispose();
			System.out.println("Panel Simple User affich�");
		}
		else if (source==validateDeleteGoalButton)
		{
			deleteGoalButtonClicked(goal_name);
		}
	}
	
	public void deleteGoalButtonClicked(String goal_name)
	{
//		try {
//			FU.deleteGoal(pdt_product);
//		} catch (UserNotInTheDatabaseException e) {
//			JOptionPane.showMessageDialog(null, pdt_product+" n'existe pas dans la BD.", "Suppression de produit", JOptionPane.ERROR_MESSAGE);
//
//		} catch (UserDeletedException e) {
//			JOptionPane.showMessageDialog(null, pdt_product+" a bien �t� supprim� de la BD !", "Suppression de produit", JOptionPane.INFORMATION_MESSAGE);
//			new SimpleUserView(FU.getUser());
//			dispose();
//			System.out.println("Panel Simple User affich�");
//
//		}
	}
}