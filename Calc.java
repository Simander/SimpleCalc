import javax.swing.JFrame;


public class Calc 
{
	public static void main(String[] args)
	{
		GUI gui = new GUI();
		gui.setVisible(true);
		gui.setSize(300, 360);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
