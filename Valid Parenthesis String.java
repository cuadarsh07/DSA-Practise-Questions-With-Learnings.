class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0; // Minimum possible open left parentheses
        int maxOpen = 0; // Maximum possible open left parentheses
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { 
                // c == '*'
                minOpen--; // Treat '*' as ')'
                maxOpen++; // Treat '*' as '('
                           // (Treating as empty string means neither changes, which is naturally covered by this range)
            }
            
            // If the maximum possible open parentheses is less than 0, 
            // it means there are too many closing brackets ')' to ever be matched.
            if (maxOpen < 0) {
                return false;
            }
            
            // minOpen can't be negative. If it drops below 0, it just means 
            // we counted a '*' as a ')' but we shouldn't have. We just treat it as an empty string instead.
            if (minOpen < 0) {
                minOpen = 0;
            }
        }
        
        // If the minimum possible open parentheses is 0, then all '(' were matched.
        return minOpen == 0;
    }
}
