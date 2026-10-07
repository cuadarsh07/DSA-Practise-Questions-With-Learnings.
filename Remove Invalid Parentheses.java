import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRemoved = 0;
        int rightRemoved = 0;
        
        // Step 1: Count minimum number of '(' and ')' to be removed
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftRemoved++;
            } else if (c == ')') {
                if (leftRemoved > 0) {
                    leftRemoved--;
                } else {
                    rightRemoved++;
                }
            }
        }
        
        List<String> result = new ArrayList<>();
        dfs(s, 0, leftRemoved, rightRemoved, result);
        return result;
    }
    
    private void dfs(String s, int start, int leftRemoved, int rightRemoved, List<String> result) {
        // If no more parentheses need to be removed, check validity
        if (leftRemoved == 0 && rightRemoved == 0) {
            if (isValid(s)) {
                result.add(s);
            }
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            // Pruning: skip duplicates to avoid generating the same string multiple times
            if (i != start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }
            
            char c = s.charAt(i);
            
            // Try removing the current parenthesis and backtrack
            if (c == '(' || c == ')') {
                String nextStr = s.substring(0, i) + s.substring(i + 1);
                
                if (rightRemoved > 0 && c == ')') {
                    dfs(nextStr, i, leftRemoved, rightRemoved - 1, result);
                } else if (leftRemoved > 0 && c == '(') {
                    dfs(nextStr, i, leftRemoved - 1, rightRemoved, result);
                }
            }
        }
    }
    
    // Helper method to check if a string consists of valid parentheses
    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            // If at any point right parentheses exceed left, it's invalid
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }
}
