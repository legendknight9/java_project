package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import util.RelativeLayout;
import util.UICreator;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class projectUI extends JFrame {

	private JPanel contentPane;
	private JTextField projectPathTF;

	/**
	 * Create the frame.
	 */
	public projectUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{574, 0};
		gbl_contentPane.rowHeights = new int[]{40, 160, 90, 90, 90, 40, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel row1 = new JPanel();
		GridBagConstraints gbc_row1 = new GridBagConstraints();
		gbc_row1.fill = GridBagConstraints.BOTH;
		gbc_row1.insets = new Insets(0, 0, 5, 0);
		gbc_row1.gridx = 0;
		gbc_row1.gridy = 0;
		contentPane.add(row1, gbc_row1);
		row1.setLayout(new RelativeLayout(RelativeLayout.X_AXIS, 10));
		
		JLabel lblProjectPath = new JLabel("Project path");
		row1.add(lblProjectPath, new Float(1));	
		projectPathTF = new JTextField();
		row1.add(projectPathTF, new Float(3));
		projectPathTF.setColumns(10);	
		final JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(btnBrowse);
				projectPathTF.setText(fc.getSelectedFile().getPath());
			}
		});
		row1.add(btnBrowse, new Float(1));
		
		JPanel row2 = new JPanel();
		GridBagConstraints gbc_row2 = new GridBagConstraints();
		gbc_row2.fill = GridBagConstraints.BOTH;
		gbc_row2.insets = new Insets(0, 0, 5, 0);
		gbc_row2.gridx = 0;
		gbc_row2.gridy = 1;
		contentPane.add(row2, gbc_row2);
		row2.setLayout(new RelativeLayout(RelativeLayout.Y_AXIS, 10));
		
		JLabel lblHeaderFiles = new JLabel("Header files");
		row2.add(lblHeaderFiles);		
		final JPanel headerFiles = new JPanel();
		row2.add(headerFiles);
		headerFiles.setLayout(new RelativeLayout(RelativeLayout.Y_AXIS, 10));		
		final JButton btnAddHFile = new JButton("Add file");
		btnAddHFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(btnAddHFile);
				headerFiles.add(UICreator.createAddFileRow(fc.getSelectedFile().getPath()));
				((JFrame)contentPane.getParent().getParent().getParent()).pack();
			}
		});
		row2.add(btnAddHFile);
		headerFiles.setLayout(new RelativeLayout(RelativeLayout.Y_AXIS, 10));			
		
		JPanel row3 = new JPanel();
		GridBagConstraints gbc_row3 = new GridBagConstraints();
		gbc_row3.fill = GridBagConstraints.BOTH;
		gbc_row3.insets = new Insets(0, 0, 5, 0);
		gbc_row3.gridx = 0;
		gbc_row3.gridy = 2;
		contentPane.add(row3, gbc_row3);
		row3.setLayout(new RelativeLayout(RelativeLayout.Y_AXIS, 10));
		
		JLabel lblSourceFiles = new JLabel("Source files");
		row3.add(lblSourceFiles);		
		JPanel sourceFiles = new JPanel();
		row3.add(sourceFiles);
		JButton btnAddSFile = new JButton("Add file");
		row3.add(btnAddSFile);
		
		JPanel row4 = new JPanel();
		GridBagConstraints gbc_row4 = new GridBagConstraints();
		gbc_row4.fill = GridBagConstraints.BOTH;
		gbc_row4.insets = new Insets(0, 0, 5, 0);
		gbc_row4.gridx = 0;
		gbc_row4.gridy = 3;
		contentPane.add(row4, gbc_row4);
		row4.setLayout(new RelativeLayout(RelativeLayout.Y_AXIS, 10));
		
		JLabel lblLibFiles = new JLabel("Lib files");
		row4.add(lblLibFiles);		
		JPanel libFiles = new JPanel();
		row4.add(libFiles);
		JButton btnAddLFile = new JButton("Add file");
		row4.add(btnAddLFile);
		
		JPanel row5 = new JPanel();
		GridBagConstraints gbc_row5 = new GridBagConstraints();
		gbc_row5.fill = GridBagConstraints.BOTH;
		gbc_row5.insets = new Insets(0, 0, 5, 0);
		gbc_row5.gridx = 0;
		gbc_row5.gridy = 4;
		contentPane.add(row5, gbc_row5);
		row5.setLayout(new RelativeLayout(RelativeLayout.Y_AXIS, 10));
		
		JLabel lblOtherFiles = new JLabel("Other files");
		row5.add(lblOtherFiles);		
		JPanel otherFiles = new JPanel();
		row5.add(otherFiles);
		JButton btnAddOFile = new JButton("Add file");
		row5.add(btnAddOFile);
		
		JPanel row6 = new JPanel();
		GridBagConstraints gbc_row6 = new GridBagConstraints();
		gbc_row6.fill = GridBagConstraints.BOTH;
		gbc_row6.gridx = 0;
		gbc_row6.gridy = 5;
		contentPane.add(row6, gbc_row6);
		row6.setLayout(new RelativeLayout(RelativeLayout.X_AXIS, 50));	
		row6.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		JButton btnBack = new JButton("Back");
		row6.add(btnBack, new Float(1));
		JButton btnCreate = new JButton("Create folder");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String config[] = {"", "", "", ""};
				mainUI frame = new mainUI(config);
				frame.setVisible(true);
				((JFrame)contentPane.getParent().getParent().getParent()).setVisible(false);
			}
		});
		row6.add(btnCreate, new Float(1));	
	}	 
}
