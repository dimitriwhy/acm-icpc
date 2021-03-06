import java.math.*;
import java.util.*;

public class PolygonTraversal {
    public long count(int n, int[] points) {
        long[][] ways = new long[1 << n][n];
        if (true) {
            int mask = 0;
            for (int point : points) {
                mask |= 1 << point - 1;
            }
            ways[mask][points[points.length - 1] - 1] = 1;
        }
        for (int mask = 0; mask < 1 << n; ++ mask) {
            for (int i = 0; i < n; ++ i) {
                if (ways[mask][i] > 0) {
                    int first = 1;
                    while ((mask >> (i + first) % n & 1) == 0) {
                        first ++;
                    }
                    int last = n - 1;
                    while ((mask >> (i + last) % n & 1) == 0) {
                        last --;
                    }
                    for (int j = first; j <= last; ++ j) {
                        int k = (i + j) % n;
                        if ((mask >> k & 1) == 0) {
                            ways[mask | 1 << k][k] += ways[mask][i];
                        }
                    }
                }
            }
        }
        long result = 0;
        for (int i = 2; i < n - 1; ++ i) {
            result += ways[(1 << n) - 1][(points[0] - 1 + i) % n];
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			PolygonTraversalHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				PolygonTraversalHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class PolygonTraversalHarness {
	public static void run_test(int casenum) {
		if (casenum != -1) {
			if (runTestCase(casenum) == -1)
				System.err.println("Illegal input! Test case " + casenum + " does not exist.");
			return;
		}
		
		int correct = 0, total = 0;
		for (int i=0;; ++i) {
			int x = runTestCase(i);
			if (x == -1) {
				if (i >= 100) break;
				continue;
			}
			correct += x;
			++total;
		}
		
		if (total == 0) {
			System.err.println("No test cases run.");
		} else if (correct < total) {
			System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
		} else {
			System.err.println("All " + total + " tests passed!");
		}
	}
	
	static boolean compareOutput(long expected, long result) { return expected == result; }
	static String formatResult(long res) {
		return String.format("%d", res);
	}
	
	static int verifyCase(int casenum, long expected, long received) { 
		System.err.print("Example " + casenum + "... ");
		if (compareOutput(expected, received)) {
			System.err.println("PASSED");
			return 1;
		} else {
			System.err.println("FAILED");
			System.err.println("    Expected: " + formatResult(expected)); 
			System.err.println("    Received: " + formatResult(received)); 
			return 0;
		}
	}

	static int runTestCase(int casenum__) {
		switch(casenum__) {
		case 0: {
			int N                     = 5;
			int[] points              = {1, 3, 5};
			long expected__           = 1;

			return verifyCase(casenum__, expected__, new PolygonTraversal().count(N, points));
		}
		case 1: {
			int N                     = 6;
			int[] points              = {1, 4, 2};
			long expected__           = 1;

			return verifyCase(casenum__, expected__, new PolygonTraversal().count(N, points));
		}
		case 2: {
			int N                     = 7;
			int[] points              = {2, 4, 7};
			long expected__           = 2;

			return verifyCase(casenum__, expected__, new PolygonTraversal().count(N, points));
		}
		case 3: {
			int N                     = 7;
			int[] points              = {1, 2, 3, 4, 6, 5};
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new PolygonTraversal().count(N, points));
		}
		case 4: {
			int N                     = 18;
			int[] points              = {1, 7, 18};
			long expected__           = 4374612736L;

			return verifyCase(casenum__, expected__, new PolygonTraversal().count(N, points));
		}

		// custom cases

/*      case 5: {
			int N                     = ;
			int[] points              = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PolygonTraversal().count(N, points));
		}*/
/*      case 6: {
			int N                     = ;
			int[] points              = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PolygonTraversal().count(N, points));
		}*/
/*      case 7: {
			int N                     = ;
			int[] points              = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PolygonTraversal().count(N, points));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
