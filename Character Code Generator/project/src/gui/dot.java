/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author ThanhNhut
 *
 */
public class dot extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6770883791847515026L;
	private int state;
	
    public dot() {
		setState(0);
        this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reverseState();
			}
		});
        
        this.setPreferredSize(constants.DOTSIZE);
    }

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		if (state == 1) {
			this.state = 1;
			setBackground(constants.BIT1COLOR);
		} else {
			setBackground(constants.BIT0COLOR);
			this.state = 0;
		}
	}
	
	public void reverseState() {
		if (getBackground() == constants.BIT0COLOR) {			
			setState(1);
		} else {
			setState(0);
		}		
	}
}
