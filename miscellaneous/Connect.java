package miscellaneous;
import javax.swing.SwingUtilities;

import jdbc.JDBConnectionOpen;

public class Connect {

	 public static void main(String[] args)
	  {
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
