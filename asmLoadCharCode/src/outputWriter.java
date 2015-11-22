import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 */

/**
 * @author ThanhNhut
 *
 */
public class outputWriter {
	private File output;
	private String processedData;
	private final String LS = System.getProperty("line.separator");	
	private final String BLOCKHEADER = LS + ";" + LS + "BANKSEL DARRAY" + LS;
	private final String format = "MOVLW %s" + LS + "MOVWF DARRAY + %s" + LS;
	private final String BLOCKFOOTER = ";" + LS + "BSF PCLATH, 3" + LS 
			+ "BSF PCLATH, 4" + LS 
			+ "CALL mEEPROM24C64_writeDataTable_writeNextPage" + LS;
	private final String FUNCTIONHEADER = "mEEPROM24C64_writeDataTable"
			 						 + LS + "BANKSEL EEPROM24C64_FADDBUF"
			 						 + LS + "CLRF EEPROM24C64_FADDBUF"
			 						 + LS + "CLRF EEPROM24C64_SADDBUF"
			 						 + LS + "MOVLW DARRAY"
			 						 + LS + "MOVWF FSR";
	private final String FUNCTIONFOOTER = "RETURN;" + LS;
	
	public outputWriter(String filePath, ArrayList<String> linesOfFile) {
		output = new File(filePath);
		processData(linesOfFile);				
	}
	
	private void processData(ArrayList<String> linesOfFile) {			
		int dataArrayOrder = 0;
		StringBuilder genCharBlocks = null;
		StringBuilder genBlock = new StringBuilder();
		for (String line : linesOfFile) {
			int posOpen = line.indexOf("= {");
			int posClose = line.indexOf("};");
			if (posOpen == -1 || posClose == -1) {
				break;
			}
			String title = line.substring(0, posOpen) + " " + line.substring(posClose + 2);
			String value = line.substring(posOpen + 3, posClose);
			StringTokenizer getValue = new StringTokenizer(value, ", ");
			if (dataArrayOrder == 0) {
				genCharBlocks = new StringBuilder(BLOCKHEADER + title + LS);				
			} else {
				genCharBlocks.append(title + LS);
			}
						
			while (getValue.hasMoreTokens()) {
				String data = String.format(format, 
						convertToHex(getValue.nextToken()), 
						convertToHex(Integer.toString(dataArrayOrder)));
				genCharBlocks.append(data);
				dataArrayOrder++;
				if (dataArrayOrder > 31) {
				    genBlock.append(genCharBlocks.toString());
				    genBlock.append(BLOCKFOOTER);
				    genCharBlocks = new StringBuilder(BLOCKHEADER);	
				    dataArrayOrder = 0;
				}	
			}
			
			if (dataArrayOrder < 32) {
				genCharBlocks.append(LS);	
			} else {
			    genBlock.append(genCharBlocks.toString());
			    genBlock.append(BLOCKFOOTER);
			    dataArrayOrder = 0;
			}		
		}
		if (dataArrayOrder > 0 && dataArrayOrder < 32) {
			while (dataArrayOrder < 32) {
				String data = String.format(format, "0x00", 
						convertToHex(Integer.toString(dataArrayOrder)));
				genCharBlocks.append(data);
				dataArrayOrder++;
			}
		    genBlock.append(genCharBlocks.toString());
		    genBlock.append(BLOCKFOOTER);
		}
		genBlock.insert(0, FUNCTIONHEADER);
		genBlock.append(FUNCTIONFOOTER);
		processedData = genBlock.toString();
	}
	
	public boolean writeFile() {
		boolean result = true;
		try {
		FileWriter fileWriter = new FileWriter(output);
			fileWriter.write(processedData);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			result = false;
			e.printStackTrace();
		}				
		return result;
	}
	
	private String convertToHex(String number) {
		String hex = null;
		if (number.contains("0x")) {
			return number;
		} 
		hex = Integer.toHexString(Integer.parseInt(number)).toUpperCase();
		if (hex.length() < 2) {
			hex = "0" + hex;
		}
		hex = "0x" + hex;
		return hex;
	}
	
}
