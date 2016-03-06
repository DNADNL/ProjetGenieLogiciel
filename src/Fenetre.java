import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Fenetre extends JFrame  {


	//création de la fenêtre de connexion
	JPanel panel1 = new JPanel();

	//création de la fenêtre de connexion réussi
	JPanel panel2 = new JPanel();
	
	Button loginButton = new Button("LOGIN", 220, 170, 90, 30);
	Button logoutButton = new Button("SE DECONNECTER",180, 170, 190, 30);
	
	JTextField userText = new JTextField("maroane");
	JPasswordField passwordText = new JPasswordField(20);
	String log = "pas encore de log";


	public Fenetre(){

		super("Login");

		// Options de la fenetre
		this.setSize(500,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// Ajout du Bouton Login sur la fenetre 1
		
		loginButton.addActionListener(new BoutonLoginListener());
		panel1.add(loginButton);

		// Ajout du Bouton Logout sur la fenetre 2
		logoutButton.addActionListener(new BoutonLogoutListener());
		panel2.add(logoutButton);

		// textfield 
		
		userText.setBounds(180, 100, 160, 25);
		panel1.add(userText);
		
		passwordText.setBounds(180, 130, 160, 25);
		panel1.add(passwordText);

		this.setContentPane(this.panel1);

		//Ajout des champs de textes et labels 
		placeComponents(panel1);
		placeComponentsFenetre2(panel2);


		this.setVisible(true);
	}

	// Methode d'ajout des labels et textfield à la fenetre 1
	public static void placeComponents(JPanel panel) {

		panel.setLayout(null);
		Font fontTitre = new Font("Courier", Font.BOLD, 20);
		Font font = new Font("Courier", Font.BOLD, 15);

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

		

		JLabel TitreCo = new JLabel("<html>TEST DE REDOUBLEMENT <br>Entrez votre prénom :</html>");
		TitreCo.setBounds(100, 10, 400, 80);
		TitreCo.setFont(fontTitre);
		TitreCo.setForeground(Color.BLACK);
		panel.add(TitreCo);

		JLabel image = new JLabel(new ImageIcon("8.jpg"));
		panel.setLayout(new BorderLayout());
		panel.add(image, BorderLayout.CENTER);




	}

	// Methode d'ajout des labels et textfield à la fenetre 1
	private void placeComponentsFenetre2(JPanel panel) {

		panel.setLayout(null);
		Font fontTitre = new Font("Courier", Font.BOLD, 30);
		Font font = new Font("Courier", Font.BOLD, 15);

		
		
		System.out.println(log);
		JLabel TitreCo = new JLabel("OUI TU REDOUBLES DFP !");
		TitreCo.setBounds(60, 10, 400, 200);
		TitreCo.setFont(fontTitre);
		TitreCo.setForeground(Color.BLACK);
		panel.add(TitreCo);

		JLabel image = new JLabel(new ImageIcon("8.jpg"));
		panel.setLayout(new BorderLayout());
		panel.add(image, BorderLayout.CENTER);




	}

	// Methode pour aller à la fenetre 2
	public void allerVersFenetre2(){
		this.setContentPane(this.panel2);
		System.out.println(log);
		this.revalidate();
	}

	// Methode pour revenir à la fenetre 1
	public void allerVersFenetre1(){
		this.setContentPane(this.panel1);
		this.revalidate();
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fenetre fen = new Fenetre();

	}
	
	//Classe écoutant notre premier bouton
	  class BoutonLoginListener implements ActionListener{
	    //Redéfinition de la méthode actionPerformed()
	    public void actionPerformed(ActionEvent arg0) {
	    	log = userText.getText();
	    	String mdp = passwordText.getText();
	    	System.out.println("login : "+log);
	    	System.out.println("mot de passe : "+mdp);
	    	
	    	if (mdp.equals("a") || log.equals("maroane")){
	    		allerVersFenetre2();
	    	}
	    	else
	    		System.out.println("MAUVAIS MOT DE PASSE !");
	    }
	  }
	      
	  //Classe écoutant notre second bouton
	  class BoutonLogoutListener implements ActionListener{
	    //Redéfinition de la méthode actionPerformed()
	    public void actionPerformed(ActionEvent e) {
	         
	    	allerVersFenetre1();
	    }
	  }      
	
	

}
