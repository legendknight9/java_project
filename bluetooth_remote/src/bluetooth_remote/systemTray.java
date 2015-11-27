/**
 * 
 */
package bluetooth_remote;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * @author java_dev
 *
 */
public class systemTray implements ActionListener, ItemListener{

	private MenuItem aboutItem, connectItem, disconnectItem, exitItem;
	private CheckboxMenuItem startupItem;
	
	public systemTray() {
		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}
		// Check the SystemTray is supported
		final PopupMenu popup = new PopupMenu();
		final TrayIcon trayIcon = new TrayIcon((new ImageIcon(systemTray.class.getResource("trayicon.gif"), "")).getImage(), "Bluetooth Remote");
		final SystemTray tray = SystemTray.getSystemTray();

		// Create a pop-up menu components
		aboutItem = new MenuItem("About");
		connectItem = new MenuItem("Connect Remote");
		disconnectItem = new MenuItem("Disconnect");
		exitItem = new MenuItem("Exit");
		startupItem = new CheckboxMenuItem("Run on startup");
		
		// Add components to pop-up menu
		popup.add(aboutItem);
		popup.add(startupItem);
		popup.addSeparator();
		popup.add(connectItem);
		popup.add(disconnectItem);
		popup.addSeparator();
		popup.add(exitItem);

		trayIcon.setPopupMenu(popup);

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
		}
		
		aboutItem.setActionCommand(ABOUT_CMS); aboutItem.addActionListener(this);
		connectItem.setActionCommand(CONNECT_CMS); connectItem.addActionListener(this); 
		disconnectItem.setActionCommand(DISCONNECT_CMS); disconnectItem.addActionListener(this);
		exitItem.setActionCommand(EXIT_CMS); exitItem.addActionListener(this);
		startupItem.addItemListener(this);
		File startup_file = new File(FILE_PATH);
		if (startup_file.exists() && !startup_file.isDirectory()) { 
		    startupItem.setState(true);
		} else {
			startupItem.setState(false);
		}
	}

	private final String ABOUT_CMS = "0";
	private final String CONNECT_CMS = "1";
	private final String DISCONNECT_CMS = "2";
	private final String EXIT_CMS = "3";
	private final String STARTUP_CMS = "4";
	private final String FILE_PATH = System.getProperty("user.home") + "\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\bt_remote.bat";
	private final String STARTUP_PATH = "\"" + FILE_PATH + "\"";
	private final String INSTALL_PATH = "\"Z:\\D\\remote_1.4.jar\"";
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
		switch(Integer.parseInt(e.getActionCommand())) {
			case 0 : 	
				break;
			case 1 : 	btControlRemote.connect();
					 	connectItem.setLabel("Reconnect");
				break;
			case 2 : 	
				break;
			case 3 : 	System.exit(0);
				break;
			default : 
				break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getItem().toString().equals(startupItem.getLabel())) {
			try {
				if (startupItem.getState()) {								
					Runtime.getRuntime().exec("cmd /c echo start javaw -jar " + INSTALL_PATH + " > " + STARTUP_PATH);
				} else {
					Runtime.getRuntime().exec("cmd /c del " + STARTUP_PATH);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}	
}
