import java.util.ArrayList;
import java.util.List;

public class MS {

	private static class Pattern {
		int loc;
		char c;
	}

	public static List<Integer> measureTime(char[] x, char[] y, int highestCharacter) {
		long timeStart, timeEnd;
		int i, j = 0, temp1, temp2, m = x.length, n = y.length;
		int[] adaptedGs = new int[m + 1], qsBc = new int[highestCharacter + 1], minShift;
		Pattern[] pat = new Pattern[m];
		List<Integer> result = new ArrayList<Integer>();
		result.add(-1);

		timeStart = System.currentTimeMillis();

		/* Preprocessing */
		minShift = computeMinShift(x);
		orderPattern(x, pat, minShift);
		preQsBc(x, qsBc);
		preAdaptedGs(x, adaptedGs, pat);

		/* Searching */
		while (j <= n - m) {
			i = 0;
			while (i < m && pat[i].c == y[j + pat[i].loc]) {
				++i;
			}
			if (i >= m) {
				result.add(j);
			}
			if (j < n - m) {
				temp1 = adaptedGs[i];
				temp2 = qsBc[y[j + m]];
				if (temp1 > temp2) {
					j += temp1;
				} else {
					j += temp2;
				}
			} else {
				j += adaptedGs[i];
			}
		}

		timeEnd = System.currentTimeMillis();
		result.set(0, (int) (timeEnd - timeStart));

		return result;
	}
	private static int[] computeMinShift(char[] x) {
		int i, j;
		int[] minShift = new int[x.length];

		for (i = 0; i < x.length; ++i) {
			for (j = i - 1; j >= 0; --j) {
				if (x[i] == x[j]) {
					break;
				}
			}
			minShift[i] = i - j;
		}
		return minShift;
	}

	private static void orderPattern(char[] x, Pattern[] pat, int[] minShift) {

		for (int i = 0; i < x.length; ++i) {
			Pattern ptrn = new Pattern();
			ptrn.loc = i;
			ptrn.c = x[i];
			pat[i] = ptrn;
		}

		qsortPtrn(pat, 0, x.length - 1, minShift);
	}

	private static void qsortPtrn(Pattern[] pat, int low, int n, int[] minShift) {
		int lo = low;
		int hi = n;
		if (lo >= n) {
			return;
		}
		Pattern mid = pat[(lo + hi) / 2];
		while (lo < hi) {
			while (lo < hi && maxShiftPcmp(pat[lo], mid, minShift) < 0) {
				lo++;
			}
			while (lo < hi && maxShiftPcmp(pat[hi], mid, minShift) > 0) {
				hi--;
			}
			if (lo < hi) {
				Pattern T = pat[lo];
				pat[lo] = pat[hi];
				pat[hi] = T;
			}
		}
		if (hi < lo) {
			int T = hi;
			lo = T;
		}
		qsortPtrn(pat, low, lo, minShift);
		if (lo == low) {
			qsortPtrn(pat, lo + 1, n, minShift);
		} else {
			qsortPtrn(pat, lo, n, minShift);
		}
	}

