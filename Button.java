import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

/**
 * The class Button extends JButton.
 * <p>
 *
 * @param  		font (a {@link Font} giving the font type of the button),
 */
@SuppressWarnings("serial")
public class Button extends JButton {
	private Font font = new Font("courier", Font.BOLD, 16);

	public Button(String str, int positionX, int positionY, int tailleX, int tailleY){
		super(str);
		
		this.setBounds(positionX, positionY, tailleX, tailleY);
		
		this.setBackground(Color.LIGHT_GRAY);
		this.setForeground(Color.BLACK);
		
		this.setFont(font);
	}

	
}