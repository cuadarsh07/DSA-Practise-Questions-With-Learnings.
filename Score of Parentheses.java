class Solution {
    public int scoreOfParentheses(String s) {
        int score = 0;
        int depth = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                // If it's an immediate matching parenthesis, it's an innermost pair "()"
                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth; // Adds 2^depth to the total score
                }
            }
        }
        
        return score;
    }
}
