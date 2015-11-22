/**
 * 
 */
package util;

import java.io.File;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author ThanhNhut
 * 
 */
public class other {
	public static String[] toArray(ArrayList<String> data) {
		String temp[] = new String[data.size()];
		int index = 0;
		for (String line : data) {
			temp[index] = line;
			index++;
		}
		return temp;
	}

	public static final int CENTER = 2;
	public static final int LEFT = 1;
	public static final int RIGHT = 3;

	public static String createFixedLengthString(int alignment, String data,
			int stringLength) {
		String result = "";
		StringBuilder sb = new StringBuilder();
		if (alignment == CENTER) {
			sb.append(data);
		} else if (alignment == LEFT) {
			sb.append(data);
			int numberOfSpaces = stringLength - data.length();
			do {
				sb.append(" ");
				numberOfSpaces--;
			} while (numberOfSpaces > 0);
		} else {
			int numberOfSpaces = stringLength - data.length();
			do {
				sb.append(" ");
				numberOfSpaces--;
			} while (numberOfSpaces > 0);
			sb.append(data);
		}
		result = sb.toString();
		return result;
	}

	public static String randomString(int Length) {
		StringBuilder sb = new StringBuilder();
		int loop = Length;
		char ranChar;
		do {
			ranChar = (char) ((Math.random() + 2.6) * 25);
			sb.append(ranChar);
			loop--;
		} while (loop > 0);
		return sb.toString();
	}

	public static String getPICNumber(String processor) {
		if (processor.startsWith("PIC")) {
			return processor.substring(3);
		} else if (processor.startsWith("dsPIC")) {
			return processor.substring(5);
		} else {
			return processor;
		}
	}

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
			fileSize = Integer.toString((int) temp) + " Bytes";
			break;
		case 1:
			fileSize = Integer.toString((int) temp) + " KB";
			break;
		case 2:
			fileSize = Integer.toString((int) temp) + " MB";
			break;
		case 3:
			fileSize = Integer.toString((int) temp) + " GB";
			break;
		case 4:
			fileSize = Integer.toString((int) temp) + " TB";
			break;
		default:
			fileSize = "unknown";
			break;
		}
		return fileSize;
	}

	public static DefaultMutableTreeNode getFolderStructure(File folder) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(
				folder.getName());
		if (folder.isFile()) {
			return root;
		} else {
			DefaultMutableTreeNode rootChild = null;
			File content[] = folder.listFiles();
			if ((content == null) || (content.length == 0)) {
				return root;
			} else {
				for (int index = 0; index < content.length; index++) {				
					rootChild = getFolderStructure(content[index]);	
					root.add(rootChild);
				}
			}
		}
		return root;
	}
}
