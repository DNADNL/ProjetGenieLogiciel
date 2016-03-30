import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



@SuppressWarnings("serial")
public class LoginView  extends JFrame implements ActionListener, KeyListener{

	//Get the Facade
		FacadeUser FU = FacadeUser.getFU();

	//Create the panel
		JPanel panel1 = new JPanel();
		
	//Create the Textfields for LoginView
		JTextField userText = new JTextField("admin");
		JPasswordField passwordText = new JPasswordField("mot_de_passe");
		
	//Create the Buttons for LoginView
		Button loginButton = new Button("LOGIN", 190, 170, 90, 30);


		
		  
	 
	//Constructeurs
	  public LoginView() 
	  {
		  super("Lazy'N Yourself");

			// Frame Config
			this.setSize(500,300);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);

			// Construct the panel
			placeComponents(panel1);
			
			

			this.setContentPane(this.panel1);
			this.setVisible(true);
	  }

	//Method to construct the panel
	  private void placeComponents(JPanel panel) 
	  {

			panel.setLayout(null);
			
			// Font
			Font fontTitre = new Font("Courier", Font.BOLD, 20);
			Font font = new Font("Courier", Font.BOLD, 15);

			// Ajout du Bouton Login sur la fenetre 1
			loginButton.addActionListener(this);
			loginButton.addKeyListener(this);
			getRootPane().setDefaultButton(loginButton);
			panel1.add(loginButton);
			
			// Textfields
			userText.setBounds(150, 100, 160, 25);
			panel1.add(userText);
			passwordText.setBounds(150, 130, 160, 25);
			panel1.add(passwordText);
			
			// Labels
			JLabel userLabel = new JLabel("Pseudo");
			userLabel.setBounds(70, 100, 90, 25);
			userLabel.setFont(font);
			userLabel.setForeground(Color.BLACK);
			panel.add(userLabel);

			JLabel passwordLabel = new JLabel("Password");
			passwordLabel.setBounds(70, 130, 90, 25);
			passwordLabel.setFont(font);
			passwordLabel.setForeground(Color.BLACK);
			panel.add(passwordLabel);

			
			// Titre
			JLabel TitreCo = new JLabel("Login");
			TitreCo.setBounds(70, 10, 400, 80);
			TitreCo.setFont(fontTitre);
			TitreCo.setForeground(Color.BLACK);
			panel.add(TitreCo);

			// Logo
			
			JLabel image = new JLabel(new ImageIcon("logo.png"));
			JPanel panelLogo = new JPanel();
			panelLogo.setBounds(330, 45, 150, 150);
			panelLogo.setLayout(new BorderLayout());
			panelLogo.add(image, BorderLayout.CENTER);
			panel.add(panelLogo);
		}
		  
	  
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
		}
		
		//This method handle the role of the user that logs in and load the main View
		public void login(String nick, String pass)
		{
			try 
			{
				FU.login(nick,pass);
				System.out.println("SUCCESS");
				
				if(FU.isAdmin(nick))
				{
					new AdminView();
					dispose();				
					System.out.println("Admin Connected");
				}
				else if (FU.isSeller(nick))
				{	
					new SellerView();
					dispose();
					System.out.println("Seller Connected");	
				}
				else
				{
					
					new SimpleUserView();
					dispose();
					System.out.println("Simple User Connected");				
				}
			} 
			catch (ObjectNotInTheDatabaseException e) 
			{
				JOptionPane.showMessageDialog(null, "Identifiant incorrect. Veuillez réessayer.", "Connexion", JOptionPane.ERROR_MESSAGE);			
			}
			catch (WrongPasswordException e)
			{
				JOptionPane.showMessageDialog(null, "Mot de passe incorrect. Veuillez réessayer.", "Connexion", JOptionPane.ERROR_MESSAGE);
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