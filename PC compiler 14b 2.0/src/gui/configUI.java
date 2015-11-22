package gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.FileHandler;
import util.UICreator;
import util.constants;
import util.other;

public class configUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8583609417781634362L;

	/**
	 * Create the frame.
	 */
	public configUI() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		final JPanel pane = (JPanel) getContentPane();
		//setContentPane(UICreator.createConfigUI("", ""));

		String path = System.getProperty("user.dir") + "\\" +constants.DFDLIST;
		String data[] = FileHandler.readFile(new File(path), -1);
		ArrayList<String> list = new ArrayList<String>();
		String temp = "";
		StringBuilder groupDeviceBuilder = new StringBuilder();
		final Hashtable<String, String> configFileTable = new Hashtable<String, String>();
		for (int index = 0; index < data.length; index++) {		
			if (data[index].startsWith("[[")) {
				temp = data[index].substring(2);				
			} else if (data[index].startsWith("]]>>")) {
				configFileTable.put(groupDeviceBuilder.toString(), data[index].substring(4));
				groupDeviceBuilder = new StringBuilder();
				continue;
			} else {
				temp = data[index];
			}
			groupDeviceBuilder.append(temp + " ");
			list.add(temp);
		}
		String deviceList[] = other.toArray(list);
		final CardLayout layout = new CardLayout();		
		JPanel selectPanel = UICreator.createSelectPICUI(deviceList);
		pane.setLayout(layout);
		pane.add(selectPanel);
		//layout.addLayoutComponent(selectPanel, constants.SELECTDEVICEUI_TITLE);	
		JButton btnNext = (JButton)((JPanel)selectPanel.getComponent(1)).getComponent(1);
		@SuppressWarnings("unchecked")
		final JComboBox<String> comboBoxDevice = (JComboBox<String>)((JPanel)selectPanel.getComponent(0)).getComponent(1);
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//panel.getParent().getParent().getParent().setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							String device = (String)comboBoxDevice.getSelectedItem();
							String configFile = "";
							Set<String> groupDevice = configFileTable.keySet();
							for (String group : groupDevice) {
								if (group.contains(device)) {
								    configFile = configFileTable.get(group);
								}
							}		
							pane.add(UICreator.createConfigUI(device, configFile), constants.CONFIGUI_TITLE);
							layout.next(pane);		
//							layout.show(pane, constants.CONFIGUI_TITLE);
							((JFrame)pane.getParent().getParent().getParent()).pack();

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
				
		setTitle(constants.APPNAME + " " + constants.VERSION);		
		layout.first(pane);
		pack();
	}
}