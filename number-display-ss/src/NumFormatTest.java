// NumberDisplayTest -- a simple, incomplete test of 
// SpreadSheet modified to implement the number-display task

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Formatter;

import junit.framework.Assert;
import junit.framework.TestCase;

public class NumFormatTest extends TestCase {

	private SpreadSheet ss = new SpreadSheet("test");
	
    /**
     * Test method for {@link SpreadSheet#evaluate()}.
     */
    public final void testEvaluate() {
       	double val4 = 0.11123d;
    	assertTrue((val4 % 1) > 0);
    	
       	double val5 = 11123d;
    	assertTrue((val5 % 1) == 0);
    	
       	double val6 = 11123.222222d;
    	assertTrue((val6 % 1) > 0);
    	
    	String val = "123456789"; //3
    	assertEquals("1.23e+08", ss.formatText(val));
    	
    	String val2 = "0.000000000000000000000000000000000000004"; //3
    	assertEquals("4.00e-39", ss.formatText(val2));
    	
    	String val3 = "12345.5678901234"; //7
    	assertEquals("12345.57", ss.formatText(val3));
    	
       	String val7 = "1.234567890"; //7
    	assertEquals("1.234568", ss.formatText(val7));
    
    	
    }
}
