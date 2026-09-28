class Solution {
    public int maxDepth(String s) {
        int maxDepth = 0;
        int currentDepth = 0;
        
        // Loop through each character in the string
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Entering a new set of parentheses increases depth
                currentDepth++;
                maxDepth = Math.max(maxDepth, currentDepth);
            } else if (c == ')') {
                // Exiting a set of parentheses decreases depth
                currentDepth--;
            }
            // Ignore digits and operators like +, -, *, /
        }
        
        return maxDepth;
    }
}
