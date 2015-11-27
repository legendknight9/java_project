/**
 * 
 */
package nhut.java.keyboard;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.*;


/**
 * @author java_dev
 *
 */
public class keyboardPresser {

	private Robot keyPresser;
	
	public keyboardPresser() throws AWTException {
		keyPresser = new Robot();
		keyPresser.setAutoDelay(100);
	}
	
	public keyboardPresser(int autoDelayTime) throws AWTException {
		keyPresser = new Robot();
		if ((autoDelayTime < 0) || (autoDelayTime > 60000)) {
			autoDelayTime = 250;
		}
		keyPresser.setAutoDelay(autoDelayTime);
	}
	
	public void pressKey(int keyCode) {
		keyPresser.keyPress(keyCode);
		keyPresser.keyRelease(keyCode);		
	}
	
	public void holdKey(int keyCode) {
		keyPresser.keyPress(keyCode);
	}
	
	public void releaseKey(int keyCode) {
		keyPresser.keyRelease(keyCode);
	}
	
	public void pressComboKeys(int comboKeys[]) {
		for (int key : comboKeys) keyPresser.keyPress(key);
		keyPresser.delay(250);
		for (int key : comboKeys) keyPresser.keyRelease(key);
	}
	
	public static final int LOCKSCREEN = 0;
	public static final int HIBERNATE = 1;
	public static final int LOGOFF = 2;
	public static final int RESTART = 3;
	public static final int SHUTDOWN = 4;
	public static final int VOLUMEPLUS = 5;
	public static final int VOLUMEMINUS = 6;
	public static final int VOLUMEMUTE = 7;
	public static final int SWITCHAPP = 8;
	public static final int CLOSEAPP = 9;
	public static final int CONFIRM = 10;
	
	public void specialFunction(int function) throws IOException {
		switch(function) {
			case LOCKSCREEN: Runtime.getRuntime().exec("rundll32 user32.dll,LockWorkStation");
					break;
			case HIBERNATE: Runtime.getRuntime().exec("Rundll32.exe powrprof.dll,SetSuspendState Sleep");
					break;
			case LOGOFF: Runtime.getRuntime().exec("shutdown /l");
					break;
			case RESTART: Runtime.getRuntime().exec("shutdown /r /f /t 20");
					break;
			case SHUTDOWN: Runtime.getRuntime().exec("shutdown /s /f /t 20");
					break;			
			case VOLUMEPLUS: Runtime.getRuntime().exec("sndvol"); keyPresser.delay(1600);
							// pressComboKeys(new int[] {KeyEvent.VK_ALT, KeyEvent.VK_SHIFT, KeyEvent.VK_TAB, KeyEvent.VK_TAB});							 
							 pressKey(KeyEvent.VK_UP); pressKey(KeyEvent.VK_UP); pressKey(KeyEvent.VK_UP); 
							 pressKey(KeyEvent.VK_UP); pressKey(KeyEvent.VK_UP);
							 pressComboKeys(new int[] {KeyEvent.VK_ALT, KeyEvent.VK_F4});
					break;
			case VOLUMEMINUS: Runtime.getRuntime().exec("sndvol"); keyPresser.delay(1600);
							  //pressComboKeys(new int[] {KeyEvent.VK_ALT, KeyEvent.VK_SHIFT, KeyEvent.VK_TAB, KeyEvent.VK_TAB});
							  pressKey(KeyEvent.VK_DOWN); pressKey(KeyEvent.VK_DOWN); pressKey(KeyEvent.VK_DOWN);
							  pressKey(KeyEvent.VK_DOWN); pressKey(KeyEvent.VK_DOWN);
							  pressComboKeys(new int[] {KeyEvent.VK_ALT, KeyEvent.VK_F4});
					break;
			case VOLUMEMUTE: Runtime.getRuntime().exec("sndvol"); keyPresser.delay(1600);
							 //pressComboKeys(new int[] {KeyEvent.VK_ALT, KeyEvent.VK_SHIFT, KeyEvent.VK_TAB, KeyEvent.VK_TAB});						 
							 pressKey(KeyEvent.VK_TAB);
							 pressKey(KeyEvent.VK_SPACE);
							 pressComboKeys(new int[] {KeyEvent.VK_ALT, KeyEvent.VK_F4});
					break;
			case SWITCHAPP: pressComboKeys(new int[] {KeyEvent.VK_ALT, KeyEvent.VK_TAB});
					break;
			case CLOSEAPP:  pressComboKeys(new int[] {KeyEvent.VK_ALT, KeyEvent.VK_F4});
					break;
			case CONFIRM:	pressKey(KeyEvent.VK_ENTER);
					break;				
			default : 
					break;
		}
	}
	
//	public void fixMicrophone() throws Exception {
//		Port microphone = (Port) AudioSystem.getLine(Port.Info.MICROPHONE);
//		try {
//			microphone.open();
//			for (Control control : microphone.getControls()) {
//				if (control instanceof CompoundControl) {
//					CompoundControl cc = (CompoundControl) control;
//					for (Control control1 : cc.getMemberControls()) {
//						String name = control1.getType().toString();
//						if (name.equals("Select")) {
//							BooleanControl select = (BooleanControl) control1;
//							select.setValue(true);
//						} else if (name.equals("Microphone Boost")) {
//							BooleanControl boost = (BooleanControl) control1;
//							boost.setValue(false);
//						} else if (name.equals("Volume")) {
//							FloatControl volume = (FloatControl) control1;
//							volume.setValue(volume.getMaximum());
//						}
//					}
//				}
//			}
//		} finally {
//			microphone.close();
//		}
//	}
	
