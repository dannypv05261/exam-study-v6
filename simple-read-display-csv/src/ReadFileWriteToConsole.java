import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import com.csvreader.CsvReader;

public class ReadFileWriteToConsole {
	
	private static final int MAXROW = 3;
	private static final int MAXCOL = 3;
    private static final String inFile = "in.csv";
    public String[][] sheet = new String[MAXROW][MAXCOL];
	   
    public static void main(String args[]) throws IOException {
    	// create a ReadWriteToConsole object
    	//  Do not change the signature of this method.
    	ReadFileWriteToConsole console = new ReadFileWriteToConsole();
    	// invoke readSheet()
    	console.readSheet();
    	// invoke writeSheet()
    	console.writeSheet();
    }	
	   
    public void readSheet() throws IOException {
        InputStream stream = new FileInputStream(inFile);
        CsvReader csvReader = new CsvReader(stream, StandardCharsets.UTF_8);
        
        int row = 0;
        while(csvReader.readRecord() && row < MAXROW){
            String[] values = csvReader.getValues();
            for(int col= 0; col < values.length; col++){
            	this.sheet[row][col] = values[col];
            }
            row++;
        }
        csvReader.close();
    	//  Do not change the signature of this method.
	}
	   
	public void writeSheet(){
        for(int row=0; row < MAXROW; row++){
            for(int col=0; col < MAXCOL; col++){
                String cell = this.sheet[row][col];
                if(cell != null){
                    System.out.print("["+cell+"]");
                }
            }
            System.out.println();
        }
    	//  Do not change the signature of this method.
	}
}
