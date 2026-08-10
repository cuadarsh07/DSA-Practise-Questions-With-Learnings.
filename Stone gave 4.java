class Solution {
    public boolean winnerSquareGame(int n) {
        // dp[i] represents if the current player can win starting with i stones
        boolean[] dp = new boolean[n + 1];
        
        // Base case is implicitly dp[0] = false
        
        for (int i = 1; i <= n; i++) {
            // Check all possible square numbers we can subtract
            for (int k = 1; k * k <= i; k++) {
                // If making this move leaves the opponent in a losing state, we win
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break; // No need to check other moves for this i
                }
            }
        }
        
        return dp[n];
    }
}