	public void setMasterVolume(int volumeValue) throws LineUnavailableException {
		Port speaker = (Port) AudioSystem.getLine(Port.Info.SPEAKER);
		speaker.open();
		Control temp[] = speaker.getControls();
		CompoundControl masterVolume = null;
		
		for (Control control : speaker.getControls()) {
			String name = control.getType().toString();
			if (name.equals("Master Volume")) {
				masterVolume = (CompoundControl)control;		
				break;
			}
		}
		if (masterVolume == null) return;
		
		if (volumeValue == 0) {
			for (Control control : masterVolume.getMemberControls()) {
				String name = control.getType().toString();
				if (name.equals("Mute")) {
					BooleanControl mute_Checkbox = (BooleanControl) control;
					mute_Checkbox.setValue(true);
					System.out.println("mute checkbox is " + mute_Checkbox.getValue());
					break;
				}				
			}
		} else {			
			for (Control control : masterVolume.getMemberControls()) {
				String name = control.getType().toString();
				if (name.equals("Volume")) {
					FloatControl volume = (FloatControl) control;
					volume.setValue(volume.getValue() + 0.05f);
					System.out.println("volume value is " + volume.getValue());
				}
			}
		}
	}
	
//	public void fixSpeaker() throws Exception {
//		Port speaker = (Port) AudioSystem.getLine(Port.Info.SPEAKER);
//		try {
//			speaker.open();
//			for (Control control : speaker.getControls()) {
//				String name = control.getType().toString();
//				if (name.equals("Volume")) {
//					FloatControl volume = (FloatControl) control;
//					volume.setValue(volume.getMaximum());
//				} else if (name.equals("Balance")) {
//					FloatControl balance = (FloatControl) control;
//					balance.setValue(balance.getMinimum() + (balance.getMaximum() - balance.getMinimum()) / 2.0F);
//				} else if (name.equals("Mute")) {
//					BooleanControl mute = (BooleanControl) control;
//					mute.setValue(false);
//				} else if (control instanceof CompoundControl) {
//					CompoundControl cc = (CompoundControl) control;
//					for (Control control1 : cc.getMemberControls()) {
//						String name1 = control1.getType().toString();
//						if (name.equals("Microphone")) {
//							if (name1.equals("Microphone Boost")) {
//								BooleanControl boost = (BooleanControl) control1;
//								boost.setValue(false);
//							} else if (name1.equals("Volume")) {
//								FloatControl volume = (FloatControl) control1;
//								volume.setValue(volume.getMaximum());
//							} else if (name1.equals("Mute")) {
//								BooleanControl mute = (BooleanControl) control1;
//								mute.setValue(true);
//							}
//						} else if (name.equals("Wave")) {
//							if (name1.equals("Volume")) {
//								FloatControl volume = (FloatControl) control1;
//								volume.setValue(volume.getMaximum());
//							} else if (name1.equals("Balance")) {
//								FloatControl balance = (FloatControl) control1;
//								balance.setValue(
//										balance.getMinimum() + (balance.getMaximum() - balance.getMinimum()) / 2.0F);
//							} else if (name1.equals("Mute")) {
//								BooleanControl mute = (BooleanControl) control1;
//								mute.setValue(false);
//							}
//						}
//					}
//
//				}
//			}
//
//		} finally {
//			speaker.close();
//		}
//	}
}