class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;
        
        // R[i][j] stores the number of paths entering cell (i, j) moving RIGHT
        int[][] R = new int[m][n];
        // D[i][j] stores the number of paths entering cell (i, j) moving DOWN
        int[][] D = new int[m][n];
        
        // Initialize start point (0, 0)
        // From the starting point, the robot can choose to go right or down.
        if (1 < n) R[0][1] = 1;
        if (1 < m) D[1][0] = 1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                
                if (grid[i][j] == 0) { // Empty cell
                    // Total paths arriving at this empty cell
                    long currentPaths = (R[i][j] + D[i][j]) % MOD;
                    
                    if (currentPaths > 0) {
                        // The robot makes a choice to go right
                        if (j + 1 < n) {
                            R[i][j + 1] = (int) ((R[i][j + 1] + currentPaths) % MOD);
                        }
                        // The robot makes a choice to go down
                        if (i + 1 < m) {
                            D[i + 1][j] = (int) ((D[i + 1][j] + currentPaths) % MOD);
                        }
                    }
                } else { // Mirror cell (grid[i][j] == 1)
                    // Moving RIGHT into mirror reflects DOWN
                    if (R[i][j] > 0 && i + 1 < m) {
                        D[i + 1][j] = (int) ((D[i + 1][j] + R[i][j]) % MOD);
                    }
                    // Moving DOWN into mirror reflects RIGHT
                    if (D[i][j] > 0 && j + 1 < n) {
                        R[i][j + 1] = (int) ((R[i][j + 1] + D[i][j]) % MOD);
                    }
                }
            }
        }
        
        return (R[m - 1][n - 1] + D[m - 1][n - 1]) % MOD;
    }
}
