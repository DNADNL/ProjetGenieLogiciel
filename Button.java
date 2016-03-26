
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException; 
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Button extends JButton {
	private String name;
	private Image img;
	private Font font = new Font("courier", Font.BOLD, 16);

	public Button(String str, int positionX, int positionY, int tailleX, int tailleY){
		super(str);
		this.name = str;
		
		
		this.setBounds(positionX, positionY, tailleX, tailleY);
		
		this.setBackground(Color.LIGHT_GRAY);
		this.setForeground(Color.BLACK);
		
		this.setFont(font);
	}

	
}