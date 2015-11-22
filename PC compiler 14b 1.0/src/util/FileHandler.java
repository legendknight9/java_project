/**
 * 
 */
package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author ThanhNhut
 * 
 */
public class FileHandler {

	public static String getStringFileSize(long size) {
		long temp = size;
		int unit = 0;
		String fileSize = "";
		do {
			temp = temp / 1024;
			unit++;
		} while (temp > 1024);

		switch (unit) {
		case 0:
			fileSize = Integer.toString((int) temp) + "Bytes";
			break;
		case 1:
			fileSize = Integer.toString((int) temp) + "KB";
			break;
		case 2:
			fileSize = Integer.toString((int) temp) + "MB";
			break;
		case 3:
			fileSize = Integer.toString((int) temp) + "GB";
			break;
		case 4:
			fileSize = Integer.toString((int) temp) + "TB";
			break;
		default:
			fileSize = "unknown";
			break;
		}
		return fileSize;
	}

	public static boolean createNewFile(String contentString, File oldfile,
			File newfile, long currentPos, boolean save) {
		boolean result = true;
		long newFilePos = 0;
		long oldFilePos = currentPos;
		byte[] data;
		if (oldfile.length() > constants.BYTEBUFFER) {
			// write contentString newfile
			data = contentString.getBytes();
			newFilePos += writeDataByte(data, newfile, newFilePos);
			// read oldfile (buffer size pos)
			data = getNextData(oldFilePos, oldfile);
			while(data != null) {
				oldFilePos += data.length;
				// write to new file
				newFilePos += writeDataByte(data, newfile, newFilePos);
				// read oldfile (buffer size pos)
				data = getNextData(oldFilePos, oldfile);
			}
		} else {
			// write ContentString newfile
			writeDataByte(contentString.getBytes(), newfile, 0);
		}

	    if (save) {
			// delete oldfile		 
			String filePath = oldfile.getPath();
			result = oldfile.delete();	
			// rename newfile to old file
		    result = newfile.renameTo(new File(filePath));
	    }
		return result;
	}

	public static byte[] getNextData(long currentPos, File file) {
		FileChannel fch = null;
		int readBytes = 0;
		ByteBuffer buffer = ByteBuffer.allocate(constants.BYTEBUFFER);
		byte data[] = null;
		
		if (currentPos >= (file.length() - 1)) {
			return data;
		}
		
		try {
			fch = new RandomAccessFile(file, "rw").getChannel();
			fch.position(currentPos);
			readBytes = fch.read(buffer);
			fch.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (readBytes > 0) {
			data = new byte[readBytes];
			for (int index = 0; index < readBytes; index++) {
				data[index] = buffer.get(index);
			}
		}

		return data;
	}

	@SuppressWarnings("resource")
	public static int writeDataByte(byte[] data, File file, long filePos) {
		FileChannel fch;
		int writtenBytes = 0;
		try {
			fch = new RandomAccessFile(file, "rw").getChannel();
			ByteBuffer buffer = ByteBuffer.wrap(data);
			writtenBytes = fch.write(buffer, filePos);
			fch.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writtenBytes;
	}
	
	public static String[] readFile(File input) {
		Scanner in = null;

		try {
			in = new Scanner(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> Inc = new ArrayList<String>();
		int index = 0;
		do {
			Inc.add(in.nextLine());
			index++;
		} while((index < 806) && (in.hasNextLine()));
		
		in.close();
		
		return other.toArray(Inc);
	}
	
	//  asm [0] : intro
	//  asm [1] : config
	//  asm [2] : define mem
	//  asm [3] : define constant
	//  asm [4] : main
	//  asm [5] : sub program
	public static boolean outputAsm(String asm[], File output) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(
					output)));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		for (String code : asm) {
			out.write(code);
		}
		out.flush();
		out.close();
		return true;
	}
}