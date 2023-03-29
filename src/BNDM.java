import java.util.ArrayList;
import java.util.List;

public class BNDM {

	public static List<Integer> measureTime(char[] x, char[] y, int highestCharacter) {
		long timeStart, timeEnd;
		int i, j, s, d, last, m = x.length, n = y.length;
		List<Integer> result = new ArrayList<Integer>();
		result.add(-1);

		int[] b = new int[highestCharacter + 1];

		timeStart = System.currentTimeMillis();

		/* Pre processing */
		for (i = 0; i < b.length; i++)
			b[i] = 0;
		s = 1;
		for (i = m - 1; i >= 0; i--) {
			b[x[i]] |= s;
			s <<= 1;
		}

		/* Searching phase */
		j = 0;
		while (j <= n - m) {
			i = m - 1;
			last = m;
			d = ~0;
			while (i >= 0 && d != 0) {
				d &= b[y[j + i]];
				i--;
				if (d != 0) {
					if (i >= 0) {
						last = i + 1;
					} else {
						result.add(j);
					}
				}
				d <<= 1;
			}
			j += last;
		}

		timeEnd = System.currentTimeMillis();
		result.set(0, (int) (timeEnd - timeStart));

		return result;
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

		int i, j = 0, s, d, last, m = x.length, n = y.length;
		List<Integer> result = new ArrayList<Integer>();
		for (int a = 0; a < operations.length; ++a) {
			result.add(-1);
		}

		int[] b = new int[highestCharacter + 1];

		/* Pre processing */
		for (i = 0; i < b.length; i++){
			b[i] = 0;
		}
		s = 1;
		for (i = m - 1; i >= 0; i--) {
			b[x[i]] |= s;
			s <<= 1;
		}

		/* Searching phase */
		++operations[1];
		++operations[2];
		while (j <= n - m) {
			++operations[1];
			++operations[2];

			++operations[2];
			i = m - 1;
			++operations[4];
			last = m;
			++operations[4];
			d = ~0;

			++operations[1];
			++operations[1];
			while (i >= 0 && d != 0) {
				++operations[1];
				++operations[1];

				++operations[2];
				d &= b[y[j + i]];
				++operations[2];
				i--;
				++operations[1];
				if (d != 0) {
					++operations[1];
					if (i >= 0) {
						++operations[2];
						last = i + 1;
					} else {
						result.add(j);
					}
				}
				++operations[3];
				d <<= 1;
			}
			++operations[2];
			j += last;
		}

		for (int a = 0; a < operations.length; ++a) {
			result.set(a, operations[a]);
		}

		return result;
	}
}
