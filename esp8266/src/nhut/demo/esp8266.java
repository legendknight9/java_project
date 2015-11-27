/**
 * 
 */
package nhut.demo;

import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JTextField;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * @author Nhut
 *
 */
public class esp8266 {

	/**
	 * @param args
	 */
	 
	public SerialPort sp;
	
    public esp8266(String portName) throws SerialPortException {
    	sp = new SerialPort(portName);
		sp.openPort();
		sp.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		sp.addEventListener(new SerialPortEventListener() {
			
			@Override
			public void serialEvent(SerialPortEvent event) {
				// TODO Auto-generated method stub
				if (event.isRXCHAR() && event.getEventValue() > 0) {
					try {
						System.out.println(sp.readString(event.getEventValue()));
					} catch (SerialPortException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
    }
    
    public static void main(String[] args) {
    	try {
    		esp8266 wm = new esp8266("COM5");
			while(true) {
//				wm.sp.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
//				wm.sp.writeString("AT");//AT+RST\nAT+GMR\n");
//				TimeUnit.SECONDS.sleep(1);
//				wm.sp.setParams(SerialPort.BAUDRATE_14400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
//				wm.sp.writeString("AT+RST\r\n");//AT+RST\nAT+GMR\n");
//				TimeUnit.SECONDS.sleep(5);
//				wm.sp.writeString("AT\r\n");//AT+RST\nAT+GMR\n");
//				TimeUnit.SECONDS.sleep(1);
//				wm.sp.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
//				wm.sp.writeString("AT");
//				TimeUnit.MILLISECONDS.sleep(500);
//				wm.sp.writeString("AT+RST");
//				TimeUnit.SECONDS.sleep(1);
//				wm.sp.writeString("AT+GMR\r\n");
//				TimeUnit.MILLISECONDS.sleep(500);
//				wm.sp.writeString("AT+CWMODE=3\r\n");
//				TimeUnit.MILLISECONDS.sleep(5000);		
//				wm.sp.writeString("AT+CWSAP=\"HOUSEKEEPER\",\"THANHNHUT\",9,3");
//				wm.sp.writeString("\r\n");
//				TimeUnit.MILLISECONDS.sleep(4000);
//				wm.sp.writeString("AT+CWLAP\r\n"); 
//				TimeUnit.SECONDS.sleep(5);
//				TimeUnit.MILLISECONDS.sleep(500);
//				wm.sp.writeString("AT+CIFSR\r\n");
//				TimeUnit.MILLISECONDS.sleep(400);
//				wm.sp.writeString("AT+CWJAP=" + "\"INTERNET\"" + ",\"THANHNHUT\"");
//				wm.sp.writeString("\r\n");
//				TimeUnit.SECONDS.sleep(10);+
//				wm.sp.writeString("AT+CWSAP=\"HOUSEKEEPER\",\"THANHNHUT\",9,3");
//				wm.sp.writeString("\r\n");
//				TimeUnit.MILLISECONDS.sleep(4000);
//				wm.sp.writeString("AT+CWLIF\r\n");
//				TimeUnit.MILLISECONDS.sleep(4000);
//				wm.sp.writeString("AT+CWSAP?\r\n");
//				wm.sp.writeString("AT+CIPMUX=1\r\n");
//				TimeUnit.MILLISECONDS.sleep(4000);				
				wm.sp.writeString("AT+CIPSTART=4,\"TCP\",\"192.168.0.20\",800\r\n");
				TimeUnit.MILLISECONDS.sleep(4000);
				String message = "Devices status : light1 on, light2 off, light3 off, fan1 on, fan2 off";
				wm.sp.writeString("AT+CIPSEND=4," + message.length() + "\r\n");
				TimeUnit.MILLISECONDS.sleep(4000);
				wm.sp.writeString(message);
				 
				TimeUnit.SECONDS.sleep(100);				
			}
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
