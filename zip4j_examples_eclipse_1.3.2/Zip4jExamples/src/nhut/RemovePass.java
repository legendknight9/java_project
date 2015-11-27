/*
* Copyright 2015 Luu Thanh Nhut  
* 
* Licensed under the Apache License, Version 2.0 (the "License"); 
* you may not use this file except in compliance with the License. 
* You may obtain a copy of the License at 
* 
* http://www.apache.org/licenses/LICENSE-2.0 
* 
* Unless required by applicable law or agreed to in writing, 
* software distributed under the License is distributed on an "AS IS" BASIS, 
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
* See the License for the specific language governing permissions and 
* limitations under the License. 
*/

package nhut;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * Demonstrates extraction of a single file from the zip file
 * 
 * @author Thanh Nhut
 */

public class RemovePass implements Runnable {
	static String filePath, outputPath, param;
	static int minPassLength = 1;
	static int maxPassLength = 10;
	static int startChar = 32;
	static int endChar = 126;
	static int numberOfThread;
	static int numberOfActiveThread;
	static int numberOfAvailableThread = Runtime.getRuntime().availableProcessors();	
	static int numberOfChar;
	
	private int length;
	private ZipFile zipFile;
	public char pass[];
	
	public RemovePass(ZipFile zipFile, char pass[]) {
		this.zipFile = zipFile;
		this.length = pass.length;
		this.pass = pass;		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		args = new String[10];
//		args[0] = "test";
//		args[1] = "web";
//		args[2] = "5";
//		args[3] = "7";
//		args[4] = "32";
//		args[5] = "126";
//		args[6] = "-v";
//		args[7] = "3";
		
		if (args.length < 4) {
			showHelp();
			System.exit(0);
		} else {
			filePath = args[0];
			outputPath = args[1];
			minPassLength = Integer.parseInt(args[2]);
			maxPassLength = Integer.parseInt(args[3]);
			param = "";
			if (args.length >= 5) startChar = Integer.parseInt(args[4]);
			if (args.length >= 6) endChar = Integer.parseInt(args[5]);
			if (args.length >= 7) param = args[6];
			if (args.length >= 8) numberOfAvailableThread = Integer.parseInt(args[7]);
			numberOfChar = endChar - startChar + 1;			
		}		
		
		File saveFile = new File("log-current.txt");
		List<String> savePass = new ArrayList<String>();
		if (saveFile.exists() && saveFile.isFile()) {			
			try {				
				Scanner scanFile = new Scanner(saveFile);
				while (scanFile.hasNextLine()) {
					String passString = scanFile.nextLine();
					savePass.add(passString.substring(24, passString.indexOf(32, 25)));
					String temp = passString.substring(passString.indexOf(32, 27));
					savePass.add(temp.substring(2, temp.length() - 1));
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.err.println(saveFile.getName() + " is not Found");
			}
		}
		
		RemovePass rpArray[] = new RemovePass[maxPassLength - minPassLength + 1];
		int index = 0;
		numberOfThread = 0;	
		numberOfActiveThread = 0;
		for (int tryingLength = minPassLength; tryingLength <= maxPassLength; tryingLength++) {
			try {
				// Initiate ZipFile object with the path/name of the zip file.
				ZipFile zf = new ZipFile(filePath + "-" + tryingLength + ".zip");			
				char pass[] = initializePass(tryingLength, savePass);
		    	RemovePass rp = new RemovePass(zf, pass);	
		    	rpArray[index++] = rp;
			    (new Thread(rp)).start();				
			} catch (ZipException e) {
				// TODO Auto-generated atch block
				e.printStackTrace();
			}
		}
		
		while(true) {
			Scanner key = new Scanner(System.in);
			String input = key.nextLine().trim();
			if (input.equalsIgnoreCase("Stop")) {
				try {
					PrintWriter pw = new PrintWriter(new FileWriter(new File("log-current.txt"), false), true);
					for (int i = 0; i < rpArray.length; i++) {
						pw.println("Last pass of passlength " + rpArray[i].pass.length 
								+ " is \"" + String.copyValueOf(rpArray[i].pass) + "\"");
						
					}
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
			} else if (input.equalsIgnoreCase("Save")) {
				try {
					PrintWriter pw = new PrintWriter(new FileWriter(new File("log-current.txt"), false), true);
					for (int i = 0; i < rpArray.length; i++) {
						pw.println("Last pass of passlength " + rpArray[i].pass.length 
								+ " is \"" + String.copyValueOf(rpArray[i].pass) + "\"");
						
					}
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (input.equalsIgnoreCase("progress")) {
				for (int i = 0; i < rpArray.length; i++) {
					System.out.println("Passlength " + rpArray[i].pass.length + " : progress " + rpArray[i].calculatePercent());
					System.out.println("Current Pass : " + String.copyValueOf(rpArray[i].pass));
				}
			} else if (input.equalsIgnoreCase("exit")) {
				System.exit(0);
			}
		}
	}
	
	private static char[] initializePass(int tryingLength, List<String> args) {
    	// initialize pass
		char p[] = new char[tryingLength];
		Math.sqrt(5);
    	for (int index = 0; index < tryingLength; index++) {
    		p[index] = (char)startChar;
    	}
		try {
			int argsLength = args.size();
			if (argsLength > 1) {				
				for (int index = 0; index < argsLength; index += 2) {
					if (tryingLength == Integer.parseInt(args.get(index))) {
						return args.get(index+1).toCharArray();
					}
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("No initial value for pass length " + tryingLength);		
		}
		return p;
	}
	
	private boolean tryPass(char pass[], ZipFile zipFile) {
		String passWord  = String.copyValueOf(pass);
        if (passWord == null || passWord.trim().length() <= 0) {
        	return false;
        }
		try {
			// Check to see if the zip file is password protected 
			if (zipFile.isEncrypted()) {
				zipFile.setPassword(passWord);
			} else {
				System.out.println("File is not password-protected.");
				zipFile.extractAll(outputPath);
				System.exit(0);
			}
			zipFile.extractAll(outputPath);
			System.out.println("Extracted all! Password is \"" + passWord + "\".");
			PrintStream ps = new PrintStream(new File("PASSWORD.txt"));
			ps.println("Extracted all! Password is \"" + passWord + "\".");
			ps.close();
			System.exit(0);
		} catch (ZipException e) {
			if (e.getMessage().endsWith("- Wrong Password?")) {
				if (param.equals("-v")) {
					System.out.println(passWord + " is not password!");
				}
				return false;
			} else if (e.getMessage().endsWith("file does not exist")) {
				System.out.println("File " + filePath + " not found!");
				System.exit(-1);
				return false;
			} else {			
				e.printStackTrace();
				return false;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		return false;
	}	
	
	private void tryPassWithLength(ZipFile zipFile, int length, char pass[]) {
		switch(length) {
			case 1 :			
				for (; pass[pass.length-1] <= endChar; pass[pass.length-1]++) {
					if (tryPass(pass, zipFile)) {
						System.exit(0);
					}
				}	
				pass[pass.length-1] = (char)startChar;
				break;
			default :
				int index = pass.length - length;
				for (; (int)pass[index] <= endChar; pass[index]++) {
					tryPassWithLength(zipFile, length - 1, pass);
				}
				pass[index] = (char)startChar;
		}
	}
	
	public String calculatePercent() {
		// TODO Auto-generated method stub
		int percent = (100 * (((int)this.pass[0] - startChar))/(endChar - startChar + 1));
		percent = (percent > 100) ? 100 : percent;
		return (String)(percent + "%");
	}

	private static void showHelp() {
		System.out.println("Usage : RemovePass path_file output_path pass_minlength pass_maxlength startChar endChar param maxthread length start_password length start_password ...");
		System.out.println("Param : \n-v : show trying passwords.");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int number;
		synchronized (this) {
			// let short length pass loop run first
			try {
				wait(calculateWaitTime());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			numberOfThread++;
		    numberOfActiveThread++;
			number = numberOfThread;
		    while (numberOfActiveThread > numberOfAvailableThread) {
		    	try {
		    		System.out.println("Thread #" + number + " is pending.");
				    numberOfActiveThread--;
		    		wait(((maxPassLength + minPassLength) / 2 ) * 60000);
				    numberOfActiveThread++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    System.out.println("Start thread #" + number + " length of pass " + length + " : progress " + calculatePercent());
		}
		tryPassWithLength(zipFile, length, pass);
		synchronized (this) {
			numberOfActiveThread--;
			System.out.println("End thread #" + number + " length of pass " + length);
		}
	}
	
	private int calculateWaitTime() {
		switch(this.length - minPassLength + 1) {
		case 1 : 
		case 2 : return 100;
		case 3 : return 1000;
		case 4 : return 2000;
		default : return 10000;
		}
	}
}
