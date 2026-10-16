class Solution {
    private long[] sum_c;
    private long[] sum_sq;
    private long[] lazy;
    private final int MOD = 1000000007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum_c = new long[4 * n + 1];
        sum_sq = new long[4 * n + 1];
        lazy = new long[4 * n + 1];

        // Values are up to 10^5, so array size of 100005 is sufficient.
        int[] last = new int[100005]; 
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int val = nums[i];
            int l = last[val] + 1; // 1-indexed for segment tree
            int r = i + 1;

            update(1, 1, n, l, r, 1);
            ans = (ans + sum_sq[1]) % MOD;

            last[val] = i + 1;
        }

        return (int) ans;
    }

    private void apply(int node, int start, int end, long val) {
        long len = end - start + 1;
        long valSq = (val * val) % MOD;
        
        // (c + x)^2 = c^2 + 2*c*x + x^2
        long addSq = (2 * val % MOD * sum_c[node]) % MOD;
        addSq = (addSq + valSq * len) % MOD;
        sum_sq[node] = (sum_sq[node] + addSq) % MOD;
        
        long addC = (val * len) % MOD;
        sum_c[node] = (sum_c[node] + addC) % MOD;
        
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void pushDown(int node, int start, int end) {
        if (lazy[node] != 0 && start != end) {
            int mid = start + (end - start) / 2;
            apply(2 * node, start, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, end, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void update(int node, int start, int end, int l, int r, long val) {
        if (l <= start && end <= r) {
            apply(node, start, end, val);
            return;
        }
        
        pushDown(node, start, end);
        int mid = start + (end - start) / 2;
        
        if (l <= mid) {
            update(2 * node, start, mid, l, r, val);
        }
        if (r > mid) {
            update(2 * node + 1, mid + 1, end, l, r, val);
        }
        
        sum_c[node] = (sum_c[2 * node] + sum_c[2 * node + 1]) % MOD;
        sum_sq[node] = (sum_sq[2 * node] + sum_sq[2 * node + 1]) % MOD;
    }
}
