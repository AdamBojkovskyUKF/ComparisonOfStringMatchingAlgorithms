import java.util.ArrayList;
import java.util.List;

public class KPMSS {

	public static List<Integer> measureTime(char[] ptrn, char[] y, int highestCharacter) {
		long timeStart, timeEnd;
		char[] x = new char[ptrn.length + 1];
		System.arraycopy(ptrn, 0, x, 0, ptrn.length);
		int i, j, k, kmpStart, per, start, wall, m = ptrn.length, n = y.length;
		List<Integer> result = new ArrayList<Integer>();
		result.add(-1);

		int[] kmpNext = new int[x.length];
		int[] list = new int[x.length];
		int[] mpNext = new int[x.length];
		int[] z = new int[highestCharacter + 1];

		timeStart = System.currentTimeMillis();

		/* Preprocessing */
		preMp(x, mpNext);
		preKmp(x, kmpNext);

		for (i = 0; i < z.length; i++) {
			z[i] = -1;
		}
		for (i = 0; i < m; i++) {
			list[i] = -1;
		}
		z[x[0]] = 0;
		for (i = 1; i < m; ++i) {
			list[i] = z[x[i]];
			z[x[i]] = i;
		}

		/* Searching */
		wall = 0;
		per = m - kmpNext[m];
		j = -1;
		do {
			j += m;
		} while (j < n && z[y[j]] < 0);
		if (j >= n) {

			timeEnd = System.currentTimeMillis();
			result.set(0, (int) (timeEnd - timeStart));

			return result;
		}
		i = z[y[j]];
		start = j - i;
		while (start <= n - m) {
			if (start > wall) {
				wall = start;
			}
			k = attempt(y, x, start, wall);
			wall = start + k;
			if (k == m) {
				result.add(start);
				i -= per;
			} else {
				i = list[i];
			}
			if (i < 0) {
				do {
					j += m;
				} while (j < n && z[y[j]] < 0);
				if (j >= n) {

					timeEnd = System.currentTimeMillis();
					result.set(0, (int) (timeEnd - timeStart));

					return result;
				}
				i = z[y[j]];
			}
			kmpStart = start + k - kmpNext[k];
			k = kmpNext[k];
			start = j - i;
			while (start < kmpStart || (kmpStart < start && start < wall)) {
				if (start < kmpStart) {
					i = list[i];
					if (i < 0) {
						do {
							j += m;
						} while (j < n && z[y[j]] < 0);
						if (j >= n) {

							timeEnd = System.currentTimeMillis();
							result.set(0, (int) (timeEnd - timeStart));

							return result;
						}
						i = z[y[j]];
					}
					start = j - i;
				} else {
					kmpStart += (k - mpNext[k]);
					k = mpNext[k];
				}
			}
		}

		timeEnd = System.currentTimeMillis();
		result.set(0, (int) (timeEnd - timeStart));

		return result;
	}

	private static void preMp(char[] x, int[] mpNext) {
		int i = 0, j = mpNext[0] = -1, m = (x.length - 1);

		while (i < m) {
			while (j > -1 && x[i] != x[j]) {
				j = mpNext[j];
			}
			mpNext[++i] = ++j;
		}
	}

	private static void preKmp(char[] x, int[] kmpNext) {
		int i = 0, j = kmpNext[0] = -1, m = (x.length - 1);

		while (i < m) {
			while (j > -1 && x[i] != x[j]) {
				j = kmpNext[j];
			}
			i++;
			j++;
			if (x[i] == x[j]) {
				kmpNext[i] = kmpNext[j];
			} else {
				kmpNext[i] = j;
			}
		}
	}

	private static int attempt(char[] y, char[] x, int start, int wall) {
		int m = (x.length - 1);
		int k = wall - start;
		while (k < m && x[k] == y[k + start]) {
			++k;
		}
		return k;
	}

