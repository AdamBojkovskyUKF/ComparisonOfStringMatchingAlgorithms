import java.util.ArrayList;
import java.util.List;

public class SS {

	public static List<Integer> measureTime(char[] x, char[] y, int highestCharacter) {
		long timeStart, timeEnd;
		int i, j, m = x.length, n = y.length;
		Cell ptr;
		Cell[] z = new Cell[highestCharacter + 1];
		List<Integer> result = new ArrayList<Integer>();
		result.add(-1);

		timeStart = System.currentTimeMillis();

		/* Preprocessing */
		for (i = 0; i < m; ++i) {
			ptr = new Cell();
			ptr.element = i;
			ptr.next = z[x[i]];
			z[x[i]] = ptr;
		}

		/* Searching */
		for (j = m - 1; j < n; j += m) {
			for (ptr = z[y[j]]; ptr != null; ptr = ptr.next) {
				if (arrayCmp(x, 0, y, j - ptr.element, m) == 0) {
					result.add(j - ptr.element);
				}
			}
		}
		timeEnd = System.currentTimeMillis();
		result.set(0, (int) (timeEnd - timeStart));

		return result;
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

		int i, j, m = x.length, n = y.length;
		Cell ptr;
		Cell[] z = new Cell[highestCharacter + 1];
		List<Integer> result = new ArrayList<Integer>();
		for (int a = 0; a < operations.length; ++a) {
			result.add(-1);
		}

		/* Preprocessing */
		for (i = 0; i < m; ++i) {
			ptr = new Cell();
			ptr.element = i;
			ptr.next = z[x[i]];
			z[x[i]] = ptr;
		}

		/* Searching */
		++operations[1];
		++operations[2];
		for (j = m - 1; j < n; j += m) {
			++operations[1];
			++operations[2];

			++operations[1];
			++operations[4];
			for (ptr = z[y[j]]; ptr != null; ptr = ptr.next) {
				++operations[1];
				++operations[2];

				++operations[1];
				if (arrayCmpOperations(x, 0, y, j - ptr.element, m,operations) == 0) {
					++operations[2];
					result.add(j - ptr.element);
				}
			}
		}

		for (int a = 0; a < operations.length; ++a) {
			result.set(a, operations[a]);
		}

		return result;
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

	private static class Cell {
		int element;
		Cell next;
	}

}
