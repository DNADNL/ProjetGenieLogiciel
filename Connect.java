import javax.swing.SwingUtilities;

public class Connect {

	 public static void main(String[] args)
	  {
		 
		 //Lancement fen�tre
		 	//LoginView mafenetre = new LoginView(); 
<<<<<<< HEAD
		 
		 
=======
	 
>>>>>>> origin/master
		 SwingUtilities.invokeLater(new Runnable(){

	            @Override
	            public void run()
	            {
	                new LoginView().setVisible(true);
	            }
	        });
	  }

}
