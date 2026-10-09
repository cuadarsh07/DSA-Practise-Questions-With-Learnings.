class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int rightNeeded = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // Every '(' requires exactly two consecutive ')'.
                // If rightNeeded is odd, it means a previous '(' only found one ')'. 
                // We must insert a missing ')' before starting the new '('.
                if (rightNeeded % 2 != 0) {
                    insertions++;
                    rightNeeded--;
                }
                // Each new '(' requires two right parentheses.
                rightNeeded += 2;
                
            } else { // c == ')'
                rightNeeded--;
                
                // If rightNeeded becomes negative, we have a ')' without a matching '('.
                if (rightNeeded < 0) {
                    // Insert a '(' to match this extra ')'.
                    insertions++;
                    
                    // The inserted '(' needs two ')'. We just used one, so we still need one more.
                    rightNeeded = 1; 
                }
            }
        }
        
        // Add any pending right parentheses that weren't closed by the end of the string.
        return insertions + rightNeeded;
    }
}
