import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;


public class ReadFileWriteToConsole {

    private static final String inFile = "in.csv";
    private String[][] cells = null;

    public static void main(String args[]) throws IOException {
    	// (add code to implement the following)
    	// create a ReadFileWriteToConsole object
    	ReadFileWriteToConsole console = new ReadFileWriteToConsole();
    	// call method below to read the data from inFile
    	console.makeSheet();
    	// call method below to write the data to the console
    	console.writeSheet();
    	// Do not change the signature of this method.
    }
	
    public String getCell(int row, int col) {
	// return the value of the spreadsheet at the given row and column
	// Do not change the signature of this method.
    	if(this.getRowCount() < row || this.getColCount() < col || row < 0 || col < 0){
    		return null;
    	}
		return this.cells[row][col]; // <-- fix this to return a proper value
    }
	
    public int getRowCount() {
	// return the number of rows in the spreadsheet
	// Do not change the signature of this method.
		return this.cells.length; // <-- fix this to return a proper value
    }
    
    public int getColCount() {
	// return the number of columns in row
	// Do not change the signature of this method.
		return this.cells != null && this.cells.length > 1? this.cells[0].length : 0; // <-- fix this to return a proper value
    }
	  
    public void makeSheet() throws IOException {
	// read the data from inFile
	    InputStream stream = new FileInputStream(inFile);
	    CsvReader csvReader = new CsvReader(stream, StandardCharsets.UTF_8);
	    
	    int colCount = 0;
	    List<String[]> cells = new ArrayList<String[]>();
	    while(csvReader.readRecord()){
	    	String[] values = csvReader.getValues();
	    	colCount = Math.max(colCount, values.length);
	    	cells.add(values);
	    }
	    csvReader.close();
	    
	    this.cells = cells.toArray(new String[cells.size()][colCount]);
	    
	// Do not change the signature of this method.
    }	
	   
    public void writeSheet() {
	// format data to console
        for(int row=0; row < this.getRowCount(); row++){
        	for(int col=0; col < this.getColCount(); col++){
        		String cell = this.getCell(row, col);
        		if(cell != null){
        			System.out.print("["+cell+"]");
        		}
        	}
    		System.out.println();
        }
    // Do not change the signature of this method.
    }
    
}
