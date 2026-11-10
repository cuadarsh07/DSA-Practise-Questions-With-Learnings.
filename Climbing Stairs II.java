class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] represents the minimum cost to reach step i
        int[] dp = new int[n + 1];
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            // Check the 1-step jump
            int minPrev = dp[i - 1] + 1; // 1^2 = 1
            
            // Check the 2-step jump
            if (i >= 2) {
                minPrev = Math.min(minPrev, dp[i - 2] + 4); // 2^2 = 4
            }
            
            // Check the 3-step jump
            if (i >= 3) {
                minPrev = Math.min(minPrev, dp[i - 3] + 9); // 3^2 = 9
            }
            
            // Add the cost of landing on the current step
            // costs is 1-indexed in the problem description, so step i corresponds to costs[i-1]
            dp[i] = minPrev + costs[i - 1];
        }
        
        return dp[n];
    }
}
