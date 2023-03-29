import java.util.ArrayList;
import java.util.List;

public class QS {

	public static List<Integer> measureTime(char[] x, char[] y, int highestCharacter) {
		long timeStart, timeEnd;
		int j = 0, m = x.length, n = y.length;
		List<Integer> result = new ArrayList<Integer>();
		result.add(-1);

		int[] qsBc = new int[highestCharacter + 1];

		timeStart = System.currentTimeMillis();

		/* Preprocessing */
		preQsBc(x, qsBc);

		/* Searching */
		while (j < n - m) {
			if (arrayCmp(x, 0, y, j, m) == 0) {
				result.add(j);
			}
			j += qsBc[y[j + m]]; /* shift */
		}
		if (j == n - m && arrayCmp(x, 0, y, j, m) == 0) {
			result.add(j);
		}

		timeEnd = System.currentTimeMillis();
		result.set(0, (int) (timeEnd - timeStart));

		return result;
	}

	private static void preQsBc(char[] x, int[] qsBc) {
		int i, m = x.length;

		for (i = 0; i < qsBc.length; ++i) {
			qsBc[i] = m + 1;
		}
		for (i = 0; i < m; ++i) {
			qsBc[x[i]] = m - i;
		}
	}

	private static int arrayCmp(char[] a, int aIdx, char[] b, int bIdx, int length) {
		int i = 0;

		for (; i < length && aIdx + i < a.length && bIdx + i < b.length; i++) {
			if (a[aIdx + i] == b[bIdx + i]) {
			} else if (a[aIdx + i] > b[bIdx + i]) {
				return 1;
			} else {
				return 2;
			}
		}

		if (i < length) {
			if (a.length - aIdx == b.length - bIdx) {
				return 0;
			} else if (a.length - aIdx > b.length - bIdx) {
				return 1;
			} else {
				return 2;
			}
		} else {
			return 0;
		}
	}

	public static List<Integer> countOperations(char[] x, char[] y, int highestCharacter) {
		/*
		Operations:
        0 Character Comparisons
        1 Integer Comparisons
        2 Two Integer Operations
        3 Integer Bitshifts
        4 Assignments
        */
		int[] operations = new int[5];

		int j = 0, m = x.length, n = y.length;
		List<Integer> result = new ArrayList<Integer>();
		for (int a = 0; a < operations.length; ++a) {
			result.add(-1);
		}

		int[] qsBc = new int[highestCharacter + 1];

		/* Preprocessing */
		preQsBcOperations(x, qsBc, operations);

		/* Searching */
		++operations[1];
		++operations[2];
		while (j < n - m) {
			++operations[1];
			++operations[2];

			++operations[1];
			if (arrayCmpOperations(x, 0, y, j, m, operations) == 0) {
				result.add(j);
			}

			++operations[2];
			++operations[2];
			j += qsBc[y[j + m]]; /* shift */
		}

		++operations[1];
		++operations[1];
		++operations[2];
		if (j == n - m && arrayCmpOperations(x, 0, y, j, m, operations) == 0) {
			result.add(j);
		}

		for (int a = 0; a < operations.length; ++a) {
			result.set(a, operations[a]);
		}

		return result;
	}

	private static void preQsBcOperations(char[] x, int qsBc[], int[] operations) {
		int i, m = x.length;
		++operations[4];
		++operations[4];

		++operations[1];
		++operations[4];
		for (i = 0; i < qsBc.length; ++i) {
			++operations[1];
			++operations[2];

			++operations[2];
			qsBc[i] = m + 1;
		}

		++operations[1];
		++operations[4];
		for (i = 0; i < m; ++i) {
			++operations[1];
			++operations[2];

			++operations[2];
			qsBc[x[i]] = m - i;
		}
	}

	private static int arrayCmpOperations(char[] a, int aIdx, char[] b, int bIdx, int length, int[] operations) {
		++operations[4];
		int i = 0;

		++operations[1];
		++operations[1];
		++operations[1];
		++operations[2];
		++operations[2];
		for (; i < length && aIdx + i < a.length && bIdx + i < b.length; i++) {
			++operations[1];
			++operations[1];
			++operations[1];
			++operations[2];
			++operations[2];
			++operations[2];

			++operations[0];
			++operations[2];
			++operations[2];
			if (a[aIdx + i] == b[bIdx + i]) {
			} else if (a[aIdx + i] > b[bIdx + i]) {
				++operations[0];
				++operations[2];
				++operations[2];
				return 1;
			} else {
				++operations[0];
				++operations[2];
				++operations[2];
				return 2;
			}
		}

		++operations[1];
		if (i < length) {

			++operations[1];
			++operations[2];
			++operations[2];
			if (a.length - aIdx == b.length - bIdx) {
				return 0;
			} else if (a.length - aIdx > b.length - bIdx) {
				++operations[1];
				++operations[2];
				++operations[2];
				return 1;
			} else {
				++operations[1];
				++operations[2];
				++operations[2];
				return 2;
			}
		} else {
			return 0;
		}
	}
}