	private static int maxShiftPcmp(Pattern pat1, Pattern pat2, int[] minShift) {
		int dsh;

		dsh = minShift[pat2.loc] - minShift[pat1.loc];
		if (dsh != 0) {
			return dsh;
		} else {
			return pat2.loc - pat1.loc;
		}
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

	private static int matchShift(char[] x, int ploc, int lshift, Pattern[] pat) {
		int i, j;

		for (; lshift < x.length; ++lshift) {
			i = ploc;
			while (--i >= 0) {
				if ((j = (pat[i].loc - lshift)) < 0) {
					continue;
				}
				if (pat[i].c != x[j]) {
					break;
				}
			}
			if (i < 0) {
				break;
			}
		}
		return (lshift);
	}

	private static void preAdaptedGs(char[] x, int[] adaptedGs, Pattern[] pat) {
		int lshift, i, ploc;

		adaptedGs[0] = lshift = 1;
		for (ploc = 1; ploc <= x.length; ++ploc) {
			lshift = matchShift(x, ploc, lshift, pat);
			adaptedGs[ploc] = lshift;
		}
		for (ploc = 0; ploc < x.length; ++ploc) {
			lshift = adaptedGs[ploc];
			while (lshift < x.length) {
				i = pat[ploc].loc - lshift;
				if (i < 0 || pat[ploc].c != x[i]) {
					break;
				}
				++lshift;
				lshift = matchShift(x, ploc, lshift, pat);
			}
			adaptedGs[ploc] = lshift;
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
		int[] adaptedGs = new int[m + 1], qsBc = new int[highestCharacter + 1], minShift;
		Pattern[] pat = new Pattern[m];
		List<Integer> result = new ArrayList<Integer>();
		for (int a = 0; a < operations.length; ++a) {
			result.add(-1);
		}

		/* Preprocessing */
		minShift = computeMinShiftOperations(x, operations);
		orderPatternOperations(x, pat, minShift, operations);
		preQsBcOperations(x, qsBc, operations);
		preAdaptedGsOperations(x, adaptedGs, pat, operations);

		/* Searching */
		++operations[1];
		++operations[2];
		while (j <= n - m) {
			++operations[1];
			++operations[2];

			++operations[4];
			i = 0;

			++operations[0];
			++operations[1];
			++operations[2];
			while (i < m && pat[i].c == y[j + pat[i].loc]) {
				++operations[0];
				++operations[1];
				++operations[2];

				++operations[2];
				++i;
			}
			++operations[1];
			if (i >= m) {
				result.add(j);
			}
			++operations[1];
			++operations[2];
			if (j < n - m) {
				++operations[4];
				temp1 = adaptedGs[i];
				++operations[2];
				++operations[4];
				temp2 = qsBc[y[j + m]];
				++operations[1];
				if (temp1 > temp2) {
					++operations[2];
					j += temp1;
				} else {
					++operations[2];
					j += temp2;
				}
			} else {
				++operations[2];
				j += adaptedGs[i];
			}
		}

		for (int a = 0; a < operations.length; ++a) {
			result.set(a, operations[a]);
		}

		return result;
	}

	private static int[] computeMinShiftOperations(char[] x, int[] operations) {
		int i, j;
		++operations[4];
		++operations[4];
		int[] minShift = new int[x.length];
		++operations[4];

		++operations[1];
		++operations[4];
		for (i = 0; i < x.length; ++i) {
			++operations[1];
			++operations[2];

			++operations[1];
			++operations[2];
			for (j = i - 1; j >= 0; --j) {
				++operations[1];
				++operations[2];

				++operations[0];
				if (x[i] == x[j]) {
					break;
				}
			}
			minShift[i] = i - j;
			++operations[2];
		}
		return minShift;
	}

	private static void orderPatternOperations(char[] x, Pattern[] pat, int[] minShift, int[] operations) {

		++operations[1];
		++operations[4];
		for (int i = 0; i < x.length; ++i) {
			++operations[1];
			++operations[4];

			Pattern ptrn = new Pattern();
			++operations[4];
			ptrn.loc = i;
			++operations[4];
			ptrn.c = x[i];
			++operations[4];
			pat[i] = ptrn;
			++operations[4];
		}

		++operations[2];
		qsortPtrnOperations(pat, 0, x.length - 1, minShift, operations);
	}

	private static void qsortPtrnOperations(Pattern[] pat, int low, int n, int[] minShift, int[] operations) {
		int lo = low;
		++operations[4];
		int hi = n;
		++operations[4];

		++operations[1];
		if (lo >= n) {
			return;
		}

		Pattern mid = pat[(lo + hi) / 2];
		++operations[2];
		++operations[2];

		++operations[1];
		while (lo < hi) {
			++operations[1];

			++operations[1];
			++operations[1];
			while (lo < hi && maxShiftPcmpOperations(pat[lo], mid, minShift, operations) < 0) {
				++operations[1];
				++operations[1];

				++operations[2];
				lo++;
			}

			++operations[1];
			++operations[1];
			while (lo < hi && maxShiftPcmpOperations(pat[hi], mid, minShift, operations) > 0) {
				++operations[1];
				++operations[1];

				++operations[2];
				hi--;
			}

			++operations[1];
			if (lo < hi) {
				Pattern T = pat[lo];
				++operations[4];
				pat[lo] = pat[hi];
				++operations[4];
				pat[hi] = T;
				++operations[4];
			}
		}

		++operations[1];
		if (hi < lo) {
			int T = hi;
			++operations[4];
			lo = T;
			++operations[4];
		}

		qsortPtrnOperations(pat, low, lo, minShift, operations);

		++operations[1];
		if (lo == low) {
			++operations[2];
			qsortPtrnOperations(pat, lo + 1, n, minShift, operations);
		} else {
			qsortPtrnOperations(pat, lo, n, minShift, operations);
		}
	}

	private static int maxShiftPcmpOperations(Pattern pat1, Pattern pat2, int[] minShift, int[] operations) {
		int dsh;
		++operations[4];

		dsh = minShift[pat2.loc] - minShift[pat1.loc];
		++operations[2];

		++operations[1];
		if (dsh != 0) {
			return dsh;
		} else {
			++operations[2];
			return pat2.loc - pat1.loc;
		}
	}

	private static void preQsBcOperations(char[] x, int[] qsBc, int[] operations) {
		int i, m = x.length;
		++operations[4];
		++operations[4];

		++operations[1];
		++operations[4];
		for (i = 0; i < qsBc.length; ++i) {
			++operations[1];
			++operations[2];

			qsBc[i] = m + 1;
			++operations[2];
		}

		++operations[1];
		++operations[4];
		for (i = 0; i < m; ++i) {
			++operations[1];
			++operations[2];

			qsBc[x[i]] = m - i;
			++operations[2];
		}
	}

	private static int matchShiftOperations(char[] x, int ploc, int lshift, Pattern[] pat, int[] operations) {
		int i, j;
		++operations[4];
		++operations[4];

		++operations[1];
		for (; lshift < x.length; ++lshift) {
			++operations[1];
			++operations[2];

			i = ploc;
			++operations[4];

			++operations[1];
			++operations[2];
			while (--i >= 0) {
				++operations[1];
				++operations[2];

				++operations[1];
				++operations[2];
				if ((j = (pat[i].loc - lshift)) < 0) {
					continue;
				}

				++operations[0];
				if (pat[i].c != x[j]) {
					break;
				}
			}
			++operations[1];
			if (i < 0) {
				break;
			}
		}
		return (lshift);
	}

	private static void preAdaptedGsOperations(char[] x, int[] adaptedGs, Pattern[] pat, int[] operations) {
		int lshift, i, ploc;
		++operations[4];
		++operations[4];
		++operations[4];


		adaptedGs[0] = lshift = 1;
		++operations[4];
		++operations[4];

		++operations[1];
		++operations[4];
		for (ploc = 1; ploc <= x.length; ++ploc) {
			++operations[1];
			++operations[2];

			lshift = matchShiftOperations(x, ploc, lshift, pat, operations);
			++operations[4];
			adaptedGs[ploc] = lshift;
			++operations[4];
		}

		++operations[1];
		++operations[4];
		for (ploc = 0; ploc < x.length; ++ploc) {
			++operations[1];
			++operations[2];

			lshift = adaptedGs[ploc];
			++operations[4];

			++operations[1];
			while (lshift < x.length) {
				++operations[1];

				i = pat[ploc].loc - lshift;
				++operations[2];

				++operations[0];
				++operations[1];
				if (i < 0 || pat[ploc].c != x[i]) {
					break;
				}
				++lshift;
				++operations[2];
				lshift = matchShiftOperations(x, ploc, lshift, pat, operations);
				++operations[4];
			}
			adaptedGs[ploc] = lshift;
			++operations[4];
		}
	}
}
