package solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * 
 * @author Xiao Zhe
 *
 */
public class RedMartSkiingSolution {
	
	private int N;
	private int[][] data;
	private int[][] rst;
	private ArrayList<Index> endPoints;
	private int maxDrop;
	
	public RedMartSkiingSolution(String dataPath){
		endPoints = new ArrayList<Index>();
	    maxDrop = Integer.MIN_VALUE;
		dataImport(dataPath);
		initializeStartStatus();
	}
    
	/**
	 * Index class is used to record the matrix row and column index
	 * 
	 * @author Xiao Zhe
	 *
	 */
	private class Index {
		private int i;
		private int j;

		public Index(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	/**
	 * Read data from .txt file to array
	 * 
	 * @param path
	 */
	private void dataImport(String path) {
		String thisLine = null;
		String[] arr = null;
		int i = 0;
		int j = 0;
		try {

			BufferedReader br = new BufferedReader(new FileReader(path));

			while ((thisLine = br.readLine()) != null) {

				j = 0;
				arr = thisLine.split(" ");
				if (arr.length == 2)
				{
					N = Integer.valueOf(arr[0]);
					data = new int[N][N];
					rst = new int[N][N];
					continue;
				}

				for (String e : arr) {
					data[i][j] = Integer.valueOf(e);
					j++;
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * find all the "peak point" and label them as 1 in rst array;
	 * 
	 * "peak point" is defined as the point which is larger than the surrounding points;
	 * 
	 */
	private void initializeStartStatus() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i - 1 < 0 ? true : data[i][j] > data[i - 1][j])
						&& (i + 1 >= N ? true : data[i][j] > data[i + 1][j])
						&& (j - 1 < 0 ? true : data[i][j] > data[i][j - 1])
						&& (j + 1 >= N ? true : data[i][j] > data[i][j + 1])) {
					rst[i][j] = 1;
				}
			}
		}
	}
	
    /**
     * DP algorithm to calculate the longest path to each of the points,
     * and the results are stored in rst array.
     * 
     * state transfer function: 
     * rst[i][j] = max{surrounding points:(rst[i-1][j],rst[i+1][j],rst[i][j-1],rst[i][j+1]) | for those surrounding points with value > rst[i][j]}+1;
     * 
     * Note the boundary points(please see the code)
     * 
     * @param i
     * @param j
     * @return
     */
	private int getMaxLength(int i, int j) {

		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		int a4 = 0;

		if (i - 1 >= 0 && data[i - 1][j] > data[i][j]) {
			if (rst[i - 1][j] == 0) {
				a1 = getMaxLength(i - 1, j);
				rst[i - 1][j] = a1;
			} else {
				a1 = rst[i - 1][j];
			}
		}

		if (i + 1 < N && data[i + 1][j] > data[i][j]) {
			if (rst[i + 1][j] == 0) {
				a2 = getMaxLength(i + 1, j);
				rst[i + 1][j] = a2;
			} else {
				a2 = rst[i + 1][j];
			}
		}

		if (j - 1 >= 0 && data[i][j - 1] > data[i][j]) {
			if (rst[i][j - 1] == 0) {
				a3 = getMaxLength(i, j - 1);
				rst[i][j - 1] = a3;
			} else {
				a3 = rst[i][j - 1];
			}
		}

		if (j + 1 < N && data[i][j + 1] > data[i][j]) {
			if (rst[i][j + 1] == 0) {
				a4 = getMaxLength(i, j + 1);
				rst[i][j + 1] = a4;
			} else {
				a4 = rst[i][j + 1];
			}
		}

		int max = Integer.MIN_VALUE;
		if (a1 > max)
			max = a1;
		if (a2 > max)
			max = a2;
		if (a3 > max)
			max = a3;
		if (a4 > max)
			max = a4;

		return max + 1;

	}

	/**
	 * call the {@link #getMaxLength(int, int)}
	 * for all the points to get the longest skiing path that ends at each point
	 * and calculate the longest path steps;
	 * 
	 * In this method, all the end points with longest path are recorded in{@link #endPoints}
	 * 
	 * 
	 * @return max length
	 */
	public int getMaxLength() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				rst[i][j] = getMaxLength(i, j);
			}
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (rst[i][j] == max) {
					endPoints.add(new Index(i, j));
				}
				if (rst[i][j] > max) {
					max = rst[i][j];
					endPoints.clear();
					endPoints.add(new Index(i, j));
				}
			}
		}
		return max;
	}

	
	public int getMaxDrop() {
		for (Index in : endPoints) {
			int i = in.i;
			int j = in.j;
			getMaxDrop(i, j, data[i][j]);
		}
		return maxDrop;
	}

	/**
	 * from the recorded end points to track back to find the path with max drop
	 * 
	 * @param i
	 * @param j
	 * @param des
	 */
	private void getMaxDrop(int i, int j, int des) {
		if ((i - 1 < 0 ? true : data[i][j] > data[i - 1][j])
				&& (i + 1 >= N ? true : data[i][j] > data[i + 1][j])
				&& (j - 1 < 0 ? true : data[i][j] > data[i][j - 1])
				&& (j + 1 >= N ? true : data[i][j] > data[i][j + 1])) {

			if (data[i][j] - des > maxDrop)
				maxDrop = data[i][j] - des;
			return;
		}

		if (i - 1 >= 0 && rst[i - 1][j] == rst[i][j] - 1) {
			getMaxDrop(i - 1, j, des);
		}

		if (i + 1 < N && rst[i + 1][j] == rst[i][j] - 1) {
			getMaxDrop(i + 1, j, des);
		}

		if (j - 1 >= 0 && rst[i][j - 1] == rst[i][j] - 1) {
			getMaxDrop(i, j - 1, des);
		}

		if (j + 1 < N && rst[i][j + 1] == rst[i][j] - 1) {
			getMaxDrop(i, j + 1, des);
		}

		return;

	}

}
