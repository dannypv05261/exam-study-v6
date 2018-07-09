import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.csvreader.*;

public class WriteHtml {
    public static final String inFileName = "input.csv";
    public static final String outFileName = "output.html";

    public static void main(String[] args){
    	WriteHtml object = new WriteHtml();
    	
    	try{
    		List<String[]> data = object.readFile(inFileName);
    		String result = object.transform(outFileName, data);
    		object.output(outFileName, result);
    	}catch(Exception e){
    		Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
    	}
    	//  Do not change the signature of this method.
    }
    
    public List<String[]> readFile(String inFileName) throws IOException{
    	List<String[]> list = new ArrayList<String[]>();
    
    	CsvReader reader = new CsvReader(inFileName);
        
    	while(reader.readRecord()){
    		list.add(reader.getValues());
    	}
         reader.close();
		return list;
    }
    
    public String transform(String title, List<String[]> data) throws IOException{
    	HtmlTableBuilder builder = new HtmlTableBuilder(title);
    	builder.startTable();
    	for(String[] cells: data){
    		builder.startRow();
    		for(String cell: cells){
    			builder.addCol(cell);
    		}
    		builder.endRow();
    	}
    	String result = builder
    			.endTable()
    			.output();
		return result;
    }
    
    
    public void output(String outFileName, String content) throws IOException{
    	FileWriter writer = new FileWriter(outFileName);
    	writer.write(content);
    	writer.close();
    }
}
