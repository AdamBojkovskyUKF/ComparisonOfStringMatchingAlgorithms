import java.util.ArrayList;
import java.util.List;

public class BF {

	public static List<Integer> measureTime(char[] x, char[] y) {
		long timeStart, timeEnd;
		int i, j, m = x.length, n = y.length;
		List<Integer> result = new ArrayList<Integer>();
		result.add(-1);

		timeStart = System.currentTimeMillis();

		/* Searching */
		for (j = 0; j <= n - m; ++j) {
			for (i = 0; i < m && x[i] == y[i + j]; ++i) {
			}
			if (i >= m) {
				result.add(j);
			}
		}

		timeEnd = System.currentTimeMillis();
		result.set(0, (int) (timeEnd - timeStart));

		return result;
	}

	public static List<Integer> countOperations(char[] x, char[] y) {
		/*
		Operations:
        0 Character Comparisons
        1 Integer Comparisons
        2 Two Integer Operations
        3 Integer Bitshifts
        4 Assignments
        */
		int[] operations = new int[5];

		int i, j, m = x.length, n = y.length;
		List<Integer> result = new ArrayList<Integer>();
		for (int a = 0; a < operations.length; ++a) {
			result.add(-1);
		}

		/* Searching */
		++operations[1];
		++operations[2];
		++operations[4];
		for (j = 0; j <= n - m; ++j) {
			++operations[1];
			++operations[2];
			++operations[2];

			++operations[0];
			++operations[1];
			++operations[2];
			++operations[4];
			for (i = 0; i < m && x[i] == y[i + j]; ++i) {
				++operations[0];
				++operations[1];
				++operations[2];
				++operations[2];
			}

			++operations[1];
			if (i >= m) {
				result.add(j);
			}
		}

		for (int a = 0; a < operations.length; ++a) {
			result.set(a, operations[a]);
		}

		return result;
	}
}
