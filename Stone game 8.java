class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int currentPrefix = 0;
        
        // Calculate the total sum of all stones, which represents prefix[n-1]
        for (int i = 0; i < n; i++) {
            currentPrefix += stones[i];
        }
        
        // Base case: If the player is forced to take all remaining stones (index n-1)
        int dp = currentPrefix; 
        
        // Traverse backwards from the second to last index down to 1
        for (int i = n - 2; i >= 1; i--) {
            // currentPrefix becomes prefix[i]
            currentPrefix -= stones[i + 1]; 
            
            // Calculate max score difference from this state
            dp = Math.max(dp, currentPrefix - dp);
        }
        
        // dp now holds the maximum score difference starting from index 1 (Alice's first choice)
        return dp;
    }
}
