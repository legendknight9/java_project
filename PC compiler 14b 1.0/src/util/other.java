/**
 * 
 */
package util;

import java.util.ArrayList;

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
    
    public static String createFixedLengthString(int alignment, String data, int stringLength) {
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
}
