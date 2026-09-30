class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];
        int depth = 0;
        
        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                // Assign to A (0) or B (1) based on current depth parity
                ans[i] = depth % 2; 
                depth++; // Increase depth after assigning
            } else {
                depth--; // Decrease depth before assigning the closing bracket
                // The closing bracket gets assigned to the same group as its matching open bracket
                ans[i] = depth % 2;
            }
        }
        
        return ans;
    }
}
