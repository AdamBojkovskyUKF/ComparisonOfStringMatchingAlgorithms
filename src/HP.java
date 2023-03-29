import java.util.ArrayList;
import java.util.List;

public class HP {

	public static List<Integer> measureTime(char[] x, char[] y, int highestCharacter) {
		long timeStart, timeEnd;
		int j = 0, m = x.length, n = y.length;
		List<Integer> result = new ArrayList<Integer>();
		result.add(-1);

		char c;

		int[] bmBc = new int[highestCharacter + 1];

		timeStart = System.currentTimeMillis();

		/* Preprocessing */
		preBmBc(x, bmBc);

		/* Searching */
		while (j <= n - m) {
			c = y[j + m - 1];
			if (x[m - 1] == c && arrayCmp(x, 0, y, j, (m - 1)) == 0) {
				result.add(j);
			}
			j += bmBc[c];
		}

		timeEnd = System.currentTimeMillis();
		result.set(0, (int) (timeEnd - timeStart));

		return result;
	}

	private static void preBmBc(char[] x, int[] bmBc) {
		int i, m = x.length;

		for (i = 0; i < bmBc.length; ++i)
			bmBc[i] = m;
		for (i = 0; i < m - 1; ++i)
			bmBc[x[i]] = m - i - 1;
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

		char c;

		int[] bmBc = new int[highestCharacter + 1];


		/* Preprocessing */
		preBmBcOperations(x, bmBc, operations);

		/* Searching */
		++operations[1];
		++operations[2];
		while (j <= n - m) {
			++operations[1];
			++operations[2];

			++operations[2];
			++operations[2];
			c = y[j + m - 1];

			++operations[0];
			++operations[1];
			++operations[2];
			++operations[2];
			if (x[m - 1] == c && arrayCmpOperations(x, 0, y, j, (m - 1),operations) == 0) {
				result.add(j);
			}
			++operations[2];
			j += bmBc[c];
		}

		for (int a = 0; a < operations.length; ++a) {
			result.set(a, operations[a]);
		}

		return result;
	}

	private static void preBmBcOperations(char[] x, int[] bmBc, int[] operations) {
		int i, m = x.length;
		++operations[4];
		++operations[4];

		++operations[1];
		++operations[4];
		for (i = 0; i < bmBc.length; ++i) {
			++operations[1];
			++operations[2];

			bmBc[i] = m;
			++operations[4];
		}
		++operations[1];
		++operations[2];
		++operations[4];
		for (i = 0; i < m - 1; ++i) {
			++operations[1];
			++operations[2];
			++operations[2];

			bmBc[x[i]] = m - i - 1;
			++operations[2];
			++operations[2];
			++operations[2];
		}
	}

	private static int arrayCmpOperations(char[] a, int aIdx, char[] b, int bIdx, int length, int[] operations) {
		int i = 0;
		++operations[4];

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
