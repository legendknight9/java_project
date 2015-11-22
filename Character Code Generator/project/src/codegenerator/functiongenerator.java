/**
 * 
 */
package codegenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author ThanhNhut
 * 
 */

public class functiongenerator extends codegenerator {
	private File codeTableFile, outputFunctionFile;
	private String function;

	public functiongenerator(File codeTable, File outputFile) {
		codeTableFile = codeTable;
		outputFunctionFile = outputFile;
		readFromFile(codeTableFile);
	}

	/**
	 * Generate funtion to load data for character Array
	 * 
	 * @param startLine
	 *            included
	 * @param endLine
	 *            not included
	 * @param startCaseNumber
	 *            included
	 * @param full
	 *            add other code or just case routine
	 * @param segment
	 * 
	 * @param charArrayName
	 * @return
	 */
	private int generateFunction(int startLine, int endLine,
			int startCaseNumber, int full, int segment, String charArrayName) {
		int numberofFunction = 0, line = startLine;
		int caseNumber = startCaseNumber;
		String endOfLine = System.getProperty("line.separator");
		String delimiterFirst = "{";
		String delimiterLast = "}";
		String delimiterMiddle = ",";
		StringTokenizer spliter;
		// String stringPatternHead = endOfLine + "case %d :" + endOfLine;
		String stringPatternBody = "      %s[%d] = 0x%s;" + endOfLine;
		String functionBody = "";
		int length = 0;
		String body = "";
		String value = "";
		// char c_A[11] = {10, ..., ..., ...}; // ...

		do {
			body = "";

			// get value array
			value = codeTable.get(line);
			if (!value.contains("c_")) {
				line++;
				continue;
			}
			spliter = new StringTokenizer(value, delimiterFirst);
			spliter.nextToken();
			value = spliter.nextToken();
			spliter = new StringTokenizer(value, delimiterMiddle);

			length = spliter.countTokens();
			int valueArray[] = new int[length];
			valueArray[0] = Integer.parseInt(spliter.nextToken());
			for (int i = 1; i < length - 1; i++) {
				valueArray[i] = Integer.parseInt(
						(spliter.nextToken()).substring(3), 16);
			}
			value = spliter.nextToken();
			spliter = new StringTokenizer(value, delimiterLast);
			valueArray[length - 1] = Integer.parseInt(
					(spliter.nextToken()).substring(3), 16);

			// create body string
			// generate assign value commands
			for (int index = 0; index < length; index++) {
				String temp = Integer.toHexString(valueArray[index])
						.toUpperCase();
				if (temp.length() < 2) {
					temp = "0" + temp;
				}
				body += String.format(stringPatternBody, charArrayName, index,
						temp);
			}
			// add case and break, then append to functionBody
			functionBody += endOfLine + "    case " + caseNumber + " :"
					+ endOfLine + body + "      break;";
			caseNumber++;
			line++;
		} while (line < endLine);

		if (full != 0) {
			function = endOfLine + "void getCharData" + segment
					+ "(int charNumber){" + endOfLine
					+ "  switch(charNumber) {" + functionBody + endOfLine
					+ "  default :" + endOfLine + "    break;" + endOfLine
					+ "  }" + endOfLine + "}" + endOfLine;
		} else {
			function = functionBody;
		}

		// 1 line => 1 function (load data for 1 character)
		numberofFunction = line;
		return numberofFunction;
	}

	/**
	 * Generate funtion to get address for character Array
	 * 
	 * @param startLine
	 *            included
	 * @param endLine
	 *            not included
	 * @param startCaseNumber
	 *            included
	 * @param full
	 *            add other code or just case routine
	 * @param segment
	 * 
	 * @param charArrayName
	 * @return
	 */
	private int generateAddressFunction() {
		int resultCode = 0; // 0 : ok, != 0 : error
		long address = 0;
		int characterCodeOffset = 32;
		String endOfLine = System.getProperty("line.separator");
		String delimiterFirst = "{";
		String delimiterMiddle = ",";
		StringTokenizer spliter;
		function = "long getAddress(char characterCode);" + endOfLine
				+ "long getAddress(char characterCode) {" + endOfLine
				+ "  switch(characterCode) {" + endOfLine;
		String body = "";
		
		int line = 0;
		do {
			function += "    case " + (line + characterCodeOffset) + " :" + endOfLine + "      return " + address + ";" + endOfLine;

			// get and calculate next character address
			body = codeTable.get(line);
			spliter = new StringTokenizer(body, delimiterFirst);
			spliter.nextToken();
			body = spliter.nextToken();
			spliter = new StringTokenizer(body, delimiterMiddle);
			
			address += Integer.parseInt(spliter.nextToken()) + 1;			
			line++;
		} while (line < codeTable.size());
		
		function += "    default : " + endOfLine + "      return 0;" + endOfLine + "  }" + endOfLine + "}" + endOfLine; 
		
		return resultCode;
	}

	public int generate(int segment) {
		int result = 0; // 0 : ok, 1 : error
		int segmentLength = (int) Math.ceil(codeTable.size() / (float) segment);

		String endOfLine = System.getProperty("line.separator");

		PrintWriter out = null;
		try {
			outputFunctionFile.createNewFile();
			out = new PrintWriter(new BufferedWriter(new FileWriter(
					outputFunctionFile)));
		} catch (IOException e) {
			e.printStackTrace();
			result = 1;
		}

		// function prototypes
		for (int i = 1; i < segment + 1; i++) {
			out.write(endOfLine + "void getCharData" + i + "(int charNumber);");
		}

		if (segment > 1) {
			// functions
			for (int index = 1; index < segment; index++) {
				generateFunction((index - 1) * segmentLength, index
						* segmentLength, 0, 1, index, "charptr");
				out.write(function);
				out.flush();
				function = "";
			}
		}
		// last function
		generateFunction((segment - 1) * segmentLength, codeTable.size(), 0, 1,
				segment, "charptr");
		out.write(function);
		out.flush();
		function = "";

		// character display dataArray memory address
		generateAddressFunction();
		out.write(function);
		out.close();

		return result;
	}

}