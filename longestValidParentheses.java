class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxLength = 0;
        
        // 1st Pass: Left to Right
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            
            // When we have an equal number of '(' and ')', it's a valid substring
            if (left == right) {
                maxLength = Math.max(maxLength, 2 * right);
            } 
            // If we have more ')' than '(', the sequence becomes invalid, so we reset
            else if (right > left) {
                left = right = 0;
            }
        }
        
        left = right = 0;
        
        // 2nd Pass: Right to Left
        // This handles cases like "(()" where the left-to-right pass misses the valid substring
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            
            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            } 
            // If we have more '(' than ')', the sequence becomes invalid
            else if (left > right) {
                left = right = 0;
            }
        }
        
        return maxLength;
    }
}
