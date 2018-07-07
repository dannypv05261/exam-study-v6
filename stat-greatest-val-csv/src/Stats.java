
public class Stats {

	public Stats(int rows, int cols, String[][] data) {
		this.greatestValue(rows, cols, data);
	}

	public void writeData(int rows, int cols, String[][] data) {
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				System.out.print("["+data[i][j]+"]");
			}
			System.out.println();
		}
	}
	
	private void greatestValue(int rows, int cols, String[][] data){
		String[] result = new String[cols];
		result[0] = "Greatest";
		result[1] = "Value";
		for(int j = 2; j < cols; j++){
			int max = -1;
			for(int i = 1; i < rows; i++){
				max = Math.max(max, Integer.parseInt(data[i][j]));
			}
			result[j] = String.valueOf(max);
		}
		data[rows] = result;
	}

}
