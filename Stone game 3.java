class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i] will store the maximum score difference a player can get starting from index i
        int[] dp = new int[n + 1];
        
        // Base case: dp[n] is 0 because there are no stones left to pick.
        dp[n] = 0;
        
        // Work backward from the last stone
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;
            int currentSum = 0;
            
            // Try picking 1, 2, or 3 stones
            for (int k = 0; k < 3 && i + k < n; k++) {
                currentSum += stoneValue[i + k];
                // The score difference is our current sum minus the maximum score difference 
                // the opponent can get from the remaining stones.
                dp[i] = Math.max(dp[i], currentSum - dp[i + k + 1]);
            }
        }
        
        // Evaluate the result based on the maximum score difference Alice can achieve from the start
        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}
