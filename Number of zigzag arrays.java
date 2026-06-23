class Solution {
    public int zigZagArrays(int n, int l, int r) {
        final long MOD = 1_000_000_007L;

        int m = r - l + 1;

        if (n == 1) return m;

        long[] up = new long[m + 1];
        long[] down = new long[m + 1];

        // Length = 2
        for (int v = 1; v <= m; v++) {
            up[v] = v - 1;      // previous value < v
            down[v] = m - v;    // previous value > v
        }

        if (n == 2) {
            long ans = 0;
            for (int v = 1; v <= m; v++) {
                ans = (ans + up[v] + down[v]) % MOD;
            }
            return (int) ans;
        }

        for (int len = 3; len <= n; len++) {
            long[] prefixDown = new long[m + 1];
            long[] prefixUp = new long[m + 1];

            for (int v = 1; v <= m; v++) {
                prefixDown[v] = (prefixDown[v - 1] + down[v]) % MOD;
                prefixUp[v] = (prefixUp[v - 1] + up[v]) % MOD;
            }

            long totalUp = prefixUp[m];

            long[] newUp = new long[m + 1];
            long[] newDown = new long[m + 1];

            for (int v = 1; v <= m; v++) {
                // Last move becomes UP, so previous move must be DOWN
                newUp[v] = prefixDown[v - 1];

                // Last move becomes DOWN, so previous move must be UP
                newDown[v] = (totalUp - prefixUp[v] + MOD) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;
        for (int v = 1; v <= m; v++) {
            ans = (ans + up[v] + down[v]) % MOD;
        }

        return (int) ans;
    }
}
