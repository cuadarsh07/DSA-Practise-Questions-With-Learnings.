class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];
        
        for (int num : nums) {
            long[] nextDp = new long[k];
            int mod = num % k;
            
            // The single-element subarray
            nextDp[mod]++;
            
            // Extend previous contiguous subarrays
            for (int i = 0; i < k; i++) {
                if (dp[i] > 0) {
                    nextDp[(i * mod) % k] += dp[i];
                }
            }
            
            // Accumulate counts to our answer and swap the DP states
            for (int i = 0; i < k; i++) {
                ans[i] += nextDp[i];
                dp[i] = nextDp[i];
            }
        }
        
        return ans;
    }
}
