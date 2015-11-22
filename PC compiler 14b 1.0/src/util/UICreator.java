/**
 * 
 */
package util;

import gui.FileTab;
import gui.mainUI;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

/**
 * @author ThanhNhut
 * 
 */
public class UICreator {
	public static FileTab createFileTab(File file) throws IOException {
		String fileContent;
		byte data[] = FileHandler.getNextData(0, file);
		if (data != null) {
			StringBuilder fileContentBuilder = new StringBuilder();
			for (int index = 0; index < data.length; index++) {
				fileContentBuilder.append((char) data[index]);
			}
			fileContent = fileContentBuilder.toString();
		} else {
			data = new byte[0];
			fileContent = "";
		}
		final JTextArea fileDisplayer = new JTextArea();

		fileDisplayer.setText(fileContent);
		fileDisplayer.setCaretPosition(0);
		final FileTab tab = new FileTab(data.length, file, fileDisplayer);

		final JScrollBar vScrollBar = tab.getVerticalScrollBar();
		vScrollBar.setOrientation(JScrollBar.VERTICAL);
		vScrollBar.setUnitIncrement(16);
		vScrollBar.addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				if (vScrollBar.getValue() == vScrollBar.getMaximum()
						- vScrollBar.getHeight()) {
					String fileContent = tab.nextString();
					DefaultCaret caret = (DefaultCaret) fileDisplayer
							.getCaret();
					caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
					fileDisplayer.setText(fileDisplayer.getText() + fileContent);
					caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
				}
			}
		});

		return tab;
	}

	@SuppressWarnings("rawtypes")
	public static JPanel createConfigUI() {
		String data[] = FileHandler.readFile(new File(constants.OPTIONFILEPATH));
		int line = 0;
		ArrayList<JLabel> groupName = new ArrayList<JLabel>();
		final ArrayList<JComboBox> groupListOption = new ArrayList<JComboBox>();
		ArrayList<String> optionString = null;
		@SuppressWarnings("unused")
		ArrayList<String> selectedOptions = new ArrayList<String>();

		do {
			if (data[line].startsWith("[[")) {
				groupName.add(new JLabel(((String) data[line]).substring(2)));
				optionString = new ArrayList<String>();
			} else if (data[line].startsWith("]]")) {
				groupListOption.add(new JComboBox<String>(other
						.toArray(optionString)));
			} else {
				if (data[line].contains(";")) {
					String option = data[line].substring(0, data[line].indexOf(" "));
					optionString.add(data[line].substring(data[line].indexOf(";") + 2) + " " + option);					
				}
			}
			line++;
		} while (line < data.length);

		final JPanel panel = new JPanel(new GridLayout(groupName.size() + 2, 1, 5, 5));
		panel.add(new JLabel(constants.CONFIGUI_TITLE), JPanel.CENTER_ALIGNMENT);
		for (int index = 0; index < groupName.size(); index++) {
			JPanel col = new JPanel(new RelativeLayout(RelativeLayout.X_AXIS, 3));
			col.add(groupName.get(index), new Float(2));
			col.add(groupListOption.get(index), new Float(5));
			panel.add(col);
		}
		JButton setBT = new JButton(constants.CONFIGUI_SELECTBT);
		setBT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.getParent().getParent().getParent().setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							String CONFIG1 = "", CONFIG2 = "", PROCESSOR = "", INCLUDEFILE = ""; 
							for (int index = 1; index < groupListOption.size() - 2; index++) {
								String desc = (String)groupListOption.get(index).getSelectedItem();
								CONFIG1 += " & " + desc.substring(desc.lastIndexOf(" _") + 1, desc.length());
							}
							for (int index = groupListOption.size() - 2; index < groupListOption.size(); index++) {
								String desc = (String)groupListOption.get(index).getSelectedItem();
								CONFIG2 += " & " + desc.substring(desc.lastIndexOf(" _") + 1, desc.length());
							}
							CONFIG1 = CONFIG1.substring(3);
							CONFIG2 = CONFIG2.substring(3);
							PROCESSOR = (String)groupListOption.get(0).getSelectedItem();
							PROCESSOR = PROCESSOR.substring(PROCESSOR.lastIndexOf(" _") + 2, PROCESSOR.length());
							INCLUDEFILE = "p" + PROCESSOR + ".inc";
							String config[] = {PROCESSOR, INCLUDEFILE, CONFIG1, CONFIG2};
					    	mainUI frame = new mainUI(config);
					    	frame.setVisible(true);				
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});	
			}
		});
		panel.add(setBT);

		return panel;
	}
}
