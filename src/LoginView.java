import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginView  extends JFrame implements ActionListener{

	FacadeUser FU = new FacadeUser();
	JTextField userText;
	JPasswordField passwordText;
	
	  
	 
	  
	  public LoginView(int height, int width) 
	  {
		JFrame frame = new JFrame("Login");
		JPanel panel = new JPanel();
		frame.add(panel);
	    frame.setSize(width, height);
	    
	    panel.setLayout(null);

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		this.userText = new JTextField(20);
		this.userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 80, 80, 25);
		loginButton.addActionListener(this);
		panel.add(loginButton);
		
		//JButton registerButton = new JButton("register");
		//registerButton.setBounds(180, 80, 80, 25);
		//this.add(registerButton);
	    
	    frame.setVisible(true);
	  }

		
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			String password = new String(passwordText.getPassword());
			String nickname = userText.getText();
			this.buttonClicked(nickname, password);
		}
		
		private void buttonClicked(String nick, String pass)
		{
			int bool = 0;
			System.out.println("test");
			bool = FU.login(nick,pass);
			connectionResult(bool);
		}
		
		private void connectionResult(int bool)
		{
			if (bool == 1)
			{
				String nom = FU.getUser().nicknameUser;
				
				
				JFrame frame = new JFrame("Connexion réussie");
				JPanel panel = new JPanel();
				frame.add(panel);
			    frame.setSize(500, 100);
			    
			    JLabel userLabel = new JLabel(nom + " est maintenant connecté");
				userLabel.setBounds(10, 10, 80, 25);
				panel.add(userLabel);
				
			    frame.setVisible(true);
			}
			else
			{
				JFrame frame = new JFrame("Connexion échouée");
				JPanel panel = new JPanel();
				frame.add(panel);
			    frame.setSize(500, 100);
			    
			    JLabel userLabel = new JLabel("Mauvais nom d'utilisateur ou Mauvais mot de passe");
				userLabel.setBounds(10, 10, 80, 25);
				panel.add(userLabel);
				
			    frame.setVisible(true);
			}
		}

	

}
