// NummbersAsExpressionsTest -- a simple, incomplete test of SpreadSheet,
// modified to implement the numbers-as-expressions task.

import java.lang.reflect.InvocationTargetException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class AdvNumbersAsExpressionsTest extends TestCase {

	// this method is designed to run on the AWT thread
	public void reportError(String msg) {
		throw new RuntimeException(msg);
	}

	/**
	 * Test method for {@link SpreadSheet#evaluate()}.
	 */
	public final void testEvaluate() {
		try {
			javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					SpreadSheet spreadsheet = new SpreadSheet("SpreadSheet");
					// Set up the content pane.
					spreadsheet.addComponentsToPane(spreadsheet
							.getContentPane());
					// Display the window.
					spreadsheet.pack();
					spreadsheet.setVisible(true);
					spreadsheet.setCell(0, 0, "10");
					spreadsheet.setCell(0, 1, "20");
					spreadsheet.setCell(0, 2, "=(((-(A1))*(4.5))+(B1))");
					spreadsheet.evaluate();
					String cellText1 = spreadsheet.getCellText(0, 2);
				
					assertTrue("failed to evaluate formula with -3",
						Double.valueOf(cellText1) == ((((-(10))*(4.5))+(20))));
					
				}
			});
		} catch (InvocationTargetException e) {
			Assert.fail(e.getCause().getMessage());
		} catch (Exception e) {
			Assert.fail("invokeAndWait (Swing task) raised exception");
		}
	}
}
