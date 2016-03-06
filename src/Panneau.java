import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Panneau extends JPanel {
	
	public void paintComponent(Graphics g){
	    try {
	      Image img = ImageIO.read(new File("8.jpg"));
	      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	      //Pour une image de fond
	      //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }                
	  }          
}
