import javax.swing.SwingUtilities;

public class Connect {

	 public static void main(String[] args)
	  {
		 
		 //Lancement fenêtre
		 	//LoginView mafenetre = new LoginView(); 

		 SwingUtilities.invokeLater(new Runnable(){

	            @Override
	            public void run()
	            {
	            	JDBConnectionOpen.getJDBCO();
	                new LoginView().setVisible(true);
	            }
	        });
	  }

}
