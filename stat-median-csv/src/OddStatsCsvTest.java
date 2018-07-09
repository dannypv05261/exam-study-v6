import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

public class OddStatsCsvTest extends TestCase {
	
	public final void testMain() {

        try {
            StatsCsv a = new StatsCsv();
            a.readSheet();
            a.sheet = Arrays.copyOf((a.sheet), a.getRows());
            a.setRows(a.getRows() - 1);
            
			// read in data set and compute medians			
			// test some of the fields
			assertTrue(
					"Failed to read data to sheet",
					a.sheet[0][0].equals("1st Name") && a.sheet[0][1].equals("2nd Name")
							&& Double.parseDouble(a.sheet[3][3]) == 0.0);
            // a.getRows() is the row count before adding medians
            Stats s = new Stats(a.getRows(), a.getCols(), a.getData());
            a.setRows(a.getRows() + 1); // because we added a row of medians
			
            a.writeSheet();
            
            // read Data02.csv
            String inFileName = "Data02.csv";
            BufferedReader input;
    	    try {
    	        // use buffering, reading one line at a time
    	        input =  new BufferedReader(new FileReader(inFileName));
    	    } catch (FileNotFoundException ex) {
    	    	System.out.println("Could not open " + inFileName);
    	    	fail("Failed to open Data02.csv");
    	    	return;
    	    }
    	    ArrayList<String> data = new ArrayList<String>();
    	    ArrayList<String> good = new ArrayList<String>();
    	    good.add("1st Name,2nd Name,Task 1,Task 2,Task 3,Task 4");
    	    good.add("Andrew,Andrews,10,0,0,0");
			good.add("MadDog,Miller,10,5,8,7");
			good.add("Kevin,Olson,10,0,1,7");
			good.add("\"\",Median,10,0,1,7");;
    	    try {
             	String inp;
    	        while ((inp = input.readLine()) != null) {
    	        	data.add(inp);
    	        }
    	    } catch (IOException e) {
    	    	e.printStackTrace();
    	    } finally {
    	    	try {
    	    		if (input != null) input.close();
    	    	} catch (IOException e) {
    	    		e.printStackTrace();
    	    	}
    	    }
    	    for (int i = 0; i < 5; i++) {
    	    	System.out.println(data.get(i) + "===" + good.get(i));
    	    	assertTrue("Incorrect output file", data.get(i).equals(good.get(i)));
    	    }

		} catch (IOException e) {
			fail("IOException encountered establishing in.csv");
		}
	}
}
