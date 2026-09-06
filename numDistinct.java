class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // dp[j] represents the number of distinct subsequences of s 
        // up to the current index that match t[0...j-1]
        int[] dp = new int[n + 1];
        
        // An empty string t can be formed by any prefix of s exactly 1 time
        dp[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            // Traverse backwards to avoid using updated values from the same iteration
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return dp[n];
    }
}
