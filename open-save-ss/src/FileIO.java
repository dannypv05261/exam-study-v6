// FileIOBase.java -- template for FileIO.java.
//
// this file should be modified to implement
// open, save, and save as... commands for SpreadSheet.java
// 
// Do not modify the signatures of these methods.


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class FileIO {
	
	private final static char CSV_DELIMITER = ',';
	
    public static boolean open(SpreadSheet ss, File file) {
    	System.out.println("Open " + file);
		try {
			
			if(!file.exists() || !file.isFile()) {
				Logger.getGlobal().log(Level.SEVERE, "The file does not exist.");
				return false;
			}
			
	    	InputStream stream = new FileInputStream(file);
	    	CsvReader reader = new CsvReader(stream, CSV_DELIMITER, StandardCharsets.UTF_8);
	    	for(int i = 0; reader.readRecord(); i++){
	    		String[] cells = reader.getValues();
	    		for(int j = 0; j < cells.length; j++){
	    			ss.setCell(i, j, cells[j]);
	    		}
	    	}
	    	ss.evaluate();
	    	return true;
		} catch (FileNotFoundException e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
	    	return false;
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
	    	return false;
		}
    }
	
    public static boolean saveAs(SpreadSheet ss, File file) {
		System.out.println("SaveAs " + file);
		try {
	    	OutputStream stream = new FileOutputStream(file);
	    	CsvWriter writer = new CsvWriter(stream, CSV_DELIMITER, StandardCharsets.UTF_8);
	    	for(int i = 0; i < ss.maxRows; i++){
	    		int lastNonEmptyIndex = -1;
	    		String[] outputRecord = new String[ss.maxCols];
	    		for(int j = 0; j < ss.maxCols; j++){
	    			String cell = ss.getCellFormula(i, j);
	    			if(cell != null){
	    				lastNonEmptyIndex = j;
	    			}
	    			outputRecord[j] = cell;
	    		}
	    		
	    		if(lastNonEmptyIndex >= 0){
		    		outputRecord = Arrays.copyOf(outputRecord, lastNonEmptyIndex + 1);
					writer.writeRecord(outputRecord);
	    		} else {
	    			writer.endRecord();
	    		}
	    	}
	    	writer.flush();
	    	writer.close();
	    	return file.exists();
		} catch (FileNotFoundException e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
	    	return false;
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
	    	return false;
		}
    }
    
}
