/**
 * 
 */
package run;

import gui.configUI;
import gui.mainUI;

import java.awt.EventQueue;

import util.constants;

/**
 * @author ThanhNhut
 *
 */
public class starter {
	private static String module;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 0) {
			module = args[0];
		} else {
			module = "";
		}
		module = "-c";
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						callUI(module);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}

	static void callUI(String UI) {
		switch(UI) {
			case "-e" :
				System.exit(0);
				break;
		    case "-c" :
		    	configUI ui = new configUI();
		    	ui.setVisible(true);
		    	break;
		    default :
		    	String defaultConfig[] = {constants.PROCESSOR, constants.INCLUDEFILE, constants.CONFIG1, constants.CONFIG2};
		    	mainUI frame = new mainUI(defaultConfig);
		    	frame.setVisible(true);
		    	break;
		}
	}
}
