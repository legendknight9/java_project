/**
 * 
 */
package nhut.java.mouse;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * @author java_dev
 *
 */
public class mouseMover {
	private Robot mouseHandler;
	
	public mouseMover() throws AWTException {
		mouseHandler = new Robot();
		mouseHandler.setAutoDelay(100);
	}

	public mouseMover(int autoDelayTime) throws AWTException {
		mouseHandler = new Robot();
		if ((autoDelayTime < 0) || (autoDelayTime > 60000)) autoDelayTime = 250;
		mouseHandler.setAutoDelay(autoDelayTime);
	}
	
	public void mousePress(int keyCode) {
		mouseHandler.mousePress(keyCode);
		mouseHandler.mouseRelease(keyCode);
	}
	
	public void mouseHold(int keyCode) {
		mouseHandler.mousePress(keyCode);
	}
	
	public void mouseRelease(int keyCode) {
		mouseHandler.mouseRelease(keyCode);
	}
	
	public void mouseDrag(int x, int y, int keyCode) {
		mouseHandler.mousePress(keyCode);
		mouseHandler.mouseMove(x, y);
		mouseHandler.mouseRelease(keyCode);
	}
	/*
	 * x, y : length to move cursor
	 * 	mouseMover.mouseMove(100, 0) : move right
	 *	mouseMover.mouseMove(0, 100) : move down
	 *	mouseMover.mouseMove(-100, 0) : move left
	 *	mouseMover.mouseMove(0, -100) :	move up
	 */
	public void mouseMove(int x, int y) {
		Point p = MouseInfo.getPointerInfo().getLocation();
		mouseHandler.mouseMove(p.x + x, p.y + y);
	}
	
	/*
	 * time : number of time to scroll
	 * time > 0 : scroll down
	 * time < 0 : scroll up
	 * 
	 * line = time x line/scroll
	 * 		line : number of lines which are scrolled up or down
	 * 		time : number of scroll time
	 * 		line/scroll : this depends on system settings.
	 */
	public void mouseScroll(int time) {
		mouseHandler.mouseWheel(time);
	}
	
	public static final byte LEFTMOUSEBT = 11;
	public static final byte MIDDLEMOUSEBT = 12;
	public static final byte RIGHTMOUSEBT = 13;
	public static final byte LEFTMOUSE = 14;
	public static final byte RIGHTMOUSE = 15;
	public static final byte UPMOUSE = 16;
	public static final byte DOWNMOUSE = 17;
	public static final byte SCROLLUP = 18;
	public static final byte SCROLLDOWN = 19;

	public void specialFunction(int command) {
		// TODO Auto-generated method stub
		switch(command) {
			case LEFTMOUSEBT:	mousePress(InputEvent.BUTTON1_DOWN_MASK);
				break;
			case MIDDLEMOUSEBT:	mousePress(InputEvent.BUTTON2_DOWN_MASK);
				break;
			case RIGHTMOUSEBT:	mousePress(InputEvent.BUTTON3_DOWN_MASK);
				break;
			case LEFTMOUSE:		mouseMove(-10, 0);
				break;
			case RIGHTMOUSE:	mouseMove(10, 0);
				break;
			case UPMOUSE:		mouseMove(0, -10);
				break;
			case DOWNMOUSE:		mouseMove(0, 10);
				break;
			case SCROLLUP:		mouseScroll(-1);
				break;
			case SCROLLDOWN:	mouseScroll(1);
				break;
		}
	}
}
