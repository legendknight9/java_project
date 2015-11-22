import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class asmLoadCharCodeGen {

	private JFrame frame;
	private JTextField textField;
	private ArrayList<String> fileContent;
	private File outputFile;
	private final String NOFILE = "No file selected";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					asmLoadCharCodeGen window = new asmLoadCharCodeGen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public asmLoadCharCodeGen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 370, 120);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(100, 100, 100, 100));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnOpenFile = new JButton("Open File");
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser openFile = new JFileChooser();
				openFile.showOpenDialog(frame);
				File input = openFile.getSelectedFile();
				inputReader readFile = null;
				if (input != null) {
					try {
						readFile = new inputReader(input.getPath());						
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}	
					fileContent = readFile.getFileContent();
				}				
			}
		});
		btnOpenFile.setBounds(10, 11, 89, 23);
		panel.add(btnOpenFile);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filePath = textField.getText().toString();
				if (filePath != null && filePath.length() != 0 && !filePath.equals(NOFILE) ) {
					outputWriter output = new outputWriter(filePath, fileContent);
					output.writeFile();
				}
			}
		});
		btnGenerate.setBounds(251, 11, 89, 23);
		panel.add(btnGenerate);
		
		JLabel lblOutputPath = new JLabel("Output Path");
		lblOutputPath.setBounds(10, 48, 75, 14);
		panel.add(lblOutputPath);
		
		textField = new JTextField();
		textField.setBounds(98, 45, 240, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnSelectPath = new JButton("Select Path");
		btnSelectPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser saveFile = new JFileChooser();
				saveFile.showSaveDialog(frame);
				outputFile = saveFile.getSelectedFile();
				if (outputFile != null) {
					textField.setText(outputFile.getPath());
				} else {
					textField.setText(NOFILE);
				}
			}
		});
		btnSelectPath.setBounds(128, 11, 89, 23);
		panel.add(btnSelectPath);		
	}
}