	public static List<Integer> countOperations(char[] ptrn, char[] y, int highestCharacter) {
		/*
		Operations:
        0 Character Comparisons
        1 Integer Comparisons
        2 Two Integer Operations
        3 Integer Bitshifts
        4 Assignments
        */
		int[] operations = new int[5];

		char[] x = new char[ptrn.length + 1];
		System.arraycopy(ptrn, 0, x, 0, ptrn.length);
		int i, j = -1, k, kmpStart, per, start, wall = 0, m = ptrn.length, n = y.length;
		List<Integer> result = new ArrayList<Integer>();
		for (int a = 0; a < operations.length; ++a) {
			result.add(-1);
		}

		int[] kmpNext = new int[x.length];
		int[] list = new int[x.length];
		int[] mpNext = new int[x.length];
		int[] z = new int[highestCharacter + 1];

		/* Preprocessing */
		preMpOperations(x, mpNext, operations);
		preKmpOperations(x, kmpNext, operations);

		for (i = 0; i < z.length; i++) {
			z[i] = -1;
		}
		for (i = 0; i < m; i++) {
			list[i] = -1;
		}
		z[x[0]] = 0;
		for (i = 1; i < m; ++i) {
			list[i] = z[x[i]];
			z[x[i]] = i;
		}

		/* Searching */
		++operations[2];
		per = m - kmpNext[m];
		do {
			++operations[2];
			j += m;

			++operations[1];
			++operations[1];
		} while (j < n && z[y[j]] < 0);

		++operations[1];
		if (j >= n) {

			for (int a = 0; a < operations.length; ++a) {
				result.set(a, operations[a]);
			}

			return result;
		}

		++operations[4];
		i = z[y[j]];
		++operations[2];
		start = j - i;

		++operations[1];
		++operations[2];
		while (start <= n - m) {
			++operations[1];
			++operations[2];

			++operations[1];
			if (start > wall) {
				++operations[4];
				wall = start;
			}
			k = attemptOperations(y, x, start, wall, operations);
			++operations[2];
			wall = start + k;
			++operations[1];
			if (k == m) {
				result.add(start);
				++operations[2];
				i -= per;
			} else {
				++operations[4];
				i = list[i];
			}
			++operations[1];
			if (i < 0) {
				do {
					++operations[2];
					j += m;

					++operations[1];
					++operations[1];
				} while (j < n && z[y[j]] < 0);
				++operations[1];
				if (j >= n) {

					for (int a = 0; a < operations.length; ++a) {
						result.set(a, operations[a]);
					}

					return result;
				}
				++operations[4];
				i = z[y[j]];
			}
			++operations[2];
			++operations[2];
			kmpStart = start + k - kmpNext[k];
			++operations[4];
			k = kmpNext[k];
			++operations[2];
			start = j - i;

			++operations[1];
			++operations[1];
			++operations[1];
			while (start < kmpStart || (kmpStart < start && start < wall)) {
				++operations[1];
				++operations[1];
				++operations[1];

				++operations[1];
				if (start < kmpStart) {
					++operations[4];
					i = list[i];
					++operations[1];
					if (i < 0) {
						do {
							++operations[2];
							j += m;

							++operations[1];
							++operations[1];
						} while (j < n && z[y[j]] < 0);
						++operations[1];
						if (j >= n) {

							for (int a = 0; a < operations.length; ++a) {
								result.set(a, operations[a]);
							}

							return result;
						}
						++operations[4];
						i = z[y[j]];
					}
					++operations[2];
					start = j - i;
				} else {
					++operations[2];
					++operations[2];
					kmpStart += (k - mpNext[k]);
					++operations[4];
					k = mpNext[k];
				}
			}
		}

		for (int a = 0; a < operations.length; ++a) {
			result.set(a, operations[a]);
		}

		return result;
	}

	private static void preMpOperations(char[] x, int[] mpNext, int[] operations) {
		int i = 0, j = mpNext[0] = -1, m = (x.length - 1);
		++operations[2];
		++operations[4];
		++operations[4];
		++operations[4];

		++operations[1];
		while (i < m) {
			++operations[1];

			++operations[0];
			++operations[1];
			while (j > -1 && x[i] != x[j]) {
				++operations[0];
				++operations[1];

				j = mpNext[j];
				++operations[4];
			}
			mpNext[++i] = ++j;
			++operations[2];
			++operations[2];
		}
	}

	private static void preKmpOperations(char[] x, int[] kmpNext, int[] operations) {
		int i = 0, j = kmpNext[0] = -1, m = (x.length - 1);
		++operations[2];
		++operations[4];
		++operations[4];
		++operations[4];

		++operations[1];
		while (i < m) {
			++operations[1];

			++operations[0];
			++operations[1];
			while (j > -1 && x[i] != x[j]) {
				++operations[0];
				++operations[1];

				j = kmpNext[j];
				++operations[4];
			}
			i++;
			++operations[2];
			j++;
			++operations[2];

			++operations[0];
			if (x[i] == x[j]) {
				kmpNext[i] = kmpNext[j];
				++operations[4];
			} else {
				kmpNext[i] = j;
				++operations[4];
			}
		}
	}

	private static int attemptOperations(char[] y, char[] x, int start, int wall, int[] operations) {
		++operations[2];
		int m = (x.length - 1);
		++operations[2];
		int k = wall - start;
		++operations[0];
		++operations[1];
		while (k < m && x[k] == y[k + start]) {
			++operations[0];
			++operations[2];
			++k;
		}
		return k;
	}
}