package Hangman;

import javax.swing.JApplet;

public class Applet extends JApplet{
	private static final long serialVersionUID = 1L;

	public void init (){
		GamePanel panel = new GamePanel ();
		getContentPane ().add(panel);
		setSize (1200,500);
		setVisible (true);
	}
}
