import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {

	public Button(String str, int positionX, int positionY, int tailleX, int tailleY){
		super(str);
		
		this.setBounds(positionX, positionY, tailleX, tailleY);
		
		//this.setBackground(Color.LIGHT_GRAY);
		//this.setForeground(Color.BLACK);
		
		//this.setFont(font);
	}

	
}