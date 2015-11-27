/**
 * 
 */
package bluetooth_remote;

import java.awt.AWTException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
public class btControlRemote {
	

	public static void main(String[] args) {
		systemTray sysT = new systemTray();		
	}

	public static void connect() {
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
