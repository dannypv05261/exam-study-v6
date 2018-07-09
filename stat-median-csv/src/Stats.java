import java.util.Arrays;


public class Stats {

	public Stats(int rows, int cols, String[][] data) {
		this.computeMedians(rows, cols, data);
	}

	public void computeMedians(int rows, int cols, String[][] data){
        String[] result = new String[cols];
        result[0] = "";
        result[1] = "Median";     
        
        for(int j = 2; j < cols; j++){
        	String[] transposedData = new String[rows-1];
            for(int i = 1; i < rows; i++){
            	transposedData[i-1] = data[i][j];
            }
            
            Arrays.sort(transposedData);
            
            if(transposedData.length % 2 == 0){
            	int index1 = transposedData.length / 2 - 1;
            	int index2 = index1 + 1;
            	int median = (int) Math.ceil((Integer.parseInt(transposedData[index1]) + Integer.parseInt(transposedData[index2])) / 2.0d);
            	result[j] = String.valueOf(median);
            }else {
            	int index = transposedData.length / 2;
            	result[j] = transposedData[index];
            }
        }
        data[rows] = result;
	}
}
