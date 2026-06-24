class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        int dim = 2 * m;

        long[][] T = new long[dim][dim];

        // State:
        // 0..m-1     -> down[v]
        // m..2m-1    -> up[v]
        //
        // up[v]   = sum down[u] where u < v
        // down[v] = sum up[u]   where u > v

        for (int v = 0; v < m; v++) {
            // up[v]
            int upRow = m + v;
            for (int u = 0; u < v; u++) {
                T[upRow][u] = 1;
            }

            // down[v]
            int downRow = v;
            for (int u = v + 1; u < m; u++) {
                T[downRow][m + u] = 1;
            }
        }

        long[] base = new long[dim];

        // Length = 2
        for (int v = 0; v < m; v++) {
            base[v] = m - 1 - v; // down[v]
            base[m + v] = v;     // up[v]
        }

        long[][] P = matPow(T, n - 2);

        long[] res = matVecMul(P, base);

        long ans = 0;
        for (long x : res) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }

    private long[] matVecMul(long[][] A, long[] v) {
        int n = A.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long cur = 0;
            for (int k = 0; k < n; k++) {
                if (A[i][k] != 0) {
                    cur = (cur + A[i][k] * v[k]) % MOD;
                }
            }
            res[i] = cur;
        }

        return res;
    }

    private long[][] matPow(long[][] A, long exp) {
        int n = A.length;

        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        long[][] cur = A;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, cur);
            }
            cur = multiply(cur, cur);
            exp >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;

                long aik = A[i][k];
                for (int j = 0; j < n; j++) {
                    if (B[k][j] == 0) continue;

                    C[i][j] = (C[i][j] + aik * B[k][j]) % MOD;
                }
            }
        }

        return C;
    }
}
