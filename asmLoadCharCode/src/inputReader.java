import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author ThanhNhut
 *
 */
public class inputReader {
	private File input;
	private ArrayList<String> linesOfFile;
	
	public inputReader(String filePath) throws FileNotFoundException {
		input = new File(filePath);
		readFileInToLines();
	}
	
	private void readFileInToLines() throws FileNotFoundException {
		Scanner fileScanner = new Scanner(new FileInputStream(input));
		linesOfFile = new ArrayList<String>();
		while(fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			if (line.startsWith(";const")) {
				linesOfFile.add(line);	
			}			
		}
		fileScanner.close();		
	}
	
	public ArrayList<String> getFileContent() {
		return linesOfFile;		
	}
}
