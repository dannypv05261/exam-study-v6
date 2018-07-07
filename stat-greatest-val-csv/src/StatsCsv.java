// StatsCsv.java -- add greatest values to spreadsheet
//
// This is NOT a working program. This is a "skeleton" that
// should be modified and extended to meet the specifications.

import java.io.IOException;
import java.util.Arrays;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


public class StatsCsv {
    
    private static final int MAXROW = 16;
    private static final int MAXCOL = 16;
    private int rowsUsed = 0;
    private int colsUsed = 0;
    private static final String inFile = "Data01.csv";
    private static final String outFile = "Data02.csv"; 
   
    public String[][] sheet = new String[MAXROW][MAXCOL];
    
    public static void main(String[] args) throws IOException {

        StatsCsv a = new StatsCsv();
        a.readSheet();
        // a.getRows() is the row count before adding greatest values
        Stats s = new Stats(a.getRows(), a.getCols(), a.getData());
        a.setRows(a.getRows() + 1); // because we added a row of greatest values
        a.writeSheet();
        s.writeData(a.getRows(), a.getCols(), a.getData());
    }
    
    public void writeSheet() throws IOException {
        CsvWriter writer = new CsvWriter(outFile);
        for(int i = 0; i < sheet.length; i++){
        	String[] cells = sheet[i];
        	for(int j = 0; j < cells.length; j++){
        		String cell = sheet[i][j];
        		if(cell != null){
        			writer.write(cell);
        		}
        	}
    		writer.endRecord();
        }
        writer.flush();
        writer.close();
    	//  Do not change the signature of this method.
    }

    public void readSheet() throws IOException {
        CsvReader reader = new CsvReader(inFile);
        
        int i = 0;
        int j = 0;
        
        if(reader.readHeaders()){
        	this.sheet[i] = reader.getHeaders();
        	j = reader.getHeaderCount();
        	i++;
        }
        
        for(; i < MAXROW && reader.readRecord(); i++){
        	this.sheet[i] = Arrays.copyOf(reader.getValues(), reader.getColumnCount());
        	j = Math.max(reader.getColumnCount(), j);
        }
        
        this.setRows(i);
        this.setCols(j);
        
        reader.close();
    	//  Do not change the signature of this method.
    }

    public int getRows() {
    	return rowsUsed;
    }
    
    public int setRows(int r) {
        rowsUsed = r;
        return rowsUsed;
    }

    public int getCols() {
    	return colsUsed;
    }
    
    public int setCols(int r) {
    	colsUsed = r;
        return colsUsed;
    }
    
    public String[][] getData() {
    	return sheet;
    }
}
