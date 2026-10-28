import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[r][c] stores all possible XOR path values to reach cell (r, c)
        Set<Integer>[][] dp = new HashSet[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new HashSet<>();
            }
        }
        
        dp[0][0].add(grid[0][0]);
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                for (int val : dp[r][c]) {
                    // Move Down
                    if (r + 1 < m) {
                        dp[r + 1][c].add(val ^ grid[r + 1][c]);
                    }
                    // Move Right
                    if (c + 1 < n) {
                        dp[r][c + 1].add(val ^ grid[r][c + 1]);
                    }
                }
            }
        }
        
        // Find the minimum XOR value at the bottom-right cell
        int minXor = Integer.MAX_VALUE;
        for (int val : dp[m - 1][n - 1]) {
            minXor = Math.min(minXor, val);
        }
        
        return minXor;
    }
}
