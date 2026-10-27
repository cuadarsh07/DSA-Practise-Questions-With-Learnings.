import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] g = new char[m][n];
        
        // Step 1: Fill the grid with obstacles ('#')
        for (int i = 0; i < m; i++) {
            Arrays.fill(g[i], '#');
        }
        
        // Step 2: Make the first row entirely free ('.')
        Arrays.fill(g[0], '.');
        
        // Step 3: Make the last column entirely free ('.')
        for (int i = 0; i < m; i++) {
            g[i][n - 1] = '.';
        }
        
        // Convert char[][] to String[]
        String[] ans = new String[m];
        for (int i = 0; i < m; i++) {
            ans[i] = new String(g[i]);
        }
        
        return ans;
    }
}
