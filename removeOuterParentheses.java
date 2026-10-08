class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int opened = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If opened > 0, this is an inner parenthesis, so we append it
                if (opened > 0) {
                    result.append(c);
                }
                opened++;
            } else if (c == ')') {
                opened--;
                // If opened > 0 after decrementing, it's an inner parenthesis, so we append it
                if (opened > 0) {
                    result.append(c);
                }
            }
        }
        
        return result.toString();
    }
}
