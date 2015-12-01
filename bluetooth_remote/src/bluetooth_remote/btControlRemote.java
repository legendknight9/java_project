/**
 * 
 */
package bluetooth_remote;

import java.awt.AWTException;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

import nhut.java.keyboard.keyboardPresser;
import nhut.java.mouse.mouseMover;

/**
 * @author java_dev
 *
 */
public class btControlRemote implements Runnable{
	
	private static systemTray sysT;
	private final static String NOPASS_COMMENT = "# my btremote add-in";
	private final static String PATH_SEPARATOR = System.getProperty("file.separator");
	private final static String NEWLINE = System.getProperty("line.separator");
	private final static String NOPASS = NOPASS_COMMENT + NEWLINE
								  	+ System.getProperty("user.name") + " ALL=NOPASSWD: /usr/sbin/pm-hibernate";
	private final static String SUDOERS_FILE = PATH_SEPARATOR + "etc" + PATH_SEPARATOR + "sudoers.d" 
								  	+ PATH_SEPARATOR + "mybtpermission";
	
	public static void main(String[] args) {
		sysT = new systemTray();
		initialize(systemTray.OSNUMBER);
	}
	
	public static void initialize(int OSNUMBER) {
		if (OSNUMBER == systemTray.WIN) {
			
		} else {
//			try {
//				Runtime.getRuntime().exec("/bin/sh -c if [ ! -e " + SUDOERS_FILE + " ]; then echo \"hi\" > file;fi");
//				if ((new File("file")).exists()) {
////					Runtime.getRuntime().exec("rm file");
//					sysT.showMessage("OK", "no password require for pm-hibernate", MessageType.INFO);
//					return;
//				}
//				sysT.showMessage("Password require!!", "Input password to enable no password require for pm-hibernate", MessageType.INFO);
//				Runtime.getRuntime().exec("./a.sh");
//				Runtime.getRuntime().exec("bash -c echo \"" + NOPASS + "\" > ./a");
//				//Runtime.getRuntime().exec("sudo mv a " + SUDOERS_FILE);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			try {
				File f = new File("./mybtremote_permission");
				if (f.exists()) return;
				PrintWriter pw = new PrintWriter(new FileWriter(f, false), true);
				pw.println(NOPASS);
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sysT.showMessage("Update /etc/sudoers require !", 
					"chmod 0440, chown root & copy file mybtremote_permission to /etc/sudoers.d/ if necessary", MessageType.INFO);
		}
	}

	public static void connect() {
		(new File("./mybtremote_permission")).delete();
		//display local device address and name
		LocalDevice localDevice = null;
		try {
			localDevice = LocalDevice.getLocalDevice();
			localDevice.setDiscoverable(DiscoveryAgent.GIAC);
		} catch (BluetoothStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("TV Address: " + localDevice.getBluetoothAddress() + " TV Name: " + localDevice.getFriendlyName());
		Thread server = new Thread(new btControlRemote());
		server.start();
	}

	@Override
	public void run() {
		//Create a UUID for SPP
		UUID uuid = new UUID("1101", true);
		//Create the servicve url
		String connectionString = "btspp://localhost:" + uuid.toString() + ";authenticate=true;encrypt=true";// uuid.toString() + ";name=TV";
		try {
			//open server url
			StreamConnectionNotifier streamConnNotifier = (StreamConnectionNotifier)Connector.open(connectionString);

			//Wait for client connection
			System.out.println("\nServer Started. Waiting for clients to connect...");
			StreamConnection connection = streamConnNotifier.acceptAndOpen();
			System.out.println("Connected.");

			RemoteDevice dev = RemoteDevice.getRemoteDevice(connection);
			System.out.println("BT Remote address: " + dev.getBluetoothAddress());
			System.out.println("BT Remote name: " + dev.getFriendlyName(true));

			//read string from spp client
			InputStream inStream = connection.openInputStream();
			while (true) {
				int command = inStream.read();				
				if (command == 255) {
					break;
				} else if (command < 11) {
					keyboardPresser kp = new keyboardPresser();
					kp.specialFunction(command);
				} else {
					mouseMover mm = new mouseMover();
					mm.specialFunction(command);
				}
			}
			streamConnNotifier.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
