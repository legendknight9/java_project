/**
 * 
 */
package gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import util.FileHandler;
import util.other;

/**
 * @author ThanhNhut
 *
 */
public class FileTab extends JScrollPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5347020511249662898L;

	private File file;
	private long currentPos;
	private String fileName;
	private long fileSize;
	
	private JTextArea fileDisplayer;
	
	public FileTab(long fileReadPos, File f, JTextArea textArea) {
		super(textArea);
		fileName = f.getName();
		fileSize = f.length();
		fileDisplayer = textArea;
		currentPos = fileReadPos;		
		file = f;		
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileSize() {
		return other.getStringFileSize(fileSize);
	}
	
	public void saveFile() {
		File tempFile = new File(file.getPath() + "t");
		FileHandler.createNewFile(fileDisplayer.getText(), file, tempFile, currentPos, true);
	}
	
	public void saveAsFile() {
		JFileChooser fc = new JFileChooser();
		fc.showSaveDialog(this);	
		FileHandler.createNewFile(fileDisplayer.getText(), file, fc.getSelectedFile(), currentPos, false);
	}
	
	public void close() {
		fileName = null;
		fileSize = 0;
		fileDisplayer = null;
		file = null;	
		currentPos = 0;
	}

	public long getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(long currentPos) {
		this.currentPos = currentPos;
	}
	
	public String nextString() {
		byte data[] = FileHandler.getNextData(currentPos, file);
		
		String fileContent = "";
		if ((data != null) && (data.length > 0)) {		
			StringBuilder fileContentBuilder = new StringBuilder();
			for (int index = 0; index < data.length; index++) {
			    fileContentBuilder.append((char)data[index]);
			}
			fileContent = fileContentBuilder.toString();
			currentPos += data.length;
		}
		
		return fileContent;
	}
	
	public void setText(String text) {
		fileDisplayer.setText(text);
	}
}
