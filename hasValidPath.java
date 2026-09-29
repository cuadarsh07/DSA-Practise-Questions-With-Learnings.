class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // A valid parentheses string must be of even length
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
        
        // A valid sequence must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        // visited[r][c][balance] array for memoization
        // Max balance can never exceed m + n
        boolean[][][] visited = new boolean[m][n][m + n];
        
        return dfs(grid, 0, 0, 0, visited, m, n);
    }
    
    private boolean dfs(char[][] grid, int r, int c, int balance, boolean[][][] visited, int m, int n) {
        // Update balance based on current cell
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }
        
        // Invalid if closing brackets exceed opening brackets
        if (balance < 0) {
            return false;
        }
        
        // Pruning: Invalid if we have more opening brackets than remaining steps to close them
        int remainingSteps = (m - 1 - r) + (n - 1 - c);
        if (balance > remainingSteps) {
            return false;
        }
        
        // If we reached the bottom-right corner, check if balance is perfectly 0
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }
        
        // If we have already visited this cell with the same balance, skip it
        if (visited[r][c][balance]) {
            return false;
        }
        
        // Mark current state as visited
        visited[r][c][balance] = true;
        
        // Explore downwards and rightwards
        if (r + 1 < m && dfs(grid, r + 1, c, balance, visited, m, n)) {
            return true;
        }
        if (c + 1 < n && dfs(grid, r, c + 1, balance, visited, m, n)) {
            return true;
        }
        
        return false;
    }
}
