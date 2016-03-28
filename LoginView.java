import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class LoginView  extends JFrame implements ActionListener, KeyListener{

	FacadeUser FU = FacadeUser.getFU();
	
	
	//création de la fenêtre de connexion
		JPanel panel1 = new JPanel();
		JTextField userText = new JTextField("admin");
		JPasswordField passwordText = new JPasswordField("mot_de_passe");
		Button loginButton = new Button("LOGIN", 220, 170, 90, 30);

	//création de la fenêtre d'erreurs de connexion
		JPanel panel2 = new JPanel();
		Button returnButton = new Button("RETOUR",155, 170, 190, 30);
		
		  
	 
	//Constructeurs
	  public LoginView() 
	  {
		  super("Login");

			// Options de la fenetre
			this.setSize(500,300);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);

			// Construction du panel
			placeComponents(panel1);
			
			// Choix du panel
			this.setContentPane(this.panel1);
			getRootPane().setDefaultButton(loginButton);
			
			this.setVisible(true);
	  }

		
	  private void placeComponents(JPanel panel) 
	  {

			panel.setLayout(null);
			
			// Font
			Font fontTitre = new Font("Courier", Font.BOLD, 20);
			Font font = new Font("Courier", Font.BOLD, 15);

			// Ajout du Bouton Login sur la fenetre 1
			loginButton.addActionListener(this);
			loginButton.addKeyListener(this);
			panel1.add(loginButton);
			
			// Textfields
			userText.setBounds(180, 100, 160, 25);
			panel1.add(userText);
			passwordText.setBounds(180, 130, 160, 25);
			panel1.add(passwordText);
			
			// Labels
			JLabel userLabel = new JLabel("Pseudo");
			userLabel.setBounds(100, 100, 90, 25);
			userLabel.setFont(font);
			userLabel.setForeground(Color.BLACK);
			panel.add(userLabel);

			JLabel passwordLabel = new JLabel("Password");
			passwordLabel.setBounds(100, 130, 90, 25);
			passwordLabel.setFont(font);
			passwordLabel.setForeground(Color.BLACK);
			panel.add(passwordLabel);

			
			// Titre
			JLabel TitreCo = new JLabel("Login");
			TitreCo.setBounds(100, 10, 400, 80);
			TitreCo.setFont(fontTitre);
			TitreCo.setForeground(Color.BLACK);
			panel.add(TitreCo);

			// Background
			JLabel image = new JLabel(new ImageIcon("fond.jpg"));
			panel.setLayout(new BorderLayout());
			panel.add(image, BorderLayout.CENTER);
		}


//	// Methode pour revenir à  la fenetre 1
//	  public void allerVersFenetre1()
//	  {
//		  this.setContentPane(this.panel1);
//		  System.out.println("fenetre 1 affichée");
//		  this.revalidate();
//	  }

		  
	  
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			Object source = e.getSource();
			if (source == loginButton)
			{
				String password = new String(passwordText.getPassword());
				String nickname = userText.getText();
				this.login(nickname, password);
			}
//			else if (source == returnButton)
//			{
//				allerVersFenetre1();
//			}
		}
		
		public void login(String nick, String pass)
		{
			try 
			{
				FU.login(nick,pass);
				System.out.println("SUCCESS");
				
				if(FU.isAdmin(nick))
				{
					System.out.println("Admin Connected");
					dispose();
					new AdminView();
				}
				else if (FU.isSeller(nick))
				{
					System.out.println("Seller Connected");
					dispose();
					new SellerView();
				}
				else
				{
					System.out.println("Simple User Connected");
					dispose();
					new SimpleUserView(FU.getCurrentUser());
				}
			} 
			catch (UserNotInTheDatabaseException e) 
			{
				JOptionPane.showMessageDialog(null, "Identifiant incorrect. Veuillez réessayer.", "Connexion", JOptionPane.ERROR_MESSAGE);
//				System.out.println("ERREUR - Identifiant incorrect");
//				placeComponentsFenetre2(panel2);
//				allerVersFenetre2();				
			}
			catch (WrongPasswordException e)
			{
				JOptionPane.showMessageDialog(null, "Mot de passe incorrect. Veuillez réessayer.", "Connexion", JOptionPane.ERROR_MESSAGE);
//				System.out.println("ERREUR - Mot de passe incorrect");
//				placeComponentsFenetre2(panel2);
//				allerVersFenetre2();	
			}
		}


		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
		}


		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
		}


		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
	        if((arg0.getKeyCode()==KeyEvent.VK_ENTER))
	        {
				System.out.println("appui ENTER");
	        	String password = new String(passwordText.getPassword());
				String nickname = userText.getText();
				this.login(nickname, password);
	        } 
		}
		
	

}