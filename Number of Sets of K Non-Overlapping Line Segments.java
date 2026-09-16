class Solution {
    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int K = 2 * k;
        int MOD = 1_000_000_007;
        
        // We need to calculate N choose K modulo 10^9 + 7.
        // Since N <= 2000, we can easily compute this using Pascal's triangle (1D array optimization)
        int[] dp = new int[K + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= N; i++) {
            // We only need to calculate up to K
            for (int j = Math.min(i, K); j > 0; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }
        
        return dp[K];
    }
}
