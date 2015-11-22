/**
 * 
 */
package MicroController;

import java.util.Hashtable;

import util.constants;

/**
 * @author ThanhNhut
 *
 */
class RAM {
	// RAM organization
	private final int RAMSIZE;
	
    private Hashtable<String, Integer> varTable;      // variable, address
    private Hashtable<Integer, Integer> statusTable;  //  address, link 
	
	public RAM(int ramSize) {
		RAMSIZE = ramSize;
		varTable = new Hashtable<String, Integer>();
		statusTable = new Hashtable<Integer, Integer>(RAMSIZE);
		setRAMAddress();
	}
	
	public void setRAMAddress() {
		int index = 0;
		do {							
		    if (((index >= 32) && (index < 128))             //96
		    		|| ((index >= 160) && (index < 240))     //80
		    		|| ((index >= 272) && (index < 368))     //96
		    		|| ((index >= 400) && (index < 496))) {  //96
			    statusTable.put(index, 0);
		    } else {
		    	statusTable.put(index, 1);
		    }
		} while (index < 512);			
	}
	
	public void releaseVar(String varName) {
		int address = varTable.get(varName);
		int link = statusTable.get(address);
		link = link - 1;
		statusTable.put(address, link);
	}
	
	public void release(int address) {
		int link = statusTable.get(address);
		link = link - 1;
		statusTable.put(address, link);
	}
	
	public void releasePtr(String ptrName, int address) {
		if (address > 255) {
			releaseVar(ptrName + "1");
			releaseVar(ptrName + "2");
		} else {
			releaseVar(ptrName);
		}
		release(address);
	}
	
	public int getAvailableAddress() {
	    for (int index = 0; index < RAMSIZE; index++) {
	    	if (statusTable.get(index) == 0) {
	    		return index;
	    	}
	    }
	    return constants.NULL;
	}
	
	public boolean declareVar(String varName) {
		int address = getAvailableAddress(); 
		if (address == constants.NULL) {
			return false;				
		} else {
			varTable.put(varName, address);
			int link = statusTable.get(address) + 1;
			statusTable.put(address, link);
			return true;
		}
	}
	
	public void declarePtr(String ptrName) {		
		varTable.put(ptrName, constants.NULL);
	}
	
	public boolean pointPtr(String ptrName, int address) {
		if (address > 255) {
			varTable.remove(ptrName);
			if (!declareVar(ptrName + "1") || !declareVar(ptrName + "2")) {
				return false; 
			}
		} else {
			if (!declareVar(ptrName)) {
				return false;
			}
		}
		
		int link = statusTable.get(address) + 1;
		statusTable.put(address, link);
		return true;
	}
	
	public boolean declareArray(String arrayName, int arraySize) {
		int index = 0;
		int address = 32;
		int link = 0;
		do {
			for (index = 0; index < arraySize; index++) {
			    if (statusTable.get(address + index) != 0) {
			    	break;
			    }
			}
			if (index == arraySize) {
				for (index = 0; index < arraySize; index++) {
					varTable.put(arrayName + index, address + index);
					link = statusTable.get(address + index) + 1;
					statusTable.put(address + index, link);
				}
				return true;
			} else {
				address += index + 1;
			}
		} while (address < 512); 			
		return false;
	}
	
	public void releaseArray(String arrayName, int arraySize) {
		for (int index = 0; index < arraySize; index++) {
			releaseVar(arrayName + index);
		}
	}
	
	// declare long 
	public boolean declareLong(String varName) {
		return declareArray(varName, 4);			
	}
			
	public void release4Bytes(String varName) {
		releaseArray(varName, 4);
	}
	
	// declare float
	public boolean declareFloat(String varName) {
		return declareArray(varName, 4);
	}
	
	// declare pointer with length *ptr[length]
	public boolean pointArrayPointer(String ptrName, int arraySize) {
		boolean result = true;
		result &= declareArray("array" + ptrName, arraySize);
		int address = varTable.get("array" + ptrName + "0");
		if ((address + arraySize) > 255) {
			if (!declareVar(ptrName + "1") || !declareVar(ptrName + "2")) {
				result &= false; 
			}
		} else {
			if (!declareVar(ptrName)) {
				result &= false;
			}
		}

		return result;
	}
	
	public void releaseArrayPointer(String ptrName, int arraySize) {
	    releaseArray("array" + ptrName, arraySize);
	    int address = varTable.get("array" + ptrName + "0");
	    releasePtr(ptrName, address + arraySize);
	}
}	
