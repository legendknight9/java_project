package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import util.FHandler;
import util.RelativeLayout;
import util.UICreator;
import util.constants;

public class projectUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4201880682592929312L;
	private JPanel contentPane;
	private JTextField projectPathTF, projectNameTF;
	private JFrame thisFrame;

	/**
	 * Create the frame.
	 */
	public projectUI(final String[] asmFileContent) {
		thisFrame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel row1 = new JPanel();
		row1.setBounds(new Rectangle(10, 10, 440, 30));
		contentPane.add(row1);
		row1.setLayout(new RelativeLayout(RelativeLayout.X_AXIS, 10));

		JLabel lblProjectPath = new JLabel("Project path ");
		row1.add(lblProjectPath, new Float(1));
		projectPathTF = new JTextField();
		row1.add(projectPathTF, new Float(3));
		final JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fc.showDialog(thisFrame, "Select project Folder");
				if ((result == JFileChooser.CANCEL_OPTION)
						|| (result == JFileChooser.ERROR_OPTION)) {
					return;
				} else {
					projectPathTF.setForeground(Color.BLACK);
					projectPathTF.setText(fc.getSelectedFile().getPath());
				}
			}
		});
		row1.add(btnBrowse, new Float(1));

		JPanel row1_2 = new JPanel();
		row1_2.setBounds(new Rectangle(10, 45, 350, 30));
		contentPane.add(row1_2);
		row1_2.setLayout(new RelativeLayout(RelativeLayout.X_AXIS, 10));
		JLabel lblProjectName = new JLabel("Project Name");
		row1_2.add(lblProjectName, new Float(1));
		projectNameTF = new JTextField();
		projectNameTF.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

			}

			@Override
			public void focusGained(FocusEvent e) {
				projectNameTF.setForeground(Color.BLACK);
				if (projectNameTF.getText().equals(
						constants.PROJECTUI_EMPTYPROJECTNAME_ERROR)
						|| projectNameTF.getText()
								.equals(constants.PROJECTUI_EXISTINGPROJECTFOLDER_ERROR)) {
					projectNameTF.setText("");
				}
			}
		});
		row1_2.add(projectNameTF, new Float(3));

		final JPanel row2 = new JPanel();
		row2.setBounds(new Rectangle(10, 85, 440, 100));

		contentPane.add(row2);
		row2.setLayout(null);
		JLabel lblHeaderFiles = new JLabel("Header files");
		row2.add(lblHeaderFiles);
		lblHeaderFiles.setBounds(10, 0, 400, 20);
		final JPanel headerFiles = new JPanel();
		JScrollPane hScrollPane = new JScrollPane(headerFiles);
		hScrollPane.setBounds(10, 25, 420, 50);
		hScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		headerFiles.setBounds(10, 10, 420, 50);
		row2.add(hScrollPane);
		headerFiles.setLayout(new BoxLayout(headerFiles, BoxLayout.Y_AXIS));

		final JButton btnAddHFile = new JButton("Add file");
		btnAddHFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(btnAddHFile);
				JPanel fileRow = UICreator.createAddFileRow(fc
						.getSelectedFile().getPath());
				headerFiles.add(fileRow);
				reSize();
			}
		});
		row2.add(btnAddHFile);
		btnAddHFile.setBounds(10, 80, 80, 20);

		JPanel row3 = new JPanel();
		row3.setBounds(new Rectangle(10, 190, 440, 100));

		contentPane.add(row3);
		row3.setLayout(null);
		JLabel lblSourceFiles = new JLabel("Source files");
		lblSourceFiles.setBounds(10, 0, 400, 20);
		row3.add(lblSourceFiles);
		final JPanel sourceFiles = new JPanel();
		JScrollPane sScrollPane = new JScrollPane(sourceFiles);
		sScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sScrollPane.setBounds(10, 25, 420, 50);
		sourceFiles.setBounds(0, 0, 420, 50);
		row3.add(sScrollPane);
		sourceFiles.setLayout(new BoxLayout(sourceFiles, BoxLayout.Y_AXIS));
		JButton btnAddSFile = new JButton("Add file");
		btnAddSFile.setBounds(10, 80, 80, 20);
		row3.add(btnAddSFile);
		btnAddSFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(btnAddHFile);
				sourceFiles.add(UICreator.createAddFileRow(fc.getSelectedFile()
						.getPath()));
				reSize();
			}
		});

		JPanel row4 = new JPanel();
		row4.setBounds(new Rectangle(10, 295, 440, 100));

		contentPane.add(row4);
		row4.setLayout(null);

		JLabel lblLibFiles = new JLabel("Lib files");
		lblLibFiles.setBounds(10, 0, 400, 20);
		row4.add(lblLibFiles);
		final JPanel libFiles = new JPanel();
		JScrollPane lScrollPane = new JScrollPane(libFiles);
		lScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		lScrollPane.setBounds(10, 25, 420, 50);
		libFiles.setBounds(0, 0, 420, 50);
		row4.add(lScrollPane);
		libFiles.setLayout(new BoxLayout(libFiles, BoxLayout.Y_AXIS));
		JButton btnAddLFile = new JButton("Add file");
		btnAddLFile.setBounds(10, 80, 80, 20);
		row4.add(btnAddLFile);
		btnAddLFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(btnAddHFile);
				libFiles.add(UICreator.createAddFileRow(fc.getSelectedFile()
						.getPath()));
				reSize();
			}
		});

		JPanel row5 = new JPanel();
		row5.setBounds(new Rectangle(10, 400, 440, 100));

		contentPane.add(row5);
		row5.setLayout(null);

		JLabel lblOtherFiles = new JLabel("Other files");
		lblOtherFiles.setBounds(10, 0, 400, 20);
		row5.add(lblOtherFiles);
		final JPanel otherFiles = new JPanel();
		JScrollPane oScrollPane = new JScrollPane(otherFiles);
		oScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		oScrollPane.setBounds(10, 25, 420, 50);
		otherFiles.setBounds(0, 0, 420, 50);
		row5.add(oScrollPane);
		otherFiles.setLayout(new BoxLayout(otherFiles, BoxLayout.Y_AXIS));
		JButton btnAddOFile = new JButton("Add file");
		btnAddOFile.setBounds(10, 80, 80, 20);

		row5.add(btnAddOFile);
		btnAddOFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(btnAddHFile);
				otherFiles.add(UICreator.createAddFileRow(fc.getSelectedFile()
						.getPath()));
				reSize();
			}
		});

		JPanel row6 = new JPanel();
		row6.setBounds(new Rectangle(10, 515, 440, 30));

		contentPane.add(row6);
		row6.setLayout(null);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(110, 0, 70, 20);
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				configUI config = new configUI();
				config.setVisible(true);
				thisFrame.setVisible(false);
			}
		});
		row6.add(btnBack);

		JButton btnCreate = new JButton("Create folder");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String projectPath = projectPathTF.getText();
				String projectName = projectNameTF.getText();
				boolean check = true;
				if ((projectPath == null) || 
						(projectPath.equals(
							constants.PROJECTUI_NOPROJECTPATH_ERROR)) || 
							(projectPath.length() == 0)) {
					projectPathTF
							.setText(constants.PROJECTUI_NOPROJECTPATH_ERROR);
					projectPathTF.setForeground(Color.RED);
					check = false;
				}
				if ((projectName == null) || (projectName.length() == 0) ||
						(projectName.equals(constants.PROJECTUI_EXISTINGPROJECTFOLDER_ERROR))) {
					projectNameTF
							.setText(constants.PROJECTUI_EMPTYPROJECTNAME_ERROR);
					projectNameTF.setForeground(Color.RED);
					check = false;
				} else if (projectName.equals(constants.PROJECTUI_EMPTYPROJECTNAME_ERROR)) {
					check = false;
				}

				if (!check) {
					return;
				} else {
					if (!FHandler.createProjectFolders(projectPath + projectName)) {
						projectNameTF
								.setText(constants.PROJECTUI_EXISTINGPROJECTFOLDER_ERROR);
						projectNameTF.setForeground(Color.RED);
						//check = false;
						return;
					}
				}
				mainUI frame = new mainUI(asmFileContent, projectPath
						+ projectName);
				frame.setVisible(true);
				thisFrame.dispose();
			}
		});
		btnCreate.setBounds(220, 0, 110, 20);
		row6.add(btnCreate);

		thisFrame.setResizable(false);
		thisFrame.setTitle(constants.APPNAME + " " + constants.VERSION);
	}

	protected void reSize() {// JPanel[] r, int pos, int offset) {
	//
	// for (int index = pos + 1; index < r.length; index++) {
	// r[index].setLocation((int)r[index].getX(), (int)r[index].getY() +
	// offset);
	// }
	// Rectangle rec = r[pos].getBounds();
	// rec.resize((int)rec.getWidth(), (int)rec.getHeight() + offset);
	// r[pos].setBounds(rec);
		Dimension d = thisFrame.getSize();
		// d.setSize((int)d.getWidth(), (int)d.getHeight() + 34);
		thisFrame.pack();
		thisFrame.setSize(d);
	}
}