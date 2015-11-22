package gui;

import javax.swing.JFrame;

import util.UICreator;
import util.constants;

public class configUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8583609417781634362L;

	/**
	 * Create the frame.
	 */
	public configUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setContentPane(UICreator.createConfigUI());
		
		pack();
		setTitle(constants.APPNAME + " " + constants.VERSION);
		
	}
}
