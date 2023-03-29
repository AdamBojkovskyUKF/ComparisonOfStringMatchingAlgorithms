import java.util.ArrayList;
import java.util.List;

public class SO {

	public static List<Integer> measureTime(char[] x, char[] y, int highestCharacter) {
		long timeStart, timeEnd;
		long lim, state;
		long[] s = new long[highestCharacter + 1];
		int j, m = x.length, n = y.length;

		List<Integer> result = new ArrayList<Integer>();
		result.add(-1);

		timeStart = System.currentTimeMillis();

		/* Preprocessing */
		lim = preSo(x, s);

		/* Searching */
		for (state = ~0, j = 0; j < n; ++j) {
			state = (state << 1) | s[y[j]];
			if (state < lim)
				result.add(j - m + 1);
		}

		timeEnd = System.currentTimeMillis();
		result.set(0, (int) (timeEnd - timeStart));

		return result;
	}

	private static long preSo(char[] x, long[] s) {
		long j, lim, m = x.length;
		int i;
		for (i = 0; i < s.length; ++i) {
			s[i] = ~0;
		}
		for (lim = i = 0, j = 1; i < m; ++i, j <<= 1) {
			s[x[i]] &= ~j;
			lim |= j;
		}
		lim = ~(lim >> 1);
		return (lim);
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

		long lim, state;
		long[] s = new long[highestCharacter + 1];
		int j, m = x.length, n = y.length;

		List<Integer> result = new ArrayList<Integer>();
		for (int a = 0; a < operations.length; ++a) {
			result.add(-1);
		}

		/* Preprocessing */
		lim = preSoOperations(x, s, operations);

		/* Searching */
		++operations[1];
		++operations[4];
		++operations[4];
		for (state = ~0, j = 0; j < n; ++j) {
			++operations[1];
			++operations[2];
			++operations[4];
			++operations[4];

			++operations[2];
			++operations[3];
			state = (state << 1) | s[y[j]];

			++operations[1];
			if (state < lim) {
				++operations[2];
				++operations[2];
				result.add(j - m + 1);
			}
		}

		for (int a = 0; a < operations.length; ++a) {
			result.set(a, operations[a]);
		}

		return result;
	}

	private static long preSoOperations(char[] x, long[] s, int[] operations) {
		long j, lim, m = x.length;
		++operations[4];
		++operations[4];
		++operations[4];
		int i;
		++operations[4];

		++operations[1];
		++operations[4];
		for (i = 0; i < s.length; ++i) {
			++operations[1];
			++operations[2];

			s[i] = ~0;
			++operations[2];
		}

		++operations[1];
		++operations[4];
		++operations[4];
		for (lim = i = 0, j = 1; i < m; ++i, j <<= 1) {
			++operations[1];
			++operations[2];
			++operations[3];

			s[x[i]] &= ~j;
			++operations[2];
			++operations[2];
			lim |= j;
			++operations[2];
		}
		lim = ~(lim >> 1);
		++operations[2];
		++operations[3];
		return (lim);
	}
}
