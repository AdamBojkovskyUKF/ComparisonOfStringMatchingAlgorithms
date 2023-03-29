import java.util.ArrayList;
import java.util.List;

public class BM {

	public static List<Integer> measureTime(char[] x, char[] y, int highestCharacter) {
		long timeStart, timeEnd;
		int i, j = 0, m = x.length, n = y.length;
		List<Integer> result = new ArrayList<>();
		result.add(-1);

		int[] bmGs = new int[m];
		int[] bmBc = new int[highestCharacter + 1];

		timeStart = System.currentTimeMillis();

		/* Preprocessing */
		preBmGs(x, bmGs);
		preBmBc(x, bmBc);

		/* Searching */
		while (j <= n - m) {
			for (i = m - 1; i >= 0 && x[i] == y[i + j]; --i) {
			}
			if (i < 0) {
				result.add(j);
				j += bmGs[0];
			} else {
				j += Math.max(bmGs[i], bmBc[y[i + j]] - m + 1 + i);
			}
		}
		timeEnd = System.currentTimeMillis();
		result.set(0, (int) (timeEnd - timeStart));

		return result;
	}

	private static void preBmBc(char[] x, int[] bmBc) {
		int i, m = x.length;

		for (i = 0; i < bmBc.length; ++i) {
			bmBc[i] = m;
		}
		for (i = 0; i < m - 1; ++i) {
			bmBc[x[i]] = m - i - 1;
		}
	}

	private static void suffixes(char[] x, int[] suff) {
		int f = 0, g, i, m = x.length;

		suff[m - 1] = m;
		g = m - 1;
		for (i = m - 2; i >= 0; --i) {
			if (i > g && suff[i + m - 1 - f] < i - g) {
				suff[i] = suff[i + m - 1 - f];
			} else {
				if (i < g) {
					g = i;
				}
				f = i;
				while (g >= 0 && x[g] == x[g + m - 1 - f]) {
					--g;
				}
				suff[i] = f - g;
			}
		}
	}

	private static void preBmGs(char[] x, int[] bmGs) {
		int i, j, m = x.length;
		int[] suff = new int[m];

		suffixes(x, suff);

		for (i = 0; i < m; ++i)
			bmGs[i] = m;
		j = 0;
		for (i = m - 1; i >= 0; --i) {
			if (suff[i] == i + 1) {
				for (; j < m - 1 - i; ++j) {
					if (bmGs[j] == m) {
						bmGs[j] = m - 1 - i;
					}
				}
			}
		}
		for (i = 0; i <= m - 2; ++i) {
			bmGs[m - 1 - suff[i]] = m - 1 - i;
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

		int i, j = 0, temp1, temp2, m = x.length, n = y.length;
		List<Integer> result = new ArrayList<>();
		for (int a = 0; a < operations.length; ++a) {
			result.add(-1);
		}

		int[] bmGs = new int[m];
		int[] bmBc = new int[highestCharacter + 1];

		/* Preprocessing */
		preBmGsOperations(x, bmGs, operations);
		preBmBcOperations(x, bmBc, operations);

		/* Searching */
		++operations[1];
		++operations[2];
		while (j <= n - m) {
			++operations[1];
			++operations[2];

			++operations[0];
			++operations[1];
			for (i = m - 1; i >= 0 && x[i] == y[i + j]; --i) {
				++operations[0];
				++operations[1];
			}
				++operations[1];
				if (i < 0) {
					result.add(j);
					++operations[2];
					j += bmGs[0];
				} else {
					++operations[4];
					temp1 = bmGs[i];
					++operations[2];
					++operations[2];
					++operations[2];
					++operations[2];
					++operations[4];
					temp2 = bmBc[y[i + j]] - m + 1 + i;
					++operations[1];
					if (temp1 > temp2) {
						++operations[2];
						j += temp1;
					} else {
						++operations[2];
						j += temp2;
					}
				}
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

			++operations[2];
			++operations[2];
			bmBc[x[i]] = m - i - 1;
		}
	}

	private static void suffixesOperations(char[] x, int[] suff, int[] operations) {
		int f = 0, g, i, m = x.length;
		++operations[4];
		++operations[4];
		++operations[4];
		++operations[4];

		suff[m - 1] = m;
		++operations[2];
		g = m - 1;
		++operations[2];

		++operations[1];
		++operations[2];
		for (i = m - 2; i >= 0; --i) {
			++operations[1];
			++operations[2];


			if (i > g && suff[i + m - 1 - f] < i - g) {
				++operations[1];
				++operations[1];
				++operations[2];
				++operations[2];
				++operations[2];
				++operations[2];

				suff[i] = suff[i + m - 1 - f];
				++operations[2];
				++operations[2];
				++operations[2];
			} else {
				if (i < g) {
					++operations[1];

					g = i;
					++operations[4];
				}
				f = i;
				++operations[4];

				++operations[1];
				++operations[1];
				++operations[2];
				++operations[2];
				++operations[2];
				while (g >= 0 && x[g] == x[g + m - 1 - f]) {
					++operations[1];
					++operations[1];
					++operations[2];
					++operations[2];
					++operations[2];

					--g;
					++operations[2];
				}
				suff[i] = f - g;
				++operations[2];
			}
		}
	}

	private static void preBmGsOperations(char[] x, int[] bmGs, int[] operations) {
		int i, j, m = x.length;
		++operations[4];
		++operations[4];
		++operations[4];
		int[] suff = new int[m];
		++operations[4];

		suffixesOperations(x, suff, operations);

		++operations[1];
		++operations[4];
		for (i = 0; i < m; ++i) {
			++operations[1];
			++operations[2];

			bmGs[i] = m;
			++operations[4];
		}
		j = 0;
		++operations[4];

		++operations[1];
		++operations[2];
		++operations[4];
		for (i = m - 1; i >= 0; --i) {
			++operations[1];
			++operations[2];
			if (suff[i] == i + 1) {
				++operations[4];

				++operations[1];
				++operations[2];
				++operations[2];
				for (; j < m - 1 - i; ++j) {
					++operations[1];
					++operations[2];
					++operations[2];
					++operations[2];

					if (bmGs[j] == m) {
						++operations[1];
						bmGs[j] = m - 1 - i;
						++operations[2];
						++operations[2];
					}
				}
			}
		}
		++operations[1];
		++operations[2];
		for (i = 0; i <= m - 2; ++i) {
			++operations[1];
			++operations[2];
			++operations[2];

			bmGs[m - 1 - suff[i]] = m - 1 - i;
			++operations[2];
			++operations[2];
			++operations[2];
			++operations[2];
		}
	}
}
