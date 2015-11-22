/**
 * 
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * @author ThanhNhut
 *
 */
public class setmodedialog extends JDialog {
	setmodedialog(JFrame frame, String title) {
	    super(frame, title);
	    setModalExclusionType(JDialog.ModalExclusionType.NO_EXCLUDE);
		    
		JPanel contentPanel = createSelectModeDialog();
		setContentPane(contentPanel);	
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setModal(true);
		pack();
		setLocationRelativeTo(frame);
		setVisible(true);
	}
	
	private JPanel createSelectModeDialog() {
		JPanel ModeDialogPanel = new JPanel(new GridLayout(7, 1, 5, 5));
		
		JRadioButton freemodeRB = new JRadioButton(constants.FREEMODESTRING);
		freemodeRB.setActionCommand(constants.FREEMODESTRING);
		JRadioButton halfmodeRB = new JRadioButton(constants.HALFMODESTRING);
		halfmodeRB.setActionCommand(constants.HALFMODESTRING);
		JRadioButton fullmodeRB = new JRadioButton(constants.FULLMODESTRING);
		fullmodeRB.setActionCommand(constants.FULLMODESTRING);
		JRadioButton ctrlhalfmodeRB = new JRadioButton(constants.CTRLHALFMODESTRING);
		ctrlhalfmodeRB.setActionCommand(constants.CTRLHALFMODESTRING);
		JRadioButton ctrlfullmodeRB = new JRadioButton(constants.CTRLFULLMODESTRING);
		ctrlfullmodeRB.setActionCommand(constants.CTRLFULLMODESTRING);
		
		final ButtonGroup group = new ButtonGroup();
		group.add(freemodeRB);
		group.add(halfmodeRB);
		group.add(fullmodeRB);
		group.add(ctrlhalfmodeRB);
		group.add(ctrlfullmodeRB);
		freemodeRB.setSelected(true);

		final JLabel messageLB = new JLabel(constants.SELECTMODEDIALOGMESSAGE);
		JPanel BTPanel = new JPanel(new GridLayout(1, 2, 10, 5));
		JButton confirmBT = new JButton(constants.CONFIRMBTLABEL);
		confirmBT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				messageLB.setText(group.getSelection().getActionCommand());
				((JDialog)messageLB.getParent().getParent().getParent().getParent()).setVisible(false);
			}
		});

		JButton cancelBT = new JButton(constants.CANCELBTLABEL);
		cancelBT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				messageLB.setText("");
				((JDialog)messageLB.getParent().getParent().getParent().getParent()).setVisible(false);
			}
		});
		BTPanel.add(confirmBT);
		BTPanel.add(cancelBT);
				
		ModeDialogPanel.add(messageLB);
		ModeDialogPanel.add(freemodeRB);
		ModeDialogPanel.add(halfmodeRB);
		ModeDialogPanel.add(fullmodeRB);
		ModeDialogPanel.add(ctrlhalfmodeRB);
		ModeDialogPanel.add(ctrlfullmodeRB);
		ModeDialogPanel.add(BTPanel);
		
		return ModeDialogPanel;
	}
}
