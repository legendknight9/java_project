import gui.charactercreatefunctiongui;
import gui.maingui;

/**
 * 
 */

/**
 * @author ThanhNhut
 *
 */
public class generator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		final charactercreatefunctiongui gui = new charactercreatefunctiongui();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {       	            	
            	gui.showGUI();
            }
        });
	}

}